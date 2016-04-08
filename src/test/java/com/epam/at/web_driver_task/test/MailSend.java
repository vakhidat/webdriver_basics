package com.epam.at.web_driver_task.test;

import com.epam.at.web_driver_task.page.DraftPage;
import com.epam.at.web_driver_task.page.MailSendSuccess;
import com.epam.at.web_driver_task.util.ReportUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class MailSend extends Base {
    @Test
    public void sendMailFromDraftAndVerifySuccessSend() throws InterruptedException {
        DraftPage draftPage = mailPage.draftFolderForceGo().goToFirstDraftInFolder();
        Assert.assertTrue(draftPage.getDraftRecipientEmailText().equals(""));
        MailSendSuccess mailSendSuccess = draftPage.sendMail();
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(5, TimeUnit.SECONDS)
                .pollingEvery(1, TimeUnit.SECONDS);
        wait.until(webDriver -> driver.getCurrentUrl().endsWith(MailSendSuccess.DONE_PAGE_SUFFICS));
        Assert.assertEquals(mailSendSuccess.getMailSendSuccessfullyMessageText(), MailSendSuccess.MAIL_SEND_MESSAGE);
    }

    @AfterTest(groups = "afterTestCheck")
    public void checkMailPresentInSentFolder() {
        WebElement sentMessageDiv = mailPage.goToSentFolder().getSentMessageDiv();

        ReportUtil.highlightElement(driver, sentMessageDiv);
        Assert.assertNotNull(sentMessageDiv);
    }
}
