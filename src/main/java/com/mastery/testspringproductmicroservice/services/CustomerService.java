package com.mastery.testspringproductmicroservice.services;

import com.mastery.testspringproductmicroservice.dtos.request.CustomerRegisterRequestDto;
import com.mastery.testspringproductmicroservice.dtos.response.CustomerLastOrderDto;
import com.mastery.testspringproductmicroservice.entities.Customer;
import com.mastery.testspringproductmicroservice.repositories.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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

    public ResponseEntity<String> registerCustomer(CustomerRegisterRequestDto customerRegisterRequestDto) {
        Customer customer = new Customer();
        customer.setAddress(customerRegisterRequestDto.getAddress());
        customer.setCountry(customerRegisterRequestDto.getCountry());
        customer.setPhone(customerRegisterRequestDto.getPhone());
        customer.setName(customerRegisterRequestDto.getName());
        try {
            customerRepository.save(customer);
            return ResponseEntity.ok().body("SUCCESS");
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body("FAILED");
        }
    }
}
