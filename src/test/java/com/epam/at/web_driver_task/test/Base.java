package com.epam.at.web_driver_task.test;

import com.epam.at.web_driver_task.factory.WebDriverRemoteFactory;
import com.epam.at.web_driver_task.page.Inbox;
import com.epam.at.web_driver_task.page.MailPage;
import com.epam.at.web_driver_task.page.Main;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import java.net.MalformedURLException;

public class Base {
    protected static WebDriver driver;
    protected static MailPage mailPage;
    protected static final String HUB = "http://10.12.12.237:4444/wd/hub";

    @BeforeTest(alwaysRun = true)
    @Parameters("browserName")
    public static void setUpDriver(String browserName) throws MalformedURLException {
        driver = WebDriverRemoteFactory.detDriverForBrowser(browserName);
        driver.manage().window().maximize();
        driver.get(Main.YANDEX_URL);
        mailPage = new Inbox(driver);
    }

    @AfterTest(alwaysRun = true)
    public static void quitBrowser() {
        driver.quit();
    }
}
