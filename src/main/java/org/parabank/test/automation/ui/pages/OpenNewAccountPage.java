package org.parabank.test.automation.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class OpenNewAccountPage extends AbstractPage {

    private final By accountsOverviewLink = By.linkText("Accounts Overview");

    private final By accountTypeLocator = By.id("type");
    private final By transferAccountDropDown = By.id("fromAccountId");
    private final By openNewAccountButton = By.cssSelector("#rightPanel > div > div > form > div > input");
    private final By titleHeader = By.className("title");
    private final By message = By.cssSelector(".ng-scope > p:nth-child(2)");
    private final By accountNumberMessage = By.cssSelector(".ng-scope > p:nth-child(3)");
    private final By accountNumber = By.id("newAccountId");

    public OpenNewAccountPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void goTo() {
        String goToUrl = baseUrl + "/openaccount.htm";

        if (!getUrl().equals(goToUrl)) {
            driver.get(baseUrl + "/openaccount.htm");
        }
    }

    public AccountsOverviewPage clickAccountsOverviewLink() {
        driver.findElement(accountsOverviewLink).click();

        return new AccountsOverviewPage(driver);
    }

    public OpenNewAccountPage selectAccountType(String type) {
        if (type != null) {
            Select accountTypeDropdown = new Select(driver.findElement(accountTypeLocator));
            if (accountTypeDropdown.getFirstSelectedOption().getText().equals(type)) {
                accountTypeDropdown.selectByVisibleText(type);
            }
        }
        return this;
    }

    public OpenNewAccountPage selectTransferAccount(String account) {
        if (account != null) {
            Select tad = new Select(driver.findElement(transferAccountDropDown));
            if (tad.getFirstSelectedOption().getText().equals(account)) {
                new Select(driver.findElement(transferAccountDropDown)).selectByVisibleText(account);
            }
        }
        return this;
    }

    public OpenNewAccountPage clickOpenNewAccountButton() {
        driver.findElement(openNewAccountButton).click();

        return this;
    }

    public AccountDetailsPage clickAccountNumber() {
        driver.findElement(accountNumber).click();

        return new AccountDetailsPage(driver);
    }

    public String getTitle() {
        return driver.findElement(titleHeader).getText();
    }

    public String getMessage() {
        return driver.findElement(message).getText();
    }

    public String getAccountNumberMessage() {
        return driver.findElement(accountNumberMessage).getText();
    }

    public String getAccountNumber() {
        return driver.findElement(accountNumber).getText();
    }
}
