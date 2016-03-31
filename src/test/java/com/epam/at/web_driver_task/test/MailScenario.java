package com.epam.at.web_driver_task.test;

import com.epam.at.web_driver_task.WebDriverFactory;
import com.epam.at.web_driver_task.page.Mailbox;
import com.epam.at.web_driver_task.page.Main;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import static com.epam.at.web_driver_task.WebDriverFactory.firefoxDriver;

public abstract class MailScenario {
    protected static WebDriver driver = WebDriverFactory.firefoxDriver();

    @BeforeSuite(alwaysRun = true)
    public static void startFirefox() {
        firefoxDriver().get(Main.YANDEX_URL);
    }

    @AfterSuite(alwaysRun = true, dependsOnMethods = "logout")
    public static void quitFirefox() {
        firefoxDriver().close();
    }

    @AfterSuite
    public void logout() {
        Mailbox mailbox = new Mailbox(driver);
        mailbox.logout();
    }
}
