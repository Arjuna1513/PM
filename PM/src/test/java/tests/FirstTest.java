package tests;

import static org.testng.Assert.fail;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import base.ConfigClass;
import utilities.LoggerClass;

public class FirstTest extends ConfigClass
{
	@Test
	public void myTest() throws Exception
	{
		try
		{
			System.out.println("Hello Jenkins");
			LoggerClass.error("error", "MyTest");
			driver.findElement(By.id("kkk")).click();
		}
		catch(Exception e)
		{
			LoggerClass.error(e.toString(), "MyTest");
			throw e;
		}

	}
}
