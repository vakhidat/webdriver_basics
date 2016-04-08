package com.epam.at.web_driver_task.webdriver.factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class FirefoxDriverCreator {
    private static volatile WebDriver firefoxDriver;

    public static WebDriver getFirefoxRemoteDriver(String url) throws MalformedURLException {
        WebDriver localDriver = firefoxDriver;
        if (localDriver == null) {
            synchronized (FirefoxDriverCreator.class) {
                localDriver = firefoxDriver;
                if (localDriver == null) {
                    firefoxDriver = localDriver = new RemoteWebDriver(new URL(url), DesiredCapabilities.firefox());
                }
            }
        }
        return localDriver;
    }
}
