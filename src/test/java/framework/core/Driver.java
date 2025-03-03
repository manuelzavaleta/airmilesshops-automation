package framework.core;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Driver {

    private static WebDriver driver;

    private Driver() {
    }

    public static void setupDriver() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    public static WebDriver getDriver() {
        if (!hasSession()) {
            setupDriver();
        }
        return driver;
    }

    public static void quit() {
        if (hasSession()) {
            driver.quit();
        }
    }

    private static boolean hasSession() {
        return driver != null && !driver.toString().contains("(null)");
    }
}