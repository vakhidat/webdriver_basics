package com.epam.at.web_driver_task.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Main {
    public static final String YANDEX_URL = "https://www.yandex.kz/";

    private WebDriver driver;
    private Actions builder;

    @FindBy(xpath = "//input[@name=\"login\"]")
    private WebElement loginInput;
    @FindBy(xpath = "//input[@name=\"passwd\"]")
    private WebElement passwordInput;
    @FindBy(xpath = "//div[@class=\"domik2__submit\"]")
    private WebElement buttonLogIn;

    public Main(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    public void login(String login, String password) {
        loginInput.sendKeys(login);
        passwordInput.sendKeys(password);
        buttonLogIn.click();
    }
}
