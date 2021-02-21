package com.mastery.testspringproductmicroservice.controllers;

import com.mastery.testspringproductmicroservice.models.entities.Category;
import com.mastery.testspringproductmicroservice.services.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor // dependency injection via constructor is achieved by AllArgsConstructor Lombok annotation.
@RequestMapping("/category")
@RestController
public class CategoryController {
    // autowired fields are deliberately made final, so we can be sure that they are injected at runtime
    private final CategoryService categoryService;

    @GetMapping("/list")
    public ResponseEntity<List<Category>> getListOfCategories(){
        List<Category> categories = categoryService.getAllCategories();
        return ResponseEntity.ok().body(categories);
    }

    @GetMapping
    public ResponseEntity<Category> getCategoryDetails(@RequestParam(name = "product_id") int productId){
        return categoryService.getProductCategoryByProductId(productId);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addCategory(@RequestParam("name")String categoryName){
        String result = categoryService.addCategory(categoryName);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }
}
