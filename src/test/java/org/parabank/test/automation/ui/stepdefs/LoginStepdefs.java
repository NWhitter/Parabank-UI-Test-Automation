package org.parabank.test.automation.ui.stepdefs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.parabank.test.automation.ui.helper.PropertyLoader;
import org.parabank.test.automation.ui.models.Customer;
import org.parabank.test.automation.ui.pages.AccountCreatedPage;
import org.parabank.test.automation.ui.pages.AccountsOverviewPage;
import org.parabank.test.automation.ui.pages.LandingPage;
import org.parabank.test.automation.ui.webdriver.ContextSteps;

import static org.assertj.core.api.Assertions.assertThat;

public class LoginStepdefs {
    private final ContextSteps contextSteps;

    public LoginStepdefs(ContextSteps contextSteps) {
        this.contextSteps = contextSteps;
    }

    @When("{} sign(s) up with the following details")
    public void theCustomerSignsUpWithTheFollowingDetails(String user, Customer credentials) {

        contextSteps.customer = credentials;
        contextSteps.signUpPage.submitForm(credentials);
    }

    @When("{} sign(s) in with the following details")
    public void theCustomerSignsInWithTheFollowingDetails(String user, Customer customer) {
        if (customer.getUsername().equals("john")) {
            customer.setFirstName("John");
            customer.setLastName("Smith");
        }

        contextSteps.landingPage.login(customer,
                AccountsOverviewPage.class.getSimpleName());
    }

    @When("{} log(s) out")
    public void theCustomerLogsOut(String user) {
        contextSteps.landingPage = contextSteps.accountsOverviewPage.clickLogout();
    }

    @Then("the/their {} page should be displayed")
    public void thePageShouldBeDisplayed(String page) {
        switch (page) {
            case "customer login":
                contextSteps.landingPage = new LandingPage(contextSteps.getDriver());
                assertThat(contextSteps.landingPage.getUrl()).contains(PropertyLoader.getLoginPageUrl());
                break;
            case "customer index":
                contextSteps.landingPage = new LandingPage(contextSteps.getDriver());
                assertThat(contextSteps.landingPage.getUrl()).contains(PropertyLoader.getIndexPageUrl());
                break;
            case "accounts overview":
                contextSteps.accountsOverviewPage = new AccountsOverviewPage(contextSteps.getDriver());
                assertThat(contextSteps.accountsOverviewPage.getUrl()).contains(PropertyLoader.getAccountManagementUrl());
                break;
            case "account created":
                contextSteps.accountCreatedPage = new AccountCreatedPage(contextSteps.getDriver());
                assertThat(contextSteps.accountCreatedPage.getUrl()).contains(PropertyLoader.getAccountCreatedUrl());

                String loginMessage = String.format("Welcome %s %s", contextSteps.customer.getFirstName(),
                        contextSteps.customer.getLastName());
                assertThat(contextSteps.accountCreatedPage.getLoggedInName()).contains(loginMessage);
                break;
            default:
                throw new RuntimeException("Incorrect page: " + page);
        }
    }

    @And("the error message {string} should be displayed")
    public void theErrorMessageShouldBeDisplayed(String message) {
        assertThat(contextSteps.landingPage.getErrorMessage()).isEqualTo(message);
    }

    @And("the error title {string} should be displayed")
    public void theErrorTitleErrorShouldBeDisplayed(String title) {
        assertThat(contextSteps.landingPage.getErrorTitle()).isEqualTo(title);
    }

    @And("the message {string} should be displayed")
    public void theMessageShouldBeDisplayed(String message) {
        assertThat(contextSteps.accountCreatedPage.getMessage()).isEqualTo(message);
    }
}
