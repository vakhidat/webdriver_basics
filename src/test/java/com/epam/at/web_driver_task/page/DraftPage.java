package com.epam.at.web_driver_task.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DraftPage {
    private WebDriver driver;

    @FindBy(xpath = "//table[@class=\"b-compose-head\"]/descendant::tr/td/descendant::span/button")
    private WebElement sendMailButton;
    @FindBy(xpath = "//div[@class=\"b-mail-input__yabbles\"]/div/input")
    private WebElement mailRecipientEmailInput;
    @FindBy(xpath = "id(\"compose-subj\")")
    private WebElement mailSubjectInput;
    @FindBy(xpath = "id(\"compose-send_ifr\")")
    private WebElement mailMessageInput;
    @FindBy(xpath = "//div[@class=\"b-popup__confirm\"]/button")
    private WebElement alertSaveConfirmation;

    public DraftPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    public void writeDraftAndSaveIt(String to, String subject, String message) {
        mailRecipientEmailInput.sendKeys(to);
        mailSubjectInput.sendKeys(subject);
        mailMessageInput.sendKeys(message);
        Mailbox mailbox = new Mailbox(driver);
        mailbox.goToInboxPage();
        alertSaveConfirmation.click();
    }

    public void sendMail() {
        sendMailButton.click();
    }
}
