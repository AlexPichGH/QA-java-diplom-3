package ru.yandex.practicum.pages;

import org.openqa.selenium.WebDriver;
import ru.yandex.practicum.utils.Config;

public class BasePage {

    protected final WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    // запустить веб-приложение
    public void startWebApp() {
        driver.get(Config.SERVICE_URL);
    }

    // закрыть драйвер и браузер
    public void closeDriverAndQuitBrowser() {
        driver.quit();
    }
}
