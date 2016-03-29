package com.epam.at.web_driver_task;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
        driver.get(driver.getCurrentUrl().replace("#inbox", "#draft"));
        Assert.assertTrue(driver.findElement(By.xpath("//div[@class=\"block-messages-wrap\"]/div[@class=\"b-messages\"]/div[1]")) != null);
    }


    @Test(priority = 2, enabled = false)
    public void checkDraftContent() throws InterruptedException {
        driver.findElement(By.xpath("//div[@class=\"block-messages-wrap\"]/div[@class=\"b-messages\"]/div[1]")).click();
        Thread.sleep(5000);
        String toActual = driver.findElement(By.xpath("//div[@class=\"b-mail-input__yabbles\"]/div/span/span[last()]")).getText();
        String subjectActual = driver.findElement(By.xpath("//div[@class=\"b-input\"]/descendant::div")).getText();

        WebElement element = driver.findElement(By.xpath("//table[@id=\"compose-send_tbl\"]/descendant::tr[@class=\"mceLast\"]/td/iframe"));
        element.click();
        String messageActual = element.findElement(By.xpath("//*[@id='tinymce']/div[1]")).getText();
        System.out.println(toActual);
        System.out.println(subjectActual);
        System.out.println(messageActual);
    }
}
