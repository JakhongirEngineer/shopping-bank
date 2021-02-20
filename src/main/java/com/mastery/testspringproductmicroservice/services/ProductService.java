package com.mastery.testspringproductmicroservice.services;

import com.mastery.testspringproductmicroservice.dtos.response.HighDemandProductDto;
import com.mastery.testspringproductmicroservice.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public List<HighDemandProductDto> findHighDemandProducts(){
        return productRepository
                .findHighDemandProducts()
                .orElseThrow(()-> new RuntimeException("error while querying with findHighDemandProducts()"));
    }
}
