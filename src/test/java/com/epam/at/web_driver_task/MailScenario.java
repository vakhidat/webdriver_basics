package com.epam.at.web_driver_task;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class MailScenario {
    protected WebDriver driver = WebDriverFactory.firefoxInstance();
    private static final String YANDEX_URL = "https://www.yandex.kz/";

    @BeforeSuite(alwaysRun = true)
    public static void startFirefox() {
        WebDriverFactory.firefoxInstance().get(YANDEX_URL);
    }

    @AfterSuite(alwaysRun = true, dependsOnMethods = "logout")
    public static void quitFirefox() {
        WebDriverFactory.firefoxInstance().close();
    }

    @AfterSuite
    public void logout() {
        driver.findElement(By.xpath("//a[@id='nb-1']")).click();
        driver.findElement(By.xpath("id(\"user-dropdown-popup\")/descendant::div[@class=\"b-mail-dropdown__item\"][6]/a"))
                .click();
    }
}
