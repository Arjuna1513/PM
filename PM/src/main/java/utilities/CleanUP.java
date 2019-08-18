package utilities;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import junit.framework.Assert;
import pm_pom_classes.PM_Login_Page;
import pm_pom_classes.PM_Main_Page;
import pm_pom_classes.PM_User;
import pm_pom_classes.PM_Users;

public class CleanUP 
{
	public PM_Login_Page pmLoginPage;
	public PM_Main_Page pmMainPage;
	public PM_User pmUser;
	public PM_Users pmUsers;
	
	public void deleteUser(WebDriver driver, ExcelReadAndWrite ipData, String[] data, String uerName)
	{
		pmMainPage = new PM_Main_Page(driver);
		pmLoginPage = new PM_Login_Page(driver);
		pmUser = new PM_User(driver);
		pmUsers = new PM_Users(driver);
		driver.get(ipData.getData(0, 0));
		pmLoginPage.PM_Login(data[0], data[1]);
		pmMainPage.getUsers().click();
		pmUsers.getUser().click();
		pmUser.setUserSearchTextBox(uerName);
		pmUser.getOnViewRangeButton().click();
		List<WebElement> eles = driver.findElements(By.xpath("(//td[contains(text(),'"+uerName+"')]//preceding-sibling::td)[8]"));
		if(eles.size() > 0)
		{
			driver.findElement(By.xpath("(//td[contains(text(),'"+uerName+"')]//preceding-sibling::td)[8]")).click();
			driver.switchTo().alert().accept();
			Assert.assertEquals(pmUser.getResponseMessage().getText().trim(), "Remove operation successful for:");
			pmUser.getLogoutLink().click();
		}
	}
	
	
	
	public void deleteUser(WebDriver driver, ExcelReadAndWrite ipData, String[] data, String uerName, int iterations)
	{
		pmMainPage = new PM_Main_Page(driver);
		pmLoginPage = new PM_Login_Page(driver);
		pmUser = new PM_User(driver);
		pmUsers = new PM_Users(driver);
		driver.get(ipData.getData(0, 0));
		driver.get(ipData.getData(0, 0));
		pmLoginPage.PM_Login(data[0], data[1]);
		pmMainPage.getUsers().click();
		pmUsers.getUser().click();
		pmUser.setUserSearchTextBox(uerName);
		pmUser.getOnViewRangeButton().click();
		List<WebElement> eles = driver.findElements(By.xpath("//input[@name='selectItem']"));
		if(eles.size() > 0)
		{
			for(int i=1; i<=iterations; i++)
			{
				driver.findElement(By.xpath("(//input[@name='selectItem'])["+i+"]")).click();
			}
			pmUser.getRemoveSelected().click();
			driver.switchTo().alert().accept();
			Assert.assertEquals(pmUser.getResponseMessage().getText().trim(), "Remove operation successful for:");
			pmUser.getLogoutLink().click();
		}
	}
}
