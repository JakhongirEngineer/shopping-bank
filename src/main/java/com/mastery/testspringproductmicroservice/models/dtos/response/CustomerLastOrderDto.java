package com.mastery.testspringproductmicroservice.models.dtos.response;

import java.time.LocalDate;

public interface CustomerLastOrderDto {
    String getName();
    Integer getCustomerId();
    LocalDate getDate();
}
