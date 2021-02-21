package com.mastery.testspringproductmicroservice.models.dtos.response;

import com.mastery.testspringproductmicroservice.models.entities.Detail;

public class OrderDetailsDto {
    private String productName;
    private int detailId;
    private int orderId;
    private int productId;
    private int quantity;

    public OrderDetailsDto(Detail detail, String productName){
        this.detailId = detail.getDetailId();
        this.orderId = detail.getOrder().getOrderId();
        this.productId = detail.getProduct().getProductId();
        this.quantity = detail.getQuantity();
        this.productName = productName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getDetailId() {
        return detailId;
    }

    public void setDetailId(int detailId) {
        this.detailId = detailId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
