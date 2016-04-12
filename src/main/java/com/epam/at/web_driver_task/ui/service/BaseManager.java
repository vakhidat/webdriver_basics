package com.epam.at.web_driver_task.ui.service;

import com.epam.at.web_driver_task.ui.page.*;
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

    public void fluentWait(int timeout, int polling, TimeUnit unit, Function<? super WebDriver, ?> waitCondition) {
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(timeout, unit)
                .pollingEvery(polling, unit);
        wait.until(waitCondition);
    }

    public void fluentWait(int timeout, int polling, Function<? super WebDriver, ?> waitCondition) {
        fluentWait(timeout, polling, TimeUnit.SECONDS, waitCondition);
    }

    public void implicitlyWait(long time, TimeUnit unit) {
        driver.manage().timeouts().implicitlyWait(time , unit);
    }

    public void implicitlyWait(long time) {
        implicitlyWait(time, TimeUnit.SECONDS);
    }

    public SentFolder goToSentFolder() {
        return new Inbox(driver).goToSentFolder();
    }

    public DraftFolder goToDraftFolder() {
        return new Inbox(driver).draftFolderForceGo();
    }

    public boolean currentUrlIsMailboxPage() {
        fluentWait(5, 1, (webDriver -> getCurrentUrl().startsWith(Mailbox.YANDEX_MAILBOX_URL_FRAGMENT)));
        String currentUrl = getCurrentUrl();
        log.info("Current url is {}", currentUrl);
        return currentUrl.startsWith(Mailbox.YANDEX_MAILBOX_URL_FRAGMENT);
    }

    public boolean currentUrlIsInboxPage() {
        fluentWait(5, 1, (webDriver -> getCurrentUrl().endsWith(Inbox.SUFFIX)));
        String currentUrl = getCurrentUrl();
        log.info("Current url is {}", currentUrl);
        return getCurrentUrl().endsWith(Inbox.SUFFIX);
    }
}