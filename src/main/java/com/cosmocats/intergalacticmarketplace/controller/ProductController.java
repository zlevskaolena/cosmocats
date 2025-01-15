package com.cosmocats.intergalacticmarketplace.controller;

import com.cosmocats.intergalacticmarketplace.dto.ProductDTO;
import com.cosmocats.intergalacticmarketplace.entity.Product;
import com.cosmocats.intergalacticmarketplace.service.mapper.ProductMapper;
import com.cosmocats.intergalacticmarketplace.service.mapper.ProductService; // Додайте сервіс для взаємодії з продуктами
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // Отримання всіх продуктів
    @GetMapping
    public List<ProductDTO> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return ProductMapper.INSTANCE.productsToProductDTOs(products);
    }

    // Отримання продукту за ID
    @GetMapping("/{id}")
    public ProductDTO getProduct(@PathVariable Long id) {
        Optional<Product> product = productService.getProductById(id);
        return product.map(ProductMapper.INSTANCE::productToProductDTO).orElse(null);
    }

    // Створення нового продукту
    @PostMapping
    public ProductDTO createProduct(@RequestBody ProductDTO productDTO) {
        Product product = ProductMapper.INSTANCE.productDTOToProduct(productDTO);
        product = productService.createProduct(product);
        return ProductMapper.INSTANCE.productToProductDTO(product);
    }

    // Оновлення продукту
    @PutMapping("/{id}")
    public ProductDTO updateProduct(@PathVariable Long id, @RequestBody ProductDTO productDTO) {
        Product product = ProductMapper.INSTANCE.productDTOToProduct(productDTO);
        Optional<Product> updatedProduct = productService.updateProduct(id, product);
        return updatedProduct.map(ProductMapper.INSTANCE::productToProductDTO).orElse(null);
    }

    // Видалення продукту
    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable Long id) {
        boolean deleted = productService.deleteProduct(id);
        return deleted ? "Product deleted successfully" : "Product not found";
    }
}
