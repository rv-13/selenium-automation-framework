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

    public WebDriver driver;
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
        homePage.navigateToRegisterPage();
    }

    @AfterMethod
    public void teardown() {
        driver.quit();
    }


    @Test
    //(priority = 2)
    public void verifyRegisteringWithMandatoryFields() {
        registerPage.registerUser(dataProperties.getProperty("firstName"),
                dataProperties.getProperty("lastName"), Utilities.generateEmailWithTimeStamp(),
                dataProperties.getProperty("telephone"), properties.getProperty("validPassword"));
        registerPage.clickOnAgree();
        registerPage.clickOnContinue();
        String actualHeadingText = accountSuccessPage.retrieveAccountSuccessCreatedHeadingText();
        Assert.assertEquals(actualHeadingText, dataProperties.getProperty("accountSuccessCreatedHeading"));


    }

    @Test
    //(priority = 3)
    public void verifyRegisteringWithMandatoryWithRadioOptionFields() {
        registerPage.registerUser(dataProperties.getProperty("firstName"),
                dataProperties.getProperty("lastName"), Utilities.generateEmailWithTimeStamp(),
                dataProperties.getProperty("telephone"), properties.getProperty("validPassword"));
        registerPage.newsLetterClick();
        registerPage.clickOnAgree();
        registerPage.clickOnContinue();

        String actualHeadingText = accountSuccessPage.retrieveAccountSuccessCreatedHeadingText();
        Assert.assertEquals(actualHeadingText, dataProperties.getProperty("accountSuccessCreatedHeading"));


    }

    @Test
    //(priority = 1)
    public void verifyRegisteringWithMandatoryWithoutPolicy() {
        registerPage.registerUser(dataProperties.getProperty("firstName"),
                dataProperties.getProperty("lastName"), Utilities.generateEmailWithTimeStamp(),
                dataProperties.getProperty("telephone"), properties.getProperty("validPassword"));
        registerPage.clickOnContinue();

        String actualErrorMessagePrivacyPolicy = registerPage.retrieveActualErrorMessagePrivacyPolicyField();
        Assert.assertEquals(actualErrorMessagePrivacyPolicy, dataProperties.getProperty("privacyPolicyExpectedWarning"));


    }

    @Test
    //(priority = 4)
    public void verifyRegisteringWithoutAnyFields() {
        registerPage.clickOnContinue();
        Assert.assertTrue(registerPage.displayStatusOfAllWarningMessages
                        (dataProperties.getProperty("firstNameWarningExpectedWarning"),
                                dataProperties.getProperty("lastNameWarningExpectedWarning"),
                                dataProperties.getProperty("emailWarningExpectedWarning"),
                                dataProperties.getProperty("telephoneWarningExpectedWarning")),
                "Warning Message(s) are not Displayed!");
    }

}
