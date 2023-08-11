package com.projectx.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.v85.page.Page;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchPage {

    WebDriver driver;

    public SearchPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //Page Objects
    @FindBy(linkText = "HP LP3065")
    WebElement validProduct;

    @FindBy(xpath = "//input[@id='button-search']/following-sibling::p")
    WebElement noProductFoundMessage;

    //Actions

    public boolean displayStatusOfValidHProduct() {
        return validProduct.isDisplayed();
    }

    public String retrieveNoProductFoundMessageText() {
        return noProductFoundMessage.getText();
    }

}
