package com.epam.at.web_driver_task.test;

import com.epam.at.web_driver_task.model.entity.User;
import com.epam.at.web_driver_task.test.dataprovider.MailDataProvider;
import com.epam.at.web_driver_task.ui.page.Main;
import com.epam.at.web_driver_task.ui.service.InboxManager;
import com.epam.at.web_driver_task.ui.service.MainPageManager;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

public class Authorisation extends Base {
    private MainPageManager mainPageManager;
    private InboxManager inboxManager;

    @Test(dataProvider = "testAccountCredentials", dataProviderClass = MailDataProvider.class)
    public void loginSuccessIfMailAndPasswordAreCorrect(User user) {
        mainPageManager = new MainPageManager(driver);
        inboxManager = mainPageManager.login(user);
        Assert.assertTrue(inboxManager.isCurrentUrlIsMailboxPage());
    }

    @AfterTest(alwaysRun = true, dependsOnGroups = "afterTestCheck")
    public void logoutExpectMainPage() {
        inboxManager = new InboxManager(driver);
        mainPageManager = inboxManager.logout();
        Assert.assertEquals(mainPageManager.getCurrentUrl(), Main.YANDEX_URL, "Url is not the same with yandex main page");
    }
}
