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
	
	/*@Test
	public void create_PN_IP_Extension(Method method) throws InterruptedException
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
			
			driver.get(ipData.getData(0, 0));
			loginPage.PM_Login(credentials[0], credentials[1]);
			pmMainPge.getServices().click();
			pmServices.getExtension().click();
			pmExtension.getAddButton().click();
			
			new SelectDropDownValue().selectByVisibleText(pmExtension.getExtensionType(), "IP");
			pmExtension.getNextButton().click();
			
			new SelectDropDownValue().selectByVisibleText(pmExtension.getSelectExtensionsRange(), testData[1]);
			String version = new GetMxoneVersionNumber(driver).getMxoneVersionNumber(driver);
			System.out.println(version);
			int ver = Integer.parseInt(version);
			System.out.println(ver);
			if(ver >= 720000)
			{
				pmExtension.setSingleExtensionValue(testData[1]);
			}
			else
			{
				new SelectDropDownValue().selectByVisibleText(pmExtension.getSingleExtensionDropDown(), testData[1]);
			}
			new SelectDropDownValue().selectByIndex(pmExtension.getMyCSPNameDropDown(), 0);
			new SelectDropDownValue().selectByIndex(pmExtension.getServerDropDown(), 1);
			
			pmExtension.getPENListButton().click();
			
			new Call_list_utilities(driver).create_extension_with_personalNumber(driver, method.getName(),
					loginData, pmTests, ipData);
			Thread.sleep(1000);
			driver.findElement(By.xpath("(//img//following::input[10])[3]")).click();
//			pmExtension.getMultiStepBackButton().click();
			Thread.sleep(1000);
			pmExtension.getApplyButton().click();
			Assert.assertEquals(pmExtension.getResponseMessage(), "Add operation successful for:");
			pmExtension.getDoneButton().click();
			
			pmExtension.setEnterExtensionNumberTextBox(testData[1]);
			pmExtension.getViewRangeButton().click();
			WebDriverWait wait = new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("blockUI")));
			driver.findElement(By.xpath("//td[contains(text(),'"+testData[1]+"')]//preceding-sibling::td[20]")).click();
		
			new Call_list_utilities(driver).verifyPN_In_Ext_View_Page(driver, "1");
			pmExtension.getDoneButton().click();
			
			pmUser.getLogoutLink().click();
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
	public void create_PN_Analog_Extension(Method method) throws InterruptedException
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
			
			driver.get(ipData.getData(0, 0));
			loginPage.PM_Login(credentials[0], credentials[1]);
			pmMainPge.getServices().click();
			pmServices.getExtension().click();
			pmExtension.getAddButton().click();
			
			new SelectDropDownValue().selectByVisibleText(pmExtension.getExtensionType(), "Analog");
			pmExtension.getNextButton().click();
			
			new SelectDropDownValue().selectByVisibleText(pmExtension.getSelectExtensionsRange(), testData[1]);
			String version = new GetMxoneVersionNumber(driver).getMxoneVersionNumber(driver);
			System.out.println(version);
			int ver = Integer.parseInt(version);
			System.out.println(ver);
			if(ver >= 720000)
			{
				pmExtension.setEnterAnalogDirectoryNumber(testData[1]);
			}
			else
			{
				new SelectDropDownValue().selectByVisibleText(pmExtension.getAnalogDirctoryDropDown(), testData[1]);
			}
			new SelectDropDownValue().selectByIndex(pmExtension.getCommonCategoryDropDown(), 0);
			pmExtension.setEquipmentPosition(testData[2]);
//			new SelectDropDownValue().selectByVisibleText(pmExtension.getServerDropDown(), testData[3]);
			
			pmExtension.getPENListButton().click();
			
			new Call_list_utilities(driver).create_extension_with_personalNumber(driver, method.getName(),
					loginData, pmTests, ipData);
			pmExtension.getMultiStepBackButton().click();
			pmExtension.getApplyButton().click();
			Assert.assertEquals(pmExtension.getResponseMessage(), "Add operation successful for:");
			pmExtension.getDoneButton().click();
			
			new SelectDropDownValue().selectByVisibleText(pmExtension.getExtensionTypeDropDownHomePage(), "Analog");
			pmExtension.setEnterExtensionNumberTextBox(testData[1]);
			pmExtension.getViewRangeButton().click();
			WebDriverWait wait = new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("blockUI")));
			driver.findElement(By.xpath("//td[contains(text(),'"+testData[1]+"')]//preceding-sibling::td[20]")).click();
		
			new Call_list_utilities(driver).verifyPN_In_Ext_View_Page(driver, "1");
			pmExtension.getDoneButton().click();
			
			pmUser.getLogoutLink().click();
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
	public void create_PN_Digital_Extension(Method method) throws InterruptedException
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
			
			driver.get(ipData.getData(0, 0));
			loginPage.PM_Login(credentials[0], credentials[1]);
			pmMainPge.getServices().click();
			pmServices.getExtension().click();
			pmExtension.getAddButton().click();
			
			new SelectDropDownValue().selectByVisibleText(pmExtension.getExtensionType(), "Digital");
			pmExtension.getNextButton().click();
			new SelectDropDownValue().selectByVisibleText(pmExtension.getSelectExtensionsRange(), testData[1]);
			String version = new GetMxoneVersionNumber(driver).getMxoneVersionNumber(driver);
			System.out.println(version);
			int ver = Integer.parseInt(version);
			System.out.println(ver);
			if(ver >= 720000)
			{
				pmExtension.setDigitalExtensionNumber(testData[1]);
			}
			else
			{
				new SelectDropDownValue().selectByVisibleText(pmExtension.getEnterDigitalExtensionNumber(), testData[1]);
			}
			new SelectDropDownValue().selectByIndex(pmExtension.getDigitalPhoneTypeDropDown(), 1);
			new SelectDropDownValue().selectByIndex(pmExtension.getDigitalCommonCategoryDropDown(), 0);
			pmExtension.setEquipmentPosition(testData[2]);
//			new SelectDropDownValue().selectByVisibleText(pmExtension.getServerDropDown(), testData[3]);
//			new SelectDropDownValue().selectByVisibleText(pmExtension.getPhoneTypeDropDown(), testData[6]);
			
			pmExtension.getPENListButton().click();
			new Call_list_utilities(driver).create_extension_with_personalNumber(driver, method.getName(),
					loginData, pmTests, ipData);
			pmExtension.getMultiStepBackButton().click();
			
			pmExtension.getApplyButton().click();
			Assert.assertEquals(pmExtension.getResponseMessage(), "Add operation successful for:");
			pmExtension.getDoneButton().click();
			
			new SelectDropDownValue().selectByVisibleText(pmExtension.getExtensionTypeDropDownHomePage(), "Digital");
			pmExtension.setEnterExtensionNumberTextBox(testData[1]);
			pmExtension.getViewRangeButton().click();
			WebDriverWait wait = new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("blockUI")));
			driver.findElement(By.xpath("//td[contains(text(),'"+testData[1]+"')]//preceding-sibling::td[20]")).click();
		
			new Call_list_utilities(driver).verifyPN_In_Ext_View_Page(driver, "1");
			pmExtension.getDoneButton().click();
			
			pmUser.getLogoutLink().click();
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
	public void create_PN_Virtual_Extension(Method method) throws InterruptedException
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
			
			driver.get(ipData.getData(0, 0));
			loginPage.PM_Login(credentials[0], credentials[1]);
			pmMainPge.getServices().click();
			pmServices.getExtension().click();
			pmExtension.getAddButton().click();
			
			new SelectDropDownValue().selectByVisibleText(pmExtension.getExtensionType(), "Virtual");
			pmExtension.getNextButton().click();
			new SelectDropDownValue().selectByVisibleText(pmExtension.getSelectExtensionsRange(), testData[1]);
			String version = new GetMxoneVersionNumber(driver).getMxoneVersionNumber(driver);
			System.out.println(version);
			int ver = Integer.parseInt(version);
			System.out.println(ver);
			if(ver >= 720000)
			{
				pmExtension.setVirtualExtensionTextBox(testData[1]);
			}
			else
			{
				new SelectDropDownValue().selectByVisibleText(pmExtension.getVirtualExtensionTextBox(), testData[1]);
			}
			new SelectDropDownValue().selectByVisibleText(pmExtension.getVirtualExtensionTypeDropDown(), "Virtual");
			new SelectDropDownValue().selectByIndex(pmExtension.getMyCSPNameDropDown(), 0);
			new SelectDropDownValue().selectByIndex(pmExtension.getVirtualExtServerDropDown(), 1);
//			new SelectDropDownValue().selectByVisibleText(pmExtension.getServerDropDown(), testData[3]);
//			new SelectDropDownValue().selectByVisibleText(pmExtension.getPhoneTypeDropDown(), testData[6]);
			
			pmExtension.getPENListButton().click();
			new Call_list_utilities(driver).create_extension_with_personalNumber(driver, method.getName(),
					loginData, pmTests, ipData);
			pmExtension.getMultiStepBackButton().click();
			
			pmExtension.getApplyButton().click();
			Assert.assertEquals(pmExtension.getResponseMessage(), "Add operation successful for:");
			pmExtension.getDoneButton().click();
			
			new SelectDropDownValue().selectByVisibleText(pmExtension.getExtensionTypeDropDownHomePage(), "Virtual");
			pmExtension.setEnterExtensionNumberTextBox(testData[1]);
			pmExtension.getViewRangeButton().click();
			WebDriverWait wait = new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("blockUI")));
			driver.findElement(By.xpath("//td[contains(text(),'"+testData[1]+"')]//preceding-sibling::td[20]")).click();
		
			new Call_list_utilities(driver).verifyPN_In_Ext_View_Page(driver, "1");
			pmExtension.getDoneButton().click();
			
			pmUser.getLogoutLink().click();
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
	public void test_create_PN_SIPDECT_Extension(Method method) throws InterruptedException
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
			
			driver.get(ipData.getData(0, 0));
			loginPage.PM_Login(credentials[0], credentials[1]);
			pmMainPge.getServices().click();
			pmServices.getExtension().click();
			pmExtension.getAddButton().click();
			
			new SelectDropDownValue().selectByVisibleText(pmExtension.getExtensionType(), "SIP DECT");
			pmExtension.getNextButton().click();
			new SelectDropDownValue().selectByVisibleText(pmExtension.getSelectExtensionsRange(), testData[1]);
			String version = new GetMxoneVersionNumber(driver).getMxoneVersionNumber(driver);
			System.out.println(version);
			int ver = Integer.parseInt(version);
			System.out.println(ver);
			if(ver >= 720000)
			{
				pmExtension.setMultiTerminalExtensionTextBox(testData[1]);
			}
			else
			{
				new SelectDropDownValue().selectByVisibleText(pmExtension.getMultiTerminalExtensionDropDown(), testData[1]);
			}
			new SelectDropDownValue().selectByIndex(pmExtension.getMyCSPNameDropDown(), 0);
			new SelectDropDownValue().selectByIndex(pmExtension.getMultiTerminalServerDropDown(), 1);
			
			pmExtension.getAddSipDectTerminalButton().click();
			pmExtension.setSIPDectName(testData[2]);
			pmExtension.setSIPDectDescription1("SIP DECT");
			pmExtension.setSIPDectDescription2("SIP DECT");
			pmExtension.setSIPDectAuthKey(testData[3]);
			pmExtension.setSIPDectIPEINumber(testData[4]);
			pmExtension.getApplyButton().click();
			
			pmExtension.getPENListButton().click();
			new Call_list_utilities(driver).create_extension_with_personalNumber(driver, method.getName(),
					loginData, pmTests, ipData);
			
			pmExtension.getMultiStepBackButton().click();
			
			pmExtension.getApplyButton().click();
			Assert.assertEquals(pmExtension.getResponseMessage(), "Add operation successful for:");
			pmExtension.getDoneButton().click();
			
			new SelectDropDownValue().selectByVisibleText(pmExtension.getExtensionTypeDropDownHomePage(), "Multi-Terminal");
			pmExtension.setEnterExtensionNumberTextBox(testData[1]);
			pmExtension.getViewRangeButton().click();
			
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("blockUI")));
			driver.findElement(By.xpath("//td[contains(text(),'"+testData[1]+"')]//preceding-sibling::td[20]")).click();
		
			new Call_list_utilities(driver).verifyPN_In_Ext_View_Page(driver, "1");
			pmExtension.getDoneButton().click();
			
			pmUser.getLogoutLink().click();
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
	public void test_edit_IP_extension_assign_PN(Method method) throws InterruptedException
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
	public void test_edit_Analog_extension_assign_PN(Method method) throws InterruptedException
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
	public void test_edit_Digital_extension_assign_PN(Method method) throws InterruptedException
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
	public void test_edit_Virtual_extension_assign_PN(Method method) throws InterruptedException
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
	}*/
	
	@Test
	public void test_edit_SIPDECT_Assign_PN(Method method) throws InterruptedException
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
	
}
