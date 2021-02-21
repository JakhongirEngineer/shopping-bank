package com.mastery.testspringproductmicroservice.repositories;

import com.mastery.testspringproductmicroservice.dtos.response.NumberOfProductsInYearDto;
import com.mastery.testspringproductmicroservice.dtos.response.NumberOfProductsInYearDtoImpl;
import com.mastery.testspringproductmicroservice.dtos.response.OrderWithoutInvoiceDto;
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

    @Query("SELECT c.country as country, COUNT(o.orderId) AS total FROM Order_product o INNER JOIN Customer c ON c.customerId=o.customer.customerId WHERE EXTRACT(YEAR FROM o.date)=?1 GROUP BY c.country HAVING COUNT(*)>0 ORDER BY COUNT(o.orderId) DESC")
    Optional<List<NumberOfProductsInYearDto>> findNumberOfProductsInYear(int year);

    @Query("SELECT o FROM Order_product o INNER JOIN Detail d ON d.order.orderId = o.orderId LEFT JOIN Invoice i ON i.order.orderId<>o.orderId INNER JOIN Product p ON p.productId=d.product.productId")
    Optional<List<OrderWithoutInvoiceDto>> findOrdersWithoutInvoice();
}
