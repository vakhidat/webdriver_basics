package com.epam.at.web_driver_task.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SentFolder extends Page{
    @FindBy(xpath = "//div[@data-action=\"mail.message.show-or-select\"]")
    private WebElement sentMessageDiv;

    public SentFolder(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    public WebElement getSentMessageDiv() {
        return sentMessageDiv;
    }
}
