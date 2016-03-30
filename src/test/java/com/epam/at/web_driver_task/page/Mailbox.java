package com.epam.at.web_driver_task.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Mailbox {
    private WebDriver driver;

    @FindBy(xpath = "//a[@id='nb-1']")
    private WebElement userDropdownLink;
    @FindBy(xpath = "id(\"user-dropdown-popup\")/descendant::div[@class=\"b-mail-dropdown__item\"][6]/a")
    private WebElement logoutLink;

    public Mailbox(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    public void logout() {
        userDropdownLink.click();
        logoutLink.click();
    }
}
