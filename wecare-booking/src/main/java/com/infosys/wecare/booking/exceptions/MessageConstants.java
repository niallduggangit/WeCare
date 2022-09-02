package com.infosys.wecare.booking.exceptions;

public enum MessageConstants {
    BOOKING_ALREADY_EXISTS("booking.already.exists"),

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
