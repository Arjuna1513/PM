package base;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestContext;
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
		excelPath = "./TestData.xlsx";
		loginData = new ExcelReadAndWrite("logindata", excelPath);
		pmTests = new ExcelReadAndWrite("PMTestData", excelPath);
		snmTests = new ExcelReadAndWrite("SNMTestData", excelPath);
		ipData = new ExcelReadAndWrite("IP", excelPath);
		
		File file = new File("./ScreenShots");
	    if (file.exists()) 
	    {
	      FileUtils.cleanDirectory(file);
	    }
	    
	    File file1 = new File("./seleniumLogs.log");
	    if (file1.exists()) 
	    	System.out.println(file1);
	    {
	      FileUtils.deleteQuietly(file1);
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
		
//		dc.setCapability(ChromeOptions.CAPABILITY, options);
		
		// ChromeDriver is just AWFUL because every version or two it breaks unless you pass cryptic arguments
        //AGRESSIVE: options.setPageLoadStrategy(PageLoadStrategy.NONE); // https://www.skptricks.com/2018/08/timed-out-receiving-message-from-renderer-selenium.html
//        options.addArguments("start-maximized"); // https://stackoverflow.com/a/26283818/1689770
//        options.addArguments("enable-automation"); // https://stackoverflow.com/a/43840128/1689770
//        options.addArguments("--headless"); // only if you are ACTUALLY running headless
//        options.addArguments("--no-sandbox"); //https://stackoverflow.com/a/50725918/1689770
//        options.addArguments("--disable-infobars"); //https://stackoverflow.com/a/43840128/1689770
//        options.addArguments("--disable-dev-shm-usage"); //https://stackoverflow.com/a/50725918/1689770
//        options.addArguments("--disable-browser-side-navigation"); //https://stackoverflow.com/a/49123152/1689770
//        options.addArguments("--disable-gpu"); //https://stackoverflow.com/questions/51959986/how-to-solve-selenium-chromedriver-timed-out-receiving-message-from-renderer-exc
//        driver = new ChromeDriver(options);
		
		
		
//		ChromeOptions options = new ChromeOptions();
//		options.addArguments("enable-automation");
//		options.addArguments("--window-size=1920,1080");
//		options.addArguments("--no-sandbox");
//		options.addArguments("--disable-extensions");
//		options.addArguments("--dns-prefetch-disable");
//		options.addArguments("--disable-gpu");
//		options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
//		options.merge(dc);
//		
//		driver = new ChromeDriver();
		
		 
	}
	
	@AfterClass
	public void afterClass()
	{
//		driver.close();
	}
	
	@BeforeMethod
	public void beforeMethod(ITestContext context)
	{
//		driver.get("https://sqa.stackexchange.com/questions/36253/taking-screenshot-on-test-failure-selenium-webdriver-testng");
//		System.out.println(driver);
		FirefoxProfile profile = new FirefoxProfile();
	    profile.setPreference("browser.cache.disk.enable", false);
	    profile.setPreference("browser.cache.memory.enable", false);
	    profile.setPreference("browser.cache.offline.enable", false);
	    profile.setPreference("network.http.use-cache", false);
	    FirefoxOptions options = new FirefoxOptions().setProfile(profile);
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		
		context.setAttribute("WebDriver", driver);
	}
	
	@AfterMethod
	public void afterMethod(ITestResult result) throws IOException
	{
		driver.close();
		/*if(result.getStatus() == 2)
		{
			String methodName = result.getMethod().getMethodName();
			new Take_Screenshot().get_Screenshot(driver, methodName);
		}*/
	}
}
