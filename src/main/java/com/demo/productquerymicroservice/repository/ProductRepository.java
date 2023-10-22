package com.demo.productquerymicroservice.repository;

import com.demo.productquerymicroservice.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
