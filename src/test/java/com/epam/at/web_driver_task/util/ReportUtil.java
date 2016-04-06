package com.epam.at.web_driver_task.util;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public final class ReportUtil {
    public static void highlightElement(WebDriver driver, WebElement element)
    {
        String bg = element.getCssValue("backgroundColor");
        JavascriptExecutor js = ((JavascriptExecutor) driver);
        js.executeScript("arguments[0].style.backgroundColor = '" + "yellow" + "'", element);
        js.executeScript("arguments[0].style.backgroundColor = '" + bg + "'", element);
    }
}
