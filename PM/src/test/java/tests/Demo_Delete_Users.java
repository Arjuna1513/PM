package tests;

import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.ConfigClass;
import pm_pom_classes.Extension;
import pm_pom_classes.PM_Login_Page;
import pm_pom_classes.PM_Main_Page;
import pm_pom_classes.PM_System;
import pm_pom_classes.PM_System_DataManagement;
import pm_pom_classes.PM_User;
import pm_pom_classes.PM_Users;
import utilities.CleanUP;
import utilities.ExecuteCommands;
import utilities.ExplicitWait;
import utilities.SelectDropDownValue;
import utilities.Take_Screenshot;
import utilities.UploadFile;

public class Demo_Delete_Users extends ConfigClass
{
	public PM_Login_Page pmLoginPage;
	public PM_Main_Page pmMainPage;
	public PM_User pmUser;
	public PM_Users pmUsers;
	ArrayList<String> list = null;
	WebDriverWait wait = null;
	public Extension pmExtension;
	public PM_System pmSystem;
	public PM_System_DataManagement dataMgmt;
	List<WebElement> elementList;
	
	@Test
	public void test_bulk_user_import_with_existing_extensions_delimiter_semicolon(Method method) throws Exception
	{
		pmLoginPage = new PM_Login_Page(driver);
		pmMainPage = new PM_Main_Page(driver);
		pmUser = new PM_User(driver);
		pmUsers = new PM_Users(driver);
		pmSystem = new PM_System(driver);
		dataMgmt = new PM_System_DataManagement(driver);
		list = new ArrayList<String>();
		elementList = new ArrayList<WebElement>();
		wait = new WebDriverWait(driver, 100);
				
		String[] credentials;
		String[] testData;
		
			pmTests.checkTestStatus(method.getName());
			credentials = loginData.getData("test_pm_valid_login", 1);
			driver.get(ipData.getData(0, 0));
			pmLoginPage.PM_Login(credentials[0], credentials[1]);
			pmMainPage.getUsers().click();
			pmUsers.getUser().click();
			new SelectDropDownValue().selectByValue(pmUser.getViewExtensionsLimit(), "10");
			pmUser.setUserSearchTextBox("Cisco");
			pmUser.getOnViewRangeButton().click();
//			List<WebElement> eleList;
//			eleList = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//input[@name='selectItem']"));
			while(new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//input[@name='selectItem']")).size()>0)
			{
				List<WebElement> eles;
				eles = driver.findElements(By.name("selectItem"));
				if(eles.size() > 0)
				{
						driver.findElement(By.name("selectItemAll")).click();
						pmUser.getRemoveSelected().click();
						driver.switchTo().alert().accept();
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("blockUI")));
						wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("blockUI")));
						wait.until(ExpectedConditions.presenceOfElementLocated(By.className("responseMessage")));
//						Assert.assertEquals(pmUser.getResponseMessage().getText().trim(), "Remove operation successful for:");
					
				}
			}
		}		

}
