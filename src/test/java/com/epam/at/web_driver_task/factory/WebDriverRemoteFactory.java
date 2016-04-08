package com.epam.at.web_driver_task.factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class WebDriverRemoteFactory {
    private static final String HUB = "http://10.12.12.237:4444/wd/hub";
    public static final String FIREFOX_BROWSER_NAME = "firefox";
    private static WebDriver driver;

    public static WebDriver detDriverForBrowser(String browserName) throws MalformedURLException {
        if (browserName.equals(FIREFOX_BROWSER_NAME)) {
            getFirefoxDriver();
        } else if (browserName.equals("chrome")) {
            getChromeDriver();
        }
        return driver;
    }

    private static void getFirefoxDriver() throws MalformedURLException {
        driver = new RemoteWebDriver(new URL(HUB), DesiredCapabilities.firefox());
    }

    private static void getChromeDriver() throws MalformedURLException {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Vakhidat_Matiyeva@epam.com\\Downloads\\chromedriver.exe");
        driver = new RemoteWebDriver(new URL(HUB), DesiredCapabilities.chrome());
    }
}
