package tests;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.NoInjection;
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
	public PM_Login_Page pmLoginPge;
	public PM_Users pmUsers;
	
	@Test
	public void test_create_IP_extension(Method method) throws InterruptedException
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
			new ReusableUnits(driver).createExtension(driver, method.getName(), ipData, loginData, pmTests,0);
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
	}
	
	@Test
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
			new ReusableUnits(driver).createExtension(driver, method.getName(), ipData, loginData, pmTests,0);
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
	}
	
	@Test
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
	}
	
	@Test
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
	}
	
	
	
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
			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
			new ReusableUnits(driver).createExtension(driver, method.getName(), ipData, loginData, pmTests,0);
			
			driver.findElement(By.xpath("//td[contains(text(),'"+testData[2]+"')]//preceding-sibling::td[19]")).click();
			new SelectDropDownValue().selectByVisibleText(pmExtension.getPhoneTypeDropDown(), testData[7]);

			pmExtension.getEditPageApplyButton().click();
			Assert.assertEquals(pmExtension.getResponseMessage(), "Change operation successful for:");
			pmExtension.getDoneButton().click();
			new SelectDropDownValue().selectByVisibleText(pmExtension.getExtensionTypeDropDownHomePage(), "IP");
			pmExtension.getEnterExtensionNumberTextBox().clear();
			pmExtension.setEnterExtensionNumberTextBox(testData[2]);
			pmExtension.getViewRangeButton().click();
			WebDriverWait wait = new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("blockUI")));
			driver.findElement(By.xpath("//td[contains(text(),'"+testData[2]+"')]//preceding-sibling::td[20]")).click();
			String value = testData[7].replace(" ", "");
			List<WebElement> sdn1  = driver.findElements(By.xpath("//td[contains(text(),'Phone type')]//following-sibling::td[contains(text(),'"+value+"')]"));
			System.out.println("//td[contains(text(),'Phone type')]//following-sibling::td[contains(text(),'"+testData[7]+"')]");
			Assert.assertTrue(sdn1.size() == 1);
			pmExtension.getDoneButton().click();
			pmUser.getLogoutLink().click();

		}
		finally
		{
			list.clear();
			list.add(testData[8]);
			list.add(testData[9]);
			list.add(testData[10]);
			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
		}
	}
	
	
	@Test
	public void test_editIPExtensionCSP(Method method) throws InterruptedException
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
			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
			new ReusableUnits(driver).createExtension(driver, method.getName(), ipData, loginData, pmTests,0);
			
			driver.findElement(By.xpath("//td[contains(text(),'"+testData[2]+"')]//preceding-sibling::td[19]")).click();
			new SelectDropDownValue().selectByIndex(pmExtension.getMyCSPNameDropDown(), 1);

			pmExtension.getEditPageApplyButton().click();
			Assert.assertEquals(pmExtension.getResponseMessage(), "Change operation successful for:");
			pmExtension.getDoneButton().click();
			new SelectDropDownValue().selectByVisibleText(pmExtension.getExtensionTypeDropDownHomePage(), "IP");
			pmExtension.getEnterExtensionNumberTextBox().clear();
			pmExtension.setEnterExtensionNumberTextBox(testData[2]);
			pmExtension.getViewRangeButton().click();
			WebDriverWait wait = new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("blockUI")));
			driver.findElement(By.xpath("//td[contains(text(),'"+testData[2]+"')]//preceding-sibling::td[20]")).click();
			List<WebElement> sdn1  = driver.findElements(By.xpath("//td[contains(text(),'Common Service Profile')]//following-sibling::td[contains(text(),'"+testData[10]+"')]"));
			System.out.println("//td[contains(text(),'Phone type')]//following-sibling::td[contains(text(),'"+testData[7]+"')]");
			Assert.assertTrue(sdn1.size() == 1);
			pmExtension.getDoneButton().click();
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
	}
	
	@Test
	public void test_editToAssignThirdPartySIPClient(Method method) throws InterruptedException
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
			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
			new ReusableUnits(driver).createExtension(driver, method.getName(), ipData, loginData, pmTests,0);
			
			driver.findElement(By.xpath("//td[contains(text(),'"+testData[2]+"')]//preceding-sibling::td[19]")).click();
			pmExtension.getAdvanceButton().click();
			pmExtension.getThirdPartySIPClient().click();
			pmExtension.getEditPageApplyButton().click();
			Assert.assertEquals(pmExtension.getResponseMessage(), "Change operation successful for:");
			pmExtension.getDoneButton().click();
			new SelectDropDownValue().selectByVisibleText(pmExtension.getExtensionTypeDropDownHomePage(), "IP");
			pmExtension.getEnterExtensionNumberTextBox().clear();
			pmExtension.setEnterExtensionNumberTextBox(testData[2]);
			pmExtension.getViewRangeButton().click();
			WebDriverWait wait = new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("blockUI")));
			driver.findElement(By.xpath("//td[contains(text(),'"+testData[2]+"')]//preceding-sibling::td[20]")).click();
			List<WebElement> sdn1  = driver.findElements(By.xpath("//td[contains(text(),'Allow Third Party SIP Client')]//following-sibling::td[contains(text(),'Yes')]"));
			Assert.assertTrue(sdn1.size() == 1);
			pmExtension.getDoneButton().click();
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
	}
	
	@Test
	public void test_editToConfigureCallPark(Method method) throws InterruptedException
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
			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
			new ReusableUnits(driver).createExtension(driver, method.getName(), ipData, loginData, pmTests,0);
			
			driver.findElement(By.xpath("//td[contains(text(),'"+testData[2]+"')]//preceding-sibling::td[19]")).click();
			pmExtension.getAdvanceButton().click();
			pmExtension.getExtensionCallParkPool().click();
			pmExtension.getEditPageApplyButton().click();
			Assert.assertEquals(pmExtension.getResponseMessage(), "Change operation successful for:");
			pmExtension.getDoneButton().click();
			new SelectDropDownValue().selectByVisibleText(pmExtension.getExtensionTypeDropDownHomePage(), "IP");
			pmExtension.getEnterExtensionNumberTextBox().clear();
			pmExtension.setEnterExtensionNumberTextBox(testData[2]);
			pmExtension.getViewRangeButton().click();
			WebDriverWait wait = new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("blockUI")));
			driver.findElement(By.xpath("//td[contains(text(),'"+testData[2]+"')]//preceding-sibling::td[20]")).click();
			
			List<WebElement> verify1  = driver.findElements(By.xpath("//td[contains(text(),'Terminal Identity')]//following-sibling::td[contains(text(),'0.0.0.0')]"));
			List<WebElement> verify2  = driver.findElements(By.xpath("//td[contains(text(),'Universal Resource Identifier')]//following-sibling::td[contains(text(),'0.0.0.0')]"));
			List<WebElement> verify3  = driver.findElements(By.xpath("//td[contains(text(),'Transport')]//following-sibling::td[contains(text(),'TCP')]"));
			List<WebElement> verify4  = driver.findElements(By.xpath("//td[contains(text(),'IP Address')]//following-sibling::td[contains(text(),'0.0.0.0')]"));
			List<WebElement> verify5  = driver.findElements(By.xpath("//td[contains(text(),'CS Port')]//following-sibling::td[contains(text(),'5060')]"));
			
			Assert.assertTrue(verify1.size() == 1 && verify2.size() == 1 && verify3.size() == 1 && verify4.size() == 1 && verify5.size() == 1);
			pmExtension.getDoneButton().click();
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
	}
	
	@DataProvider(name = "SLDProvider")
	public Object[] getData()
	{
		Object[] obj = new Object[3];
				obj[0] = "No, but can be changed via terminal menu";
				obj[1] = "Yes, can not be changed via terminal menu";
				obj[2] = "No, can not be changed via terminal menu";
		return obj;
	}
	
	@Test(dataProvider="SLDProvider")
	public void test_editToConfigureSecondLineState(String slConfig,Method method) throws InterruptedException
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
			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
			new ReusableUnits(driver).createExtension(driver, method.getName(), ipData, loginData, pmTests,0);
			
			driver.findElement(By.xpath("//td[contains(text(),'"+testData[2]+"')]//preceding-sibling::td[19]")).click();
			pmExtension.getAdvanceButton().click();
			new SelectDropDownValue().selectByVisibleText(pmExtension.getSecondLineSetting(), slConfig);
			pmExtension.getEditPageApplyButton().click();
			Assert.assertEquals(pmExtension.getResponseMessage(), "Change operation successful for:");
			pmExtension.getDoneButton().click();
			new SelectDropDownValue().selectByVisibleText(pmExtension.getExtensionTypeDropDownHomePage(), "IP");
			pmExtension.getEnterExtensionNumberTextBox().clear();
			pmExtension.setEnterExtensionNumberTextBox(testData[2]);
			pmExtension.getViewRangeButton().click();
			WebDriverWait wait = new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("blockUI")));
			driver.findElement(By.xpath("//td[contains(text(),'"+testData[2]+"')]//preceding-sibling::td[20]")).click();
			
			List<WebElement> eles  = driver.findElements(By.xpath("//td[contains(text(),'Free on Second Line')]//following-sibling::td[contains(text(),'"+slConfig+"')]"));
			
			Assert.assertTrue(eles.size() == 1);
			pmExtension.getDoneButton().click();
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
	}
	
	@DataProvider(name = "hotline")
	public Object[] getHotLineData()
	{
		Object[] obj = new Object[2];
				obj[0] = "Hot-line";
				obj[1] = "Delayed hot-line";
		return obj;
	}
	
	@Test(dataProvider="hotline")
	public void test_editToConfigureHotLineNumber(String hotLineType,Method method) throws InterruptedException
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
			pmExtension.getAdvanceButton().click();
			
			new SelectDropDownValue().selectByVisibleText(pmExtension.getPhoneHotLineType(), hotLineType);
			pmExtension.setHotLineNumber(testData[4]);
			pmExtension.getEditPageApplyButton().click();
			Assert.assertEquals(pmExtension.getResponseMessage(), "Change operation successful for:");
			pmExtension.getDoneButton().click();
			new SelectDropDownValue().selectByVisibleText(pmExtension.getExtensionTypeDropDownHomePage(), "IP");
			pmExtension.getEnterExtensionNumberTextBox().clear();
			pmExtension.setEnterExtensionNumberTextBox(testData[3]);
			pmExtension.getViewRangeButton().click();
			WebDriverWait wait = new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("blockUI")));
			driver.findElement(By.xpath("//td[contains(text(),'"+testData[3]+"')]//preceding-sibling::td[20]")).click();
			
			List<WebElement> eles  = driver.findElements(By.xpath("//td[contains(text(),'Phone Type')]//following-sibling::td[contains(text(),'"+hotLineType+"')]"));
			List<WebElement> eles2  = driver.findElements(By.xpath("//td[contains(text(),'Hotline Number')]//following-sibling::td[contains(text(),'"+testData[4]+"')]"));
			Assert.assertTrue(eles.size() == 1 && eles2.size() == 1);
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
	
	
	@Test
	public void test_editToSetFirst_LastNames(Method method) throws InterruptedException
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
			pmExtension.getAdvanceButton().click();
			
			pmExtension.setFirstName(testData[4]);
			pmExtension.setLastName(testData[5]);
			
			pmExtension.getEditPageApplyButton().click();
			Assert.assertEquals(pmExtension.getResponseMessage(), "Change operation successful for:");
			pmExtension.getDoneButton().click();
			new SelectDropDownValue().selectByVisibleText(pmExtension.getExtensionTypeDropDownHomePage(), "IP");
			pmExtension.getEnterExtensionNumberTextBox().clear();
			pmExtension.setEnterExtensionNumberTextBox(testData[3]);
			pmExtension.getViewRangeButton().click();
			WebDriverWait wait = new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("blockUI")));
			driver.findElement(By.xpath("//td[contains(text(),'"+testData[3]+"')]//preceding-sibling::td[20]")).click();
		
			List<WebElement> eles  = driver.findElements(By.xpath("//td[contains(text(),'First Name')]//following-sibling::td[contains(text(),'"+testData[4]+"')]"));
			List<WebElement> eles2  = driver.findElements(By.xpath("//td[contains(text(),'Last Name')]//following-sibling::td[contains(text(),'"+testData[5]+"')]"));
			Assert.assertTrue(eles.size() == 1 && eles2.size() == 1);
			Thread.sleep(10000);
			pmExtension.getDoneButton().click();
			pmUser.getLogoutLink().click();

		}
		finally
		{
			list.clear();
			list.add(testData[6]);
			list.add(testData[7]);
			list.add(testData[8]);
			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
		}
	}
	
	@Test
	public void test_create_IP_extension_usingTemplate(Method method) throws InterruptedException
	{
		String[] testData = null;
		try
		{
			pmTests.checkTestStatus(method.getName());
			pmUser = new PM_User(driver);
			pmMainPge = new PM_Main_Page(driver);
			pmServices = new PM_Services(driver);
			pmExtension = new Extension(driver);
			pmLoginPge = new PM_Login_Page(driver);
			pmUsers = new PM_Users(driver);
			
			testData = pmTests.getData(method.getName(), 1);
			list = new ArrayList<String>();
			list.add(testData[0]);
			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
			new ReusableUnits(driver).createIPTemplate(driver, method.getName(), ipData, loginData, testData[2]);
			driver.get(ipData.getData(0, 0));
			String[] credentials = loginData.getData("test_pm_valid_login", 1);
			pmLoginPge.PM_Login(credentials[0], credentials[1]);
			pmMainPge.getServices().click();
			pmServices.getExtension().click();
			new SelectDropDownValue().selectByIndex(pmExtension.getExtensionHomePageTemplateDropDown(), 1);
			pmExtension.getAddButton().click();
			Thread.sleep(2000);
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
			new SelectDropDownValue().selectByVisibleText(pmExtension.getPhoneTypeDropDown(), testData[3]);
			pmExtension.getApplyButton().click();
			
			Assert.assertEquals("Add operation successful for:", pmExtension.getResponseMessage());
			pmExtension.getDoneButton().click();
			pmExtension.setEnterExtensionNumberTextBox(testData[1]);
			pmExtension.getViewRangeButton().click();
			List<WebElement> eles = driver.findElements(By.xpath("//td[contains(text(),'"+testData[1]+"')]"));
			Assert.assertTrue(eles.size()==1);
			pmMainPge.getLogoutLink().click();
		}
		finally
		{
			new CleanUP(driver).deleteTemplate(driver, method.getName(), loginData, testData[2], ipData);
			list.clear();
			list.add(testData[4]);
			list.add(testData[5]);
			list.add(testData[6]);
			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
		}
	}
	
}
