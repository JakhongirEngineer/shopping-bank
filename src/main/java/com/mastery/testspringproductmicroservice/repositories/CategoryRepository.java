package com.mastery.testspringproductmicroservice.repositories;

import com.mastery.testspringproductmicroservice.entities.Category;
import com.mastery.testspringproductmicroservice.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Integer> {

    Optional<Category> findCategoryByProducts(Product product);

}
