package com.epam.at.web_driver_task.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DraftMailPage extends MailPage {
    @FindBy(xpath = "//div[@class=\"b-mail-input__yabbles\"]/div/input")
    private WebElement draftRecipientEmail;
    @FindBy(xpath = "id(\"compose-subj\")")
    private WebElement draftSubject;
    @FindBy(xpath = "id(\"compose-send_ifr\")")
    private WebElement draftMessage;
    @FindBy(xpath = "//table[@class=\"b-compose-head\"]/descendant::tr/td/descendant::span/button")
    private WebElement sendMailButton;

    public DraftMailPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    public MailSendSuccess sendMail() {
        sendMailButton.click();
        return new MailSendSuccess(driver);
    }

    public String getDraftSubjectText() {
        return draftSubject.getText();
    }

    public String  getDraftRecipientEmailText() {
        return draftRecipientEmail.getText();
    }

    public String  getDraftMessageText() {
        return draftMessage.getText();
    }
}
