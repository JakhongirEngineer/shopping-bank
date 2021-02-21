package com.mastery.testspringproductmicroservice.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "invoice_id")
    private Integer invoiceId;

    @NotNull(message = "amount field in Invoice table cannot be null")
    @Column(precision = 8, scale = 2)
    private BigDecimal amount;

    @NotNull(message = "issued field in Invoice table cannot be null")
    private LocalDate issued;

    @NotNull(message = "due field in Invoice table cannot be null")
    private LocalDate due;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id") // owning side of the relationship between Invoice and Order tables
    @JsonIgnore // resolves infinite recursion
    private Order order;

    @OneToMany(mappedBy = "invoice") // inverse side of the relationship between Invoice and Payment tables
    @JsonIgnore // resolves infinite recursion
    private List<Payment> payments;

    public Integer getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(Integer invoiceId) {
        this.invoiceId = invoiceId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDate getIssued() {
        return issued;
    }

    public void setIssued(LocalDate issued) {
        this.issued = issued;
    }

    public LocalDate getDue() {
        return due;
    }

    public void setDue(LocalDate due) {
        this.due = due;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }
}
