package com.mastery.testspringproductmicroservice.services;

import com.mastery.testspringproductmicroservice.entities.Category;
import com.mastery.testspringproductmicroservice.entities.Product;
import com.mastery.testspringproductmicroservice.repositories.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor // dependency injection via constructor is achieved by AllArgsConstructor Lombok annotation.
@Service
public class CategoryService {
    // autowired fields are deliberately made final, so we can be sure that they are injected at runtime
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


    public ResponseEntity<String> addCategory(String name){
        Category category = new Category();
        category.setName(name);
        try {
            categoryRepository.save(category);
            return ResponseEntity.status(HttpStatus.CREATED).body("SUCCESS");
        } catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("FAILED");
        }
    }
}
