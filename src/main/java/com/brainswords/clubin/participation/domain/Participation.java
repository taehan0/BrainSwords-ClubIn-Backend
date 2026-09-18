package com.brainswords.clubin.participation.domain;

import com.brainswords.clubin.event.domain.Event;
import com.brainswords.clubin.member.domain.Member;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "participations")
public class Participation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private ParticipationStatus status;

    @Column(nullable = false)
    private LocalDateTime appliedAt;

    public Participation(Member member, Event event) {
        this.member = member;
        this.event = event;
        this.status = ParticipationStatus.APPLIED;
        this.appliedAt = LocalDateTime.now();
    }

    public void apply() {
        this.status = ParticipationStatus.APPLIED;
        this.appliedAt = LocalDateTime.now();
    }

    public void cancel() {
        this.status = ParticipationStatus.CANCELLED;
    }

    public boolean isApplied() {
        return status == ParticipationStatus.APPLIED;
    }
}
