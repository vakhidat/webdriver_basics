package com.epam.at.web_driver_task.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DraftPage {
    private WebDriver driver;

    @FindBy(xpath = "//table[@class=\"b-compose-head\"]/descendant::tr/td/descendant::span/button")
    private WebElement sendMailButton;

    public DraftPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    public void sendMail() {
        sendMailButton.click();
    }
}
