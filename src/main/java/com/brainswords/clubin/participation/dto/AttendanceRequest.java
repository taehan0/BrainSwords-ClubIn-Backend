package com.brainswords.clubin.participation.dto;

import com.brainswords.clubin.participation.domain.AttendanceStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AttendanceRequest {

    @NotNull(message = "출석 상태는 필수입니다.")
    private AttendanceStatus attendanceStatus;
}
