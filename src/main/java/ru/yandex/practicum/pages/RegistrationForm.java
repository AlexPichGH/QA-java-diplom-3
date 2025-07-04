package ru.yandex.practicum.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.practicum.utils.Config;

import java.time.Duration;

public class RegistrationForm extends BasePage {

    // заголовок "Регистрация"
    private final By rRegistrationTitle = By.xpath(".//h2[text()='Регистрация']");
    // поле ввода "Имя"
    private final By rNameField = By.xpath(".//label[text()='Имя']/../input");
    // поле ввода "Email"
    private final By rEmailField = By.xpath(".//label[text()='Email']/../input");
    // поле ввода "Пароль"
    private final By rPasswordField = By.xpath(".//input[@name='Пароль']");
    // кнопка "Зарегистрироваться"
    private final By rRegistrationButton = By.xpath(".//button[text()='Зарегистрироваться']");
    // кнопка "Войти"
    private final By rLoginButton = By.cssSelector("a[href='/login']");
    // сообщение об ошибке "Некорректный пароль"
    private final By rPasswordErrorMessage = By.xpath(".//p[text()='Некорректный пароль']");

    public RegistrationForm(WebDriver driver) {
        super(driver);
    }

    @Step("Ожидание загрузки формы регистрации")
    public RegistrationForm waitRegistrationFormLoad() {
        new WebDriverWait(driver, Duration.ofSeconds(Config.TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(rRegistrationTitle));
        return this;
    }

    @Step("Ввести имя в поле ввода")
    public void enterName(String name) {
        driver.findElement(rNameField).sendKeys(name);
    }

    @Step("Ввести email в поле ввода")
    public void enterEmail(String email) {
        driver.findElement(rEmailField).sendKeys(email);
    }

    @Step("Ввести пароль в поле ввода")
    public void enterPassword(String password) {
        driver.findElement(rPasswordField).sendKeys(password);
    }

    @Step("Нажать на кнопку \"Зарегистрироваться\"")
    public void clickOnRegistrationButton() {
        driver.findElement(rRegistrationButton).click();
    }

    @Step("Нажать на кнопку \"Войти\"")
    public void clickOnLoginButton() {
        driver.findElement(rLoginButton).click();
    }

    @Step("Зарегистрироваться")
    public void register(String name, String email, String password) {
        enterName(name);
        enterEmail(email);
        enterPassword(password);
        clickOnRegistrationButton();
    }

    @Step("Отображение ошибки \"Некорректный пароль\"")
    public boolean isPasswordErrorMessageDisplayed() {
        new WebDriverWait(driver, Duration.ofSeconds(Config.TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(rPasswordErrorMessage));
        return driver.findElement(rPasswordErrorMessage).isDisplayed();
    }
}
