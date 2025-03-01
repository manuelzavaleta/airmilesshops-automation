package framework.pages;

import framework.core.Utils;
import org.openqa.selenium.By;

import java.util.Properties;

public class HomePage {
    private static final Properties properties = Utils.getInstance().getProperties();

    public static String getBaseUrl() {
        return properties.getProperty("page.base_url");
    }


    public static By getBrowseCategoriesSelector() {
        return By.className("category");
    }

    public static By getApparelSelector() {
        return By.id("categories-nav-slot_apparel");
    }

    public static By getAllApparelSelector() {
        return By.id("categories-nav-slot_apparel_side-bar");
    }
}
