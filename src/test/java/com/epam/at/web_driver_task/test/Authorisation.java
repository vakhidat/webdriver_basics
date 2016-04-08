package com.epam.at.web_driver_task.test;

import com.epam.at.web_driver_task.model.entity.User;
import com.epam.at.web_driver_task.dataprovider.MailDataProvider;
import com.epam.at.web_driver_task.page.MailPage;
import com.epam.at.web_driver_task.page.Main;
import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

@Slf4j
public class Authorisation extends Base {
    @Test(dataProvider = "testAccountCredentials", dataProviderClass = MailDataProvider.class)
    public void loginSuccessIfMailAndPasswordAreCorrect(User user) {
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        String login = user.getLogin();
        String password = user.getPassword();
        new Main(driver).login(login, password);
        Assert.assertTrue(driver.getCurrentUrl().startsWith(MailPage.YANDEX_MAILBOX_URL_FRAGMENT));
        log.info("user logged in. Login: {} Password: {}", login, password);
    }

    @AfterTest(alwaysRun = true, dependsOnGroups = "afterTestCheck")
    public void logout() {
        mailPage.logout();
    }
}
