package org.parabank.test.automation.ui.webdriver;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.parabank.test.automation.ui.pages.AccountsOverviewPage;
import org.parabank.test.automation.ui.pages.LandingPage;
import org.parabank.test.automation.ui.pages.OpenNewAccountPage;
import org.parabank.test.automation.ui.pages.SignUpPage;
import uk.co.mwtestconsultancy.CookieAdapter;

import static org.parabank.test.automation.ui.enums.WebBrowsers.CHROME;


public class ContextSteps {
    public String username;
    public String firstName;
    public String lastName;

    private WebDriver driver;

    public LandingPage landingPage;
    public AccountsOverviewPage accountsOverviewPage;
    public SignUpPage signUpPage;
    public OpenNewAccountPage openNewAccountPage;

    @Before
    public void setup() {
        driver = WebDriverFactory.initDriver(CHROME);
    }

    public void setCookie() {
        LandingPage landingPage = new LandingPage(driver);
        landingPage.goTo();

        Response response = RestAssured.given()
                .header("Content-Type", "application/x-www-form-urlencoded")
                .formParam("username", "john")
                .formParam("password", "demo")
                .request()
                .post("https://parabank.parasoft.com/parabank/login.htm");

        for (io.restassured.http.Cookie cookie : response.getDetailedCookies()) {
            Cookie seleniumCookie = new CookieAdapter().convertToSelenium(cookie);
            driver.manage().addCookie(seleniumCookie);
        }
    }

    public WebDriver getDriver() {
        return driver;
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.close();
        }
    }
}
