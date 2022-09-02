package com.infosys.wecare.coach.exceptions;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ErrorMessage {
    private int errorCode;
    private String message;
    private LocalDateTime localDateTime;
}
