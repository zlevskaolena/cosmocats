package com.cosmocats.intergalacticmarketplace.service.mapper;

import com.cosmocats.intergalacticmarketplace.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cosmocats.intergalacticmarketplace.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final List<Product> products = new ArrayList<>();

    // Конструктор для додавання кількох продуктів до списку
    public ProductService() {
        products.add(new Product(1L, "Cosmic Widget", "A very shiny widget", 99.99));
        products.add(new Product(2L, "Starry T-shirt", "A t-shirt with stars", 19.99));
    }

    // Створення продукту
    public Product createProduct(Product product) {
        product.setId((long) (products.size() + 1)); // Генерація ID
        products.add(product);
        return product;
    }

    // Отримання всіх продуктів
    public List<Product> getAllProducts() {
        return new ArrayList<>(products);
    }

    // Отримання продукту за ID
    public Optional<Product> getProductById(Long id) {
        return products.stream().filter(product -> product.getId().equals(id)).findFirst();
    }

    // Оновлення продукту
    public Optional<Product> updateProduct(Long id, Product updatedProduct) {
        Optional<Product> existingProduct = getProductById(id);
        if (existingProduct.isPresent()) {
            Product product = existingProduct.get();
            product.setName(updatedProduct.getName());
            product.setDescription(updatedProduct.getDescription());
            product.setPrice(updatedProduct.getPrice());
            return Optional.of(product);
        }
        return Optional.empty();
    }

    // Видалення продукту за ID
    public boolean deleteProduct(Long id) {
        return products.removeIf(product -> product.getId().equals(id));
    }
}
