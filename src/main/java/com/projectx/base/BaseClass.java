package com.projectx.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.time.Duration;

public class BaseClass {

    WebDriver driver;

    public WebDriver initilizeBrowserAndOpenApp(String browser) {
        String browserName = browser;
        if (browserName.equals("chrome")) {
            driver = new ChromeDriver();
        } else if (browserName.equals("safari")) {
            driver = new SafariDriver();
        } else if (browserName.equals("edge")) {
            driver = new EdgeDriver();

        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
        driver.get("http://www.tutorialsninja.com/demo/");
        return driver;
    }
}
