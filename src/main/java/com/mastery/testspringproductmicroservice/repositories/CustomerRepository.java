package com.mastery.testspringproductmicroservice.repositories;

import com.mastery.testspringproductmicroservice.dtos.response.CustomerLastOrderDto;
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


//    @Query("SELECT  c.name, c.customerId, o.date from Customer c INNER JOIN Order_product o ON o.customer.customerId=c.customerId ORDER BY o.date DESC LIMIT 1")
//    Optional<List<CustomerLastOrderDto>> findCustomersLastOrder();

    // JPQL does not support LIMIT, thus, I had to use Native query below
    @Query(value = "SELECT name, customer.customer_id, date FROM \n" +
            "customer INNER JOIN order_product ON order_product.customer_id=customer.customer_id \n" +
            "ORDER BY date DESC LIMIT 1;",nativeQuery = true)
    Optional<List<CustomerLastOrderDto>> findCustomersLastOrder();

}
