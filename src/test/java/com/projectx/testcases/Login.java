package com.projectx.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Date;

public class Login {

    WebDriver driver;

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void verifyLoginWithValidCredentials() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
        driver.get("http://www.tutorialsninja.com/demo/");
        driver.findElement(By.xpath("//span[text()='My Account']")).click();
        driver.findElement(By.linkText("Login")).click();
        driver.findElement(By.id("input-email")).sendKeys("amotooricap9@gmail.com");
        driver.findElement(By.id("input-password")).sendKeys("12345");
        driver.findElement(By.xpath("//input[@value='Login']")).click();
        Assert.assertTrue(driver.findElement(By.linkText("Edit your account information")).isDisplayed());
    }

    @Test
    public void verifyLoginWithInValidCredentials() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
        driver.get("http://www.tutorialsninja.com/demo/");
        driver.findElement(By.xpath("//span[text()='My Account']")).click();
        driver.findElement(By.linkText("Login")).click();
        driver.findElement(By.id("input-email")).sendKeys("amotooricap" + generateTimeStamp() + "@gmail.com1");
        driver.findElement(By.id("input-password")).sendKeys("123451");
        driver.findElement(By.xpath("//input[@value='Login']")).click();
        String actualWarning = driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText();
        Assert.assertTrue(true, actualWarning);
    }

    @Test
    public void verifyLoginWithoutCredentials() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
        driver.get("http://www.tutorialsninja.com/demo/");
        driver.findElement(By.xpath("//span[text()='My Account']")).click();
        driver.findElement(By.linkText("Login")).click();
        driver.findElement(By.id("input-email")).sendKeys("");
        driver.findElement(By.id("input-password")).sendKeys("");
        driver.findElement(By.xpath("//input[@value='Login']")).click();
        String actualWarningMessage = driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText();
        String expectedWarningMessage = "Warning: No match for E-Mail Address and/or Password.";
        Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage), "Warning: No match for E-Mail Address and/or Password.");
    }

    public String generateTimeStamp() {
        Date date = new Date();
        return date.toString().replace(" ", "_").replace(":", "_");
    }
}
