package ru.nshpakov.services.user;

import io.restassured.response.Response;
import ru.nshpakov.dto.request.User;
import ru.nshpakov.utils.EnvReader;

public class CreateUserApi extends BaseSpecApi {
    private String userCreatePath = EnvReader.getEnvProperties().get("GET_USER_PATH");

    public CreateUserApi() {
        super.createSpecReqApi(userCreatePath);
    }

    public Response getUser(User user) {
        return super.getResponseBodyForPost(user);
    }

}
