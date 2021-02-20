package com.mastery.testspringproductmicroservice.dtos.response;

public class OverpaymentDto {
    private int invoiceId;
    private double reimbursed;

    public int getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }

    public double getReimbursed() {
        return reimbursed;
    }

    public void setReimbursed(double reimbursed) {
        this.reimbursed = reimbursed;
    }
}
