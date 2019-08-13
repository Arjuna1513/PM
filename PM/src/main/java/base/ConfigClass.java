package base;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
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
	public void beforeSuite()
	{
		excelPath = "C://Users//mallikar//git//PM//PM//src//main//java//testData//TestData.xlsx";
		loginData = new ExcelReadAndWrite("logindata", excelPath);
		pmTests = new ExcelReadAndWrite("PMTestData", excelPath);
		snmTests = new ExcelReadAndWrite("SNMTestData", excelPath);
		ipData = new ExcelReadAndWrite("IP", excelPath);
	}
	
	@AfterSuite
	public void afterSuite() 
	{
		
	}

	@BeforeClass
	public void beforeClass()
	{
		driver = new FirefoxDriver();
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
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	@AfterMethod
	public void afterMethod(ITestResult result) throws IOException
	{
		if(result.getStatus() == 2)
		{
			String methodName = result.getMethod().getMethodName();
			new Take_Screenshot().get_Screenshot(driver, methodName);
		}
	}
}
