package tests;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ExtractColor 
{
	static
	{
		System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"/Driver/chromedriver.exe");
		System.setProperty("webdriver.gecko.driver",System.getProperty("user.dir")+"/Driver/geckodriver.exe");
	}
	
	@Test
	public void Test1() throws InterruptedException, IOException 
	{
		WebDriver driver = new ChromeDriver();
		/*Dimension d = new Dimension(100, 100);
		Point p = new Point(100, 100);
		driver.manage().window().setSize(d);
		driver.manage().window().setPosition(p);*/
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get("https://10.211.62.212/mp/");
		
		
		WebDriver.Options options = driver.manage();
		WebDriver.Window window = options.window();
		window.maximize();
		//i am getting the cssValue for attribute color.
		String clr = driver.findElement(By.xpath("//input[@name='login']")).getCssValue("background");
		System.out.println(clr);
		//since the color is represented in the form of rgba(255,255,255,1) we need to convert it to hexagonal digits.
		String[] hex = clr.replace("rgb(","").replace(")", "").split(",");// to remove rgba( and )

		for(int i=0; i<hex.length; i++)
		{
			hex[i] = hex[i].trim();
			if(i == hex.length-1)
			{
				hex[i] = hex[i].substring(0, 3);
			}
		}
		
		int hex1 = Integer.parseInt(hex[0]);
		int hex2 = Integer.parseInt(hex[1]);
		int hex3 = Integer.parseInt(hex[2]);

		String act_hexa = String.format("#%02x%02x%02x", hex1,hex2,hex3);//if u give d instead of x then it will be formatted
		//to integer values which will give wrong values for color-code since color codes are represented in hex values.
		//hence we should give x to get proper color code values.
		System.out.println(act_hexa);
		Assert.assertEquals(act_hexa, "#00a1e0");
		driver.close();
	
		
	
	}
}
