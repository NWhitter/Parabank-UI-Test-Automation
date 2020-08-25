package org.parabank.test.automation.ui.driver;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.parabank.test.automation.ui.pages.LandingPage;
import uk.co.mwtestconsultancy.CookieAdapter;

public class DriverManager {
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    private DriverManager() {
    }

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void setDriver(WebDriver driver) {
        DriverManager.driver.set(driver);
    }

    public static void quit() {
        DriverManager.driver.get().quit();
        driver.remove();
    }

    public static void setCookie() {
        WebDriver driver = DriverManager.getDriver();
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

    public static String getInfo() {
        Capabilities cap = ((RemoteWebDriver) DriverManager.getDriver()).getCapabilities();
        String browserName = cap.getBrowserName();
        String platform = cap.getPlatform().toString();
        String version = cap.getVersion();
        return String.format("browser: %s v: %s platform: %s", browserName, version, platform);
    }
}
