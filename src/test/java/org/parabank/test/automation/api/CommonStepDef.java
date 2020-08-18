package org.parabank.test.automation.api;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.http.ContentType;
import org.junit.Assert;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class CommonStepDef {
    public StepData stepData;

    public CommonStepDef(StepData stepData) {
        this.stepData = stepData;
    }

    @Given("^the Accept header is set as \"([^\"]*)\"$")
    public void theAcceptHeaderIsSetAs(String contentType) {
        if (contentType.equals("application/json")) {
            stepData.request.accept(ContentType.JSON);
        } else if (contentType.equals("application/xml")) {
            stepData.request.accept(ContentType.XML);
        }
    }

    @Then("^the response's status is (\\d+)$")
    public void theResponsesStatusIs(Integer status) {
        stepData.json = stepData.response.then().statusCode(status);
    }

    @And("^the response's content type is \"([^\"]*)\"$")
    public void theResponsesContentTypeIs(String contentType) {
        String actualContentType = stepData.response.contentType();

        if (contentType.equals("application/json")) {
            assertThat(actualContentType).isEqualTo(ContentType.JSON.toString());
        }

        if (contentType.equals("text/plain")) {
            String expectedContentType = ContentType.TEXT.withCharset("ISO-8859-1")
                    .replace(" ", "");
            assertThat(actualContentType).isEqualTo(expectedContentType);
        }
    }

    @And("^the response's body matches the expected body$")
    public void theResponsesBodyMatchesTheExpectedBody(DataTable table) {
        Map<String, String> fields = table.asMap(String.class, String.class);

        for (Map.Entry<String, String> field : fields.entrySet()) {
            String expectedResponse = field.getValue();
            String actualResponse = stepData.json.extract().body().jsonPath().getString(field.getKey());

            Assert.assertEquals(actualResponse, expectedResponse);
        }
    }

    @And("^the response's body includes the following in any order$")
    public void theResponsesBodyIncludesTheFollowingInAnyOrder(DataTable table) {
        Map<String, String> fields = table.asMap(String.class, String.class);

        for (Map.Entry<String, String> field : fields.entrySet()) {
            Integer expectedResponse = Integer.valueOf(field.getValue());
            List<Integer> actualResponse = stepData.json.extract().body().jsonPath().getList(field.getKey());

            //  Assert.assertTrue(actualResponse.contains(expectedResponse));

            assertThat(actualResponse).contains(expectedResponse);
        }
    }

}