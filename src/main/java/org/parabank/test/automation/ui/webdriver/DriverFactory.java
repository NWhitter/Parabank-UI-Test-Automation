package org.parabank.test.automation.ui.webdriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.parabank.test.automation.ui.configuration.Configuration;
import org.parabank.test.automation.ui.configuration.ConfigurationLoader;
import org.parabank.test.automation.ui.enums.WebBrowser;

public class DriverFactory {

    private DriverFactory() {
    }

    public static WebDriver init() {

        Configuration configuration = ConfigurationLoader.getConfiguration();

        WebBrowser browser = WebBrowser.valueOf(configuration.browser().toUpperCase());
        boolean isHeadless = ConfigurationLoader.getConfiguration().headless();

        switch (browser) {
            case CHROME:
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                if (isHeadless) {
                    chromeOptions.addArguments("--headless",
                            "--disable-gpu",
                            "--window-size=1920,1200",
                            "--ignore-certificate-errors");
                }
                return new ChromeDriver(chromeOptions);

            case FIREFOX:
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                if (isHeadless) {
                    firefoxOptions.addArguments("-headless",
                            "-height 1200",
                            "-width 1920");
                }
                return new FirefoxDriver();

            case EDGE:
                WebDriverManager.edgedriver().setup();
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.setHeadless(true)
                        .addArguments(
                                "--disable-gpu",
                                "--window-size=1920,1200",
                                "--ignore-certificate-errors");
                return new EdgeDriver();

            case SAFARI:
                if (isHeadless) {
                    System.out.println("Safari headless is not supported, using Windowed mode");
                }
                return new SafariDriver();

            default:
                throw new RuntimeException("Unsupported browser type: " + browser);
        }
    }
}
