package com.brainswords.clubin.participation.exception;

public class ParticipationNotFoundException extends RuntimeException {

    public ParticipationNotFoundException() {
        super("참가 신청 내역이 없습니다.");
    }
}
