package com.projectx.testcases;

import base.Base;
import com.projectx.pageobjects.HomePage;
import com.projectx.pageobjects.SearchPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

public class SearchTests extends Base {

    public WebDriver driver;
    SearchPage searchPage;
    HomePage homePage;

    public SearchTests() throws IOException {
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
        homePage.feedSetProductSearchField(dataProperties.getProperty("validProductSearch"));
        Assert.assertTrue(searchPage.displayStatusOfValidHProduct());

    }

    @Test
    public void verifySearchWithInValidProduct() {
        homePage.feedSetProductSearchField(dataProperties.getProperty("invalidProductSearch"));
        Assert.assertTrue(searchPage.retrieveNoProductFoundMessageText().contains(dataProperties.getProperty("noProductSearchActualWarning")));

    }

    @Test(dependsOnMethods = {"verifySearchWithInValidProduct", "verifySearchWithValidProduct"})
    public void verifySearchWithNoProduct() {
        homePage.clickSearchButton();
        Assert.assertTrue(searchPage.retrieveNoProductFoundMessageText().contains(dataProperties.getProperty("noProductSearchActualWarning")));

    }
}
