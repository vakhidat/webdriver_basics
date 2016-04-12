package com.epam.at.web_driver_task.ui.service;

import com.epam.at.web_driver_task.ui.page.DraftFolder;
import com.epam.at.web_driver_task.ui.page.Inbox;
import com.epam.at.web_driver_task.ui.page.Mailbox;
import com.epam.at.web_driver_task.ui.page.SentFolder;
import com.google.common.base.Function;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.util.concurrent.TimeUnit;

@Slf4j
public abstract class BaseManager {
    protected WebDriver driver;

    public BaseManager(WebDriver driver){
        this.driver = driver;
    }

    public String getCurrentUrl() {
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        return driver.getCurrentUrl();
    }

    public void fluentWait(int timeout, int polling, Function<? super WebDriver, ?> waitCondition) {
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(timeout, TimeUnit.SECONDS)
                .pollingEvery(polling, TimeUnit.SECONDS);
        wait.until(waitCondition);
    }

    public SentFolder goToSentFolder() {
        return new Inbox(driver).goToSentFolder();
    }

    public DraftFolder goToDraftFolder() {
        return new Inbox(driver).draftFolderForceGo();
    }

    public boolean currentUrlIsMailboxPage() {
        String currentUrl = getCurrentUrl();
        log.info("Current url is {}", currentUrl);
        return currentUrl.startsWith(Mailbox.YANDEX_MAILBOX_URL_FRAGMENT);
    }

    public boolean currentUrlIsInboxPage() {
        String currentUrl = getCurrentUrl();
        log.info("Current url is {}", currentUrl);
        return getCurrentUrl().endsWith(Inbox.SUFFIX);
    }
}