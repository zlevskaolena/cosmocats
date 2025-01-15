package com.cosmocats.intergalacticmarketplace.controller;

import com.cosmocats.intergalacticmarketplace.entity.Product;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @GetMapping
    public List<Product> getAllProducts() {
        return List.of(
                new Product(1L, "Cosmic Widget", "A very shiny widget", 99.99),
                new Product(2L, "Starry T-shirt", "A t-shirt with stars", 19.99)
        );
    }
}
