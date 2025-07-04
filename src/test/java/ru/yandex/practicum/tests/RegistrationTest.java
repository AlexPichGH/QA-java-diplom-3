package ru.yandex.practicum.tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.practicum.ErrorUtils;
import ru.yandex.practicum.api.models.User;
import ru.yandex.practicum.api.steps.UserSteps;
import ru.yandex.practicum.pages.BasePage;
import ru.yandex.practicum.pages.LoginPage;
import ru.yandex.practicum.pages.MainPage;
import ru.yandex.practicum.pages.RegistrationForm;

import static org.hamcrest.Matchers.is;

public class RegistrationTest extends BaseTest {

    private MainPage mainPage;
    private LoginPage loginPage;
    private RegistrationForm registrationForm;

    private UserSteps userSteps;
    private User user;
    private String accessToken;

    @Before
    public void init() {
        mainPage = new MainPage(getDriver());
        loginPage = new LoginPage(getDriver());
        registrationForm = new RegistrationForm(getDriver());

        initUser();

        new BasePage(getDriver()).startWebApp();
    }

    private void initUser() {
        userSteps = new UserSteps();
        user = new User()
                .withEmail(faker.internet().emailAddress())
                .withPassword(faker.internet().password())
                .withName(faker.name().name());
    }

    @Test
    @DisplayName("Успешная регистрацию")
    @Description("Проверка, что можно успешно зарегистрироваться через форму регистрации")
    public void checkSuccessfulRegistrationTest() {
        mainPage.waitMainPageLoad()
                .clickOnPersonalAccountButton();
        loginPage.waitLoginPageLoad()
                .clickOnRegistrationButton();
        registrationForm.waitRegistrationFormLoad()
                .register(user.getName(), user.getEmail(), user.getPassword());
        loginPage.waitLoginPageLoad();
        ValidatableResponse loginResponse = userSteps.loginUser(user);
        accessToken = loginResponse.extract()
                .body()
                .path("accessToken");
        loginResponse.statusCode(HttpStatus.SC_OK)
                .body("success", is(true));
    }

    @Test
    @DisplayName("Ошибка регистрации с коротким паролем")
    @Description("Проверка, что возникает ошибка о некорректном пароле при регистрации с паролем длиной меньше 6 символов")
    public void checkRegistrationErrorWithShortPasswordTest() {
        user = user.withPassword(faker.internet().password(1, 5));
        mainPage.waitMainPageLoad()
                .clickOnPersonalAccountButton();
        loginPage.waitLoginPageLoad()
                .clickOnRegistrationButton();
        registrationForm.waitRegistrationFormLoad()
                .register(user.getName(), user.getEmail(), user.getPassword());
        Assert.assertTrue(
                ErrorUtils.REGISTRATION_WRONG_PASSWORD_MESSAGE_ERROR,
                registrationForm.isPasswordErrorMessageDisplayed()
        );
    }

    @After
    public void tearDown() {
        if (accessToken != null) {
            userSteps.deleteUser(accessToken);
        }
    }
}
