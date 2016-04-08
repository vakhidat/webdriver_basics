package com.epam.at.web_driver_task.webdriver.factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class ChromeDriverCreator {
    private static volatile WebDriver firefoxDriver;

    public static WebDriver getChromeRemoteDriver(String url) throws MalformedURLException {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Vakhidat_Matiyeva@epam.com\\Downloads\\chromedriver.exe");
        WebDriver localDriver = firefoxDriver;
        if (localDriver == null) {
            synchronized (ChromeDriverCreator.class) {
                localDriver = firefoxDriver;
                if (localDriver == null) {
                    firefoxDriver = localDriver = new RemoteWebDriver(new URL(url), DesiredCapabilities.chrome());
                }
            }
        }
        return localDriver;
    }
}