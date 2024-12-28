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

import static com.restAssured.hooks.Base.baseURI;
import static com.restAssured.hooks.Base.log;
import static com.restAssured.utilities.Constants.createUserEP;
import static com.restAssured.utilities.Constants.createUserPayloadPath;

public class createUserStepDef {

    RestResources restResources = new RestResources();
    RequestSpecification request;
    Response response;

    @Given("I have endpoint")
    public void i_have_endpoint() {
        request = restResources.setUpRequest(baseURI, null, null, null, ContentType.JSON);
    }

    @When("I hit POST http method")
    public void i_hit_post_http_method() {
        response = restResources.post(request, createUserEP, createUserPayloadPath);
    }

    @Then("The Status code should be {int}")
    public void the_status_code_should_be(int code) {
        restResources.doVerifyStatusCode(response, code);
        log.info("Status code is as expected.");
    }

    @And("I should get {string} and {string} details in response")
    public void i_should_get_and_details_in_response(String name, String Job) {
        String username = restResources.doExtractValueInResponse(response, "name", String.class);
        log.info("NAME IS - " + username);
        String jobDetails = restResources.doExtractValueInResponse(response, "job", String.class);
        log.info("JOB IS - " + jobDetails);

        Assert.assertEquals(username, name);
        log.info("User Name is as expected.");
        Assert.assertEquals(jobDetails, Job);
        log.info("Job is as expected.");
    }

}
