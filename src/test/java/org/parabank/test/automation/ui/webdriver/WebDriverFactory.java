package org.parabank.test.automation.ui.webdriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.parabank.test.automation.ui.enums.WebBrowsers;

public class WebDriverFactory {
    public static WebDriver initDriver(WebBrowsers driver) {

        switch (driver) {
            case CHROME:
                WebDriverManager.chromedriver().setup();
                return new ChromeDriver();

            case FIREFOX:
                WebDriverManager.firefoxdriver().setup();
                return new FirefoxDriver();

            case SAFARI:
                return new SafariDriver();

            default:
                throw new RuntimeException("Unsupported webdriver: " + driver);
        }
    }
}
