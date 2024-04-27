package com.example.Supermart;



import com.example.Supermart.controller.SupermartController;

//package com.example.Supermart.controller;

import com.example.Supermart.model.Supermart;
import com.example.Supermart.repo.SupermartRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;
class SupermartControllerTest {

    @Mock
    private SupermartRepo supermartRepo;

    @InjectMocks
    private SupermartController supermartController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllProducts_ReturnsListOfProducts() {
        List<Supermart> productList = new ArrayList<>();
        productList.add(new Supermart(1L, "Product 1", 10.0));
        productList.add(new Supermart(2L, "Product 2", 20.0));

        when(supermartRepo.findAll()).thenReturn(productList);

        ResponseEntity<List<Supermart>> response = supermartController.getAllProducts();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(productList, response.getBody());
    }
    
    @Test
    void getAllProducts_ReturnsEmptyOfProducts() {
        List<Supermart> productList = new ArrayList<>();

        when(supermartRepo.findAll()).thenReturn(productList);

        ResponseEntity<List<Supermart>> response = supermartController.getAllProducts();

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void getProductById_WithValidId_ReturnsProduct() {
        long id = 1L;
        Supermart product = new Supermart(id, "Product 1", 10.0);

        when(supermartRepo.findById(id)).thenReturn(Optional.of(product));

        ResponseEntity<Supermart> response = supermartController.getProductById(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(product, response.getBody());
    }

    @Test
    void getProductById_WithInvalidId_ReturnsNotFoundStatus() {
        long id = 1L;

        when(supermartRepo.findById(id)).thenReturn(Optional.empty());

        ResponseEntity<Supermart> response = supermartController.getProductById(id);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void addProduct_ReturnsAddedProduct() {
        Supermart productToAdd = new Supermart(1L, "Product 1", 10.0);

        when(supermartRepo.save(productToAdd)).thenReturn(productToAdd);

        ResponseEntity<Supermart> response = supermartController.addProduct(productToAdd);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(productToAdd, response.getBody());
    }

    @Test
    void updateProductById_WithValidId_ReturnsUpdatedProduct() {
        long id = 1L;
        Supermart oldProduct = new Supermart(id, "Old Product", 20.0);
        Supermart newProductData = new Supermart(id, "New Product", 30.0);

        when(supermartRepo.findById(id)).thenReturn(Optional.of(oldProduct));
        when(supermartRepo.save(oldProduct)).thenReturn(oldProduct);

        ResponseEntity<Supermart> response = supermartController.updateProductById(id, newProductData);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(newProductData.getProductName(), response.getBody().getProductName());
        assertEquals(newProductData.getPrice(), response.getBody().getPrice());
    }

    @Test
    void updateProductById_WithInvalidId_ReturnsNotFoundStatus() {
        long id = 1L;
        Supermart newProductData = new Supermart(id, "New Product", 30.0);

        when(supermartRepo.findById(id)).thenReturn(Optional.empty());

        ResponseEntity<Supermart> response = supermartController.updateProductById(id, newProductData);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void deleteProductById_WithValidId_ReturnsOkStatus() {
        long id = 1L;

        ResponseEntity<HttpStatus> response = supermartController.deleteProductById(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(supermartRepo, times(1)).deleteById(id);
    }
    @Test
    void createSupermartInstance_NoArgsConstructor() {
        Supermart product = new Supermart();

        assertNotNull(product);
        
    }
    @Test
    void verifySetterGetterMethods_ForSupermartProperties() {
        Supermart product = new Supermart();
        // Test setter methods
        product.setId(1L);
        product.setProductName("Test Product");
        product.setPrice(10.0);

        // Test getter methods
        assertEquals(1L, product.getId());
        assertEquals("Test Product", product.getProductName());
        assertEquals(10.0, product.getPrice());
    }
}




