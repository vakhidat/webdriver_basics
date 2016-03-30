package com.epam.at.web_driver_task.test;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class MailSend extends MailScenario {
    public static final String MAIL_SEND_MESSAGE = "Письмо успешно отправлено.";
    public static final String DONE_SUFFICS = "#done";

    @Test
    public void sendMailFromDraftAndVerifySuccessSend() {
        Assert.assertNotNull(driver.findElement(By.xpath("//div[@class=\"b-mail-input__yabbles\"]/div/span/span[last()]")));
        draftPage.sendMail();
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(5, TimeUnit.SECONDS)
                .pollingEvery(1, TimeUnit.SECONDS);
        wait.until(webDriver -> driver.getCurrentUrl().endsWith(DONE_SUFFICS));
        Assert.assertEquals(driver
                .findElement(By.xpath("//div[@class=\"block-compose-done\"]/descendant::div[@class=\"b-done-title\"]"))
                .getText(), MAIL_SEND_MESSAGE);
    }

    @Test
    public void checkMailPresentInSentFolder() {
        mailbox.goToSentFolder();
        Assert.assertNotNull(driver.findElement(By.xpath("//div[@data-action=\"mail.message.show-or-select\"]")));
    }
}
