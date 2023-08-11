package com.projectx.testcases;

import base.Base;
import com.projectx.pageobjects.HomePage;
import com.projectx.pageobjects.SearchPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

public class Search extends Base {

    WebDriver driver;
    SearchPage searchPage;
    HomePage homePage;

    public Search() throws IOException {
    }

    @BeforeMethod
    public void preSetup() {

        driver = initilizeBrowserAndOpenApp(properties.getProperty("browserName"));
        searchPage = new SearchPage(driver);
        homePage = new HomePage(driver);
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void verifySearchWithValidProduct() {
        homePage.feedSetSearchField(dataProperties.getProperty("validProductSearch"));
        homePage.clickSearchButton();

        Assert.assertTrue(searchPage.displayStatusOfValidHProduct());

    }

    @Test
    public void verifySearchWithInValidProduct() {
        homePage.feedSetSearchField(dataProperties.getProperty("invalidProductSearch"));
        homePage.clickSearchButton();

        String noProductFoundMessage = searchPage.retrieveNoProductFoundMessageText();
        Assert.assertTrue(noProductFoundMessage.contains(dataProperties.getProperty("noProductSearchActualWarning")));

    }

    @Test
    public void verifySearchWithNoProduct() {
        homePage.clickSearchButton();
        String noProductFoundMessage = searchPage.retrieveNoProductFoundMessageText();
        Assert.assertTrue(noProductFoundMessage.contains(dataProperties.getProperty("noProductSearchActualWarning")));

    }
}
