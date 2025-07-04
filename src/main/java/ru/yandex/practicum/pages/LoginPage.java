package ru.yandex.practicum.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.practicum.utils.Config;

import java.time.Duration;

public class LoginPage extends BasePage {

    // заголовок "Вход"
    private final By lLoginTitle = By.xpath(".//h2[text()='Вход']");
    // поле ввода "Email"
    private final By lEmailField = By.xpath(".//input[@name='name']");
    // поле ввода "Пароль"
    private final By lPasswordField = By.xpath(".//input[@name='Пароль']");
    // кнопка "Войти"
    private final By lLoginButton = By.xpath(".//button[text()='Войти']");
    // кнопка "Зарегистрироваться"
    private final By lRegistrationButton = By.cssSelector("a[href='/register']");
    // кнопка "Восстановить пароль"
    private final By lForgotPasswordButton = By.cssSelector("a[href='/forgot-password']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Step("Ожидание загрузки страницы логина")
    public LoginPage waitLoginPageLoad() {
        new WebDriverWait(driver, Duration.ofSeconds(Config.TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(lLoginTitle));
        return this;
    }

    @Step("Ввести email в поле ввода")
    public void enterEmail(String email) {
        driver.findElement(lEmailField).sendKeys(email);
    }

    @Step("Ввести пароль в поле ввода")
    public void enterPassword(String password) {
        driver.findElement(lPasswordField).sendKeys(password);
    }

    @Step("Нажать на кнопку \"Войти\"")
    public void clickOnLoginButton() {
        driver.findElement(lLoginButton).click();
    }

    @Step("Нажать на кнопку \"Зарегистрироваться\"")
    public void clickOnRegistrationButton() {
        driver.findElement(lRegistrationButton).click();
    }

    @Step("Нажать на кнопку \"Восстановить пароль\"")
    public void clickOnForgotPasswordButton() {
        driver.findElement(lForgotPasswordButton).click();
    }

    @Step("Авторизоваться")
    public void login(String email, String password) {
        enterEmail(email);
        enterPassword(password);
        clickOnLoginButton();
    }
}
