package com.epam.at.web_driver_task.util;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public final class ReportUtil {
    private static final String DEFAULT_HIGHLIGHT_COLOR = "yellow";
    private static final String DEFAULT_HIGHLIGHT = "red";
    public static final String DEFAULT_SCREENSHOT_PREFIX = "hl-el";

    public static void highlightElement(WebDriver driver, WebElement element) {
        String backgroundColor = element.getCssValue("backgroundColor");
        startHighlightElement(driver, element, DEFAULT_HIGHLIGHT_COLOR);
        stopHighlightElement(driver, element, backgroundColor);
    }

    public static void highlightElementAndTakeScreenshot(WebDriver driver, WebElement element, String screenshotPrefix, String color) {
        String elementBackgroundColor = element.getCssValue("backgroundColor");
        startHighlightElement(driver, element, color);
        ScreenshotUtils.makeScreenshot(driver, screenshotPrefix);
        stopHighlightElement(driver, element, elementBackgroundColor);
    }

    public static void highlightElementAndTakeScreenshot(WebDriver driver, WebElement element, String screenshotPrefix) {
        highlightElementAndTakeScreenshot(driver, element, screenshotPrefix, DEFAULT_HIGHLIGHT);
    }

    public static void highlightElementAndTakeScreenshot(WebDriver driver, WebElement element) {
        highlightElementAndTakeScreenshot(driver, element, DEFAULT_SCREENSHOT_PREFIX, DEFAULT_HIGHLIGHT);
    }

    private static void startHighlightElement(WebDriver driver, WebElement element, String color) {
        JavascriptExecutor js = ((JavascriptExecutor) driver);
        js.executeScript("arguments[0].style.backgroundColor = '" + color + "'", element);
    }

    private static void stopHighlightElement(WebDriver driver, WebElement element, String initialBackgroundColor) {
        JavascriptExecutor js = ((JavascriptExecutor) driver);
        js.executeScript("arguments[0].style.backgroundColor = '" + initialBackgroundColor + "'", element);
    }
}
