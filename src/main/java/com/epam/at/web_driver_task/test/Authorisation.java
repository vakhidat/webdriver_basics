package com.epam.at.web_driver_task.test;

import com.epam.at.web_driver_task.model.entity.User;
import com.epam.at.web_driver_task.test.dataprovider.MailDataProvider;
import com.epam.at.web_driver_task.ui.page.Main;
import com.epam.at.web_driver_task.ui.service.MainPageManager;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

public class Authorisation extends Base {
    private MainPageManager manager;

    @Test(dataProvider = "testAccountCredentials", dataProviderClass = MailDataProvider.class)
    public void loginSuccessIfMailAndPasswordAreCorrect(User user) {
        manager = new MainPageManager(driver);
        manager.login(user);
        Assert.assertTrue(manager.isCurrentUrlIsMailboxPage());
    }

    @AfterTest(alwaysRun = true, dependsOnGroups = "afterTestCheck")
    public void logoutExpectMainPage() {
        manager = new MainPageManager(driver);
        manager.logout();
        Assert.assertEquals(manager.getCurrentUrl(), Main.YANDEX_URL, "Url is not the same with yandex main page");
    }
}
