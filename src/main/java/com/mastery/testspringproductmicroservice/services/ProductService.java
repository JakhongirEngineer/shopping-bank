package com.mastery.testspringproductmicroservice.services;

import com.mastery.testspringproductmicroservice.dtos.response.BulkProductDto;
import com.mastery.testspringproductmicroservice.dtos.response.HighDemandProductDto;
import com.mastery.testspringproductmicroservice.entities.Product;
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

    public List<BulkProductDto> findBulkProducts(){
        return productRepository
                .findBulkProducts()
                .orElseThrow(()->new RuntimeException("error while querying with findBulkProducts()"));
    }

    public Product findProductByProductId(int productId){
        try {
         return productRepository
                    .findById(productId)
                    .orElseThrow(()->new RuntimeException("error while querying with findProductByProductId(int productId)"));

        } catch (RuntimeException e){
            return null;
        }
    }

    public List<Product> findAllProducts(){
        try {
            return productRepository.findAll();

        } catch (RuntimeException e){
            return null;
        }
    }
}
