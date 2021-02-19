package com.mastery.testspringproductmicroservice.controllers;


import com.mastery.testspringproductmicroservice.entities.Invoice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
public class InvoiceController {
    // here services are injected
    @GetMapping("/expired_invoices")
    public List<Invoice> getExpiredInvoices(){

        // dummy data to hit the endpoint
        ArrayList<Invoice> exp = new ArrayList<>();
        Invoice invoice = new Invoice();

        invoice.setAmount(BigDecimal.valueOf(10));
        invoice.setDue(LocalDate.now());
        invoice.setIssued(LocalDate.now());

        exp.add(invoice);

        return exp;
    }

    @GetMapping("/wrong_data_invoices")
    public List<Invoice> getWrongDataInvoices(){
        // DTO needs to be created
        ArrayList<Invoice> exp = new ArrayList<>();
        Invoice invoice = new Invoice();

        invoice.setAmount(BigDecimal.valueOf(10));
        invoice.setDue(LocalDate.now());
        invoice.setIssued(LocalDate.now());

        exp.add(invoice);

        return exp;
    }

    @GetMapping("/overpaid_invoices")
    public List<Invoice> getOverpaidInvoices(){
        // requires DTO
        ArrayList<Invoice> exp1 = new ArrayList<>();
        Invoice invoice = new Invoice();

        invoice.setAmount(BigDecimal.valueOf(20));
        invoice.setDue(LocalDate.now());
        invoice.setIssued(LocalDate.now());

        exp1.add(invoice);

        return exp1;
    }


}
