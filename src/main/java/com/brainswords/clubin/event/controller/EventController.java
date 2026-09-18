package com.brainswords.clubin.event.controller;

import com.brainswords.clubin.event.dto.EventRequest;
import com.brainswords.clubin.event.dto.EventResponse;
import com.brainswords.clubin.event.service.EventService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Event", description = "행사 API")
@RestController
@RequestMapping("/api/events")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;

    @Operation(summary = "행사 등록")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EventResponse createEvent(@Valid @RequestBody EventRequest request) {
        return eventService.createEvent(request);
    }

    @Operation(summary = "행사 수정")
    @PutMapping("/{eventId}")
    public EventResponse updateEvent(@PathVariable Long eventId, @Valid @RequestBody EventRequest request) {
        return eventService.updateEvent(eventId, request);
    }

    @Operation(summary = "행사 삭제")
    @DeleteMapping("/{eventId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEvent(@PathVariable Long eventId) {
        eventService.deleteEvent(eventId);
    }

    @Operation(summary = "행사 상세 조회")
    @GetMapping("/{eventId}")
    public EventResponse getEvent(@PathVariable Long eventId) {
        return eventService.getEvent(eventId);
    }

    @Operation(summary = "행사 목록 조회")
    @GetMapping
    public List<EventResponse> getEvents() {
        return eventService.getEvents();
    }
}
