package com.mastery.testspringproductmicroservice.repositories;

import com.mastery.testspringproductmicroservice.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {

    @Query("SELECT c from Customer c LEFT JOIN Order_product o ON c.customerId <> o.customer.customerId  WHERE EXTRACT(YEAR FROM o.date) =?1")
    Optional<List<Customer>> findCustomersWhoHaveNotOrderedInYear(int year);
}
