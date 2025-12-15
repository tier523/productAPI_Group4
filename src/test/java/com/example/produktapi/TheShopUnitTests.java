package com.example.produktapi;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TheShopUnitTests {
    WebDriver driver;

    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "G:\\_AutoTestKurs\\Laboration2\\Selenium\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
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
        assertTrue(websiteTitle.contains("IT-Högskolan – Här startar din IT-karriär!"), "Sidtiteln ska innehålla IT-Högskolan");
    }
}
