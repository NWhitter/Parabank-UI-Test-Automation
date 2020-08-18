package org.parabank.test.automation.api.json;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import org.parabank.test.automation.api.StepData;

import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class MiscStepDef {
    public StepData stepData;

    public MiscStepDef(StepData stepData) {
        this.stepData = stepData;
    }

    @Given("^the base url is set to parabank's url$")
    public void theBaseUrlIsSetToParabanksUrl() {
        stepData.request = RestAssured.given().baseUri("http://parabank.parasoft.com/parabank/services/bank/");
    }

    @When("^an GET request is sent with path parameters to the login endpoint$")
    public void anGetRequestIsSentWithPathParametersToTheLoginEndpoint(DataTable table) {
        Map<String, String> rows = table.asMap(String.class, String.class);

        stepData.response = stepData.request
                .basePath("login")
                .pathParams(rows)
                .when()
                .log().all()
                .get("/{username}/{password}");
    }

    @And("^the response's body contains message \"([^\"]*)\"$")
    public void theResponsesBodyContainsMessage(String expectedMessage) {
        assertThat(stepData.json.extract().body().asString(), equalTo(expectedMessage));
    }
}
