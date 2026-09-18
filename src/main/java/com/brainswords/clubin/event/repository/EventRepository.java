package com.brainswords.clubin.event.repository;

import com.brainswords.clubin.event.domain.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
}
