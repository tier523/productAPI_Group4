package com.example.produktapi.service;

import com.example.produktapi.exception.EntityNotFoundException;
import com.example.produktapi.model.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductServiceTest {

    @Autowired
    private ProductService productService;

    // Kodavsnitt skriven av Nafisa Shams
    @Test
    void getAllProducts() {
        List<Product> products = productService.getAllProducts();
        assertNotNull(products);
        assertFalse(products.isEmpty());

        System.out.println("products ::::: " + String.join("\n", products.stream().map(Product::toString).toList()));
    }

    // Kodavsnitt skriven av Nafisa Shams
    @Test
    public void getAllCategory(){

        List<String> categories = productService.getAllCategories();

        assertNotNull(categories);
        assertFalse(categories.isEmpty());
    }


    @Test  //Beata
    public void getProductById_existing() {
        Product product = productService.getAllProducts().get(0);
        Product result = productService.getProductById(product.getId());
        assertEquals(product.getId(), result.getId());
        assertEquals(product.getTitle(), result.getTitle());
    }

    @Test  //Beata
    public void getProductsByCategory_nonExistingCategory() {
        List<Product> products = productService.getProductsByCategory("NoSuchCategory");
        assertNotNull(products);
        assertTrue(products.isEmpty());
    }
}