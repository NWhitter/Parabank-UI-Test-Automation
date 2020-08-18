package org.parabank.test.automation.ui.stepDefinitions;

import io.cucumber.java.en.Given;
import org.parabank.test.automation.ui.pages.*;
import org.parabank.test.automation.ui.webdriver.ContextSteps;

public class CommonStepdefs {
    private final ContextSteps context;

    public CommonStepdefs(ContextSteps context) {
        this.context = context;
    }

    @Given("the customer is on their/the {} page")
    public Page theCustomerIsOnThePage(String page) {
        switch (page.toLowerCase()) {
            case "customer login":
                context.landingPage = new LandingPage(context.getDriver());
            case "account overview":
                context.setCookie();
                context.accountsOverviewPage = new AccountsOverviewPage(context.getDriver());
            case "sign up":
                context.signUpPage = new SignUpPage(context.getDriver());
            case "open new account":
                context.openNewAccountPage = new OpenNewAccountPage(context.getDriver());
            default:
                throw new RuntimeException("Incorrect page: " + page);
        }
    }
}
