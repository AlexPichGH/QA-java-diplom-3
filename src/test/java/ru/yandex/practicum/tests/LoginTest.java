package ru.yandex.practicum.tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.practicum.ErrorUtils;
import ru.yandex.practicum.api.models.User;
import ru.yandex.practicum.api.steps.UserSteps;
import ru.yandex.practicum.pages.*;

public class LoginTest extends BaseTest {

    private MainPage mainPage;
    private LoginPage loginPage;
    private RegistrationForm registrationForm;
    private PasswordRecoveryForm passwordRecoveryForm;

    private UserSteps userSteps;
    private User user;
    private String accessToken;

    @Before
    public void init() {
        mainPage = new MainPage(getDriver());
        loginPage = new LoginPage(getDriver());
        registrationForm = new RegistrationForm(getDriver());
        passwordRecoveryForm = new PasswordRecoveryForm(getDriver());

        createUser();

        new BasePage(getDriver()).startWebApp();
    }

    private void createUser() {
        userSteps = new UserSteps();
        user = new User()
                .withEmail(faker.internet().emailAddress())
                .withPassword(faker.internet().password())
                .withName(faker.name().name());
        accessToken = userSteps.createUser(user);
    }

    @Test
    @DisplayName("Вход по кнопке \"Войти в аккаунт\" на главной странице")
    @Description("Проверка, что можно войти в аккаунт по кнопке \"Войти в аккаунт\" на главной странице")
    public void checkLoginByUsingLoginButtonOnMainPageTest() {
        mainPage.waitMainPageLoad()
                .clickOnLoginButton();
        loginPage.waitLoginPageLoad()
                .login(user.getEmail(), user.getPassword());
        Assert.assertTrue(ErrorUtils.MAIN_PAGE_OPEN_ERROR, mainPage.isPlaceOrderButtonDisplayed());
    }

    @Test
    @DisplayName("Вход через кнопку \"Личный кабинет\"")
    @Description("Проверка, что можно войти в аккаунт по кнопке \"Личный кабинет\" на главной странице")
    public void checkLoginByUsingPersonalAccountButtonOnMainPageTest() {
        mainPage.waitMainPageLoad()
                .clickOnPersonalAccountButton();
        loginPage.waitLoginPageLoad()
                .login(user.getEmail(), user.getPassword());
        Assert.assertTrue(ErrorUtils.MAIN_PAGE_OPEN_ERROR, mainPage.isPlaceOrderButtonDisplayed());
    }

    @Test
    @DisplayName("Вход через кнопку в форме регистрации")
    @Description("Проверка, что можно войти в аккаунт по кнопке входа в форме регистрации")
    public void checkLoginViaRegistrationFormTest() {
        mainPage.waitMainPageLoad()
                .clickOnLoginButton();
        loginPage.waitLoginPageLoad()
                .clickOnRegistrationButton();
        registrationForm.waitRegistrationFormLoad()
                .clickOnLoginButton();
        loginPage.waitLoginPageLoad()
                .login(user.getEmail(), user.getPassword());
        Assert.assertTrue(ErrorUtils.MAIN_PAGE_OPEN_ERROR, mainPage.isPlaceOrderButtonDisplayed());
    }

    @Test
    @DisplayName("Вход через кнопку в форме восстановления пароля")
    @Description("Проверка, что можно войти в аккаунт по кнопке входа в форме восстановления пароля")
    public void checkLoginViaPasswordRecoveryFormTest() {
        mainPage.waitMainPageLoad()
                .clickOnLoginButton();
        loginPage.waitLoginPageLoad()
                .clickOnForgotPasswordButton();
        passwordRecoveryForm.waitPasswordRecoveryFormLoad()
                .clickOnLoginButton();
        loginPage.waitLoginPageLoad()
                .login(user.getEmail(), user.getPassword());
        Assert.assertTrue(ErrorUtils.MAIN_PAGE_OPEN_ERROR, mainPage.isPlaceOrderButtonDisplayed());
    }

    @After
    public void tearDown() {
        if (accessToken != null) {
            userSteps.deleteUser(accessToken);
        }
    }
}
