package com.epam.at.web_driver_task.test;

import com.epam.at.web_driver_task.MailDataProvider;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Draft extends MailScenario {
    public static final String INBOX_SUFFIX = "#inbox";
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
        mailbox.goToComposeNewEmailPage();
        draftPage.writeDraftAndSaveIt(to, subject, message);
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(5, TimeUnit.SECONDS)
                .pollingEvery(1, TimeUnit.SECONDS);
        wait.until(webDriver -> driver.getCurrentUrl().endsWith(INBOX_SUFFIX));
    }

    @Test(priority = 1)
    public void checkDraftIsPresentInDraftFolder() {
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(5, TimeUnit.SECONDS)
                .pollingEvery(1, TimeUnit.SECONDS);
        wait.until(webDriver -> driver.getCurrentUrl().endsWith(INBOX_SUFFIX));
        draftFolder.draftFolderForceGo();
        Assert.assertNotNull(draftFolder.getDraftFirstInList());
    }


    @Test(priority = 2)
    public void checkDraftContent() throws InterruptedException {
        draftFolder.goToFirstDraftInFolder();
        Assert.assertNotNull(driver.findElement(By.xpath("//div[@class=\"b-mail-input__yabbles\"]/div/span/span[last()]")));
    }

    @Test
    public void checkDraftDisappearedFromFolder() {
        draftFolder.draftFolderForceGo();
        Assert.assertNotNull(draftFolder.getEmptyFolderDiv());
    }
}
