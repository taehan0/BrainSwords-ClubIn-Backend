package com.brainswords.clubin.participation.exception;

public class EventFullException extends RuntimeException {

    public EventFullException() {
        super("정원이 마감된 행사입니다.");
    }
}
