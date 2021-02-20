package com.mastery.testspringproductmicroservice.controllers;

import com.mastery.testspringproductmicroservice.entities.Category;
import com.mastery.testspringproductmicroservice.entities.Product;
import com.mastery.testspringproductmicroservice.services.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/category")
public class CategoryController {
    // services are injected here
    private final CategoryService categoryService;

    @GetMapping("/list")
    public List<Category> getListOfCategories(){
        return categoryService.getAllCategories();
    }
    @GetMapping("/")
    public Category getCategoryDetails(@RequestParam(name = "product_id") int productId){

        var category = new Category();
        var p = new Product();
        p.setProductId(productId);
        category.setProducts(List.of(p));

        return category;
    }

}
