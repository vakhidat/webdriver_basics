package com.epam.at.web_driver_task.test.dataprovider;

import com.epam.at.web_driver_task.model.entity.Mail;
import com.epam.at.web_driver_task.model.entity.User;
import org.testng.annotations.DataProvider;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class MailDataProvider {
    public static final String DEFAULT_LOGIN = "test1441";
    public static final String DEFAULT_PASSWORD = "123qwerty";
    public static final String DEFAULT_RECIPIENT_MAIL = "vahidat.m@yandex.ru";
    public static final String DEFAULT_SUBJECT = "Mail scenario";
    public static final String DEFAULT_MESSAGE = "Hi! Test finished successfully. Congrats :)";

    @DataProvider(name = "testAccountCredentials", parallel = true)
    public static Object[][] testAccountCredentials(Method m) {
        return new Object[][]{
                new Object[]{new User(DEFAULT_LOGIN, DEFAULT_PASSWORD)},
        };
    }

    @DataProvider(name = "draftMailContentAndRecipientMail", parallel = true)
    public static Object[][] draftMailContentAndRecipientMail(Method m) {
        List<String> recipients = new ArrayList<>();
        recipients.add(DEFAULT_RECIPIENT_MAIL);
        Mail mailWithOneRecipient = new Mail(recipients, DEFAULT_SUBJECT, DEFAULT_MESSAGE);
        return new Object[][]{
                new Object[]{mailWithOneRecipient}
        };
    }
}
