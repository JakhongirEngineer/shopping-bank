package com.mastery.testspringproductmicroservice.models.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id")
    private Integer paymentId;

    @NotNull(message = "time in Payment table cannot be null")
    private LocalDateTime time;

    @NotNull(message = "amount in Payment table cannot be null")
    @Column(precision = 8, scale = 2)
    private BigDecimal amount;

   @ManyToOne(cascade = CascadeType.ALL) // owners side of the relationship between Invoice and Payment tables
   @JoinColumn(name = "invoice_id")
   private Invoice invoice;

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
