package com.mastery.testspringproductmicroservice.services;

import com.mastery.testspringproductmicroservice.models.dtos.request.CustomerRegisterRequestDto;
import com.mastery.testspringproductmicroservice.models.dtos.response.CustomerLastOrderDto;
import com.mastery.testspringproductmicroservice.models.entities.Customer;
import com.mastery.testspringproductmicroservice.exceptions.FailedToSaveException;
import com.mastery.testspringproductmicroservice.exceptions.ServerErrorException;
import com.mastery.testspringproductmicroservice.repositories.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor // dependency injection via constructor is achieved by AllArgsConstructor Lombok annotation.
@Service
public class CustomerService {
    // autowired fields are deliberately made final, so we can be sure that they are injected at runtime
    private final CustomerRepository customerRepository;

    public List<Customer> findCustomersWhoHaveNotOrderedInYear(int year){
        return customerRepository
                .findCustomersWhoHaveNotOrderedInYear(year)
                .orElseThrow(()->new ServerErrorException("unable to query users who have not ordered in " + year +" year", LocalDateTime.now()));
    }

    public List<CustomerLastOrderDto> findCustomersLastOrder(){
        return customerRepository
                .findCustomersLastOrder()
                .orElseThrow(()->new ServerErrorException("unable to find last orders of customers",LocalDateTime.now()));
    }

    public String registerCustomer(CustomerRegisterRequestDto customerRegisterRequestDto) {
        Customer customer = new Customer();
        customer.setAddress(customerRegisterRequestDto.getAddress());
        customer.setCountry(customerRegisterRequestDto.getCountry());
        customer.setPhone(customerRegisterRequestDto.getPhone());
        customer.setName(customerRegisterRequestDto.getName());
        try {
            customerRepository.save(customer);
            return "SUCCESS";
        }catch (RuntimeException e){
            throw new FailedToSaveException("FAILED",customerRegisterRequestDto.getName(),LocalDateTime.now());
        }
    }
}
