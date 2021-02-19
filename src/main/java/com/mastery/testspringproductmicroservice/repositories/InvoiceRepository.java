package com.mastery.testspringproductmicroservice.repositories;

import com.mastery.testspringproductmicroservice.entities.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice,Integer> {
}
