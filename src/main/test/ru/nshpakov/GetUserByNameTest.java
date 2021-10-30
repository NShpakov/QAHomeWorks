package ru.nshpakov;

import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.nshpakov.dto.request.User;
import ru.nshpakov.dto.response.get_user_resp.GetUserNotFound;
import ru.nshpakov.dto.response.get_user_resp.GetUserResponseBody;
import ru.nshpakov.services.user.CreateUserApi;
import ru.nshpakov.services.user.GetUserApi;
import ru.nshpakov.services.user.GetUserApiSingleton;

import static org.hamcrest.Matchers.equalTo;

/*
 * Получение данных о пользователе по имени перед каждым тестом выполняется  @BeforeEach (создание пользователя)
 * Сценарии:
 * - Предусловие, - создаем пользователя
 * - Запрос пользователя по имени. Успешный ответ
 * - Запрос пользователя по несуществующему имени. Пользователь не найден
 *
 * */
public class GetUserByNameTest {
    private final CreateUserApi createUserApi = new CreateUserApi();
    private final String userName = "userNameValue";
    private final GetUserResponseBody expectedResponseBody = new GetUserResponseBody();
    private final GetUserNotFound getUserNotFound = new GetUserNotFound();
    private final Response actualResponse = GetUserApiSingleton.getInstance("userNameValue").getResponseBody();
    private final Response actualResponseEror = new GetUserApi("zzz").getResponseBody();
    private final User user = User.builder()
            .id(3L)
            .email("emailValue")
            .firstName("firstNameValue")
            .lastName("lastNameValue")
            .password("passValue")
            .phone("phoneValue")
            .username(userName)
            .userStatus(5L)
            .build();

    @BeforeEach
    public void beforeActionCreateUser() {
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
    public void getUserByName() {
        Assertions.assertEquals(actualResponse.statusCode(), 200, "http code");
        Assertions.assertEquals(getActualRespBody().getId(), getExpectedRespBody().getId(), "id");
        Assertions.assertEquals(getActualRespBody().getUsername(), getExpectedRespBody().getUsername(), "username");
        Assertions.assertEquals(getActualRespBody().getFirstName(), getExpectedRespBody().getFirstName(), "firstName");
        Assertions.assertEquals(getActualRespBody().getLastName(), getExpectedRespBody().getLastName(), "lastName");
        Assertions.assertEquals(getActualRespBody().getEmail(), getExpectedRespBody().getEmail(), "email");
        Assertions.assertEquals(getActualRespBody().getPassword(), getExpectedRespBody().getPassword(), "password");
        Assertions.assertEquals(getActualRespBody().getPhone(), getExpectedRespBody().getPhone(), "phone");
        Assertions.assertEquals(getActualRespBody().getUserStatus(), getExpectedRespBody().getUserStatus(), "userStatus");
    }

    @Test
    public void getUserNameNotFound() {
        Assertions.assertEquals(404, actualResponseEror.statusCode(), "http code");
        Assertions.assertEquals(userNotFoundExpecctedRespBody().getCode(), getActualRespBodyUserNotFound().getCode(), "code");
        Assertions.assertEquals(userNotFoundExpecctedRespBody().getType(), getActualRespBodyUserNotFound().getType(), "type");
        Assertions.assertEquals(userNotFoundExpecctedRespBody().getMessage(), getActualRespBodyUserNotFound().getMessage(), "message");
    }

    private GetUserNotFound getActualRespBodyUserNotFound() {
        return actualResponseEror.then().extract().body().as(GetUserNotFound.class);
    }

    private GetUserResponseBody getActualRespBody() {
        return actualResponse.then().extract().body().as(GetUserResponseBody.class);
    }

    private GetUserNotFound userNotFoundExpecctedRespBody() {
        getUserNotFound.setCode(1);
        getUserNotFound.setType("error");
        getUserNotFound.setMessage("User not found");
        return getUserNotFound;
    }

    private GetUserResponseBody getExpectedRespBody() {
        expectedResponseBody.setId(3L);
        expectedResponseBody.setUsername("userNameValue");
        expectedResponseBody.setFirstName("firstNameValue");
        expectedResponseBody.setLastName("lastNameValue");
        expectedResponseBody.setEmail("emailValue");
        expectedResponseBody.setPassword("passValue");
        expectedResponseBody.setPhone("phoneValue");
        expectedResponseBody.setUserStatus(5);
        return expectedResponseBody;
    }
}