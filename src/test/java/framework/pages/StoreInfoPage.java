package framework.pages;

import org.openqa.selenium.By;

public class StoreInfoPage {
    public static By getShopNowButtonSelector() {
        return By.className("buttonWrapper");
    }

    public static By getFavouriteButtonSelector() {
        return By.className("button-text-wrap");
    }
}
