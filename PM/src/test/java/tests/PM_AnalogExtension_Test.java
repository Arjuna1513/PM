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
import utilities.ExecuteCommands;
import utilities.ReusableUnits;
import utilities.ReusableUnits_Analog_Digital;
import utilities.SelectDropDownValue;

public class PM_AnalogExtension_Test extends ConfigClass
{
	public ArrayList<String> list;
//	public PM_Login_Page loginPage;
	public PM_User pmUser;
	public Extension pmExtension;
	public PM_Main_Page pmMainPage;
	public PM_Services pmServices;
	WebDriverWait wait = null;
	public PM_Login_Page pmLoginPage;
	public PM_Users pmUsers;
	
	
	/*@Test
	public void test_createDigitalExtension(Method method) throws InterruptedException
	{
		String[] testData = null;
		try
		{
			System.out.println(pmTests);
			pmTests.checkTestStatus(method.getName());
			pmUser = new PM_User(driver);
			testData = pmTests.getData(method.getName(), 1);
			list = new ArrayList<String>();
			list.add(testData[0]);
			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
			new ReusableUnits_Analog_Digital(driver).createDigitalExtension(driver, method.getName(), ipData, pmTests, loginData, 0);
			pmUser.getLogoutLink().click();
		}
		finally
		{
			list.clear();
			list.add(testData[6]);
			list.add(testData[7]);
			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
		}
	}*/
	
	/*@Test
	public void test_edit_digital_extension_toChange_CAT(Method method) throws InterruptedException
	{
		String[] testData = null;
		String[] credentials = null;
		try
		{
			System.out.println(pmTests);
			pmTests.checkTestStatus(method.getName());
			pmUser = new PM_User(driver);
			pmLoginPage = new PM_Login_Page(driver);
			pmMainPage = new PM_Main_Page(driver);
			pmExtension = new Extension(driver);
			pmServices = new PM_Services(driver);
			testData = pmTests.getData(method.getName(), 1);
			credentials = loginData.getData("test_pm_valid_login", 1);
			
			list = new ArrayList<String>();
			list.add(testData[0]);
			list.add("KSEXI:DIR="+testData[1]+",CAT=0,EQU="+testData[2]+",ITYPE=0;");
			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
			
			driver.get(ipData.getData(0, 0));
			pmLoginPage.PM_Login(credentials[0], credentials[1]);
			pmMainPage.getServices().click();
			pmServices.getExtension().click();
			new SelectDropDownValue().selectByVisibleText(pmExtension.getExtensionTypeDropDownHomePage(), "Digital");
			pmExtension.setEnterExtensionNumberTextBox(testData[1]);
			pmExtension.getViewRangeButton().click();
			
			driver.findElement(By.xpath("//td[contains(text(),'"+testData[1]+"')]//preceding-sibling::td[19]")).click();
			new SelectDropDownValue().selectByIndex(pmExtension.getDigitalCommonCategoryDropDown(), 1);
			pmExtension.getEditPageApplyButton().click();
			Assert.assertEquals(pmExtension.getResponseMessage(), "Change operation successful for:");
			pmExtension.getDoneButton().click();
			
			pmExtension.getEnterExtensionNumberTextBox().clear();
			pmExtension.setEnterExtensionNumberTextBox(testData[1]);
			pmExtension.getViewRangeButton().click();
			
			driver.findElement(By.xpath("//td[contains(text(),'"+testData[1]+"')]//preceding-sibling::td[20]")).click();
			List<WebElement> eles = driver.findElements(By.xpath("//td[contains(text(),'Common Category')]//following-sibling::td[contains(text(),'1 - CAT 1')]"));
			Assert.assertTrue(eles.size()==1);
			
			pmExtension.getDoneButton().click();
			pmUser.getLogoutLink().click();
		}
		finally
		{
			list.clear();
			list.add(testData[3]);
			list.add(testData[4]);
			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
		}
	}*/
	
	
/*	@Test
	public void test_edit_digital_extension_toChange_First_Last_Names(Method method) throws InterruptedException
	{
		String[] testData = null;
		String[] credentials = null;
		try
		{
			System.out.println(pmTests);
			pmTests.checkTestStatus(method.getName());
			pmUser = new PM_User(driver);
			pmLoginPage = new PM_Login_Page(driver);
			pmMainPage = new PM_Main_Page(driver);
			pmExtension = new Extension(driver);
			pmServices = new PM_Services(driver);
			testData = pmTests.getData(method.getName(), 1);
			credentials = loginData.getData("test_pm_valid_login", 1);
			
			list = new ArrayList<String>();
			list.add(testData[0]);
			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
			new ReusableUnits_Analog_Digital(driver).createDigitalExtension(driver, method.getName(), ipData, pmTests, loginData, 0);
			
			driver.findElement(By.xpath("//td[contains(text(),'"+testData[1]+"')]//preceding-sibling::td[19]")).click();
			pmExtension.getDigitalFirstname().clear();
			pmExtension.getDigitalLastname().clear();
			
			pmExtension.setDigitalFirstname("Mitel");
			pmExtension.setDigitalLastname("Networks");
			pmExtension.getEditPageApplyButton().click();
			Assert.assertEquals(pmExtension.getResponseMessage(), "Change operation successful for:");
			pmExtension.getDoneButton().click();
			
			pmExtension.getEnterExtensionNumberTextBox().clear();
			pmExtension.setEnterExtensionNumberTextBox(testData[1]);
			pmExtension.getViewRangeButton().click();
			
			driver.findElement(By.xpath("//td[contains(text(),'"+testData[1]+"')]//preceding-sibling::td[20]")).click();
			List<WebElement> eles = driver.findElements(By.xpath("//td[contains(text(),'First Name')]//following-sibling::td[contains(text(),'Mitel')]"));
			List<WebElement> eles2 = driver.findElements(By.xpath("//td[contains(text(),'Last Name')]//following-sibling::td[contains(text(),'Networks')]"));
			Assert.assertTrue(eles.size()==1 && eles2.size()==1);
			
			pmExtension.getDoneButton().click();
			pmUser.getLogoutLink().click();
		}
		finally
		{
			list.clear();
			list.add(testData[6]);
			list.add(testData[7]);
			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
		}
	}*/	
		/*
		@Test
		public void test_edit_digital_extension_toChange_PhoneModel(Method method) throws InterruptedException
		{
			String[] testData = null;
			String[] credentials = null;
			try
			{
				System.out.println(pmTests);
				pmTests.checkTestStatus(method.getName());
				pmUser = new PM_User(driver);
				pmLoginPage = new PM_Login_Page(driver);
				pmMainPage = new PM_Main_Page(driver);
				pmExtension = new Extension(driver);
				pmServices = new PM_Services(driver);
				testData = pmTests.getData(method.getName(), 1);
				credentials = loginData.getData("test_pm_valid_login", 1);
				
				list = new ArrayList<String>();
				list.add(testData[0]);
				list.add("KSEXI:DIR="+testData[1]+",CAT=0,EQU="+testData[2]+",ITYPE=0;");
				new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
				
				driver.get(ipData.getData(0, 0));
				pmLoginPage.PM_Login(credentials[0], credentials[1]);
				pmMainPage.getServices().click();
				pmServices.getExtension().click();
				new SelectDropDownValue().selectByVisibleText(pmExtension.getExtensionTypeDropDownHomePage(), "Digital");
				pmExtension.setEnterExtensionNumberTextBox(testData[1]);
				pmExtension.getViewRangeButton().click();
				
				driver.findElement(By.xpath("//td[contains(text(),'"+testData[1]+"')]//preceding-sibling::td[19]")).click();
				new SelectDropDownValue().selectByVisibleText(pmExtension.getDigitalPhoneTypeDropDown(), testData[3]);
				pmExtension.getEditPageApplyButton().click();
				Assert.assertEquals(pmExtension.getResponseMessage(), "Change operation successful for:");
				pmExtension.getDoneButton().click();
				
				pmExtension.getEnterExtensionNumberTextBox().clear();
				pmExtension.setEnterExtensionNumberTextBox(testData[1]);
				pmExtension.getViewRangeButton().click();
				
				driver.findElement(By.xpath("//td[contains(text(),'"+testData[1]+"')]//preceding-sibling::td[20]")).click();
				List<WebElement> eles = driver.findElements(By.xpath("//td[contains(text(),'Phone Type')]//following-sibling::td[contains(text(),'"+testData[3]+"')]"));
				Assert.assertTrue(eles.size()==1);
				pmExtension.getDoneButton().click();
				pmUser.getLogoutLink().click();
			}
			finally
			{
				list.clear();
				list.add(testData[4]);
				list.add(testData[5]);
				new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
			}
	}*/
		
		
		/*@Test
		public void test_edit_digital_extension_toSet_AgentPosition(Method method) throws InterruptedException
		{
			String[] testData = null;
			String[] credentials = null;
			try
			{
				System.out.println(pmTests);
				pmTests.checkTestStatus(method.getName());
				pmUser = new PM_User(driver);
				pmLoginPage = new PM_Login_Page(driver);
				pmMainPage = new PM_Main_Page(driver);
				pmExtension = new Extension(driver);
				pmServices = new PM_Services(driver);
				testData = pmTests.getData(method.getName(), 1);
				credentials = loginData.getData("test_pm_valid_login", 1);
				
				list = new ArrayList<String>();
				list.add(testData[0]);
				list.add("KSEXI:DIR="+testData[1]+",CAT=0,EQU="+testData[2]+",ITYPE=0;");
				new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
				
				driver.get(ipData.getData(0, 0));
				pmLoginPage.PM_Login(credentials[0], credentials[1]);
				pmMainPage.getServices().click();
				pmServices.getExtension().click();
				new SelectDropDownValue().selectByVisibleText(pmExtension.getExtensionTypeDropDownHomePage(), "Digital");
				pmExtension.setEnterExtensionNumberTextBox(testData[1]);
				pmExtension.getViewRangeButton().click();
				
				driver.findElement(By.xpath("//td[contains(text(),'"+testData[1]+"')]//preceding-sibling::td[19]")).click();
				pmExtension.getAdvanceButton().click();
				pmExtension.getDigitalAgentPosition().click();
				pmExtension.getEditPageApplyButton().click();
				Assert.assertEquals(pmExtension.getResponseMessage(), "Change operation successful for:");
				pmExtension.getDoneButton().click();
				
				pmExtension.getEnterExtensionNumberTextBox().clear();
				pmExtension.setEnterExtensionNumberTextBox(testData[1]);
				pmExtension.getViewRangeButton().click();
				
				driver.findElement(By.xpath("//td[contains(text(),'"+testData[1]+"')]//preceding-sibling::td[20]")).click();
				List<WebElement> eles = driver.findElements(By.xpath("//td[contains(text(),'Allow to Initiate as Agent Position')]//following-sibling::td[contains(text(),'Yes')]"));
				Assert.assertTrue(eles.size()==1);
				pmExtension.getDoneButton().click();
				pmUser.getLogoutLink().click();
			}
			finally
			{
				list.clear();
				list.add(testData[3]);
				list.add(testData[4]);
				new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
			}
	}*/
	
		
		/*@Test
		public void test_edit_digital_extension_toSet_HotLine(Method method) throws InterruptedException
		{
			String[] testData = null;
			String[] credentials = null;
			String[] ipExtData = null;
			try
			{
				System.out.println(pmTests);
				pmTests.checkTestStatus(method.getName());
				pmUser = new PM_User(driver);
				pmLoginPage = new PM_Login_Page(driver);
				pmMainPage = new PM_Main_Page(driver);
				pmExtension = new Extension(driver);
				pmServices = new PM_Services(driver);
				testData = pmTests.getData(method.getName(), 1);
				ipExtData = pmTests.getData(method.getName(), 3);
				credentials = loginData.getData("test_pm_valid_login", 1);
				
				list = new ArrayList<String>();
				list.add(testData[0]);
				list.add("KSEXI:DIR="+testData[1]+",CAT=0,EQU="+testData[2]+",ITYPE=0;");
				list.add(ipExtData[0]);
				list.add(ipExtData[1]);
				list.add(ipExtData[2]);
				new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
				
				driver.get(ipData.getData(0, 0));
				pmLoginPage.PM_Login(credentials[0], credentials[1]);
				pmMainPage.getServices().click();
				pmServices.getExtension().click();
				new SelectDropDownValue().selectByVisibleText(pmExtension.getExtensionTypeDropDownHomePage(), "Digital");
				pmExtension.setEnterExtensionNumberTextBox(testData[1]);
				pmExtension.getViewRangeButton().click();
				
				driver.findElement(By.xpath("//td[contains(text(),'"+testData[1]+"')]//preceding-sibling::td[19]")).click();
				pmExtension.getAdvanceButton().click();
				new SelectDropDownValue().selectByVisibleText(pmExtension.getHotLineDelayedHotLineDropDown(), "Hot-line");
				pmExtension.setHotLineNumberTextBox(ipExtData[3]);
				pmExtension.getEditPageApplyButton().click();
				Assert.assertEquals(pmExtension.getResponseMessage(), "Change operation successful for:");
				pmExtension.getDoneButton().click();
				
				pmExtension.getEnterExtensionNumberTextBox().clear();
				pmExtension.setEnterExtensionNumberTextBox(testData[1]);
				pmExtension.getViewRangeButton().click();
				
				driver.findElement(By.xpath("//td[contains(text(),'"+testData[1]+"')]//preceding-sibling::td[20]")).click();
				List<WebElement> eles = driver.findElements(By.xpath("//td[contains(text(),'Phone Type')]//following-sibling::td[contains(text(),'Hot-line')]"));
				Assert.assertTrue(eles.size()==1);
				pmExtension.getDoneButton().click();
				
				driver.findElement(By.xpath("//td[contains(text(),'"+testData[1]+"')]//preceding-sibling::td[19]")).click();
				pmExtension.getAdvanceButton().click();
				new SelectDropDownValue().selectByVisibleText(pmExtension.getHotLineDelayedHotLineDropDown(), "Normal");
//				pmExtension.setHotLineNumberTextBox(ipExtData[3]);
				pmExtension.getEditPageApplyButton().click();
				Assert.assertEquals(pmExtension.getResponseMessage(), "Change operation successful for:");
				pmExtension.getDoneButton().click();
				
				driver.findElement(By.xpath("//td[contains(text(),'"+testData[1]+"')]//preceding-sibling::td[20]")).click();
				List<WebElement> eles1 = driver.findElements(By.xpath("//td[contains(text(),'Phone Type')]//following-sibling::td[contains(text(),'Hot-line')]"));
				Assert.assertTrue(eles1.size()==0);
				pmExtension.getDoneButton().click();
				
				pmUser.getLogoutLink().click();
			}
			finally
			{
				list.clear();
				list.add(ipExtData[4]);
				list.add(ipExtData[5]);
				list.add(ipExtData[6]);
				list.add(testData[3]);
				list.add(testData[4]);
				new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
			}
	}*/
	
	
	/*@Test
	public void test_edit_digital_extension_toDelayed_HotLine(Method method) throws InterruptedException
	{
		String[] testData = null;
		String[] credentials = null;
		String[] ipExtData = null;
		try
		{
			System.out.println(pmTests);
			pmTests.checkTestStatus(method.getName());
			pmUser = new PM_User(driver);
			pmLoginPage = new PM_Login_Page(driver);
			pmMainPage = new PM_Main_Page(driver);
			pmExtension = new Extension(driver);
			pmServices = new PM_Services(driver);
			testData = pmTests.getData(method.getName(), 1);
			ipExtData = pmTests.getData(method.getName(), 3);
			credentials = loginData.getData("test_pm_valid_login", 1);
			
			list = new ArrayList<String>();
			list.add(testData[0]);
			list.add("KSEXI:DIR="+testData[1]+",CAT=0,EQU="+testData[2]+",ITYPE=0;");
			list.add(ipExtData[0]);
			list.add(ipExtData[1]);
			list.add(ipExtData[2]);
			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
			
			driver.get(ipData.getData(0, 0));
			pmLoginPage.PM_Login(credentials[0], credentials[1]);
			pmMainPage.getServices().click();
			pmServices.getExtension().click();
			new SelectDropDownValue().selectByVisibleText(pmExtension.getExtensionTypeDropDownHomePage(), "Digital");
			pmExtension.setEnterExtensionNumberTextBox(testData[1]);
			pmExtension.getViewRangeButton().click();
			
			driver.findElement(By.xpath("//td[contains(text(),'"+testData[1]+"')]//preceding-sibling::td[19]")).click();
			pmExtension.getAdvanceButton().click();
			new SelectDropDownValue().selectByVisibleText(pmExtension.getHotLineDelayedHotLineDropDown(), "Delayed hot-line");
			pmExtension.setHotLineNumberTextBox(ipExtData[3]);
			pmExtension.getEditPageApplyButton().click();
			Assert.assertEquals(pmExtension.getResponseMessage(), "Change operation successful for:");
			pmExtension.getDoneButton().click();
			
			pmExtension.getEnterExtensionNumberTextBox().clear();
			pmExtension.setEnterExtensionNumberTextBox(testData[1]);
			pmExtension.getViewRangeButton().click();
			
			driver.findElement(By.xpath("//td[contains(text(),'"+testData[1]+"')]//preceding-sibling::td[20]")).click();
			List<WebElement> eles = driver.findElements(By.xpath("//td[contains(text(),'Phone Type')]//following-sibling::td[contains(text(),'Delayed hot-line')]"));
			Assert.assertTrue(eles.size()==1);
			pmExtension.getDoneButton().click();
			
			driver.findElement(By.xpath("//td[contains(text(),'"+testData[1]+"')]//preceding-sibling::td[19]")).click();
			pmExtension.getAdvanceButton().click();
			new SelectDropDownValue().selectByVisibleText(pmExtension.getHotLineDelayedHotLineDropDown(), "Normal");
//			pmExtension.setHotLineNumberTextBox(ipExtData[3]);
			pmExtension.getEditPageApplyButton().click();
			Assert.assertEquals(pmExtension.getResponseMessage(), "Change operation successful for:");
			pmExtension.getDoneButton().click();
			
			driver.findElement(By.xpath("//td[contains(text(),'"+testData[1]+"')]//preceding-sibling::td[20]")).click();
			List<WebElement> eles1 = driver.findElements(By.xpath("//td[contains(text(),'Phone Type')]//following-sibling::td[contains(text(),'Delayed hot-line')]"));
			Assert.assertTrue(eles1.size()==0);
			pmExtension.getDoneButton().click();
			
			pmExtension.getEnterExtensionNumberTextBox().clear();
			pmExtension.setEnterExtensionNumberTextBox(testData[1]);
			pmExtension.getViewRangeButton().click();
			pmUser.getLogoutLink().click();
		}
		finally
		{
			list.clear();
			list.add(ipExtData[4]);
			list.add(ipExtData[5]);
			list.add(ipExtData[6]);
			list.add(testData[3]);
			list.add(testData[4]);
			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
		}
	}*/
	
	
	/*@Test
	public void test_delete_digitalExtension(Method method) throws InterruptedException
	{
		String[] testData = null;
		String[] credentials = null;
		try
		{
			System.out.println(pmTests);
			pmTests.checkTestStatus(method.getName());
			pmUser = new PM_User(driver);
			pmLoginPage = new PM_Login_Page(driver);
			pmMainPage = new PM_Main_Page(driver);
			pmExtension = new Extension(driver);
			pmServices = new PM_Services(driver);
			testData = pmTests.getData(method.getName(), 1);
			credentials = loginData.getData("test_pm_valid_login", 1);
			
			list = new ArrayList<String>();
			list.add(testData[0]);
			list.add("KSEXI:DIR="+testData[1]+",CAT=0,EQU="+testData[2]+",ITYPE=0;");
			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
			
			driver.get(ipData.getData(0, 0));
			pmLoginPage.PM_Login(credentials[0], credentials[1]);
			pmMainPage.getServices().click();
			pmServices.getExtension().click();
			new SelectDropDownValue().selectByVisibleText(pmExtension.getExtensionTypeDropDownHomePage(), "Digital");
			pmExtension.setEnterExtensionNumberTextBox(testData[1]);
			pmExtension.getViewRangeButton().click();
			driver.findElement(By.xpath("//td[contains(text(),'"+testData[1]+"')]//preceding-sibling::td[18]")).click();
			driver.switchTo().alert().accept();
			Assert.assertEquals(pmExtension.getResponseMessage(), "Remove operation successful for:");
			pmUser.getLogoutLink().click();
		}
		finally
		{
			list.clear();
			list.add(testData[3]);
			list.add(testData[4]);
			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
		}
	}*/
	
	
	@Test
	public void test_swap_digitalEquipmentPositions(Method method) throws InterruptedException
	{
		String[] testData = null;
		String[] credentials = null;
		wait = new WebDriverWait(driver, 20);
		try
		{
			System.out.println(pmTests);
			pmTests.checkTestStatus(method.getName());
			pmUser = new PM_User(driver);
			pmLoginPage = new PM_Login_Page(driver);
			pmMainPage = new PM_Main_Page(driver);
			pmExtension = new Extension(driver);
			pmServices = new PM_Services(driver);
			testData = pmTests.getData(method.getName(), 1);
			credentials = loginData.getData("test_pm_valid_login", 1);
			
			list = new ArrayList<String>();
			list.add(testData[0]);
			list.add("KSEXI:DIR="+testData[1]+",CAT=0,EQU="+testData[2]+",ITYPE=0;");
			list.add("KSEXI:DIR="+testData[3]+",CAT=0,EQU="+testData[4]+",ITYPE=0;");
			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
			
			driver.get(ipData.getData(0, 0));
			pmLoginPage.PM_Login(credentials[0], credentials[1]);
			pmMainPage.getServices().click();
			pmServices.getExtension().click();
			new SelectDropDownValue().selectByVisibleText(pmExtension.getExtensionTypeDropDownHomePage(), "Digital");
			pmExtension.setEnterExtensionNumberTextBox(testData[1]+"-"+testData[3]);
			pmExtension.getViewRangeButton().click();
			
			List<WebElement> ele1 = driver.findElements(By.xpath("//td[contains(text(),'"+testData[1]+"')]//following-sibling::td[contains(text(),'"+testData[2]+"')]"));
			List<WebElement> ele2 = driver.findElements(By.xpath("//td[contains(text(),'"+testData[3]+"')]//following-sibling::td[contains(text(),'"+testData[4]+"')]"));
			Assert.assertTrue(ele1.size() == 1 && ele2.size() == 1);
			
			driver.findElement(By.xpath("//td[contains(text(),'"+testData[1]+"')]//following-sibling::td[contains(text(),'"+testData[2]+"')]//preceding-sibling::td[27]")).click();
			driver.findElement(By.xpath("//td[contains(text(),'"+testData[3]+"')]//following-sibling::td[contains(text(),'"+testData[4]+"')]//preceding-sibling::td[27]")).click();
			pmExtension.getSwapButton().click();
			driver.switchTo().alert().accept();
			wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("blockUI"))));
			wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.id("blockUI"))));
			
			List<WebElement> ele3 = driver.findElements(By.xpath("//td[contains(text(),'"+testData[1]+"')]//following-sibling::td[contains(text(),'"+testData[4]+"')]"));
			List<WebElement> ele4 = driver.findElements(By.xpath("//td[contains(text(),'"+testData[3]+"')]//following-sibling::td[contains(text(),'"+testData[2]+"')]"));
			Assert.assertTrue(ele3.size() == 1 && ele4.size() == 1);
			
			pmUser.getLogoutLink().click();
		}
		finally
		{
			list.clear();
			list.add(testData[5]);
			list.add(testData[6]);
			list.add(testData[7]);
			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
		}
	}
}
