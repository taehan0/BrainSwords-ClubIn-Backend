package com.brainswords.clubin.event.dto;

import com.brainswords.clubin.event.domain.Event;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class EventResponse {

    private Long id;
    private String title;
    private String content;
    private String location;
    private LocalDateTime startAt;
    private LocalDateTime endAt;
    private Integer capacity;

    public static EventResponse from(Event event) {
        return new EventResponse(
                event.getId(),
                event.getTitle(),
                event.getContent(),
                event.getLocation(),
                event.getStartAt(),
                event.getEndAt(),
                event.getCapacity()
        );
    }
}
