package com.mastery.testspringproductmicroservice.exceptions;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ServerErrorException extends RuntimeException{
    private LocalDateTime localDateTime;
    private String attemptName;

    public ServerErrorException(String message, LocalDateTime localDateTime) {
        super(message);
        this.localDateTime = localDateTime;
        this.attemptName = attemptName;
    }
}
