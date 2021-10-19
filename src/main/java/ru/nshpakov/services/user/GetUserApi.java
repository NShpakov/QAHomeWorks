package ru.nshpakov.services.user;

import io.restassured.response.Response;
import ru.nshpakov.dto.request.User;


public class GetUserApi extends BaseSpecApi {
    private String userPath = "/user";

    public GetUserApi(String userNameValue) {
        super.createSpecReqApiWithParams(userPath + "/" + userNameValue);
    }

    public Response getResponseBody() {
        return super.getResponseBodyForGet();
    }

}