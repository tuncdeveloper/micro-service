package com.ferhat.microservice.product_service.repository;


import com.ferhat.microservice.product_service.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {
    List<Product> findByPriceBetween(Double minPrice, Double maxPrice);

}

