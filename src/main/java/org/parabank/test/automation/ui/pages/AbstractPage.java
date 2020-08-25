package org.parabank.test.automation.ui.pages;

import org.openqa.selenium.WebDriver;
import org.parabank.test.automation.ui.configuration.Configuration;
import org.parabank.test.automation.ui.configuration.ConfigurationLoader;

public abstract class AbstractPage {
    WebDriver driver;
    String baseUrl;

    public AbstractPage(WebDriver driver) {
        this.driver = driver;

        Configuration configuration = ConfigurationLoader.getConfiguration();
        baseUrl = configuration.url();
    }

    public abstract void goTo();

    public String getTitle() {
        return this.driver.getTitle();
    }

    public String getUrl() {
        return this.driver.getCurrentUrl().split(";")[0];
    }
}
