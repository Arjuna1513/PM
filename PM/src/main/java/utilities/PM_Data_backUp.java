package utilities;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import pm_pom_classes.Extension;
import pm_pom_classes.PM_Login_Page;
import pm_pom_classes.PM_Main_Page;
import pm_pom_classes.PM_Services;
import pm_pom_classes.PM_System;
import pm_pom_classes.PM_System_DataManagement;
import pm_pom_classes.PM_User;
import pm_pom_classes.PM_Users;

public class PM_Data_backUp 
{
	public PM_Login_Page pmLoginPage;
	public PM_Main_Page pmMainPage;
	public PM_User pmUser;
	public PM_Users pmUsers;
	public PM_Services pmServices;
	public Extension pmExtension;
	public PM_System pmSystem;
	public PM_System_DataManagement dataMgmt;
	
	public PM_Data_backUp(WebDriver driver)
	{
		pmLoginPage = new PM_Login_Page(driver);
		pmMainPage = new PM_Main_Page(driver);
		pmUser = new PM_User(driver);
		pmUsers = new PM_Users(driver);
		pmServices = new PM_Services(driver);
		pmExtension = new Extension(driver);
		pmSystem = new PM_System(driver);
		dataMgmt = new PM_System_DataManagement(driver);
	}
	
	public void take_pm_data_backUp(WebDriver driver, ExcelReadAndWrite ipData, ExcelReadAndWrite loginData) throws InterruptedException
	{
		List<WebElement> list;
		String[] credentials = loginData.getData("test_pm_valid_login", 1);
		driver.get(ipData.getData(0, 0));
		pmLoginPage.PM_Login(credentials[0], credentials[1]);
		pmMainPage.getSystem().click();
		pmSystem.getDataManagement().click();
		dataMgmt.getBackUpRestoreTab().click();
		
		dataMgmt.getBackUpButton().click();
		dataMgmt.getApplyButtonBottom().click();
		
		list = driver.findElements(By.xpath("//img[contains(@id,'removeThis')]"));
		Assert.assertTrue(list.size() == 1);
		pmMainPage.getLogoutLink().click();
	}
	
	
	public void restore_pm_data_backUp(WebDriver driver, ExcelReadAndWrite ipData, ExcelReadAndWrite loginData) throws InterruptedException
	{
		List<WebElement> list;
		String[] credentials = loginData.getData("test_pm_valid_login", 1);
		driver.get(ipData.getData(0, 0));
		pmLoginPage.PM_Login(credentials[0], credentials[1]);
		pmMainPage.getSystem().click();
		pmSystem.getDataManagement().click();
		dataMgmt.getBackUpRestoreTab().click();
		
		list = driver.findElements(By.xpath("//img[contains(@id,'removeThis')]"));
		Assert.assertTrue(list.size() == 1);
		
		driver.findElement(By.id("restoreThis0_img")).click();
		driver.switchTo().alert().accept();
		Assert.assertEquals(dataMgmt.getResponseMsg().getText().trim(), "Restore operation successful for:");
		pmMainPage.getLogoutLink().click();
	}
}
