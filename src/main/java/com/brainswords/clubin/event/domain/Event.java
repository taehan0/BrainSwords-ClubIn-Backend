package com.brainswords.clubin.event.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Column(length = 100)
    private String location;

    @Column(nullable = false)
    private LocalDateTime startAt;

    @Column(nullable = false)
    private LocalDateTime endAt;

    private Integer capacity;

    public Event(String title, String content, String location, LocalDateTime startAt, LocalDateTime endAt, Integer capacity) {
        this.title = title;
        this.content = content;
        this.location = location;
        this.startAt = startAt;
        this.endAt = endAt;
        this.capacity = capacity;
    }

    public void update(String title, String content, String location, LocalDateTime startAt, LocalDateTime endAt, Integer capacity) {
        this.title = title;
        this.content = content;
        this.location = location;
        this.startAt = startAt;
        this.endAt = endAt;
        this.capacity = capacity;
    }
}
