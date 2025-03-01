package framework.definitions;

import framework.core.Driver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertEquals;

public class AirMilesShopsTest {
    private WebDriver driver;

    @Before
    public void setUp() {
        driver = Driver.getDriver();
    }

    @Given("I open the browser")
    public void i_open_the_browser() {
        driver = new ChromeDriver();
    }

    @When("I visit {string}")
    public void i_visit(String url) {
        driver.get(url);
    }

    @Then("I should see the page title as {string}")
    public void i_should_see_the_page_title_as(String expectedTitle) {
        String actualTitle = driver.getTitle();
        assertEquals(expectedTitle, actualTitle);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}