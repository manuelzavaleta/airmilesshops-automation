package framework.pages;

import org.openqa.selenium.By;

public class ApparelPage {
    public static By getHeaderSelector() {
        return By.tagName("h1");
    }

    public static By getStoreCardsSelector() {
        return By.className("shop-card__wrapper");
    }
}
