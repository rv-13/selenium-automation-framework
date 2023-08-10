package com.projectx.testcases;

import base.Base;
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

    public Register() throws IOException {
    }

    @BeforeMethod
    public void setUp() {
        driver = initilizeBrowserAndOpenApp(properties.getProperty("browserName"));
        driver.findElement(By.xpath("//span[text()='My Account']")).click();
        driver.findElement(By.linkText("Register")).click();
    }

    @AfterMethod
    public void teardown() {
        driver.quit();
    }


    @Test
    //(priority = 2)
    public void verifyRegisteringWithMandatoryFields() {
        driver.findElement(By.id("input-firstname")).sendKeys(dataProperties.getProperty("firstName"));
        driver.findElement(By.id("input-lastname")).sendKeys(dataProperties.getProperty("lastName"));
        driver.findElement(By.id("input-email")).sendKeys(Utilities.generateEmailWithTimeStamp());
        driver.findElement(By.id("input-telephone")).sendKeys(dataProperties.getProperty("telephone"));
        driver.findElement(By.id("input-password")).sendKeys(properties.getProperty("validPassword"));
        driver.findElement(By.id("input-confirm")).sendKeys(properties.getProperty("validPassword"));
        driver.findElement(By.name("agree")).click();
        driver.findElement(By.xpath("//input[@value='Continue']")).click();

        String actualSuccessMessage = driver.findElement(By.xpath("//div[@id='content']/h1")).getText();
        Assert.assertEquals(actualSuccessMessage, dataProperties.getProperty("accountSuccessCreatedHeading"));


    }

    @Test
    //(priority = 3)
    public void verifyRegisteringWithMandatoryWithRadioOptionFields() {
        driver.findElement(By.id("input-firstname")).sendKeys(dataProperties.getProperty("firstName"));
        driver.findElement(By.id("input-lastname")).sendKeys(dataProperties.getProperty("lastName"));
        driver.findElement(By.id("input-email")).sendKeys(Utilities.generateEmailWithTimeStamp());
        driver.findElement(By.id("input-telephone")).sendKeys(dataProperties.getProperty("telephone"));
        driver.findElement(By.id("input-password")).sendKeys(properties.getProperty("validPassword"));
        driver.findElement(By.id("input-confirm")).sendKeys(properties.getProperty("validPassword"));
        driver.findElement(By.xpath("//input[@name='newsletter']")).click();
        driver.findElement(By.name("agree")).click();
        driver.findElement(By.xpath("//input[@value='Continue']")).click();

        String actualsuccessMessage = driver.findElement(By.xpath("//div[@id='content']/h1")).getText();
        Assert.assertEquals(actualsuccessMessage, dataProperties.getProperty("accountSuccessCreatedHeading"));


    }

    @Test
    //(priority = 1)
    public void verifyRegisteringWithMandatoryWithoutPolicy() {
        driver.findElement(By.id("input-firstname")).sendKeys(dataProperties.getProperty("firstName"));
        driver.findElement(By.id("input-lastname")).sendKeys(dataProperties.getProperty("lastName"));
        driver.findElement(By.id("input-email")).sendKeys(Utilities.generateEmailWithTimeStamp());
        driver.findElement(By.id("input-telephone")).sendKeys(dataProperties.getProperty("telephone"));
        driver.findElement(By.id("input-password")).sendKeys(properties.getProperty("validPassword"));
        driver.findElement(By.id("input-confirm")).sendKeys(properties.getProperty("validPassword"));
        driver.findElement(By.xpath("//input[@value='Continue']")).click();

        String actualErrorMessagePrivacyPolicy = driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText();
        Assert.assertEquals(actualErrorMessagePrivacyPolicy, dataProperties.getProperty("privacyPolicyExpectedWarning"));


    }

    @Test
    //(priority = 4)
    public void verifyRegisteringWithoutAnyFields() {
        driver.findElement(By.xpath("//input[@value='Continue']")).click();
        String firstNameWarning = driver.findElement(By.xpath("//input[contains(@id,'input-firstname')]/following-sibling::div")).getText();
        Assert.assertTrue(firstNameWarning.contains(dataProperties.getProperty("firstNameWarningExpectedWarning")), "First Name Error Warning not Displayed!");
        String lastNameWarning = driver.findElement(By.xpath("//input[contains(@id,'input-lastname')]/following-sibling::div")).getText();
        Assert.assertTrue(lastNameWarning.contains(dataProperties.getProperty("lastNameWarningExpectedWarning")), "Last Name Error Warning not Displayed!");
        String emailWarning = driver.findElement(By.xpath("//input[contains(@id,'input-email')]/following-sibling::div")).getText();
        Assert.assertTrue(emailWarning.contains(dataProperties.getProperty("emailWarningExpectedWarning")), "Email Error Warning not Displayed!");
        String TelephoneWarning = driver.findElement(By.xpath("//input[contains(@id,'input-telephone')]/following-sibling::div")).getText();
        Assert.assertTrue(TelephoneWarning.contains(dataProperties.getProperty("TelephoneWarningExpectedWarning")), "Telephone Error Warning not Displayed!");
    }

}
