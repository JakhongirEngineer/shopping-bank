package com.mastery.testspringproductmicroservice.services;

import com.mastery.testspringproductmicroservice.dtos.response.CustomerLastOrderDto;
import com.mastery.testspringproductmicroservice.entities.Customer;
import com.mastery.testspringproductmicroservice.repositories.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;

    public List<Customer> findCustomersWhoHaveNotOrderedInYear(int year){
        return customerRepository
                .findCustomersWhoHaveNotOrderedInYear(year)
                .orElseThrow(()->new RuntimeException("error while querying  with findCustomersWhoHaveNotOrderedInYear()"));
    }

    public List<CustomerLastOrderDto> findCustomersLastOrder(){
        return customerRepository
                .findCustomersLastOrder()
                .orElseThrow(()->new RuntimeException("error while querying with findCustomersLastOrder()"));
    }
}
