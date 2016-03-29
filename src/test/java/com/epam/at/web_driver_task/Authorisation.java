package com.epam.at.web_driver_task;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Authorisation extends MailScenario {
    private String login;
    private String password;

    @Factory(dataProvider = "testAccountCredentials", dataProviderClass = MailDataProvider.class)
    public Authorisation(String login, String password) {
        this.login = login;
        this.password = password;
    }

    @Test
    public void loginSuccessIfMailAndPasswordAreCorrect() {
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//input[@name=\"login\"]")).sendKeys(login);
        driver.findElement(By.xpath("//input[@name=\"passwd\"]")).sendKeys(password);
        driver.findElement(By.xpath("//div[@class=\"domik2__submit\"]")).click();
        Assert.assertTrue(driver.getCurrentUrl().startsWith("https://mail.yandex.kz/?ncrnd="));
    }
}
