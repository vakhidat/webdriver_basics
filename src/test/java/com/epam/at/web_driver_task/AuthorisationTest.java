package com.epam.at.web_driver_task;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class AuthorisationTest {
    private WebDriver driver = WebDriverFactory.firefoxInstance();
    private String login;
    private String password;
    private static final String YANDEX_URL = "https://www.yandex.kz/";

    @BeforeClass(alwaysRun = true)
    public static void startFirefox() {
        WebDriverFactory.firefoxInstance().get(YANDEX_URL);
    }

    /*@AfterClass(alwaysRun = true)
    public static void quitFirefox() {
        WebDriverFactory.firefoxInstance().close();
    }*/

    @Factory(dataProvider = "testAccountCredentials", dataProviderClass = MailDataProvider.class)
    public AuthorisationTest(String login, String password) {
        this.login = login;
        this.password = password;
    }

    @Test
    public void loginSuccessIfMailAndPasswordAreCorrect() {
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//input[@name=\"login\"]")).sendKeys(login);
        driver.findElement(By.xpath("//input[@name=\"passwd\"]")).sendKeys(password);
        driver.findElement(By.xpath("//div[@class=\"domik2__submit\"]")).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.navigate().refresh();
        System.out.println(driver.getCurrentUrl());
        Assert.assertTrue(driver.getCurrentUrl().contains("uid=373236611&login=test1441#inbox"));
    }

   /* @AfterTest
    public void logoffSuccess() {
        driver.findElement(By.xpath("//a[@id='nb-1']")).click();
        driver.findElement(By.xpath("//a[@data-metric=\"Меню сервисов:Выход\"]")).click();
    }*/
}
