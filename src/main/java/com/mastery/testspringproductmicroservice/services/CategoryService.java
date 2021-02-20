package com.mastery.testspringproductmicroservice.services;

import com.mastery.testspringproductmicroservice.entities.Category;
import com.mastery.testspringproductmicroservice.entities.Product;
import com.mastery.testspringproductmicroservice.repositories.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final ProductService productService;

    public List<Category> getAllCategories(){
        return categoryRepository.findAll();
    }

    public ResponseEntity<Category> getProductCategoryByProductId(int productId){
        Product product = productService.findProductByProductId(productId);

        if (product==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(null);
        }
        Category category = categoryRepository
                .findCategoryByProducts(product)
                .orElseThrow(()->new RuntimeException("error while querying with getProductCategoryByProductId(int productId)"));
        if (category!=null){
            return ResponseEntity.ok()
                    .body(category);
        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }
}
