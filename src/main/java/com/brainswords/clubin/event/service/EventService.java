package com.brainswords.clubin.event.service;

import com.brainswords.clubin.event.domain.Event;
import com.brainswords.clubin.event.dto.EventRequest;
import com.brainswords.clubin.event.dto.EventResponse;
import com.brainswords.clubin.event.exception.EventNotFoundException;
import com.brainswords.clubin.event.repository.EventRepository;
import com.brainswords.clubin.participation.repository.ParticipationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class EventService {

    private final EventRepository eventRepository;
    private final ParticipationRepository participationRepository;

    public EventResponse createEvent(EventRequest request) {
        Event event = new Event(request.getTitle(), request.getContent(), request.getLocation(),
                request.getStartAt(), request.getEndAt(), request.getCapacity());
        Event savedEvent = eventRepository.save(event);

        return EventResponse.from(savedEvent);
    }

    public EventResponse updateEvent(Long eventId, EventRequest request) {
        Event event = getEventOrThrow(eventId);
        event.update(request.getTitle(), request.getContent(), request.getLocation(),
                request.getStartAt(), request.getEndAt(), request.getCapacity());

        return EventResponse.from(event);
    }

    public void deleteEvent(Long eventId) {
        Event event = getEventOrThrow(eventId);
        participationRepository.deleteAll(participationRepository.findAllByEventId(eventId));
        eventRepository.delete(event);
    }

    @Transactional(readOnly = true)
    public EventResponse getEvent(Long eventId) {
        return EventResponse.from(getEventOrThrow(eventId));
    }

    @Transactional(readOnly = true)
    public List<EventResponse> getEvents() {
        return eventRepository.findAll().stream()
                .map(EventResponse::from)
                .toList();
    }

    private Event getEventOrThrow(Long eventId) {
        return eventRepository.findById(eventId)
                .orElseThrow(EventNotFoundException::new);
    }
}
