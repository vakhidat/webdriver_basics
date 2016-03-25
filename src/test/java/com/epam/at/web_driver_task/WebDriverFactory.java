package com.epam.at.web_driver_task;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;

public class WebDriverFactory {
    private WebDriverFactory() {
    }

    public static class WebDriverInstancesHolder {
        public static final WebDriver FIREFOX_INSTANCE = new FirefoxDriver();
        public static final WebDriver CHROME_INSTANCE = new ChromeDriver();
        public static final WebDriver OPERA_INSTANCE = new OperaDriver();
        public static final WebDriver EXPLORER_INSTANCE = new InternetExplorerDriver();
    }

    public static WebDriver firefoxInstance() {
        return WebDriverInstancesHolder.FIREFOX_INSTANCE;
    }

    public static WebDriver chromeInstance() {
        return WebDriverInstancesHolder.CHROME_INSTANCE;
    }

    public static WebDriver operaInstance() {
        return WebDriverInstancesHolder.OPERA_INSTANCE;
    }

    public static WebDriver explorerInstance() {
        return WebDriverInstancesHolder.EXPLORER_INSTANCE;
    }
}
