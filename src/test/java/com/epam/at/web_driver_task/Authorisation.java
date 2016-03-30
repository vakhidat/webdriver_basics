package com.epam.at.web_driver_task;

import com.epam.at.web_driver_task.page.Main;
import org.testng.Assert;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Authorisation extends MailScenario {
    public static final String YANDEX_MAIL_URL_FRAGMENT = "https://mail.yandex.kz/?ncrnd=";
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
        Main mainPage = new Main(driver);
        mainPage.login(login, password);
        Assert.assertTrue(driver.getCurrentUrl().startsWith(YANDEX_MAIL_URL_FRAGMENT));
    }
}
