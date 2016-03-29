package com.epam.at.web_driver_task;

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
    public void draftCreateWithContentAndSave() {
        driver.findElement(By.xpath("//a[@href=\"#compose\"]")).click();
        driver.findElement(By.xpath("//div[@class=\"b-mail-input__yabbles\"]/div/input")).sendKeys(to);
        driver.findElement(By.xpath("id(\"compose-subj\")")).sendKeys(subject);
        driver.findElement(By.xpath("id(\"compose-send_ifr\")")).sendKeys(message);
        driver.findElement(By.xpath("//a[@href=\"#inbox\"]")).click();
        driver.findElement(By.xpath("//div[@class=\"b-popup__confirm\"]/button")).click();
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(5, TimeUnit.SECONDS)
                .pollingEvery(1, TimeUnit.SECONDS);
        wait.until(webDriver -> driver.getCurrentUrl().endsWith("#inbox"));
        Assert.assertTrue(driver.getCurrentUrl().endsWith("#inbox"));
    }

    @Test(priority = 1)
    public void checkDraftIsPresentInDraftFolder() {
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(5, TimeUnit.SECONDS)
                .pollingEvery(1, TimeUnit.SECONDS);
        wait.until(webDriver -> driver.getCurrentUrl().endsWith("#inbox"));
        String[] url = driver.getCurrentUrl().split("#");
        driver.get(url[0] + "#draft");
        Assert.assertTrue(driver.findElement(By.xpath("//div[@class=\"block-messages-wrap\"]/div[@class=\"b-messages\"]/div[1]")) != null);
    }


    @Test(priority = 2) //TODO: fix it
    public void checkDraftContent() throws InterruptedException {
        driver.findElement(By.xpath("//div[@class=\"block-messages-wrap\"]/div[@class=\"b-messages\"]/div[1]")).click();

    }

    @Test
    public void checkDraftDisappearedFromFolder() {
        String[] url = driver.getCurrentUrl().split("#");
        driver.get(url[0] + "#draft");
        String draftEmptyMessageActual = driver
                .findElement(By.xpath("//div[@class=\"b-messages\"]/descendant::div[@class=\"b-messages__placeholder-item\"][1]"))
                .getText();
        Assert.assertEquals(draftEmptyMessageActual, DRAFT_EMPTY_MESSAGE);
    }
}
