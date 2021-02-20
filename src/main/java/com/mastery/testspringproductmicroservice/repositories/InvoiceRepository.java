package com.mastery.testspringproductmicroservice.repositories;

import com.mastery.testspringproductmicroservice.dtos.response.OverpaymentDto;
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

    @Query("SELECT i.invoiceId, i.issued,o.orderId, o.date FROM Invoice i INNER JOIN Order_product o ON i.invoiceId=o.orderId WHERE i.issued<o.date")
    Optional<List<WrongDateResponseDto>> findWrongDataInvoices();

//    select payment.invoice_id, sum(payment.amount) as reimbursed
//from payment inner join invoice on payment.invoice_id = invoice.invoice_id
//group by payment.invoice_id
//having count(*)>1;
    @Query("SELECT p.invoice.invoiceId, SUM(p.amount) AS reimbursement FROM Payment p INNER JOIN Invoice i ON p.invoice.invoiceId = i.invoiceId GROUP BY p.invoice.invoiceId HAVING COUNT(*)>1")
    Optional<List<OverpaymentDto>> findOverpaidInvoices();
}
