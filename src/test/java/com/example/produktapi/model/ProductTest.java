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

    @Test
    public void getDescription() {
        assertEquals("Test Desc.", product.getDescription());
    }

    @Test
    public void getImage() {
        assertEquals("Acer.jpeg", product.getImage());
    }

    @Test
    public void setTitleAndPrice() {
        product.setTitle("Dell");
        product.setPrice(799.00);
        assertEquals("Dell", product.getTitle());
        assertEquals(799.00, product.getPrice());
    }

    @Test
    public void setAndGetId() {
        product.setId(100);
        assertEquals(100, product.getId());
    }

    @Test //Tim
    void testDifferentProductsHoldDifferentValues() {
        Product p1 = new Product("A", 10.0, "cat1", "desc1", "img1");
        Product p2 = new Product("B", 20.0, "cat2", "desc2", "img2");

        assertNotEquals(p1.getTitle(), p2.getTitle());
        assertNotEquals(p1.getPrice(), p2.getPrice());
        assertNotEquals(p1.getCategory(), p2.getCategory());
        assertNotEquals(p1.getDescription(), p2.getDescription());
        assertNotEquals(p1.getImage(), p2.getImage());
    }
}