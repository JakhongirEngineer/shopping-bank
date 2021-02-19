package com.mastery.testspringproductmicroservice.controllers;

import com.mastery.testspringproductmicroservice.entities.Category;
import com.mastery.testspringproductmicroservice.entities.Product;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    // services are injected here

    @GetMapping("/list")
    public List<Category> getListOfCategories(){
        // DUMMY DATA
        ArrayList<Category> categories = new ArrayList<>();
        categories.add(new Category());
        return categories;
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
