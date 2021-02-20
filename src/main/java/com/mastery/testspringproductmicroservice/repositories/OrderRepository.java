package com.mastery.testspringproductmicroservice.repositories;

import com.mastery.testspringproductmicroservice.dtos.response.NumberOfProductsInYearDto;
import com.mastery.testspringproductmicroservice.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order,Integer> {

    @Query("SELECT o FROM Order_product o LEFT JOIN Detail d ON d.order.orderId <> o.orderId WHERE o.date<'2016-09-01'")
    Optional<List<Order>> findOrdersWithoutDetailsBeforeSeptember2016();

    @Query("SELECT c.country, sum(o.orderId) AS total_orders FROM Order_product o INNER JOIN Customer c ON c.customerId=o.customer.customerId WHERE EXTRACT(YEAR FROM o.date)=?1 GROUP BY c.country HAVING COUNT(*)>0")
    Optional<List<NumberOfProductsInYearDto>> findNumberOfProductsInYear(int year);

//    SELECT customer.country, sum(order_product.order_id) as total_orders
//FROM order_product INNER JOIN customer
//ON customer.customer_id=order_product.customer_id
//WHERE EXTRACT(YEAR FROM order_product.date)=2016
//GROUP BY customer.country
//HAVING count(*)>0
}
