package org.parabank.test.automation.ui.stepdefs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.parabank.test.automation.ui.utils.ContextSteps;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class NewAccountStepdefs {
    private final ContextSteps context;

    public NewAccountStepdefs(ContextSteps context) {
        this.context = context;
    }

    @When("{} open(s) a new account with the following details")
    public void openANewAccount(String user, Map<String, String> account) {
        context.openNewAccountPage
                .selectAccountType(account.get("account type"))
                .selectTransferAccount(account.get("transfer account"));
        context.openNewAccountPage.submit();

        context.accountType = account.get("account type");
        context.transferAccountNumber = account.get("transfer account");
        context.transactionDate = LocalDate.now();
    }

    @Then("their account should be opened")
    public void theirAccountShouldBeOpened() {
        SoftAssertions softAssert = new SoftAssertions();
        softAssert.assertThat(context.openNewAccountPage.getTitle()).isEqualTo("Account Opened!");
        softAssert.assertThat(context.openNewAccountPage.getMessage()).isEqualTo("Congratulations, your account is now open.");
        softAssert.assertThat(context.openNewAccountPage.getAccountNumberMessage()).contains("Your new account number:");
        softAssert.assertAll();

        context.accountNumber = context.openNewAccountPage.getAccountNumber();
    }

    @And("the new account should have an {string} transaction")
    public void theNewAccountShouldHaveAnFundsTransferReceivedTransaction(String type) {
        context.accountDetailsPage = context.openNewAccountPage.clickAccountNumber();

        SoftAssertions softAssert = new SoftAssertions();
        softAssert.assertThat(context.accountDetailsPage.getAccountIdRow()).isEqualTo("Account Number: " + context.accountNumber);
        softAssert.assertThat(context.accountDetailsPage.getAccountTypeRow()).isEqualTo("Account Type: " + context.accountType.toUpperCase());
        softAssert.assertThat(context.accountDetailsPage.getAccountBalanceRow()).isEqualTo("Balance: $100.00");
        softAssert.assertThat(context.accountDetailsPage.getAccountAvailableBalanceRow()).isEqualTo("Available: $100.00");

        List<WebElement> transactions = context.accountDetailsPage.getTransactionsByDateAndType(context.transactionDate, context.accountType);

        for (WebElement transaction : transactions) {
            softAssert.assertThat(transaction.findElement(By.cssSelector("td:nth-child(1)")).getText()).isEqualTo(context.newAccountNumber);
            softAssert.assertThat(transaction.findElement(By.cssSelector("td:nth-child(2)")).getText()).isEqualTo(type);
            softAssert.assertThat(transaction.findElement(By.cssSelector("td:nth-child(3)")).getText()).isEmpty();
            softAssert.assertThat(transaction.findElement(By.cssSelector("td:nth-child(4)")).getText()).isEqualTo("$100.00");
        }
        softAssert.assertAll();
    }

    @And("the transfer account should have an {string} transaction")
    public void theTransferAccountShouldHaveAnFundsTransferTransaction(String type) {
        context.accountsOverviewPage = context.openNewAccountPage.clickAccountsOverviewLink();
        context.accountsOverviewPage.clickAccountLink(context.transferAccountNumber);

        SoftAssertions softAssert = new SoftAssertions();
        softAssert.assertThat(context.accountDetailsPage.getAccountIdRow()).isEqualTo("Account Number: " + context.newAccountNumber);
//        assertThat(context.accountDetailsPage.getAccountTypeRow()).isEqualTo("Account Type: " + context.accountType);
//        assertThat(context.accountDetailsPage.getAccountBalanceRow()).isEqualTo("Balance: $100.00");
//        assertThat(context.accountDetailsPage.getAccountAvailableBalanceRow()).isEqualTo("Available: $100.00");

        List<WebElement> transactions = context.accountDetailsPage.getTransactionsByDateAndType(context.transactionDate, context.accountType);

        for (WebElement transaction : transactions) {
            softAssert.assertThat(transaction.findElement(By.cssSelector("td:nth-child(1)")).getText()).isEqualTo(context.newAccountNumber);
            softAssert.assertThat(transaction.findElement(By.cssSelector("td:nth-child(2)")).getText()).isEqualTo(type);
            softAssert.assertThat(transaction.findElement(By.cssSelector("td:nth-child(3)")).getText()).isEqualTo("$100.00");
            softAssert.assertThat(transaction.findElement(By.cssSelector("td:nth-child(4)")).getText()).isEmpty();
        }
        softAssert.assertAll();

        // On OpenAccountPage, go to transfer account
        // On AccountDetailsPage, check:
        // - #accountId equals account number
        // - #accountType
        // - #balance and #availableBalance should be $100.00
        // cssSelector - #transactionTable > tbody > tr > td:nth-child(1) - should be today's date
        // cssSelector - #transactionTable > tbody > tr > td:nth-child(2) > a - should match 'Funds Transfer Sent'
        // cssSelector - #transactionTable > tbody > tr > td:nth-child(3) - should be $100.00
        // cssSelector - #transactionTable > tbody > tr > td.ng-binding.ng-scope - should be blank
    }

    @Given("the customer has an account")
    public void theCustomerHasAnAccount() {
    }

    @When("they view the account's details")
    public void theyViewTheAccountSDetails() {
    }
}
