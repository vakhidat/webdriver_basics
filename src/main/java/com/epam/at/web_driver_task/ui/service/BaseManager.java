package com.epam.at.web_driver_task.ui.service;

import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public abstract class BaseManager {
    protected WebDriver driver;

    public BaseManager(WebDriver driver){
        this.driver = driver;
    }

    public String getCurrentUrl() {
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        return driver.getCurrentUrl();
    }
}