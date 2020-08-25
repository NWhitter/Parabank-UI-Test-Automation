package org.parabank.test.automation.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

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

        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(openNewAccountButton));
        submitButton.click();

        return this;
    }

    public OpenNewAccountPage submit() {
        do {
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            driver.findElement(transferAccountDropDown).submit();
        } while (driver.findElement(transferAccountDropDown).isDisplayed());

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
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(message));

        return driver.findElement(message).getText();
    }

    public String getAccountNumberMessage() {
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofSeconds(2))
                .ignoring(NoSuchElementException.class);

        WebElement foo = wait.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver driver) {
                return driver.findElement(accountNumberMessage);
            }
        });

        return foo.getText();
    }

    public String getAccountNumber() {
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofSeconds(2))
                .ignoring(NoSuchElementException.class);

        WebElement foo = wait.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver driver) {
                return driver.findElement(accountNumber);
            }
        });

        return foo.getText();
    }
}
