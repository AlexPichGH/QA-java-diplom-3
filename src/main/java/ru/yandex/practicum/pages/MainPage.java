package ru.yandex.practicum.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.practicum.utils.Config;

import java.time.Duration;

public class MainPage extends BasePage {

    // кнопка "Личный кабинет"
    private final By mPersonalAccountButton = By.cssSelector("a[href='/account']");
    // кнопка "Войти в аккаунт"
    private final By mLoginButton = By.xpath("//button[text()='Войти в аккаунт']");
    // вкладка "Булки"
    private final By mBunsTab = By.xpath(".//span[text()='Булки']/parent::div");
    // вкладка "Соусы"
    private final By mSaucesTab = By.xpath(".//span[text()='Соусы']/parent::div");
    // вкладка "Начинки"
    private final By mFillingsTab = By.xpath(".//span[text()='Начинки']/parent::div");
    // выбранная вкладка
    private final By mActiveTab = By.xpath("//div[contains(@class,'tab_tab_type_current')]/span");
    // логотип сайта
    private final By mLogo = By.className("AppHeader_header__logo__2D0X2");


    public MainPage(WebDriver driver) {
        super(driver);
    }

    @Step("Ожидание загрузки главной страницы")
    public MainPage waitMainPageLoad() {
        new WebDriverWait(driver, Duration.ofSeconds(Config.TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(mLogo));
        return this;
    }

    @Step("Нажать на кнопку \"Личный кабинет\"")
    public void clickOnPersonalAccountButton() {
        driver.findElement(mPersonalAccountButton).click();
    }

    @Step("Нажать на кнопку \"Войти в аккаунт\"")
    public void clickOnLoginButton() {
        driver.findElement(mLoginButton).click();
    }

    @Step("Нажать на вкладку \"Булки\"")
    public MainPage clickOnBunsTab() {
        driver.findElement(mBunsTab).click();
        return this;
    }

    @Step("Нажать на вкладку \"Соусы\"")
    public MainPage clickOnSaucesTab() {
        driver.findElement(mSaucesTab).click();
        return this;
    }

    @Step("Нажать на вкладку \"Начинки\"")
    public MainPage clickOnFillingsTab() {
        driver.findElement(mFillingsTab).click();
        return this;
    }

    @Step("Получить текст активной вкладки")
    public String getActiveTabText() {
        new WebDriverWait(driver, Duration.ofSeconds(Config.TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(mActiveTab));
        return driver.findElement(mActiveTab).getText();
    }
}
