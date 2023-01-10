package com.projectx.testcases;

import com.projectx.base.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Search extends BaseClass {

    WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = initilizeBrowserAndOpenApp("chrome");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void verifySearchWithValidProduct() {
        driver.findElement(By.name("search")).sendKeys("HP");
        driver.findElement(By.xpath("//div[@id='search']/descendant::button")).click();
        Assert.assertTrue(driver.findElement(By.linkText("HP LP3065")).isDisplayed());

    }

    @Test
    public void verifySearchWithInValidProduct() {
        driver.findElement(By.name("search")).sendKeys("avxj");
        driver.findElement(By.xpath("//div[@id='search']/descendant::button")).click();
        String noProductFoundMessage = driver.findElement(By.xpath("//input[@id='button-search']/following-sibling::p")).getText();
        Assert.assertTrue(noProductFoundMessage.contains("There is no product that matches the search criteria."));

    }

    @Test
    public void verifySearchWithNoProduct() {
        driver.findElement(By.name("search")).sendKeys("");
        driver.findElement(By.xpath("//div[@id='search']/descendant::button")).click();
        String noProductFoundMessage = driver.findElement(By.xpath("//input[@id='button-search']/following-sibling::p")).getText();
        Assert.assertTrue(noProductFoundMessage.contains("There is no product that matches the search criteria."));

    }
}
