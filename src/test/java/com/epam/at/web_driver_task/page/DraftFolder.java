package com.epam.at.web_driver_task.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DraftFolder extends MailPage {
    public static final String SUFFIX = "#draft";

    @FindBy(xpath = "//div[@class=\"b-messages\"]/descendant::div[@class=\"b-messages__placeholder-item\"][1]")
    private WebElement emptyFolderDiv;
    @FindBy(xpath = "//div[@data-action=\"mail.message.show-or-select\"]")
    private WebElement draftFirstInList;
    @FindBy(xpath = "//span[@class=\"b-messages__message__left\"]/descendant::span[@class=\"b-messages__from\"]/descendant::span[@class=\"b-messages__from__text\"]")
    private WebElement draftRecipientMail;

    @FindBy(xpath = "//span[@class=\"b-messages__message__left\"]/descendant::span[@class=\"b-messages__firstline-wrapper\"]/descendant::span[@class=\"b-messages__subject\"]")
    private WebElement draftRecipientSubject;

    @FindBy(xpath = "//span[@class=\"b-messages__message__left\"]/descendant::span[@class=\"b-messages__firstline-wrapper\"]/descendant::span[@class=\"b-messages__firstline\"]")
    private WebElement draftRecipientMessage;

    public DraftFolder(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    public DraftPage goToFirstDraftInFolder() {
        draftFirstInList.click();
        return new DraftPage(this.driver);
    }

    public WebElement getEmptyFolderDiv() {
        return emptyFolderDiv;
    }

    public WebElement getDraftFirstInList() {
        return draftFirstInList;
    }

    public String getDraftRecipientMailText() {
        return draftRecipientMail.getAttribute("title");
    }

    public String getDraftRecipientSubjectText() {
        return draftRecipientSubject.getAttribute("title");
    }

    public String getDraftRecipientMessageText() {
        return draftRecipientMessage.getAttribute("title");
    }

    public WebElement getDraftRecipientMail() {
        return draftRecipientMail;
    }

    public WebElement getDraftRecipientSubject() {
        return draftRecipientSubject;
    }

    public WebElement getDraftRecipientMessage() {
        return draftRecipientMessage;
    }
}
