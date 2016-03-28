package com.epam.at.web_driver_task;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class DraftCreation extends MailScenario {

    @Test
    public void draftCreateWithContentAndSave() {
        driver.findElement(By.xpath("//a[@href=\"#compose\"]")).click();
        driver.findElement(By.xpath("//div[@class=\"b-mail-input__yabbles\"]/div/input")).sendKeys("vahidat.m@yandex.ru");
        driver.findElement(By.xpath("id(\"compose-subj\")")).sendKeys("mail scenario");
        driver.findElement(By.xpath("id(\"compose-send_ifr\")")).sendKeys("Hi! Test finished successfully. Congrats :)");
        driver.findElement(By.xpath("//a[@href=\"#inbox\"]")).click();
        driver.findElement(By.xpath("//div[@class=\"b-popup__confirm\"]/button")).click();
    }
}
