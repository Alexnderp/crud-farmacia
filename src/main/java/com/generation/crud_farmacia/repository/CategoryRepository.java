package com.generation.crud_farmacia.repository;

import com.generation.crud_farmacia.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Optional<Category> findByNameContainingIgnoreCase(@Param("name") String name );
}
