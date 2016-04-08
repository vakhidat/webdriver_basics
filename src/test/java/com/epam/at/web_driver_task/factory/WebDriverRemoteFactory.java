package com.epam.at.web_driver_task.factory;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class WebDriverRemoteFactory {
    private static final String HUB = "http://10.12.12.237:4444/wd/hub";
    private static WebDriver driver;

    public static WebDriver detDriverForBrowser(String browserName) throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setPlatform(Platform.WINDOWS);
        capabilities.setBrowserName(browserName);
        if (browserName.equals("chrome")) {
            System.setProperty("webdriver.chrome.driver", "C:\\Users\\Vakhidat_Matiyeva@epam.com\\Downloads\\chromedriver.exe");
        }
        driver = new RemoteWebDriver(new URL(HUB), capabilities);
        return driver;
    }
}
