package com.epam.at.web_driver_task.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MailSendSuccess {
    public static final String MAIL_SEND_MESSAGE = "������ ������� ����������.";
    public static final String DONE_PAGE_SUFFICS = "#done";

    private WebDriver driver;

    @FindBy(xpath = "//div[@class=\"block-compose-done\"]/descendant::div[@class=\"b-done-title\"]")
    private WebElement mailSendSuccessfullyMessage;

    public MailSendSuccess(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    public String getMailSendSuccessfullyMessageText() {
        return mailSendSuccessfullyMessage.getText();
    }
}