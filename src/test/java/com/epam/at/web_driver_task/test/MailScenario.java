package com.epam.at.web_driver_task.test;

import com.epam.at.web_driver_task.page.Inbox;
import com.epam.at.web_driver_task.page.MailPage;
import com.epam.at.web_driver_task.page.Main;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public abstract class MailScenario {
    protected static WebDriver driver = new FirefoxDriver();
    protected static Main mainPage = new Main(driver);
    protected static MailPage mailPage = new Inbox(driver);

    @BeforeSuite(alwaysRun = true)
    public static void startFirefox() {
        driver.get(Main.YANDEX_URL);
    }

    @AfterSuite(alwaysRun = true)
    public static void quitFirefox() {
        driver.close();
    }
}
