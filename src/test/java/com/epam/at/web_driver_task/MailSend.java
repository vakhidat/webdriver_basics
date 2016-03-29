package com.epam.at.web_driver_task;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class MailSend extends MailScenario {
    public static final String MAIL_SEND_MESSAGE = "Письмо успешно отправлено.";

    @Test
    public void sendMailAndVerifySuccessSend(){
        Assert.assertNotNull(driver.findElement(By.xpath("//div[@class=\"b-mail-input__yabbles\"]/div/span/span[last()]")));
        driver.findElement(By.xpath("//table[@class=\"b-compose-head\"]/descendant::tr/td/descendant::span/button")).click();
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(5, TimeUnit.SECONDS)
                .pollingEvery(1, TimeUnit.SECONDS);
        wait.until(webDriver -> driver.getCurrentUrl().endsWith("#done"));
        Assert.assertEquals(driver
                .findElement(By.xpath("//div[@class=\"block-compose-done\"]/descendant::div[@class=\"b-done-title\"]"))
                .getText(), MAIL_SEND_MESSAGE);
    }

    @Test
    public void checkMailPresentInSentFolder() {
        driver.findElement(By.xpath("//a[@href = \"#sent\"]")).click();
        Assert.assertNotNull(driver.findElement(By.xpath("//div[@data-action=\"mail.message.show-or-select\"]")));
    }
}
