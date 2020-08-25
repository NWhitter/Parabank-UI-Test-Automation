package org.parabank.test.automation.ui.stepdefs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.parabank.test.automation.ui.models.Customer;
import org.parabank.test.automation.ui.pages.AccountsOverviewPage;
import org.parabank.test.automation.ui.utils.ContextSteps;

import static org.assertj.core.api.Assertions.assertThat;

public class LoginStepdefs {
    private final ContextSteps context;

    public LoginStepdefs(ContextSteps context) {
        this.context = context;
    }

    @When("{} sign(s) up with the following details")
    public void theCustomerSignsUpWithTheFollowingDetails(String user, Customer credentials) {

        context.customer = credentials;
        context.signUpPage.submitForm(credentials);
    }

    @When("{} sign(s) in with the following details")
    public void theCustomerSignsInWithTheFollowingDetails(String user, Customer customer) {
        if (customer.getUsername().equals("john")) {
            customer.setFirstName("John");
            customer.setLastName("Smith");
        }

        context.landingPage.login(customer,
                AccountsOverviewPage.class.getSimpleName());
    }

    @When("{} log(s) out")
    public void theCustomerLogsOut(String user) {
        context.landingPage = context.accountsOverviewPage.clickLogout();
    }

    @And("the error message {string} should be displayed")
    public void theErrorMessageShouldBeDisplayed(String message) {
        assertThat(context.landingPage.getErrorMessage()).isEqualTo(message);
    }

    @And("the error title {string} should be displayed")
    public void theErrorTitleErrorShouldBeDisplayed(String title) {
        assertThat(context.landingPage.getErrorTitle()).isEqualTo(title);
    }

    @And("the message {string} should be displayed")
    public void theMessageShouldBeDisplayed(String message) {
        assertThat(context.accountCreatedPage.getMessage()).isEqualTo(message);
    }
}
