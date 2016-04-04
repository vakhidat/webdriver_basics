package com.epam.at.web_driver_task.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public abstract class MailPage {
    public static final String YANDEX_MAILBOX_URL_FRAGMENT = "https://mail.yandex.kz/?ncrnd=";

    protected WebDriver driver;

    @FindBy(xpath = "//a[@href=\"#compose\"]")
    private WebElement composeNewEmailLink;
    @FindBy(xpath = "//a[@href = \"#sent\"]")
    private WebElement sentFolderLink;
    @FindBy(xpath = "//a[@href=\"#inbox\"]")
    private WebElement inboxLink;
    @FindBy(xpath = "//a[@id='nb-1']")
    private WebElement userDropdownLink;
    @FindBy(xpath = "id(\"user-dropdown-popup\")/descendant::div[@class=\"b-mail-dropdown__item\"][6]/a")
    private WebElement logoutLink;

    public MailPage(WebDriver driver) {
        this.driver = driver;
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
        inboxLink.click();
        return new Inbox(driver);
    }

    public DraftFolder draftFolderForceGo() {
        String[] url = driver.getCurrentUrl().split("#");
        driver.get(url[0] + DraftFolder.SUFFIX);
        return new DraftFolder(driver);
    }

    public Main logout() {
        userDropdownLink.click();
        logoutLink.click();
        return new Main(driver);
    }
}
