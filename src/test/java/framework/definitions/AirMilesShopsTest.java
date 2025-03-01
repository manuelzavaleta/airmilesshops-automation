package framework.definitions;

import framework.core.Driver;
import framework.pages.ApparelPage;
import framework.pages.HomePage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AirMilesShopsTest {
    private WebDriver driver;

    private void hoversOver(By selector) {
        new Actions(driver).moveToElement(driver.findElement(selector)).perform();
    }

    @Before
    public void setUp() {
        driver = Driver.getDriver();
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Given("the user is on the home page")
    public void theUserIsOnTheHomePage() {
        driver.get(HomePage.getBaseUrl());
    }

    @When("the user hovers over Browse Categories")
    public void theUserHoversOverBrowseCategories() {
        hoversOver(HomePage.getBrowseCategoriesSelector());
    }

    @And("the user hovers over Apparel")
    public void theUserHoversOverApparel() {
        hoversOver(HomePage.getApparelSelector());
    }

    @Then("the user should see all subcategories:")
    public void theUserShouldSeeAllSubcategories(DataTable dataTable) {
        WebElement apparelNode = driver.findElement(HomePage.getApparelSelector());
        List<String> actualSubcategories = apparelNode.findElements(By.xpath("ancestor::li/div/div/a")).stream().map(WebElement::getText).toList();

        List<String> expectedSubcategories = dataTable.asList(String.class);

        assertEquals("Apparel subcategories are not equal", expectedSubcategories, actualSubcategories);
    }

    @When("the user clicks on the All Apparel link")
    public void theUserClicksOnTheAllApparelLink() {
        driver.findElement(HomePage.getAllApparelSelector()).click();
    }

    @Then("the user should see the correct page header")
    public void theUserShouldSeeTheCorrectPageHeader() {
        WebElement actualHeader = driver.findElement(ApparelPage.getHeaderSelector());
        assertEquals("Apparel page Header is not the expected", "APPAREL", actualHeader.getText());
    }

    @And("the user should see store cards displayed on the page:")
    public void theUserShouldSeeStoreCardsDisplayedOnThePage(DataTable dataTable) {
        List<String> expectedStoreCards = dataTable.asList(String.class);
        List<String> actualStoreCards = driver.findElements(ApparelPage.getStoreCardsSelector()).stream().map(we -> we.findElement(By.tagName("img")).getAttribute("alt")).toList();

        for (String expectedStoreCard : expectedStoreCards) {
            assertTrue(String.format("Expected card [%s] not in the actual store cards list [%s]", expectedStoreCard, actualStoreCards), actualStoreCards.contains(expectedStoreCard));
        }
    }
}