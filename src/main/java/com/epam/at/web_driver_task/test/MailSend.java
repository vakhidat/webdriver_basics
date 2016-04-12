package com.epam.at.web_driver_task.test;

import com.epam.at.web_driver_task.ui.page.DraftPage;
import com.epam.at.web_driver_task.ui.page.MailSendSuccess;
import com.epam.at.web_driver_task.util.ReportUtil;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

@Slf4j
public class MailSend extends Base {
    public static final String SCREENSHOT_PREFIX = "mail-send";

    @Test
    public void sendMailFromDraftAndVerifySuccessSend() throws InterruptedException {
        DraftPage draftPage = mailbox.draftFolderForceGo().goToFirstDraftInFolder();
        Assert.assertTrue(draftPage.getDraftRecipientEmailText().equals(""));
        MailSendSuccess mailSendSuccess = draftPage.sendMail();
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(5, TimeUnit.SECONDS)
                .pollingEvery(1, TimeUnit.SECONDS);
        wait.until(webDriver -> driver.getCurrentUrl().endsWith(MailSendSuccess.DONE_PAGE_SUFFICS));
        ReportUtil.highlightElementAndTakeScreenshot(driver, mailSendSuccess.getMailSendSuccessfullyMessage(), SCREENSHOT_PREFIX);
        Assert.assertEquals(mailSendSuccess.getMailSendSuccessfullyMessageText(), MailSendSuccess.MAIL_SEND_MESSAGE);
        log.info("mail send success");
    }

    @AfterTest(groups = "afterTestCheck")
    public void checkMailPresentInSentFolder() {
        WebElement sentMessageDiv = mailbox.goToSentFolder().getSentMessageDiv();
        ReportUtil.highlightElementAndTakeScreenshot(driver, sentMessageDiv, SCREENSHOT_PREFIX);
        Assert.assertNotNull(sentMessageDiv);
        log.info("mail present in Sent folder");
    }
}
