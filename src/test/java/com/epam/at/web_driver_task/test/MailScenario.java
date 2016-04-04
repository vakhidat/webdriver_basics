package com.epam.at.web_driver_task.test;

import com.epam.at.web_driver_task.WebDriverFactory;
import com.epam.at.web_driver_task.page.Inbox;
import com.epam.at.web_driver_task.page.MailPage;
import com.epam.at.web_driver_task.page.Main;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import static com.epam.at.web_driver_task.WebDriverFactory.firefoxDriver;

public abstract class MailScenario {
    protected static WebDriver driver = WebDriverFactory.firefoxDriver();
    protected static Main mainPage = new Main(driver);
    protected static MailPage mailPage = new Inbox(driver);

    @BeforeSuite(alwaysRun = true)
    public static void startFirefox() {
        firefoxDriver().get(Main.YANDEX_URL);
    }

    @AfterSuite(alwaysRun = true)
    public static void quitFirefox() {
        firefoxDriver().close();
    }
}
