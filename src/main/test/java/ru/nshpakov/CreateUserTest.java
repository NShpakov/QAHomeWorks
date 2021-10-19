package ru.nshpakov;

import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.nshpakov.dto.request.User;
import ru.nshpakov.dto.response.create_user_resp.UserRespBody;
import ru.nshpakov.services.user.CreateUserApi;

import static org.hamcrest.Matchers.*;
/*
* Данный класс демонстрирует различные способы валидации
* Сценарии:
* - Запрос на создание пользователя со всеми параметрами. Валидация hamcrest
* - Запрос на создание пользователя со всеми параметрами. Валидация схемы json
* - Запрос на создание пользователя со всеми параметрами. Валидация ответа с помощью Assertions
* - Запрос на создание пользователя с пустым телом (параметры = null). Валидация hamcrest
*
* */
public class CreateUserTest {
    private CreateUserApi createUserApi = new CreateUserApi();

    @Test()
    public void createUserReqWithAllParamsCheckRespByMatcher() {
        User user = User.builder()
                .id(3L)
                .email("emailValue")
                .firstName("firstNameValue")
                .lastName("lastNameValue")
                .password("passValue")
                .phone("phoneValue")
                .username("userNameValue")
                .userStatus(5L)
                .build();

        createUserApi.getUser(user)
                .then()
                .log()
                .all()
                .statusCode(HttpStatus.SC_OK)
                .body("code", equalTo(200))
                .body("type", equalTo("unknown"))
                .body("message", equalTo("3"));
    }

    @Test
    public void createUserReqWithAllParamsCheckRespBySchemaValidation() {
        User user = User.builder()
                .id(3L)
                .email("emailValue")
                .firstName("firstNameValue")
                .lastName("lastNameValue")
                .password("passValue")
                .phone("phoneValue")
                .username("userNameValue")
                .userStatus(5L)
                .build();

        createUserApi.getUser(user)
                .then()
                .log()
                .all()
                .statusCode(HttpStatus.SC_OK)
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schema/CreateUserResponse.json"));
    }

    @Test()
    public void createUserReqWithAllParamsCheckRespByAsserts() {
        User user = User.builder()
                .id(3L)
                .email("emailValue")
                .firstName("firstNameValue")
                .lastName("lastNameValue")
                .password("passValue")
                .phone("phoneValue")
                .username("userNameValue")
                .userStatus(5L)
                .build();

        Response response = createUserApi.getUser(user);
        Assertions.assertEquals(getExpectedRespBody().getCode(), getActualRespBody(response).getCode(), "code");
        Assertions.assertEquals(getExpectedRespBody().getType(), getActualRespBody(response).getType(), "type");
        Assertions.assertEquals(getExpectedRespBody().getMessage(), getActualRespBody(response).getMessage(), "message");

    }

    @Test
    public void createUserReqWithOutParamsCheckRespByMatcher() {
        User user = User.builder()
                .build();

        createUserApi
                .getUser(user)
                .then()
                .log()
                .all()
                .statusCode(HttpStatus.SC_OK)
                .body("code", equalTo(200))
                .body("type", equalTo("unknown"))
                .body("message", equalTo("0"));
    }

    private UserRespBody getExpectedRespBody() {
        return new UserRespBody(200, "unknown", "3");
    }

    private UserRespBody getActualRespBody(Response response) {
        return response.then().extract().body().as(UserRespBody.class);
    }
}