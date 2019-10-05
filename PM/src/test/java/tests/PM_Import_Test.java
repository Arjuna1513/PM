package tests;

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
import utilities.PM_Data_backUp;
import utilities.ReusableUnits;
import utilities.SelectDropDownValue;
import utilities.Take_Screenshot;
import utilities.UploadFile;

public class PM_Import_Test extends ConfigClass
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
	
	@Test
	public void test_bulk_user_import_with_existing_extensions(Method method) throws Exception
	{
		pmLoginPage = new PM_Login_Page(driver);
		pmMainPage = new PM_Main_Page(driver);
		pmUser = new PM_User(driver);
		pmUsers = new PM_Users(driver);
		pmSystem = new PM_System(driver);
		dataMgmt = new PM_System_DataManagement(driver);
		list = new ArrayList<String>();
		wait = new WebDriverWait(driver, 20);
				
		String[] credentials;
		String[] testData;
//		String[] extData;
		
		try 
		{
			pmTests.checkTestStatus(method.getName());
			credentials = loginData.getData("test_pm_valid_login", 1);
			testData = pmTests.getData(method.getName(), 1);
			new UploadFile().generate_BulkFile_For_Existing_Extension(testData[3], ";",1000, testData[4]);
			Thread.sleep(3000);
			
			
			list.add(testData[0]);
			list.add(testData[1]);
			list.add(testData[2]);
			
			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
			driver.get(ipData.getData(0, 0));
			System.out.println(testData);
			pmLoginPage.PM_Login(credentials[0], credentials[1]);
			pmMainPage.getSystem().click();
			pmSystem.getDataManagement().click();
			dataMgmt.getBackUpRestoreTab().click();
			
			//delete the existing backUpFile
			List<WebElement> list = driver.findElements(By.xpath("//img[contains(@id,'removeThis')]"));
			if(list.size() > 0)
			{
				for(WebElement element : list)
				{
					wait.until(ExpectedConditions.elementToBeClickable(element));
					element.click();
					driver.switchTo().alert().accept();
					Assert.assertEquals(dataMgmt.getResponseMsg().getText().trim(), "Remove operation successful for:");
				}
			}
			
			//deleting existing import record if present
			dataMgmt.getImportTabButton().click();
			list = driver.findElements(By.xpath("//img[contains(@id,'removeThis')]"));
			if(list.size() > 0)
			{
				for(WebElement element : list)
				{
					wait.until(ExpectedConditions.elementToBeClickable(element));
					element.click();
					driver.switchTo().alert().accept();
					Assert.assertEquals(dataMgmt.getResponseMsg().getText().trim(), "Remove operation successful for:");
				}
			}
			
			//taking a new backUp of pm data
			dataMgmt.getBackUpRestoreTab().click();
			dataMgmt.getBackUpButton().click();
			dataMgmt.getApplyButtonBottom().click();
			
			list = driver.findElements(By.xpath("//img[contains(@id,'removeThis')]"));
			Assert.assertTrue(list.size() == 1);
			
			
//			new CleanUP(driver).delete_existing_backUps(driver, ipData, loginData);
//			new PM_Data_backUp(driver).take_pm_data_backUp(driver, ipData, loginData);
//			
			
			dataMgmt.getImportTabButton().click();
			dataMgmt.getOnImportButton().click();
			dataMgmt.getNextButton().click();
			dataMgmt.getUserRadioButton().click();
			dataMgmt.getuserBrowseButton().sendKeys("C:\\Users\\mallikar\\git\\PM\\PM\\BulkFile.csv");
			new SelectDropDownValue().selectByValue(dataMgmt.getDelimiterDropDown(),";");
			dataMgmt.getNextButton().click();
			
			List<WebElement> eles = driver.findElements(By.xpath("//select[contains(@id,'mySelectedMPFields')]"));
			Assert.assertTrue(eles.size() == 10);
			new SelectDropDownValue().selectByValue(driver.findElement(By.id("mySelectedMPFields[0]")), "First Name");
			new SelectDropDownValue().selectByValue(driver.findElement(By.id("mySelectedMPFields[1]")), "Alternate First Names");
			new SelectDropDownValue().selectByValue(driver.findElement(By.id("mySelectedMPFields[2]")), "Last Name");
			new SelectDropDownValue().selectByValue(driver.findElement(By.id("mySelectedMPFields[3]")), "Alternate Last Names");
			new SelectDropDownValue().selectByValue(driver.findElement(By.id("mySelectedMPFields[4]")), "Extension");
			new SelectDropDownValue().selectByValue(driver.findElement(By.id("mySelectedMPFields[5]")), "Department");
			new SelectDropDownValue().selectByValue(driver.findElement(By.id("mySelectedMPFields[6]")), "Keywords");
			new SelectDropDownValue().selectByValue(driver.findElement(By.id("mySelectedMPFields[7]")), "User Id");
			new SelectDropDownValue().selectByValue(driver.findElement(By.id("mySelectedMPFields[8]")), "User Password");
			new SelectDropDownValue().selectByValue(driver.findElement(By.id("mySelectedMPFields[9]")), "Email");
			
			dataMgmt.getApplyButton().click();
			Assert.assertEquals(dataMgmt.getResponseMsg().getText().trim(), "Import operation successful for:");
			
			dataMgmt.getDoneButton().click();
			String extCount = driver.findElement(By.xpath("(//td[text()='CSV file']//following-sibling::td/a)[1]")).getText().trim();
			int count = Integer.parseInt(extCount);
			System.out.println("Total Number of Records in Import Summary Page :"+ count);
			Assert.assertTrue(count == 1000);
			pmMainPage.getUsers().click();
			pmUsers.getUser().click();
			new SelectDropDownValue().selectByValue(pmUser.getViewExtensionsLimit(), "200");
			pmUser.setUserSearchTextBox(testData[4]);
			pmUser.getOnViewRangeButton().click();
			List<WebElement> pagingNumber = new Select(pmUser.getResultsPagingDropDown()).getOptions();
			System.out.println("Number of result pages are :"+ pagingNumber.size());
			Assert.assertTrue(pagingNumber.size() == 1000/200);
			for(int i=1; i<=pagingNumber.size(); i++)
			{
				new SelectDropDownValue().selectByValue(pmUser.getResultsPagingDropDown(), ""+i);
				pmUser.getUsersResultsPagingGoButton().click();
				List<WebElement> elements = driver.findElements(By.xpath("//td[contains(text(),'"+testData[4]+"')]//following-sibling::td[contains(text(),'"+testData[3].substring(0, 16)+"')]"));
				Assert.assertTrue(elements.size() == 200);
				Thread.sleep(2000);
			}
			
			pmMainPage.getSystem().click();
			pmSystem.getDataManagement().click();
			dataMgmt.getBackUpRestoreTab().click();
			
			list = driver.findElements(By.xpath("//img[contains(@id,'removeThis')]"));
			Assert.assertTrue(list.size() == 1);
			
			driver.findElement(By.id("restoreThis0_img")).click();
			driver.switchTo().alert().accept();
			Assert.assertEquals(dataMgmt.getResponseMsg().getText().trim(), "Restore operation successful for:");
			
			pmMainPage.getUsers().click();
			pmUsers.getUser().click();
			new SelectDropDownValue().selectByValue(pmUser.getViewExtensionsLimit(), "200");
			pmUser.setUserSearchTextBox(testData[4]);
			pmUser.getOnViewRangeButton().click();
			
			String noDataMsg = driver.findElement(By.xpath("//b[contains(text(),'No data found')]")).getText().trim();
			Assert.assertEquals(noDataMsg, "No data found");
			pmMainPage.getLogoutLink().click();
		}
		catch(Exception e)
		{
				new Take_Screenshot().get_Screenshot(driver, method.getName());
				throw e;
		}
		finally
		{
			credentials = loginData.getData("test_pm_valid_login", 1);
			testData = pmTests.getData(method.getName(), 1);
			list.clear();
			list.add(testData[5]);
			list.add(testData[6]);
			list.add(testData[7]);
			
			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
			new CleanUP(driver).delete_import_summary_record(driver, ipData, loginData);
			new CleanUP(driver).delete_existing_backUps(driver, ipData, loginData);
//			new PM_Data_backUp(driver).restore_pm_data_backUp(driver, ipData, loginData);
		}
		
	}
}
