package com.example.product_service.controller;

import com.example.product_service.model.Product;
import com.example.product_service.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductRepository repo;

    @GetMapping
    public List<Product> all() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public Product one(@PathVariable Long id) {
        return repo.findById(id).orElseThrow();
    }

    @PostMapping
    public Product create(@RequestBody Product p) {
        return repo.save(p);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repo.deleteById(id);
    }
}
