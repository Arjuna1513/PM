package tests;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import base.ConfigClass;
import junit.framework.Assert;
import pm_pom_classes.Extension;
import pm_pom_classes.PM_Login_Page;
import pm_pom_classes.PM_Main_Page;
import utilities.ExecuteCommands;
import utilities.ReusableUnits;

public class PM_FunctionKeys_Test extends ConfigClass
{
	
	ArrayList<String> list;
	public PM_Main_Page pmMainPage;
	public Extension pmExtension;
	
	@Test
	public void test_createDMN_FuncKey(Method method) throws InterruptedException
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
		finally
		{
			list.clear();
			list.add(testData[9]);
			list.add(testData[10]);
			list.add(testData[11]);
			list.add(testData[12]);
			list.add(testData[12]);
			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
		}
	}
	
	@Test
	public void test_createEDN_FuncKey(Method method) throws InterruptedException
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
		finally
		{
			list.clear();
			list.add(testData[9]);
			list.add(testData[10]);
			list.add(testData[11]);
			list.add(testData[12]);
			list.add(testData[13]);
			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
		}
	}	
		
	@Test
	public void test_createGMA_FuncKey(Method method) throws InterruptedException
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
		finally
		{
			list.clear();
			list.add(testData[9]);
			list.add(testData[10]);
			list.add(testData[11]);
			list.add(testData[12]);
			list.add(testData[13]);
			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
		}
	}
	
	
	@Test
	public void test_createMNS_FuncKey(Method method) throws InterruptedException
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
		finally
		{
			list.clear();
			list.add(testData[9]);
			list.add(testData[10]);
			list.add(testData[11]);
			list.add(testData[12]);
			list.add(testData[13]);
			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
		}
	}
	
	
	@Test
	public void test_createTNS_FuncKey(Method method) throws InterruptedException
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
		finally
		{
			list.clear();
			list.add(testData[9]);
			list.add(testData[10]);
			list.add(testData[11]);
			list.add(testData[12]);
			list.add(testData[13]);
			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
		}
	}
	
	@Test
	public void test_createMCT_FuncKey(Method method) throws InterruptedException
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
	public void test_createMOI_FuncKey(Method method) throws InterruptedException
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
	public void test_createPGM_FuncKey(Method method) throws InterruptedException
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
	public void test_createREC_FuncKey(Method method) throws InterruptedException
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
	public void test_createSCA_FuncKey(Method method) throws InterruptedException
	{
		String[] testData = null;
		pmExtension = new Extension(driver);
		try
		{
			pmTests.checkTestStatus(method.getName());
			pmMainPage = new PM_Main_Page(driver);
			testData = pmTests.getData(method.getName(), 3);
			list = new ArrayList<String>();
			list.add(testData[0]);
/*			list.add(testData[1]);
			list.add(testData[2]);*/
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
		finally
		{
			list.clear();
			list.add(testData[9]);
			list.add(testData[10]);
			list.add(testData[11]);
			list.add(testData[12]);
			list.add(testData[13]);
			list.add(testData[14]);
			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
		}
	}
	
	
	@Test
	public void test_createSCABR_FuncKey(Method method) throws InterruptedException
	{
		String[] testData = null;
		pmExtension = new Extension(driver);
		try
		{
			pmTests.checkTestStatus(method.getName());
			pmMainPage = new PM_Main_Page(driver);
			testData = pmTests.getData(method.getName(), 3);
			list = new ArrayList<String>();
			list.add(testData[0]);
/*			list.add(testData[1]);
			list.add(testData[2]);*/
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
		finally
		{
			list.clear();
			list.add(testData[9]);
			list.add(testData[10]);
			list.add(testData[11]);
			list.add(testData[12]);
			list.add(testData[13]);
			list.add(testData[14]);
			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
		}
	}
}
