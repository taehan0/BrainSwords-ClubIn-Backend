package com.brainswords.clubin.participation.dto;

import com.brainswords.clubin.participation.domain.AttendanceStatus;
import com.brainswords.clubin.participation.domain.Participation;
import com.brainswords.clubin.participation.domain.ParticipationStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ParticipationResponse {

    private Long id;
    private Long eventId;
    private Long memberId;
    private String loginId;
    private ParticipationStatus status;
    private AttendanceStatus attendanceStatus;
    private LocalDateTime appliedAt;

    public static ParticipationResponse from(Participation participation) {
        return new ParticipationResponse(
                participation.getId(),
                participation.getEvent().getId(),
                participation.getMember().getId(),
                participation.getMember().getLoginId(),
                participation.getStatus(),
                participation.getAttendanceStatus(),
                participation.getAppliedAt()
        );
    }
}
