package com.example.produktapi;
import com.example.produktapi.model.Product;
import com.example.produktapi.repository.ProductRepository;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TheShopSystemTests {
    WebDriver driver;

    @BeforeEach //Tim
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.get("https://webshop-agil-testautomatiserare.netlify.app/");
    }

    @AfterEach //Tim
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test //SiaSam, Tim
    @DisplayName("Hemsidans Titel visas korrekt")
    public void checkWebSiteTitle() {
        try {
            Thread.sleep(3000); // 3 sekunder
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        String websiteTitle = driver.getTitle();
        Assertions.assertNotNull(websiteTitle);
        assertTrue(websiteTitle.contains("The Shop"), "Sidtiteln ska innehålla The Shop");
    }


    // Kodavsnitt skriven av Nafisa Shams
    @Test
    @DisplayName("Kolla om varukorgen är tom när sidan läddas första gången")
    public void checkCartCounter() {
        try {
            Thread.sleep(3000); // vänta för 3 sekunder
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        WebElement cartCounterElement = driver.findElement(By.id("buttonSize"));
        String cartCounter = cartCounterElement.getText();

        Assertions.assertNotNull(cartCounter);
        Assertions.assertEquals("", cartCounter);

    }



    @Test //Elin, Tim
    @DisplayName("Lägg till varor i varukorgen")
    public void AddToCart() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        var allProductsButton = driver.findElement(
                org.openqa.selenium.By.cssSelector(".btn-primary")
        );

        allProductsButton.click();

        try {
            Thread.sleep(3000); // 3 sekunder
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        WebElement addToCartButton = wait.until( ExpectedConditions.elementToBeClickable(By.cssSelector(".card .btn.btn-primary")) );

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", addToCartButton);


        var goToCheckOutButton = driver.findElement(
                org.openqa.selenium.By.cssSelector(".btn.btn-warning")
        );
        goToCheckOutButton.click();

        WebElement cartSizeElement = driver.findElement(By.id("cartSize"));

        String cartSizeText = cartSizeElement.getText();

        Assertions.assertNotNull(cartSizeText);
        assertTrue(cartSizeText.equals("1"), "CartSize ska vara 1");
    }


    // Kodavsnitt skriven av Nafisa Shams
    @Test
    @DisplayName("Testa sökfältet in shopen")

    public void checkSearchBox() throws InterruptedException {

        Thread.sleep(3000); // vänta för 3 sekunder

        WebElement headerMenuShop = driver.findElement(By.linkText("Shop")); // Clicka på shop i header: by linkText
        headerMenuShop.click();

        WebElement searchBox = driver.findElement(By.id("search"));
        searchBox.click();
        Thread.sleep(2000);
        searchBox.sendKeys("acer");

        // kontrollera om sökresultatet är korrekt
        WebElement searchResultTitle = driver.findElement(By.cssSelector("#main > div > div > div > h3"));
        assertEquals("Acer SB220Q bi 21.5 inches Full HD (1920 x 1080) IPS Ultra-Thin", searchResultTitle.getText());

    }

    //Elin
    @Test
    @DisplayName("Test navigation")

    public void testNavigation() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(4));
        Thread.sleep(3000);

        WebElement headerMenuShopHome = driver.findElement(By.linkText("Home"));
        headerMenuShopHome.click();
        Thread.sleep(3000);

        WebElement headerMenuShopAbout = driver.findElement(By.linkText("About"));
        headerMenuShopAbout.click();
        Thread.sleep(3000);

        //Verify that we can see the 'About The Shop' text
        WebElement aboutText = driver.findElement(By.xpath("//h2[text()='About The Shop']"));
        aboutText.click();

    }


    // Kodavsnitt skriven av Nafisa Shams
    @Test
    @DisplayName("Testa invalid feedback för First name i checkout")
    public void invalidFeedbackFirstNameInCheckout() throws InterruptedException {

        Thread.sleep(3000); // vänta för 3 sekunder

        WebElement invalidFeedback = driver.findElement(By.partialLinkText("Checkout"));
        invalidFeedback.click();
        Thread.sleep(3000);

        WebElement continueToCheckout = driver.findElement(By.xpath("/html/body/main/div[2]/div[2]/form/button"));
        continueToCheckout.click();
        Thread.sleep(3000);

        // Kontrollera om feedbacken för ogiltiga förnamn är korrekt i checkoutformuläret.
        WebElement invalidFeedbackFirstName = driver.findElement(By.xpath("/html/body/main/div[2]/div[2]/form/div[1]/div[1]/div"));
        assertNotNull(invalidFeedbackFirstName);
        assertEquals("Valid first name is required.", invalidFeedbackFirstName.getText());
    }

    @Nested //Tim
    @SpringBootTest
    @AutoConfigureTestDatabase
    @DisplayName("Integrationstest för databasen")
    class ProductRepositoryIntegrationTest {

        @Autowired
        private ProductRepository productRepository;

        @Test
        void testDataSqlIsLoaded() {
            List<Product> products = productRepository.findAll();

            // You inserted 20 rows in data.sql
            assertEquals(20, products.size());
        }

        @Test
        void testFindByCategory() {
            List<Product> mensClothing = productRepository.findByCategory("men's clothing");

            assertFalse(mensClothing.isEmpty());
            assertTrue(mensClothing.size() >= 4); // based on your data.sql
        }
    }


}


