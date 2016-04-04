package com.epam.at.web_driver_task.test;

import com.epam.at.web_driver_task.MailDataProvider;
import com.epam.at.web_driver_task.page.DraftFolder;
import com.epam.at.web_driver_task.page.Inbox;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Draft extends MailScenario {
    private String to;
    private String subject;
    private String message;

    @Factory(dataProvider = "draftMailContentAndRecipientMail", dataProviderClass = MailDataProvider.class)
    public Draft(String to, String subject, String message) {
        this.to = to;
        this.subject = subject;
        this.message = message;
    }

    @Test(priority = 0)
    public void draftCreateWithContentAndReturnToInbox() {
        mailbox.goToComposeNewEmailPage().writeMessageAndSaveItAsDraft(to, subject, message);
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(5, TimeUnit.SECONDS)
                .pollingEvery(1, TimeUnit.SECONDS);
        wait.until(webDriver -> driver.getCurrentUrl().endsWith(Inbox.SUFFIX));
    }

    @Test(priority = 1)
    public void checkDraftIsPresentInDraftFolder() {
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(5, TimeUnit.SECONDS)
                .pollingEvery(1, TimeUnit.SECONDS);
        wait.until(webDriver -> driver.getCurrentUrl().endsWith(Inbox.SUFFIX));
        DraftFolder draftFolder = mailbox.draftFolderForceGo();
        Assert.assertNotNull(draftFolder.getDraftFirstInList());
    }


    @Test(priority = 2)
    public void checkDraftContent() {
        DraftFolder draftFolder = mailbox.draftFolderForceGo();
        Assert.assertEquals(draftFolder.getDraftRecipientMail(), to);
        Assert.assertEquals(draftFolder.getDraftRecipientSubject(), subject);
        Assert.assertEquals(draftFolder.getDraftRecipientMessage(), message);
    }

    @Test
    public void checkDraftDisappearedFromFolder() {
        DraftFolder draftFolder = mailbox.draftFolderForceGo();
        draftFolder.getEmptyFolderDiv().getText();
        Assert.assertNotNull(draftFolder.getEmptyFolderDiv());
    }
}
