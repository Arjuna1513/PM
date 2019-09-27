package utilities;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

//import junit.framework.Assert;
import pm_pom_classes.Extension;
import pm_pom_classes.PM_Login_Page;
import pm_pom_classes.PM_Main_Page;
import pm_pom_classes.PM_Services;
import pm_pom_classes.PM_User;
import pm_pom_classes.PM_Users;

public class CleanUP 
{
	public PM_Login_Page pmLoginPage;
	public PM_Main_Page pmMainPage;
	public PM_User pmUser;
	public PM_Users pmUsers;
	public PM_Services pmServices;
	public Extension pmExtension;
	public CleanUP(WebDriver driver)
	{
		pmLoginPage = new PM_Login_Page(driver);
		pmMainPage = new PM_Main_Page(driver);
		pmUser = new PM_User(driver);
		pmUsers = new PM_Users(driver);
		pmServices = new PM_Services(driver);
		pmExtension = new Extension(driver);
		
	}
	
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
//			pmUser.getLogoutLink().click();
		}
		pmUser.getUserSearchTextBox().clear();
		pmUser.setUserSearchTextBox(uerName);
		pmUser.getOnViewRangeButton().click();
		List<WebElement> eles1 = driver.findElements(By.xpath("(//td[contains(text(),'"+uerName+"')])[1]"));
		Assert.assertTrue(eles1.size() == 0);
		pmMainPage.getLogoutLink().click();
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
	
	
	public void deleteMultipleExtensions(WebDriver driver, ExcelReadAndWrite ipData, String[] data, String extNumber, int iterations)
	{
		pmMainPage = new PM_Main_Page(driver);
		pmLoginPage = new PM_Login_Page(driver);
		pmUser = new PM_User(driver);
		pmUsers = new PM_Users(driver);
		driver.get(ipData.getData(0, 0));
		pmLoginPage.PM_Login(data[0], data[1]);
		pmMainPage.getUsers().click();
		pmUsers.getUser().click();
		pmUser.setUserSearchTextBox(extNumber);
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
	
	public void deleteTemplate(WebDriver driver, String methodName, ExcelReadAndWrite loginData, 
			String templateName, ExcelReadAndWrite ipData)
	{
		WebDriverWait wait = new WebDriverWait(driver, 10);
		driver.get(ipData.getData(0, 0));
		String[] credentials = loginData.getData("test_pm_valid_login", 1);
//		String[] testData = pmTests.getData(methodName, 3);
		pmLoginPage.PM_Login(credentials[0], credentials[1]);
		pmMainPage.getServices().click();
		pmServices.getExtension().click();
		pmExtension.getManageTemplates().click();
		driver.findElement(By.xpath("//td[contains(text(),'"+templateName+"')]//preceding-sibling::td[18]")).click();
		driver.switchTo().alert().accept();
		Assert.assertEquals("Remove Template operation successful for:", pmExtension.getResponseMessage());
		pmExtension.getTemplateSubmitButton().click();
		pmMainPage.getLogoutLink().click();
	}
}
