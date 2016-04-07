package com.epam.at.web_driver_task;

import org.openqa.selenium.WebDriver;

public final class WebDriverFactory {
    private static WebDriver driver;

    public static void setDriver(WebDriver driver) {
        WebDriverFactory.driver = driver;
    }

    public static WebDriver getDriverInstance() {
        return driver;
    }
}
