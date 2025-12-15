package com.example.produktapi;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TheShopUnitTests {
    WebDriver driver;

    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "G:\\_AutoTestKurs\\Laboration2\\Selenium\\chromedriver-win64\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.get("https://webshop-agil-testautomatiserare.netlify.app/");
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    @DisplayName("Hemsidans Titel visas korrekt")
    public void checkWebSiteTitle() {
        String websiteTitle = driver.getTitle();
        Assertions.assertNotNull(websiteTitle);
        assertTrue(websiteTitle.contains("The Shop"), "Sidtiteln ska innehålla The Shop");
    }
}
