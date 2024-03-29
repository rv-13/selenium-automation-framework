package com.projectx.pageobjects;

import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);

    }


    //Page Objects
    @FindBy(id = "input-email")
    WebElement emailAddressField;

    @FindBy(id = "input-password")
    WebElement passwordField;

    @FindBy(xpath = "//input[@value='Login']")
    WebElement loginButton;


    @FindBy(xpath = "//div[contains(@class,'alert-dismissible')]")
    WebElement emailWarningMessageOnLoginPage;

    //Actions
    public void sendKeysEmail(String emailText) {
        emailAddressField.sendKeys(emailText);
    }

    public void sendKeysPassword(String emailPassword) {
        passwordField.sendKeys(emailPassword);
    }

    public void clickOnLoginButton() {
        loginButton.click();
    }

    public void login(String emailText, String emailPassword) {
        emailAddressField.sendKeys(emailText);
        passwordField.sendKeys(emailPassword);
        loginButton.click();
    }

    public String emailWarningMessageOnLoginPageGetText() {
        return emailWarningMessageOnLoginPage.getText();

    }
}
