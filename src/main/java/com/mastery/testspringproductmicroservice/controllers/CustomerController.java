package com.mastery.testspringproductmicroservice.controllers;

import com.mastery.testspringproductmicroservice.entities.Customer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CustomerController {
    // services are injected here
    @GetMapping("/customers_without_orders")
    public List<Customer> getCustomersWithoutOrders(){
        return new ArrayList<>();
    }

    @GetMapping("/customers_last_orders")
    public List<Customer> getCustomersLastOrder(){
        return new ArrayList<>();
    }
}
