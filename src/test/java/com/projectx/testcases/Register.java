package com.projectx.testcases;

import com.projectx.utils.Utilities;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class Register {

    WebDriver driver;

    @Test
    public void verifyRegisteringWithMandatoryFields() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
        driver.get("http://www.tutorialsninja.com/demo/");
        driver.findElement(By.xpath("//span[text()='My Account']")).click();
        driver.findElement(By.linkText("Register")).click();
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
        driver.quit();


    }

}
