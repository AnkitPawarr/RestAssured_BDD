package com.restAssured.testRunners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(
        glue = {"com.restAssured.stepDefinition", "com.restAssured.hooks"},
        features = {"src/test/resources/featureFiles"},
        monochrome = true,
        /*
        AND condition: Use tags = "@tag1 and @tag2" to run scenarios with both tags.
        OR condition: Use tags = "@tag1 or @tag2" to run scenarios with either tag.
        NOT condition: Use tags = "not @tag1" to exclude scenarios with a specific tag
        Complex condition: tags = "(@smoke or @regression) and not @wip"
        */
        plugin = {
                "pretty",
                "html:target/cucumber-reports/cucumber.html",
                "json:target/cucumber-reports-json/cucumber.json",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
        }
)

public class MyTestNGRunnerTest extends AbstractTestNGCucumberTests {
}