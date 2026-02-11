package com.example.order_service.controller;

import com.example.order_service.dto.Product;
import com.example.order_service.model.Orders;
import com.example.order_service.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderRepository repo;
    private final RestTemplate restTemplate;

    @Value("${product.service.url}")
    private String productUrl;

    @GetMapping
    public List<Orders> all() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public Orders one(@PathVariable Long id) {
        return repo.findById(id).orElseThrow();
    }

    @PostMapping
    public Orders create(@RequestBody Orders order) {

        Product product =
                restTemplate.getForObject(
                        productUrl + "/" + order.getProductId(),
                        Product.class);

        if (product == null || product.getQuantity() < order.getQuantity()) {
            throw new RuntimeException("Product unavailable");
        }

        order.setTotalPrice(product.getPrice() * order.getQuantity());
        order.setStatus("CREATED");

        return repo.save(order);
    }
}
