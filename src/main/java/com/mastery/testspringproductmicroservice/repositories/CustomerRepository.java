package com.mastery.testspringproductmicroservice.repositories;

import com.mastery.testspringproductmicroservice.dtos.response.CustomerLastOrderDto;
import com.mastery.testspringproductmicroservice.dtos.response.CustomerLastOrderDtoImpl;
import com.mastery.testspringproductmicroservice.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {

    @Query("SELECT c from Customer c LEFT JOIN Order_product o " +
            "ON c.customerId = o.customer.customerId  " +
            "WHERE o.customer.customerId is NULL AND " +
            "EXTRACT(YEAR FROM o.date) =:year  OR o.date IS NULL")
    Optional<List<Customer>> findCustomersWhoHaveNotOrderedInYear(int year);


    @Query("SELECT c.name AS name, o.customer.customerId AS customerId, MAX(o.date) AS date " +
            "FROM Customer c INNER JOIN Order_product o ON c.customerId = o.customer.customerId " +
            "GROUP BY o.customer.customerId,c.name")
    Optional<List<CustomerLastOrderDto>> findCustomersLastOrder();
}
