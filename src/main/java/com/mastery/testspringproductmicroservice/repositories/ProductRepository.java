package com.mastery.testspringproductmicroservice.repositories;

import com.mastery.testspringproductmicroservice.models.dtos.response.BulkProductDto;
import com.mastery.testspringproductmicroservice.models.dtos.response.HighDemandProductDto;
import com.mastery.testspringproductmicroservice.models.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {

    @Query("SELECT p.productId AS productId, COUNT(p.productId) AS total " +
            "FROM Detail d INNER JOIN Product p ON d.product.productId=p.productId " +
            "GROUP BY p.productId, d.quantity HAVING COUNT(p.productId)>10 ORDER BY d.quantity DESC")
    Optional<List<HighDemandProductDto>> findHighDemandProducts();



    @Query("SELECT distinct p.price AS price,p.productId AS productId " +
            "FROM Product p INNER JOIN Detail d ON d.product.productId=p.productId " +
            "GROUP BY p.productId HAVING AVG(d.quantity)>=8")
    Optional<List<BulkProductDto>> findBulkProducts();
}
