import io.cucumber.java.Before;
import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
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

        // Vänta max 20 sekunder tills titeln är "The Shop"
        new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(ExpectedConditions.titleIs("The Shop"));
    }

    @When("User visits The Shop")
    public void user_visits_the_shop() {
        // Eventuell extra navigering kan läggas här
    }

    @Then("The title should be {string}")
    public void the_title_should_be(String expectedTitle) {
        Assertions.assertEquals(expectedTitle, driver.getTitle());
    }

    @After
    public void closeBrowser() {
        try {
            Thread.sleep(3000); // 3 sekunder
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        if (driver != null) {
            driver.quit();
        }
    }


    @Given("The cart is empty when page loads for the first time")
    public void cart_empty_when_page_loads(){
        driver.get("https://webshop-agil-testautomatiserare.netlify.app/");

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
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

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @When("Item is added to the cart")
    public void item_is_added_to_the_cart() {

        WebElement allProductsButton = driver.findElement(By.cssSelector(".btn-primary"));
        allProductsButton.click();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }


        WebElement addToCartButton = driver.findElement(By.cssSelector(".btn.btn-primary"));
        addToCartButton.click();


        WebElement goToCheckOutButton = driver.findElement(By.cssSelector(".btn.btn-warning"));
        goToCheckOutButton.click();
    }

    @Then("The cart size should be 1")
    public void the_cart_size_should_be_1() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        WebElement cartSizeElement = driver.findElement(By.id("cartSize"));
        String cartSizeText = cartSizeElement.getText();

        Assertions.assertNotNull(cartSizeText);
        Assertions.assertTrue(cartSizeText.equals("1"), "CartSize ska vara 1");
    }




}
