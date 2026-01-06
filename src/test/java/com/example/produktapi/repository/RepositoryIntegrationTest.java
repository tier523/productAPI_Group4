package com.example.produktapi.repository;

import com.example.produktapi.model.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase
public class RepositoryIntegrationTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    void testDataSqlIsLoaded() {
        List<Product> products = productRepository.findAll();

        // 20 rows are inserted in data.sql
        assertEquals(20, products.size());
    }

    @Test
    void testFindByCategory() {
        List<Product> mensClothing = productRepository.findByCategory("men's clothing");

        assertFalse(mensClothing.isEmpty());
        assertTrue(mensClothing.size() >= 4); // based on your data.sql
    }
}
