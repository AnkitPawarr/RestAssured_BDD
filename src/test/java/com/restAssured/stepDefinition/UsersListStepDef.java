package com.restAssured.stepDefinition;

import com.restAssured.restResouces.RestResources;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;

import java.util.HashMap;
import java.util.Map;

import static com.restAssured.hooks.Base.baseURI;
import static com.restAssured.hooks.Base.log;
import static com.restAssured.utilities.Constants.usersListPageEP;

public class UsersListStepDef {

    RestResources restResources = new RestResources();

    RequestSpecification request;
    Response response;

    @Given("I have endpoint and query parameter for Page {int}")
    public void i_have_endpoint_and_query_parameter_for_page(int pageNumber) {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("page", pageNumber);

        request = restResources.setUpRequest(baseURI, null, queryParams, null, ContentType.JSON);
    }

    @When("I hit GET http method")
    public void i_hit_get_http_method() {
        response = restResources.get(request, usersListPageEP);
    }

    @Then("I should get a response with Status code {int}")
    public void i_should_get_a_response_with_status_code(int statusCode) {
        restResources.doVerifyStatusCode(response, statusCode);
        log.info("Status code is as expected.");
    }

    @And("All the Users from Page {int} should be listed")
    public void all_the_users_from_page_should_be_listed(int pageNumber) {
        int page = restResources.doExtractValueInResponse(response, "page", Integer.class);
        Assert.assertEquals(page, pageNumber);
        log.info("User is on the same page as expected.");
    }
}