package com.epam.at.web_driver_task;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MailSend extends MailScenario {
    @Test
    public void sendMail() {
        Assert.assertNotNull(driver.findElement(By.xpath("//div[@class=\"b-mail-input__yabbles\"]/div/span/span[last()]")));
        driver.findElement(By.xpath("id(\"nb-5\")")).click();
        Assert.assertEquals(driver
                .findElement(By.xpath("//div[@class=\"block-compose-done\"]/descendant::div[@class=\"b-done-title\"]"))
                .getText(), "Письмо успешно отправлено.");
    }
}
