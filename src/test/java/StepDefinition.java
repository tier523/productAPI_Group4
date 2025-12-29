import io.cucumber.java.Before;
import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class StepDefinition {

    private WebDriver driver;

    @Before
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);
    }

    @Given("The Shop is available")
    public void the_shop_is_available() {
        driver.get("https://webshop-agil-testautomatiserare.netlify.app/");

        new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(ExpectedConditions.titleIs("The Shop"));
    }

    @When("User visits The Shop")
    public void user_visits_the_shop() {
        // Optional navigation
    }

    @Then("The title should be {string}")
    public void the_title_should_be(String expectedTitle) {
        Assertions.assertEquals(expectedTitle, driver.getTitle());
    }

    @Given("The user is on the webshop homepage for the first time")
    public void cart_empty_when_page_loads() {
        driver.get("https://webshop-agil-testautomatiserare.netlify.app/");
        sleep(3000);
    }

    @Then("The cart counter should be empty")
    public void cart_counter_should_be_empty() {
        WebElement checkCartCounter = driver.findElement(By.id("buttonSize"));
        String cartCounter = checkCartCounter.getText();

        Assertions.assertNotNull(cartCounter);
        Assertions.assertEquals("", cartCounter);
    }

    @Given("User wants to add item to cart")
    public void user_wants_to_add_item_to_cart() {
        driver.get("https://webshop-agil-testautomatiserare.netlify.app/");
        sleep(3000);
    }

    @When("Item is added to the cart")
    public void item_is_added_to_the_cart() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement allProductsButton = driver.findElement(By.cssSelector(".btn-primary"));
        allProductsButton.click();

        sleep(3000);

        WebElement addToCartButton = wait.until(
                ExpectedConditions.elementToBeClickable(By.cssSelector(".card .btn.btn-primary"))
        );
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", addToCartButton);

        WebElement goToCheckOutButton = driver.findElement(By.cssSelector(".btn.btn-warning"));
        goToCheckOutButton.click();
    }

    @Then("The cart size should be 1")
    public void the_cart_size_should_be_1() {
        sleep(5000);

        WebElement cartSizeElement = driver.findElement(By.id("cartSize"));
        String cartSizeText = cartSizeElement.getText();

        Assertions.assertNotNull(cartSizeText);
        Assertions.assertEquals("1", cartSizeText, "CartSize ska vara 1");
    }

    @When("The user clicks on the all products button")
    public void the_user_clicks_on_the_all_products_button() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement allProductsButton = wait.until(
                ExpectedConditions.elementToBeClickable(By.cssSelector(".btn-primary"))
        );
        allProductsButton.click();
    }

    @Then("The top section of the page should display all product categories")
    public void the_top_section_should_display_all_product_categories() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        String[] expectedCategories = {
                "All",
                "Men's clothing",
                "Women's clothing",
                "Jewelery",
                "Electronics"
        };
        for (String category : expectedCategories) {
            WebElement categoryElement = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//*[text()=\"" + category + "\"]")
                    )
            );
            Assertions.assertTrue(
                    categoryElement.isDisplayed(),
                    "Category should be visible: " + category
            );
        }
    }

    @After
    public void closeBrowser() {
        sleep(3000);
        if (driver != null) {
            driver.quit();
        }
    }

    private void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
