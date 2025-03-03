package framework.pages;

import framework.core.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Properties;
import java.util.function.Predicate;

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

    public static List<WebElement> getAllLinkChildElementsFromSubCategoryElement(WebElement subCategoryNode) {
        return subCategoryNode.findElements(By.xpath("ancestor::li/div/div/a"));
    }

    public static Predicate<WebElement> filterByAriaLabel(String ariaLabelValue) {
        return webElement -> ariaLabelValue.equals(webElement.getAttribute("aria-label"));
    }

    public static By getAllInputSelector() {
        return By.tagName("input");
    }

    public static By getAllButtonSelector() {
        return By.tagName("button");
    }
}
