package com.projectx.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {

    WebDriver driver;

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //Page Objects
    @FindBy(id = "input-firstname")
    private WebElement firstNameField;

    @FindBy(id = "input-lastname")
    private WebElement lastNameField;

    @FindBy(id = "input-email")
    private WebElement emailField;

    @FindBy(id = "input-telephone")
    private WebElement telephoneField;

    @FindBy(id = "input-password")
    private WebElement passwordField;

    @FindBy(id = "input-confirm")
    private WebElement confirmField;

    @FindBy(name = "agree")
    private WebElement agreeCheckField;

    @FindBy(xpath = "//input[@value='Continue']")
    private WebElement continueElement;


    @FindBy(xpath = "//input[@name='newsletter']")
    private WebElement newsLetterCheckField;

    @FindBy(xpath = "//div[contains(@class,'alert-dismissible')]")
    private WebElement actualErrorMessagePrivacyPolicyField;

    @FindBy(xpath = "//input[contains(@id,'input-firstname')]/following-sibling::div")
    private WebElement firstNameWarningField;

    @FindBy(xpath = "//input[contains(@id,'input-lastname')]/following-sibling::div")
    private WebElement lastNameWarningField;

    @FindBy(xpath = "//input[contains(@id,'input-email')]/following-sibling::div")
    private WebElement emailWarningWarningField;

    @FindBy(xpath = "//input[contains(@id,'input-telephone')]/following-sibling::div")
    private WebElement telephoneWarningField;


    //Actions

    public void feedFirstNameField(String firstName) {
        firstNameField.sendKeys(firstName);
    }

    public void feedLastNameField(String lastName) {
        lastNameField.sendKeys(lastName);
    }

    public void feedEmailField(String email) {
        emailField.sendKeys(email);
    }

    public void feedTelephoneField(String telePhone) {
        telephoneField.sendKeys(telePhone);
    }

    public void feedPasswordField(String password) {
        passwordField.sendKeys(password);
    }

    public void feedConfirmField(String confirmedPassword) {
        confirmField.sendKeys(confirmedPassword);
    }

    public void clickOnAgree() {
        agreeCheckField.click();
    }

    public AccountSuccessPage clickOnContinue() {
        continueElement.click();
        return new AccountSuccessPage(driver);
    }

    public void newsLetterClick() {
        newsLetterCheckField.click();
    }

    public String retrieveActualErrorMessagePrivacyPolicyField() {
        return actualErrorMessagePrivacyPolicyField.getText();
    }

    public String retrieveFirstNameWarningField() {
        return firstNameWarningField.getText();
    }

    public String retrieveLastNameWarningFieldField() {
        return lastNameWarningField.getText();
    }

    public String retrieveEmailWarningWarningFieldField() {
        return emailWarningWarningField.getText();
    }

    public String retrieveTelephoneWarningField() {
        return telephoneWarningField.getText();
    }

    public void registerUser(String firstName, String lastName, String email, String telePhone, String password) {
        firstNameField.sendKeys(firstName);
        lastNameField.sendKeys(lastName);
        emailField.sendKeys(email);
        telephoneField.sendKeys(telePhone);
        passwordField.sendKeys(password);
        confirmField.sendKeys(password);
    }

    public boolean displayStatusOfAllWarningMessages(String firstNameWarningExpectedWarning, String lastNameWarningExpectedWarning, String emailWarningExpectedWarning, String telephoneWarningExpectedWarning) {

        return firstNameWarningField.getText().contains(firstNameWarningExpectedWarning) &&
                lastNameWarningField.getText().contains(lastNameWarningExpectedWarning) &&
                emailWarningWarningField.getText().contains(emailWarningExpectedWarning) &&
                telephoneWarningField.getText().contains(telephoneWarningExpectedWarning);


    }

}
