package com.mastery.testspringproductmicroservice.repositories;

import com.mastery.testspringproductmicroservice.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment,Integer> {
}