package com.mastery.testspringproductmicroservice.services;

import com.mastery.testspringproductmicroservice.dtos.response.OverpaymentDto;
import com.mastery.testspringproductmicroservice.dtos.response.WrongDateResponseDto;
import com.mastery.testspringproductmicroservice.entities.Invoice;
import com.mastery.testspringproductmicroservice.repositories.InvoiceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@AllArgsConstructor // dependency injection via constructor is achieved by AllArgsConstructor Lombok annotation.
@Service
public class InvoiceService {
    // autowired fields are deliberately made final, so we can be sure that they are injected at runtime
    private final InvoiceRepository invoiceRepository;

    public List<Invoice> findExpiredInvoices(){
        return invoiceRepository
                .findExpiredInvoices()
                .orElseThrow(()->new RuntimeException("error while querying with findExpiredInvoices()"));
    }

    public List<WrongDateResponseDto> findWrongDateInvoices(){
        return invoiceRepository
                .findWrongDataInvoices()
                .orElseThrow(()->new RuntimeException("error while querying with findWrongDateInvoices()"));
    }

    public List<OverpaymentDto> findOverpaidInvoices(){
        return invoiceRepository
                .findOverpaidInvoices()
                .orElseThrow(()-> new RuntimeException("error while querying with findOverpaidInvoices()"));
    }
}
