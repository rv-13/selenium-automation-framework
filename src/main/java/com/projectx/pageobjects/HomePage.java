package com.projectx.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

    WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);

    }

    //Login Page Objects
    @FindBy(xpath = "//span[text()='My Account']")
    WebElement myAccountDropMenu;

    @FindBy(linkText = "Login")
    WebElement loginOption;

    @FindBy(linkText = "Register")
    WebElement registerOption;

    @FindBy(name = "search")
    WebElement searchField;

    @FindBy(xpath = "//div[@id='search']/descendant::button")
    WebElement searchButton;


    //Actions
    public void clickOnMyAccount() {
        myAccountDropMenu.click();
    }

    public void selectLoginOption() {
        loginOption.click();
    }

    public void clickOnRegister() {
        registerOption.click();
    }

    public void feedSetProductSearchField(String productName) {
        searchField.sendKeys(productName);
        clickSearchButton();
    }

    public void clickSearchButton() {
        searchButton.click();
    }

    public void navigateToRegisterPage() {
        myAccountDropMenu.click();
        registerOption.click();
    }

}
