package framework.definitions;

import framework.pages.ApparelPage;
import framework.pages.HomePage;
import framework.pages.SearchResultPage;
import framework.pages.StoreInfoPage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

import static framework.core.Driver.getDriver;
import static framework.core.Driver.quit;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AirMilesShopsTest {

    private void hoversOver(By selector) {
        WebElement element = getDriver().findElement(selector);
        hoversOver(element);
    }

    private void hoversOver(WebElement element) {
        new Actions(getDriver()).moveToElement(element).perform();
    }

    @After
    public void tearDown() {
        quit();
    }

    @Given("the user is on the home page")
    public void theUserIsOnTheHomePage() {
        getDriver().get(HomePage.getBaseUrl());
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
        WebElement apparelNode = getDriver().findElement(HomePage.getApparelSelector());
        List<String> actualSubcategories = HomePage.getAllLinkChildElementsFromSubCategoryElement(apparelNode).stream().map(WebElement::getText).toList();

        List<String> expectedSubcategories = dataTable.asList(String.class);

        assertEquals("Apparel subcategories are not equal", expectedSubcategories, actualSubcategories);
    }


    @When("the user clicks on the All Apparel link")
    public void theUserClicksOnTheAllApparelLink() {
        getDriver().findElement(HomePage.getAllApparelSelector()).click();
    }

    @Then("the user should see the correct {string} page header")
    public void theUserShouldSeeTheCorrectPageHeader(String pageName) {
        WebElement actualHeader = getDriver().findElement(By.tagName("h1"));
        assertEquals(String.format("[%s] page Header is not the expected", pageName), pageName, actualHeader.getText());
    }

    @And("the user should see store cards displayed on the page:")
    public void theUserShouldSeeStoreCardsDisplayedOnThePage(DataTable dataTable) {
        List<String> expectedStoreCards = dataTable.asList(String.class);
        List<String> actualStoreCards = getDriver().findElements(ApparelPage.getStoreCardsSelector()).stream()
                .map(ApparelPage.mapStoreCardElementsByItsAlt()).toList();

        for (String expectedStoreCard : expectedStoreCards) {
            assertTrue(String.format("Expected card [%s] not in the actual store cards list [%s]", expectedStoreCard, actualStoreCards), actualStoreCards.contains(expectedStoreCard));
        }
    }

    @When("the user searches for {string}")
    public void theUserSearchesFor(String searchString) {
        List<WebElement> searchBars = getDriver().findElements(HomePage.getAllInputSelector()).stream()
                .filter(HomePage.filterByAriaLabel("Search")).toList();

        assertEquals("There should be only one search bar", 1, searchBars.size());

        WebElement searchBarWebElement = searchBars.get(0);
        searchBarWebElement.sendKeys(searchString);
    }

    @And("the user clicks on the search button")
    public void theUserClicksOnTheSearchButton() {
        List<WebElement> searchButton = getDriver().findElements(HomePage.getAllButtonSelector()).stream()
                .filter(HomePage.filterByAriaLabel("Search")).toList();

        assertEquals("There should be only one search button", 1, searchButton.size());

        WebElement searchBarWebElement = searchButton.get(0);
        searchBarWebElement.click();
    }

    @Then("the user should see results for {string}")
    public void theUserShouldSeeResultsFor(String searchString) {
        WebElement headerElement = getDriver().findElement(By.tagName("h1"));
        String headerText = headerElement.getText();

        assertTrue("header must contain 'results for' keywords", headerText.contains("results for"));
        assertTrue(String.format("header must contain the search string value of [%s]", searchString), headerText.contains(searchString));
    }

    @And("the {string} store card should be displayed")
    public void theStoreCardShouldBeDisplayed(String searchString) {
        List<String> storeCards = getDriver().findElements(SearchResultPage.getStoreCardsSelector()).stream()
                .map(SearchResultPage.mapStoreCardElementsByItsAlt()).toList();

        assertTrue(String.format("[%s] should be contained among the store cards in [%s]", searchString, storeCards), storeCards.contains(searchString));
    }

    @When("the user hovers on the {string} store card")
    public void theUserHoversOnTheStoreCard(String storeName) {
        List<WebElement> storeCards = getDriver().findElements(SearchResultPage.getStoreCardsSelector()).stream()
                .filter(SearchResultPage.filterWebElementsByItsAlt(storeName))
                .toList();

        assertEquals(String.format("There should be exactly one store card for [%s] store.", storeName), 1, storeCards.size());
        hoversOver(storeCards.get(0));
    }

    @And("the user selects Store Info from {string} store card")
    public void theUserSelectsStoreInfoFromStoreCard(String storeName) {
        List<WebElement> storeCards = getDriver().findElements(SearchResultPage.getStoreCardsSelector()).stream()
                .filter(SearchResultPage.filterWebElementsByItsAlt(storeName))
                .toList();

        assertEquals(String.format("There should be exactly one store card for [%s] store.", storeName), 1, storeCards.size());

        List<WebElement> storeInfoButtons = storeCards.get(0).findElements(By.tagName("a")).stream()
                .filter(webElement -> "STORE INFO".equals(webElement.getText())).toList();

        assertEquals(String.format("There should be exactly one 'Store Info' button for [%s] store card.", storeName), 1, storeInfoButtons.size());

        storeInfoButtons.get(0).click();
    }

    @And("store info page should display Shop Now button")
    public void storeInfoPageShouldDisplayShopNowButton() {
        String actualShopNowButtonText = getDriver().findElement(StoreInfoPage.getShopNowButtonSelector()).getText();
        assertEquals("Shop Now button text is not the expected.", "SHOP NOW", actualShopNowButtonText);
    }

    @And("store info page should display Favourite button")
    public void storeInfoPageShouldDisplayFavouriteButton() {
        String actualFavouriteButtonText = getDriver().findElement(StoreInfoPage.getFavouriteButtonSelector()).getText();
        assertEquals("Favourite button text is not the expected.", "FAVOURITE", actualFavouriteButtonText);
    }
}