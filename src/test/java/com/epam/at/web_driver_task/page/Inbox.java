package com.epam.at.web_driver_task.page;

import org.openqa.selenium.WebDriver;

public class Inbox {
    public static final String SUFFIX = "#inbox";
    private WebDriver driver;

    public Inbox(WebDriver driver) {
        this.driver = driver;
    }
}
