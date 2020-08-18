package org.parabank.test.automation.ui.pages;

import org.openqa.selenium.WebDriver;

public abstract class Page {
    String baseUrl = "https://parabank.parasoft.com/parabank/";
    WebDriver driver;

    public Page(WebDriver driver) {
        this.driver = driver;
    }

    public abstract void goTo();

    public String getTitle() {
        return this.driver.getTitle();
    }

    public String getUrl() {
        return this.driver.getCurrentUrl().split(";")[0];
    }
}
