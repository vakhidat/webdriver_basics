package com.epam.at.web_driver_task.test;

import com.epam.at.web_driver_task.page.MailSendSuccess;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class MailSend extends MailScenario {
    @Test
    public void sendMailFromDraftAndVerifySuccessSend() throws InterruptedException {
        MailSendSuccess mailSendSuccess = mailbox.draftFolderForceGo().goToFirstDraftInFolder().sendMail();
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(5, TimeUnit.SECONDS)
                .pollingEvery(1, TimeUnit.SECONDS);
        wait.until(webDriver -> driver.getCurrentUrl().endsWith(MailSendSuccess.DONE_PAGE_SUFFICS));
        Assert.assertEquals(mailSendSuccess.getMailSendSuccessfullyMessageText(), MailSendSuccess.MAIL_SEND_MESSAGE);
    }

    @Test
    public void checkMailPresentInSentFolder() {
        Assert.assertNotNull(mailbox.goToSentFolder().getSentMessageDiv());
    }
}
