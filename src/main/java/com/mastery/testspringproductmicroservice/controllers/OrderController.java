package com.mastery.testspringproductmicroservice.controllers;

import com.mastery.testspringproductmicroservice.entities.Order;
import com.mastery.testspringproductmicroservice.services.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
public class OrderController {
    // services are be injected here
    private final OrderService orderService;

    @GetMapping("/orders_without_details")
    public List<Order> getOrdersWithoutDetailsBeforeSeptember2016(){
        return orderService.findOrdersWithoutDetailsBeforeSeptember2016();
    }

    @GetMapping("/number_of_products_in_year")
    public List<Order> getTotalOrdersByCountry(){
        return new ArrayList<>();
    }

    @GetMapping("/orders_without_invoices")
    public List<Order> getOrdersWithoutInvoices(){
        return new ArrayList<>();
    }

    @PostMapping("/order")
    public String postOrder(@RequestBody String order){ // DTO must be created
        return "SUCCESS";
    }
}
