package com.mastery.testspringproductmicroservice.services;

import com.mastery.testspringproductmicroservice.dtos.response.NumberOfProductsInYearDto;
import com.mastery.testspringproductmicroservice.entities.Order;
import com.mastery.testspringproductmicroservice.repositories.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    public List<Order> findOrdersWithoutDetailsBeforeSeptember2016(){
        return orderRepository
                .findOrdersWithoutDetailsBeforeSeptember2016()
                .orElseThrow(()->new RuntimeException("error while querying findOrdersWithoutDetailsBeforeSeptember2016"));
    }

    public List<NumberOfProductsInYearDto> findNumberOfProductsInYear(int year){
        return orderRepository
                .findNumberOfProductsInYear(year)
                .orElseThrow(()->new RuntimeException("error while querying findNumberOfProductsInYear(int year)"));
    }
}
