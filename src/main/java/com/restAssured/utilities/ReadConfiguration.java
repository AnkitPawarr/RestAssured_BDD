package com.restAssured.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import static com.restAssured.utilities.Constants.*;

public class ReadConfiguration {

    private Properties prop;
    private FileInputStream fis;

    public Properties readConfig(String env) {
        prop = new Properties();

        try {
            if (env.equalsIgnoreCase("dev")) {
                fis = new FileInputStream(DEV_CONFIG_PATH);
            } else if (env.equalsIgnoreCase("sit")) {
                fis = new FileInputStream(SIT_CONFIG_PATH);
            } else if (env.equalsIgnoreCase("prod")) {
                fis = new FileInputStream(PROD_CONFIG_PATH);
            } else {
                throw new IllegalArgumentException("ENV is passed Incorrectly. Please set a valid ENV.");
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        try {
            prop.load(fis);
        } catch (IOException e) {
            e.getCause();
            throw new RuntimeException(e);
        }
        return prop;
    }
}