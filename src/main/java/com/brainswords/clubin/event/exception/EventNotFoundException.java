package com.brainswords.clubin.event.exception;

public class EventNotFoundException extends RuntimeException {

    public EventNotFoundException() {
        super("존재하지 않는 행사입니다.");
    }
}
