package com.epam.at.web_driver_task.webdriver.factory;

import org.openqa.selenium.WebDriver;

import java.net.MalformedURLException;

public class WebDriverRemoteFactory {
    private static final String HUB = "http://10.12.12.237:4444/wd/hub";

    public static WebDriver getDriverForBrowser(String browserName) throws MalformedURLException {
        WebDriver driver = null;
        if (browserName.equals("chrome")) {
            driver = ChromeDriverCreator.getChromeRemoteDriver(HUB);
        } else if (browserName.equals("firefox")) {
            driver = FirefoxDriverCreator.getFirefoxRemoteDriver(HUB);
        }
        return driver;
    }
}
