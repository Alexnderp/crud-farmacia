package com.generation.crud_farmacia.controller;

import com.generation.crud_farmacia.model.Category;
import com.generation.crud_farmacia.model.Product;
import com.generation.crud_farmacia.repository.CategoryRepository;
import com.generation.crud_farmacia.repository.ProductRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
@AllArgsConstructor
public class ProductController {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @GetMapping
    public ResponseEntity<List<Product>> getAll() {
        return ResponseEntity.ok(productRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getById(@PathVariable Long id) {
        return productRepository.findById(id)
                .map(response -> ResponseEntity.ok(response))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("/description/{description}")
    public ResponseEntity<List<Product>> getByDescription(@PathVariable String description) {
        return productRepository.findAllByNameContainingIgnoreCase(description)
                .map(response -> ResponseEntity.ok(response))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping
    public ResponseEntity<Product> create(@Valid @RequestBody Product product) {
        if (categoryRepository.existsById(product.getCategory().getId()))
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(productRepository.save(product));
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Categoria não encontrada", null);
    }

    @PutMapping
    public ResponseEntity<Product> update(@Valid @RequestBody Product product) {
        if (productRepository.existsById(product.getId())) {
            if (categoryRepository.existsById(product.getCategory().getId()))
                return ResponseEntity.status(HttpStatus.OK)
                        .body(productRepository.save(product));

            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Categoria não encontrada", null);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        Optional<Product> product = productRepository.findById(id);

        if (product.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        productRepository.deleteById(id);
    }

}
