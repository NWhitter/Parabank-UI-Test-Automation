package org.parabank.test.automation.api;

import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import jdk.jfr.ContentType;

public class StepData {
    public Response response;
    public ValidatableResponse json;
    public RequestSpecification request;
    public ContentType contentType;
}
