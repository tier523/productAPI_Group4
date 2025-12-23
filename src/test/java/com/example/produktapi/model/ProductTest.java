package com.example.produktapi.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    private Product product;

    @BeforeEach
    public void setUp() {
        product = new Product(
                "Acer",
                599.00,
                "Electronics",
                "Test Desc.",
                "Acer.jpeg"
        );
    }

    @Test
    public void getTitle() {
        String title = product.getTitle();
        assertEquals("Acer", title);
    }

    @Test
    public void getPrice() {
        Double price = product.getPrice();
        assertEquals(599.00, price);
    }

    @Test
    public void getCategory() {
        assertEquals("Electronics", product.getCategory());
        System.out.println(product.getCategory());
        System.out.println(product.getTitle());
    }
}