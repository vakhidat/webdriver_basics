package com.epam.at.web_driver_task;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class DraftCreation extends MailScenario {
    private String to;
    private String subject;
    private String message;

    @Factory(dataProvider = "draftMailContentAndRecipientMail", dataProviderClass = MailDataProvider.class)
    public DraftCreation(String to, String subject, String message) {
        this.to = to;
        this.subject = subject;
        this.message = message;
    }

    @Test
    public void draftCreateWithContentAndSave() throws InterruptedException {
        driver.findElement(By.xpath("//a[@href=\"#compose\"]")).click();
        driver.findElement(By.xpath("//div[@class=\"b-mail-input__yabbles\"]/div/input")).sendKeys(to);
        driver.findElement(By.xpath("id(\"compose-subj\")")).sendKeys(subject);
        driver.findElement(By.xpath("id(\"compose-send_ifr\")")).sendKeys(message);
        driver.findElement(By.xpath("//a[@href=\"#inbox\"]")).click();
        driver.findElement(By.xpath("//div[@class=\"b-popup__confirm\"]/button")).click();
        Thread.sleep(2000);
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(5, TimeUnit.SECONDS)
                .pollingEvery(1, TimeUnit.SECONDS);
        wait.until(webDriver -> driver.getCurrentUrl().endsWith("#inbox"));
        Assert.assertTrue(driver.getCurrentUrl().endsWith("#inbox"));
    }
}
