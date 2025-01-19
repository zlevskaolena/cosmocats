package com.cosmocats.intergalacticmarketplace;


import com.cosmocats.intergalacticmarketplace.entity.Product;
import com.cosmocats.intergalacticmarketplace.service.mapper.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

class ProductServiceTest {

    private ProductService productService;

    @BeforeEach
    void setUp() {
        productService = new ProductService();
    }

    @Test
    void testCreateProduct() {
        Product newProduct = new Product(null, "Galaxy Mug", "A mug with galaxy design", 15.99);
        Product createdProduct = productService.createProduct(newProduct);
        assertNotNull(createdProduct.getId());
        assertEquals("Galaxy Mug", createdProduct.getName());
    }

    @Test
    void testGetAllProducts() {
        List<Product> products = productService.getAllProducts();
        assertEquals(2, products.size());
    }

    @Test
    void testGetProductByIdExists() {
        Optional<Product> product = productService.getProductById(1L);
        assertTrue(product.isPresent());
        assertEquals("Cosmic Widget", product.get().getName());
    }

    @Test
    void testGetProductByIdNotExists() {
        Optional<Product> product = productService.getProductById(99L);
        assertFalse(product.isPresent());
    }

    @Test
    void testUpdateProductExists() {
        Product updatedProduct = new Product(null, "Updated Widget", "Updated description", 79.99);
        Optional<Product> product = productService.updateProduct(1L, updatedProduct);
        assertTrue(product.isPresent());
        assertEquals("Updated Widget", product.get().getName());
    }

    @Test
    void testUpdateProductNotExists() {
        Product updatedProduct = new Product(null, "Nonexistent", "Description", 50.0);
        Optional<Product> product = productService.updateProduct(99L, updatedProduct);
        assertFalse(product.isPresent());
    }

    @Test
    void testDeleteProductExists() {
        boolean isDeleted = productService.deleteProduct(1L);
        assertTrue(isDeleted);
        assertEquals(1, productService.getAllProducts().size());
    }

    @Test
    void testDeleteProductNotExists() {
        boolean isDeleted = productService.deleteProduct(99L);
        assertFalse(isDeleted);
        assertEquals(2, productService.getAllProducts().size());
    }
}
