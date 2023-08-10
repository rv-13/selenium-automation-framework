package com.projectx.testcases;

import base.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

public class Search extends Base {

    WebDriver driver;

    public Search() throws IOException {
    }

    @BeforeMethod
    public void setup() {
        driver = initilizeBrowserAndOpenApp(properties.getProperty("browserName"));
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void verifySearchWithValidProduct() {
        driver.findElement(By.name("search")).sendKeys(dataProperties.getProperty("validProductSearch"));
        driver.findElement(By.xpath("//div[@id='search']/descendant::button")).click();
        Assert.assertTrue(driver.findElement(By.linkText("HP LP3065")).isDisplayed());

    }

    @Test
    public void verifySearchWithInValidProduct() {
        driver.findElement(By.name("search")).sendKeys(dataProperties.getProperty("invalidProductSearch"));
        driver.findElement(By.xpath("//div[@id='search']/descendant::button")).click();
        String noProductFoundMessage = driver.findElement(By.xpath("//input[@id='button-search']/following-sibling::p")).getText();
        Assert.assertTrue(noProductFoundMessage.contains(dataProperties.getProperty("noProductSearchActualWarning")));

    }

    @Test
    public void verifySearchWithNoProduct() {
        driver.findElement(By.xpath("//div[@id='search']/descendant::button")).click();
        String noProductFoundMessage = driver.findElement(By.xpath("//input[@id='button-search']/following-sibling::p")).getText();
        Assert.assertTrue(noProductFoundMessage.contains(dataProperties.getProperty("noProductSearchActualWarning")));

    }
}
