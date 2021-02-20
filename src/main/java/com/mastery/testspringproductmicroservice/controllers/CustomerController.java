package com.mastery.testspringproductmicroservice.controllers;

import com.mastery.testspringproductmicroservice.dtos.response.CustomerLastOrderDto;
import com.mastery.testspringproductmicroservice.entities.Customer;
import com.mastery.testspringproductmicroservice.services.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@RestController
public class CustomerController {
    // services are injected here
    private final CustomerService customerService;


    // I generalized this endpoint. It not only supports year 2016 now.
    @GetMapping("/customers_without_orders/{year}")
    public List<Customer> getCustomersWithoutOrders(@PathVariable(name = "year") int year){
        return customerService.findCustomersWhoHaveNotOrderedInYear(year);
    }


    @GetMapping("/customers_last_orders")
    public List<CustomerLastOrderDto> getCustomersLastOrder(){
        return customerService.findCustomersLastOrder();
    }
}












