package org.parabank.test.automation.ui.stepdefs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.parabank.test.automation.ui.configuration.World;
import org.parabank.test.automation.ui.models.Customer;
import org.parabank.test.automation.ui.pages.AccountsOverviewPage;

import static org.assertj.core.api.Assertions.assertThat;

public class LoginStepdefs {
    private final World world;

    public LoginStepdefs(World world) {
        this.world = world;
    }

    @When("{} sign(s) up with the following details")
    public void theCustomerSignsUpWithTheFollowingDetails(String user, Customer credentials) {

        world.setCustomer(credentials);
        world.getSignUpPage().submitForm(credentials);
    }

    @When("{} sign(s) in with the following details")
    public void theCustomerSignsInWithTheFollowingDetails(String user, Customer customer) {
        if (customer.getUsername().equals("john")) {
            customer.setFirstName("John");
            customer.setLastName("Smith");
        }

        world.getLandingPage().login(customer,
                AccountsOverviewPage.class.getSimpleName());
    }

    @When("{} log(s) out")
    public void theCustomerLogsOut(String user) {
        world.setLandingPage(world.getAccountsOverviewPage().clickLogout());
    }

    @And("the error message {string} should be displayed")
    public void theErrorMessageShouldBeDisplayed(String message) {
        assertThat(world.getLandingPage().getErrorMessage()).isEqualTo(message);
    }

    @And("the error title {string} should be displayed")
    public void theErrorTitleErrorShouldBeDisplayed(String title) {
        assertThat(world.getLandingPage().getErrorTitle()).isEqualTo(title);
    }

    @And("the message {string} should be displayed")
    public void theMessageShouldBeDisplayed(String message) {
        assertThat(world.getAccountCreatedPage().getMessage()).isEqualTo(message);
    }
}
