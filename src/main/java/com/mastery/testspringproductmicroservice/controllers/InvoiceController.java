package com.mastery.testspringproductmicroservice.controllers;


import com.mastery.testspringproductmicroservice.dtos.response.OverpaymentDto;
import com.mastery.testspringproductmicroservice.dtos.response.WrongDateResponseDto;
import com.mastery.testspringproductmicroservice.entities.Invoice;
import com.mastery.testspringproductmicroservice.services.InvoiceService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@AllArgsConstructor // dependencies are automatically injected because of Lombok's All arguments constructor
@RestController
public class InvoiceController {
    // autowired fields are deliberately made final, so we can be sure that they are injected at runtime
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
