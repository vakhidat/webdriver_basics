package com.epam.at.web_driver_task.ui.page;

import org.openqa.selenium.WebDriver;

public class Inbox extends Mailbox {
    public static final String SUFFIX = "#inbox";

    public Inbox(WebDriver driver) {
        super(driver);
    }
}
