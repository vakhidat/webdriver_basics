package com.epam.at.web_driver_task.test;

import com.epam.at.web_driver_task.page.DraftPage;
import com.epam.at.web_driver_task.page.MailSendSuccess;
import com.epam.at.web_driver_task.page.Mailbox;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class MailSend extends MailScenario {
    @Test
    public void sendMailFromDraftAndVerifySuccessSend() throws InterruptedException {
        new DraftPage(driver).sendMail();
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(5, TimeUnit.SECONDS)
                .pollingEvery(1, TimeUnit.SECONDS);
        wait.until(webDriver -> driver.getCurrentUrl().endsWith(MailSendSuccess.DONE_PAGE_SUFFICS));
        MailSendSuccess mailSendSuccess = new MailSendSuccess(driver);
        Assert.assertEquals(mailSendSuccess.getMailSendSuccessfullyMessageText(), MailSendSuccess.MAIL_SEND_MESSAGE);
    }

    @Test
    public void checkMailPresentInSentFolder() {
        Assert.assertNotNull(new Mailbox(driver).goToSentFolder().getSentMessageDiv());
    }
}
