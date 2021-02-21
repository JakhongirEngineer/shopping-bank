package com.mastery.testspringproductmicroservice.services;

import com.mastery.testspringproductmicroservice.models.dtos.response.OverpaymentDto;
import com.mastery.testspringproductmicroservice.models.dtos.response.WrongDateResponseDto;
import com.mastery.testspringproductmicroservice.models.entities.Invoice;
import com.mastery.testspringproductmicroservice.exceptions.ServerErrorException;
import com.mastery.testspringproductmicroservice.repositories.InvoiceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@AllArgsConstructor // dependency injection via constructor is achieved by AllArgsConstructor Lombok annotation.
@Service
public class InvoiceService {
    // autowired fields are deliberately made final, so we can be sure that they are injected at runtime
    private final InvoiceRepository invoiceRepository;

    public List<Invoice> findExpiredInvoices(){
        return invoiceRepository
                .findExpiredInvoices()
                .orElseThrow(()->new ServerErrorException("unable to find expired invoices", LocalDateTime.now()));
    }

    public List<WrongDateResponseDto> findWrongDateInvoices(){
        return invoiceRepository
                .findWrongDataInvoices()
                .orElseThrow(()->new ServerErrorException("unable to find wrong date invoices",LocalDateTime.now()));
    }

    public List<OverpaymentDto> findOverpaidInvoices(){
        return invoiceRepository
                .findOverpaidInvoices()
                .orElseThrow(()-> new ServerErrorException("unable to find overpaid invoices", LocalDateTime.now()));
    }
}
