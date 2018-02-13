package TestNGData;

import java.util.concurrent.TimeUnit;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import util.TestUtil;

public class LoginTest {
	WebDriver driver;
	
	@BeforeMethod
	public void setUp() {
		System.setProperty("WebDriver.chrome.driver","D:/00/chromedriver_win32/chromedriver.exe");
		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(20,TimeUnit.SECONDS);
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.get("https://www.freecrm.com/index.html");
	}
	@DataProvider
	public Object[][] getLoginData() throws InvalidFormatException, org.openxml4j.exceptions.InvalidFormatException {
		Object data[][] = TestUtil.getTestData("LoginUserName");
	    return data;
	}
	
	@Test(dataProvider = "getLoginData")
	public void logInTest(String Username, String Password) {
		driver.findElement(By.xpath("//input[@name='username' and @type='text']")).sendKeys(Username);;
		driver.findElement(By.xpath("//input[@name='password' and @type='password']")).sendKeys(Password);;
	
		//Login Button
		WebElement LoginBtn = driver.findElement(By.xpath("//input[@class='btn btn-small' and @type='submit']"));
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();",LoginBtn);
		
	}
	@AfterMethod
	public void tearDown() {
		driver.quit();
		
	}

}
