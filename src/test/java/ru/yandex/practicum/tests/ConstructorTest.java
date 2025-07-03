package ru.yandex.practicum.tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.practicum.pages.MainPage;

public class ConstructorTest extends BaseTest {

    private MainPage mainPage;

    @Before
    public void init() {
        mainPage = new MainPage(getDriver());
    }

    @Test
    @DisplayName("Переход на вкладку \"Булки\"")
    @Description("Проверка, что осуществляется переход на вкладку \"Булки\"")
    public void checkSwitchingToBunsTabTest() {
        String activeTabText = mainPage.waitMainPageLoad()
                .clickOnFillingsTab()
                .clickOnBunsTab()
                .getActiveTabText();
        Assert.assertEquals("Булки", activeTabText);
    }

    @Test
    @DisplayName("Переход на вкладку \"Соусы\"")
    @Description("Проверка, что осуществляется переход на вкладку \"Соусы\"")
    public void checkSwitchingToSaucesTabTest() {
        String activeTabText = mainPage.waitMainPageLoad()
                .clickOnSaucesTab()
                .getActiveTabText();
        Assert.assertEquals("Соусы", activeTabText);
    }

    @Test
    @DisplayName("Переход на вкладку \"Начинки\"")
    @Description("Проверка, что осуществляется переход на вкладку \"Начинки\"")
    public void checkSwitchingToFillingsTabTest() {
        String activeTabText = mainPage.waitMainPageLoad()
                .clickOnFillingsTab()
                .getActiveTabText();
        Assert.assertEquals("Начинки", activeTabText);
    }
}
