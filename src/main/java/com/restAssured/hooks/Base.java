package com.restAssured.hooks;

import com.restAssured.utilities.ReadConfiguration;
import io.cucumber.java.Before;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Properties;

public class Base {

    public static Logger log = LogManager.getLogger(Base.class);

    public Properties prop;
    public static String baseURI;

    private String fetchParameter(String parameterName, String defaultValue) {
        String value = System.getProperty(parameterName);
        if (value != null) {
            return value;
        }
        return defaultValue;
    }

    @Before
    public void setUp() {
        log.info("Initiating Properties.");

        String env = fetchParameter("env", "sit");
        log.info("Running Test suite on ENV: " + env);

        ReadConfiguration readConfiguration = new ReadConfiguration();
        prop = readConfiguration.readConfig(env);

        baseURI = prop.getProperty("baseURI");
        log.info("Base URI is: " + baseURI);
    }
}