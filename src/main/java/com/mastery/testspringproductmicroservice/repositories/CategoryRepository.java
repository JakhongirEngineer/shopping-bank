package com.mastery.testspringproductmicroservice.repositories;

import com.mastery.testspringproductmicroservice.models.entities.Category;
import com.mastery.testspringproductmicroservice.models.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Integer> {

    Optional<Category> findCategoryByProducts(Product product);

}
