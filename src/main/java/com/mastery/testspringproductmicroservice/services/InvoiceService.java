package com.mastery.testspringproductmicroservice.services;

import com.mastery.testspringproductmicroservice.dtos.response.WrongDateResponseDto;
import com.mastery.testspringproductmicroservice.entities.Invoice;
import com.mastery.testspringproductmicroservice.repositories.InvoiceRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class InvoiceService {
    private final InvoiceRepository invoiceRepository;

    public List<Invoice> findExpiredInvoices(){
        return invoiceRepository.findExpiredInvoices().orElseThrow(()->new RuntimeException("error while querying expired invoices"));
    }

    public List<WrongDateResponseDto> findWrongDateInvoices(){
        return invoiceRepository.findWrongDataInvoices().orElseThrow(()->new RuntimeException("error while querying wrong date invoices"));
    }

}