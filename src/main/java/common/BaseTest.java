package common;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.sun.javafx.PlatformUtil;


public class BaseTest {
	
	public WebDriver driver;
	
	@BeforeSuite
    public WebDriver setDriverPath() {
        if (PlatformUtil.isMac()) {
            System.setProperty("webdriver.chrome.driver", "chromedriver");
        }
        
        if (PlatformUtil.isWindows()) {
            System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        }
        if (PlatformUtil.isLinux()) {
            System.setProperty("webdriver.chrome.driver", "chromedriver_linux");
        }
        driver =new ChromeDriver();
        driver.manage().window().maximize();
        return driver;
    }

@AfterSuite
public void quitDriver()
{
	driver.quit();
	
}

}
