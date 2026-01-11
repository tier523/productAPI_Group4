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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class StepDefinition {

    private WebDriver driver;
    String resultUpper;
    String resultLower;
    String resultCapitalized;

    @Before //Tim
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);
    }

    @After  //Siavash
    public void closeBrowser() {
        sleep(3000);
        if (driver != null) {
            driver.quit();
        }
    }

    private void sleep(long millis) {  //Siavash
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Given("The Shop is available") //Siavash, Tim
    public void the_shop_is_available() {
        driver.get("https://webshop-agil-testautomatiserare.netlify.app/");

        new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(ExpectedConditions.titleIs("The Shop"));
    }

    @When("User visits The Shop")  //Siavash
    public void user_visits_the_shop() {
        // Optional navigation
    }

    @Then("The title should be {string}") //Siavash, Tim
    public void the_title_should_be(String expectedTitle) {
        Assertions.assertEquals(expectedTitle, driver.getTitle());
    }


    @Given("The user is on the webshop homepage for the first time")  //Nafisa, Siavash
    public void cart_empty_when_page_loads() {
        driver.get("https://webshop-agil-testautomatiserare.netlify.app/");
        sleep(3000);
    }

    @Then("The cart counter should be empty")  //Nafisa
    public void cart_counter_should_be_empty() {
        WebElement checkCartCounter = driver.findElement(By.id("buttonSize"));
        String cartCounter = checkCartCounter.getText();

        Assertions.assertNotNull(cartCounter);
        Assertions.assertEquals("", cartCounter);
    }

    @Given("User wants to add item to cart")  //Elin
    public void user_wants_to_add_item_to_cart() {
        driver.get("https://webshop-agil-testautomatiserare.netlify.app/");
        sleep(3000);
    }

    @When("Item is added to the cart") //Elin, Siavash, Tim
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

    @Then("The cart size should be 1")  //Elin
    public void the_cart_size_should_be_1() {
        sleep(5000);

        WebElement cartSizeElement = driver.findElement(By.id("cartSize"));
        String cartSizeText = cartSizeElement.getText();

        Assertions.assertNotNull(cartSizeText);
        Assertions.assertEquals("1", cartSizeText, "CartSize ska vara 1");
    }

    @When("The user clicks on the all products button")  //Beata
    public void the_user_clicks_on_the_all_products_button() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement allProductsButton = wait.until(
                ExpectedConditions.elementToBeClickable(By.cssSelector(".btn-primary"))
        );
        allProductsButton.click();
    }

    @Then("The top section of the page should display all product categories") //Beata
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

    @Given("The user adds 2 items to the cart")  //Beata
    public void the_user_adds_two_items_to_the_cart() {
        driver.get("https://webshop-agil-testautomatiserare.netlify.app/");
        sleep(3000);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement allProductsButton = driver.findElement(By.cssSelector(".btn-primary"));
        allProductsButton.click();
        sleep(3000);

        WebElement addToCartButton = wait.until(
                ExpectedConditions.elementToBeClickable(By.cssSelector(".card .btn.btn-primary"))
        );

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", addToCartButton);
        sleep(1000);

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", addToCartButton);
        sleep(1000);

        WebElement goToCheckOutButton = driver.findElement(By.cssSelector(".btn.btn-warning"));
        goToCheckOutButton.click();
    }

    @When("The user removes one item from the cart")  //Beata
    public void the_user_removes_one_item_from_the_cart() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement removeButton = wait.until(
                ExpectedConditions.elementToBeClickable(By.xpath("//li[1]//div[1]//button[1]"))
        );
        removeButton.click();
        sleep(2000);
    }

    @Then("The cart should only contain the remaining item") //Beata
    public void the_cart_should_only_contain_the_remaining_item() {
        WebElement cartSizeElement = driver.findElement(By.id("cartSize"));
        String cartSizeText = cartSizeElement.getText();
        Assertions.assertEquals("1", cartSizeText, "Cart should contain 1 item");
    }

    @Given("The user is on the webshop homepage")  //Nafisa
    public void searchBox() {
        driver.get("https://webshop-agil-testautomatiserare.netlify.app/");
        sleep(3000);
    }

    @When("User clicks on the header menu 'Shop'")  //Nafisa
    public void user_clicks_on_shop() throws InterruptedException {

        Thread.sleep(3000);
        WebElement shopMenu = driver.findElement(By.linkText("Shop")); // Clicka på shop i header: by linkText
        shopMenu.click();

    }

    @Then("User searches 'acer' in the search box")  //Nafisa
    public void user_searches_acer_in_search_box() throws InterruptedException {
        WebElement searchBox = driver.findElement(By.id("search"));

        searchBox.click();
        Thread.sleep(2000);
        searchBox.sendKeys("acer");

        WebElement searchResultTitle = driver.findElement(By.cssSelector("#main > div > div > div > h3"));
        assertEquals("Acer SB220Q bi 21.5 inches Full HD (1920 x 1080) IPS Ultra-Thin", searchResultTitle.getText());
    }

    @Given("The user wants to navigate")  //Elin
    public void user_wants_to_navigate() {
        driver.get("https://webshop-agil-testautomatiserare.netlify.app/");
        sleep(3000);
        WebElement headerMenuShopHome = driver.findElement(By.linkText("Home"));
        headerMenuShopHome.click();
        sleep(3000);
    }

    @When("The user clicks on the header menu 'About'")  //Elin
    public void user_clicks_about_link() {
        WebElement headerMenuShopAbout = driver.findElement(By.linkText("About"));
        headerMenuShopAbout.click();
        sleep(3000);
    }

    @Then("The user should be taken to the 'About' page")  //Elin
    public void user_taken_to_about_page(){
        WebElement aboutText = driver.findElement(By.xpath("//h2[text()='About The Shop']"));
        aboutText.click();
    }


    @Given("The user clicks Checkout button on homepage")  //Nafisa
    public void user_clicks_checkout_button() {
        driver.get("https://webshop-agil-testautomatiserare.netlify.app/");
        sleep(3000);
        WebElement checkout = driver.findElement(By.partialLinkText("Checkout"));
        checkout.click();
    }

    @When("The user clicks continue to checkout button")  //Nafisa
    public void user_clicks_continue_to_checkout(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement continueToCheckout = wait.until( ExpectedConditions.elementToBeClickable(By.xpath("/html/body/main/div[2]/div[2]/form/button")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", continueToCheckout);
        sleep(3000);
    }

    @Then("The user can see invalid feedback for first name on checkout form")  //Nafisa
    public void invalid_first_name_feedback_checkout_form(){
        WebElement invalidFeedbackFirstName = driver.findElement(By.xpath("/html/body/main/div[2]/div[2]/form/div[1]/div[1]/div"));
        assertNotNull(invalidFeedbackFirstName);
        assertEquals("Valid first name is required.", invalidFeedbackFirstName.getText());
        sleep(3000);
    }

    @When("User searches for {string}")  //Beata
    public void user_searches_for(String searchText) throws InterruptedException {
        WebElement searchBox = driver.findElement(By.id("search"));
        searchBox.clear();
        Thread.sleep(1000);
        searchBox.sendKeys(searchText);
        Thread.sleep(2000);

        String resultText = driver
                .findElement(By.cssSelector("#main > div > div > div > h3"))
                .getText();
        if (searchText.equals("ACER")) {
            resultUpper = resultText;
        } else if (searchText.equals("acer")) {
            resultLower = resultText;
        } else if (searchText.equals("Acer")) {
            resultCapitalized = resultText;
        }
    }

    @Then("The search result should be the same for all cases") //Beata
    public void the_search_result_should_be_the_same_for_all_cases() {
        assertEquals(resultUpper, resultLower);
        assertEquals(resultUpper, resultCapitalized);
    }

}



