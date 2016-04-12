package com.epam.at.web_driver_task.test;

import com.epam.at.web_driver_task.model.entity.Mail;
import com.epam.at.web_driver_task.test.dataprovider.MailDataProvider;
import com.epam.at.web_driver_task.ui.service.DraftManager;
import com.epam.at.web_driver_task.ui.service.InboxManager;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

public class Draft extends Base {
    private DraftManager draftManager;
    private InboxManager inboxManager;

    @Test(priority = 0, dataProvider = "draftMailContentAndRecipientMail", dataProviderClass = MailDataProvider.class)
    public void draftCreateWithContentAndReturnToInbox(Mail mail) {
        inboxManager = new InboxManager(driver);
        inboxManager.composeNewMailAndReturnToInbox(mail);
        Assert.assertTrue(inboxManager.currentUrlIsInboxPage());
    }

    @Test(priority = 1)
    public void checkDraftIsPresentInDraftFolder() {
        draftManager = new DraftManager(driver);
        Assert.assertTrue(draftManager.draftPresentInFolder());
    }


    @Test(priority = 2, dataProvider = "draftMailContentAndRecipientMail", dataProviderClass = MailDataProvider.class)
    public void checkDraftFullContentExpectedAllFieldsMatch(Mail mail) {
        draftManager = new DraftManager(driver);
        boolean draftContentMatchExpected = draftManager.checkFullDraftContent(mail);
        Assert.assertTrue(draftContentMatchExpected);
    }

    @AfterTest(alwaysRun = true, groups = "afterTestCheck")
    public void checkDraftDisappearedFromFolder() {
        draftManager = new DraftManager(driver);
        Assert.assertTrue(draftManager.draftDisappearedFromFolder());
    }
}
