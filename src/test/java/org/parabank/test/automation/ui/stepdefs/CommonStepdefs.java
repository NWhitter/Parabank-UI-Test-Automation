package org.parabank.test.automation.ui.stepdefs;

import io.cucumber.java.en.Given;
import org.parabank.test.automation.ui.pages.AccountsOverviewPage;
import org.parabank.test.automation.ui.pages.LandingPage;
import org.parabank.test.automation.ui.pages.OpenNewAccountPage;
import org.parabank.test.automation.ui.pages.SignUpPage;
import org.parabank.test.automation.ui.webdriver.ContextSteps;

public class CommonStepdefs {
    private final ContextSteps context;

    public CommonStepdefs(ContextSteps context) {
        this.context = context;
    }

    @Given("{} are/is on their/the {} page")
    public void theCustomerIsOnThePage(String user, String page) {
        switch (page.toLowerCase()) {
            case "customer login":
                context.landingPage = new LandingPage(context.getDriver());
                context.landingPage.goTo();
                break;
            case "account overview":
            case "accounts overview":
                context.setCookie();

                context.accountsOverviewPage = new AccountsOverviewPage(context.getDriver());
                context.accountsOverviewPage.goTo();
                break;
            case "sign up":
                context.signUpPage = new SignUpPage(context.getDriver());
                context.signUpPage.goTo();
                break;
            case "open new account":
                context.setCookie();

                context.openNewAccountPage = new OpenNewAccountPage(context.getDriver());
                context.openNewAccountPage.goTo();
                break;
            default:
                throw new RuntimeException("Incorrect page: " + page);
        }
    }
}
