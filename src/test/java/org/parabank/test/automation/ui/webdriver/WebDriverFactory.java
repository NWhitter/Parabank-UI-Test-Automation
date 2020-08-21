package org.parabank.test.automation.ui.webdriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.parabank.test.automation.ui.enums.WebBrowsers;
import org.parabank.test.automation.ui.helper.PropertyLoader;

import static org.parabank.test.automation.ui.enums.WebBrowsers.CHROME_LOCAL;

public class WebDriverFactory {
    public static WebDriver initDriver() {

        WebBrowsers browser = WebBrowsers.valueOf(PropertyLoader.getBrowserName());

        switch (browser) {
            case CHROME_REMOTE:
            case CHROME_LOCAL:
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                if (browser.equals(CHROME_LOCAL)) {
                    chromeOptions.addArguments("--headless",
                            "--disable-gpu",
                            "--window-size=1920,1200",
                            "--ignore-certificate-errors");
                }
                return new ChromeDriver(chromeOptions);

            case FIREFOX_LOCAL:
                WebDriverManager.firefoxdriver().setup();
                return new FirefoxDriver();

            case EDGE_LOCAL:
                WebDriverManager.edgedriver().setup();
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.setHeadless(true)
                        .addArguments(
                                "--disable-gpu",
                                "--window-size=1920,1200",
                                "--ignore-certificate-errors");
                return new EdgeDriver();

            case SAFARI_LOCAL:
                return new SafariDriver();

            default:
                throw new RuntimeException("Unsupported browser type: " + browser);
        }
    }
}
