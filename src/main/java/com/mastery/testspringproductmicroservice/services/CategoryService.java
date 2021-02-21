package com.mastery.testspringproductmicroservice.services;

import com.mastery.testspringproductmicroservice.models.entities.Category;
import com.mastery.testspringproductmicroservice.models.entities.Product;
import com.mastery.testspringproductmicroservice.exceptions.FailedToSaveException;
import com.mastery.testspringproductmicroservice.exceptions.ResourceNotFoundException;
import com.mastery.testspringproductmicroservice.repositories.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
            throw new ResourceNotFoundException("Product with the id: "+productId+"+not found");
        }
        Category category = categoryRepository
                .findCategoryByProducts(product)
                .orElseThrow(()->new ResourceNotFoundException("Category not found"));
        if (category!=null){
            return ResponseEntity.ok()
                    .body(category);
        } else {
            throw new ResourceNotFoundException("Category not found");
        }
    }


    public String addCategory(String name){
        Category category = new Category();
        category.setName(name);
        try {
            categoryRepository.save(category);
            return "SUCCESS";
        } catch (RuntimeException e){
            throw new FailedToSaveException("unable to add category",name, LocalDateTime.now());
        }
    }
}
