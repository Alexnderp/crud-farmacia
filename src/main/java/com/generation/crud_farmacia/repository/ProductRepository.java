package com.generation.crud_farmacia.repository;

import com.generation.crud_farmacia.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<List<Product>> findAllByNameContainingIgnoreCase(@Param("name")String name);
}
