package org.parabank.test.automation.ui.stepdefs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.parabank.test.automation.ui.models.Customer;
import org.parabank.test.automation.ui.pages.AccountsOverviewPage;
import org.parabank.test.automation.ui.webdriver.ContextSteps;

import java.util.Map;

public class LoginStepdefs {
    private final ContextSteps contextSteps;

    public LoginStepdefs(ContextSteps contextSteps) {
        this.contextSteps = contextSteps;
    }

    @When("{} signs up with the following details")
    public void theCustomerSignsUpWithTheFollowingDetails(String user, Customer signUpCredentials) {

        contextSteps.firstName = signUpCredentials.getFirstName();
        contextSteps.lastName = signUpCredentials.getLastName();
        contextSteps.username = signUpCredentials.getUsername();

        contextSteps.signUpPage.submitForm(signUpCredentials);
    }

    @When("{} sign(s) in with the following details")
    public void theCustomerSignsInWithTheFollowingDetails(String user, Map<String, String> loginCredentials) {

        contextSteps.username = loginCredentials.get("username");
        contextSteps.firstName = loginCredentials.getOrDefault("first name", "John");
        contextSteps.lastName = loginCredentials.getOrDefault("last name", "Smith");

        Customer customer = new Customer(contextSteps.username, loginCredentials.get("password"),
                contextSteps.firstName,
                contextSteps.lastName);

        contextSteps.landingPage.login(customer,
                AccountsOverviewPage.class.getSimpleName());
    }

    @When("{} log(s) out")
    public void theCustomerLogsOut(String user) {
        contextSteps.landingPage = contextSteps.accountsOverviewPage.clickLogout();
    }

    @Then("their accounts overview page should be displayed")
    public void theCustomersAccountOverviewPageShouldBeDisplayed() {
        contextSteps.accountsOverviewPage = new AccountsOverviewPage(contextSteps.getDriver());
        Assert.assertEquals(String.format("Welcome %s %s", contextSteps.firstName, contextSteps.lastName),
                contextSteps.accountsOverviewPage.getLoggedInName());
    }

    @Then("the customer {} page should be displayed")
    public void theCustomerPageShouldBeDisplayed(String page) {
        Assert.assertTrue(contextSteps.landingPage.getUrl().contains(page + ".htm"));
    }

    @And("the error message {string} should be displayed")
    public void theErrorMessageShouldBeDisplayed(String message) {
        Assert.assertEquals(message, contextSteps.landingPage.getErrorMessage());
    }

    @And("the error title {string} should be displayed")
    public void theErrorTitleErrorShouldBeDisplayed(String title) {
        Assert.assertEquals(title, contextSteps.landingPage.getErrorTitle());
    }

    @And("the message {string} should be displayed")
    public void theMessageShouldBeDisplayed(String message) {
        Assert.assertEquals(message, contextSteps.accountsOverviewPage.getMessage());
    }
}
