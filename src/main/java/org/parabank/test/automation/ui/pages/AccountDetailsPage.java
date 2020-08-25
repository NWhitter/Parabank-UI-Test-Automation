package org.parabank.test.automation.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class AccountDetailsPage extends AbstractPage {

    private final By accountsOverviewLink = By.linkText("/parabank/overview.htm");

    private final By idLocator = By.id("accountId");
    private final By idRow = By.xpath("//*[@id=\"accountId\"]/..");
    private final By typeLocator = By.id("accountType");
    private final By typeRow = By.xpath("//*[@id=\"accountType\"]/..");
    private final By balanceLocator = By.id("balance");
    private final By balanceRow = By.xpath("//*[@id=\"balance\"]/..");
    private final By availableBalanceLocator = By.id("availableBalance");
    private final By availableBalanceRow = By.xpath("//*[@id=\"availableBalance\"]/..");

    private final By accountActivityRows = By.cssSelector("#transactionTable > tbody > tr");

    public AccountDetailsPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void goTo() {
        // https://parabank.parasoft.com/parabank/activity.htm?id=14010
        String goToUrl = baseUrl + "/openaccount.htm";

        if (!getUrl().equals(goToUrl)) {
            driver.get(baseUrl + "/openaccount.htm");
        }
    }

    public AccountsOverviewPage clickAccountsOverviewLink() {
        driver.findElement(accountsOverviewLink).click();
        return new AccountsOverviewPage(driver);
    }

    public String getAccountId() {
        return driver.findElement(idLocator).getText();
    }

    public String getAccountIdRow() {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.textMatches(idLocator, Pattern.compile("([.0-9])*\\d")));
        return driver.findElement(idRow).getText();
    }

    public String getAccountType() {
        return driver.findElement(typeLocator).getText();
    }

    public String getAccountTypeRow() {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.textMatches(typeLocator, Pattern.compile("([A-Z])\\w+")));
        return driver.findElement(typeRow).getText();
    }

    public String getAccountBalance() {
        return driver.findElement(balanceLocator).getText();
    }

    public String getAccountBalanceRow() {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.textMatches(balanceLocator, Pattern.compile("\\$\\d+[,]?\\d+(?:\\d*\\.)?\\d+")));
        return driver.findElement(balanceRow).getText();
    }

    public String getAccountAvailableBalance() {
        return driver.findElement(availableBalanceLocator).getText();
    }

    public String getAccountAvailableBalanceRow() {
        return driver.findElement(availableBalanceRow).getText();
    }

    public List<WebElement> getTransactionsByDate(LocalDate date) {
        if (date != null) {
            List<WebElement> rowsWithMatchingDate = new ArrayList<>();

            List<WebElement> transactionRows = driver.findElements(accountActivityRows);

            for (WebElement row : transactionRows) {
                WebElement rowDate = row.findElement(By.cssSelector("td:nth-child(1)"));

                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");

                if (rowDate.getText().equals(dtf.format(date))) {
                    // WebElement rowTransaction = row.findElement(By.cssSelector("td:nth-child(2)"));
                    // WebElement rowDebit = row.findElement(By.cssSelector("td:nth-child(3)"));
                    rowsWithMatchingDate.add(row);
                }
            }
            return rowsWithMatchingDate;
        }
        throw new RuntimeException("Error! Date is " + date);
    }

    public List<WebElement> getTransactionsByType(String type) {
        if (type != null) {
            List<WebElement> rowsWithMatchingType = new ArrayList<>();

            List<WebElement> transactionRows = driver.findElements(accountActivityRows);

            for (WebElement row : transactionRows) {
                WebElement rowType = row.findElement(By.cssSelector("td:nth-child(2)"));

                if (rowType.getText().toLowerCase().equals(type.toLowerCase())) {
                    // WebElement rowTransaction = row.findElement(By.cssSelector("td:nth-child(2)"));
                    // WebElement rowDebit = row.findElement(By.cssSelector("td:nth-child(3)"));
                    rowsWithMatchingType.add(row);
                }
            }
            return rowsWithMatchingType;
        }
        throw new RuntimeException("Error! Type is " + type);
    }

    public List<WebElement> getTransactionsByDateAndType(LocalDate date, String type) {
        if (date != null || type != null) {
            List<WebElement> rowsWithMatchingType = new ArrayList<>();

            List<WebElement> transactionRows = driver.findElements(accountActivityRows);

            for (WebElement row : transactionRows) {
                WebElement rowDate = row.findElement(By.cssSelector("td:nth-child(1)"));
                WebElement rowType = row.findElement(By.cssSelector("td:nth-child(2)"));
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");

                if (rowType.getText().toLowerCase().equals(type.toLowerCase()) &&
                        rowDate.getText().equals(dtf.format(date))) {
                    // WebElement rowTransaction = row.findElement(By.cssSelector("td:nth-child(2)"));
                    // WebElement rowDebit = row.findElement(By.cssSelector("td:nth-child(3)"));
                    rowsWithMatchingType.add(row);
                }
            }
            return rowsWithMatchingType;
        }
        throw new RuntimeException("Error! Type is " + type);
    }
}

// Add waits to get element
