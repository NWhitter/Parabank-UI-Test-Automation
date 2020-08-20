package org.parabank.test.automation.ui;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.parabank.test.automation.ui.helper.PropertyLoader;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty"}
)
public class RunCucumberTest {

    @BeforeClass
    public static void cleanDatabase() {
        System.out.println("Cleaning database...");
        Response response = RestAssured.given()
                .header("Content-Type", "application/x-www-form-urlencoded")
                .formParam("action", "clean")
                .request()
                .post(PropertyLoader.getDatabasePageUrl());

        if (response.getStatusCode() != 200) {
            throw new RuntimeException("Failed to clean database");
        }
    }
}
