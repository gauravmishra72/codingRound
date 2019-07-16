package testSuites;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import common.BaseTest;

public class SignInTest extends BaseTest  {


	@Test
    public void shouldThrowAnErrorIfSignInDetailsAreMissing() {

		driver.get("https://www.cleartrip.com/");
        waitFor(2000);

        driver.findElement(By.linkText("Your trips")).click();
        driver.findElement(By.id("SignIn")).click();
        driver.switchTo().frame("modal_window");
        driver.findElement(By.xpath("//button[@id='signInButton']")).click();;
        
        String errors1 = driver.findElement(By.xpath("//div[@id='errors1']/span")).getText().trim();;
        
        Assert.assertTrue(errors1.contains("There were errors in your submission"));
        driver.quit();
    }

}
