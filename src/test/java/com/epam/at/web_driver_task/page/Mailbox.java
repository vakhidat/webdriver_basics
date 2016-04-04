package com.epam.at.web_driver_task.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Mailbox extends Page{
    public static final String YANDEX_MAIL_URL_FRAGMENT = "https://mail.yandex.kz/?ncrnd=";
    public static final String DRAFT_SUFFIX = "#draft";

    @FindBy(xpath = "//a[@id='nb-1']")
    private WebElement userDropdownLink;
    @FindBy(xpath = "id(\"user-dropdown-popup\")/descendant::div[@class=\"b-mail-dropdown__item\"][6]/a")
    private WebElement logoutLink;
    @FindBy(xpath = "//a[@href=\"#compose\"]")
    private WebElement composeNewEmailLink;
    @FindBy(xpath = "//a[@href = \"#sent\"]")
    private WebElement sentFolderLink;
    @FindBy(xpath = "//a[@href=\"#inbox\"]")
    private WebElement indoxLink;

    public Mailbox(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    public SentFolder goToSentFolder() {
        sentFolderLink.click();
        return new SentFolder(driver);
    }

    public ComposeMail goToComposeNewEmailPage() {
        composeNewEmailLink.click();
        return new ComposeMail(driver);
    }

    public Inbox goToInboxPage() {
        indoxLink.click();
        return new Inbox(driver);
    }

    public DraftFolder draftFolderForceGo() {
        String[] url = driver.getCurrentUrl().split("#");
        driver.get(url[0] + DRAFT_SUFFIX);
        return new DraftFolder(driver);
    }

    public Main logout() {
        userDropdownLink.click();
        logoutLink.click();
        return new Main(driver);
    }
}
