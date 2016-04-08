package com.epam.at.web_driver_task.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
public class ScreenshotUtils {
    private static final String DEFAULT_SCREENSHOT_PREFIX = "scr";

    private static final SimpleDateFormat DEFAULT_DATE_FORMAT = new SimpleDateFormat("dd.MMM.yyyy-HH.mm.ss[SSS]");


    public static File makeScreenshot(WebDriver webDriver) {
        return makeScreenshot(webDriver, DEFAULT_SCREENSHOT_PREFIX);
    }

    public static File makeScreenshot(WebDriver webDriver, String screenshotPrefix) {
        return saveScreeenshot(webDriver, screenshotPrefix);
    }

    private static File saveScreeenshot(WebDriver webDriver, String screenshotPrefix) {
        System.setProperty("org.uncommons.reportng.escape-output", "false");
        try {
            byte[] screenshotBytes = getScreenshotAs(webDriver, OutputType.BYTES);
            File screenShotFile = new File("test-output/html/screenshots", String.format("%s-%s.png", screenshotPrefix,
                    DEFAULT_DATE_FORMAT.format(new Date(System.currentTimeMillis()))));
            FileUtils.writeByteArrayToFile(screenShotFile, screenshotBytes);
            log.info("Taken screenshot <a href='screenshots/%1$s'>%1$s</a> {}", screenShotFile.getName());
            return screenShotFile;
        } catch (Exception e) {
            log.error("Error during taking screenshot", e);
            return null;
        }
    }

    private static <X> X getScreenshotAs(WebDriver webDriver, OutputType<X> paramOutputType) throws WebDriverException {
        if (RemoteWebDriver.class.equals(webDriver.getClass())) {
            return ((TakesScreenshot) new Augmenter().augment(webDriver)).getScreenshotAs(paramOutputType);
        } else {
            return ((TakesScreenshot) webDriver).getScreenshotAs(paramOutputType);
        }
    }
}