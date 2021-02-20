package com.mastery.testspringproductmicroservice.dtos.response;

public class OrderResponseDto {
    private String status;
    private int invoiceId;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }
}
