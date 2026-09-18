package com.brainswords.clubin.participation.service;

import com.brainswords.clubin.event.domain.Event;
import com.brainswords.clubin.event.exception.EventNotFoundException;
import com.brainswords.clubin.event.repository.EventRepository;
import com.brainswords.clubin.member.domain.Member;
import com.brainswords.clubin.member.exception.MemberNotFoundException;
import com.brainswords.clubin.member.repository.MemberRepository;
import com.brainswords.clubin.participation.domain.AttendanceStatus;
import com.brainswords.clubin.participation.domain.Participation;
import com.brainswords.clubin.participation.domain.ParticipationStatus;
import com.brainswords.clubin.participation.dto.ParticipationResponse;
import com.brainswords.clubin.participation.exception.DuplicateParticipationException;
import com.brainswords.clubin.participation.exception.EventFullException;
import com.brainswords.clubin.participation.exception.ParticipationNotFoundException;
import com.brainswords.clubin.participation.repository.ParticipationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ParticipationService {

    private final ParticipationRepository participationRepository;
    private final EventRepository eventRepository;
    private final MemberRepository memberRepository;

    public ParticipationResponse apply(Long eventId, Long memberId) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(EventNotFoundException::new);
        Member member = memberRepository.findById(memberId)
                .orElseThrow(MemberNotFoundException::new);

        Participation participation = participationRepository.findByMemberIdAndEventId(memberId, eventId)
                .orElse(null);

        if (participation != null && participation.isApplied()) {
            throw new DuplicateParticipationException();
        }

        if (event.getCapacity() != null) {
            long appliedCount = participationRepository.countByEventIdAndStatus(eventId, ParticipationStatus.APPLIED);
            if (appliedCount >= event.getCapacity()) {
                throw new EventFullException();
            }
        }

        if (participation == null) {
            participation = participationRepository.save(new Participation(member, event));
        } else {
            participation.apply();
        }

        return ParticipationResponse.from(participation);
    }

    public void cancel(Long eventId, Long memberId) {
        Participation participation = participationRepository.findByMemberIdAndEventId(memberId, eventId)
                .filter(Participation::isApplied)
                .orElseThrow(ParticipationNotFoundException::new);

        participation.cancel();
    }

    @Transactional(readOnly = true)
    public List<ParticipationResponse> getParticipants(Long eventId) {
        if (!eventRepository.existsById(eventId)) {
            throw new EventNotFoundException();
        }

        return participationRepository.findAllByEventId(eventId).stream()
                .map(ParticipationResponse::from)
                .toList();
    }

    public ParticipationResponse checkAttendance(Long eventId, Long memberId, AttendanceStatus attendanceStatus) {
        Participation participation = participationRepository.findByMemberIdAndEventId(memberId, eventId)
                .filter(Participation::isApplied)
                .orElseThrow(ParticipationNotFoundException::new);

        participation.checkAttendance(attendanceStatus);

        return ParticipationResponse.from(participation);
    }
}
