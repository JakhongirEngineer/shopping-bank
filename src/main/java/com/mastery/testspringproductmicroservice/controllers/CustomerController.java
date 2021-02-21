package com.mastery.testspringproductmicroservice.controllers;

import com.mastery.testspringproductmicroservice.models.dtos.request.CustomerRegisterRequestDto;
import com.mastery.testspringproductmicroservice.models.dtos.response.CustomerLastOrderDto;
import com.mastery.testspringproductmicroservice.models.entities.Customer;
import com.mastery.testspringproductmicroservice.services.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor // dependency injection via constructor is achieved by AllArgsConstructor Lombok annotation.
@RestController
public class CustomerController {
    // autowired fields are deliberately made final, so we can be sure that they are injected at runtime
    private final CustomerService customerService;

    // I generalized this endpoint. It not only supports year 2016 now.
    @GetMapping("/customers_without_orders/{year}")
    public ResponseEntity<List<Customer>> getCustomersWithoutOrders(@PathVariable(name = "year") int year){
        List<Customer> customers = customerService.findCustomersWhoHaveNotOrderedInYear(year);
        return new ResponseEntity<>(customers, HttpStatus.FOUND);
    }

    @GetMapping("/customers_last_orders")
    public ResponseEntity<List<CustomerLastOrderDto>> getCustomersLastOrder(){
        List<CustomerLastOrderDto> customerLastOrderDtos = customerService.findCustomersLastOrder();
        return new ResponseEntity<>(customerLastOrderDtos,HttpStatus.FOUND);
    }

    @PostMapping("/customers/register")
    public ResponseEntity<String> registerCustomer(@RequestBody CustomerRegisterRequestDto customerRegisterRequestDto){
        String result = customerService.registerCustomer(customerRegisterRequestDto);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }
}












