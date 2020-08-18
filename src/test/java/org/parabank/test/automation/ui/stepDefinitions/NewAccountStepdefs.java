package org.parabank.test.automation.ui.stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.parabank.test.automation.ui.pages.OpenNewAccountPage;

import java.util.Map;

public class NewAccountStepdefs {
    private OpenNewAccountPage openNewAccountPage;

    @When("{} open(s) a new account with the following details")
    public void openANewAccountWithTheFollowingDetails(String user, Map<String, String> account) {

    }

    @Then("their account should be successfully opened")
    public void theirAccountShouldBeSuccessfullyOpened() {
    }

    @And("the new account should have an funds transfer received transaction")
    public void theNewAccountShouldHaveAnFundsTransferReceivedTransaction() {
    }

    @And("the transfer account should have an funds transfer transaction")
    public void theTransferAccountShouldHaveAnFundsTransferTransaction() {
    }

    @Given("the customer has an account")
    public void theCustomerHasAnAccount() {
    }

    @And("they are on the accounts overview page")
    public void theyAreOnTheAccountsOverviewPage() {
        openNewAccountPage.goTo();
    }

    @When("they view the account's details")
    public void theyViewTheAccountSDetails() {
    }
}
