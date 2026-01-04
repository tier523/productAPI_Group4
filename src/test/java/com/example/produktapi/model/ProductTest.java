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

    // Kodavsnitt skriven av Nafisa Shams
    @Test
    public void getTitle() {
        String title = product.getTitle();
        assertEquals("Acer", title);
    }

    // Kodavsnitt skriven av Nafisa Shams
    @Test
    public void getPrice() {
        Double price = product.getPrice();
        assertEquals(599.00, price);
    }

    // Kodavsnitt skriven av Nafisa Shams
    @Test
    public void getCategory() {
        assertEquals("Electronics", product.getCategory());
        System.out.println(product.getCategory());
        System.out.println(product.getTitle());
    }

    @Test   //Beata
    public void getDescription() {
        assertEquals("Test Desc.", product.getDescription());
    }

    @Test  //Beata
    public void getImage() {
        assertEquals("Acer.jpeg", product.getImage());
    }

    @Test   //Beata
    public void setTitleAndPrice() {
        product.setTitle("Dell");
        product.setPrice(799.00);
        assertEquals("Dell", product.getTitle());
        assertEquals(799.00, product.getPrice());
    }

    @Test   //Beata
    public void setAndGetId() {
        product.setId(100);
        assertEquals(100, product.getId());
    }

    @Test //Tim
    void testNoArgsConstructor() {
        Product product = new Product();
        assertNotNull(product);
        assertNull(product.getId());
    }
  
    @Test //Tim 
    void testNullValuesAllowed() {
        Product product = new Product();
        product.setTitle(null);
        product.setPrice(null);
        product.setCategory(null);
        product.setDescription(null);
        product.setImage(null);

        assertNull(product.getTitle());
        assertNull(product.getPrice());
        assertNull(product.getCategory());
        assertNull(product.getDescription());
        assertNull(product.getImage());
    }
  
    @Test //Beata
    void testEmptyProduct() {
        Product emptyProduct = new Product();
        assertNull(emptyProduct.getTitle());
        assertNull(emptyProduct.getPrice());
        assertNull(emptyProduct.getCategory());
        assertNull(emptyProduct.getDescription());
        assertNull(emptyProduct.getImage());
        assertNull(emptyProduct.getId());
    }

    @Test  //Beata
    void testProductsAreIndependent() {
        Product product1 = new Product("A", 10.0, "Category1", "Desc1", "img1.png");
        Product product2 = new Product("B", 20.0, "Category2", "Desc2", "img2.png");

        assertNotEquals(product1.getCategory(), product2.getCategory());
    }

    @Test //Tim
    void testSettersAndGetters() {
        Product product = new Product();

        product.setId(10);
        product.setTitle("New Title");
        product.setPrice(55.5);
        product.setCategory("men's clothing");
        product.setDescription("Some description");
        product.setImage("https://example.com/img.png");

        assertEquals(10, product.getId());
        assertEquals("New Title", product.getTitle());
        assertEquals(55.5, product.getPrice());
        assertEquals("men's clothing", product.getCategory());
        assertEquals("Some description", product.getDescription());
        assertEquals("https://example.com/img.png", product.getImage());
    }

    @Test //Tim
    void testIdCanBeChanged() {
        Product product = new Product();
        assertNull(product.getId());

        product.setId(1);
        assertEquals(1, product.getId());

        product.setId(2);
        assertEquals(2, product.getId());
    }
}