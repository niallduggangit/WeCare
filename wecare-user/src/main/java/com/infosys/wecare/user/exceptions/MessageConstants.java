package com.infosys.wecare.user.exceptions;

public enum MessageConstants {
    USER_NOT_FOUND("user.not.found"),
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
