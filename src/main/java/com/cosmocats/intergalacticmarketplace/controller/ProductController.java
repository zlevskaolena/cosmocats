package com.cosmocats.intergalacticmarketplace.controller;

import com.cosmocats.intergalacticmarketplace.dto.ProductDTO;
import com.cosmocats.intergalacticmarketplace.entity.Product;
import com.cosmocats.intergalacticmarketplace.service.mapper.ProductMapper;
import com.cosmocats.intergalacticmarketplace.service.mapper.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import org.springframework.http.ResponseEntity;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    // Створення продукту
    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(@Valid @RequestBody ProductDTO productDTO) {
        ProductDTO createdProduct = ProductMapper.INSTANCE.productToProductDTO(productService.createProduct(
                ProductMapper.INSTANCE.productDTOToProduct(productDTO)));
        return ResponseEntity.ok(createdProduct);
    }

    // Отримання всіх продуктів
    @GetMapping
    public List<ProductDTO> getAllProducts() {
        return productService.getAllProducts().stream()
                .map(ProductMapper.INSTANCE::productToProductDTO)
                .toList();
    }

    // Отримання продукту за ID
    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProduct(@PathVariable @NotNull Long id) {
        Optional<Product> product = productService.getProductById(id);
        return product.map(p -> ResponseEntity.ok(ProductMapper.INSTANCE.productToProductDTO(p)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Оновлення продукту
    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable Long id, @Valid @RequestBody ProductDTO productDTO) {
        Optional<Product> updatedProduct = productService.updateProduct(id, ProductMapper.INSTANCE.productDTOToProduct(productDTO));
        return updatedProduct.map(p -> ResponseEntity.ok(ProductMapper.INSTANCE.productToProductDTO(p)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Видалення продукту
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
