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

public class PM_SIPDECT_Tests extends ConfigClass
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
	public void test_create_SIP_DECT_extension(Method method) throws InterruptedException
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
			new ReusableUnits(driver).create_SIPDECT_Extension(driver, method.getName(), ipData, loginData, pmTests,0);
			pmUser.getLogoutLink().click();
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
	public void test_delete_SIPDECT_extension(Method method) throws InterruptedException
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
			new ReusableUnits(driver).create_SIPDECT_Extension(driver, method.getName(), ipData, loginData, pmTests,0);
			driver.findElement(By.xpath("//td[contains(text(),'"+testData[1]+"')]//preceding-sibling::td[18]")).click();
			driver.switchTo().alert().accept();
			Assert.assertEquals(pmExtension.getResponseMessage(), "Remove operation successful for:");
			pmUser.getLogoutLink().click();
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
	public void test_delete_multiple_SIPDECT_extension(Method method) throws InterruptedException
	{
		String[] testData = null;
		try
		{
			System.out.println(pmTests);
			pmTests.checkTestStatus(method.getName());
			pmUser = new PM_User(driver);
			pmExtension = new Extension(driver);
			testData = pmTests.getData(method.getName(), 1);
			list = new ArrayList<String>();
			list.add(testData[0]);
			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
			new ReusableUnits(driver).create_SIPDECT_Extension(driver, method.getName(), ipData, loginData, pmTests,0);
			List<WebElement> eles = driver.findElements(By.xpath("//input[@name='selectItem']"));
			Assert.assertTrue(eles.size() == 1);
			for(int i=1; i < 2; i++)
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
			list.add(testData[8]);
			list.add(testData[9]);
			list.add(testData[10]);
			list.add(testData[11]);
			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
		}
	}
	
	@Test
	public void test_edit_configureParallel_ringing_to_SIPDECT(Method method) throws InterruptedException
	{
		String[] testData = null;
		String[] prllRingData = null;
		try
		{
			pmTests.checkTestStatus(method.getName());
			wait = new WebDriverWait(driver, 10);
			pmExtension = new Extension(driver);
			loginPage = new PM_Login_Page(driver);
			pmMainPge = new PM_Main_Page(driver);
			pmServices = new PM_Services(driver);
			pmUser = new PM_User(driver);
			testData = pmTests.getData(method.getName(), 1);
			prllRingData = pmTests.getData(method.getName(), 3);
			list = new ArrayList<String>();
			list.add(testData[0]);
			list.add(prllRingData[0]);
			list.add(prllRingData[1]);
			list.add(prllRingData[2]);
			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
			
			new ReusableUnits(driver).navigate_to_sipdect_settings_page(driver, method.getName(), ipData, loginData, pmTests,0);
			
			new SelectDropDownValue().selectByValue(pmExtension.getSelectExtensionsRange(), testData[1]);
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
			new SelectDropDownValue().selectByVisibleText(pmExtension.getMultiTerminalServerDropDown(), testData[2]);
			
			pmExtension.getAddSipDectTerminalButton().click();
			pmExtension.setSIPDectName(testData[3]);
			pmExtension.setSIPDectDescription1("SIP DECT");
			pmExtension.setSIPDectDescription2("SIP DECT");
			pmExtension.setSIPDectAuthKey(testData[4]);
			pmExtension.setSIPDectIPEINumber(testData[5]);
			pmExtension.getApplyButton().click();
			
			pmExtension.setMultiTerminalFirstName(testData[6]);
			pmExtension.setMultiTerminalLastName(testData[7]);
			
			pmExtension.getAdvanceButton().click();
			pmExtension.setSecDirNum1(prllRingData[3]);
			pmExtension.setSecDirNum2(prllRingData[4]);
			pmExtension.getApplyButton().click();
			
			Assert.assertEquals(pmExtension.getResponseMessage(), "Add operation successful for:");
			pmExtension.getDoneButton().click();
			
			new SelectDropDownValue().selectByVisibleText(pmExtension.getExtensionTypeDropDownHomePage(), "Multi-Terminal");
			pmExtension.setEnterExtensionNumberTextBox(testData[1]);
			pmExtension.getViewRangeButton().click();
			
			List<WebElement> eles = driver.findElements(By.xpath("//td[contains(text(),'"+testData[1]+"')]"));
			Assert.assertTrue(eles.size()==1);
			
			driver.findElement(By.xpath("//td[contains(text(),'"+testData[1]+"')]//preceding-sibling::td[20]")).click();
			List<WebElement> sdn1 = driver.findElements(By.xpath("//td[contains(text(),'Secondary Directory Number 1')]//following-sibling::td[contains(text(),'"+prllRingData[3]+"')]"));
			List<WebElement> sdn2 = driver.findElements(By.xpath("//td[contains(text(),'Secondary Directory Number 2')]//following-sibling::td[contains(text(),'"+prllRingData[4]+"')]"));
			System.out.println(sdn1.size() +" "+sdn2.size());
			Assert.assertTrue(sdn1.size() == 1 && sdn2.size() == 1);
			pmExtension.getDoneButton().click();
			pmUser.getLogoutLink().click();
		}
		finally
		{
			list.clear();
			list.add(testData[8]);
			list.add(prllRingData[5]);
			list.add(prllRingData[6]);
			list.add(prllRingData[7]);
			list.add(testData[9]);
			list.add(testData[10]);
			list.add(testData[11]);
			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
		}
	}
	
	@Test
	public void test_edit_SIPDECT_ExtensionCSP(Method method) throws InterruptedException
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
			new ReusableUnits(driver).create_SIPDECT_Extension(driver, method.getName(), ipData, loginData, pmTests,0);
			
			driver.findElement(By.xpath("//td[contains(text(),'"+testData[1]+"')]//preceding-sibling::td[19]")).click();
			new SelectDropDownValue().selectByIndex(pmExtension.getMyCSPNameDropDown(), 1);

			pmExtension.getEditPageApplyButton().click();
			Assert.assertEquals(pmExtension.getResponseMessage(), "Change operation successful for:");
			pmExtension.getDoneButton().click();
			new SelectDropDownValue().selectByVisibleText(pmExtension.getExtensionTypeDropDownHomePage(), "Multi-Terminal");
			pmExtension.getEnterExtensionNumberTextBox().clear();
			pmExtension.setEnterExtensionNumberTextBox(testData[1]);
			pmExtension.getViewRangeButton().click();
			WebDriverWait wait = new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("blockUI")));
			driver.findElement(By.xpath("//td[contains(text(),'"+testData[1]+"')]//preceding-sibling::td[20]")).click();
			List<WebElement> sdn1  = driver.findElements(By.xpath("//td[contains(text(),'Common Service Profile')]//following-sibling::td[contains(text(),'"+testData[12]+"')]"));
//			System.out.println("//td[contains(text(),'Phone type')]//following-sibling::td[contains(text(),'"+testData[7]+"')]");
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
			list.add(testData[11]);
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
	public void test_editT_ConfigureHotLineNumber_SIPDECT(String hotLineType,Method method) throws InterruptedException
	{
		String[] testData = null;
		String[] hotLineDelayedHotLineData = null;
		try
		{
			pmTests.checkTestStatus(method.getName());
			wait = new WebDriverWait(driver, 10);
			pmExtension = new Extension(driver);
			loginPage = new PM_Login_Page(driver);
			pmMainPge = new PM_Main_Page(driver);
			pmServices = new PM_Services(driver);
			pmUser = new PM_User(driver);
			testData = pmTests.getData(method.getName(), 1);
			hotLineDelayedHotLineData = pmTests.getData(method.getName(), 3);
			list = new ArrayList<String>();
			list.add(testData[0]);
			list.add(hotLineDelayedHotLineData[0]);
			list.add(hotLineDelayedHotLineData[1]);
			list.add(hotLineDelayedHotLineData[2]);
			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
			
			new ReusableUnits(driver).create_SIPDECT_Extension(driver, method.getName(), ipData, loginData, pmTests,0);
			
			driver.findElement(By.xpath("//td[contains(text(),'"+testData[1]+"')]//preceding-sibling::td[19]")).click();
			
			pmExtension.getAdvanceButton().click();
			
			new SelectDropDownValue().selectByVisibleText(pmExtension.getHotLineDelayedHotLineForMultiTerminal(), hotLineType);
			pmExtension.setMultiTrmnlHotLineDelayedHotLineTextBox(hotLineDelayedHotLineData[3]);
			pmExtension.getEditPageApplyButton().click();
			Assert.assertEquals(pmExtension.getResponseMessage(), "Change operation successful for:");
			pmExtension.getDoneButton().click();
			
			List<WebElement> eles = driver.findElements(By.xpath("//td[contains(text(),'"+testData[1]+"')]"));
			Assert.assertTrue(eles.size()==1);
//			new SelectDropDownValue().selectByVisibleText(pmExtension.getExtensionTypeDropDownHomePage(), "Multi-Terminal");
//			pmExtension.getEnterExtensionNumberTextBox().clear();
//			pmExtension.setEnterExtensionNumberTextBox(testData[1]);
//			pmExtension.getViewRangeButton().click();
//			WebDriverWait wait = new WebDriverWait(driver, 20);
//			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("blockUI")));
//			driver.findElement(By.xpath("//td[contains(text(),'"+testData[1]+"')]//preceding-sibling::td[20]")).click();
//			
//			List<WebElement> eles  = driver.findElements(By.xpath("//td[contains(text(),'Phone Type')]//following-sibling::td[contains(text(),'"+hotLineType+"')]"));
//			List<WebElement> eles2  = driver.findElements(By.xpath("//td[contains(text(),'Hotline Number')]//following-sibling::td[contains(text(),'"+hotLineDelayedHotLineData[3]+"')]"));
//			Assert.assertTrue(eles.size() == 1 && eles2.size() == 1);
//			pmExtension.getDoneButton().click();
			pmUser.getLogoutLink().click();

		}
		finally
		{
			list.clear();
			list.add(testData[8]);
			list.add(hotLineDelayedHotLineData[4]);
			list.add(hotLineDelayedHotLineData[5]);
			list.add(hotLineDelayedHotLineData[6]);
			list.add(testData[9]);
			list.add(testData[10]);
			list.add(testData[11]);
			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
		}
	}
	
	@Test
	public void test_editSet_First_LastNames_SIPDECT(Method method) throws InterruptedException
	{
		String[] testData = null;
		try
		{
			pmTests.checkTestStatus(method.getName());
			wait = new WebDriverWait(driver, 10);
			pmExtension = new Extension(driver);
			loginPage = new PM_Login_Page(driver);
			pmMainPge = new PM_Main_Page(driver);
			pmServices = new PM_Services(driver);
			pmUser = new PM_User(driver);
			testData = pmTests.getData(method.getName(), 1);
			list = new ArrayList<String>();
			list.add(testData[0]);
			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
			
			new ReusableUnits(driver).create_SIPDECT_Extension(driver, method.getName(), ipData, loginData, pmTests,0);
			
			driver.findElement(By.xpath("//td[contains(text(),'"+testData[1]+"')]//preceding-sibling::td[19]")).click();
			
			pmExtension.getMultiTerminalFirstName().clear();
			pmExtension.getMultiTerminalLastName().clear();
			pmExtension.setMultiTerminalFirstName("EditedFirstName");
			pmExtension.setMultiTerminalLastName("EditedLastName");
			
			pmExtension.getEditPageApplyButton().click();
			Assert.assertEquals(pmExtension.getResponseMessage(), "Change operation successful for:");
			pmExtension.getDoneButton().click();
			new SelectDropDownValue().selectByVisibleText(pmExtension.getExtensionTypeDropDownHomePage(), "Multi-Terminal");
			pmExtension.getEnterExtensionNumberTextBox().clear();
			pmExtension.setEnterExtensionNumberTextBox(testData[1]);
			pmExtension.getViewRangeButton().click();
			WebDriverWait wait = new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("blockUI")));
			driver.findElement(By.xpath("//td[contains(text(),'"+testData[1]+"')]//preceding-sibling::td[20]")).click();
		
			List<WebElement> eles  = driver.findElements(By.xpath("//td[contains(text(),'First Name')]//following-sibling::td[contains(text(),'EditedFirstName')]"));
			List<WebElement> eles2  = driver.findElements(By.xpath("//td[contains(text(),'Last Name')]//following-sibling::td[contains(text(),'EditedLastName')]"));
			Assert.assertTrue(eles.size() == 1 && eles2.size() == 1);
			pmExtension.getDoneButton().click();
			pmUser.getLogoutLink().click();

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
	public void test_createUserWith_SIPDECT_Extension(Method method) throws InterruptedException
	{
		list = new ArrayList<String>();
		String[] credentials = loginData.getData("test_pm_valid_login", 1);
		String[] testData = pmTests.getData(method.getName(), 1);
		try
		{
			pmTests.checkTestStatus(method.getName());
			new ReusableUnits(driver).createUserWith_SIPDECT_Extension(driver, method.getName(), ipData, loginData, pmTests);
		}
		finally
		{
			new CleanUP(driver).deleteUser(driver, ipData, credentials, testData[0]);
			String[] extData = pmTests.getData(method.getName(), 3);
			list.clear();
			list.add(extData[8]);
			list.add(extData[9]);
			list.add(extData[10]);
			list.add(extData[11]);
			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
		}
	}
	
	@Test
	public void test_editUserToRemove_SIPDECT_Extension(Method method) throws InterruptedException
	{
		list = new ArrayList<String>();
		String[] credentials = loginData.getData("test_pm_valid_login", 1);
		String[] testData = pmTests.getData(method.getName(), 1);
		String[] extData = pmTests.getData(method.getName(), 3);
		try
		{
			pmTests.checkTestStatus(method.getName());
			pmUser = new PM_User(driver);
			pmMainPge = new PM_Main_Page(driver);
			new ReusableUnits(driver).createUserWith_SIPDECT_Extension(driver, method.getName(), ipData, loginData, pmTests);
			driver.findElement(By.xpath("(//td[contains(text(),'"+testData[0]+"')])[1]"
			+ "//following-sibling::td[contains(text(),'"+extData[1]+" ')]//preceding-sibling::td[25]")).click();
			pmUser.getUserEditServiceSummary().click();
			driver.findElement(By.id("removeThis"+extData[1]+"-MultiTerminal-SN_img")).click();
			driver.switchTo().alert().accept();
			Thread.sleep(2000);
			pmUser.getApplyButton().click();
			Assert.assertEquals(pmUser.getResponseMessage().getText().trim(), "Change operation successful for:");
			pmUser.getDoneButton().click();
			
			pmUser.getUserSearchTextBox().clear();
			pmUser.setUserSearchTextBox(testData[0]);
			pmUser.getOnViewRangeButton().click();
			
			List<WebElement> eles = driver.findElements(By.xpath("(//td[contains(text(),'"+testData[0]+"')])[1]//following-sibling::td[contains(text(),'"+extData[1]+"')]"));
			Assert.assertTrue(eles.size()==0);
			
		}
		finally
		{
			new CleanUP(driver).deleteUser(driver, ipData, credentials, testData[0]);
			list.clear();
			list.add(extData[8]);
			list.add(extData[9]);
			list.add(extData[10]);
			list.add(extData[11]);
			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
		}
	}
	
	@Test
	public void test_editUser_AssignExisting_SIPDECT_Extension(Method method) throws InterruptedException
	{
		list = new ArrayList<String>();
		String[] credentials = loginData.getData("test_pm_valid_login", 1);
		String[] testData = pmTests.getData(method.getName(), 1);
		String[] extData = pmTests.getData(method.getName(), 3);
		wait = new WebDriverWait(driver, 20);
		try 
		{
			pmTests.checkTestStatus(method.getName());
			pmExtension = new Extension(driver);
			loginPage = new PM_Login_Page(driver);
			pmMainPge = new PM_Main_Page(driver);
			pmServices = new PM_Services(driver);
			pmUser = new PM_User(driver);
			pmUsers = new PM_Users(driver);
			list.add(extData[0]);
			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
			new ReusableUnits(driver).createUser(driver, method.getName(), ipData, loginData, pmTests);
			
			//create SIP DECT extension
			pmMainPge.getServices().click();
			pmServices.getExtension().click();
			pmExtension.getAddButton().click();
			new SelectDropDownValue().selectByVisibleText(pmExtension.getExtensionType(), "SIP DECT");
			pmExtension.getNextButton().click();
			new SelectDropDownValue().selectByVisibleText(pmExtension.getSelectExtensionsRange(), extData[1]);
			String version = new GetMxoneVersionNumber(driver).getMxoneVersionNumber(driver);
			System.out.println(version);
			int ver = Integer.parseInt(version);
			System.out.println(ver);
			if(ver >= 720000)
			{
				pmExtension.setMultiTerminalExtensionTextBox(extData[1]);
			}
			else
			{
				new SelectDropDownValue().selectByVisibleText(pmExtension.getMultiTerminalExtensionDropDown(), extData[1]);
			}
			new SelectDropDownValue().selectByIndex(pmExtension.getMyCSPNameDropDown(), 0);
			new SelectDropDownValue().selectByVisibleText(pmExtension.getMultiTerminalServerDropDown(), extData[2]);
			
			pmExtension.getAddSipDectTerminalButton().click();
			pmExtension.setSIPDectName(extData[3]);
			pmExtension.setSIPDectDescription1("SIP DECT");
			pmExtension.setSIPDectDescription2("SIP DECT");
			pmExtension.setSIPDectAuthKey(extData[4]);
			pmExtension.setSIPDectIPEINumber(extData[5]);
			pmExtension.getApplyButton().click();
			
			pmExtension.setMultiTerminalFirstName(extData[6]);
			pmExtension.setMultiTerminalLastName(extData[7]);
			pmExtension.getApplyButton().click();
			
			Assert.assertEquals(pmExtension.getResponseMessage(), "Add operation successful for:");
			pmExtension.getDoneButton().click();
			
			new SelectDropDownValue().selectByVisibleText(pmExtension.getExtensionTypeDropDownHomePage(), "Multi-Terminal");
			pmExtension.setEnterExtensionNumberTextBox(extData[1]);
			pmExtension.getViewRangeButton().click();
			
			List<WebElement> eles = driver.findElements(By.xpath("//td[contains(text(),'"+extData[1]+"')]"));
			Assert.assertTrue(eles.size()==1);
			
			//now edit user and assign extension...
			pmMainPge.getUsers().click();
			pmUsers.getUser().click();
			pmUser.setUserSearchTextBox(testData[0]);
			pmUser.getOnViewRangeButton().click();
			
			driver.findElement(By.xpath("(//td[contains(text(),'"+testData[0]+"')])[1]//preceding-sibling::td[19]")).click();
			pmUser.getUserEditServiceSummary().click();
			pmUser.setExistingExtensionField(extData[1]);
			pmUser.getApplyButton().click();
			Assert.assertEquals(pmUser.getResponseMessage().getText().trim(), "Change operation successful for:");
			pmUser.getDoneButton().click();
			
			pmUser.getUserSearchTextBox().clear();
			pmUser.setUserSearchTextBox(testData[0]);
			pmUser.getOnViewRangeButton().click();
			List<WebElement> eles2 = driver.findElements(By.xpath("(//td[contains(text(),'"+testData[0]+"')])[1]//following-sibling::td[contains(text(),'"+extData[1]+"')]"));
			Assert.assertTrue(eles2.size() == 1);
		}
		finally
		{
			new CleanUP(driver).deleteUser(driver, ipData, credentials, testData[0]);
			list.clear();
			list.add(extData[8]);
			list.add(extData[9]);
			list.add(extData[10]);
			list.add(extData[11]);
			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
		}
	}
	
	@Test
	public void test_createUserAssignExisting_SIPDECT_Extension(Method method) throws InterruptedException
	{
		list = new ArrayList<String>();
		String[] credentials = loginData.getData("test_pm_valid_login", 1);
		String[] testData = pmTests.getData(method.getName(), 1);
		String[] extData = pmTests.getData(method.getName(), 3);
		wait = new WebDriverWait(driver, 20);
		try 
		{
			pmTests.checkTestStatus(method.getName());
			pmExtension = new Extension(driver);
			loginPage = new PM_Login_Page(driver);
			pmMainPge = new PM_Main_Page(driver);
			pmServices = new PM_Services(driver);
			
			pmUser = new PM_User(driver);
			pmUsers = new PM_Users(driver);
			list.add(extData[0]);
			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
			
			
			//create SIP DECT extension
			driver.get(ipData.getData(0, 0));
			credentials = loginData.getData("test_pm_valid_login", 1);
			loginPage.PM_Login(credentials[0], credentials[1]);
			pmMainPge.getServices().click();
			pmServices.getExtension().click();
			pmExtension.getAddButton().click();
			new SelectDropDownValue().selectByVisibleText(pmExtension.getExtensionType(), "SIP DECT");
			pmExtension.getNextButton().click();
			new SelectDropDownValue().selectByVisibleText(pmExtension.getSelectExtensionsRange(), extData[1]);
			String version = new GetMxoneVersionNumber(driver).getMxoneVersionNumber(driver);
			System.out.println(version);
			int ver = Integer.parseInt(version);
			System.out.println(ver);
			if(ver >= 720000)
			{
				pmExtension.setMultiTerminalExtensionTextBox(extData[1]);
			}
			else
			{
				new SelectDropDownValue().selectByVisibleText(pmExtension.getMultiTerminalExtensionDropDown(), extData[1]);
			}
			new SelectDropDownValue().selectByIndex(pmExtension.getMyCSPNameDropDown(), 0);
			new SelectDropDownValue().selectByVisibleText(pmExtension.getMultiTerminalServerDropDown(), extData[2]);
			
			pmExtension.getAddSipDectTerminalButton().click();
			pmExtension.setSIPDectName(extData[3]);
			pmExtension.setSIPDectDescription1("SIP DECT");
			pmExtension.setSIPDectDescription2("SIP DECT");
			pmExtension.setSIPDectAuthKey(extData[4]);
			pmExtension.setSIPDectIPEINumber(extData[5]);
			pmExtension.getApplyButton().click();
			
			pmExtension.setMultiTerminalFirstName(extData[6]);
			pmExtension.setMultiTerminalLastName(extData[7]);
			pmExtension.getApplyButton().click();
			
			Assert.assertEquals(pmExtension.getResponseMessage(), "Add operation successful for:");
			pmExtension.getDoneButton().click();
			
			new SelectDropDownValue().selectByVisibleText(pmExtension.getExtensionTypeDropDownHomePage(), "Multi-Terminal");
			pmExtension.setEnterExtensionNumberTextBox(extData[1]);
			pmExtension.getViewRangeButton().click();
			
			List<WebElement> eles = driver.findElements(By.xpath("//td[contains(text(),'"+extData[1]+"')]"));
			Assert.assertTrue(eles.size()==1);
			
			new ReusableUnits(driver).navigateUserToServiceSummaryPage(driver, method.getName(), ipData, loginData, pmTests);
			pmUser.setExistingExtensionField(extData[1]);
			pmUser.getApplyButton().click();
			Assert.assertEquals(pmUser.getResponseMessage().getText().trim(), "Add operation successful for:");
			pmUser.getDoneButton().click();
			
			pmUser.getUserSearchTextBox().clear();
			pmUser.setUserSearchTextBox(testData[0]);
			pmUser.getOnViewRangeButton().click();
			List<WebElement> eles2 = driver.findElements(By.xpath("(//td[contains(text(),'"+testData[0]+"')])[1]//following-sibling::td[contains(text(),'"+extData[1]+"')]"));
			Assert.assertTrue(eles2.size() == 1);
		}
		finally
		{
			new CleanUP(driver).deleteUser(driver, ipData, credentials, testData[0]);
			list.clear();
			list.add(extData[8]);
			list.add(extData[9]);
			list.add(extData[10]);
			list.add(extData[11]);
			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
		}
	}
	
	@Test
	public void test_editUserAssignNew_SIPDECT_Extension(Method method) throws InterruptedException
	{
		list = new ArrayList<String>();
		String[] credentials = loginData.getData("test_pm_valid_login", 1);
		String[] testData = pmTests.getData(method.getName(), 1);
		String[] extData = pmTests.getData(method.getName(), 3);
		wait = new WebDriverWait(driver, 20);
		try 
		{
			pmTests.checkTestStatus(method.getName());
			pmUser = new PM_User(driver);
			pmMainPge = new PM_Main_Page(driver);
			pmExtension = new Extension(driver);
			pmServices = new PM_Services(driver);
			pmUsers = new PM_Users(driver);
			
			list.add(extData[0]);
			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
			new ReusableUnits(driver).createUser(driver, method.getName(), ipData, loginData, pmTests);
						
			//Edit user...
			driver.findElement(By.xpath("(//td[contains(text(),'"+testData[0]+"')])[1]//preceding-sibling::td[19]")).click();
			pmUser.getUserEditServiceSummary().click();
			
			//create SIPDECT Extension...
			pmUser.getCreateAndAssignExtensionToUser().click();
			new SelectDropDownValue().selectByVisibleText(pmExtension.getExtensionType(), "SIP DECT");
			pmExtension.getNextButton().click();
			
			//Provide extension details.
			new SelectDropDownValue().selectByVisibleText(pmExtension.getSelectExtensionsRange(), extData[1]);
			String version = new GetMxoneVersionNumber(driver).getMxoneVersionNumber(driver);
			System.out.println(version);
			int ver = Integer.parseInt(version);
			System.out.println(ver);
			if(ver >= 720000)
			{
				pmExtension.setMultiTerminalExtensionTextBox(extData[1]);
			}
			else
			{
				new SelectDropDownValue().selectByVisibleText(pmExtension.getMultiTerminalExtensionDropDown(), extData[1]);
			}
			new SelectDropDownValue().selectByIndex(pmExtension.getMyCSPNameDropDown(), 0);
			new SelectDropDownValue().selectByVisibleText(pmExtension.getMultiTerminalServerDropDown(), extData[2]);
			
			pmExtension.getAddSipDectTerminalButton().click();
			pmExtension.setSIPDectName(extData[3]);
			pmExtension.setSIPDectDescription1("SIP DECT");
			pmExtension.setSIPDectDescription2("SIP DECT");
			pmExtension.setSIPDectAuthKey(extData[4]);
			pmExtension.setSIPDectIPEINumber(extData[5]);
			pmExtension.getApplyButton().click();
			
			pmExtension.setMultiTerminalFirstName(extData[6]);
			pmExtension.setMultiTerminalLastName(extData[7]);
			pmExtension.getApplyButton().click();
			pmExtension.getApplyButton().click();
			
			Assert.assertEquals(pmExtension.getResponseMessage(), "Add operation successful for:");
			pmExtension.getDoneButton().click();
			
			pmUser.setUserSearchTextBox(testData[0]);
			pmUser.getOnViewRangeButton().click();
			
			List<WebElement> eles = driver.findElements(By.xpath("(//td[contains(text(),'"+testData[0]+"')])[1]//following-sibling::td[contains(text(),'"+extData[1]+"')]"));
			Assert.assertTrue(eles.size()==1);
			pmMainPge.getLogoutLink().click();
		}
		finally
		{
			new CleanUP(driver).deleteUser(driver, ipData, credentials, testData[0]);
			list.clear();
			list.add(extData[8]);
			list.add(extData[9]);
			list.add(extData[10]);
			list.add(extData[11]);
			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
		}
	}
}
