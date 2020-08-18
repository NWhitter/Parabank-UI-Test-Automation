package org.parabank.test.automation.api.json;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.parabank.test.automation.api.StepData;

import java.util.Map;

public class AccountsStepDef {
    public StepData stepData;

    public AccountsStepDef(StepData stepData) {
        this.stepData = stepData;
    }

    @When("^an GET request is sent with path parameters to the accounts endpoint$")
    public void anGetRequestIsSentWithPathParametersToTheAccountsEndpoint(DataTable table) {
        Map<String, String> rows = table.asMap(String.class, String.class);

        stepData.response = stepData.request
                .pathParams(rows)
                .when()
                .log().all()
                .get("/{customerId}/accounts");
    }

    @And("^the base path is set to the customers endpoint$")
    public void theBasePathIsSetToTheCustomersEndpoint() {
        stepData.request.basePath("customers");
    }
}
