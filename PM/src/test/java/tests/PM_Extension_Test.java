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
import utilities.CleanUP;
import utilities.ExecuteCommands;
import utilities.ReusableUnits;
import utilities.SelectDropDownValue;

public class PM_Extension_Test extends ConfigClass
{
	public ArrayList<String> list;
	public PM_Login_Page loginPage;
	public PM_User pmUser;
	public Extension pmExtension;
	public PM_Main_Page pmMainPge;
	public PM_Services pmServices;
	WebDriverWait wait = null;
	
/*	@Test
	public void test_create_IP_extension(Method method) throws InterruptedException
	{
		String[] testData = null;
		try
		{
			pmTests.checkTestStatus(method.getName());
			pmUser = new PM_User(driver);
			testData = pmTests.getData(method.getName(), 1);
			list = new ArrayList<String>();
			list.add(testData[0]);
			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
			new ReusableUnits(driver).createExtension(driver, method.getName(), ipData, loginData, pmTests);
			pmUser.getLogoutLink().click();
		}
		finally
		{
			list.clear();
			list.add(testData[7]);
			list.add(testData[8]);
			list.add(testData[9]);
			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
		}
	}*/
	
/*	@Test
	public void test_delete_IP_extension(Method method) throws InterruptedException
	{
		String[] testData = null;
		try
		{
			pmTests.checkTestStatus(method.getName());
			pmExtension = new Extension(driver);
			pmUser = new PM_User(driver);
			testData = pmTests.getData(method.getName(), 1);
			list = new ArrayList<String>();
			list.add(testData[0]);
			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
			new ReusableUnits(driver).createExtension(driver, method.getName(), ipData, loginData, pmTests);
			driver.findElement(By.xpath("//td[contains(text(),'"+testData[2]+"')]//preceding-sibling::td[18]")).click();
			driver.switchTo().alert().accept();
			Assert.assertEquals(pmExtension.getResponseMessage(), "Remove operation successful for:");
			pmUser.getLogoutLink().click();
		}
		finally
		{
			list.clear();
			list.add(testData[7]);
			list.add(testData[8]);
			list.add(testData[9]);
			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
		}
	}*/
	
/*	@Test
	public void test_delete_multiple_IP_extension(Method method) throws InterruptedException
	{
		String[] testData = null;
		try
		{
			pmTests.checkTestStatus(method.getName());
			pmExtension = new Extension(driver);
			loginPage = new PM_Login_Page(driver);
			pmMainPge = new PM_Main_Page(driver);
			pmServices = new PM_Services(driver);
			pmUser = new PM_User(driver);
			testData = pmTests.getData(method.getName(), 1);
			list = new ArrayList<String>();
			list.add(testData[0]);
			list.add(testData[1]);
			list.add(testData[2]);
			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
			driver.get(ipData.getData(0, 0));
			String[] credentials = loginData.getData("test_pm_valid_login", 1);
			loginPage.PM_Login(credentials[0], credentials[1]);
			pmMainPge.getServices().click();
			pmServices.getExtension().click();
			new SelectDropDownValue().selectByVisibleText(pmExtension.getExtensionTypeDropDownHomePage(), "IP");
			pmExtension.setEnterExtensionNumberTextBox(testData[3]);
			pmExtension.getViewRangeButton().click();
			List<WebElement> eles = driver.findElements(By.xpath("//input[@name='selectItem']"));
			Assert.assertTrue(eles.size() == 10);
			for(int i=1; i <= 10; i++)
			{
				driver.findElement(By.xpath("(//input[@name='selectItem'])["+i+"]")).click();
			}
			pmExtension.getRemoveButton().click();
			driver.switchTo().alert().accept();
			Assert.assertEquals(pmExtension.getResponseMessage(), "Remove operation successful for:");
			pmUser.getLogoutLink().click();
		}
		finally
		{
			list.clear();
			list.add(testData[4]);
			list.add(testData[5]);
			list.add(testData[6]);
			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
		}
	}*/
	
/*	@Test
	public void test_configureParallel_ringing_to_IP(Method method) throws InterruptedException
	{
		String[] testData = null;
		try
		{
			pmTests.checkTestStatus(method.getName());
			pmExtension = new Extension(driver);
			loginPage = new PM_Login_Page(driver);
			pmMainPge = new PM_Main_Page(driver);
			pmServices = new PM_Services(driver);
			pmUser = new PM_User(driver);
			testData = pmTests.getData(method.getName(), 1);
			list = new ArrayList<String>();
			list.add(testData[0]);
			list.add(testData[1]);
			list.add(testData[2]);
			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
			driver.get(ipData.getData(0, 0));
			String[] credentials = loginData.getData("test_pm_valid_login", 1);
			loginPage.PM_Login(credentials[0], credentials[1]);
			pmMainPge.getServices().click();
			pmServices.getExtension().click();
			new SelectDropDownValue().selectByVisibleText(pmExtension.getExtensionTypeDropDownHomePage(), "IP");
			pmExtension.setEnterExtensionNumberTextBox(testData[3]);
			pmExtension.getViewRangeButton().click();
			driver.findElement(By.xpath("//td[contains(text(),'"+testData[4]+"')]//preceding-sibling::td[19]")).click();
			pmExtension.getAdvanceButton().click();
			pmExtension.setSecDirNum1(testData[5]);
			pmExtension.setSecDirNum2(testData[6]);
			pmExtension.getEditPageApplyButton().click();
			Assert.assertEquals(pmExtension.getResponseMessage(), "Change operation successful for:");
			pmExtension.getDoneButton().click();
			new SelectDropDownValue().selectByVisibleText(pmExtension.getExtensionTypeDropDownHomePage(), "IP");
			pmExtension.getEnterExtensionNumberTextBox().clear();
			pmExtension.setEnterExtensionNumberTextBox(testData[4]);
			pmExtension.getViewRangeButton().click();
			wait = new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("blockUI")));
			driver.findElement(By.xpath("//td[contains(text(),'"+testData[4]+"')]//preceding-sibling::td[20]")).click();
			List<WebElement> sdn1 = driver.findElements(By.xpath("//td[contains(text(),'Secondary Directory Number 1')]//following-sibling::td[contains(text(),'"+testData[5]+"')]"));
			List<WebElement> sdn2 = driver.findElements(By.xpath("//td[contains(text(),'Secondary Directory Number 2')]//following-sibling::td[contains(text(),'"+testData[6]+"')]"));
			System.out.println(sdn1.size() +" "+sdn2.size());
			Assert.assertTrue(sdn1.size() == 1 && sdn2.size() == 1);
			pmExtension.getDoneButton().click();
			pmUser.getLogoutLink().click();
		}
		finally
		{
			list.clear();
			list.add(testData[7]);
			list.add(testData[8]);
			list.add(testData[9]);
			list.add(testData[10]);
			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
		}
	}*/
	
	
	
	@Test
	public void test_editIPExtensionPhoneType(Method method) throws InterruptedException
	{
		String[] testData = null;
		try
		{
			pmTests.checkTestStatus(method.getName());
			pmExtension = new Extension(driver);
			loginPage = new PM_Login_Page(driver);
			pmMainPge = new PM_Main_Page(driver);
			pmServices = new PM_Services(driver);
			pmUser = new PM_User(driver);
			testData = pmTests.getData(method.getName(), 1);
			list = new ArrayList<String>();
			list.add(testData[0]);
			list.add(testData[1]);
			list.add(testData[2]);
			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
			driver.get(ipData.getData(0, 0));
			String[] credentials = loginData.getData("test_pm_valid_login", 1);
			loginPage.PM_Login(credentials[0], credentials[1]);
			pmMainPge.getServices().click();
			pmServices.getExtension().click();
			new SelectDropDownValue().selectByVisibleText(pmExtension.getExtensionTypeDropDownHomePage(), "IP");
			pmExtension.setEnterExtensionNumberTextBox(testData[3]);
			pmExtension.getViewRangeButton().click();
			driver.findElement(By.xpath("//td[contains(text(),'"+testData[3]+"')]//preceding-sibling::td[19]")).click();
			Thread.sleep(3000);
			new SelectDropDownValue().selectByVisibleText(pmExtension.getPhoneTypeDropDown(), testData[4]);
			Thread.sleep(3000);
			System.out.println(testData[4]);
			System.out.println(testData[4].length());
//			pmExtension.getAdvanceButton().click();
//			pmExtension.setSecDirNum1(testData[5]);
//			pmExtension.setSecDirNum2(testData[6]);
			pmExtension.getEditPageApplyButton().click();
			Assert.assertEquals(pmExtension.getResponseMessage(), "Change operation successful for:");
			pmExtension.getDoneButton().click();
			new SelectDropDownValue().selectByVisibleText(pmExtension.getExtensionTypeDropDownHomePage(), "IP");
			pmExtension.getEnterExtensionNumberTextBox().clear();
			pmExtension.setEnterExtensionNumberTextBox(testData[3]);
			pmExtension.getViewRangeButton().click();
			wait = new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("blockUI")));
			driver.findElement(By.xpath("//td[contains(text(),'"+testData[3]+"')]//preceding-sibling::td[20]")).click();
			String value = testData[4].replace(" ", "");
//			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//td[contains(text(),'Phone type')]//following-sibling::td[contains(text(),'Mitel6873i')]")));
			List<WebElement> sdn1  = driver.findElements(By.xpath("//td[contains(text(),'Phone type')]//following-sibling::td[contains(text(),'"+value+"')]"));
			System.out.println("//td[contains(text(),'Phone type')]//following-sibling::td[contains(text(),'"+testData[4]+"')]");
			Assert.assertTrue(sdn1.size() == 1);
			pmExtension.getDoneButton().click();
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
