package tests;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.ConfigClass;
import pm_pom_classes.Extension;
import pm_pom_classes.PM_Login_Page;
import pm_pom_classes.PM_Main_Page;
import pm_pom_classes.PM_Services;
import pm_pom_classes.PM_User;
import pm_pom_classes.PM_Users;
import utilities.Call_list_utilities;
import utilities.ExecuteCommands;
import utilities.GetMxoneVersionNumber;
import utilities.ReusableUnits;
import utilities.SelectDropDownValue;
import utilities.Take_Screenshot;

public class PM_PersonalNumber_Tests extends ConfigClass
{
	public ArrayList<String> list;
	public PM_Login_Page loginPage;
	public PM_User pmUser;
	public Extension pmExtension;
	public PM_Main_Page pmMainPge;
	public PM_Services pmServices;
	WebDriverWait wait = null;
	public PM_Users pmUsers;
	
	@Test
	public void create_PN_IP_Extension(Method method) throws Exception
	{
		pmTests.checkTestStatus(method.getName());
		String[] testData = null;
		String [] extData = null;
		String[] credentials = null;
		wait = new WebDriverWait(driver, 20);
		try
		{
			pmExtension = new Extension(driver);
			loginPage = new PM_Login_Page(driver);
			pmMainPge = new PM_Main_Page(driver);
			pmServices = new PM_Services(driver);
			pmUser = new PM_User(driver);
			pmUsers = new PM_Users(driver);
			testData = pmTests.getData(method.getName(), 1);
			extData = pmTests.getData(method.getName(), 3);
			credentials = loginData.getData("test_pm_valid_login", 1);
			
			list = new ArrayList<String>();
			list.add(testData[0]);
			list.add(extData[0]);
			list.add(extData[1]);
			list.add(extData[2]);
			
			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
			
			new ReusableUnits(driver).create_personal_number_for_given_extension
			(driver, method.getName(), ipData, loginData, pmTests, "IP");
			
			pmUser.getLogoutLink().click();
		}
		catch(Exception e)
		{
				new Take_Screenshot().get_Screenshot(driver, method.getName());
				throw e;
		}
		finally
		{
			list.clear();
			list.add(extData[13]);
			list.add(extData[14]);
			list.add(extData[15]);
			list.add(testData[2]);
			list.add(testData[3]);
			list.add(testData[4]);
			list.add(testData[5]);
			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
		}
	}
	
	
	
	@Test
	public void create_PN_Analog_Extension(Method method) throws Exception
	{
		pmTests.checkTestStatus(method.getName());
		String[] testData = null;
		String [] extData = null;
		String[] credentials = null;
		wait = new WebDriverWait(driver, 20);
		try
		{
			pmExtension = new Extension(driver);
			loginPage = new PM_Login_Page(driver);
			pmMainPge = new PM_Main_Page(driver);
			pmServices = new PM_Services(driver);
			pmUser = new PM_User(driver);
			pmUsers = new PM_Users(driver);
			testData = pmTests.getData(method.getName(), 1);
			extData = pmTests.getData(method.getName(), 3);
			credentials = loginData.getData("test_pm_valid_login", 1);
			
			list = new ArrayList<String>();
			list.add(testData[0]);
			list.add(extData[0]);
			list.add(extData[1]);
			list.add(extData[2]);
			
			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
			
			new ReusableUnits(driver).create_personal_number_for_given_extension
			(driver, method.getName(), ipData, loginData, pmTests, "analog");
			
			pmUser.getLogoutLink().click();
		}
		catch(Exception e)
		{
				new Take_Screenshot().get_Screenshot(driver, method.getName());
				throw e;
		}
		finally
		{
			list.clear();
			list.add(extData[13]);
			list.add(extData[14]);
			list.add(extData[15]);
			list.add(testData[3]);
			list.add(testData[4]);
			list.add(testData[5]);
			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
		}
	}
	
	@Test
	public void create_PN_Digital_Extension(Method method) throws Exception
	{
		pmTests.checkTestStatus(method.getName());
		String[] testData = null;
		String [] extData = null;
		String[] credentials = null;
		wait = new WebDriverWait(driver, 20);
		try
		{
			pmExtension = new Extension(driver);
			loginPage = new PM_Login_Page(driver);
			pmMainPge = new PM_Main_Page(driver);
			pmServices = new PM_Services(driver);
			pmUser = new PM_User(driver);
			pmUsers = new PM_Users(driver);
			testData = pmTests.getData(method.getName(), 1);
			extData = pmTests.getData(method.getName(), 3);
			credentials = loginData.getData("test_pm_valid_login", 1);
			
			list = new ArrayList<String>();
			list.add(testData[0]);
			list.add(extData[0]);
			list.add(extData[1]);
			list.add(extData[2]);
			
			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
			
			new ReusableUnits(driver).create_personal_number_for_given_extension
			(driver, method.getName(), ipData, loginData, pmTests, "digital");
			
			pmUser.getLogoutLink().click();
		}
		catch(Exception e)
		{
				new Take_Screenshot().get_Screenshot(driver, method.getName());
				throw e;
		}
		finally
		{
			list.clear();
			list.add(extData[13]);
			list.add(extData[14]);
			list.add(extData[15]);
			list.add(testData[3]);
			list.add(testData[4]);
			list.add(testData[5]);
			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
		}
	}
	
	@Test
	public void create_PN_Virtual_Extension(Method method) throws Exception
	{
		pmTests.checkTestStatus(method.getName());
		String[] testData = null;
		String [] extData = null;
		String[] credentials = null;
		wait = new WebDriverWait(driver, 20);
		try
		{
			pmExtension = new Extension(driver);
			loginPage = new PM_Login_Page(driver);
			pmMainPge = new PM_Main_Page(driver);
			pmServices = new PM_Services(driver);
			pmUser = new PM_User(driver);
			pmUsers = new PM_Users(driver);
			testData = pmTests.getData(method.getName(), 1);
			extData = pmTests.getData(method.getName(), 3);
			credentials = loginData.getData("test_pm_valid_login", 1);
			
			list = new ArrayList<String>();
			list.add(testData[0]);
			list.add(extData[0]);
			list.add(extData[1]);
			list.add(extData[2]);
			
			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
			
			new ReusableUnits(driver).create_personal_number_for_given_extension
			(driver, method.getName(), ipData, loginData, pmTests, "virtual");
			
			pmUser.getLogoutLink().click();
		}
		catch(Exception e)
		{
				new Take_Screenshot().get_Screenshot(driver, method.getName());
				throw e;
		}
		finally
		{
			list.clear();
			list.add(extData[13]);
			list.add(extData[14]);
			list.add(extData[15]);
			list.add(testData[2]);
			list.add(testData[3]);
			list.add(testData[4]);
			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
		}
	}
	
	@Test
	public void test_create_PN_SIPDECT_Extension(Method method) throws Exception
	{
		pmTests.checkTestStatus(method.getName());
		String[] testData = null;
		String [] extData = null;
		String[] credentials = null;
		wait = new WebDriverWait(driver, 20);
		try
		{
			pmExtension = new Extension(driver);
			loginPage = new PM_Login_Page(driver);
			pmMainPge = new PM_Main_Page(driver);
			pmServices = new PM_Services(driver);
			pmUser = new PM_User(driver);
			pmUsers = new PM_Users(driver);
			testData = pmTests.getData(method.getName(), 1);
			extData = pmTests.getData(method.getName(), 3);
			credentials = loginData.getData("test_pm_valid_login", 1);
			
			list = new ArrayList<String>();
			list.add(testData[0]);
			list.add(extData[0]);
			list.add(extData[1]);
			list.add(extData[2]);
			
			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
			
			new ReusableUnits(driver).create_personal_number_for_given_extension
			(driver, method.getName(), ipData, loginData, pmTests, "sip-dect");
			
			pmUser.getLogoutLink().click();
		}
		catch(Exception e)
		{
				new Take_Screenshot().get_Screenshot(driver, method.getName());
				throw e;
		}
		finally
		{
			list.clear();
			list.add(extData[13]);
			list.add(extData[14]);
			list.add(extData[15]);
			list.add(testData[5]);
			list.add(testData[6]);
			list.add(testData[7]);
			list.add(testData[8]);
			list.add(testData[9]);
			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
		}
	}
	
	
	@Test
	public void test_edit_IP_extension_assign_PN(Method method) throws Exception
	{
		String[] testData = null;
		String[] extData = null;
		list = new ArrayList<String>();
		try
		{
			pmTests.checkTestStatus(method.getName());
			pmExtension = new Extension(driver);
			loginPage = new PM_Login_Page(driver);
			pmMainPge = new PM_Main_Page(driver);
			pmServices = new PM_Services(driver);
			pmUser = new PM_User(driver);
			pmUsers = new PM_Users(driver);
			wait = new WebDriverWait(driver, 20);
			
			testData = pmTests.getData(method.getName(), 1);
			extData = pmTests.getData(method.getName(), 3);
			
			list.add(testData[0]);
			list.add(testData[2]);
			list.add(testData[3]);
			list.add(extData[0]);
			list.add(extData[1]);
			list.add(extData[2]);
			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
			new ReusableUnits(driver).navigateToExtensionEditPage(driver, method.getName(), ipData, loginData, pmTests,"IP");
			
			pmExtension.getPENListButton().click();
			new Call_list_utilities(driver).create_extension_with_personalNumber(driver, method.getName(),
					loginData, pmTests, ipData);
			
			pmExtension.getMultiStepBackButton().click();
			Thread.sleep(2000);
			pmExtension.getEditPageApplyButton().click();
			Assert.assertEquals(pmExtension.getResponseMessage(), "Change operation successful for:");
			pmExtension.getDoneButton().click();
			
			new SelectDropDownValue().selectByVisibleText(pmExtension.getExtensionTypeDropDownHomePage(), "IP");
			pmExtension.getEnterExtensionNumberTextBox().clear();
			pmExtension.setEnterExtensionNumberTextBox(testData[1]);
			pmExtension.getViewRangeButton().click();
			
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("blockUI")));
			driver.findElement(By.xpath("//td[contains(text(),'"+testData[1]+"')]//preceding-sibling::td[20]")).click();
		
			new Call_list_utilities(driver).verifyPN_In_Ext_View_Page(driver, "1");
			pmExtension.getDoneButton().click();
			
			pmUser.getLogoutLink().click();
		}
		catch(Exception e)
		{
				new Take_Screenshot().get_Screenshot(driver, method.getName());
				throw e;
		}
		finally
		{
			list.clear();
			list.add(extData[13]);
			list.add(extData[14]);
			list.add(extData[15]);
			list.add(testData[4]);
			list.add(testData[5]);
			list.add(testData[6]);
			list.add(testData[7]);
			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
		}
	}
	
	@Test
	public void test_edit_Analog_extension_assign_PN(Method method) throws Exception
	{
		String[] testData = null;
		String[] extData = null;
		list = new ArrayList<String>();
		try
		{
			pmTests.checkTestStatus(method.getName());
			pmExtension = new Extension(driver);
			loginPage = new PM_Login_Page(driver);
			pmMainPge = new PM_Main_Page(driver);
			pmServices = new PM_Services(driver);
			pmUser = new PM_User(driver);
			pmUsers = new PM_Users(driver);
			wait = new WebDriverWait(driver, 20);
			
			testData = pmTests.getData(method.getName(), 1);
			extData = pmTests.getData(method.getName(), 3);
			
			list.add(testData[0]);
			list.add("EXTEI:DIR="+testData[1]+",CAT=0,EQU="+testData[2]+",TYPE=EL6,ICAT=8000100,TRM=1,ADC=000000001;");
			list.add(extData[0]);
			list.add(extData[1]);
			list.add(extData[2]);
			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
			new ReusableUnits(driver).navigateToExtensionEditPage(driver, method.getName(), ipData, loginData, pmTests,"Analog");
			
			pmExtension.getPENListButton().click();
			new Call_list_utilities(driver).create_extension_with_personalNumber(driver, method.getName(),
					loginData, pmTests, ipData);
			
			pmExtension.getMultiStepBackButton().click();
			
			Thread.sleep(2000);
			pmExtension.getEditPageApplyButton().click();
			Assert.assertEquals(pmExtension.getResponseMessage(), "Change operation successful for:");
			pmExtension.getDoneButton().click();
			
			new SelectDropDownValue().selectByVisibleText(pmExtension.getExtensionTypeDropDownHomePage(), "Analog");
			pmExtension.getEnterExtensionNumberTextBox().clear();
			pmExtension.setEnterExtensionNumberTextBox(testData[1]);
			pmExtension.getViewRangeButton().click();
			
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("blockUI")));
			driver.findElement(By.xpath("//td[contains(text(),'"+testData[1]+"')]//preceding-sibling::td[20]")).click();
		
			new Call_list_utilities(driver).verifyPN_In_Ext_View_Page(driver, "1");
			pmExtension.getDoneButton().click();
			
			pmUser.getLogoutLink().click();
		}
		catch(Exception e)
		{
				new Take_Screenshot().get_Screenshot(driver, method.getName());
				throw e;
		}
		finally
		{
			list.clear();
			list.add(extData[13]);
			list.add(extData[14]);
			list.add(extData[15]);
			list.add(testData[3]);
			list.add(testData[4]);
			list.add(testData[5]);
			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
		}
	}
	
	
	@Test
	public void test_edit_Digital_extension_assign_PN(Method method) throws Exception
	{
		String[] testData = null;
		String[] extData = null;
		list = new ArrayList<String>();
		try
		{
			pmTests.checkTestStatus(method.getName());
			pmExtension = new Extension(driver);
			loginPage = new PM_Login_Page(driver);
			pmMainPge = new PM_Main_Page(driver);
			pmServices = new PM_Services(driver);
			pmUser = new PM_User(driver);
			pmUsers = new PM_Users(driver);
			wait = new WebDriverWait(driver, 20);
			
			testData = pmTests.getData(method.getName(), 1);
			extData = pmTests.getData(method.getName(), 3);
			
			list.add(testData[0]);
			list.add("KSEXI:DIR="+testData[1]+",CAT=0,EQU="+testData[2]+",ITYPE=0;");
			list.add(extData[0]);
			list.add(extData[1]);
			list.add(extData[2]);
			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
			new ReusableUnits(driver).navigateToExtensionEditPage(driver, method.getName(), ipData, loginData, pmTests,"Digital");
			
			pmExtension.getPENListButton().click();
			new Call_list_utilities(driver).create_extension_with_personalNumber(driver, method.getName(),
					loginData, pmTests, ipData);
			
			pmExtension.getMultiStepBackButton().click();
			
			Thread.sleep(2000);
			pmExtension.getEditPageApplyButton().click();
			Assert.assertEquals(pmExtension.getResponseMessage(), "Change operation successful for:");
			pmExtension.getDoneButton().click();
			
			new SelectDropDownValue().selectByVisibleText(pmExtension.getExtensionTypeDropDownHomePage(), "Digital");
			pmExtension.getEnterExtensionNumberTextBox().clear();
			pmExtension.setEnterExtensionNumberTextBox(testData[1]);
			pmExtension.getViewRangeButton().click();
			
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("blockUI")));
			driver.findElement(By.xpath("//td[contains(text(),'"+testData[1]+"')]//preceding-sibling::td[20]")).click();
		
			new Call_list_utilities(driver).verifyPN_In_Ext_View_Page(driver, "1");
			pmExtension.getDoneButton().click();
			
			pmUser.getLogoutLink().click();
		}
		catch(Exception e)
		{
				new Take_Screenshot().get_Screenshot(driver, method.getName());
				throw e;
		}
		finally
		{
			list.clear();
			list.add(extData[13]);
			list.add(extData[14]);
			list.add(extData[15]);
			list.add(testData[3]);
			list.add(testData[4]);
			list.add(testData[5]);
			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
		}
	}
	
	@Test
	public void test_edit_Virtual_extension_assign_PN(Method method) throws Exception
	{
		String[] testData = null;
		String[] extData = null;
		list = new ArrayList<String>();
		try
		{
			pmTests.checkTestStatus(method.getName());
			pmExtension = new Extension(driver);
			loginPage = new PM_Login_Page(driver);
			pmMainPge = new PM_Main_Page(driver);
			pmServices = new PM_Services(driver);
			pmUser = new PM_User(driver);
			pmUsers = new PM_Users(driver);
			wait = new WebDriverWait(driver, 20);
			
			testData = pmTests.getData(method.getName(), 1);
			extData = pmTests.getData(method.getName(), 3);
			
			list.add(testData[0]);
			list.add(testData[2]);
			list.add(extData[0]);
			list.add(extData[1]);
			list.add(extData[2]);
			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
			new ReusableUnits(driver).navigateToExtensionEditPage(driver, method.getName(), ipData, loginData, pmTests,"Virtual");
			
			pmExtension.getPENListButton().click();
			new Call_list_utilities(driver).create_extension_with_personalNumber(driver, method.getName(),
					loginData, pmTests, ipData);
			
			pmExtension.getMultiStepBackButton().click();
			
			Thread.sleep(2000);
			pmExtension.getEditPageApplyButton().click();
			Assert.assertEquals(pmExtension.getResponseMessage(), "Change operation successful for:");
			pmExtension.getDoneButton().click();
			
			new SelectDropDownValue().selectByVisibleText(pmExtension.getExtensionTypeDropDownHomePage(), "Virtual");
			pmExtension.getEnterExtensionNumberTextBox().clear();
			pmExtension.setEnterExtensionNumberTextBox(testData[1]);
			pmExtension.getViewRangeButton().click();
			
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("blockUI")));
			driver.findElement(By.xpath("//td[contains(text(),'"+testData[1]+"')]//preceding-sibling::td[20]")).click();
		
			new Call_list_utilities(driver).verifyPN_In_Ext_View_Page(driver, "1");
			pmExtension.getDoneButton().click();
			
			pmUser.getLogoutLink().click();
		}
		catch(Exception e)
		{
				new Take_Screenshot().get_Screenshot(driver, method.getName());
				throw e;
		}
		finally
		{
			list.clear();
			list.add(extData[13]);
			list.add(extData[14]);
			list.add(extData[15]);
			list.add(testData[3]);
			list.add(testData[4]);
			list.add(testData[5]);
			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
		}
	}
	
	@Test
	public void test_edit_SIPDECT_Assign_PN(Method method) throws Exception
	{
		pmTests.checkTestStatus(method.getName());
		String[] testData = null;
		String [] extData = null;
		String[] credentials = null;
		wait = new WebDriverWait(driver, 20);
		try
		{
			pmExtension = new Extension(driver);
			loginPage = new PM_Login_Page(driver);
			pmMainPge = new PM_Main_Page(driver);
			pmServices = new PM_Services(driver);
			pmUser = new PM_User(driver);
			pmUsers = new PM_Users(driver);
			testData = pmTests.getData(method.getName(), 1);
			extData = pmTests.getData(method.getName(), 3);
			credentials = loginData.getData("test_pm_valid_login", 1);
			
			list = new ArrayList<String>();
			list.add(testData[0]);
			list.add(extData[0]);
			list.add(extData[1]);
			list.add(extData[2]);
			
			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
			
			new ReusableUnits(driver).create_SIPDECT_Extension(driver, method.getName(), ipData, loginData, pmTests,0);
			
			driver.findElement(By.xpath("//td[contains(text(),'"+testData[1]+"')]//preceding-sibling::td[19]")).click();
			
			pmExtension.getPENListButton().click();
			new Call_list_utilities(driver).create_extension_with_personalNumber(driver, method.getName(),
					loginData, pmTests, ipData);
			
			pmExtension.getMultiStepBackButton().click();
			
			pmExtension.getEditPageApplyButton().click();
			Assert.assertEquals(pmExtension.getResponseMessage(), "Change operation successful for:");
			pmExtension.getDoneButton().click();
			
			new SelectDropDownValue().selectByVisibleText(pmExtension.getExtensionTypeDropDownHomePage(), "Multi-Terminal");
			pmExtension.getEnterExtensionNumberTextBox().clear();
			pmExtension.setEnterExtensionNumberTextBox(testData[1]);
			pmExtension.getViewRangeButton().click();
			
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("blockUI")));
			driver.findElement(By.xpath("//td[contains(text(),'"+testData[1]+"')]//preceding-sibling::td[20]")).click();
		
			new Call_list_utilities(driver).verifyPN_In_Ext_View_Page(driver, "1");
			pmExtension.getDoneButton().click();
			
			pmUser.getLogoutLink().click();
		}
		catch(Exception e)
		{
				new Take_Screenshot().get_Screenshot(driver, method.getName());
				throw e;
		}
		finally
		{
			list.clear();
			list.add(extData[13]);
			list.add(extData[14]);
			list.add(extData[15]);
			list.add(testData[8]);
			list.add(testData[9]);
			list.add(testData[10]);
			list.add(testData[11]);
			list.add(testData[12]);
			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
		}
	}
	
	@Test
	public void edit_IP_extension_to_change_active_call_list(Method method) throws Exception
	{
		pmTests.checkTestStatus(method.getName());
		String[] testData = null;
		String [] extData = null;
		String[] credentials = null;
		wait = new WebDriverWait(driver, 20);
		try
		{
			pmExtension = new Extension(driver);
			loginPage = new PM_Login_Page(driver);
			pmMainPge = new PM_Main_Page(driver);
			pmServices = new PM_Services(driver);
			pmUser = new PM_User(driver);
			pmUsers = new PM_Users(driver);
			testData = pmTests.getData(method.getName(), 1);
			extData = pmTests.getData(method.getName(), 3);
			credentials = loginData.getData("test_pm_valid_login", 1);
			
			list = new ArrayList<String>();
			list.add(testData[0]);
			list.add(extData[0]);
			list.add(extData[1]);
			list.add(extData[2]);
			
			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
			
			new ReusableUnits(driver).create_personal_number_for_given_extension
			(driver, method.getName(), ipData, loginData, pmTests, "IP");
			
			driver.findElement(By.xpath("//td[contains(text(),'"+testData[1]+"')]//preceding-sibling::td[19]")).click();
			pmExtension.getPENListButton().click();
			driver.findElement(By.xpath("//img[@id='activateThis9_img']")).click();
			pmExtension.getMultiStepBackButton().click();
			
			List<WebElement> p10 = driver.findElements(By.xpath("//td[contains(text(),'Profile10:Active')]"));
			Assert.assertTrue(p10.size() == 1);
			
			pmExtension.getEditPageApplyButton().click();
			Assert.assertEquals(pmExtension.getResponseMessage(), "Change operation successful for:");
			pmExtension.getDoneButton().click();
			
			driver.findElement(By.xpath("//td[contains(text(),'"+testData[1]+"')]//preceding-sibling::td[20]")).click();
			
			new Call_list_utilities(driver).verifyPN_In_Ext_View_Page(driver, "10");
//			Thread.sleep(10000);
			pmExtension.getDoneButton().click();
			
			pmUser.getLogoutLink().click();
		}
		catch(Exception e)
		{
				new Take_Screenshot().get_Screenshot(driver, method.getName());
				throw e;
		}
		finally
		{
			list.clear();
			list.add(extData[13]);
			list.add(extData[14]);
			list.add(extData[15]);
			list.add(testData[2]);
			list.add(testData[3]);
			list.add(testData[4]);
			list.add(testData[5]);
			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
		}
	}
	
	
	@Test
	public void edit_analog_extension_to_change_active_call_list(Method method) throws Exception
	{
		pmTests.checkTestStatus(method.getName());
		String[] testData = null;
		String [] extData = null;
		String[] credentials = null;
		wait = new WebDriverWait(driver, 20);
		try
		{
			pmExtension = new Extension(driver);
			loginPage = new PM_Login_Page(driver);
			pmMainPge = new PM_Main_Page(driver);
			pmServices = new PM_Services(driver);
			pmUser = new PM_User(driver);
			pmUsers = new PM_Users(driver);
			testData = pmTests.getData(method.getName(), 1);
			extData = pmTests.getData(method.getName(), 3);
			credentials = loginData.getData("test_pm_valid_login", 1);
			
			list = new ArrayList<String>();
			list.add(testData[0]);
			list.add(extData[0]);
			list.add(extData[1]);
			list.add(extData[2]);
			
			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
			
			new ReusableUnits(driver).create_personal_number_for_given_extension
			(driver, method.getName(), ipData, loginData, pmTests, "analog");
			
			driver.findElement(By.xpath("//td[contains(text(),'"+testData[1]+"')]//preceding-sibling::td[19]")).click();
			pmExtension.getPENListButton().click();
			driver.findElement(By.xpath("//img[@id='activateThis9_img']")).click();
			pmExtension.getMultiStepBackButton().click();
			
			List<WebElement> p10 = driver.findElements(By.xpath("//td[contains(text(),'Profile10:Active')]"));
			Assert.assertTrue(p10.size() == 1);
			
			pmExtension.getEditPageApplyButton().click();
			Assert.assertEquals(pmExtension.getResponseMessage(), "Change operation successful for:");
			pmExtension.getDoneButton().click();
			
			driver.findElement(By.xpath("//td[contains(text(),'"+testData[1]+"')]//preceding-sibling::td[20]")).click();
			
			new Call_list_utilities(driver).verifyPN_In_Ext_View_Page(driver, "10");
//			Thread.sleep(10000);
			pmExtension.getDoneButton().click();
			
			pmUser.getLogoutLink().click();
		}
		catch(Exception e)
		{
				new Take_Screenshot().get_Screenshot(driver, method.getName());
				throw e;
		}
		finally
		{
			list.clear();
			list.add(extData[13]);
			list.add(extData[14]);
			list.add(extData[15]);
			list.add(testData[3]);
			list.add(testData[4]);
			list.add(testData[5]);
			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
		}
	}
	
	
	@Test
	public void edit_digital_extension_to_change_active_call_list(Method method) throws Exception
	{
		pmTests.checkTestStatus(method.getName());
		String[] testData = null;
		String [] extData = null;
		String[] credentials = null;
		wait = new WebDriverWait(driver, 20);
		try
		{
			pmExtension = new Extension(driver);
			loginPage = new PM_Login_Page(driver);
			pmMainPge = new PM_Main_Page(driver);
			pmServices = new PM_Services(driver);
			pmUser = new PM_User(driver);
			pmUsers = new PM_Users(driver);
			testData = pmTests.getData(method.getName(), 1);
			extData = pmTests.getData(method.getName(), 3);
			credentials = loginData.getData("test_pm_valid_login", 1);
			
			list = new ArrayList<String>();
			list.add(testData[0]);
			list.add(extData[0]);
			list.add(extData[1]);
			list.add(extData[2]);
			
			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
			
			new ReusableUnits(driver).create_personal_number_for_given_extension
			(driver, method.getName(), ipData, loginData, pmTests, "digital");
			
			driver.findElement(By.xpath("//td[contains(text(),'"+testData[1]+"')]//preceding-sibling::td[19]")).click();
			pmExtension.getPENListButton().click();
			driver.findElement(By.xpath("//img[@id='activateThis9_img']")).click();
			pmExtension.getMultiStepBackButton().click();
			
			List<WebElement> p10 = driver.findElements(By.xpath("//td[contains(text(),'Profile10:Active')]"));
			Assert.assertTrue(p10.size() == 1);
			
			pmExtension.getEditPageApplyButton().click();
			Assert.assertEquals(pmExtension.getResponseMessage(), "Change operation successful for:");
			pmExtension.getDoneButton().click();
			
			driver.findElement(By.xpath("//td[contains(text(),'"+testData[1]+"')]//preceding-sibling::td[20]")).click();
			
			new Call_list_utilities(driver).verifyPN_In_Ext_View_Page(driver, "10");
//			Thread.sleep(10000);
			pmExtension.getDoneButton().click();
			
			pmUser.getLogoutLink().click();
		}
		catch(Exception e)
		{
				new Take_Screenshot().get_Screenshot(driver, method.getName());
				throw e;
		}
		finally
		{
			list.clear();
			list.add(extData[13]);
			list.add(extData[14]);
			list.add(extData[15]);
			list.add(testData[3]);
			list.add(testData[4]);
			list.add(testData[5]);
			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
		}
	}
	
	@Test
	public void edit_virtual_extension_to_change_active_call_list(Method method) throws Exception
	{
		pmTests.checkTestStatus(method.getName());
		String[] testData = null;
		String [] extData = null;
		String[] credentials = null;
		wait = new WebDriverWait(driver, 20);
		try
		{
			pmExtension = new Extension(driver);
			loginPage = new PM_Login_Page(driver);
			pmMainPge = new PM_Main_Page(driver);
			pmServices = new PM_Services(driver);
			pmUser = new PM_User(driver);
			pmUsers = new PM_Users(driver);
			testData = pmTests.getData(method.getName(), 1);
			extData = pmTests.getData(method.getName(), 3);
			credentials = loginData.getData("test_pm_valid_login", 1);
			
			list = new ArrayList<String>();
			list.add(testData[0]);
			list.add(extData[0]);
			list.add(extData[1]);
			list.add(extData[2]);
			
			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
			
			new ReusableUnits(driver).create_personal_number_for_given_extension
			(driver, method.getName(), ipData, loginData, pmTests, "virtual");
			
			driver.findElement(By.xpath("//td[contains(text(),'"+testData[1]+"')]//preceding-sibling::td[19]")).click();
			pmExtension.getPENListButton().click();
			driver.findElement(By.xpath("//img[@id='activateThis9_img']")).click();
			pmExtension.getMultiStepBackButton().click();
			
			List<WebElement> p10 = driver.findElements(By.xpath("//td[contains(text(),'Profile10:Active')]"));
			Assert.assertTrue(p10.size() == 1);
			
			pmExtension.getEditPageApplyButton().click();
			Assert.assertEquals(pmExtension.getResponseMessage(), "Change operation successful for:");
			pmExtension.getDoneButton().click();
			
			driver.findElement(By.xpath("//td[contains(text(),'"+testData[1]+"')]//preceding-sibling::td[20]")).click();
			
			new Call_list_utilities(driver).verifyPN_In_Ext_View_Page(driver, "10");
//			Thread.sleep(10000);
			pmExtension.getDoneButton().click();
			
			pmUser.getLogoutLink().click();
		}
		catch(Exception e)
		{
				new Take_Screenshot().get_Screenshot(driver, method.getName());
				throw e;
		}
		finally
		{
			list.clear();
			list.add(extData[13]);
			list.add(extData[14]);
			list.add(extData[15]);
			list.add(testData[2]);
			list.add(testData[3]);
			list.add(testData[4]);
			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
		}
	}
	
	@Test
	public void edit_sip_dect_extension_to_change_active_call_list(Method method) throws Exception
	{
		pmTests.checkTestStatus(method.getName());
		String[] testData = null;
		String [] extData = null;
		String[] credentials = null;
		wait = new WebDriverWait(driver, 20);
		try
		{
			pmExtension = new Extension(driver);
			loginPage = new PM_Login_Page(driver);
			pmMainPge = new PM_Main_Page(driver);
			pmServices = new PM_Services(driver);
			pmUser = new PM_User(driver);
			pmUsers = new PM_Users(driver);
			testData = pmTests.getData(method.getName(), 1);
			extData = pmTests.getData(method.getName(), 3);
			credentials = loginData.getData("test_pm_valid_login", 1);
			
			list = new ArrayList<String>();
			list.add(testData[0]);
			list.add(extData[0]);
			list.add(extData[1]);
			list.add(extData[2]);
			
			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
			
			new ReusableUnits(driver).create_personal_number_for_given_extension
			(driver, method.getName(), ipData, loginData, pmTests, "sip-dect");
			
			driver.findElement(By.xpath("//td[contains(text(),'"+testData[1]+"')]//preceding-sibling::td[19]")).click();
			pmExtension.getPENListButton().click();
			driver.findElement(By.xpath("//img[@id='activateThis9_img']")).click();
			pmExtension.getMultiStepBackButton().click();
			
			List<WebElement> p10 = driver.findElements(By.xpath("//td[contains(text(),'Profile10:Active')]"));
			Assert.assertTrue(p10.size() == 1);
			
			pmExtension.getEditPageApplyButton().click();
			Assert.assertEquals(pmExtension.getResponseMessage(), "Change operation successful for:");
			pmExtension.getDoneButton().click();
			
			driver.findElement(By.xpath("//td[contains(text(),'"+testData[1]+"')]//preceding-sibling::td[20]")).click();
			
			new Call_list_utilities(driver).verifyPN_In_Ext_View_Page(driver, "10");
//			Thread.sleep(10000);
			pmExtension.getDoneButton().click();			
			
			pmUser.getLogoutLink().click();
		}
		catch(Exception e)
		{
				new Take_Screenshot().get_Screenshot(driver, method.getName());
				throw e;
		}
		finally
		{
			list.clear();
			list.add(extData[13]);
			list.add(extData[14]);
			list.add(extData[15]);
			list.add(testData[5]);
			list.add(testData[6]);
			list.add(testData[7]);
			list.add(testData[8]);
			list.add(testData[9]);
			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
		}
	}
	
}
