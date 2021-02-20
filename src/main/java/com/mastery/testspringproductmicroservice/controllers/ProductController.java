package com.mastery.testspringproductmicroservice.controllers;

import com.mastery.testspringproductmicroservice.dtos.response.BulkProductDto;
import com.mastery.testspringproductmicroservice.dtos.response.HighDemandProductDto;
import com.mastery.testspringproductmicroservice.entities.Product;
import com.mastery.testspringproductmicroservice.services.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<List<Product>> getListOfProducts(){
        List<Product> products = productService.findAllProducts();
        if (products.size()<=0){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(null);
        } else {
            return ResponseEntity.ok()
                    .body(products);
        }
    }

    @GetMapping("/product/details")
    public ResponseEntity<Product> getProductDetails(@RequestParam(name = "product_id")int productId){
       Product product = productService.findProductByProductId(productId);
       if (product==null){
           return ResponseEntity.status(HttpStatus.NOT_FOUND)
                   .body(null);
       } else {
           return ResponseEntity.ok()
                   .body(product);
       }
    }

}
