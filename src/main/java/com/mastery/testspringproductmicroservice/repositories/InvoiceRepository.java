package com.mastery.testspringproductmicroservice.repositories;

import com.mastery.testspringproductmicroservice.dtos.response.WrongDateResponseDto;
import com.mastery.testspringproductmicroservice.entities.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice,Integer> {

    @Query("SELECT i FROM Invoice i WHERE i.issued > i.due")
    Optional<List<Invoice>> findExpiredInvoices();

    @Query("SELECT i.invoiceId, i.issued,o.orderId, o.date FROM Invoice i INNER JOIN order_product o ON i.invoiceId=o.orderId WHERE i.issued<o.date")
    Optional<List<WrongDateResponseDto>> findWrongDataInvoices();
}
