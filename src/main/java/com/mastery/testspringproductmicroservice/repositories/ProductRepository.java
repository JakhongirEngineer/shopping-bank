package com.mastery.testspringproductmicroservice.repositories;

import com.mastery.testspringproductmicroservice.dtos.response.HighDemandProductDto;
import com.mastery.testspringproductmicroservice.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {


    @Query(value = "SELECT product.product_id, COUNT(*) AS total FROM detail INNER JOIN product \n" +
            "ON detail.product_id=product.product_id\n" +
            "GROUP BY product.product_id, detail.quantity\n" +
            "HAVING COUNT(product.product_id)>10\n" +
            "ORDER BY detail.quantity DESC",
           nativeQuery = true)
    Optional<List<HighDemandProductDto>> findHighDemandProducts();
}
