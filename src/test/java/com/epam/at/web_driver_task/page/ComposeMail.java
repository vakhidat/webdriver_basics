package com.epam.at.web_driver_task.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ComposeMail {
    private WebDriver driver;

    @FindBy(xpath = "//div[@class=\"b-mail-input__yabbles\"]/div/input")
    private WebElement mailRecipientEmailInput;
    @FindBy(xpath = "id(\"compose-subj\")")
    private WebElement mailSubjectInput;
    @FindBy(xpath = "id(\"compose-send_ifr\")")
    private WebElement mailMessageInput;
    @FindBy(xpath = "//div[@class=\"b-popup__confirm\"]/button")
    private WebElement alertSaveConfirmation;

    public ComposeMail(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    public void writeMessageAndSaveItAsDraft(String to, String subject, String message) {
        mailRecipientEmailInput.sendKeys(to);
        mailSubjectInput.sendKeys(subject);
        mailMessageInput.sendKeys(message);
        Mailbox mailbox = new Mailbox(driver);
        mailbox.goToInboxPage();
        alertSaveConfirmation.click();
    }
}
