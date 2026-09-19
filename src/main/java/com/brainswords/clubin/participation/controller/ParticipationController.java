package com.brainswords.clubin.participation.controller;

import com.brainswords.clubin.participation.dto.AttendanceRequest;
import com.brainswords.clubin.participation.dto.ParticipationResponse;
import com.brainswords.clubin.participation.service.ParticipationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @Operation(summary = "내 참가 신청 상태 조회")
    @GetMapping("/me")
    public ResponseEntity<ParticipationResponse> getMyParticipation(@PathVariable Long eventId,
                                                                      @AuthenticationPrincipal Long memberId) {
        return participationService.getMyParticipation(eventId, memberId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.noContent().build());
    }

    @Operation(summary = "행사 신청자 목록 조회")
    @GetMapping
    public List<ParticipationResponse> getParticipants(@PathVariable Long eventId) {
        return participationService.getParticipants(eventId);
    }

    @Operation(summary = "출석 체크")
    @PatchMapping("/{memberId}/attendance")
    public ParticipationResponse checkAttendance(@PathVariable Long eventId,
                                                  @PathVariable Long memberId,
                                                  @Valid @RequestBody AttendanceRequest request) {
        return participationService.checkAttendance(eventId, memberId, request.getAttendanceStatus());
    }
}
