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
import org.testng.asserts.Assertion;

import base.ConfigClass;
import pm_pom_classes.Extension;
//import pm_pom_classes.Extension;
import pm_pom_classes.PM_Function_Keys;
import pm_pom_classes.PM_Login_Page;
import pm_pom_classes.PM_Main_Page;
import pm_pom_classes.PM_Services;
import utilities.ExecuteCommands;
import utilities.GetMxoneVersionNumber;
import utilities.ReusableUnits;
import utilities.SelectDropDownValue;
import utilities.Take_Screenshot;

public class PM_FunctionKeys_Test extends ConfigClass
{
	
	ArrayList<String> list;
	public PM_Main_Page pmMainPage;
	public Extension pmExtension;
	public PM_Login_Page pmLogin;
	public PM_Services pmServices;
	public PM_Function_Keys funcKeys;
	public WebDriverWait wait = null;
	
	@Test
	public void test_createDMN_FuncKey(Method method) throws Exception
	{
		String[] testData = null;
		try
		{
			pmTests.checkTestStatus(method.getName());
			pmMainPage = new PM_Main_Page(driver);
			testData = pmTests.getData(method.getName(), 3);
			list = new ArrayList<String>();
			list.add(testData[0]);
			list.add(testData[1]);
			list.add(testData[2]);
			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
			testData = pmTests.getData(method.getName(), 1);
			list.clear();
			list.add(testData[0]);
			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
			new ReusableUnits(driver).createExt_With_FunctionKey(driver, method.getName(), ipData, loginData, pmTests, 0);
			driver.findElement(By.xpath("//td[contains(text(),'"+testData[2]+"')]//preceding-sibling::td[20]")).click();
			List<WebElement> eles  = driver.findElements(By.xpath("//td[contains(text(),'"+testData[8]+"-"+testData[3]+" "+testData[8]+"')]"));
			Assert.assertTrue(eles.size() == 1);
			pmMainPage.getLogoutLink().click();
		}
		catch(Exception e)
		{
				new Take_Screenshot().get_Screenshot(driver, method.getName());
				throw e;
		}
		finally
		{
			list.clear();
			list.add(testData[9]);
			list.add(testData[10]);
			list.add(testData[11]);
			list.add(testData[12]);
			list.add(testData[13]);
			list.add(testData[14]);
			list.add(testData[15]);
			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
		}
	}
	
	@Test
	public void test_createEDN_FuncKey(Method method) throws Exception
	{
		String[] testData = null;
		try
		{
			pmTests.checkTestStatus(method.getName());
			pmMainPage = new PM_Main_Page(driver);
			testData = pmTests.getData(method.getName(), 3);
			list = new ArrayList<String>();
			list.add(testData[0]);
			list.add(testData[1]);
			list.add(testData[2]);
			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
			testData = pmTests.getData(method.getName(), 1);
			list.clear();
			list.add(testData[0]);
			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
			new ReusableUnits(driver).createExt_With_FunctionKey(driver, method.getName(), ipData, loginData, pmTests, 0);
			driver.findElement(By.xpath("//td[contains(text(),'"+testData[2]+"')]//preceding-sibling::td[20]")).click();
			List<WebElement> eles  = driver.findElements(By.xpath("//td[contains(text(),'"+testData[8]+"-"+testData[3]+" "+testData[8]+" "+testData[3]+"')]"));
			Assert.assertTrue(eles.size() == 1);
			pmMainPage.getLogoutLink().click();
		}
		catch(Exception e)
		{
				new Take_Screenshot().get_Screenshot(driver, method.getName());
				throw e;
		}
		finally
		{
			list.clear();
			list.add(testData[9]);
			list.add(testData[10]);
			list.add(testData[11]);
			list.add(testData[12]);
			list.add(testData[13]);
			list.add(testData[14]);
			list.add(testData[15]);
			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
		}
	}	
		
	@Test
	public void test_createGMA_FuncKey(Method method) throws Exception
	{
		String[] testData = null;
		try
		{
			pmTests.checkTestStatus(method.getName());
			pmMainPage = new PM_Main_Page(driver);
			testData = pmTests.getData(method.getName(), 3);
			list = new ArrayList<String>();
			list.add(testData[0]);
			list.add(testData[1]);
			list.add(testData[2]);
			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
			testData = pmTests.getData(method.getName(), 1);
			list.clear();
			list.add(testData[0]);
			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
			new ReusableUnits(driver).createExt_With_FunctionKey(driver, method.getName(), ipData, loginData, pmTests, 0);
			driver.findElement(By.xpath("//td[contains(text(),'"+testData[2]+"')]//preceding-sibling::td[20]")).click();
			List<WebElement> eles  = driver.findElements(By.xpath("//td[contains(text(),'"+testData[8]+"-"+testData[3]+" "+testData[8]+" "+testData[3]+"')]"));
			Assert.assertTrue(eles.size() == 1);
			pmMainPage.getLogoutLink().click();
		}
		catch(Exception e)
		{
				new Take_Screenshot().get_Screenshot(driver, method.getName());
				throw e;
		}
		finally
		{
			list.clear();
			list.add(testData[9]);
			list.add(testData[10]);
			list.add(testData[11]);
			list.add(testData[12]);
			list.add(testData[13]);
			list.add(testData[14]);
			list.add(testData[15]);
			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
		}
	}
	
	
	@Test
	public void test_createMNS_FuncKey(Method method) throws Exception
	{
		String[] testData = null;
		try
		{
			pmTests.checkTestStatus(method.getName());
			pmMainPage = new PM_Main_Page(driver);
			testData = pmTests.getData(method.getName(), 3);
			list = new ArrayList<String>();
			list.add(testData[0]);
			list.add(testData[1]);
			list.add(testData[2]);
			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
			testData = pmTests.getData(method.getName(), 1);
			list.clear();
			list.add(testData[0]);
			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
			new ReusableUnits(driver).createExt_With_FunctionKey(driver, method.getName(), ipData, loginData, pmTests, 0);
			driver.findElement(By.xpath("//td[contains(text(),'"+testData[2]+"')]//preceding-sibling::td[20]")).click();
			List<WebElement> eles  = driver.findElements(By.xpath("//td[contains(text(),'"+testData[8]+"-"+testData[3]+" "+testData[8]+" "+testData[3]+"')]"));
			Assert.assertTrue(eles.size() == 1);
			pmMainPage.getLogoutLink().click();
		}
		catch(Exception e)
		{
				new Take_Screenshot().get_Screenshot(driver, method.getName());
				throw e;
		}
		finally
		{
			list.clear();
			list.add(testData[9]);
			list.add(testData[10]);
			list.add(testData[11]);
			list.add(testData[12]);
			list.add(testData[13]);
			list.add(testData[14]);
			list.add(testData[15]);
			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
		}
	}
	
	
	@Test
	public void test_createTNS_FuncKey(Method method) throws Exception
	{
		String[] testData = null;
		try
		{
			pmTests.checkTestStatus(method.getName());
			pmMainPage = new PM_Main_Page(driver);
			testData = pmTests.getData(method.getName(), 3);
			list = new ArrayList<String>();
			list.add(testData[0]);
			list.add(testData[1]);
			list.add(testData[2]);
			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
			testData = pmTests.getData(method.getName(), 1);
			list.clear();
			list.add(testData[0]);
			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
			new ReusableUnits(driver).createExt_With_FunctionKey(driver, method.getName(), ipData, loginData, pmTests, 0);
			driver.findElement(By.xpath("//td[contains(text(),'"+testData[2]+"')]//preceding-sibling::td[20]")).click();
			List<WebElement> eles  = driver.findElements(By.xpath("//td[contains(text(),'"+testData[8]+"-"+testData[3]+" "+testData[8]+" "+testData[3]+"')]"));
			Assert.assertTrue(eles.size() == 1);
			pmMainPage.getLogoutLink().click();
		}
		catch(Exception e)
		{
				new Take_Screenshot().get_Screenshot(driver, method.getName());
				throw e;
		}
		finally
		{
			list.clear();
			list.add(testData[9]);
			list.add(testData[10]);
			list.add(testData[11]);
			list.add(testData[12]);
			list.add(testData[13]);
			list.add(testData[14]);
			list.add(testData[15]);
			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
		}
	}
	
	@Test
	public void test_createMCT_FuncKey(Method method) throws Exception
	{
		String[] testData = null;
		try
		{
			pmTests.checkTestStatus(method.getName());
			pmMainPage = new PM_Main_Page(driver);
			testData = pmTests.getData(method.getName(), 1);
			list = new ArrayList<String>();
			list.add(testData[0]);
			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
			new ReusableUnits(driver).createExt_With_FunctionKey(driver, method.getName(), ipData, loginData, pmTests, 0);
			driver.findElement(By.xpath("//td[contains(text(),'"+testData[2]+"')]//preceding-sibling::td[20]")).click();
			List<WebElement> eles  = driver.findElements(By.xpath("//td[contains(text(),'"+testData[8]+" "+testData[8]+"')]"));
			Assert.assertTrue(eles.size() == 1);
			pmMainPage.getLogoutLink().click();
		}
		catch(Exception e)
		{
				new Take_Screenshot().get_Screenshot(driver, method.getName());
				throw e;
		}
		finally
		{
			list.clear();
			list.add(testData[9]);
			list.add(testData[10]);
			list.add(testData[11]);
			list.add(testData[12]);
			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
		}
	}
	
	@Test
	public void test_createMOI_FuncKey(Method method) throws Exception
	{
		String[] testData = null;
		try
		{
			pmTests.checkTestStatus(method.getName());
			pmMainPage = new PM_Main_Page(driver);
			testData = pmTests.getData(method.getName(), 1);
			list = new ArrayList<String>();
			list.add(testData[0]);
			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
			new ReusableUnits(driver).createExt_With_FunctionKey(driver, method.getName(), ipData, loginData, pmTests, 0);
			driver.findElement(By.xpath("//td[contains(text(),'"+testData[2]+"')]//preceding-sibling::td[20]")).click();
			List<WebElement> eles  = driver.findElements(By.xpath("//td[contains(text(),'"+testData[8]+" "+testData[8]+"')]"));
			Assert.assertTrue(eles.size() == 1);
			pmMainPage.getLogoutLink().click();
		}
		catch(Exception e)
		{
				new Take_Screenshot().get_Screenshot(driver, method.getName());
				throw e;
		}
		finally
		{
			list.clear();
			list.add(testData[9]);
			list.add(testData[10]);
			list.add(testData[11]);
			list.add(testData[12]);
			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
		}
	}
	
	@Test
	public void test_createPGM_FuncKey(Method method) throws Exception
	{
		String[] testData = null;
		try
		{
			pmTests.checkTestStatus(method.getName());
			pmMainPage = new PM_Main_Page(driver);
			testData = pmTests.getData(method.getName(), 1);
			list = new ArrayList<String>();
			list.add(testData[0]);
			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
			new ReusableUnits(driver).createExt_With_FunctionKey(driver, method.getName(), ipData, loginData, pmTests, 0);
			driver.findElement(By.xpath("//td[contains(text(),'"+testData[2]+"')]//preceding-sibling::td[20]")).click();
			List<WebElement> eles  = driver.findElements(By.xpath("//td[contains(text(),'"+testData[8]+" "+testData[8]+"')]"));
			Assert.assertTrue(eles.size() == 1);
			pmMainPage.getLogoutLink().click();
		}
		catch(Exception e)
		{
				new Take_Screenshot().get_Screenshot(driver, method.getName());
				throw e;
		}
		finally
		{
			list.clear();
			list.add(testData[9]);
			list.add(testData[10]);
			list.add(testData[11]);
			list.add(testData[12]);
//			list.add(testData[13]);
//			list.add(testData[14]);
//			list.add(testData[15]);
			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
		}
	}
	
	@Test
	public void test_createREC_FuncKey(Method method) throws Exception
	{
		String[] testData = null;
		try
		{
			pmTests.checkTestStatus(method.getName());
			pmMainPage = new PM_Main_Page(driver);
			testData = pmTests.getData(method.getName(), 1);
			list = new ArrayList<String>();
			list.add(testData[0]);
			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
			new ReusableUnits(driver).createExt_With_FunctionKey(driver, method.getName(), ipData, loginData, pmTests, 0);
			driver.findElement(By.xpath("//td[contains(text(),'"+testData[2]+"')]//preceding-sibling::td[20]")).click();
			List<WebElement> eles  = driver.findElements(By.xpath("//td[contains(text(),'"+testData[8]+" "+testData[8]+" "+testData[3]+"')]"));
			Assert.assertTrue(eles.size() == 1);
			pmMainPage.getLogoutLink().click();
		}
		catch(Exception e)
		{
				new Take_Screenshot().get_Screenshot(driver, method.getName());
				throw e;
		}
		finally
		{
			list.clear();
			list.add(testData[9]);
			list.add(testData[10]);
			list.add(testData[11]);
			list.add(testData[12]);
			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
		}
	}
	
	
	@Test
	public void test_createSCA_FuncKey(Method method) throws Exception
	{
		String[] testData = null;
		String[] extData = null;
		pmExtension = new Extension(driver);
		try
		{
			pmTests.checkTestStatus(method.getName());
			pmMainPage = new PM_Main_Page(driver);
			extData = pmTests.getData(method.getName(), 3);
			list = new ArrayList<String>();
			list.add(extData[0]);
			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
			testData = pmTests.getData(method.getName(), 1);
			list.clear();
			list.add(testData[0]);
			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
			new ReusableUnits(driver).createExt_Line1_SCA_SCABR(driver, method.getName(), ipData, loginData, pmTests, 0);
			driver.findElement(By.xpath("//td[contains(text(),'"+testData[2]+"')]//preceding-sibling::td[20]")).click();
			List<WebElement> eles  = driver.findElements(By.xpath("//td[contains(text(),'"+testData[8]+" "+testData[8]+" "+testData[2]+"')]"));
			Assert.assertTrue(eles.size() == 1);
			pmExtension.getDoneButton().click();
			pmMainPage.getLogoutLink().click();
			new ReusableUnits(driver).createExt_SCA_SCABRFunctionKey(driver, method.getName(), ipData, loginData, pmTests, 0);
			driver.findElement(By.xpath("//td[contains(text(),'"+testData[3]+"')]//preceding-sibling::td[20]")).click();
			List<WebElement> eles1  = driver.findElements(By.xpath("//td[contains(text(),'"+testData[8]+"-"+testData[2]+" "+testData[8]+" "+testData[2]+"')]"));
			Assert.assertTrue(eles1.size() == 1);
			pmExtension.getDoneButton().click();
			pmMainPage.getLogoutLink().click();
		}
		catch(Exception e)
		{
				new Take_Screenshot().get_Screenshot(driver, method.getName());
				throw e;
		}
		finally
		{
			list.clear();
			list.add(testData[9]);
			list.add(testData[10]);
			list.add(testData[11]);
			list.add(testData[12]);
			list.add(testData[13]);
			list.add(testData[14]);
			list.add(testData[15]);
			list.add(testData[16]);
			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
		}
	}
	
	
	@Test
	public void test_createSCABR_FuncKey(Method method) throws Exception
	{
		String[] testData = null;
		String[] extData = null;
		pmExtension = new Extension(driver);
		try
		{
			pmTests.checkTestStatus(method.getName());
			pmMainPage = new PM_Main_Page(driver);
			extData = pmTests.getData(method.getName(), 3);
			list = new ArrayList<String>();
			list.add(extData[0]);
			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
			testData = pmTests.getData(method.getName(), 1);
			list.clear();
			list.add(testData[0]);
			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
			new ReusableUnits(driver).createExt_Line1_SCA_SCABR(driver, method.getName(), ipData, loginData, pmTests, 0);
			driver.findElement(By.xpath("//td[contains(text(),'"+testData[2]+"')]//preceding-sibling::td[20]")).click();
			List<WebElement> eles  = driver.findElements(By.xpath("//td[contains(text(),'"+testData[8]+" "+testData[8]+" "+testData[2]+"')]"));
			Assert.assertTrue(eles.size() == 1);
			pmExtension.getDoneButton().click();
			pmMainPage.getLogoutLink().click();
			new ReusableUnits(driver).createExt_SCA_SCABRFunctionKey(driver, method.getName(), ipData, loginData, pmTests, 0);
			driver.findElement(By.xpath("//td[contains(text(),'"+testData[3]+"')]//preceding-sibling::td[20]")).click();
			List<WebElement> eles1  = driver.findElements(By.xpath("//td[contains(text(),'"+testData[8]+"-"+testData[2]+" "+testData[8]+" "+testData[2]+"')]"));
			Assert.assertTrue(eles1.size() == 1);
			pmExtension.getDoneButton().click();
			pmMainPage.getLogoutLink().click();
		}
		catch(Exception e)
		{
				new Take_Screenshot().get_Screenshot(driver, method.getName());
				throw e;
		}
		finally
		{
			list.clear();
			list.add(testData[9]);
			list.add(testData[10]);
			list.add(testData[11]);
			list.add(testData[12]);
			list.add(testData[13]);
			list.add(testData[14]);
			list.add(testData[15]);
			list.add(testData[16]);
			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
		}
	}
	
	
	@Test
	public void test_clearTNS_funcKey(Method method) throws Exception
	{
		String[] testData = null;
		String[] credentials = null;
		try
		{
			pmTests.checkTestStatus(method.getName());
			pmMainPage = new PM_Main_Page(driver);
			pmLogin = new PM_Login_Page(driver);
			pmServices = new PM_Services(driver);
			pmExtension = new Extension(driver);
			funcKeys = new PM_Function_Keys(driver);
			testData = pmTests.getData(method.getName(), 1);
			credentials = loginData.getData("test_pm_valid_login", 1);
			list = new ArrayList<String>();
			list.add(testData[0]);
			list.add(testData[1]);
			list.add(testData[2]);
			list.add(testData[3]);
			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
			new ReusableUnits(driver).clear_function_key(driver,method.getName(), ipData, loginData,pmTests,"TNS");
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
			list.add(testData[8]);
			list.add(testData[9]);
			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
		}
	}
	
	
	@Test
	public void test_clearMNS_funcKey(Method method) throws Exception
	{
		String[] testData = null;
		String[] credentials = null;
		try
		{
			pmTests.checkTestStatus(method.getName());
			pmMainPage = new PM_Main_Page(driver);
			pmLogin = new PM_Login_Page(driver);
			pmServices = new PM_Services(driver);
			pmExtension = new Extension(driver);
			funcKeys = new PM_Function_Keys(driver);
			testData = pmTests.getData(method.getName(), 1);
			credentials = loginData.getData("test_pm_valid_login", 1);
			list = new ArrayList<String>();
			list.add(testData[0]);
			list.add(testData[1]);
			list.add(testData[2]);
			list.add(testData[3]);
			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
			new ReusableUnits(driver).clear_function_key(driver,method.getName(), ipData, loginData,pmTests,"MNS");
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
			list.add(testData[8]);
			list.add(testData[9]);
			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
		}
	}
	
	
	@Test
	public void test_clearEDN_funcKey(Method method) throws Exception
	{
		String[] testData = null;
		String[] credentials = null;
		try
		{
			pmTests.checkTestStatus(method.getName());
			pmMainPage = new PM_Main_Page(driver);
			pmLogin = new PM_Login_Page(driver);
			pmServices = new PM_Services(driver);
			pmExtension = new Extension(driver);
			funcKeys = new PM_Function_Keys(driver);
			testData = pmTests.getData(method.getName(), 1);
			credentials = loginData.getData("test_pm_valid_login", 1);
			list = new ArrayList<String>();
			list.add(testData[0]);
			list.add(testData[1]);
			list.add(testData[2]);
			list.add(testData[3]);
			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
			new ReusableUnits(driver).clear_function_key(driver,method.getName(), ipData, loginData,pmTests,"EDN");
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
			list.add(testData[8]);
			list.add(testData[9]);
			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
		}
	}
	
	
	@Test
	public void test_clearMOI_funcKey(Method method) throws Exception
	{
		String[] testData = null;
		String[] credentials = null;
		try
		{
			pmTests.checkTestStatus(method.getName());
			pmMainPage = new PM_Main_Page(driver);
			pmLogin = new PM_Login_Page(driver);
			pmServices = new PM_Services(driver);
			pmExtension = new Extension(driver);
			funcKeys = new PM_Function_Keys(driver);
			testData = pmTests.getData(method.getName(), 1);
			credentials = loginData.getData("test_pm_valid_login", 1);
			list = new ArrayList<String>();
			list.add(testData[0]);
			list.add(testData[1]);
			list.add(testData[2]);
			list.add(testData[3]);
			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
			new ReusableUnits(driver).clear_function_key(driver,method.getName(), ipData, loginData,pmTests,"MOI");
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
			list.add(testData[8]);
			list.add(testData[9]);
			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
		}
	}
	
	
	@Test
	public void test_clearPGM_funcKey(Method method) throws Exception
	{
		String[] testData = null;
		String[] credentials = null;
		try
		{
			pmTests.checkTestStatus(method.getName());
			pmMainPage = new PM_Main_Page(driver);
			pmLogin = new PM_Login_Page(driver);
			pmServices = new PM_Services(driver);
			pmExtension = new Extension(driver);
			funcKeys = new PM_Function_Keys(driver);
			testData = pmTests.getData(method.getName(), 1);
			credentials = loginData.getData("test_pm_valid_login", 1);
			list = new ArrayList<String>();
			list.add(testData[0]);
			list.add(testData[1]);
			list.add(testData[2]);
			list.add(testData[3]);
			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
			new ReusableUnits(driver).clear_function_key(driver,method.getName(), ipData, loginData,pmTests,"PGM");
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
			list.add(testData[8]);
			list.add(testData[9]);
			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
		}
	}
	
	
	@Test
	public void test_clearREC_funcKey(Method method) throws Exception
	{
		String[] testData = null;
		String[] credentials = null;
		try
		{
			pmTests.checkTestStatus(method.getName());
			pmMainPage = new PM_Main_Page(driver);
			pmLogin = new PM_Login_Page(driver);
			pmServices = new PM_Services(driver);
			pmExtension = new Extension(driver);
			funcKeys = new PM_Function_Keys(driver);
			testData = pmTests.getData(method.getName(), 1);
			credentials = loginData.getData("test_pm_valid_login", 1);
			list = new ArrayList<String>();
			list.add(testData[0]);
			list.add(testData[1]);
			list.add(testData[2]);
			list.add(testData[3]);
			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
			new ReusableUnits(driver).clear_function_key(driver,method.getName(), ipData, loginData,pmTests,"REC");
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
			list.add(testData[8]);
			list.add(testData[9]);
			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
		}
	}
	
	
	@Test
	public void test_clearDMN_funcKey(Method method) throws Exception
	{
		String[] testData = null;
		String[] credentials = null;
		try
		{
			pmTests.checkTestStatus(method.getName());
			pmMainPage = new PM_Main_Page(driver);
			pmLogin = new PM_Login_Page(driver);
			pmServices = new PM_Services(driver);
			pmExtension = new Extension(driver);
			funcKeys = new PM_Function_Keys(driver);
			testData = pmTests.getData(method.getName(), 1);
			credentials = loginData.getData("test_pm_valid_login", 1);
			list = new ArrayList<String>();
			list.add(testData[0]);
			list.add(testData[1]);
			list.add(testData[2]);
			list.add(testData[3]);
			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
			new ReusableUnits(driver).clear_function_key(driver,method.getName(), ipData, loginData,pmTests,"DMN");
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
			list.add(testData[8]);
			list.add(testData[9]);
			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
		}
	}
	
	@Test
	public void test_clearGMA_funcKey(Method method) throws Exception
	{
		String[] testData = null;
		String[] credentials = null;
		try
		{
			pmTests.checkTestStatus(method.getName());
			pmMainPage = new PM_Main_Page(driver);
			pmLogin = new PM_Login_Page(driver);
			pmServices = new PM_Services(driver);
			pmExtension = new Extension(driver);
			funcKeys = new PM_Function_Keys(driver);
			testData = pmTests.getData(method.getName(), 1);
			credentials = loginData.getData("test_pm_valid_login", 1);
			list = new ArrayList<String>();
			list.add(testData[0]);
			list.add(testData[1]);
			list.add(testData[2]);
			list.add(testData[3]);
			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
			new ReusableUnits(driver).clear_function_key(driver,method.getName(), ipData, loginData,pmTests,"GMA");
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
			list.add(testData[8]);
			list.add(testData[9]);
			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
		}
	}
	
	@Test
	public void test_clearMCT_funcKey(Method method) throws Exception
	{
		String[] testData = null;
		String[] credentials = null;
		try
		{
			pmTests.checkTestStatus(method.getName());
			pmMainPage = new PM_Main_Page(driver);
			pmLogin = new PM_Login_Page(driver);
			pmServices = new PM_Services(driver);
			pmExtension = new Extension(driver);
			funcKeys = new PM_Function_Keys(driver);
			testData = pmTests.getData(method.getName(), 1);
			credentials = loginData.getData("test_pm_valid_login", 1);
			list = new ArrayList<String>();
			list.add(testData[0]);
			list.add(testData[1]);
			list.add(testData[2]);
			list.add(testData[3]);
			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
			new ReusableUnits(driver).clear_function_key(driver,method.getName(), ipData, loginData,pmTests,"MCT");
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
			list.add(testData[8]);
			list.add(testData[9]);
			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
		}
	}
	
	
	@Test
	public void test_clearSCA_funcKey(Method method) throws Exception
	{
		String[] testData = null;
		String[] credentials = null;
		try
		{
			pmTests.checkTestStatus(method.getName());
			pmMainPage = new PM_Main_Page(driver);
			pmLogin = new PM_Login_Page(driver);
			pmServices = new PM_Services(driver);
			pmExtension = new Extension(driver);
			funcKeys = new PM_Function_Keys(driver);
			testData = pmTests.getData(method.getName(), 1);
			credentials = loginData.getData("test_pm_valid_login", 1);
			list = new ArrayList<String>();
			list.add(testData[0]);
			list.add(testData[1]);
			list.add(testData[2]);
			list.add(testData[3]);
			list.add(testData[4]);
			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
			new ReusableUnits(driver).clear_function_key_SCA_SCABR(driver,method.getName(), ipData, loginData,pmTests,"SCA");
		}
		catch(Exception e)
		{
				new Take_Screenshot().get_Screenshot(driver, method.getName());
				throw e;
		}
		finally
		{
			list.clear();
			list.add(testData[7]);
			list.add(testData[8]);
			list.add(testData[9]);
			list.add(testData[10]);
			list.add(testData[11]);
			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
		}
	}
	
	
	@Test
	public void test_clearSCABR_funcKey(Method method) throws Exception
	{
		String[] testData = null;
		String[] credentials = null;
		try
		{
			pmTests.checkTestStatus(method.getName());
			pmMainPage = new PM_Main_Page(driver);
			pmLogin = new PM_Login_Page(driver);
			pmServices = new PM_Services(driver);
			pmExtension = new Extension(driver);
			funcKeys = new PM_Function_Keys(driver);
			testData = pmTests.getData(method.getName(), 1);
			credentials = loginData.getData("test_pm_valid_login", 1);
			list = new ArrayList<String>();
			list.add(testData[0]);
			list.add(testData[1]);
			list.add(testData[2]);
			list.add(testData[3]);
			list.add(testData[4]);
			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
			new ReusableUnits(driver).clear_function_key_SCA_SCABR(driver,method.getName(), ipData, loginData,pmTests,"SCABR");
		}
		catch(Exception e)
		{
				new Take_Screenshot().get_Screenshot(driver, method.getName());
				throw e;
		}
		finally
		{
			list.clear();
			list.add(testData[7]);
			list.add(testData[8]);
			list.add(testData[9]);
			list.add(testData[10]);
			list.add(testData[11]);
			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
		}
	}

	
//	@Test
//	public void test_create_all_function_keys(Method method) throws InterruptedException
//	{
//		String[] testData = null;
//		String[] credentials = null;
//		try
//		{
//			pmTests.checkTestStatus(method.getName());
//			pmMainPage = new PM_Main_Page(driver);
//			pmLogin = new PM_Login_Page(driver);
//			pmExtension = new Extension(driver);
//			pmServices = new PM_Services(driver);
//			funcKeys = new PM_Function_Keys(driver);
//			wait = new WebDriverWait(driver, 10);
//			
//			credentials = loginData.getData("test_pm_valid_login", 1);
//			testData = pmTests.getData(method.getName(), 1);
//			
//			list = new ArrayList<String>();
//			list.add(testData[0]);
//			list.add(testData[1]);
//			list.add(testData[2]);
//			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
//			
//			driver.get(ipData.getData(0, 0));
//			pmLogin.PM_Login(credentials[0], credentials[1]);
//			pmMainPage.getServices().click();
//			pmServices.getExtension().click();
//			new SelectDropDownValue().selectByVisibleText(pmExtension.getExtensionTypeDropDownHomePage(), "IP");
//			pmExtension.setEnterExtensionNumberTextBox(testData[3]);
//			pmExtension.getViewRangeButton().click();
//			driver.findElement(By.xpath("//td[contains(text(),'"+testData[3]+"')]//preceding-sibling::td[19]")).click();
//			new SelectDropDownValue().selectByValue(pmExtension.getPhoneTypeDropDown(), "Mitel6869i");
//			pmExtension.getFunctionKeysButton().click();
//			//TNS Key Configuration
//			funcKeys.getFuncKey6().click();
//			new SelectDropDownValue().selectByValue(funcKeys.getFunctionType(),"TNS");
//			funcKeys.setKeyLabel("TNS-"+testData[4]);
//			funcKeys.setTnsDigit(testData[4]);
//			funcKeys.getFuncKeyOKButton().click();
//			
//			//MNS Key Configuration
//			funcKeys.getFuncKey3().click();
//			new SelectDropDownValue().selectByValue(funcKeys.getFunctionType(),"MNS");
//			funcKeys.setKeyLabel("MNS-"+testData[4]);
//			funcKeys.setMNSDir(testData[4]);
//			funcKeys.getFuncKeyOKButton().click();
//			
//			
//			//EDN Key Configuration
//			funcKeys.getFuncKey4().click();
//			new SelectDropDownValue().selectByValue(funcKeys.getFunctionType(),"EDN");
//			funcKeys.setKeyLabel("EDN-"+testData[7]);
//			funcKeys.setEdnDir(testData[7]);
//			funcKeys.getFuncKeyOKButton().click();
//			
//			
//			//DMN Key Configuration
//			funcKeys.getFuncKey5().click();
//			new SelectDropDownValue().selectByValue(funcKeys.getFunctionType(),"DMN");
//			funcKeys.setKeyLabel("DMN-"+testData[4]);
//			funcKeys.setDmnDir(testData[4]);
//			funcKeys.getFuncKeyOKButton().click();
//			
//			//SXFER Key Configuration
//			funcKeys.getFuncKey2().click();
//			new SelectDropDownValue().selectByValue(funcKeys.getFunctionType(),"SXFER");
//			funcKeys.setKeyLabel("SXFER-"+testData[4]);
//			funcKeys.setSXFER(testData[4]);
//			funcKeys.getFuncKeyOKButton().click();
//			
//			//SCA Key Configuration
//			funcKeys.getFuncKeyLine1().click();
//			new SelectDropDownValue().selectByValue(funcKeys.getFunctionType(),"SCA");
//			funcKeys.setKeyLabel("SCA");
//			funcKeys.setSCADir(testData[3]);
//			funcKeys.getFuncKeyOKButton().click();
//			
//			
//			funcKeys.getApplyButton().click();
//			pmExtension.getEditPageApplyButton().click();
//			Assert.assertEquals(pmExtension.getResponseMessage(), "Change operation successful for:");
//			pmExtension.getDoneButton().click();
//			
//			//Configure SCA to some key for 80001
//			pmExtension.getEnterExtensionNumberTextBox().clear();
//			pmExtension.setEnterExtensionNumberTextBox(testData[4]);
//			pmExtension.getViewRangeButton().click();
//			driver.findElement(By.xpath("//td[contains(text(),'"+testData[4]+"')]//preceding-sibling::td[19]")).click();
//			new SelectDropDownValue().selectByValue(pmExtension.getPhoneTypeDropDown(), "Mitel6869i");
//			pmExtension.getFunctionKeysButton().click();
//			funcKeys.getFuncKey2().click();
//			new SelectDropDownValue().selectByValue(funcKeys.getFunctionType(),"SCA");
//			funcKeys.setKeyLabel("SCA-"+testData[3]);
//			funcKeys.setSCADir(testData[3]);
//			funcKeys.getFuncKeyOKButton().click();
//			funcKeys.getApplyButton().click();
//			pmExtension.getEditPageApplyButton().click();
//			Assert.assertEquals(pmExtension.getResponseMessage(), "Change operation successful for:");
//			pmExtension.getDoneButton().click();
//			
//			//Configure SCA to some key for 80002
//			pmExtension.getEnterExtensionNumberTextBox().clear();
//			pmExtension.setEnterExtensionNumberTextBox(testData[5]);
//			pmExtension.getViewRangeButton().click();
//			driver.findElement(By.xpath("//td[contains(text(),'"+testData[5]+"')]//preceding-sibling::td[19]")).click();
//			new SelectDropDownValue().selectByValue(pmExtension.getPhoneTypeDropDown(), "Mitel6869i");
//			pmExtension.getFunctionKeysButton().click();
//			funcKeys.getFuncKey2().click();
//			new SelectDropDownValue().selectByValue(funcKeys.getFunctionType(),"SCA");
//			funcKeys.setKeyLabel("SCA-"+testData[3]);
//			funcKeys.setSCADir(testData[3]);
//			funcKeys.getFuncKeyOKButton().click();
//			funcKeys.getApplyButton().click();
//			pmExtension.getEditPageApplyButton().click();
//			Assert.assertEquals(pmExtension.getResponseMessage(), "Change operation successful for:");
//			pmExtension.getDoneButton().click();
//			
//			//Configure SCA to some key for 80003
//			pmExtension.getEnterExtensionNumberTextBox().clear();
//			pmExtension.setEnterExtensionNumberTextBox(testData[6]);
//			pmExtension.getViewRangeButton().click();
//			driver.findElement(By.xpath("//td[contains(text(),'"+testData[6]+"')]//preceding-sibling::td[19]")).click();
//			new SelectDropDownValue().selectByValue(pmExtension.getPhoneTypeDropDown(), "Mitel6869i");
//			pmExtension.getFunctionKeysButton().click();
//			funcKeys.getFuncKey2().click();
//			new SelectDropDownValue().selectByValue(funcKeys.getFunctionType(),"SCA");
//			funcKeys.setKeyLabel("SCA-"+testData[3]);
//			funcKeys.setSCADir(testData[3]);
//			funcKeys.getFuncKeyOKButton().click();
//			funcKeys.getApplyButton().click();
//			pmExtension.getEditPageApplyButton().click();
//			Assert.assertEquals(pmExtension.getResponseMessage(), "Change operation successful for:");
//			pmExtension.getDoneButton().click();
//			
//			//Verifying the function keys assigned by viewing the extension
//			pmExtension.getEnterExtensionNumberTextBox().clear();
//			pmExtension.setEnterExtensionNumberTextBox(testData[3]);
//			pmExtension.getViewRangeButton().click();
//			driver.findElement(By.xpath("//td[contains(text(),'"+testData[3]+"')]//preceding-sibling::td[20]")).click();
//			List<WebElement> eles  = driver.findElements(By.xpath("//td[contains(text(),'TNS-"+testData[4]+" TNS"+" "+testData[4]+"')]"));
//			List<WebElement> eles1  = driver.findElements(By.xpath("//td[contains(text(),'MNS-"+testData[4]+" MNS"+" "+testData[4]+"')]"));
//			List<WebElement> eles2  = driver.findElements(By.xpath("//td[contains(text(),'EDN-"+testData[7]+" EDN"+" "+testData[7]+"')]"));
//			List<WebElement> eles3  = driver.findElements(By.xpath("//td[contains(text(),'DMN-"+testData[4]+" DMN')]"));
//			List<WebElement> eles4  = driver.findElements(By.xpath("//td[contains(text(),'SXFER-"+testData[4]+" SXFER"+" "+testData[4]+"')]"));
//			List<WebElement> eles5  = driver.findElements(By.xpath("//td[contains(text(),'SCA SCA "+testData[3]+"')]"));
//			Assert.assertTrue(eles.size() == 1 && eles1.size() == 1 && eles2.size() == 1 && eles3.size() == 1 
//					&& eles4.size() == 1 && eles5.size() == 1);
//			pmExtension.getDoneButton().click();
//			
//			//verifyng SCA/SCABR for other extensions.
//			pmExtension.getEnterExtensionNumberTextBox().clear();
//			pmExtension.setEnterExtensionNumberTextBox(testData[4]);
//			pmExtension.getViewRangeButton().click();
//			driver.findElement(By.xpath("//td[contains(text(),'"+testData[4]+"')]//preceding-sibling::td[20]")).click();
////			List<WebElement> eles6  = driver.findElements(By.xpath("//td[contains(text(),'SCABR SCABR "+testData[4]+"')]"));
//			List<WebElement> eles6  = driver.findElements(By.xpath("//td[contains(text(),'SCA SCA "+testData[3]+"')]"));
//			Assert.assertTrue(eles6.size() == 1);
//			pmExtension.getDoneButton().click();
//			
//			pmExtension.getEnterExtensionNumberTextBox().clear();
//			pmExtension.setEnterExtensionNumberTextBox(testData[5]);
//			pmExtension.getViewRangeButton().click();
//			driver.findElement(By.xpath("//td[contains(text(),'"+testData[5]+"')]//preceding-sibling::td[20]")).click();
////			List<WebElement> eles8  = driver.findElements(By.xpath("//td[contains(text(),'SCABR SCABR "+testData[4]+"')]"));
//			List<WebElement> eles7  = driver.findElements(By.xpath("//td[contains(text(),'SCA SCA "+testData[3]+"')]"));
//			Assert.assertTrue(eles7.size() == 1);
//			pmExtension.getDoneButton().click();
//			
//			pmExtension.getEnterExtensionNumberTextBox().clear();
//			pmExtension.setEnterExtensionNumberTextBox(testData[6]);
//			pmExtension.getViewRangeButton().click();
//			driver.findElement(By.xpath("//td[contains(text(),'"+testData[6]+"')]//preceding-sibling::td[20]")).click();
////			List<WebElement> eles10  = driver.findElements(By.xpath("//td[contains(text(),'SCABR SCABR "+testData[4]+"')]"));
//			List<WebElement> eles8  = driver.findElements(By.xpath("//td[contains(text(),'SCA SCA "+testData[3]+"')]"));
//			Assert.assertTrue(eles8.size() == 1);
//			pmMainPage.getLogoutLink().click();
//		}
//		finally
//		{
//			String flag = pmTests.checkTestStatusToCleanData(method.getName());
//			if(flag.equals("Y"))
//			{
//				list.clear();
//				list.add(testData[9]);
//				list.add(testData[10]);
//				list.add(testData[11]);
//				list.add(testData[12]);
//				list.add(testData[13]);
//				new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
//			}
//		}
//	}
	
}
