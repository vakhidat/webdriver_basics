package com.epam.at.web_driver_task;

import com.epam.at.web_driver_task.page.Main;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class MailScenario {
    protected WebDriver driver = WebDriverFactory.firefoxDriver();
    private static final String YANDEX_URL = "https://www.yandex.kz/";

    @BeforeSuite(alwaysRun = true)
    public static void startFirefox() {
        Main mainPage = new Main(WebDriverFactory.firefoxDriver());
        mainPage.getMainYandexPage();
    }

    @AfterSuite(alwaysRun = true, dependsOnMethods = "logout")
    public static void quitFirefox() {
        WebDriverFactory.firefoxDriver().close();
    }

    @AfterSuite
    public void logout() {
        driver.findElement(By.xpath("//a[@id='nb-1']")).click();
        driver.findElement(By.xpath("id(\"user-dropdown-popup\")/descendant::div[@class=\"b-mail-dropdown__item\"][6]/a"))
                .click();
    }
}
