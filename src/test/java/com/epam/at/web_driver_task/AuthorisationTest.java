package com.epam.at.web_driver_task;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

public class AuthorisationTest {
    private WebDriver driver = WebDriverFactory.firefoxInstance();
    private String login;
    private String password;

    @Factory(dataProvider = "testAccountCredentials")
    public AuthorisationTest(String login, String password) {
        this.login = login;
        this.password = password;
    }

    @Test
    public void loginSuccessIfMailAndPasswordAreCorrect() {
        driver.findElement(By.xpath("//*[@href='https://mail.yandex.kz/']")).click();
        driver.findElement(By.className("login")).sendKeys(login);
        driver.findElement(By.className("passwd")).sendKeys(password);
        driver.findElement(By.className(" nb-button _nb-action-button nb-group-start")).click();
        Assert.assertTrue(driver.getCurrentUrl().endsWith("uid=373236611&login=test1441#inbox"));
    }

    @AfterTest
    public void logoffSuccess() {
        driver.findElement(By.xpath("//a[@id='nb-1']")).click();
        driver.findElement(By.xpath("//a[@data-metric=\"Меню сервисов:Выход\"]")).click();
    }
}
