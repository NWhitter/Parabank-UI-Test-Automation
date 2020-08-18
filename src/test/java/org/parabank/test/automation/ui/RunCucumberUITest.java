package org.parabank.test.automation.ui;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"pretty"})
public class RunCucumberUITest {

    @BeforeClass
    public static void cleanDB() {
        System.out.println("Cleaning database...");
        Response response = RestAssured.given()
                .header("Content-Type", "application/x-www-form-urlencoded")
                .formParam("action", "clean")
                .request()
                .post("https://parabank.parasoft.com/parabank/db.htm");

        if (response.getStatusCode() != 200) {
            throw new RuntimeException("Failed to clean database");
        }
    }
}
