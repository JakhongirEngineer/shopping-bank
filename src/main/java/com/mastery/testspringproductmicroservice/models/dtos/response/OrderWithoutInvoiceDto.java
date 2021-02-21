package com.mastery.testspringproductmicroservice.models.dtos.response;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface OrderWithoutInvoiceDto {
    Integer getOrderId();
    LocalDate getDate();
    BigDecimal getTotalPrice();
}
