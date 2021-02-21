package com.mastery.testspringproductmicroservice.exceptions;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FailedToSaveException extends RuntimeException {

    private String itemName;
    private LocalDateTime localDateTime;

    public FailedToSaveException(String message, String itemName, LocalDateTime localDateTime) {
        super(message);
        this.itemName = itemName;
        this.localDateTime = localDateTime;
    }
}
