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
    public static final String DRAFT_EMPTY_MESSAGE = "В папке «Черновики» нет писем.";
    public static final String INBOX_SUFFIX = "#inbox";
    public static final String DRAFT_SUFFIX = "#draft";
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
        String[] url = driver.getCurrentUrl().split("#");
        driver.get(url[0] + DRAFT_SUFFIX);
        Assert.assertNotNull(driver.findElement(By.xpath("//div[@class=\"block-messages-wrap\"]/div[@class=\"b-messages\"]/div[1]")));
    }


    @Test(priority = 2)
    public void checkDraftContent() throws InterruptedException {
        driver.findElement(By.xpath("//div[@class=\"block-messages-wrap\"]/div[@class=\"b-messages\"]/div[1]")).click();
    }

    @Test
    public void checkDraftDisappearedFromFolder() {
        String[] url = driver.getCurrentUrl().split("#");
        driver.get(url[0] + DRAFT_SUFFIX);
        String draftEmptyMessageActual = driver
                .findElement(By.xpath("//div[@class=\"b-messages\"]/descendant::div[@class=\"b-messages__placeholder-item\"][1]"))
                .getText();
        Assert.assertEquals(draftEmptyMessageActual, DRAFT_EMPTY_MESSAGE);
    }
}
