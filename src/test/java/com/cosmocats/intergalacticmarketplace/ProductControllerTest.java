package com.cosmocats.intergalacticmarketplace;

import com.cosmocats.intergalacticmarketplace.dto.ProductDTO;
import com.cosmocats.intergalacticmarketplace.entity.Product;
import com.cosmocats.intergalacticmarketplace.controller.ProductController;
import com.cosmocats.intergalacticmarketplace.service.mapper.ProductService;
import com.cosmocats.intergalacticmarketplace.service.mapper.ProductMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductControllerTest {

    @InjectMocks
    private ProductController productController;

    @Mock
    private ProductService productService;

    @Mock
    private ProductMapper productMapper;


    @Autowired
    private RestTemplate restTemplate;  // тут ін'єкція RestTemplate

    @Test
    void testRestTemplateNotNull() {
        assertNotNull(restTemplate);  // перевіряємо, чи не null
    }

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Ініціалізація моків
    }

    @Test
    void testGetAllProducts() {
        List<Product> products = Arrays.asList(
                new Product(1L, "Cosmic Widget", "A shiny widget", 99.99),
                new Product(2L, "Starry T-shirt", "A t-shirt with stars", 19.99)
        );

        when(productMapper.productToProductDTO(eq(products.get(0)))).thenReturn(new ProductDTO(1L, "Cosmic Widget", "A shiny widget", 99.99));
        when(productMapper.productToProductDTO(eq(products.get(1)))).thenReturn(new ProductDTO(2L, "Starry T-shirt", "A t-shirt with stars", 19.99));

        when(productService.getAllProducts()).thenReturn(products);

        ResponseEntity<List<ProductDTO>> response = productController.getAllProducts();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().size());
        verify(productService, times(1)).getAllProducts();
    }

    @Test
    void testGetProductByIdExists() {
        Product product = new Product(1L, "Cosmic Widget", "A shiny widget", 99.99);
        ProductDTO productDTO = new ProductDTO(1L, "Cosmic Widget", "A shiny widget", 99.99);

        when(productService.getProductById(1L)).thenReturn(Optional.of(product));
        when(productMapper.productToProductDTO(any(Product.class))).thenReturn(productDTO);


        ResponseEntity<ProductDTO> response = productController.getProduct(1L); // Замінено на getProduct

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Cosmic Widget", response.getBody().getName());
        verify(productService, times(1)).getProductById(1L);
    }

    @Test
    void testGetProductByIdNotExists() {
        when(productService.getProductById(99L)).thenReturn(Optional.empty());

        ResponseEntity<ProductDTO> response = productController.getProduct(99L); // Замінено на getProduct

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
        verify(productService, times(1)).getProductById(99L);
    }

    @Test
    public void testCreateProduct() {
        Product product = new Product(null, "New Product", "Description", 100.0);
        ResponseEntity<Product> response = restTemplate.postForEntity("/products", product, Product.class);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody().getId());
    }




    @Test
    void testUpdateProductExists() {
        Product updatedProduct = new Product(1L, "Updated Widget", "Updated description", 79.99);
        ProductDTO updatedProductDTO = new ProductDTO(1L, "Updated idget", "Updated description", 79.99);

        when(productService.getProductById(1L)).thenReturn(Optional.of(updatedProduct));
        when(productService.updateProduct(eq(1L), any(Product.class))).thenReturn(Optional.of(updatedProduct));
        when(productMapper.productToProductDTO(any(Product.class))).thenReturn(updatedProductDTO);

        ResponseEntity<ProductDTO> response = productController.updateProduct(1L, updatedProductDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Updated Widget", response.getBody().getName());
        verify(productService, times(1)).updateProduct(eq(1L), any(Product.class));
    }




    @Test
    void testUpdateProductNotExists() {
        ProductDTO nonExistentProductDTO = new ProductDTO(99L, "Nonexistent", "Description", 50.0);

        when(productService.updateProduct(eq(99L), any(Product.class))).thenReturn(Optional.empty());

        ResponseEntity<ProductDTO> response = productController.updateProduct(99L, nonExistentProductDTO);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
        verify(productService, times(1)).updateProduct(eq(99L), any(Product.class));
    }




    @Test
    void testDeleteProductExists() {
        when(productService.deleteProduct(1L)).thenReturn(true);

        ResponseEntity<Void> response = productController.deleteProduct(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(productService, times(1)).deleteProduct(1L);
    }

    @Test
    void testDeleteProductNotExists() {
        when(productService.deleteProduct(99L)).thenReturn(false);

        ResponseEntity<Void> response = productController.deleteProduct(99L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(productService, times(1)).deleteProduct(99L);
    }
}
