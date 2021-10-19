package ru.nshpakov.services.user;

import io.restassured.response.Response;
import ru.nshpakov.dto.request.User;

public class CreateUserApi extends BaseSpecApi {
    private String userPath = "/user";

    public CreateUserApi() {
        super.createSpecReqApi(userPath);
    }

    public Response getUser(User user) {
        return super.getResponseBodyForPost(user);
    }

}
