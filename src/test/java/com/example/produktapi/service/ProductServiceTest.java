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

    @Test
    void getAllProducts() {
        List<Product> products = productService.getAllProducts();
        assertNotNull(products);
        assertFalse(products.isEmpty());

        System.out.println("products ::::: " + String.join("\n", products.stream().map(Product::toString).toList()));
    }

    @Test
    public void getAllCategory(){

        List<String> categories = productService.getAllCategories();

        assertNotNull(categories);
        assertFalse(categories.isEmpty());
    }
}