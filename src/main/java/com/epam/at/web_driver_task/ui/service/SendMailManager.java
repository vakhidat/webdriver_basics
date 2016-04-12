package com.epam.at.web_driver_task.ui.service;

import com.epam.at.web_driver_task.ui.page.DraftPage;
import com.epam.at.web_driver_task.ui.page.MailSendSuccess;
import com.epam.at.web_driver_task.util.ReportUtil;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

@Slf4j
public class SendMailManager extends BaseManager {
    public static final String SCREENSHOT_PREFIX = "ml-snd";

    public SendMailManager(WebDriver driver) {
        super(driver);
    }

    public boolean sendMailFromDraft() {
        DraftPage draftPage = goToDraftFolder().goToFirstDraftInFolder();
        if (!draftPage.getDraftRecipientEmailText().equals("")) {
            log.info("that's a fail");
        }

        MailSendSuccess mailSendSuccess = draftPage.sendMail();


        fluentWait(5, 1, (webDriver -> getCurrentUrl().endsWith(MailSendSuccess.DONE_PAGE_SUFFICS)));
        String mailSendSuccessfullyMessageText = mailSendSuccess.getMailSendSuccessfullyMessageText();

        ReportUtil.highlightElementAndTakeScreenshot(driver, mailSendSuccess.getMailSendSuccessfullyMessage(), SCREENSHOT_PREFIX);
        log.info(mailSendSuccessfullyMessageText);

        return mailSendSuccessfullyMessageText.equals(MailSendSuccess.MAIL_SEND_MESSAGE);
    }

    public boolean mailIsInSentFolder() {
        WebElement sentMessageDiv = goToSentFolder().getSentMessageDiv();
        ReportUtil.highlightElementAndTakeScreenshot(driver, sentMessageDiv, SCREENSHOT_PREFIX);
        return sentMessageDiv != null;
    }
}
