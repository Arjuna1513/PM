package tests;

import java.lang.reflect.Method;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.ConfigClass;
//import junit.framework.Assert;
import pm_pom_classes.Extension;
import pm_pom_classes.PM_Login_Page;
import pm_pom_classes.PM_Main_Page;
import pm_pom_classes.PM_Services;
import pm_pom_classes.PM_User;
import pm_pom_classes.PM_Users;
import utilities.ExecuteCommands;
import utilities.ReusableUnits;
import utilities.SelectDropDownValue;
import utilities.Take_Screenshot;

public class PM_MultiTerminal_Test extends ConfigClass
{
	public ArrayList<String> list;
	public PM_Login_Page loginPage;
	public PM_User pmUser;
	public Extension pmExtension;
	public PM_Main_Page pmMainPge;
	public PM_Services pmServices;
	WebDriverWait wait = null;
	public PM_Login_Page pmLoginPge;
	public PM_Users pmUsers;
	
	
	@Test
	public void test_create_multi_terminal_extension(Method method) throws Exception
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
			new ReusableUnits(driver).create_multi_TerminalExtension(driver, method.getName(), ipData, loginData, pmTests,0,"none");
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
			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
		}
	}
	
	
	@Test
	public void test_create_multi_terminal_WithIP(Method method) throws Exception
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
			new ReusableUnits(driver).create_multi_TerminalExtension(driver, method.getName(), ipData, loginData, pmTests,0, "ip");
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
			list.add(testData[8]);
			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
		}
	}
	
	
	@Test
	public void test_create_multi_terminal_WithSIP_Auto(Method method) throws Exception
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
			new ReusableUnits(driver).create_multi_TerminalExtension(driver, method.getName(), ipData, loginData, pmTests,0, "sipauto");
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
			list.add(testData[8]);
			list.add(testData[9]);
			list.add(testData[10]);
			list.add(testData[11]);
			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
		}
	}
	
	@Test
	public void test_create_multi_terminal_With_MobileExtension(Method method) throws Exception
	{
		String[] testData = null;
		try
		{
			System.out.println(pmTests);
			pmTests.checkTestStatus(method.getName());
			pmExtension = new Extension(driver);
			loginPage = new PM_Login_Page(driver);
			pmMainPge = new PM_Main_Page(driver);
			pmServices = new PM_Services(driver);
			pmUser = new PM_User(driver);
			testData = pmTests.getData(method.getName(), 1);
			list = new ArrayList<String>();
			list.add(testData[0]);
			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
			new ReusableUnits(driver).create_multi_TerminalExtension(driver, method.getName(), ipData, loginData, pmTests,0, "mobile");
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
			pmMainPge.getServices().click();
			pmServices.getExtension().click();
			new SelectDropDownValue().selectByVisibleText(pmExtension.getExtensionTypeDropDownHomePage(), "Multi-Terminal");
			pmExtension.setEnterExtensionNumberTextBox(testData[1]);
			pmExtension.getViewRangeButton().click();
			driver.findElement(By.xpath("//td[contains(text(),'"+testData[1]+"')]//preceding-sibling::td[18]")).click();
			driver.switchTo().alert().accept();
			Assert.assertEquals(pmUser.getResponseMessage().getText().trim(), "Remove operation successful for:");
			pmUser.getLogoutLink().click();
		}
	}
	
}
