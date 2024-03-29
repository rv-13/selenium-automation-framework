package base;

import com.projectx.utils.Utilities;
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
    public Properties dataProperties;

    public Base() throws IOException {
        properties = new Properties();
        File propFile = new File(System.getProperty("user.dir") + "/src/main/java/com/projectx/config/config.properties");
        dataProperties = new Properties();
        File datPropFile = new File(System.getProperty("user.dir") + "/src/main/java/com/projectx/testdata/testdata.properties");
        FileInputStream fisForData = new FileInputStream(datPropFile);
        dataProperties.load(fisForData);
        FileInputStream fis = new FileInputStream(propFile);
        properties.load(fis);
    }

    public WebDriver initilizeBrowserAndOpenApp(String browser) {
        String browserName = browser;
        if (browserName.equalsIgnoreCase("chrome")) {
            driver = new ChromeDriver();
        } else if (browserName.equalsIgnoreCase("safari")) {
            driver = new SafariDriver();
        } else if (browserName.equalsIgnoreCase("edge")) {
            driver = new EdgeDriver();

        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Utilities.IMPLICIT_WAIT_TIME));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Utilities.PAGE_LOAD_TIME));
        driver.get(properties.getProperty("url"));
        return driver;
    }
}
