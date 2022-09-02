package com.infosys.wecare.coach.exceptions;

public enum MessageConstants {
    COACH_NOT_FOUND("coach.not.found"),

    NO_SUCH_BOOKING_EXISTS("no.such.booking.exists"),
    SERVER_ERROR("server.error");
    private String message;
    MessageConstants(String s) {
        this.message = s;
    }

    @Override
    public String toString(){
        return message;
    }
}
