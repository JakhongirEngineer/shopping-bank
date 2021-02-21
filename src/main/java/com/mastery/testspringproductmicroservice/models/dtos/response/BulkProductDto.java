package com.mastery.testspringproductmicroservice.models.dtos.response;

import java.math.BigDecimal;

public interface BulkProductDto {
    Integer getProductId();
    BigDecimal getPrice();
}
