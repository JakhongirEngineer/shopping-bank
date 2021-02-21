package com.mastery.testspringproductmicroservice.models.dtos.response;

import java.math.BigDecimal;

public class BulkProductDtoImpl implements BulkProductDto {
    private int productId;
    private BigDecimal price;

    @Override
    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    @Override
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
