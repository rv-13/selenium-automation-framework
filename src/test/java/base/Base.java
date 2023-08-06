package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class Base {

    WebDriver driver;
    public Properties properties;

    public Base() throws IOException {
        properties = new Properties();
        File propFile = new File(System.getProperty("user.dir") + "/src/main/java/com/projectx/config/properties");
        System.out.println(propFile + "propFIleName");
        FileInputStream fis = new FileInputStream(propFile);
        properties.load(fis);
    }

    public WebDriver initilizeBrowserAndOpenApp(String browser) {
        String browserName = browser;
        if (browserName.equals("chrome")) {
//            System.setProperty("webdriver.chrome.driver" ,  "/Users/ravi/Applications/chromedriver_osx/chromedriver");
            driver = new ChromeDriver();
        } else if (browserName.equals("safari")) {
            driver = new SafariDriver();
        } else if (browserName.equals("edge")) {
            driver = new EdgeDriver();

        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
        driver.get(properties.getProperty("url"));
        return driver;
    }
}
