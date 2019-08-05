package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class WebdriverFactory 
{
	
	String browser;
	public WebdriverFactory(String browser)
	{
		this.browser = browser;
	}
	
	public WebDriver get_driver_instance()
	{
		WebDriver driver = null;
		if(browser.equalsIgnoreCase("chrome"))
		{
			driver = new ChromeDriver();
			return driver;
		}
		else if(browser.equalsIgnoreCase("firefox"))
		{
			driver = new FirefoxDriver();
			return driver;
		}
		else if(browser.equalsIgnoreCase("ie"))
		{
			driver = new InternetExplorerDriver();
			return driver;
		}
		else
		{
			return new FirefoxDriver();
		}
	}
}
