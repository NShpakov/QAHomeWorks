package ru.nshpakov.services.user;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public abstract class BaseSpecApi<T> {
    protected static final String BASE_URL = "https://petstore.swagger.io/v2";
    protected RequestSpecification requestSpecification;

    public void createSpecReqApi(String path) {
        requestSpecification = given()
                .contentType(ContentType.JSON)
                .log()
                .all()
                .baseUri(BASE_URL)
                .basePath(path);
    }

    public void createSpecReqApiWithParams(String path) {
        requestSpecification = given()
                .contentType(ContentType.JSON)
                .log()
                .all()
                .baseUri(BASE_URL)
                .basePath(path);

    }

    public Response getResponseBodyForPost(T t) {
        return given(requestSpecification)
                .body(t)
                .when()
                .post();
    }

    public Response getResponseBodyForGet() {
        return given(requestSpecification)
                .when()
                .get();
    }
}
