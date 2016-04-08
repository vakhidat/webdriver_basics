package com.epam.at.web_driver_task.util;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public final class ReportUtil {

    public static final String DEFAULT_HIGHLIGHT_COLOR = "yellow";

    public static void highlightElement(WebDriver driver, WebElement element) {
        String backgroundColor = element.getCssValue("backgroundColor");
        startHighlightElement(driver, element, DEFAULT_HIGHLIGHT_COLOR);
        stopHighlightElement(driver, element, backgroundColor);
    }

    public static void highlightElementAndTakeScreenshot(WebDriver driver, WebElement element, String screenshotPrefix, String color) {
        String bg = element.getCssValue("backgroundColor");
        startHighlightElement(driver, element, color);
        ScreenshotUtils.makeScreenshot(driver, screenshotPrefix);
        stopHighlightElement(driver, element, bg);
    }

    public static void highlightElementAndTakeScreenshot(WebDriver driver, WebElement element, String screenshotPrefix) {
        highlightElementAndTakeScreenshot(driver, element, screenshotPrefix, "red");
    }

    public static void highlightElementAndTakeScreenshot(WebDriver driver, WebElement element) {
        String bg = element.getCssValue("backgroundColor");
        JavascriptExecutor js = ((JavascriptExecutor) driver);
        js.executeScript("arguments[0].style.backgroundColor = '" + "red" + "'", element);
        ScreenshotUtils.makeScreenshot(driver);
        js.executeScript("arguments[0].style.backgroundColor = '" + bg + "'", element);
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
