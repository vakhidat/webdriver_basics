package com.epam.at.web_driver_task.util;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.DataProvider;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;

public class MailDataProvider {
    private static final String HUB = "http://10.12.12.237:4444/wd/hub";

    @DataProvider(name = "testAccountCredentials", parallel = true)
    public static Object[][] testAccountCredentials(Method m) {
        return new Object[][]{
                new Object[]{"test1441", "123qwerty"},
        };
    }

    @DataProvider(name = "draftMailContentAndRecipientMail", parallel = true)
    public static Object[][] draftMailContentAndRecipientMail(Method m) {
        return new Object[][]{
                new Object[]{"vahidat.m@yandex.ru", "Mail scenario", "Hi! Test finished successfully. Congrats :)"},
        };
    }

    @DataProvider(name = "browserInstance", parallel = false)
    public static Object[][] browserInstance(Method m) throws MalformedURLException {
        RemoteWebDriver remoteChromeDriver = new RemoteWebDriver(new URL(HUB), DesiredCapabilities.chrome());
        RemoteWebDriver remoteFirefoxDriver = new RemoteWebDriver(new URL(HUB), DesiredCapabilities.firefox());
        return new Object[][]{
                new Object[]{remoteChromeDriver},
                new Object[]{remoteFirefoxDriver},
        };
    }
}
