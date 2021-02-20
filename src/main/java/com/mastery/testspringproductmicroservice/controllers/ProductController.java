package com.mastery.testspringproductmicroservice.controllers;

import com.mastery.testspringproductmicroservice.dtos.response.BulkProductDto;
import com.mastery.testspringproductmicroservice.dtos.response.HighDemandProductDto;
import com.mastery.testspringproductmicroservice.entities.Product;
import com.mastery.testspringproductmicroservice.services.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
public class ProductController {
    // services are injected here
    private final ProductService productService;

    @GetMapping("/high_demand_products")
    public List<HighDemandProductDto> getHighDemandProducts(){
        return productService.findHighDemandProducts();
    }

    @GetMapping("/bulk_products")
    public List<BulkProductDto> getBulkProducts(){
        return productService.findBulkProducts();
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
