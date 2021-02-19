package com.mastery.testspringproductmicroservice.repositories;

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
}
