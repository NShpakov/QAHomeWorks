package ru.nshpakov.services.user;
import io.restassured.response.Response;
import ru.nshpakov.utils.EnvReader;


public class GetUserApi extends BaseSpecApi {
    private final String userGetPath = EnvReader.getEnvProperties().get("CREATE_USER_PATH");

    public GetUserApi(String userNameValue) {
        super.createSpecReqApiWithParams(userGetPath + "/" + userNameValue);
    }

    public Response getResponseBody() {
        return super.getResponseBodyForGet();
    }

}