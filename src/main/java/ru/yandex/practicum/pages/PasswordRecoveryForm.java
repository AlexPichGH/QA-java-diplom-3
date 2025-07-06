package ru.yandex.practicum.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.practicum.utils.Config;

import java.time.Duration;

public class PasswordRecoveryForm extends BasePage {

    // заголовок "Восстановление пароля"
    private final By prPasswordRecoveryTitle = By.xpath(".//h2[text()='Восстановление пароля']");
    // кнопка "Войти"
    private final By prLoginButton = By.cssSelector("a[href='/login']");

    public PasswordRecoveryForm(WebDriver driver) {
        super(driver);
    }

    @Step("Ожидание загрузки формы восстановления пароля")
    public PasswordRecoveryForm waitPasswordRecoveryFormLoad() {
        new WebDriverWait(driver, Duration.ofSeconds(Config.TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(prPasswordRecoveryTitle));
        return this;
    }

    @Step("Нажать на кнопку \"Войти\"")
    public void clickOnLoginButton() {
        driver.findElement(prLoginButton).click();
    }
}
