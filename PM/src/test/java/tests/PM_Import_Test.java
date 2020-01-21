package tests;

import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
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
		wait = new WebDriverWait(driver, 20);
				
		String[] credentials;
		String[] testData;
		int count = 0;
		
		try 
		{
			pmTests.checkTestStatus(method.getName());
			credentials = loginData.getData("test_pm_valid_login", 1);
			testData = pmTests.getData(method.getName(), 1);
			new UploadFile().generate_BulkFile_For_Existing_Extension(testData[3], ";",Integer.parseInt(testData[8]), testData[4]);
			Thread.sleep(3000);
//			new ReusableUnits(driver).createIPTemplate(driver, method.getName(), ipData, loginData, "IPTemp");
			
			int expExtCount = Integer.parseInt(testData[8]);
			
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
			elementList.clear();
			elementList = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//img[contains(@id,'removeThis')]"));
			if(elementList.size() > 0)
			{
				for(WebElement element : elementList)
				{
					wait.until(ExpectedConditions.elementToBeClickable(element));
					element.click();
					driver.switchTo().alert().accept();
					Assert.assertEquals(dataMgmt.getResponseMsg().getText().trim(), "Remove operation successful for:");
				}
			}
			
			//deleting existing import record if present
			elementList.clear();
			dataMgmt.getImportTabButton().click();
			elementList = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//img[contains(@id,'removeThis')]"));
			if(elementList.size() > 0)
			{
				for(WebElement element : elementList)
				{
					wait.until(ExpectedConditions.elementToBeClickable(element));
					element.click();
					driver.switchTo().alert().accept();
//					Assert.assertEquals(dataMgmt.getResponseMsg().getText().trim(), "Remove operation successful for:");
				}
			}
			
			//taking a new backUp of pm data
			dataMgmt.getBackUpRestoreTab().click();
			Thread.sleep(1000);
//			wait.until(ExpectedConditions.elementToBeClickable(dataMgmt.getBackUpButton()));
			dataMgmt.getBackUpButton().click();
			dataMgmt.getApplyButtonBottom().click();
			
			elementList.clear();
			elementList = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//img[contains(@id,'removeThis')]"));
			Assert.assertTrue(elementList.size() == 1);
			
			dataMgmt.getImportTabButton().click();
			dataMgmt.getOnImportButton().click();
			dataMgmt.getNextButton().click();
			dataMgmt.getUserRadioButton().click();
			dataMgmt.getuserBrowseButton().sendKeys(System.getProperty("user.dir")+"\\BulkFile.csv");
			new SelectDropDownValue().selectByValue(dataMgmt.getDelimiterDropDown(),";");
			dataMgmt.getNextButton().click();
			
			elementList.clear();
			elementList = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//select[contains(@id,'mySelectedMPFields')]"));
			Assert.assertTrue(elementList.size() == 10);
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
			
//			dataMgmt.getApplyButton().click();
//			wait.until(ExpectedConditions.textToBe(By.className("responseMessage"), "  Import operation successful for:"));
//			Assert.assertEquals(dataMgmt.getResponseMsg().getText().trim(), "Import operation successful for:");
			new UploadFile().wait_till_successful_import(dataMgmt, driver);
			
			dataMgmt.getDoneButton().click();
			String extCount = driver.findElement(By.xpath("(//td[text()='CSV file']//following-sibling::td/a)[1]")).getText().trim();
			count = Integer.parseInt(extCount);
			System.out.println("Total Number of Records in Import Summary Page :"+ count);
			Assert.assertTrue(count == expExtCount);
			pmMainPage.getUsers().click();
			pmUsers.getUser().click();
			pmUser.setUserSearchTextBox(testData[4]);
			if(count <= 200)
			{
				new SelectDropDownValue().selectByValue(pmUser.getViewExtensionsLimit(), "200");
				pmUser.getOnViewRangeButton().click();
//				List<WebElement> pagingNumber = new Select(pmUser.getResultsPagingDropDown()).getOptions();
//				System.out.println("Number of result pages are :"+ pagingNumber.size());
//				Assert.assertTrue(pagingNumber.size() == 100/100);
//				for(int i=1; i<=pagingNumber.size(); i++)
//				{
//					new SelectDropDownValue().selectByValue(pmUser.getResultsPagingDropDown(), ""+i);
//					pmUser.getUsersResultsPagingGoButton().click();
					List<WebElement> elements = driver.findElements(By.xpath("//td[contains(text(),'"+testData[4]+"')]//following-sibling::td[contains(text(),'"+testData[3].substring(0, 16)+"')]"));
					Assert.assertTrue(elements.size() == count);
					Thread.sleep(2000);
//				}
			}
			else
			{
				int sum = 0;
				new SelectDropDownValue().selectByValue(pmUser.getViewExtensionsLimit(), "200");
				pmUser.getOnViewRangeButton().click();
				List<WebElement> pagingNumber = new Select(pmUser.getResultsPagingDropDown()).getOptions();
				System.out.println("Number of result pages are :"+ pagingNumber.size());
				
//				int quotient = count/10;
//				int remainder = count %10;
				
//				Assert.assertTrue(pagingNumber.size() == count/10);
				for(int i=1; i<=pagingNumber.size(); i++)
				{
					new SelectDropDownValue().selectByValue(pmUser.getResultsPagingDropDown(), ""+i);
					pmUser.getUsersResultsPagingGoButton().click();
					List<WebElement> elements = driver.findElements(By.xpath("//td[contains(text(),'"+testData[4]+"')]//following-sibling::td[contains(text(),'"+testData[3].substring(0, 16)+"')]"));
					Assert.assertTrue(elements.size() == 10);
					sum = sum + elements.size();
					Thread.sleep(2000);
				}
				Assert.assertTrue(sum == count);
			}
			pmMainPage.getSystem().click();
			pmSystem.getDataManagement().click();
			dataMgmt.getBackUpRestoreTab().click();
			
			elementList.clear();
			elementList = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//img[contains(@id,'removeThis')]"));
			Assert.assertTrue(elementList.size() == 1);
			
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
		catch(Error e)
		{
				new Take_Screenshot().get_Screenshot(driver, method.getName());
				throw e;
		}
		catch(Exception e)
		{
				new Take_Screenshot().get_Screenshot(driver, method.getName());
				throw e;
		}
		finally
		{
			File file = new File("./BulkFile.csv");
		    if (file.exists()) 
		    {
		      file.delete();
		    }
			credentials = loginData.getData("test_pm_valid_login", 1);
			testData = pmTests.getData(method.getName(), 1);
			list.clear();
			list.add(testData[5]);
			list.add(testData[6]);
			list.add(testData[7]);
			
			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
			new CleanUP(driver).delete_import_summary_record(driver, ipData, loginData);
			new CleanUP(driver).delete_existing_backUps(driver, ipData, loginData);
//			new CleanUP(driver).deleteTemplate(driver, method.getName(), loginData, "IPTemp", ipData);
			new CleanUP(driver).delete_Users_With_10Users_for_Each_click(driver, ipData, credentials, testData[4], count);
		}
		
	}
	
	
/*	@Test
	public void test_bulk_user_import_with_existing_extensions_delimiter_space(Method method) throws Exception
	{
		pmLoginPage = new PM_Login_Page(driver);
		pmMainPage = new PM_Main_Page(driver);
		pmUser = new PM_User(driver);
		pmUsers = new PM_Users(driver);
		pmSystem = new PM_System(driver);
		dataMgmt = new PM_System_DataManagement(driver);
		list = new ArrayList<String>();
		elementList = new ArrayList<WebElement>();
		wait = new WebDriverWait(driver, 20);
				
		String[] credentials;
		String[] testData;
		int count = 0;
		
		try 
		{
			pmTests.checkTestStatus(method.getName());
			credentials = loginData.getData("test_pm_valid_login", 1);
			testData = pmTests.getData(method.getName(), 1);
			new UploadFile().generate_BulkFile_For_Existing_Extension(testData[3], " ",Integer.parseInt(testData[8]), testData[4]);
			Thread.sleep(3000);
//			new ReusableUnits(driver).createIPTemplate(driver, method.getName(), ipData, loginData, "IPTemp");
			
			int expExtCount = Integer.parseInt(testData[8]);
			
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
			elementList.clear();
			elementList = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//img[contains(@id,'removeThis')]"));
			if(elementList.size() > 0)
			{
				for(WebElement element : elementList)
				{
					wait.until(ExpectedConditions.elementToBeClickable(element));
					element.click();
					driver.switchTo().alert().accept();
					Assert.assertEquals(dataMgmt.getResponseMsg().getText().trim(), "Remove operation successful for:");
				}
			}
			
			//deleting existing import record if present
			elementList.clear();
			dataMgmt.getImportTabButton().click();
			elementList = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//img[contains(@id,'removeThis')]"));
			if(elementList.size() > 0)
			{
				for(WebElement element : elementList)
				{
					wait.until(ExpectedConditions.elementToBeClickable(element));
					element.click();
					driver.switchTo().alert().accept();
//					Assert.assertEquals(dataMgmt.getResponseMsg().getText().trim(), "Remove operation successful for:");
				}
			}
			
			//taking a new backUp of pm data
			dataMgmt.getBackUpRestoreTab().click();
			Thread.sleep(1000);
//			wait.until(ExpectedConditions.elementToBeClickable(dataMgmt.getBackUpButton()));
			dataMgmt.getBackUpButton().click();
			dataMgmt.getApplyButtonBottom().click();
			
			elementList.clear();
			elementList = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//img[contains(@id,'removeThis')]"));
			Assert.assertTrue(elementList.size() == 1);
			
			dataMgmt.getImportTabButton().click();
			dataMgmt.getOnImportButton().click();
			dataMgmt.getNextButton().click();
			dataMgmt.getUserRadioButton().click();
			dataMgmt.getuserBrowseButton().sendKeys(System.getProperty("user.dir")+"\\BulkFile.csv");
			new SelectDropDownValue().selectByValue(dataMgmt.getDelimiterDropDown()," ");
			dataMgmt.getNextButton().click();
			
			elementList.clear();
			elementList = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//select[contains(@id,'mySelectedMPFields')]"));
			Assert.assertTrue(elementList.size() == 10);
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
			
//			dataMgmt.getApplyButton().click();
//			wait.until(ExpectedConditions.textToBe(By.className("responseMessage"), "  Import operation successful for:"));
//			Assert.assertEquals(dataMgmt.getResponseMsg().getText().trim(), "Import operation successful for:");
			new UploadFile().wait_till_successful_import(dataMgmt, driver);
			
			dataMgmt.getDoneButton().click();
			String extCount = driver.findElement(By.xpath("(//td[text()='CSV file']//following-sibling::td/a)[1]")).getText().trim();
			count = Integer.parseInt(extCount);
			System.out.println("Total Number of Records in Import Summary Page :"+ count);
			Assert.assertTrue(count == expExtCount);
			pmMainPage.getUsers().click();
			pmUsers.getUser().click();
			pmUser.setUserSearchTextBox(testData[4]);
			if(count <= 200)
			{
				new SelectDropDownValue().selectByValue(pmUser.getViewExtensionsLimit(), "200");
				pmUser.getOnViewRangeButton().click();
//				List<WebElement> pagingNumber = new Select(pmUser.getResultsPagingDropDown()).getOptions();
//				System.out.println("Number of result pages are :"+ pagingNumber.size());
//				Assert.assertTrue(pagingNumber.size() == 100/100);
//				for(int i=1; i<=pagingNumber.size(); i++)
//				{
//					new SelectDropDownValue().selectByValue(pmUser.getResultsPagingDropDown(), ""+i);
//					pmUser.getUsersResultsPagingGoButton().click();
					List<WebElement> elements = driver.findElements(By.xpath("//td[contains(text(),'"+testData[4]+"')]//following-sibling::td[contains(text(),'"+testData[3].substring(0, 16)+"')]"));
					Assert.assertTrue(elements.size() == count);
					Thread.sleep(2000);
//				}
			}
			else
			{
				int sum = 0;
				new SelectDropDownValue().selectByValue(pmUser.getViewExtensionsLimit(), "200");
				pmUser.getOnViewRangeButton().click();
				List<WebElement> pagingNumber = new Select(pmUser.getResultsPagingDropDown()).getOptions();
				System.out.println("Number of result pages are :"+ pagingNumber.size());
				
//				int quotient = count/10;
//				int remainder = count %10;
				
//				Assert.assertTrue(pagingNumber.size() == count/10);
				for(int i=1; i<=pagingNumber.size(); i++)
				{
					new SelectDropDownValue().selectByValue(pmUser.getResultsPagingDropDown(), ""+i);
					pmUser.getUsersResultsPagingGoButton().click();
					List<WebElement> elements = driver.findElements(By.xpath("//td[contains(text(),'"+testData[4]+"')]//following-sibling::td[contains(text(),'"+testData[3].substring(0, 16)+"')]"));
					Assert.assertTrue(elements.size() == 10);
					sum = sum + elements.size();
					Thread.sleep(2000);
				}
				Assert.assertTrue(sum == count);
			}
			pmMainPage.getSystem().click();
			pmSystem.getDataManagement().click();
			dataMgmt.getBackUpRestoreTab().click();
			
			elementList.clear();
			elementList = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//img[contains(@id,'removeThis')]"));
			Assert.assertTrue(elementList.size() == 1);
			
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
		catch(Error e)
		{
				new Take_Screenshot().get_Screenshot(driver, method.getName());
				throw e;
		}
		catch(Exception e)
		{
				new Take_Screenshot().get_Screenshot(driver, method.getName());
				throw e;
		}
		finally
		{
			File file = new File("./BulkFile.csv");
		    if (file.exists()) 
		    {
		      file.delete();
		    }
			credentials = loginData.getData("test_pm_valid_login", 1);
			testData = pmTests.getData(method.getName(), 1);
			list.clear();
			list.add(testData[5]);
			list.add(testData[6]);
			list.add(testData[7]);
			
			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
			new CleanUP(driver).delete_import_summary_record(driver, ipData, loginData);
			new CleanUP(driver).delete_existing_backUps(driver, ipData, loginData);
//			new CleanUP(driver).deleteTemplate(driver, method.getName(), loginData, "IPTemp", ipData);
			new CleanUP(driver).delete_Users_With_10Users_for_Each_click(driver, ipData, credentials, testData[4], count);
		}
		
	}
	
	
	@Test
	public void test_bulk_user_import_with_existing_extensions_delimiter_comma(Method method) throws Exception
	{
		pmLoginPage = new PM_Login_Page(driver);
		pmMainPage = new PM_Main_Page(driver);
		pmUser = new PM_User(driver);
		pmUsers = new PM_Users(driver);
		pmSystem = new PM_System(driver);
		dataMgmt = new PM_System_DataManagement(driver);
		list = new ArrayList<String>();
		elementList = new ArrayList<WebElement>();
		wait = new WebDriverWait(driver, 20);
				
		String[] credentials;
		String[] testData;
		int count = 0;
		
		try 
		{
			pmTests.checkTestStatus(method.getName());
			credentials = loginData.getData("test_pm_valid_login", 1);
			testData = pmTests.getData(method.getName(), 1);
			new UploadFile().generate_BulkFile_For_Existing_Extension(testData[3], ",",Integer.parseInt(testData[8]), testData[4]);
			Thread.sleep(3000);
//			new ReusableUnits(driver).createIPTemplate(driver, method.getName(), ipData, loginData, "IPTemp");
			
			int expExtCount = Integer.parseInt(testData[8]);
			
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
			elementList.clear();
			elementList = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//img[contains(@id,'removeThis')]"));
			if(elementList.size() > 0)
			{
				for(WebElement element : elementList)
				{
					wait.until(ExpectedConditions.elementToBeClickable(element));
					element.click();
					driver.switchTo().alert().accept();
					Assert.assertEquals(dataMgmt.getResponseMsg().getText().trim(), "Remove operation successful for:");
				}
			}
			
			//deleting existing import record if present
			elementList.clear();
			dataMgmt.getImportTabButton().click();
			elementList = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//img[contains(@id,'removeThis')]"));
			if(elementList.size() > 0)
			{
				for(WebElement element : elementList)
				{
					wait.until(ExpectedConditions.elementToBeClickable(element));
					element.click();
					driver.switchTo().alert().accept();
//					Assert.assertEquals(dataMgmt.getResponseMsg().getText().trim(), "Remove operation successful for:");
				}
			}
			
			//taking a new backUp of pm data
			dataMgmt.getBackUpRestoreTab().click();
			Thread.sleep(1000);
//			wait.until(ExpectedConditions.elementToBeClickable(dataMgmt.getBackUpButton()));
			dataMgmt.getBackUpButton().click();
			dataMgmt.getApplyButtonBottom().click();
			
			elementList.clear();
			elementList = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//img[contains(@id,'removeThis')]"));
			Assert.assertTrue(elementList.size() == 1);
			
			dataMgmt.getImportTabButton().click();
			dataMgmt.getOnImportButton().click();
			dataMgmt.getNextButton().click();
			dataMgmt.getUserRadioButton().click();
			dataMgmt.getuserBrowseButton().sendKeys(System.getProperty("user.dir")+"\\BulkFile.csv");
			new SelectDropDownValue().selectByValue(dataMgmt.getDelimiterDropDown(),",");
			dataMgmt.getNextButton().click();
			
			elementList.clear();
			elementList = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//select[contains(@id,'mySelectedMPFields')]"));
			Assert.assertTrue(elementList.size() == 10);
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
			
//			dataMgmt.getApplyButton().click();
//			wait.until(ExpectedConditions.textToBe(By.className("responseMessage"), "  Import operation successful for:"));
//			Assert.assertEquals(dataMgmt.getResponseMsg().getText().trim(), "Import operation successful for:");
			new UploadFile().wait_till_successful_import(dataMgmt, driver);
			
			dataMgmt.getDoneButton().click();
			String extCount = driver.findElement(By.xpath("(//td[text()='CSV file']//following-sibling::td/a)[1]")).getText().trim();
			count = Integer.parseInt(extCount);
			System.out.println("Total Number of Records in Import Summary Page :"+ count);
			Assert.assertTrue(count == expExtCount);
			pmMainPage.getUsers().click();
			pmUsers.getUser().click();
			pmUser.setUserSearchTextBox(testData[4]);
			if(count <= 200)
			{
				new SelectDropDownValue().selectByValue(pmUser.getViewExtensionsLimit(), "200");
				pmUser.getOnViewRangeButton().click();
//				List<WebElement> pagingNumber = new Select(pmUser.getResultsPagingDropDown()).getOptions();
//				System.out.println("Number of result pages are :"+ pagingNumber.size());
//				Assert.assertTrue(pagingNumber.size() == 100/100);
//				for(int i=1; i<=pagingNumber.size(); i++)
//				{
//					new SelectDropDownValue().selectByValue(pmUser.getResultsPagingDropDown(), ""+i);
//					pmUser.getUsersResultsPagingGoButton().click();
					List<WebElement> elements = driver.findElements(By.xpath("//td[contains(text(),'"+testData[4]+"')]//following-sibling::td[contains(text(),'"+testData[3].substring(0, 16)+"')]"));
					Assert.assertTrue(elements.size() == count);
					Thread.sleep(2000);
//				}
			}
			else
			{
				int sum = 0;
				new SelectDropDownValue().selectByValue(pmUser.getViewExtensionsLimit(), "200");
				pmUser.getOnViewRangeButton().click();
				List<WebElement> pagingNumber = new Select(pmUser.getResultsPagingDropDown()).getOptions();
				System.out.println("Number of result pages are :"+ pagingNumber.size());
				
//				int quotient = count/10;
//				int remainder = count %10;
				
//				Assert.assertTrue(pagingNumber.size() == count/10);
				for(int i=1; i<=pagingNumber.size(); i++)
				{
					new SelectDropDownValue().selectByValue(pmUser.getResultsPagingDropDown(), ""+i);
					pmUser.getUsersResultsPagingGoButton().click();
					List<WebElement> elements = driver.findElements(By.xpath("//td[contains(text(),'"+testData[4]+"')]//following-sibling::td[contains(text(),'"+testData[3].substring(0, 16)+"')]"));
					Assert.assertTrue(elements.size() == 10);
					sum = sum + elements.size();
					Thread.sleep(2000);
				}
				Assert.assertTrue(sum == count);
			}
			pmMainPage.getSystem().click();
			pmSystem.getDataManagement().click();
			dataMgmt.getBackUpRestoreTab().click();
			
			elementList.clear();
			elementList = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//img[contains(@id,'removeThis')]"));
			Assert.assertTrue(elementList.size() == 1);
			
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
		catch(Error e)
		{
				new Take_Screenshot().get_Screenshot(driver, method.getName());
				throw e;
		}
		catch(Exception e)
		{
				new Take_Screenshot().get_Screenshot(driver, method.getName());
				throw e;
		}
		finally
		{
			File file = new File("./BulkFile.csv");
		    if (file.exists()) 
		    {
		      file.delete();
		    }
			credentials = loginData.getData("test_pm_valid_login", 1);
			testData = pmTests.getData(method.getName(), 1);
			list.clear();
			list.add(testData[5]);
			list.add(testData[6]);
			list.add(testData[7]);
			
			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
			new CleanUP(driver).delete_import_summary_record(driver, ipData, loginData);
			new CleanUP(driver).delete_existing_backUps(driver, ipData, loginData);
//			new CleanUP(driver).deleteTemplate(driver, method.getName(), loginData, "IPTemp", ipData);
			new CleanUP(driver).delete_Users_With_10Users_for_Each_click(driver, ipData, credentials, testData[4], count);
		}
		
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	@Test
	public void test_bulk_user_import_with_NEW_extensions_delimiter_semicolon(Method method) throws Exception
	{
		pmLoginPage = new PM_Login_Page(driver);
		pmMainPage = new PM_Main_Page(driver);
		pmUser = new PM_User(driver);
		pmUsers = new PM_Users(driver);
		pmSystem = new PM_System(driver);
		dataMgmt = new PM_System_DataManagement(driver);
		list = new ArrayList<String>();
		elementList = new ArrayList<WebElement>();
		wait = new WebDriverWait(driver, 20);
				
		String[] credentials;
		String[] testData;
		int count = 0;
		
		try 
		{
			pmTests.checkTestStatus(method.getName());
			credentials = loginData.getData("test_pm_valid_login", 1);
			testData = pmTests.getData(method.getName(), 1);
			new UploadFile().generate_BulkFile_For_NEW_Extension(testData[3], ";",Integer.parseInt(testData[8]), testData[4], "IPTemp");
			Thread.sleep(3000);
			new ReusableUnits(driver).createIPTemplate(driver, method.getName(), ipData, loginData, "IPTemp");
			
			int expExtCount = Integer.parseInt(testData[8]);
			
			list.add(testData[0]);
//			list.add(testData[1]);
//			list.add(testData[2]);
			
			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
			driver.get(ipData.getData(0, 0));
			System.out.println(testData);
			pmLoginPage.PM_Login(credentials[0], credentials[1]);
			pmMainPage.getSystem().click();
			pmSystem.getDataManagement().click();
			dataMgmt.getBackUpRestoreTab().click();
			
			//delete the existing backUpFile
			elementList.clear();
			elementList = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//img[contains(@id,'removeThis')]"));
			if(elementList.size() > 0)
			{
				for(WebElement element : elementList)
				{
					wait.until(ExpectedConditions.elementToBeClickable(element));
					element.click();
					driver.switchTo().alert().accept();
					Assert.assertEquals(dataMgmt.getResponseMsg().getText().trim(), "Remove operation successful for:");
				}
			}
			
			//deleting existing import record if present
			elementList.clear();
			dataMgmt.getImportTabButton().click();
			elementList = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//img[contains(@id,'removeThis')]"));
			if(elementList.size() > 0)
			{
				for(WebElement element : elementList)
				{
					wait.until(ExpectedConditions.elementToBeClickable(element));
					element.click();
					driver.switchTo().alert().accept();
//					Assert.assertEquals(dataMgmt.getResponseMsg().getText().trim(), "Remove operation successful for:");
				}
			}
			
			//taking a new backUp of pm data
			dataMgmt.getBackUpRestoreTab().click();
			Thread.sleep(1000);
//			wait.until(ExpectedConditions.elementToBeClickable(dataMgmt.getBackUpButton()));
			dataMgmt.getBackUpButton().click();
			dataMgmt.getApplyButtonBottom().click();
			
			elementList.clear();
			elementList = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//img[contains(@id,'removeThis')]"));
			Assert.assertTrue(elementList.size() == 1);
			
			dataMgmt.getImportTabButton().click();
			dataMgmt.getOnImportButton().click();
			dataMgmt.getNextButton().click();
			dataMgmt.getUserRadioButton().click();
			dataMgmt.getuserBrowseButton().sendKeys(System.getProperty("user.dir")+"\\BulkFile.csv");
			new SelectDropDownValue().selectByValue(dataMgmt.getDelimiterDropDown(),";");
			dataMgmt.getNextButton().click();
			
			elementList.clear();
			elementList = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//select[contains(@id,'mySelectedMPFields')]"));
			Assert.assertTrue(elementList.size() == 10);
			new SelectDropDownValue().selectByValue(driver.findElement(By.id("mySelectedMPFields[0]")), "First Name");
			new SelectDropDownValue().selectByValue(driver.findElement(By.id("mySelectedMPFields[1]")), "Alternate First Names");
			new SelectDropDownValue().selectByValue(driver.findElement(By.id("mySelectedMPFields[2]")), "Last Name");
			new SelectDropDownValue().selectByValue(driver.findElement(By.id("mySelectedMPFields[3]")), "Alternate Last Names");
			new SelectDropDownValue().selectByValue(driver.findElement(By.id("mySelectedMPFields[4]")), "Extension template");
			new SelectDropDownValue().selectByValue(driver.findElement(By.id("mySelectedMPFields[5]")), "Department");
			new SelectDropDownValue().selectByValue(driver.findElement(By.id("mySelectedMPFields[6]")), "Keywords");
			new SelectDropDownValue().selectByValue(driver.findElement(By.id("mySelectedMPFields[7]")), "User Id");
			new SelectDropDownValue().selectByValue(driver.findElement(By.id("mySelectedMPFields[8]")), "User Password");
			new SelectDropDownValue().selectByValue(driver.findElement(By.id("mySelectedMPFields[9]")), "Email");
			
//			dataMgmt.getApplyButton().click();
//			wait.until(ExpectedConditions.textToBe(By.className("responseMessage"), "  Import operation successful for:"));
//			Assert.assertEquals(dataMgmt.getResponseMsg().getText().trim(), "Import operation successful for:");
			new UploadFile().wait_till_successful_import(dataMgmt, driver);
			
			dataMgmt.getDoneButton().click();
			String extCount = driver.findElement(By.xpath("(//td[text()='CSV file']//following-sibling::td/a)[1]")).getText().trim();
			count = Integer.parseInt(extCount);
			System.out.println("Total Number of Records in Import Summary Page :"+ count);
			Assert.assertTrue(count == expExtCount);
			pmMainPage.getUsers().click();
			pmUsers.getUser().click();
			pmUser.setUserSearchTextBox(testData[4]);
			if(count <= 200)
			{
				new SelectDropDownValue().selectByValue(pmUser.getViewExtensionsLimit(), "200");
				pmUser.getOnViewRangeButton().click();
//				List<WebElement> pagingNumber = new Select(pmUser.getResultsPagingDropDown()).getOptions();
//				System.out.println("Number of result pages are :"+ pagingNumber.size());
//				Assert.assertTrue(pagingNumber.size() == 100/100);
//				for(int i=1; i<=pagingNumber.size(); i++)
//				{
//					new SelectDropDownValue().selectByValue(pmUser.getResultsPagingDropDown(), ""+i);
//					pmUser.getUsersResultsPagingGoButton().click();
					List<WebElement> elements = driver.findElements(By.xpath("//td[contains(text(),'"+testData[4]+"')]//following-sibling::td[contains(text(),'"+testData[3].substring(0, 16)+"')]"));
					Assert.assertTrue(elements.size() == count);
					Thread.sleep(2000);
//				}
			}
			else
			{
				int sum = 0;
				new SelectDropDownValue().selectByValue(pmUser.getViewExtensionsLimit(), "200");
				pmUser.getOnViewRangeButton().click();
				List<WebElement> pagingNumber = new Select(pmUser.getResultsPagingDropDown()).getOptions();
				System.out.println("Number of result pages are :"+ pagingNumber.size());
				
//				int quotient = count/10;
//				int remainder = count %10;
				
//				Assert.assertTrue(pagingNumber.size() == count/10);
				for(int i=1; i<=pagingNumber.size(); i++)
				{
					new SelectDropDownValue().selectByValue(pmUser.getResultsPagingDropDown(), ""+i);
					pmUser.getUsersResultsPagingGoButton().click();
					List<WebElement> elements = driver.findElements(By.xpath("//td[contains(text(),'"+testData[4]+"')]//following-sibling::td[contains(text(),'"+testData[3].substring(0, 16)+"')]"));
					Assert.assertTrue(elements.size() == 10);
					sum = sum + elements.size();
					Thread.sleep(2000);
				}
				Assert.assertTrue(sum == count);
			}
			pmMainPage.getSystem().click();
			pmSystem.getDataManagement().click();
			dataMgmt.getBackUpRestoreTab().click();
			
			elementList.clear();
			elementList = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//img[contains(@id,'removeThis')]"));
			Assert.assertTrue(elementList.size() == 1);
			
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
		catch(Error e)
		{
				new Take_Screenshot().get_Screenshot(driver, method.getName());
				throw e;
		}
		catch(Exception e)
		{
				new Take_Screenshot().get_Screenshot(driver, method.getName());
				throw e;
		}
		finally
		{
			File file = new File("./BulkFile.csv");
		    if (file.exists()) 
		    {
		      file.delete();
		    }
			credentials = loginData.getData("test_pm_valid_login", 1);
			testData = pmTests.getData(method.getName(), 1);
			list.clear();
			list.add(testData[5]);
			list.add(testData[6]);
			list.add(testData[7]);
			
			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
			new CleanUP(driver).delete_import_summary_record(driver, ipData, loginData);
			new CleanUP(driver).delete_existing_backUps(driver, ipData, loginData);
			new CleanUP(driver).deleteTemplate(driver, method.getName(), loginData, "IPTemp", ipData);
			new CleanUP(driver).delete_Users_With_10Users_for_Each_click(driver, ipData, credentials, testData[4], count);
		}
		
	}
	
	
	@Test
	public void test_bulk_user_import_with_NEW_extensions_delimiter_space(Method method) throws Exception
	{
		pmLoginPage = new PM_Login_Page(driver);
		pmMainPage = new PM_Main_Page(driver);
		pmUser = new PM_User(driver);
		pmUsers = new PM_Users(driver);
		pmSystem = new PM_System(driver);
		dataMgmt = new PM_System_DataManagement(driver);
		list = new ArrayList<String>();
		elementList = new ArrayList<WebElement>();
		wait = new WebDriverWait(driver, 20);
				
		String[] credentials;
		String[] testData;
		int count = 0;
		
		try 
		{
			pmTests.checkTestStatus(method.getName());
			credentials = loginData.getData("test_pm_valid_login", 1);
			testData = pmTests.getData(method.getName(), 1);
			new UploadFile().generate_BulkFile_For_NEW_Extension(testData[3], " ",Integer.parseInt(testData[8]), testData[4], "IPTemp");
			Thread.sleep(3000);
			new ReusableUnits(driver).createIPTemplate(driver, method.getName(), ipData, loginData, "IPTemp");
			
			int expExtCount = Integer.parseInt(testData[8]);
			
			list.add(testData[0]);
//			list.add(testData[1]);
//			list.add(testData[2]);
			
			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
			driver.get(ipData.getData(0, 0));
			System.out.println(testData);
			pmLoginPage.PM_Login(credentials[0], credentials[1]);
			pmMainPage.getSystem().click();
			pmSystem.getDataManagement().click();
			dataMgmt.getBackUpRestoreTab().click();
			
			//delete the existing backUpFile
			elementList.clear();
			elementList = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//img[contains(@id,'removeThis')]"));
			if(elementList.size() > 0)
			{
				for(WebElement element : elementList)
				{
					wait.until(ExpectedConditions.elementToBeClickable(element));
					element.click();
					driver.switchTo().alert().accept();
					Assert.assertEquals(dataMgmt.getResponseMsg().getText().trim(), "Remove operation successful for:");
				}
			}
			
			//deleting existing import record if present
			elementList.clear();
			dataMgmt.getImportTabButton().click();
			elementList = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//img[contains(@id,'removeThis')]"));
			if(elementList.size() > 0)
			{
				for(WebElement element : elementList)
				{
					wait.until(ExpectedConditions.elementToBeClickable(element));
					element.click();
					driver.switchTo().alert().accept();
//					Assert.assertEquals(dataMgmt.getResponseMsg().getText().trim(), "Remove operation successful for:");
				}
			}
			
			//taking a new backUp of pm data
			dataMgmt.getBackUpRestoreTab().click();
			Thread.sleep(1000);
//			wait.until(ExpectedConditions.elementToBeClickable(dataMgmt.getBackUpButton()));
			dataMgmt.getBackUpButton().click();
			dataMgmt.getApplyButtonBottom().click();
			
			elementList.clear();
			elementList = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//img[contains(@id,'removeThis')]"));
			Assert.assertTrue(elementList.size() == 1);
			
			dataMgmt.getImportTabButton().click();
			dataMgmt.getOnImportButton().click();
			dataMgmt.getNextButton().click();
			dataMgmt.getUserRadioButton().click();
			dataMgmt.getuserBrowseButton().sendKeys(System.getProperty("user.dir")+"\\BulkFile.csv");
			new SelectDropDownValue().selectByValue(dataMgmt.getDelimiterDropDown()," ");
			dataMgmt.getNextButton().click();
			
			elementList.clear();
			elementList = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//select[contains(@id,'mySelectedMPFields')]"));
			Assert.assertTrue(elementList.size() == 10);
			new SelectDropDownValue().selectByValue(driver.findElement(By.id("mySelectedMPFields[0]")), "First Name");
			new SelectDropDownValue().selectByValue(driver.findElement(By.id("mySelectedMPFields[1]")), "Alternate First Names");
			new SelectDropDownValue().selectByValue(driver.findElement(By.id("mySelectedMPFields[2]")), "Last Name");
			new SelectDropDownValue().selectByValue(driver.findElement(By.id("mySelectedMPFields[3]")), "Alternate Last Names");
			new SelectDropDownValue().selectByValue(driver.findElement(By.id("mySelectedMPFields[4]")), "Extension template");
			new SelectDropDownValue().selectByValue(driver.findElement(By.id("mySelectedMPFields[5]")), "Department");
			new SelectDropDownValue().selectByValue(driver.findElement(By.id("mySelectedMPFields[6]")), "Keywords");
			new SelectDropDownValue().selectByValue(driver.findElement(By.id("mySelectedMPFields[7]")), "User Id");
			new SelectDropDownValue().selectByValue(driver.findElement(By.id("mySelectedMPFields[8]")), "User Password");
			new SelectDropDownValue().selectByValue(driver.findElement(By.id("mySelectedMPFields[9]")), "Email");
			
//			dataMgmt.getApplyButton().click();
//			wait.until(ExpectedConditions.textToBe(By.className("responseMessage"), "  Import operation successful for:"));
//			Assert.assertEquals(dataMgmt.getResponseMsg().getText().trim(), "Import operation successful for:");
			new UploadFile().wait_till_successful_import(dataMgmt, driver);
			
			dataMgmt.getDoneButton().click();
			String extCount = driver.findElement(By.xpath("(//td[text()='CSV file']//following-sibling::td/a)[1]")).getText().trim();
			count = Integer.parseInt(extCount);
			System.out.println("Total Number of Records in Import Summary Page :"+ count);
			Assert.assertTrue(count == expExtCount);
			pmMainPage.getUsers().click();
			pmUsers.getUser().click();
			pmUser.setUserSearchTextBox(testData[4]);
			if(count <= 200)
			{
				new SelectDropDownValue().selectByValue(pmUser.getViewExtensionsLimit(), "200");
				pmUser.getOnViewRangeButton().click();
//				List<WebElement> pagingNumber = new Select(pmUser.getResultsPagingDropDown()).getOptions();
//				System.out.println("Number of result pages are :"+ pagingNumber.size());
//				Assert.assertTrue(pagingNumber.size() == 100/100);
//				for(int i=1; i<=pagingNumber.size(); i++)
//				{
//					new SelectDropDownValue().selectByValue(pmUser.getResultsPagingDropDown(), ""+i);
//					pmUser.getUsersResultsPagingGoButton().click();
					List<WebElement> elements = driver.findElements(By.xpath("//td[contains(text(),'"+testData[4]+"')]//following-sibling::td[contains(text(),'"+testData[3].substring(0, 16)+"')]"));
					Assert.assertTrue(elements.size() == count);
					Thread.sleep(2000);
//				}
			}
			else
			{
				int sum = 0;
				new SelectDropDownValue().selectByValue(pmUser.getViewExtensionsLimit(), "200");
				pmUser.getOnViewRangeButton().click();
				List<WebElement> pagingNumber = new Select(pmUser.getResultsPagingDropDown()).getOptions();
				System.out.println("Number of result pages are :"+ pagingNumber.size());
				
//				int quotient = count/10;
//				int remainder = count %10;
				
//				Assert.assertTrue(pagingNumber.size() == count/10);
				for(int i=1; i<=pagingNumber.size(); i++)
				{
					new SelectDropDownValue().selectByValue(pmUser.getResultsPagingDropDown(), ""+i);
					pmUser.getUsersResultsPagingGoButton().click();
					List<WebElement> elements = driver.findElements(By.xpath("//td[contains(text(),'"+testData[4]+"')]//following-sibling::td[contains(text(),'"+testData[3].substring(0, 16)+"')]"));
					Assert.assertTrue(elements.size() == 10);
					sum = sum + elements.size();
					Thread.sleep(2000);
				}
				Assert.assertTrue(sum == count);
			}
			pmMainPage.getSystem().click();
			pmSystem.getDataManagement().click();
			dataMgmt.getBackUpRestoreTab().click();
			
			elementList.clear();
			elementList = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//img[contains(@id,'removeThis')]"));
			Assert.assertTrue(elementList.size() == 1);
			
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
		catch(Error e)
		{
				new Take_Screenshot().get_Screenshot(driver, method.getName());
				throw e;
		}
		catch(Exception e)
		{
				new Take_Screenshot().get_Screenshot(driver, method.getName());
				throw e;
		}
		finally
		{
			File file = new File("./BulkFile.csv");
		    if (file.exists()) 
		    {
		      file.delete();
		    }
			credentials = loginData.getData("test_pm_valid_login", 1);
			testData = pmTests.getData(method.getName(), 1);
			list.clear();
			list.add(testData[5]);
			list.add(testData[6]);
			list.add(testData[7]);
			
			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
			new CleanUP(driver).delete_import_summary_record(driver, ipData, loginData);
			new CleanUP(driver).delete_existing_backUps(driver, ipData, loginData);
			new CleanUP(driver).deleteTemplate(driver, method.getName(), loginData, "IPTemp", ipData);
			new CleanUP(driver).delete_Users_With_10Users_for_Each_click(driver, ipData, credentials, testData[4], count);
		}
		
	}
	
	
	@Test
	public void test_bulk_user_import_with_NEW_extensions_delimiter_comma(Method method) throws Exception
	{
		pmLoginPage = new PM_Login_Page(driver);
		pmMainPage = new PM_Main_Page(driver);
		pmUser = new PM_User(driver);
		pmUsers = new PM_Users(driver);
		pmSystem = new PM_System(driver);
		dataMgmt = new PM_System_DataManagement(driver);
		list = new ArrayList<String>();
		elementList = new ArrayList<WebElement>();
		wait = new WebDriverWait(driver, 20);
				
		String[] credentials;
		String[] testData;
		int count = 0;
		
		try 
		{
			pmTests.checkTestStatus(method.getName());
			credentials = loginData.getData("test_pm_valid_login", 1);
			testData = pmTests.getData(method.getName(), 1);
			new UploadFile().generate_BulkFile_For_NEW_Extension(testData[3], ",",Integer.parseInt(testData[8]), testData[4], "IPTemp");
			Thread.sleep(3000);
			new ReusableUnits(driver).createIPTemplate(driver, method.getName(), ipData, loginData, "IPTemp");
			
			int expExtCount = Integer.parseInt(testData[8]);
			
			list.add(testData[0]);
//			list.add(testData[1]);
//			list.add(testData[2]);
			
			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
			driver.get(ipData.getData(0, 0));
			System.out.println(testData);
			pmLoginPage.PM_Login(credentials[0], credentials[1]);
			pmMainPage.getSystem().click();
			pmSystem.getDataManagement().click();
			dataMgmt.getBackUpRestoreTab().click();
			
			//delete the existing backUpFile
			elementList.clear();
			elementList = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//img[contains(@id,'removeThis')]"));
			if(elementList.size() > 0)
			{
				for(WebElement element : elementList)
				{
					wait.until(ExpectedConditions.elementToBeClickable(element));
					element.click();
					driver.switchTo().alert().accept();
					Assert.assertEquals(dataMgmt.getResponseMsg().getText().trim(), "Remove operation successful for:");
				}
			}
			
			//deleting existing import record if present
			elementList.clear();
			dataMgmt.getImportTabButton().click();
			elementList = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//img[contains(@id,'removeThis')]"));
			if(elementList.size() > 0)
			{
				for(WebElement element : elementList)
				{
					wait.until(ExpectedConditions.elementToBeClickable(element));
					element.click();
					driver.switchTo().alert().accept();
//					Assert.assertEquals(dataMgmt.getResponseMsg().getText().trim(), "Remove operation successful for:");
				}
			}
			
			//taking a new backUp of pm data
			dataMgmt.getBackUpRestoreTab().click();
			Thread.sleep(1000);
//			wait.until(ExpectedConditions.elementToBeClickable(dataMgmt.getBackUpButton()));
			dataMgmt.getBackUpButton().click();
			dataMgmt.getApplyButtonBottom().click();
			
			elementList.clear();
			elementList = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//img[contains(@id,'removeThis')]"));
			Assert.assertTrue(elementList.size() == 1);
			
			dataMgmt.getImportTabButton().click();
			dataMgmt.getOnImportButton().click();
			dataMgmt.getNextButton().click();
			dataMgmt.getUserRadioButton().click();
			dataMgmt.getuserBrowseButton().sendKeys(System.getProperty("user.dir")+"\\BulkFile.csv");
			new SelectDropDownValue().selectByValue(dataMgmt.getDelimiterDropDown(),",");
			dataMgmt.getNextButton().click();
			
			elementList.clear();
			elementList = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//select[contains(@id,'mySelectedMPFields')]"));
			Assert.assertTrue(elementList.size() == 10);
			new SelectDropDownValue().selectByValue(driver.findElement(By.id("mySelectedMPFields[0]")), "First Name");
			new SelectDropDownValue().selectByValue(driver.findElement(By.id("mySelectedMPFields[1]")), "Alternate First Names");
			new SelectDropDownValue().selectByValue(driver.findElement(By.id("mySelectedMPFields[2]")), "Last Name");
			new SelectDropDownValue().selectByValue(driver.findElement(By.id("mySelectedMPFields[3]")), "Alternate Last Names");
			new SelectDropDownValue().selectByValue(driver.findElement(By.id("mySelectedMPFields[4]")), "Extension template");
			new SelectDropDownValue().selectByValue(driver.findElement(By.id("mySelectedMPFields[5]")), "Department");
			new SelectDropDownValue().selectByValue(driver.findElement(By.id("mySelectedMPFields[6]")), "Keywords");
			new SelectDropDownValue().selectByValue(driver.findElement(By.id("mySelectedMPFields[7]")), "User Id");
			new SelectDropDownValue().selectByValue(driver.findElement(By.id("mySelectedMPFields[8]")), "User Password");
			new SelectDropDownValue().selectByValue(driver.findElement(By.id("mySelectedMPFields[9]")), "Email");
			
//			dataMgmt.getApplyButton().click();
//			wait.until(ExpectedConditions.textToBe(By.className("responseMessage"), "  Import operation successful for:"));
//			Assert.assertEquals(dataMgmt.getResponseMsg().getText().trim(), "Import operation successful for:");
			new UploadFile().wait_till_successful_import(dataMgmt, driver);
			
			dataMgmt.getDoneButton().click();
			String extCount = driver.findElement(By.xpath("(//td[text()='CSV file']//following-sibling::td/a)[1]")).getText().trim();
			count = Integer.parseInt(extCount);
			System.out.println("Total Number of Records in Import Summary Page :"+ count);
			Assert.assertTrue(count == expExtCount);
			pmMainPage.getUsers().click();
			pmUsers.getUser().click();
			pmUser.setUserSearchTextBox(testData[4]);
			if(count <= 200)
			{
				new SelectDropDownValue().selectByValue(pmUser.getViewExtensionsLimit(), "200");
				pmUser.getOnViewRangeButton().click();
//				List<WebElement> pagingNumber = new Select(pmUser.getResultsPagingDropDown()).getOptions();
//				System.out.println("Number of result pages are :"+ pagingNumber.size());
//				Assert.assertTrue(pagingNumber.size() == 100/100);
//				for(int i=1; i<=pagingNumber.size(); i++)
//				{
//					new SelectDropDownValue().selectByValue(pmUser.getResultsPagingDropDown(), ""+i);
//					pmUser.getUsersResultsPagingGoButton().click();
					List<WebElement> elements = driver.findElements(By.xpath("//td[contains(text(),'"+testData[4]+"')]//following-sibling::td[contains(text(),'"+testData[3].substring(0, 16)+"')]"));
					Assert.assertTrue(elements.size() == count);
					Thread.sleep(2000);
//				}
			}
			else
			{
				int sum = 0;
				new SelectDropDownValue().selectByValue(pmUser.getViewExtensionsLimit(), "200");
				pmUser.getOnViewRangeButton().click();
				List<WebElement> pagingNumber = new Select(pmUser.getResultsPagingDropDown()).getOptions();
				System.out.println("Number of result pages are :"+ pagingNumber.size());
				
//				int quotient = count/10;
//				int remainder = count %10;
				
//				Assert.assertTrue(pagingNumber.size() == count/10);
				for(int i=1; i<=pagingNumber.size(); i++)
				{
					new SelectDropDownValue().selectByValue(pmUser.getResultsPagingDropDown(), ""+i);
					pmUser.getUsersResultsPagingGoButton().click();
					List<WebElement> elements = driver.findElements(By.xpath("//td[contains(text(),'"+testData[4]+"')]//following-sibling::td[contains(text(),'"+testData[3].substring(0, 16)+"')]"));
					Assert.assertTrue(elements.size() == 10);
					sum = sum + elements.size();
					Thread.sleep(2000);
				}
				Assert.assertTrue(sum == count);
			}
			pmMainPage.getSystem().click();
			pmSystem.getDataManagement().click();
			dataMgmt.getBackUpRestoreTab().click();
			
			elementList.clear();
			elementList = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//img[contains(@id,'removeThis')]"));
			Assert.assertTrue(elementList.size() == 1);
			
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
		catch(Error e)
		{
				new Take_Screenshot().get_Screenshot(driver, method.getName());
				throw e;
		}
		catch(Exception e)
		{
				new Take_Screenshot().get_Screenshot(driver, method.getName());
				throw e;
		}
		finally
		{
			File file = new File("./BulkFile.csv");
		    if (file.exists()) 
		    {
		      file.delete();
		    }
			credentials = loginData.getData("test_pm_valid_login", 1);
			testData = pmTests.getData(method.getName(), 1);
			list.clear();
			list.add(testData[5]);
			list.add(testData[6]);
			list.add(testData[7]);
			
			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
			new CleanUP(driver).delete_import_summary_record(driver, ipData, loginData);
			new CleanUP(driver).delete_existing_backUps(driver, ipData, loginData);
			new CleanUP(driver).deleteTemplate(driver, method.getName(), loginData, "IPTemp", ipData);
			new CleanUP(driver).delete_Users_With_10Users_for_Each_click(driver, ipData, credentials, testData[4], count);
		}
		
	}
	*/
}
