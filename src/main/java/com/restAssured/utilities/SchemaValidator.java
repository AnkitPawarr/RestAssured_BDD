package com.restAssured.utilities;

import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;

import static com.restAssured.hooks.Base.log;

public class SchemaValidator {

    public void validateSchema(Response response, String schemaFilePath) {
        try {
            response.then().assertThat()
                    .body(JsonSchemaValidator.matchesJsonSchemaInClasspath(schemaFilePath));
        } catch (RuntimeException e) {
            log.error("JSON Schema validation FAILED.", e.getCause());
            throw new RuntimeException("JSON Schema validation FAILED.", e);
        }
    }
}