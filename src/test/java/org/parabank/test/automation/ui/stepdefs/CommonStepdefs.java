package org.parabank.test.automation.ui.stepdefs;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.WebDriver;
import org.parabank.test.automation.ui.configuration.Configuration;
import org.parabank.test.automation.ui.configuration.ConfigurationLoader;
import org.parabank.test.automation.ui.configuration.World;
import org.parabank.test.automation.ui.pages.*;
import org.parabank.test.automation.ui.webdriver.DriverManager;

public class CommonStepdefs {
    private final World context;
    private final WebDriver driver;

    public CommonStepdefs(World context) {
        this.context = context;
        this.driver = DriverManager.getDriver();
    }

    @Given("{} are/is on their/the {} page")
    public void theCustomerIsOnThePage(String user, String page) {
        switch (page.toLowerCase()) {
            case "customer login":
                context.setLandingPage(new LandingPage(driver));
                context.getLandingPage().goTo();
                break;
            case "account overview":
            case "accounts overview":
                DriverManager.setCookie();

                context.setAccountsOverviewPage(new AccountsOverviewPage(driver));
                context.getAccountsOverviewPage().goTo();
                break;
            case "sign up":
                context.setSignUpPage(new SignUpPage(driver));
                context.getSignUpPage().goTo();
                break;
            case "open new account":
                DriverManager.setCookie();

                context.setOpenNewAccountPage(new OpenNewAccountPage(driver));
                context.getOpenNewAccountPage().goTo();
                break;
            default:
                throw new RuntimeException("Incorrect page: " + page);
        }
    }

    @Then("the/their {} page should be displayed")
    public void thePageShouldBeDisplayed(String page) {
        SoftAssertions softly = new SoftAssertions();
        Configuration config = ConfigurationLoader.getConfiguration();

        switch (page) {
            case "customer login":
                context.setLandingPage(new LandingPage(driver));
                softly.assertThat(context.getLandingPage().getUrl()).contains(config.loginPageUrl());
                break;
            case "customer index":
                context.setLandingPage(new LandingPage(driver));
                softly.assertThat(context.getLandingPage().getUrl()).contains(config.indexPageUrl());
                break;
            case "accounts overview":
                context.setAccountsOverviewPage(new AccountsOverviewPage(driver));
                softly.assertThat(context.getAccountsOverviewPage().getUrl()).contains(config.accountOverviewPageUrl());
                break;
            case "account created":
                context.setAccountCreatedPage(new AccountCreatedPage(driver));
                softly.assertThat(context.getAccountCreatedPage().getUrl()).contains(config.accountCreatedPageUrl());

                String loginMessage = String.format("Welcome %s %s", context.getCustomer().getFirstName(),
                        context.getCustomer().getLastName());
                softly.assertThat(context.getAccountCreatedPage().getLoggedInName()).contains(loginMessage);
                break;
            default:
                throw new RuntimeException("Incorrect page: " + page);
        }
        softly.assertAll();
    }
}
