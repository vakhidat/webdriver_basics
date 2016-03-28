package com.epam.at.web_driver_task;

import org.testng.annotations.DataProvider;

import java.lang.reflect.Method;

public class MailDataProvider {
    @DataProvider(name = "testAccountCredentials", parallel = true)
    public static Object[][] testAccountCredentials(Method m) {
        return new Object[][]{
                new Object[]{"test1441", "123qwerty"},
        };
    }
}
