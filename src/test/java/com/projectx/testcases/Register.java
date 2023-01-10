package com.projectx.testcases;

import com.projectx.base.BaseClass;
import com.projectx.utils.Utilities;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class Register extends BaseClass {

    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = initilizeBrowserAndOpenApp("chrome");
        driver.findElement(By.xpath("//span[text()='My Account']")).click();
        driver.findElement(By.linkText("Register")).click();
    }

    @AfterMethod
    public void teardown() {
        driver.quit();
    }


    @Test(priority = 2)
    public void verifyRegisteringWithMandatoryFields() {
        driver.findElement(By.id("input-firstname")).sendKeys("Rv");
        driver.findElement(By.id("input-lastname")).sendKeys("S");
        driver.findElement(By.id("input-email")).sendKeys(Utilities.generateEmailWithTimeStamp());
        driver.findElement(By.id("input-telephone")).sendKeys("1234567890");
        driver.findElement(By.id("input-password")).sendKeys("12345");
        driver.findElement(By.id("input-confirm")).sendKeys("12345");
        driver.findElement(By.name("agree")).click();
        driver.findElement(By.xpath("//input[@value='Continue']")).click();

        String actualsuccessMessage = driver.findElement(By.xpath("//div[@id='content']/h1")).getText();
        Assert.assertEquals(actualsuccessMessage, "Your Account Has Been Created!");


    }

    @Test(priority = 3)
    public void verifyRegisteringWithMandatoryWithRadioOptionFields() {
        driver.findElement(By.id("input-firstname")).sendKeys("Rv");
        driver.findElement(By.id("input-lastname")).sendKeys("S");
        driver.findElement(By.id("input-email")).sendKeys(Utilities.generateEmailWithTimeStamp());
        driver.findElement(By.id("input-telephone")).sendKeys("1234567890");
        driver.findElement(By.id("input-password")).sendKeys("12345");
        driver.findElement(By.id("input-confirm")).sendKeys("12345");
        driver.findElement(By.xpath("//input[@name='newsletter']")).click();
        driver.findElement(By.name("agree")).click();
        driver.findElement(By.xpath("//input[@value='Continue']")).click();

        String actualsuccessMessage = driver.findElement(By.xpath("//div[@id='content']/h1")).getText();
        Assert.assertEquals(actualsuccessMessage, "Your Account Has Been Created!");


    }

    @Test(priority = 1)
    public void verifyRegisteringWithMandatoryWithoutPolicy() {
        driver.findElement(By.id("input-firstname")).sendKeys("Rv");
        driver.findElement(By.id("input-lastname")).sendKeys("S");
        driver.findElement(By.id("input-email")).sendKeys(Utilities.generateEmailWithTimeStamp());
        driver.findElement(By.id("input-telephone")).sendKeys("1234567890");
        driver.findElement(By.id("input-password")).sendKeys("12345");
        driver.findElement(By.id("input-confirm")).sendKeys("12345");
        driver.findElement(By.xpath("//input[@value='Continue']")).click();

        String actualErrorMessage = driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText();
        Assert.assertEquals(actualErrorMessage, "Warning: You must agree to the Privacy Policy!");


    }

    @Test(priority = 4)
    public void verifyRegisteringWithoutAnyFields() {
        driver.findElement(By.xpath("//input[@value='Continue']")).click();
        String firstNameWarning = driver.findElement(By.xpath("//input[contains(@id,'input-firstname')]/following-sibling::div")).getText();
        Assert.assertTrue(firstNameWarning.contains("First Name must be between 1 and 32 characters!"), "First Name Error Warning not Displayed!");
        String lastNameWarning = driver.findElement(By.xpath("//input[contains(@id,'input-lastname')]/following-sibling::div")).getText();
        Assert.assertTrue(lastNameWarning.contains("Last Name must be between 1 and 32 characters!"), "Last Name Error Warning not Displayed!");
        String emailWarning = driver.findElement(By.xpath("//input[contains(@id,'input-email')]/following-sibling::div")).getText();
        Assert.assertTrue(emailWarning.contains("E-Mail Address does not appear to be valid!"), "Email Error Warning not Displayed!");
        String TelephoneWarning = driver.findElement(By.xpath("//input[contains(@id,'input-telephone')]/following-sibling::div")).getText();
        Assert.assertTrue(TelephoneWarning.contains("Telephone must be between 3 and 32 characters!"), "Telephone Error Warning not Displayed!");
    }

}
