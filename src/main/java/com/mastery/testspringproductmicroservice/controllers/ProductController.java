package com.mastery.testspringproductmicroservice.controllers;

import com.mastery.testspringproductmicroservice.models.dtos.request.PostProductRequestDto;
import com.mastery.testspringproductmicroservice.models.dtos.response.BulkProductDto;
import com.mastery.testspringproductmicroservice.models.dtos.response.HighDemandProductDto;
import com.mastery.testspringproductmicroservice.models.entities.Product;
import com.mastery.testspringproductmicroservice.services.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor // dependencies are automatically injected because of Lombok's All arguments constructor
@RestController
public class ProductController {
    // autowired fields are deliberately made final, so we can be sure that they are injected at runtime
    private final ProductService productService;

    @GetMapping("/high_demand_products")
    public ResponseEntity<List<HighDemandProductDto>> getHighDemandProducts(){
        List<HighDemandProductDto> highDemandProductDtos = productService.findHighDemandProducts();
        return new ResponseEntity<>(highDemandProductDtos,HttpStatus.FOUND);
    }

    @GetMapping("/bulk_products")
    public ResponseEntity<List<BulkProductDto>> getBulkProducts(){
        List<BulkProductDto> bulkProductDtos = productService.findBulkProducts();
        return new ResponseEntity<>(bulkProductDtos, HttpStatus.FOUND);
    }

    @GetMapping("/product/list")
    public ResponseEntity<List<Product>> getListOfProducts(){
        List<Product> products = productService.findAllProducts();
        return new ResponseEntity<>(products,HttpStatus.FOUND);
    }

    @GetMapping("/product/details")
    public ResponseEntity<Product> getProductDetails(@RequestParam(name = "product_id")int productId){
       Product product = productService.findProductByProductId(productId);
       return new ResponseEntity<>(product,HttpStatus.FOUND);
    }

    @PostMapping("/product/add")
    public ResponseEntity<String> addProduct(@RequestBody PostProductRequestDto postProductRequestDto){
        String result = productService.addProduct(postProductRequestDto);
        return new ResponseEntity<>(result,HttpStatus.CREATED);
    }
}
