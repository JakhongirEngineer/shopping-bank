package com.mastery.testspringproductmicroservice.dtos.response;

import java.time.LocalDate;

public interface CustomerLastOrderDto {
    String getName();
    Integer getCustomerId();
    LocalDate getDate();
}
