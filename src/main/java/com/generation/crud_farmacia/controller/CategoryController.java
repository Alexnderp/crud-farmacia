package com.generation.crud_farmacia.controller;

import com.generation.crud_farmacia.model.Category;
import com.generation.crud_farmacia.repository.CategoryRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping
    public ResponseEntity<List<Category>> getAll() {
        return ResponseEntity.ok(categoryRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getById(@PathVariable Long id) {
        return categoryRepository.findById(id)
                .map(response -> ResponseEntity.ok(response))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Category> getByName(@PathVariable String name) {
        return categoryRepository.findByNameContainingIgnoreCase(name)
                .map(response -> ResponseEntity.ok(response))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping
    public ResponseEntity<Category> create(@Valid @RequestBody Category category) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(categoryRepository.save(category));
    }

    @PutMapping
    public ResponseEntity<Category> update(@Valid @RequestBody Category category) {
        return categoryRepository.findById(category.getId())
                .map(response -> ResponseEntity.status(HttpStatus.CREATED)
                        .body(categoryRepository.save(category)))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        Optional<Category> category = categoryRepository.findById(id);

        if (category.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        categoryRepository.deleteById(id);
    }

}
