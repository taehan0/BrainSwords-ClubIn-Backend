package com.brainswords.clubin.participation.repository;

import com.brainswords.clubin.participation.domain.Participation;
import com.brainswords.clubin.participation.domain.ParticipationStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ParticipationRepository extends JpaRepository<Participation, Long> {

    Optional<Participation> findByMemberIdAndEventId(Long memberId, Long eventId);

    long countByEventIdAndStatus(Long eventId, ParticipationStatus status);

    List<Participation> findAllByEventId(Long eventId);
}
