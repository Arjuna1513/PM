package base;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import utilities.ExcelReadAndWrite;
import utilities.Take_Screenshot;

public class ConfigClass
{
	public WebDriver driver = null;
	public static String excelPath = null;
	public static ExcelReadAndWrite loginData;
	public static ExcelReadAndWrite pmTests;
	public static ExcelReadAndWrite snmTests;
	public static ExcelReadAndWrite ipData;
	
	
	@BeforeSuite
	public void beforeSuite() throws IOException
	{
		excelPath = "C://Users//mallikar//git//PM//PM//src//main//java//myTestData//TestData.xlsx";
		loginData = new ExcelReadAndWrite("logindata", excelPath);
		pmTests = new ExcelReadAndWrite("PMTestData", excelPath);
		snmTests = new ExcelReadAndWrite("SNMTestData", excelPath);
		ipData = new ExcelReadAndWrite("IP", excelPath);
		
		File file = new File("C://Users//mallikar//git//PM//PM//ScreenShots");
	    if (file.exists()) 
	    {
	      FileUtils.cleanDirectory(file);
	    }
//		FileUtils.cleanDirectory(Paths.get()) 
	}
	
	@AfterSuite
	public void afterSuite() 
	{
		
	}

	/*@Parameters({"browser"})
	@BeforeClass
	public void beforeClass(String browser)
	{
		DesiredCapabilities dc = new DesiredCapabilities();
		dc.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.ACCEPT);
		if(browser.equalsIgnoreCase("firefox"))
		{
			driver = new FirefoxDriver();
		}
		else if(browser.equalsIgnoreCase("chrome"))
		{
			driver = new ChromeDriver(dc);
		}
		
	}*/
	
	//@Parameters("browser")
	@BeforeClass
	public void beforeClass()
	{
		DesiredCapabilities dc = new DesiredCapabilities();
		dc.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.ACCEPT);
		driver = new ChromeDriver(dc);
	}
	
	@AfterClass
	public void afterClass()
	{
		driver.close();
	}
	
	@BeforeMethod
	public void beforeMethod()
	{
//		driver.get("https://sqa.stackexchange.com/questions/36253/taking-screenshot-on-test-failure-selenium-webdriver-testng");
//		System.out.println(driver);
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
	}
	
	@AfterMethod
	public void afterMethod(ITestResult result) throws IOException
	{
		/*if(result.getStatus() == 2)
		{
			String methodName = result.getMethod().getMethodName();
			new Take_Screenshot().get_Screenshot(driver, methodName);
		}*/
	}
}
