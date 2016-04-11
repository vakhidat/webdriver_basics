package com.epam.at.web_driver_task.test;

import com.epam.at.web_driver_task.page.Inbox;
import com.epam.at.web_driver_task.page.Mailbox;
import com.epam.at.web_driver_task.page.Main;
import com.epam.at.web_driver_task.webdriver.factory.WebDriverRemoteFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import java.net.MalformedURLException;

public class Base {
    protected static WebDriver driver;
    protected static Mailbox mailbox;

    @BeforeTest(alwaysRun = true)
    @Parameters("browserName")
    public static void setUpDriver(String browserName) throws MalformedURLException {
        driver = WebDriverRemoteFactory.getDriverForBrowser(browserName);
        driver.manage().window().maximize();
        driver.get(Main.YANDEX_URL);
        mailbox = new Inbox(driver);
    }

    @AfterTest(alwaysRun = true, dependsOnGroups = "afterTestCheck")
    public static void quitBrowser() {
        driver.quit();
    }
}
