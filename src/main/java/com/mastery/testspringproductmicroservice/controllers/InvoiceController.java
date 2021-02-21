package com.mastery.testspringproductmicroservice.controllers;


import com.mastery.testspringproductmicroservice.models.dtos.response.OverpaymentDto;
import com.mastery.testspringproductmicroservice.models.dtos.response.WrongDateResponseDto;
import com.mastery.testspringproductmicroservice.models.entities.Invoice;
import com.mastery.testspringproductmicroservice.services.InvoiceService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@AllArgsConstructor // dependencies are automatically injected because of Lombok's All arguments constructor
@RestController
public class InvoiceController {
    // autowired fields are deliberately made final, so we can be sure that they are injected at runtime
    private final InvoiceService invoiceService;

    @GetMapping("/expired_invoices")
    public ResponseEntity<List<Invoice>> getExpiredInvoices(){
        List<Invoice> invoices = invoiceService.findExpiredInvoices();
        return new ResponseEntity<>(invoices, HttpStatus.FOUND);
    }

    @GetMapping("/wrong_data_invoices")
    public ResponseEntity<List<WrongDateResponseDto>> getWrongDataInvoices(){
        List<WrongDateResponseDto> wrongDateResponseDtos = invoiceService.findWrongDateInvoices();
        return new ResponseEntity<>(wrongDateResponseDtos,HttpStatus.FOUND);
    }

    @GetMapping("/overpaid_invoices")
    public ResponseEntity<List<OverpaymentDto>> getOverpaidInvoices(){
        List<OverpaymentDto> overpaymentDtos = invoiceService.findOverpaidInvoices();
        return new ResponseEntity<>(overpaymentDtos,HttpStatus.FOUND);
    }
}
