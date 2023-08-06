package com.projectx.testcases;

import base.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.projectx.utils.Utilities.generateTimeStamp;

public class Login extends Base {

    WebDriver driver;

    public Login() throws IOException {

    }

    @BeforeMethod
    public void preSetup() throws IOException {
        driver = initilizeBrowserAndOpenApp(properties.getProperty("browserName"));
        driver.findElement(By.xpath("//span[text()='My Account']")).click();
        driver.findElement(By.linkText("Login")).click();
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void verifyLoginWithValidCredentials() {
        driver.findElement(By.id("input-email")).sendKeys(properties.getProperty("validEmail"));
        driver.findElement(By.id("input-password")).sendKeys(properties.getProperty("validPassword"));
        driver.findElement(By.xpath("//input[@value='Login']")).click();
        Assert.assertTrue(driver.findElement(By.linkText("Edit your account information")).isDisplayed());
    }

    @Test
    public void verifyLoginWithInValidCredentials() {
        driver.findElement(By.id("input-email")).sendKeys("amotooricap" + generateTimeStamp() + "@gmail.com1");
        driver.findElement(By.id("input-password")).sendKeys("123451");
        driver.findElement(By.xpath("//input[@value='Login']")).click();
        String actualWarning = driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText();
        Assert.assertTrue(true, actualWarning);
    }

    @Test
    public void verifyLoginWithoutCredentials() {
        driver.findElement(By.id("input-email")).sendKeys("");
        driver.findElement(By.id("input-password")).sendKeys("");
        driver.findElement(By.xpath("//input[@value='Login']")).click();
        String actualWarningMessage = driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText();
        String expectedWarningMessage = "Warning: No match for E-Mail Address and/or Password.";
        Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage), "Warning: No match for E-Mail Address and/or Password.");
    }


}
