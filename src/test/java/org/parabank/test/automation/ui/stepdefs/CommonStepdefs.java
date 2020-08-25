package org.parabank.test.automation.ui.stepdefs;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.WebDriver;
import org.parabank.test.automation.ui.config.Configuration;
import org.parabank.test.automation.ui.config.ConfigurationLoader;
import org.parabank.test.automation.ui.driver.DriverManager;
import org.parabank.test.automation.ui.pages.*;
import org.parabank.test.automation.ui.utils.ContextSteps;

public class CommonStepdefs {
    private final ContextSteps context;
    private final WebDriver driver;

    public CommonStepdefs(ContextSteps context) {
        this.context = context;
        this.driver = DriverManager.getDriver();
    }

    @Given("{} are/is on their/the {} page")
    public void theCustomerIsOnThePage(String user, String page) {
        WebDriver driver = DriverManager.getDriver();

        switch (page.toLowerCase()) {
            case "customer login":
                context.landingPage = new LandingPage(driver);
                context.landingPage.goTo();
                break;
            case "account overview":
            case "accounts overview":
                DriverManager.setCookie();

                context.accountsOverviewPage = new AccountsOverviewPage(driver);
                context.accountsOverviewPage.goTo();
                break;
            case "sign up":
                context.signUpPage = new SignUpPage(driver);
                context.signUpPage.goTo();
                break;
            case "open new account":
                DriverManager.setCookie();

                context.openNewAccountPage = new OpenNewAccountPage(driver);
                context.openNewAccountPage.goTo();
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
                context.landingPage = new LandingPage(driver);
                softly.assertThat(context.landingPage.getUrl()).contains(config.loginPageUrl());
                break;
            case "customer index":
                context.landingPage = new LandingPage(driver);
                softly.assertThat(context.landingPage.getUrl()).contains(config.indexPageUrl());
                break;
            case "accounts overview":
                context.accountsOverviewPage = new AccountsOverviewPage(driver);
                softly.assertThat(context.accountsOverviewPage.getUrl()).contains(config.accountOverviewPageUrl());
                break;
            case "account created":
                context.accountCreatedPage = new AccountCreatedPage(driver);
                softly.assertThat(context.accountCreatedPage.getUrl()).contains(config.accountCreatedPageUrl());

                String loginMessage = String.format("Welcome %s %s", context.customer.getFirstName(),
                        context.customer.getLastName());
                softly.assertThat(context.accountCreatedPage.getLoggedInName()).contains(loginMessage);
                break;
            default:
                throw new RuntimeException("Incorrect page: " + page);
        }
        softly.assertAll();
    }
}
