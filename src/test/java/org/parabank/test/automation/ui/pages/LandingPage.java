package org.parabank.test.automation.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.parabank.test.automation.ui.models.Customer;

public class LandingPage extends Page {

    private final By nameInput = By.name("username");
    private final By passwordInput = By.name("password");
    private final By submitInput = By.cssSelector(("input[type='submit']"));
    private final By errorHeading = By.className("title");
    private final By errorParagraph = By.className("error");

    public LandingPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void goTo() {
        driver.get(baseUrl + "index.htm");
    }

    public LandingPage typeUsername(String username) {
        if (username != null) {
            driver.findElement(nameInput).sendKeys(username);
        }

        return this;
    }

    public LandingPage typePassword(String password) {
        if (password != null) {
            driver.findElement(passwordInput).sendKeys(password);
        }

        return this;
    }

    public Page clickSubmit(String pageName) {
        driver.findElement(submitInput).click();

        return selectPage(pageName);
    }

    public Page login(Customer customer, String pageName) {
        typeUsername(customer.getUsername());
        typePassword(customer.getPassword());
        return clickSubmit(pageName);
    }

    public Page selectPage(String pageName) {

        if (pageName.equals(LandingPage.class.getSimpleName())) {
            return new LandingPage(driver);
        } else if (pageName.equals(AccountsOverviewPage.class.getSimpleName())) {
            return new AccountsOverviewPage(driver);
        }
        throw new RuntimeException("Unsupported page: " + pageName);
    }

    public String getErrorTitle() {
        return driver.findElement(errorHeading).getText();
    }

    public String getErrorMessage() {
        return driver.findElement(errorParagraph).getText();
    }
}
