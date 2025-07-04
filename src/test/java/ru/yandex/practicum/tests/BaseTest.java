package ru.yandex.practicum.tests;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.HttpClientConfig;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import net.datafaker.Faker;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import ru.yandex.practicum.pages.BasePage;
import ru.yandex.practicum.utils.Config;

import java.time.Duration;

public class BaseTest {

    protected Faker faker;
    private WebDriver driver;

    public WebDriver getDriver() {
        return driver;
    }

    @Before
    public void setUp() {
        faker = new Faker();

        initWebDriver();
        setUpRestAssured();
    }

    private void initWebDriver() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Config.TIMEOUT));
    }

    private void setUpRestAssured() {
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
        RestAssured.config = RestAssured.config()
                .httpClient(HttpClientConfig.httpClientConfig()
                        .setParam("http.connection.timeout", 5000)
                        .setParam("http.socket.timeout", 5000)
                        .setParam("http.connection-manager.timeout", 5000));
        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setBaseUri(Config.SERVICE_URL)
                .setContentType(ContentType.JSON)
                .build();
    }

    @After
    public void closeAndQuit() {
        new BasePage(driver).closeDriverAndQuitBrowser();
    }
}
