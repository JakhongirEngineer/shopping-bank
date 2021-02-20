package com.mastery.testspringproductmicroservice.dtos.response;

public class HighDemandProductDto {
    private int productId;
    private int total;

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
