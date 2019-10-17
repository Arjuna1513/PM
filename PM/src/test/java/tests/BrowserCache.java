package tests;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BrowserCache 
{
	public static void main(String[] args) throws InterruptedException 
	{
	    FirefoxProfile profile = new FirefoxProfile();
	    profile.setPreference("browser.cache.disk.enable", false);
	    profile.setPreference("browser.cache.memory.enable", false);
	    profile.setPreference("browser.cache.offline.enable", false);
	    profile.setPreference("network.http.use-cache", false);
	    FirefoxOptions options = new FirefoxOptions().setProfile(profile);
		WebDriver driver = new FirefoxDriver(options);
		
//		Thread.sleep(2000);
//		JavascriptExecutor js = (JavascriptExecutor)driver;
//		js.executeScript("arguments[0].scrollIntoView();",
//				driver.findElement(By.xpath("//span[contains(text(),'Advanced')]")));	
//		driver.findElement(By.xpath("//span[contains(text(),'Advanced')]")).click();
//		String parent = driver.getWindowHandle();
//		driver.findElement(By.xpath("//svg[@viewbox='0 0 24 24']")).click();
//		Set<String> set = driver.getWindowHandles();
//		for(String s : set)
//		{
//			if(!s.equals(parent))
//			{
//				driver.switchTo().window(s);
//				Select sel = new Select(driver.findElement(By.xpath("//select[@class='md-select']")));
//				sel.selectByVisibleText("All time");
//				driver.findElement(By.xpath("//cr-button[@id='clearBrowsingDataConfirm']")).click();
//			}
//		}
//		driver.switchTo().window(parent);
		
		
		
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//select[@class='md-select']")));
		Select sel = new Select(driver.findElement(By.xpath("//select[@class='md-select']")));
		sel.selectByVisibleText("All time");
		driver.findElement(By.xpath("//cr-button[@id='clearBrowsingDataConfirm']")).click();
	}
}