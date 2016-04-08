package com.epam.at.web_driver_task.test;

import com.epam.at.web_driver_task.model.entity.User;
import com.epam.at.web_driver_task.dataprovider.MailDataProvider;
import com.epam.at.web_driver_task.page.MailPage;
import com.epam.at.web_driver_task.page.Main;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Authorisation extends Base {
    @Test(dataProvider = "testAccountCredentials", dataProviderClass = MailDataProvider.class)
    public void loginSuccessIfMailAndPasswordAreCorrect(User user) {
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        new Main(driver).login(user.getLogin(), user.getPassword());
        Assert.assertTrue(driver.getCurrentUrl().startsWith(MailPage.YANDEX_MAILBOX_URL_FRAGMENT));
    }

    @AfterTest(alwaysRun = true, dependsOnGroups = "afterTestCheck")
    public void logout() {
        mailPage.logout();
    }
}
