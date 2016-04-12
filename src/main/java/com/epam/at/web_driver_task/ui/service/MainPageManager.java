package com.epam.at.web_driver_task.ui.service;

import com.epam.at.web_driver_task.model.entity.User;
import com.epam.at.web_driver_task.ui.page.Main;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;

@Slf4j
public class MainPageManager extends BaseManager {
    public MainPageManager(WebDriver driver) {
        super(driver);
    }

    public InboxManager login (User user) {
        implicitlyWait(5);
        String login = user.getLogin();
        String password = user.getPassword();
        new Main(driver).login(login, password);
        log.info("User logged in. Login: {}", login);
        return new InboxManager(driver);
    }
}
