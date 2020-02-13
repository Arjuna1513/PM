package utilities;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import pm_pom_classes.Extension;

public class GetMxoneVersionNumber 
{
	public Extension pmExtension;
	public String title = "About : MiVoice MX-ONE Provisioning Manager";
	
	public GetMxoneVersionNumber(WebDriver driver)
	{
		pmExtension = new Extension(driver);
	}
	
	public String getMxoneVersionNumber(WebDriver driver) throws InterruptedException
	{
		String version = null;
		String parentWindow = driver.getWindowHandle();
		pmExtension.getAboutLink().click();
		Thread.sleep(2000);
		Set<String> windows = driver.getWindowHandles();
		for(String window : windows)
		{
			if(!(window.equals(parentWindow)))
			{
				driver.switchTo().window(window);
				if(driver.getTitle().trim().equals(title))
				{
					version = driver.findElement(By.xpath("(//*[@class='version'])[3]")).getText().trim();
					System.out.println(version);
					driver.close();
				}
			}
		}
		driver.switchTo().window(parentWindow);
		version = version.replace("Build: ", "");
		version = version.replace(".", "");
		System.out.println(version);
		return version;
	}
}
