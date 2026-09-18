package com.brainswords.clubin.event.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class EventRequest {

    @NotBlank(message = "제목은 필수입니다.")
    @Size(max = 100, message = "제목은 100자 이하여야 합니다.")
    private String title;

    private String content;

    @Size(max = 100, message = "장소는 100자 이하여야 합니다.")
    private String location;

    @NotNull(message = "시작 시각은 필수입니다.")
    private LocalDateTime startAt;

    @NotNull(message = "종료 시각은 필수입니다.")
    private LocalDateTime endAt;

    private Integer capacity;
}
