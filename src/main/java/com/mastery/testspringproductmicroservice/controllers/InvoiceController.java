package com.mastery.testspringproductmicroservice.controllers;


import com.mastery.testspringproductmicroservice.dtos.response.OverpaymentDto;
import com.mastery.testspringproductmicroservice.dtos.response.WrongDateResponseDto;
import com.mastery.testspringproductmicroservice.entities.Invoice;
import com.mastery.testspringproductmicroservice.services.InvoiceService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor // dependencies are automatically injected because of Lombok's All arguments constructor
public class InvoiceController {
    // here services are injected
    private final InvoiceService invoiceService;

    @GetMapping("/expired_invoices")
    public List<Invoice> getExpiredInvoices(){
        return invoiceService.findExpiredInvoices();
    }

    @GetMapping("/wrong_data_invoices")
    public List<WrongDateResponseDto> getWrongDataInvoices(){
        return invoiceService.findWrongDateInvoices();
    }

    @GetMapping("/overpaid_invoices")
    public List<OverpaymentDto> getOverpaidInvoices(){
        return invoiceService.findOverpaidInvoices();
    }
}
