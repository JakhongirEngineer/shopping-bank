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

    @Query("SELECT c from Customer c LEFT JOIN Order_product o ON c.customerId = o.customer.customerId  WHERE o.customer.customerId is NULL AND EXTRACT(YEAR FROM o.date) =?1  OR o.date IS NULL")
    Optional<List<Customer>> findCustomersWhoHaveNotOrderedInYear(int year);


//    @Query("SELECT  c.name, c.customerId, o.date from Customer c INNER JOIN Order_product o ON o.customer.customerId=c.customerId ORDER BY o.date DESC LIMIT 1")
//    Optional<List<CustomerLastOrderDto>> findCustomersLastOrder();

    // JPQL does not support LIMIT, thus, I had to use Native query below
//    @Query("SELECT c.name, c.customerId, MAX(o.date) FROM Customer c INNER JOIN Order_product o ON o.customer.customerId=c.customerId GROUP BY c.customerId,c.name ")
    @Query(value = "SELECT name,order_product.customer_id AS customerId, MAX(date) AS date FROM customer INNER JOIN order_product ON customer.customer_id=order_product.customer_id GROUP BY order_product.customer_id,name",nativeQuery = true)
    Optional<List<CustomerLastOrderDto>> findCustomersLastOrder();

}
