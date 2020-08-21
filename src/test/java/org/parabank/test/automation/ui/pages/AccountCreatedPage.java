package org.parabank.test.automation.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AccountCreatedPage extends Page {
    private final By loggedInNameParagraph = By.className("smallText");
    private final By messageParagraph = By.cssSelector("#rightPanel > p");
    private final By logoutLink = By.cssSelector("#leftPanel > ul > li:nth-child(8) > a");

    public AccountCreatedPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void goTo() {
        driver.get(baseUrl + "register.htm");
    }

    public String getLoggedInName() {
        return driver.findElement(loggedInNameParagraph).getText();
    }

    public String getMessage() {
        return driver.findElement(messageParagraph).getText();
    }

    public LandingPage clickLogout() {
        driver.findElement(logoutLink).click();
        return new LandingPage(driver);
    }
}
