package com.brainswords.clubin.participation.controller;

import com.brainswords.clubin.participation.dto.ParticipationResponse;
import com.brainswords.clubin.participation.service.ParticipationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Participation", description = "행사 참가 신청 API")
@RestController
@RequestMapping("/api/events/{eventId}/participations")
@RequiredArgsConstructor
public class ParticipationController {

    private final ParticipationService participationService;

    @Operation(summary = "행사 참가 신청")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ParticipationResponse apply(@PathVariable Long eventId, @AuthenticationPrincipal Long memberId) {
        return participationService.apply(eventId, memberId);
    }

    @Operation(summary = "행사 참가 취소")
    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void cancel(@PathVariable Long eventId, @AuthenticationPrincipal Long memberId) {
        participationService.cancel(eventId, memberId);
    }
}
