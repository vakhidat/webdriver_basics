package com.epam.at.web_driver_task.test;

import com.epam.at.web_driver_task.model.entity.Mail;
import com.epam.at.web_driver_task.dataprovider.MailDataProvider;
import com.epam.at.web_driver_task.page.DraftFolder;
import com.epam.at.web_driver_task.page.Inbox;
import com.epam.at.web_driver_task.util.ReportUtil;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

@Slf4j
public class Draft extends Base {
    @Test(priority = 0, dataProvider = "draftMailContentAndRecipientMail", dataProviderClass = MailDataProvider.class)
    public void draftCreateWithContentAndReturnToInbox(Mail mail) {
        mailPage.goToComposeNewEmailPage().writeMessageAndSaveItAsDraft(mail);
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(5, TimeUnit.SECONDS)
                .pollingEvery(1, TimeUnit.SECONDS);
        wait.until(webDriver -> driver.getCurrentUrl().endsWith(Inbox.SUFFIX));
        log.info("Draft recipients: {}", mail.getRecipients());
        log.info("Draft subject: {}", mail.getMailSubject());
        log.info("Draft message: {}", mail.getMailMessage());
    }

    @Test(priority = 1)
    public void checkDraftIsPresentInDraftFolder() {
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(5, TimeUnit.SECONDS)
                .pollingEvery(1, TimeUnit.SECONDS);
        wait.until(webDriver -> driver.getCurrentUrl().endsWith(Inbox.SUFFIX));
        DraftFolder draftFolder = mailPage.draftFolderForceGo();
        Assert.assertNotNull(draftFolder.getDraftFirstInList());
        ReportUtil.highlightElement(driver, draftFolder.getDraftFirstInList());
        log.info("draft is present in folder");
    }


    @Test(priority = 2, dataProvider = "draftMailContentAndRecipientMail", dataProviderClass = MailDataProvider.class)
    public void checkDraftContent(Mail mail) {
        DraftFolder draftFolder = mailPage.draftFolderForceGo();
        ReportUtil.highlightElement(driver, draftFolder.getDraftRecipientMail());
        boolean allRecipientPresent = mail.getRecipients().stream().allMatch(recipient -> draftFolder.getDraftRecipientMailText().contains(recipient));
        Assert.assertTrue(allRecipientPresent);

        ReportUtil.highlightElement(driver, draftFolder.getDraftRecipientSubject());
        Assert.assertEquals(draftFolder.getDraftRecipientSubjectText(), mail.getMailSubject());

        ReportUtil.highlightElement(driver, draftFolder.getDraftRecipientMessage());
        Assert.assertEquals(draftFolder.getDraftRecipientMessageText(), mail.getMailMessage());

        log.info("Draft suspected recipients: {}", mail.getRecipients());
        log.info("Draft actual recipients: {}", draftFolder.getDraftRecipientMailText());

        log.info("Draft suspected subject: {}", mail.getMailSubject());
        log.info("Draft actual subject: {}", draftFolder.getDraftRecipientSubjectText());

        log.info("Draft suspected message: {}", mail.getMailMessage());
        log.info("Draft actual message: {}", draftFolder.getDraftRecipientMessageText());
    }

    @AfterTest(alwaysRun = true, groups = "afterTestCheck")
    public void checkDraftDisappearedFromFolder() {
        DraftFolder draftFolder = mailPage.draftFolderForceGo();
        draftFolder.getEmptyFolderDiv().getText();
        ReportUtil.highlightElement(driver, draftFolder.getEmptyFolderDiv());
        Assert.assertNotNull(draftFolder.getEmptyFolderDiv());
        log.info("draft disappeared");
    }
}
