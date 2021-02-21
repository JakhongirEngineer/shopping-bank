package com.mastery.testspringproductmicroservice.services;

import com.mastery.testspringproductmicroservice.dtos.request.PostProductRequestDto;
import com.mastery.testspringproductmicroservice.dtos.response.BulkProductDto;
import com.mastery.testspringproductmicroservice.dtos.response.HighDemandProductDto;
import com.mastery.testspringproductmicroservice.entities.Category;
import com.mastery.testspringproductmicroservice.entities.Product;
import com.mastery.testspringproductmicroservice.repositories.CategoryRepository;
import com.mastery.testspringproductmicroservice.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@AllArgsConstructor // dependency injection via constructor is achieved by AllArgsConstructor Lombok annotation.
@Service
public class ProductService {
    // autowired fields are deliberately made final, so we can be sure that they are injected at runtime
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

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

    public ResponseEntity<String> addProduct(PostProductRequestDto postProductRequestDto) {

        Optional<Category> optionalCategory = categoryRepository.findById(postProductRequestDto.getCategoryId());

        if (optionalCategory.isPresent()){
            Product product = new Product();

            product.setPhoto(postProductRequestDto.getPhoto());
            product.setDescription(postProductRequestDto.getDescription());
            product.setName(postProductRequestDto.getName());
            product.setPrice(postProductRequestDto.getPrice());
            product.setCategory(optionalCategory.get());
            try {
                productRepository.save(product);
                return ResponseEntity.ok().body("SUCCESS");
            } catch (RuntimeException e){
                return ResponseEntity.badRequest().body("FAILED, product could not have been saved");
            }
        }

        return ResponseEntity.badRequest().body("FAILED");
    }
}
