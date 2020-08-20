package org.parabank.test.automation.ui.webdriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.parabank.test.automation.ui.enums.WebBrowsers;

public class WebDriverFactory {
    public static WebDriver initDriver(WebBrowsers driver) {

        switch (driver) {
            case CHROME:
                WebDriverManager.chromedriver().setup();
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--headless", "--disable-gpu", "--window-size=1920,1200", "--ignore-certificate-errors");
                return new ChromeDriver(options);

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
