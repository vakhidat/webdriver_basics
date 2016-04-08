package com.epam.at.web_driver_task.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MailSendSuccess extends MailPage {
    public static final String MAIL_SEND_MESSAGE = "Письмо успешно отправлено.";
    public static final String DONE_PAGE_SUFFICS = "#done";

    @FindBy(xpath = "//div[@class=\"block-compose-done\"]/descendant::div[@class=\"b-done-title\"]")
    private WebElement mailSendSuccessfullyMessage;

    public MailSendSuccess(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    public String getMailSendSuccessfullyMessageText() {
        return mailSendSuccessfullyMessage.getText();
    }
}
