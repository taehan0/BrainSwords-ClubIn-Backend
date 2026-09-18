package com.brainswords.clubin.participation.exception;

public class DuplicateParticipationException extends RuntimeException {

    public DuplicateParticipationException() {
        super("이미 참가 신청한 행사입니다.");
    }
}
