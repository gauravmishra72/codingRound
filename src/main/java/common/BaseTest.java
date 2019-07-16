package common;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.sun.javafx.PlatformUtil;


public class BaseTest {
	
	public WebDriver driver;
	 public ExtentHtmlReporter htmlReporter;
	 public ExtentReports extent;
	 public ExtentTest logger;
	
	
	@BeforeMethod
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
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        driver =new ChromeDriver(options);
       
        driver.manage().window().maximize();
        PageFactory.initElements(driver, this);
        return this.driver;
    }

@AfterMethod
public void quitDriver()
{
	driver.quit();
	
}

public void waitFor(int durationInMilliSeconds) {
    try {
        Thread.sleep(durationInMilliSeconds);
    } catch (InterruptedException e) {
        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
    }
}

}
