package com.restAssured.restResouces;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.File;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static com.restAssured.hooks.Base.log;
import static org.hamcrest.number.OrderingComparison.lessThanOrEqualTo;

public class RestResources {

    public RequestSpecification setUpRequest(String baseUri, Map<String, Object> headers, Map<String, Object> queryParams,
                                             Map<String, Object> pathParams, ContentType contentType) {
        RequestSpecification request = RestAssured.given().log().all()
                .baseUri(baseUri)
                .contentType(contentType)
                .accept(contentType);
        if (headers != null) {
            request.headers(headers);
        }
        if (queryParams != null) {
            request.queryParams(queryParams);
        }
        if (pathParams != null) {
            request.pathParams(pathParams);
        }

        return request;
    }

    public Response get(RequestSpecification request, String endpoint) {
        return request.log().all()
                .when().get(endpoint);
    }

    public Response post(RequestSpecification request, String endpoint, String payloadFilePath) {
        File payloadFile = new File(payloadFilePath);
        return request.body(payloadFile).log().all()
                .when().post(endpoint);
    }

    public Response put(RequestSpecification request, String endpoint, String payloadFilePath) {
        File payloadFile = new File(payloadFilePath);
        return request.body(payloadFile).log().all()
                .when().put(endpoint);
    }

    public Response patch(RequestSpecification request, String endpoint, String payloadFilePath) {
        File payloadFile = new File(payloadFilePath);
        return request.body(payloadFile).log().all()
                .when().patch(endpoint);
    }

    public Response delete(RequestSpecification request, String endpoint, String payloadFilePath) {
        File payloadFile = new File(payloadFilePath);
        return request.body(payloadFile).log().all()
                .when().delete(endpoint);
    }

    public void doVerifyStatusCode(Response response, int statusCode) {
        response.then().log().all()
                .assertThat().statusCode(statusCode);
    }

    public void doVerifySchema(Response response, String schemaFilePath) {
        try {
            response.then().log().all()
                    .assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(schemaFilePath));
        } catch (RuntimeException e) {
            log.error("JSON Schema validation FAILED.", e.getCause());
            throw new RuntimeException("JSON Schema validation FAILED.", e);
        }
    }

    public <T> T doExtractValueInResponse(Response response, String path, Class<T> type) {
        return response.jsonPath().getObject(path, type);
        /*
        Example -> String email = extractValue(response, "data.user.contact.email", String.class);
        <T> in method declaration is a generic type parameter,
        which makes the method flexible and reusable for extracting values of any data type from the response.
        <T> is a placeholder for a type that will be specified when the method is called.

        In Return type, <T> specifies that the return type of the method will match the type passed as the Class<T> argument.
        For example, if you pass String.class as the Class<T> argument, the method will return a String.
        */
    }

    public Response doExtractResponse(Response response) {
        return response.then().log().all()
                .extract().response();
    }

    public String doExtractResponseToString(Response response) {
        return response.then().log().all()
                .extract().response().asPrettyString();
    }

    public void doVerifyResponseTime(Response response) {
        response.then().log().all()
                .time(lessThanOrEqualTo(200L), TimeUnit.MICROSECONDS);
        /*We can use lessThan() method as well
         The time should be in Long and not an Integer */

    }
}