package com.projectx.testcases;

import com.projectx.base.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
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

    }
}
