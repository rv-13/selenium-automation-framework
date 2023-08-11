package com.projectx.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountSuccessPage {
    WebDriver driver;

    public AccountSuccessPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //Page Objects
    @FindBy(xpath = "//div[@id='content']/h1")
    private WebElement accountSuccessCreatedHeading;


    //Actions
    public String retrieveAccountSuccessCreatedHeadingText() {
        return accountSuccessCreatedHeading.getText();
    }
}
