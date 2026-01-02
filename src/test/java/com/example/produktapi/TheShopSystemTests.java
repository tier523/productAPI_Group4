package com.example.produktapi;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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



}