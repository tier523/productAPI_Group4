import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class StepDefinition {

    WebDriver driver;

    @Given("The Shop is available")
    public void the_shop_is_available() {
        System.setProperty("webdriver.chrome.driver", "G:\\_AutoTestKurs\\Laboration2\\Selenium\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
    }
    @When("User visits The Shop")
    public void user_visits_the_shop() {

        driver.get("https://webshop-agil-testautomatiserare.netlify.app/");

    }
    @Then("The title should be {string}")
    public void the_title_should_be(String expectedTitle) {
        String actualTitle = driver.getTitle();
        Assertions.assertEquals(expectedTitle, actualTitle);
    }
}
