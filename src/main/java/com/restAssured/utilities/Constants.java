package com.restAssured.utilities;

public class Constants {

    //CONFIG PATHS
    public final static String SIT_CONFIG_PATH = "./src/main/resources/config/sit.properties";
    public final static String DEV_CONFIG_PATH = "./src/main/resources/config/dev.properties";
    public final static String PROD_CONFIG_PATH = "./src/main/resources/config/prod.properties";

    //END POINTS
    public final static String usersListPageEP = "/api/users";
    public final static String usersEP = "/api/users/";
    public final static String createUserEP = "/api/users";
    public final static String updateUserEP = "/api/users/";
    public final static String registerUserEP = "/api/register";
    public final static String loginUserEP = "/api/login";
    public final static String delayedResponseEP = "/api/users";

    //JSON PAYLOAD PATHS
    public final static String createUserPayloadPath = "src/test/resources/jsonPayload/CreateUser.json";
    public final static String registerUserPayloadPath = "src/test/resources/jsonPayload/RegisterUser.json";
    public final static String loginUserPayloadPath = "src/test/resources/jsonPayload/LoginUser.json";
    public final static String updateUserPayloadPath = "src/test/resources/jsonPayload/UpdateUser.json";

}