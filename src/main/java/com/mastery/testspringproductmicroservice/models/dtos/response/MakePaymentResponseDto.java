package com.mastery.testspringproductmicroservice.models.dtos.response;

import com.mastery.testspringproductmicroservice.models.entities.Invoice;
import com.mastery.testspringproductmicroservice.models.entities.Payment;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class MakePaymentResponseDto {
    private String paymentStatus;
    private Integer paymentId;
    private LocalDateTime time;
    private BigDecimal amount;
    private Invoice invoice;

    public MakePaymentResponseDto(Payment payment, String paymentStatus){
        this.paymentStatus = paymentStatus;
        this.paymentId = payment.getPaymentId();
        this.time = payment.getTime();
        this.amount = payment.getAmount();
        this.invoice = payment.getInvoice();
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public Integer getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Integer paymentId) {
        this.paymentId = paymentId;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }
}
