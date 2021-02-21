package com.mastery.testspringproductmicroservice.services;

import com.mastery.testspringproductmicroservice.models.dtos.request.PostProductRequestDto;
import com.mastery.testspringproductmicroservice.models.dtos.response.BulkProductDto;
import com.mastery.testspringproductmicroservice.models.dtos.response.HighDemandProductDto;
import com.mastery.testspringproductmicroservice.models.entities.Category;
import com.mastery.testspringproductmicroservice.models.entities.Product;
import com.mastery.testspringproductmicroservice.exceptions.FailedToSaveException;
import com.mastery.testspringproductmicroservice.exceptions.ResourceNotFoundException;
import com.mastery.testspringproductmicroservice.exceptions.ServerErrorException;
import com.mastery.testspringproductmicroservice.repositories.CategoryRepository;
import com.mastery.testspringproductmicroservice.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
                .orElseThrow(()-> new ResourceNotFoundException("unable to find high demand products"));
    }

    public List<BulkProductDto> findBulkProducts(){
        return productRepository
                .findBulkProducts()
                .orElseThrow(()->new ResourceNotFoundException("unable to find bulk products"));
    }

    public Product findProductByProductId(int productId){
         return productRepository
                    .findById(productId)
                    .orElseThrow(()->new ServerErrorException("unable to find a product by productId", LocalDateTime.now()));
    }

    public List<Product> findAllProducts(){
        try {
            return productRepository.findAll();
        } catch (RuntimeException e){
            throw new ResourceNotFoundException("unable to find list of products");
        }
    }

    public String addProduct(PostProductRequestDto postProductRequestDto) {

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
                return "SUCCESS";
            } catch (RuntimeException e){
                throw new FailedToSaveException("failed to save a product",postProductRequestDto.getName(),LocalDateTime.now());

            }
        }

        throw new FailedToSaveException("failed to save a product",postProductRequestDto.getName(),LocalDateTime.now());
    }
}
