package com.epam.at.web_driver_task.test;

import com.epam.at.web_driver_task.page.MailPage;
import com.epam.at.web_driver_task.page.Main;
import com.epam.at.web_driver_task.util.MailDataProvider;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Authorisation extends Base {
    @Test(dataProvider = "testAccountCredentials", dataProviderClass = MailDataProvider.class)
    public void loginSuccessIfMailAndPasswordAreCorrect(String login, String password) {
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        new Main(driver).login(login, password);
        Assert.assertTrue(driver.getCurrentUrl().startsWith(MailPage.YANDEX_MAILBOX_URL_FRAGMENT));
    }

    @AfterTest(alwaysRun = true, dependsOnGroups = "afterTestCheck")
    public void logout() {
        mailPage.logout();
    }
}
