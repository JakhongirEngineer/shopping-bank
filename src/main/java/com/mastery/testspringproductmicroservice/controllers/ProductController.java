package com.mastery.testspringproductmicroservice.controllers;

import com.mastery.testspringproductmicroservice.entities.Product;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController {
    // services are injected here

    @GetMapping("/high_demand_products")
    public List<Product> getHighDemandProducts(){

        // DUMMY DATA
        ArrayList<Product> products = new ArrayList<>();
        Product p = new Product();
        p.setName("laptop");
        p.setDescription("blazingly fast modern laptop");
        p.setPhoto("www.google.com/jibrishjibrish");
        products.add(p);
        return products;
    }

    @GetMapping("/bulk_products")
    public List<Product> getBulkProducts(){
        // DUMMY DATA
        ArrayList<Product> products = new ArrayList<>();
        Product p = new Product();
        p.setName("APPLE");
        p.setDescription("an apple a day keeps the doctor away");
        p.setPhoto("www.google.com/apple");
        products.add(p);
        return products;
    }

    @GetMapping("/product/list")
    public List<Product> getListOfProducts(){
        // DUMMY DATA
        ArrayList<Product> products = new ArrayList<>();
        Product p = new Product();
        p.setName("product");
        p.setDescription("list of products is called");
        p.setPhoto("www.google.com/apple");
        products.add(p);
        return products;
    }

    @GetMapping("/product/details")
    public Product getProductDetails(@RequestParam(name = "product_id")int productId){
       // DUMMY DATA
        Product p = new Product();
        p.setName("product");
        p.setDescription("list of products is called");
        p.setPhoto("www.google.com/apple");
        p.setProductId(productId);
        return p;
    }

}
