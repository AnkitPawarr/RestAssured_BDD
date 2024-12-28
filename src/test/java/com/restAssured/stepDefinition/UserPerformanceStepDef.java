package com.restAssured.stepDefinition;

import com.restAssured.restResouces.RestResources;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;
import java.util.Map;

import static com.restAssured.hooks.Base.baseURI;
import static com.restAssured.utilities.Constants.delayedResponseEP;

public class UserPerformanceStepDef {

    RestResources restResources = new RestResources();
    RequestSpecification request;
    Response response;

    @Given("I have endpoint and query parameter {int}")
    public void i_have_endpoint_and_query_parameter(int delaySeconds) {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("delay", delaySeconds);

        request = restResources.setUpRequest(baseURI, null, queryParams, null, ContentType.JSON);
    }

    @When("I use GET http method")
    public void i_use_get_http_method() {
        response = restResources.get(request, delayedResponseEP);
    }

    @Then("I should get a response within {long}")
    public void i_should_get_a_response_within(long milliseconds) {
        restResources.doVerifyResponseTime(response, milliseconds);
    }
}