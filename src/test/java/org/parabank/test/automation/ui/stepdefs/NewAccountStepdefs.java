package org.parabank.test.automation.ui.stepdefs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.parabank.test.automation.ui.webdriver.ContextSteps;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class NewAccountStepdefs {
    private final ContextSteps context;

    public NewAccountStepdefs(ContextSteps context) {
        this.context = context;
    }

    @When("{} open(s) a new account with the following details")
    public void openANewAccount(String user, Map<String, String> account) {
        context.openNewAccountPage
                .selectAccountType(account.get("account type"))
                .selectTransferAccount(account.get("transfer account"))
                .clickOpenNewAccountButton();
    }

    @Then("their account should be opened")
    public void theirAccountShouldBeOpened() {
        assertThat(context.openNewAccountPage.getTitle()).isEqualTo("Account Opened!");
        assertThat(context.openNewAccountPage.getMessage()).isEqualTo("Congratulations, your account is now open.");
        assertThat(context.openNewAccountPage.getAccountNumberMessage()).contains("Your new account number:");

        context.accountNumber = context.openNewAccountPage.getAccountNumber();
    }

    @And("the new account should have an 'funds transfer received' transaction")
    public void theNewAccountShouldHaveAnFundsTransferReceivedTransaction() {
    }

    @And("the transfer account should have an 'funds transfer' transaction")
    public void theTransferAccountShouldHaveAnFundsTransferTransaction() {
    }

    @Given("the customer has an account")
    public void theCustomerHasAnAccount() {
    }

    @When("they view the account's details")
    public void theyViewTheAccountSDetails() {
    }
}
