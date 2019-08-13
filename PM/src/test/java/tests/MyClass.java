package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class MyClass 
{
	@Test
	public void openBrowser()
	{
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().maximize();
	}
}
