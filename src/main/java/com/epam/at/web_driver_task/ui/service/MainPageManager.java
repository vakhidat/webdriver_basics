package com.epam.at.web_driver_task.ui.service;

import com.epam.at.web_driver_task.model.entity.User;
import com.epam.at.web_driver_task.page.Inbox;
import com.epam.at.web_driver_task.page.Mailbox;
import com.epam.at.web_driver_task.page.Main;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

@Slf4j
public class MainPageManager extends BaseManager {
    private Mailbox mailbox;

    public MainPageManager(WebDriver driver) {
        super(driver);
    }

    public void login (User user) {
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        String login = user.getLogin();
        String password = user.getPassword();
        new Main(driver).login(login, password);
        log.info("User logged in. Login: {} Password: {}", login, password);
    }

    public boolean isCurrentUrlIsMailboxPage() {
        log.info("Current url is {}", driver.getCurrentUrl());
        return driver.getCurrentUrl().startsWith(Mailbox.YANDEX_MAILBOX_URL_FRAGMENT);
    }

    public Main logout() {
        mailbox = new Inbox(driver);
        mailbox.logout();
        log.info("Logout perform");
        return new Main(driver);
    }
}
