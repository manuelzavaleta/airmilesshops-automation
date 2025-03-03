package framework.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.function.Function;
import java.util.function.Predicate;

public class SearchResultPage {

    public static By getStoreCardsSelector() {
        return By.className("shop-card__wrapper");
    }

    public static Function<WebElement, String> mapStoreCardElementsByItsAlt() {
        return webElement -> webElement.findElement(By.tagName("img")).getAttribute("alt");
    }

    public static Predicate<WebElement> filterWebElementsByItsAlt(String altValue) {
        return we -> altValue.equals(we.findElement(By.tagName("img")).getAttribute("alt"));
    }
}
