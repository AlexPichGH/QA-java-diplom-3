package ru.yandex.practicum.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import ru.yandex.practicum.utils.Config;

public class BasePage {

    protected final WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Запуск веб-приложение")
    public void startWebApp() {
        driver.get(Config.SERVICE_URL);
    }

    @Step("Закрыть драйвер и выключить браузер")
    public void closeDriverAndQuitBrowser() {
        driver.quit();
    }
}
