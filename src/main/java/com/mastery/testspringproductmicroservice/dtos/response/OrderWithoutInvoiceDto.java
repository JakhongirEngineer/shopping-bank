package com.mastery.testspringproductmicroservice.dtos.response;

import java.math.BigDecimal;
import java.time.LocalDate;

public class OrderWithoutInvoiceDto {
    private int orderId;
    private LocalDate date;
    private BigDecimal totalPrice;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
}
