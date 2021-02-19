package com.mastery.testspringproductmicroservice.dtos.response;

import java.time.LocalDate;

public class WrongDateResponseDto {
    private int invoiceId;
    private LocalDate issued;

    private int orderId;
    private LocalDate date;

    public WrongDateResponseDto() {
    }

    public WrongDateResponseDto(int invoiceId, LocalDate issued, int orderId, LocalDate date) {
        this.invoiceId = invoiceId;
        this.issued = issued;
        this.orderId = orderId;
        this.date = date;
    }


    public int getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }

    public LocalDate getIssued() {
        return issued;
    }

    public void setIssued(LocalDate issued) {
        this.issued = issued;
    }

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
}
