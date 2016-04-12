package com.epam.at.web_driver_task.test;

import com.epam.at.web_driver_task.ui.service.SendMailManager;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

public class MailSend extends Base {
    private SendMailManager sendMailManager;

    @Test
    public void verifySuccessMailSendExpectedTrue() throws InterruptedException {
        sendMailManager = new SendMailManager(driver);
        Assert.assertTrue(sendMailManager.sendMailFromDraft());
    }

    @AfterTest(groups = "afterTestCheck")
    public void checkMailPresentInSentFolderExpectedTrue() {
        sendMailManager = new SendMailManager(driver);
        Assert.assertTrue(sendMailManager.mailIsInSentFolder());
    }
}
