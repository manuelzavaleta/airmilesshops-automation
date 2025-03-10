package framework.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.function.Function;

public class ApparelPage {

    public static By getStoreCardsSelector() {
        return By.className("shop-card__wrapper");
    }

    public static Function<WebElement, String> mapStoreCardElementsByItsAlt() {
        return webElement -> webElement.findElement(By.tagName("img")).getAttribute("alt");
    }

}
