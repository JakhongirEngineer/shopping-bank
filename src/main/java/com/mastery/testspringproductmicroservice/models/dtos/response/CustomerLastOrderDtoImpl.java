package com.mastery.testspringproductmicroservice.models.dtos.response;

import lombok.AllArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
public class CustomerLastOrderDtoImpl implements CustomerLastOrderDto {
    private String name;
    private Integer customerId;
    private LocalDate date;

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    @Override
    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
