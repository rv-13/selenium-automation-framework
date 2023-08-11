package com.projectx.testcases;

import base.Base;
import com.projectx.pageobjects.AccountSuccessPage;
import com.projectx.pageobjects.HomePage;
import com.projectx.pageobjects.LoginPage;
import com.projectx.pageobjects.RegisterPage;
import com.projectx.utils.Utilities;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

public class Register extends Base {

    WebDriver driver;
    HomePage homePage;
    RegisterPage registerPage;
    AccountSuccessPage accountSuccessPage;

    public Register() throws IOException {
    }

    @BeforeMethod
    public void preSetup() {
        driver = initilizeBrowserAndOpenApp(properties.getProperty("browserName"));
        homePage = new HomePage(driver);
        registerPage = new RegisterPage(driver);
        accountSuccessPage = new AccountSuccessPage(driver);
        homePage.clickOnMyAccount();
        homePage.clickOnRegister();
    }

    @AfterMethod
    public void teardown() {
        driver.quit();
    }


    @Test
    //(priority = 2)
    public void verifyRegisteringWithMandatoryFields() {
        registerPage.feedFirstNameField(dataProperties.getProperty("firstName"));
        registerPage.feedLastNameField(dataProperties.getProperty("lastName"));
        registerPage.feedEmailField(Utilities.generateEmailWithTimeStamp());
        registerPage.feedTelephoneField(dataProperties.getProperty("telephone"));
        registerPage.feedPasswordField(properties.getProperty("validPassword"));
        registerPage.feedConfirmField(properties.getProperty("validPassword"));
        registerPage.clickOnAgree();
        registerPage.clickOnContinue();
        String actualHeadingText = accountSuccessPage.retrieveAccountSuccessCreatedHeadingText();
        Assert.assertEquals(actualHeadingText, dataProperties.getProperty("accountSuccessCreatedHeading"));


    }

    @Test
    //(priority = 3)
    public void verifyRegisteringWithMandatoryWithRadioOptionFields() {
        registerPage.feedFirstNameField(dataProperties.getProperty("firstName"));
        registerPage.feedLastNameField(dataProperties.getProperty("lastName"));
        registerPage.feedEmailField(Utilities.generateEmailWithTimeStamp());
        registerPage.feedTelephoneField(dataProperties.getProperty("telephone"));
        registerPage.feedPasswordField(properties.getProperty("validPassword"));
        registerPage.feedConfirmField(properties.getProperty("validPassword"));
        registerPage.newsLetterClick();
        registerPage.clickOnAgree();
        registerPage.clickOnContinue();

        String actualHeadingText = accountSuccessPage.retrieveAccountSuccessCreatedHeadingText();
        Assert.assertEquals(actualHeadingText, dataProperties.getProperty("accountSuccessCreatedHeading"));


    }

    @Test
    //(priority = 1)
    public void verifyRegisteringWithMandatoryWithoutPolicy() {
        registerPage.feedFirstNameField(dataProperties.getProperty("firstName"));
        registerPage.feedLastNameField(dataProperties.getProperty("lastName"));
        registerPage.feedEmailField(Utilities.generateEmailWithTimeStamp());
        registerPage.feedTelephoneField(dataProperties.getProperty("telephone"));
        registerPage.feedPasswordField(properties.getProperty("validPassword"));
        registerPage.feedConfirmField(properties.getProperty("validPassword"));
        registerPage.clickOnContinue();

        String actualErrorMessagePrivacyPolicy = registerPage.retrieveActualErrorMessagePrivacyPolicyField();
        Assert.assertEquals(actualErrorMessagePrivacyPolicy, dataProperties.getProperty("privacyPolicyExpectedWarning"));


    }

    @Test
    //(priority = 4)
    public void verifyRegisteringWithoutAnyFields() {
        registerPage.clickOnContinue();
        String firstNameWarning = registerPage.retrieveFirstNameWarningField();
        Assert.assertTrue(firstNameWarning.contains(dataProperties.getProperty("firstNameWarningExpectedWarning")), "First Name Error Warning not Displayed!");
        String lastNameWarning = registerPage.retrieveLastNameWarningFieldField();
        Assert.assertTrue(lastNameWarning.contains(dataProperties.getProperty("lastNameWarningExpectedWarning")), "Last Name Error Warning not Displayed!");
        String emailWarning = registerPage.retrieveEmailWarningWarningFieldField();
        Assert.assertTrue(emailWarning.contains(dataProperties.getProperty("emailWarningExpectedWarning")), "Email Error Warning not Displayed!");
        String telephoneWarning = registerPage.retrieveTelephoneWarningField();
        Assert.assertTrue(telephoneWarning.contains(dataProperties.getProperty("TelephoneWarningExpectedWarning")), "Telephone Error Warning not Displayed!");
    }

}
