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
import utilities.CleanUP;
import utilities.ExecuteCommands;
import utilities.GetMxoneVersionNumber;
import utilities.ReusableUnits;
import utilities.ReusableUnits_Analog_Digital;
import utilities.SelectDropDownValue;
import utilities.Take_Screenshot;

public class PM_DigitalExtension_Test extends ConfigClass
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
	
	
	@Test
	public void test_createDigitalExtension(Method method) throws Exception
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
			list.clear();
			list.add(testData[6]);
			list.add(testData[7]);
			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
		}
	}
	
	@Test
	public void test_edit_digital_extension_toChange_CAT(Method method) throws Exception
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
			List<WebElement> eles = driver.findElements(By.xpath("//td[contains(text(),'Common Category')]//following-sibling::td[contains(text(),'1 - CAT1')]"));
			Assert.assertTrue(eles.size()==1);
			
			pmExtension.getDoneButton().click();
			pmUser.getLogoutLink().click();
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
			list.clear();
			list.add(testData[3]);
			list.add(testData[4]);
			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
		}
	}
	
	
	@Test
	public void test_edit_digital_extension_toChange_First_Last_Names(Method method) throws Exception
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
			list.clear();
			list.add(testData[6]);
			list.add(testData[7]);
			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
		}
	}	
		
		@Test
		public void test_edit_digital_extension_toChange_PhoneModel(Method method) throws Exception
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
				list.clear();
				list.add(testData[4]);
				list.add(testData[5]);
				new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
			}
	}
		
				
		@Test
		public void test_edit_digital_extension_toSet_AgentPosition(Method method) throws Exception
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
				list.clear();
				list.add(testData[3]);
				list.add(testData[4]);
				new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
			}
	}
	
		
		@Test
		public void test_edit_digital_extension_toSet_HotLine(Method method) throws Exception
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
				list.clear();
				list.add(ipExtData[4]);
				list.add(ipExtData[5]);
				list.add(ipExtData[6]);
				list.add(testData[3]);
				list.add(testData[4]);
				list.add(testData[5]);
				list.add(testData[6]);
				list.add(testData[7]);
				list.add(testData[8]);
				list.add(testData[9]);
				new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
			}
	}
	
	
	@Test
	public void test_edit_digital_extension_toDelayed_HotLine(Method method) throws Exception
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
			list.clear();
			list.add(ipExtData[4]);
			list.add(ipExtData[5]);
			list.add(ipExtData[6]);
			list.add(testData[3]);
			list.add(testData[4]);
			list.add(testData[5]);
			list.add(testData[6]);
			list.add(testData[7]);
			list.add(testData[8]);
			list.add(testData[9]);
			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
		}
	}
	
	
	@Test
	public void test_delete_digitalExtension(Method method) throws Exception
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
			list.clear();
			list.add(testData[3]);
			list.add(testData[4]);
			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
		}
	}
	
	
	@Test
	public void test_swap_digitalEquipmentPositions(Method method) throws Exception
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
//			wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("blockUI"))));
//			wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.id("blockUI"))));
			
			List<WebElement> ele3 = driver.findElements(By.xpath("//td[contains(text(),'"+testData[1]+"')]//following-sibling::td[contains(text(),'"+testData[4]+"')]"));
			List<WebElement> ele4 = driver.findElements(By.xpath("//td[contains(text(),'"+testData[3]+"')]//following-sibling::td[contains(text(),'"+testData[2]+"')]"));
			Assert.assertTrue(ele3.size() == 1 && ele4.size() == 1);
			
			pmUser.getLogoutLink().click();
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
			list.clear();
			list.add(testData[5]);
			list.add(testData[6]);
			list.add(testData[7]);
			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
		}
	}
	
	
	@Test
	public void test_create_digitalExt_using_Template(Method method) throws Exception
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
			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
			new ReusableUnits_Analog_Digital(driver).createDigitalTemplate(driver, ipData, loginData, testData[3]);
			
			driver.get(ipData.getData(0, 0));
			pmLoginPage.PM_Login(credentials[0], credentials[1]);
			pmMainPage.getServices().click();
			pmServices.getExtension().click();
			new SelectDropDownValue().selectByValue(pmExtension.getExtensionHomePageTemplateDropDown(),
					"CUST."+testData[3]+"_SN_DigitalExtension");
			pmExtension.getAddButton().click();
			
			new SelectDropDownValue().selectByVisibleText(pmExtension.getSelectExtensionsRange(), testData[1]);
			String version = new GetMxoneVersionNumber(driver).getMxoneVersionNumber(driver);
			System.out.println(version);
			int ver = Integer.parseInt(version);
			System.out.println(ver);
			if(ver >= 72000)
			{
//				wait.until(ExpectedConditions.presenceOfElementLocated(By.id("myDigitalExtension_VO.DIR")));
				pmExtension.setDigitalExtensionNumber(testData[1]);
			}
			else
			{
//				wait.until(ExpectedConditions.presenceOfElementLocated(By.id("myDigitalExtension_VO.DIR")));
				new SelectDropDownValue().selectByVisibleText(pmExtension.getEnterDigitalExtensionNumber(), testData[1]);
			}
			new SelectDropDownValue().selectByIndex(pmExtension.getDigitalCommonCategoryDropDown(), 0);
			pmExtension.setEquipmentPosition(testData[2]);
			
			pmExtension.getApplyButton().click();
			Assert.assertEquals(pmExtension.getResponseMessage(), "Add operation successful for:");
			pmExtension.getDoneButton().click();
			new SelectDropDownValue().selectByVisibleText(pmExtension.getExtensionTypeDropDownHomePage(), "Digital");
			pmExtension.setEnterExtensionNumberTextBox(testData[1]);
			pmExtension.getViewRangeButton().click();
			List<WebElement> eles = driver.findElements(By.xpath("//td[contains(text(),'"+testData[1]+"')]//following-sibling::td[contains(text(),'Digital')]"));
			Assert.assertTrue(eles.size()==1);
			pmUser.getLogoutLink().click();
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
			new CleanUP(driver).deleteTemplate(driver, method.getName(), loginData, testData[3], ipData);
			list.clear();
			list.add(testData[4]);
			list.add(testData[5]);
			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
		}
	}
	
	@Test
	public void test_createUser_with_Digital_Extension(Method method) throws Exception
	{
		list = new ArrayList<String>();
		String[] credentials = loginData.getData("test_pm_valid_login", 1);
		String[] testData = pmTests.getData(method.getName(), 1);
		String[] ipTestData = pmTests.getData(method.getName(), 3);
		try
		{
			pmTests.checkTestStatus(method.getName());
			pmUser = new PM_User(driver);
			pmLoginPage = new PM_Login_Page(driver);
			pmMainPage = new PM_Main_Page(driver);
			pmExtension = new Extension(driver);
			pmServices = new PM_Services(driver);
			
			list.add(ipTestData[0]);
			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
			
			new ReusableUnits(driver).navigateUserToServiceSummaryPage(driver, method.getName(), ipData, loginData, pmTests);
			pmUser.getCreateAndAssignExtensionToUser().click();
			new SelectDropDownValue().selectByVisibleText(pmExtension.getExtensionType(), "Digital");
			pmExtension.getNextButton().click();
			
			new SelectDropDownValue().selectByVisibleText(pmExtension.getSelectExtensionsRange(), ipTestData[1]);
			String version = new GetMxoneVersionNumber(driver).getMxoneVersionNumber(driver);
			System.out.println(version);
			int ver = Integer.parseInt(version);
			System.out.println(ver);
			if(ver >= 72000)
			{
				pmExtension.setDigitalExtensionNumber(ipTestData[1]);
			}
			else
			{
				new SelectDropDownValue().selectByVisibleText(pmExtension.getEnterDigitalExtensionNumber(), ipTestData[1]);
			}
			new SelectDropDownValue().selectByVisibleText(pmExtension.getDigitalPhoneTypeDropDown(), ipTestData[2]);
			new SelectDropDownValue().selectByIndex(pmExtension.getDigitalCommonCategoryDropDown(), 0);
			pmExtension.setEquipmentPosition(ipTestData[3]);
			pmExtension.setDigitalFirstname(testData[4]);
			pmExtension.setDigitalLastname(testData[5]);
			pmExtension.getApplyButton().click();
			
//			pmExtension.getApplyButtonAfterAssigningDigitalExtension().click();
//			pmExtension.getDoneButton().click();
			
//			WebDriverWait wait = new WebDriverWait(driver, 100);
//			wait.until(ExpectedConditions.textToBe(By.className("responseMessage"), "Add operation successful for:"));
			
			Assert.assertEquals(pmExtension.getResponseMessage(), "Add operation successful for:");
			pmExtension.getDoneButton().click();
			
			pmUser.setUserSearchTextBox(testData[0]);
			pmUser.getOnViewRangeButton().click();
			
			List<WebElement> eles = driver.findElements(By.xpath("(//td[contains(text(),'"+testData[0]+"')])[1]//following-sibling::td[contains(text(),'"+ipTestData[1]+"')]"));
			Assert.assertTrue(eles.size()==1);
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
			new CleanUP(driver).deleteUser(driver, ipData, credentials, testData[0]);
			String[] extData = pmTests.getData(method.getName(), 3);
			list.clear();
			list.add(ipTestData[6]);
			list.add(ipTestData[7]);
			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
		}
	}
	
	@Test
	public void test_createUser_with_existing_Digital_Extension(Method method) throws Exception
	{
		list = new ArrayList<String>();
		String[] credentials = loginData.getData("test_pm_valid_login", 1);
		String[] testData = pmTests.getData(method.getName(), 1);
		String[] ipTestData = pmTests.getData(method.getName(), 3);
		try
		{
			pmTests.checkTestStatus(method.getName());
			pmUser = new PM_User(driver);
			pmLoginPage = new PM_Login_Page(driver);
			pmMainPage = new PM_Main_Page(driver);
			pmExtension = new Extension(driver);
			pmServices = new PM_Services(driver);
			
			list.add(ipTestData[0]);
			list.add("KSEXI:DIR="+ipTestData[1]+",CAT=0,EQU="+ipTestData[2]+",ITYPE=0;");
			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
			
			new ReusableUnits(driver).navigateUserToServiceSummaryPage(driver, method.getName(), ipData, loginData, pmTests);
			pmUser.setExistingExtensionField(ipTestData[1]);
			pmUser.getApplyButton().click();
			Assert.assertEquals(pmUser.getResponseMessage().getText().trim(), "Add operation successful for:");
			pmUser.getDoneButton().click();
			
			pmUser.getUserSearchTextBox().clear();
			pmUser.setUserSearchTextBox(testData[0]);
			pmUser.getOnViewRangeButton().click();
			List<WebElement> eles = driver.findElements(By.xpath("(//td[contains(text(),'"+testData[0]+"')])[1]//following-sibling::td[contains(text(),'"+ipTestData[1]+"')]"));
			Assert.assertTrue(eles.size() == 1);
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
			new CleanUP(driver).deleteUser(driver, ipData, credentials, testData[0]);
			list.clear();
			list.add(ipTestData[3]);
			list.add(ipTestData[4]);
			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
		}
	}
	
	
	@Test
	public void test_createUser_with_DigitalExtension_usingTemplate(Method method) throws Exception
	{
		list = new ArrayList<String>();
		String[] credentials = loginData.getData("test_pm_valid_login", 1);
		String[] testData = pmTests.getData(method.getName(), 1);
		String[] ipTestData = pmTests.getData(method.getName(), 3);
		try
		{
			pmTests.checkTestStatus(method.getName());
			pmUser = new PM_User(driver);
			pmLoginPage = new PM_Login_Page(driver);
			pmMainPage = new PM_Main_Page(driver);
			pmExtension = new Extension(driver);
			pmServices = new PM_Services(driver);
			
			list.add(ipTestData[0]);
			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
			new ReusableUnits_Analog_Digital(driver).createDigitalTemplate(driver, ipData, loginData, ipTestData[3]);
			
			new ReusableUnits(driver).navigateUserToServiceSummaryPage(driver, method.getName(), ipData, loginData, pmTests);
			new SelectDropDownValue().selectByValue(pmExtension.getExtensionHomePageTemplateDropDown(),
					"CUST."+ipTestData[3]+"_SN_DigitalExtension");
			pmExtension.getCreateAndAssignExtensionToUser().click();
			
			new SelectDropDownValue().selectByVisibleText(pmExtension.getSelectExtensionsRange(), ipTestData[1]);
			String version = new GetMxoneVersionNumber(driver).getMxoneVersionNumber(driver);
			System.out.println(version);
			int ver = Integer.parseInt(version);
			System.out.println(ver);
			if(ver >= 72000)
			{
				pmExtension.setDigitalExtensionNumber(ipTestData[1]);
			}
			else
			{
				new SelectDropDownValue().selectByVisibleText(pmExtension.getEnterDigitalExtensionNumber(), ipTestData[1]);
			}
			pmExtension.setEquipmentPosition(ipTestData[2]);
			pmExtension.getApplyButton().click();
			pmExtension.getApplyButton().click();
			
			Assert.assertEquals(pmUser.getResponseMessage().getText().trim(), "Add operation successful for:");
			pmUser.getDoneButton().click();
			
			pmUser.getUserSearchTextBox().clear();
			pmUser.setUserSearchTextBox(testData[0]);
			pmUser.getOnViewRangeButton().click();
			List<WebElement> eles = driver.findElements(By.xpath("(//td[contains(text(),'"+testData[0]+"')])[1]//following-sibling::td[contains(text(),'"+ipTestData[1]+"')]"));
			Assert.assertTrue(eles.size() == 1);
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
			new CleanUP(driver).deleteTemplate(driver, method.getName(), loginData, ipTestData[3], ipData);
			new CleanUP(driver).deleteUser(driver, ipData, credentials, testData[0]);
			list.clear();
			list.add(ipTestData[4]);
			list.add(ipTestData[5]);
			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
		}
	}
	
	
	@Test
	public void test_editUser_and_Assign_Existing_DigitalExtension(Method method) throws Exception
	{
		list = new ArrayList<String>();
		String[] credentials = loginData.getData("test_pm_valid_login", 1);
		String[] testData = pmTests.getData(method.getName(), 1);
		String[] ipTestData = pmTests.getData(method.getName(), 3);
		wait = new WebDriverWait(driver, 20);
		try 
		{
			pmTests.checkTestStatus(method.getName());
			pmUser = new PM_User(driver);
			pmLoginPage = new PM_Login_Page(driver);
			pmMainPage = new PM_Main_Page(driver);
			pmExtension = new Extension(driver);
			pmServices = new PM_Services(driver);
			
			list.add(ipTestData[0]);
			list.add("KSEXI:DIR="+ipTestData[1]+",CAT=0,EQU="+ipTestData[2]+",ITYPE=0;");
			
			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
			
			new ReusableUnits(driver).createUser(driver, method.getName(), ipData, loginData, pmTests);
			
			driver.findElement(By.xpath("(//td[contains(text(),'"+testData[0]+"')])[1]//preceding-sibling::td[19]")).click();
			pmUser.getUserEditServiceSummary().click();
			pmUser.setExistingExtensionField(ipTestData[1]);
			pmUser.getApplyButton().click();
			Assert.assertEquals(pmUser.getResponseMessage().getText().trim(), "Change operation successful for:");
			pmUser.getDoneButton().click();
			
			pmUser.getUserSearchTextBox().clear();
			pmUser.setUserSearchTextBox(testData[0]);
			pmUser.getOnViewRangeButton().click();
			List<WebElement> eles = driver.findElements(By.xpath("(//td[contains(text(),'"+testData[0]+"')])[1]//following-sibling::td[contains(text(),'"+ipTestData[1]+"')]"));
			Assert.assertTrue(eles.size() == 1);
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
			new CleanUP(driver).deleteUser(driver, ipData, credentials, testData[0]);
			list.clear();
			list.add(ipTestData[3]);
			list.add(ipTestData[4]);
			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
		}
	}
	
	
	@Test
	public void test_editUser_and_remove_Existing_DigitalExtension(Method method) throws Exception
	{
		list = new ArrayList<String>();
		String[] credentials = loginData.getData("test_pm_valid_login", 1);
		String[] testData = pmTests.getData(method.getName(), 1);
		String[] ipTestData = pmTests.getData(method.getName(), 3);
		wait = new WebDriverWait(driver, 20);
		try 
		{
			pmTests.checkTestStatus(method.getName());
			pmUser = new PM_User(driver);
			pmLoginPage = new PM_Login_Page(driver);
			pmMainPage = new PM_Main_Page(driver);
			pmExtension = new Extension(driver);
			pmServices = new PM_Services(driver);
			
			new ReusableUnits_Analog_Digital(driver).createUser_With_Digital_Extension(driver, method.getName(), ipData, loginData, pmTests,0);
			
			driver.findElement(By.xpath("(//td[contains(text(),'"+testData[0]+"')])[1]//preceding-sibling::td[19]")).click();
			pmUser.getUserEditServiceSummary().click();

			driver.findElement(By.id("removeThis"+ipTestData[1]+"-Digital-SN_link")).click();
			driver.switchTo().alert().accept();
			Thread.sleep(2000);
			pmUser.getApplyButton().click();
			Assert.assertEquals(pmUser.getResponseMessage().getText().trim(), "Change operation successful for:");
			pmUser.getDoneButton().click();
			
			pmUser.getUserSearchTextBox().clear();
			pmUser.setUserSearchTextBox(testData[0]);
			pmUser.getOnViewRangeButton().click();
			List<WebElement> eles = driver.findElements(By.xpath("(//td[contains(text(),'"+testData[0]+"')])[1]//following-sibling::td[contains(text(),'"+ipTestData[1]+"')]"));
			Assert.assertTrue(eles.size() == 0);
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
			new CleanUP(driver).deleteUser(driver, ipData, credentials, testData[0]);
			list.clear();
			list.add(ipTestData[4]);
			list.add(ipTestData[5]);
			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
		}
	}
	
	
	@Test
	public void test_editUser_to_create_DigitalExtension(Method method) throws Exception
	{
		list = new ArrayList<String>();
		String[] credentials = loginData.getData("test_pm_valid_login", 1);
		String[] testData = pmTests.getData(method.getName(), 1);
		String[] ipTestData = pmTests.getData(method.getName(), 3);
		wait = new WebDriverWait(driver, 20);
		try 
		{
			pmTests.checkTestStatus(method.getName());
			pmUser = new PM_User(driver);
			pmLoginPage = new PM_Login_Page(driver);
			pmMainPage = new PM_Main_Page(driver);
			pmExtension = new Extension(driver);
			pmServices = new PM_Services(driver);
			
			list.add(ipTestData[0]);
			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
			
			new ReusableUnits(driver).createUser(driver, method.getName(), ipData, loginData, pmTests);
			
			driver.findElement(By.xpath("(//td[contains(text(),'"+testData[0]+"')])[1]//preceding-sibling::td[19]")).click();
			pmUser.getUserEditServiceSummary().click();
			
			pmUser.getCreateAndAssignExtensionToUser().click();
			new SelectDropDownValue().selectByVisibleText(pmExtension.getExtensionType(), "Digital");
			pmExtension.getNextButton().click();
			
			new SelectDropDownValue().selectByVisibleText(pmExtension.getSelectExtensionsRange(), ipTestData[1]);
			String version = new GetMxoneVersionNumber(driver).getMxoneVersionNumber(driver);
			System.out.println(version);
			int ver = Integer.parseInt(version);
			System.out.println(ver);
			if(ver >= 72000)
			{
				pmExtension.setDigitalExtensionNumber(ipTestData[1]);
			}
			else
			{
				new SelectDropDownValue().selectByVisibleText(pmExtension.getEnterDigitalExtensionNumber(), ipTestData[1]);
			}
			new SelectDropDownValue().selectByVisibleText(pmExtension.getDigitalPhoneTypeDropDown(), ipTestData[2]);
			new SelectDropDownValue().selectByIndex(pmExtension.getDigitalCommonCategoryDropDown(), 0);
			pmExtension.setEquipmentPosition(ipTestData[3]);
			pmExtension.getApplyButton().click();
			pmExtension.getApplyButtonAfterAssigningDigitalExtension().click();
			Assert.assertEquals(pmExtension.getResponseMessage(), "Change operation successful for:");
			pmExtension.getDoneButton().click();
			
			pmUser.getUserSearchTextBox().clear();
			pmUser.setUserSearchTextBox(testData[0]);
			pmUser.getOnViewRangeButton().click();
			
			List<WebElement> eles = driver.findElements(By.xpath("(//td[contains(text(),'"+testData[0]+"')])[1]//following-sibling::td[contains(text(),'"+ipTestData[1]+"')]"));
			Assert.assertTrue(eles.size()==1);
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
			new CleanUP(driver).deleteUser(driver, ipData, credentials, testData[0]);
			list.clear();
			list.add(ipTestData[4]);
			list.add(ipTestData[5]);
			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
		}
	}
	
}
