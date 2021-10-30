package ru.nshpakov.services.user;

import io.restassured.response.Response;
import ru.nshpakov.utils.EnvReader;

public class GetUserApiSingleton extends BaseSpecApi{
    private final String userGetPath = EnvReader.getEnvProperties().get("CREATE_USER_PATH");
    private static GetUserApiSingleton getUserApiSingletonInstance;
    public String userNameValue;

    private GetUserApiSingleton(String userNameValue) {
        super.createSpecReqApiWithParams(userGetPath + "/" + userNameValue);
        this.userNameValue = userNameValue;
    }

    public static GetUserApiSingleton getInstance(String userNameValue) {
        if (getUserApiSingletonInstance == null) {
            getUserApiSingletonInstance = new GetUserApiSingleton(userNameValue);
        }
        return getUserApiSingletonInstance;
    }
    public Response getResponseBody() {
        return super.getResponseBodyForGet();
    }

}
