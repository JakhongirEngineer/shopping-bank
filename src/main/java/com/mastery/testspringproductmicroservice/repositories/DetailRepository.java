package com.mastery.testspringproductmicroservice.repositories;

import com.mastery.testspringproductmicroservice.models.entities.Detail;
import com.mastery.testspringproductmicroservice.models.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DetailRepository extends JpaRepository<Detail,Integer> {
    Optional<Detail> findByOrder(Order order);
}
