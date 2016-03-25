package com.epam.at.web_driver_task;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebDriverFactory {
    private WebDriverFactory() {
    }

    public static class WebDriverInstanceHolder {
        public static final WebDriver FIREFOX_INSTANCE = new FirefoxDriver();
    }

    public static WebDriver firefoxInstance() {
        return WebDriverInstanceHolder.FIREFOX_INSTANCE;
    }
}
