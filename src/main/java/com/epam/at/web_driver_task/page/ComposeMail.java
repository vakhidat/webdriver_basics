package com.epam.at.web_driver_task.page;

import com.epam.at.web_driver_task.model.entity.Mail;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ComposeMail extends MailPage {

    @FindBy(xpath = "//div[@class=\"b-mail-input__yabbles\"]/div/input[1]")
    private WebElement mailRecipientEmailInput;
    @FindBy(xpath = "id(\"compose-subj\")")
    private WebElement mailSubjectInput;
    @FindBy(xpath = "id(\"compose-send_ifr\")")
    private WebElement mailMessageInput;
    @FindBy(xpath = "//div[@class=\"b-popup__confirm\"]/button")
    private WebElement alertSaveConfirmation;

    public ComposeMail(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    public void writeMessageAndSaveItAsDraft(Mail mail) {
        mail.getRecipients().stream().forEach(mailRecipientEmailInput::sendKeys);
        mailSubjectInput.sendKeys(mail.getMailSubject());
        mailMessageInput.sendKeys(mail.getMailMessage());
        this.goToInboxPage();
        alertSaveConfirmation.click();
    }
}
