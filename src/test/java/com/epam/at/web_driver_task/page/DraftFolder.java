package com.epam.at.web_driver_task.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DraftFolder {
    private WebDriver driver;
    @FindBy(xpath = "//div[@class=\"b-messages\"]/descendant::div[@class=\"b-messages__placeholder-item\"][1]")
    private WebElement emptyFolderDiv;
    @FindBy(xpath = "//div[@class=\"block-messages-wrap\"]/div[@class=\"b-messages\"]/descendant::div[@data-action=\"mail.message.show-or-select\"]")
    private WebElement draftFirstInList;

    public DraftFolder(WebDriver driver) {
        this.driver = driver;
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
}
