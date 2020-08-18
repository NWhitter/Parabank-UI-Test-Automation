package org.parabank.test.automation.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class OpenNewAccountPage extends Page {

    private final By accountTypeLocator = By.id("type");
    private final By transferAccountDropDown = By.id("fromAccountId");
    private final By openNewAccountButton = By.cssSelector("input[type='submit']");
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

        if (! getUrl().equals(goToUrl)) {
            driver.get(baseUrl + "/openaccount.htm");
        }
    }

    private OpenNewAccountPage selectAccountType(String type) {
        if (type != null) {
                Select accountTypeDropdown = new Select(driver.findElement(accountTypeLocator));
                if (accountTypeDropdown.getFirstSelectedOption().getText().equals(type))
                {
                    accountTypeDropdown.selectByVisibleText(type);
                }
        }
        return this;
    }

    private OpenNewAccountPage selectTransferAccount(String account) {
        if (account != null) {
            Select tad = new Select(driver.findElement(transferAccountDropDown));
            if (tad.getFirstSelectedOption().getText().equals(account)) {
                new Select(driver.findElement(transferAccountDropDown)).selectByVisibleText(account);
            }
        }
        return this;
    }

    private OpenNewAccountPage clickOpenNewAccountButton() {
        driver.findElement(openNewAccountButton).click();

        return this;
    }

    public String getTitle() {
        return driver.findElement(titleHeader).getText();
    }

    public String getMessage() {
       return driver.findElement(message).getText();
    }

    public WebElement getAccountNumber() {
        return driver.findElement(accountNumber);
    }
}
