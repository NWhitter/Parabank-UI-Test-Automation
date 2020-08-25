package org.parabank.test.automation.ui.stepdefs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.assertj.core.api.SoftAssertions;
import org.parabank.test.automation.ui.configuration.World;

import java.time.LocalDate;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class NewAccountStepdefs {
    private final World context;

    public NewAccountStepdefs(World context) {
        this.context = context;
    }

    @When("{} open(s) a new account with the following details")
    public void openANewAccount(String user, Map<String, String> account) {
        context.getOpenNewAccountPage()
                .selectAccountType(account.get("account type"))
                .selectTransferAccount(account.get("transfer account"))
                .clickOpenNewAccountButton();

        context.setAccountType(account.get("account type"));
        context.setTransferAccountNumber(account.get("transfer account"));
        context.setTransactionDate(LocalDate.now());
    }

    @Then("their account should be opened")
    public void theirAccountShouldBeOpened() {
        SoftAssertions softAssert = new SoftAssertions();
        assertThat(context.getOpenNewAccountPage().getTitle()).isEqualTo("Account Opened!");
        assertThat(context.getOpenNewAccountPage().getMessage()).isEqualTo("Congratulations, your account is now open.");
        assertThat(context.getOpenNewAccountPage().getAccountNumberMessage()).contains("Your new account number:");
        softAssert.assertAll();

        context.setAccountNumber(context.getOpenNewAccountPage().getAccountNumber());
    }

    @And("the new account should have an {string} transaction")
    public void theNewAccountShouldHaveAnFundsTransferReceivedTransaction(String transaction) {
    }

    @And("the transfer account should have an {string} transaction")
    public void theTransferAccountShouldHaveAnFundsTransferTransaction(String transaction) {
    }

    @Given("the customer has an account")
    public void theCustomerHasAnAccount() {
    }

    @When("they view the account's details")
    public void theyViewTheAccountSDetails() {
    }
}
