package com.cosmocats.intergalacticmarketplace.controller;

import com.cosmocats.intergalacticmarketplace.dto.ProductDTO;
import com.cosmocats.intergalacticmarketplace.entity.Product;
import com.cosmocats.intergalacticmarketplace.service.mapper.ProductMapper;
import com.cosmocats.intergalacticmarketplace.service.mapper.ProductService; // Додайте сервіс для взаємодії з продуктами
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;  // Додано автозаповнення для сервісу

    // Отримати всі продукти
    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();  // Метод у сервісі для отримання всіх продуктів
    }

    // Отримати продукт за ID
    @GetMapping("/{id}")
    public ProductDTO getProduct(@PathVariable Long id) {
        Product product = productService.getProductById(id);  // Використовуємо сервіс для отримання продукту
        return ProductMapper.INSTANCE.productToProductDTO(product);  // Маппінг з Product на ProductDTO
    }

    // Створити новий продукт
    @PostMapping
    public ProductDTO createProduct(@RequestBody ProductDTO productDTO) {
        Product product = ProductMapper.INSTANCE.productDTOToProduct(productDTO);  // Маппінг з ProductDTO на Product
        product = productService.createProduct(product);  // Створення продукту через сервіс
        return ProductMapper.INSTANCE.productToProductDTO(product);  // Повернення створеного продукту у вигляді DTO
    }
}

