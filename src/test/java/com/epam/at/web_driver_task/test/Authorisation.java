package com.epam.at.web_driver_task.test;

import com.epam.at.web_driver_task.MailDataProvider;
import com.epam.at.web_driver_task.page.MailPage;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
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
        mainPage.login(login, password);
        Assert.assertTrue(driver.getCurrentUrl().startsWith(MailPage.YANDEX_MAILBOX_URL_FRAGMENT));
    }

    @AfterSuite
    public void logout() {
        mailPage.logout();
    }
}
