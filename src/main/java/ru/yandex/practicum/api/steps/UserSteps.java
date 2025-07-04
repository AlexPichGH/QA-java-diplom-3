package ru.yandex.practicum.api.steps;


import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import ru.yandex.practicum.api.ApiConfig;
import ru.yandex.practicum.api.models.User;

import static io.restassured.RestAssured.given;

public class UserSteps {

    @Step("Создание пользователя")
    public String createUser(User user) {
        return given()
                .body(user)
                .when()
                .post(ApiConfig.USER_CREATE_ENDPOINT)
                .then()
                .extract()
                .body()
                .path("accessToken");
    }

    @Step("Авторизация пользователя")
    public ValidatableResponse loginUser(User user) {
        return given()
                .body(user)
                .when()
                .post(ApiConfig.USER_LOGIN_ENDPOINT)
                .then();
    }

    @Step("Удаление пользователя")
    public void deleteUser(String accessToken) {
        given()
                .header("Authorization", accessToken)
                .when()
                .delete(ApiConfig.USER_DELETE_ENDPOINT)
                .then();
    }
}
