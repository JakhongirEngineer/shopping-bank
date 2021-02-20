package com.mastery.testspringproductmicroservice.dtos.response;

import java.math.BigDecimal;

public class BulkProductDto {
    private int productId;
    private BigDecimal price;

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
