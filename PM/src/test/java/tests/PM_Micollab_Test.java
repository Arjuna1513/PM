package tests;

import static org.testng.Assert.fail;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.Test;

import base.ConfigClass;
import pm_pom_classes.Extension;
import pm_pom_classes.Micollab_Login_Page;
import pm_pom_classes.Micollab_Server_Manager_Page;
import pm_pom_classes.PM_Auth_Code_Page;
import pm_pom_classes.PM_Login_Page;
import pm_pom_classes.PM_Main_Page;
import pm_pom_classes.PM_Services;
import pm_pom_classes.PM_User;
import pm_pom_classes.PM_Users;
import utilities.CleanUP;
import utilities.ExecuteCommands;
import utilities.ExplicitWait;
import utilities.GetMxoneVersionNumber;
import utilities.LoggerClass;
import utilities.ReusableUnits;
import utilities.SelectDropDownValue;
import utilities.Take_Screenshot;

public class PM_Micollab_Test extends ConfigClass
{
	public Micollab_Login_Page micoLogin;
	public Micollab_Server_Manager_Page serverManagerPage;
	public PM_Login_Page pmLoginPage;
	public PM_Main_Page pmMainPage;
	public PM_User pmUser;
	public PM_Users pmUsers;
	ArrayList<String> list = null;
	WebDriverWait wait = null;
	public Extension pmExtension; 
	PM_Auth_Code_Page authCodePage;
	private Logger log = LoggerClass.getLogger("PM_Micollab_Test");	
	
	/*@Test
	public void click_Users_and_services() throws InterruptedException
	{
		micoLogin = new Micollab_Login_Page(driver);
		serverManagerPage = new Micollab_Server_Manager_Page(driver);
		driver.get(ipData.getData(2, 0));
		String[] credentials = loginData.getData("test_micollab_login", 1);
		micoLogin.micollab_login(credentials[0], credentials[1]);
		driver.switchTo().frame(serverManagerPage.getInToNavigationFrame());
		serverManagerPage.getUsersAndServicesLink().click();
		driver.switchTo().defaultContent();
		driver.switchTo().frame(serverManagerPage.getMainFrame());
		serverManagerPage.getUserSearchTextBox().sendKeys("*");
		serverManagerPage.getSearchButton().click();
		Thread.sleep(10000);
//		Thread.sleep(2000);
	}
	*/
	
	@Test
	public void test_CreateUser_basicRole(Method method) throws Exception
	{
		authCodePage = new PM_Auth_Code_Page(driver);
		micoLogin = new Micollab_Login_Page(driver);
		serverManagerPage = new Micollab_Server_Manager_Page(driver);
		pmLoginPage = new PM_Login_Page(driver);
		pmMainPage = new PM_Main_Page(driver);
		pmUser = new PM_User(driver);
		pmUsers = new PM_Users(driver);
		pmExtension = new Extension(driver);
		
		
		list = new ArrayList<String>();
		String[] credentials = loginData.getData("test_pm_valid_login", 1);
		String[] testData = pmTests.getData(method.getName(), 1);
		String[] extData = pmTests.getData(method.getName(), 3);
		String[] micoCredentials = loginData.getData("test_micollab_login", 1);
		wait = new WebDriverWait(driver, 20);
		try 
		{
			pmTests.checkTestStatus(method.getName());
			pmUser = new PM_User(driver);
			pmMainPage = new PM_Main_Page(driver);
			list.add(extData[0]);
			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
			new ReusableUnits(driver).navigateUserToServiceSummaryPage(driver, method.getName(), ipData, loginData, pmTests);
			
			
			pmUser.getCreateAndAssignExtensionToUser().click();
			new SelectDropDownValue().selectByVisibleText(pmExtension.getExtensionType(), "IP");
			pmExtension.getNextButton().click();
			
			//Provide extension details.
			new SelectDropDownValue().selectByVisibleText(pmExtension.getSelectExtensionsRange(), extData[1]);
			String version = new GetMxoneVersionNumber(driver).getMxoneVersionNumber(driver);
			System.out.println(version);
			int ver = Integer.parseInt(version);
			System.out.println(ver);
			if(ver >= 72000)
			{
				pmExtension.setSingleExtensionValue(extData[1]);
			}
			else
			{
				new SelectDropDownValue().selectByVisibleText(pmExtension.getSingleExtensionDropDown(), extData[1]);
			}
			new SelectDropDownValue().selectByIndex(pmExtension.getMyCSPNameDropDown(),0);
			new SelectDropDownValue().selectByVisibleText(pmExtension.getServerDropDown(), "1");
			pmExtension.getFirstName().clear();
			pmExtension.getLastName().clear();
			pmExtension.setFirstName(extData[2]);
			pmExtension.setLastName(extData[3]);
			new SelectDropDownValue().selectByVisibleText(pmExtension.getPhoneTypeDropDown(), extData[4]);
			
			pmExtension.getAuthCodeButton().click();
			authCodePage.getAuthCodeAddButton().click();
			authCodePage.setAuthCodeTextBox(extData[5]);
			new SelectDropDownValue().selectByValue(authCodePage.gethashTypeDropDown(), extData[6]);
			authCodePage.setCilCode(extData[5]);
			authCodePage.getAuthPageApplyButton().click();
			
			List<WebElement> eleCount = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//td[contains(text(),'CLEARTEXT')]"));
			Assert.assertTrue(eleCount.size() == 1);
			
			authCodePage.getContinueButton().click();
			authCodePage.getContinueButtonToServiceSummaryPage().click();
			
//			authCodePage.getNextButtonToMicollabConfigPage().click();
			pmExtension.getNextButton().click();
			new SelectDropDownValue().selectByVisibleText(authCodePage.getMicollabRoleDropDown(), extData[7]);
			driver.switchTo().alert().accept();
			pmExtension.getApplyButton().click();
			
			WebDriverWait wait = new WebDriverWait(driver, 100);
//			wait.until(ExpectedConditions.textToBe(By.className("responseMessage"), "  Import operation successful for:"));
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(@class,'responseMessage')]")));
			Assert.assertEquals(driver.findElement(By.xpath("//span[contains(@class,'responseMessage')]")).getText().trim(), "Add operation successful for:");
			
			
//			Assert.assertEquals(pmUser.getResponseMessage().getText().trim(), "Add operation successful for:");
			pmUser.getDoneButton().click();
//			pmUser.getDoneButton().click();
			
			pmUser.getUserSearchTextBox().clear();
			pmUser.setUserSearchTextBox(testData[0]);
			pmUser.getOnViewRangeButton().click();
			
			List<WebElement> eles = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("(//td[contains(text(),'"+testData[0]+"')])[1]//following-sibling::"
					+ "td[contains(text(),'"+extData[1]+" / SN')]"
					+ "//following-sibling::td[contains(text(),'Micollab')]"
					+ "//following-sibling::td[contains(text(),'"+extData[7]+"')]"));
			Assert.assertTrue(eles.size() == 1);
			
			pmMainPage.getLogoutLink().click();
			driver.get(ipData.getData(2, 0));
			micoLogin.micollab_login(micoCredentials[0], micoCredentials[1]);
			driver.switchTo().frame(serverManagerPage.getInToNavigationFrame());
			serverManagerPage.getUsersAndServicesLink().click();
			driver.switchTo().defaultContent();
			driver.switchTo().frame(serverManagerPage.getMainFrame());
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='basicSearchTerm']")));
			serverManagerPage.getUserSearchTextBox().sendKeys(testData[0]);
			serverManagerPage.getSearchButton().click();
			Thread.sleep(1000);
			List<WebElement> phNums = driver.findElements(By.xpath("(//a[contains(text(),'"+testData[0]+"')]//parent::span//parent::td//following-sibling::td)[2]/span"));
			
			Assert.assertEquals(phNums.get(0).getText().trim(), extData[1]);
			Assert.assertEquals(phNums.get(1).getText().trim(), testData[5]);
			Assert.assertEquals(phNums.get(2).getText().trim(), testData[6]);
			Assert.assertEquals(phNums.get(3).getText().trim(), testData[7]);
			Assert.assertEquals(phNums.get(4).getText().trim(), testData[8]);
			
			driver.findElement(By.xpath("//a[contains(text(),'"+testData[0]+"')]")).click();
			Thread.sleep(1000);
			//System.out.println("First name is :"+serverManagerPage.getFirstNameTextBox().getText().trim());
			//Assert.assertEquals(serverManagerPage.getFirstNameTextBox().getAttribute("value").trim(), testData[0]);
			Assert.assertEquals(serverManagerPage.getLastNameTextBox().getAttribute("value").trim(), testData[0]);
			Assert.assertEquals(serverManagerPage.getEmailAddress().getAttribute("value").trim(), testData[2]);
			serverManagerPage.getPhoneTab().click();
			
			List<WebElement> homePhone1 = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//input[@value='Home Phone']"
					+ "//..//..//following-sibling::tr/td[2]/input[@value='"+testData[5]+"']"));
			List<WebElement> homePhone2 = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//input[@value='Home Phone 2']"
					+ "//..//..//following-sibling::tr/td[2]/input[@value='"+testData[6]+"']"));
			List<WebElement> mobNumber1 = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//input[@value='Mobile Number']"
					+ "//..//..//following-sibling::tr/td[2]/input[@value='"+testData[7]+"']"));
			List<WebElement> mobNumber2 = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//input[@value='Mobile Number 2']"
					+ "//..//..//following-sibling::tr/td[2]/input[@value='"+testData[8]+"']"));
			List<WebElement> extNumber = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//input[@value='"+extData[1]+"']"));
			Assert.assertTrue(homePhone1.size()==1 && homePhone2.size()==1 && mobNumber1.size()==1 && 
					mobNumber2.size()==1 && extNumber.size()==1);
//			Thread.sleep(1000);
//			JavascriptExecutor js = (JavascriptExecutor)driver;
//			js.executeScript("arguments[0].scrollIntoView(true);", serverManagerPage.getSipPassword());
//			Thread.sleep(5000);
//			Assert.assertEquals(serverManagerPage.getSipPassword().getAttribute("value").trim(), extData[5]);
//			Assert.assertEquals(serverManagerPage.getSipConfirmPassword().getAttribute("value").trim(), extData[5]);
			
//			serverManagerPage.getMicollabClientTab().click();
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//a[text()='MiCollab Client'])[2]"))).click();
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//select[@name='ucaService.featureProfileID']/option[@selected='selected']")));
			String assignedRole = driver.findElement(By.xpath("//select[@name='ucaService.featureProfileID']/option[@selected='selected']")).getText().trim();
//			String dskPhoneExt = driver.findElement(By.xpath("//select[@id='UCADeskphoneSelect']/option[@selected='selected']")).getText().trim();
//			String mailBoxNum = driver.findElement(By.xpath("//select[@id='ucaMailboxSelect']/option[@selected='selected']")).getText().trim();
			
			Assert.assertEquals(assignedRole, "Default Feature Profile");
//			Assert.assertTrue(dskPhoneExt.contains(extData[1]));
//			Assert.assertEquals(mailBoxNum, extData[1]);
			
			
			serverManagerPage.getCancelButton().click();
			List<WebElement> role = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//img[@src='images/g_check_000.gif']"));
			Assert.assertTrue(role.size()==1);
			
			driver.switchTo().defaultContent();
			driver.switchTo().frame(serverManagerPage.getHeaderFrame());
			Thread.sleep(2000);
			serverManagerPage.getLogoutLink().click();
			
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
			try
			{
				new CleanUP(driver).deleteUser(driver, ipData, credentials, testData[0]);
				list.clear();
				list.add(extData[8]);
				list.add(extData[9]);
				list.add(extData[10]);
				new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
			}
			catch(Exception e)
			{
					new Take_Screenshot().get_Screenshot(driver, method.getName());
					throw e;
			}
		}
	}
	@Test
	public void test_CreateUser_entryRole(Method method) throws Exception
	{
		authCodePage = new PM_Auth_Code_Page(driver);
		micoLogin = new Micollab_Login_Page(driver);
		serverManagerPage = new Micollab_Server_Manager_Page(driver);
		pmLoginPage = new PM_Login_Page(driver);
		pmMainPage = new PM_Main_Page(driver);
		pmUser = new PM_User(driver);
		pmUsers = new PM_Users(driver);
		pmExtension = new Extension(driver);
		
		
		list = new ArrayList<String>();
		String[] credentials = loginData.getData("test_pm_valid_login", 1);
		String[] testData = pmTests.getData(method.getName(), 1);
		String[] extData = pmTests.getData(method.getName(), 3);
		String[] micoCredentials = loginData.getData("test_micollab_login", 1);
		wait = new WebDriverWait(driver, 20);
		try 
		{
			pmTests.checkTestStatus(method.getName());
			pmUser = new PM_User(driver);
			pmMainPage = new PM_Main_Page(driver);
			list.add(extData[0]);
			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
			new ReusableUnits(driver).navigateUserToServiceSummaryPage(driver, method.getName(), ipData, loginData, pmTests);
			
			
			pmUser.getCreateAndAssignExtensionToUser().click();
			new SelectDropDownValue().selectByVisibleText(pmExtension.getExtensionType(), "IP");
			pmExtension.getNextButton().click();
			
			//Provide extension details.
			new SelectDropDownValue().selectByVisibleText(pmExtension.getSelectExtensionsRange(), extData[1]);
			String version = new GetMxoneVersionNumber(driver).getMxoneVersionNumber(driver);
			System.out.println(version);
			int ver = Integer.parseInt(version);
			System.out.println(ver);
			if(ver >= 72000)
			{
				pmExtension.setSingleExtensionValue(extData[1]);
			}
			else
			{
				new SelectDropDownValue().selectByVisibleText(pmExtension.getSingleExtensionDropDown(), extData[1]);
			}
			new SelectDropDownValue().selectByIndex(pmExtension.getMyCSPNameDropDown(),0);
			new SelectDropDownValue().selectByVisibleText(pmExtension.getServerDropDown(), "1");
			pmExtension.getFirstName().clear();
			pmExtension.getLastName().clear();
			pmExtension.setFirstName(extData[2]);
			pmExtension.setLastName(extData[3]);
			new SelectDropDownValue().selectByVisibleText(pmExtension.getPhoneTypeDropDown(), extData[4]);
			
			pmExtension.getAuthCodeButton().click();
			authCodePage.getAuthCodeAddButton().click();
			authCodePage.setAuthCodeTextBox(extData[5]);
			new SelectDropDownValue().selectByValue(authCodePage.gethashTypeDropDown(), extData[6]);
			authCodePage.setCilCode(extData[5]);
			authCodePage.getAuthPageApplyButton().click();
			
			List<WebElement> eleCount = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//td[contains(text(),'CLEARTEXT')]"));
			Assert.assertTrue(eleCount.size() == 1);
			
			authCodePage.getContinueButton().click();
			authCodePage.getContinueButtonToServiceSummaryPage().click();
			
//			authCodePage.getNextButtonToMicollabConfigPage().click();
			pmExtension.getNextButton().click();
			new SelectDropDownValue().selectByVisibleText(authCodePage.getMicollabRoleDropDown(), extData[7]);
			driver.switchTo().alert().accept();
			pmExtension.getApplyButton().click();
			
			WebDriverWait wait = new WebDriverWait(driver, 100);
//			wait.until(ExpectedConditions.textToBe(By.className("responseMessage"), "  Import operation successful for:"));
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(@class,'responseMessage')]")));
			Assert.assertEquals(driver.findElement(By.xpath("//span[contains(@class,'responseMessage')]")).getText().trim(), "Add operation successful for:");
			
			
//			Assert.assertEquals(pmUser.getResponseMessage().getText().trim(), "Add operation successful for:");
			pmUser.getDoneButton().click();
//			pmUser.getDoneButton().click();
			
			pmUser.getUserSearchTextBox().clear();
			pmUser.setUserSearchTextBox(testData[0]);
			pmUser.getOnViewRangeButton().click();
			
			List<WebElement> eles = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("(//td[contains(text(),'"+testData[0]+"')])[1]//following-sibling::"
					+ "td[contains(text(),'"+extData[1]+" / SN')]"
					+ "//following-sibling::td[contains(text(),'Micollab')]"
					+ "//following-sibling::td[contains(text(),'"+extData[7]+"')]"));
			Assert.assertTrue(eles.size() == 1);
			
			pmMainPage.getLogoutLink().click();
			driver.get(ipData.getData(2, 0));
			Thread.sleep(1000);
			micoLogin.micollab_login(micoCredentials[0], micoCredentials[1]);
			driver.switchTo().frame(serverManagerPage.getInToNavigationFrame());
			serverManagerPage.getUsersAndServicesLink().click();
			driver.switchTo().defaultContent();
			driver.switchTo().frame(serverManagerPage.getMainFrame());
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='basicSearchTerm']")));
			serverManagerPage.getUserSearchTextBox().sendKeys(testData[0]);
			serverManagerPage.getSearchButton().click();
			Thread.sleep(1000);
			List<WebElement> phNums = driver.findElements(By.xpath("(//a[contains(text(),'"+testData[0]+"')]//parent::span//parent::td//following-sibling::td)[2]/span"));
			
			Assert.assertEquals(phNums.get(0).getText().trim(), extData[1]);
			Assert.assertEquals(phNums.get(1).getText().trim(), testData[5]);
			Assert.assertEquals(phNums.get(2).getText().trim(), testData[6]);
			Assert.assertEquals(phNums.get(3).getText().trim(), testData[7]);
			Assert.assertEquals(phNums.get(4).getText().trim(), testData[8]);
			
			driver.findElement(By.xpath("//a[contains(text(),'"+testData[0]+"')]")).click();
			Thread.sleep(1000);
			//System.out.println("First name is :"+serverManagerPage.getFirstNameTextBox().getText().trim());
			//Assert.assertEquals(serverManagerPage.getFirstNameTextBox().getAttribute("value").trim(), testData[0]);
			Assert.assertEquals(serverManagerPage.getLastNameTextBox().getAttribute("value").trim(), testData[0]);
			Assert.assertEquals(serverManagerPage.getEmailAddress().getAttribute("value").trim(), testData[2]);
			serverManagerPage.getPhoneTab().click();
			
			List<WebElement> homePhone1 = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//input[@value='Home Phone']"
					+ "//..//..//following-sibling::tr/td[2]/input[@value='"+testData[5]+"']"));
			List<WebElement> homePhone2 = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//input[@value='Home Phone 2']"
					+ "//..//..//following-sibling::tr/td[2]/input[@value='"+testData[6]+"']"));
			List<WebElement> mobNumber1 = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//input[@value='Mobile Number']"
					+ "//..//..//following-sibling::tr/td[2]/input[@value='"+testData[7]+"']"));
			List<WebElement> mobNumber2 = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//input[@value='Mobile Number 2']"
					+ "//..//..//following-sibling::tr/td[2]/input[@value='"+testData[8]+"']"));
			List<WebElement> extNumber = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("(//input[@value='"+extData[1]+"'])[1]"));
			Assert.assertTrue(homePhone1.size()==1 && homePhone2.size()==1 && mobNumber1.size()==1 && 
					mobNumber2.size()==1 && extNumber.size()==1);
			
//			JavascriptExecutor js = (JavascriptExecutor)driver;
//			js.executeScript("arguments[0].scrollIntoView(true);", serverManagerPage.getSipPassword());
//			Thread.sleep(5000);
//			Assert.assertEquals(serverManagerPage.getSipPassword().getAttribute("value").trim(), extData[5]);
//			Assert.assertEquals(serverManagerPage.getSipConfirmPassword().getAttribute("value").trim(), extData[5]);
			
//			serverManagerPage.getMicollabClientTab().click();
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//a[text()='MiCollab Client'])[2]"))).click();
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//select[@name='ucaService.featureProfileID']/option[@selected='selected']")));
			String assignedRole = driver.findElement(By.xpath("//select[@name='ucaService.featureProfileID']/option[@selected='selected']")).getText().trim();
			String dskPhoneExt = driver.findElement(By.xpath("//select[@id='UCADeskphoneSelect']/option[@selected='selected']")).getText().trim();
			String mailBoxNum = driver.findElement(By.xpath("//select[@id='ucaMailboxSelect']/option[@selected='selected']")).getText().trim();
			
			Assert.assertEquals(assignedRole, extData[7]);
			Assert.assertTrue(dskPhoneExt.contains(extData[1]));
			Assert.assertEquals(mailBoxNum, extData[1]);
			
			serverManagerPage.getCancelButton().click();
			List<WebElement> role = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//img[@src='images/g_check_000.gif']"));
			Assert.assertTrue(role.size()==2);
			
			
			driver.switchTo().defaultContent();
			driver.switchTo().frame(serverManagerPage.getHeaderFrame());
			Thread.sleep(2000);
			serverManagerPage.getLogoutLink().click();
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
			try
			{
				new CleanUP(driver).deleteUser(driver, ipData, credentials, testData[0]);
				list.clear();
				list.add(extData[8]);
				list.add(extData[9]);
				list.add(extData[10]);
				new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
			}
			catch(Exception e)
			{
					new Take_Screenshot().get_Screenshot(driver, method.getName());
					throw e;
			}
		}
	}
	
	
	
	@Test
	public void test_CreateUser_standardRole(Method method) throws Exception
	{
		authCodePage = new PM_Auth_Code_Page(driver);
		micoLogin = new Micollab_Login_Page(driver);
		serverManagerPage = new Micollab_Server_Manager_Page(driver);
		pmLoginPage = new PM_Login_Page(driver);
		pmMainPage = new PM_Main_Page(driver);
		pmUser = new PM_User(driver);
		pmUsers = new PM_Users(driver);
		pmExtension = new Extension(driver);
		
		
		list = new ArrayList<String>();
		String[] credentials = loginData.getData("test_pm_valid_login", 1);
		String[] testData = pmTests.getData(method.getName(), 1);
		String[] extData = pmTests.getData(method.getName(), 3);
		String[] micoCredentials = loginData.getData("test_micollab_login", 1);
		wait = new WebDriverWait(driver, 20);
		try 
		{
			pmTests.checkTestStatus(method.getName());
			pmUser = new PM_User(driver);
			pmMainPage = new PM_Main_Page(driver);
			list.add(extData[0]);
			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
			new ReusableUnits(driver).navigateUserToServiceSummaryPage(driver, method.getName(), ipData, loginData, pmTests);
			
			
			pmUser.getCreateAndAssignExtensionToUser().click();
			new SelectDropDownValue().selectByVisibleText(pmExtension.getExtensionType(), "IP");
			pmExtension.getNextButton().click();
			
			//Provide extension details.
			new SelectDropDownValue().selectByVisibleText(pmExtension.getSelectExtensionsRange(), extData[1]);
			String version = new GetMxoneVersionNumber(driver).getMxoneVersionNumber(driver);
			System.out.println(version);
			int ver = Integer.parseInt(version);
			System.out.println(ver);
			if(ver >= 72000)
			{
				pmExtension.setSingleExtensionValue(extData[1]);
			}
			else
			{
				new SelectDropDownValue().selectByVisibleText(pmExtension.getSingleExtensionDropDown(), extData[1]);
			}
			new SelectDropDownValue().selectByIndex(pmExtension.getMyCSPNameDropDown(),0);
			new SelectDropDownValue().selectByVisibleText(pmExtension.getServerDropDown(), "1");
			pmExtension.getFirstName().clear();
			pmExtension.getLastName().clear();
			pmExtension.setFirstName(extData[2]);
			pmExtension.setLastName(extData[3]);
			new SelectDropDownValue().selectByVisibleText(pmExtension.getPhoneTypeDropDown(), extData[4]);
			
			pmExtension.getAuthCodeButton().click();
			authCodePage.getAuthCodeAddButton().click();
			authCodePage.setAuthCodeTextBox(extData[5]);
			new SelectDropDownValue().selectByValue(authCodePage.gethashTypeDropDown(), extData[6]);
			authCodePage.setCilCode(extData[5]);
			authCodePage.getAuthPageApplyButton().click();
			
			List<WebElement> eleCount = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//td[contains(text(),'CLEARTEXT')]"));
			Assert.assertTrue(eleCount.size() == 1);
			
			authCodePage.getContinueButton().click();
			authCodePage.getContinueButtonToServiceSummaryPage().click();
			
//			authCodePage.getNextButtonToMicollabConfigPage().click();
			pmExtension.getNextButton().click();
			new SelectDropDownValue().selectByVisibleText(authCodePage.getMicollabRoleDropDown(), extData[7]);
			driver.switchTo().alert().accept();
			pmExtension.getApplyButton().click();
			
			WebDriverWait wait = new WebDriverWait(driver, 100);
//			wait.until(ExpectedConditions.textToBe(By.className("responseMessage"), "  Import operation successful for:"));
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(@class,'responseMessage')]")));
			Assert.assertEquals(driver.findElement(By.xpath("//span[contains(@class,'responseMessage')]")).getText().trim(), "Add operation successful for:");
			
			
//			Assert.assertEquals(pmUser.getResponseMessage().getText().trim(), "Add operation successful for:");
			pmUser.getDoneButton().click();
//			pmUser.getDoneButton().click();
			
			pmUser.getUserSearchTextBox().clear();
			pmUser.setUserSearchTextBox(testData[0]);
			pmUser.getOnViewRangeButton().click();
			
			List<WebElement> eles = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("(//td[contains(text(),'"+testData[0]+"')])[1]//following-sibling::"
					+ "td[contains(text(),'"+extData[1]+" / SN')]"
					+ "//following-sibling::td[contains(text(),'Micollab')]"
					+ "//following-sibling::td[contains(text(),'"+extData[7]+"')]"));
			Assert.assertTrue(eles.size() == 1);
			
			pmMainPage.getLogoutLink().click();
			driver.get(ipData.getData(2, 0));
			Thread.sleep(1000);
			micoLogin.micollab_login(micoCredentials[0], micoCredentials[1]);
			driver.switchTo().frame(serverManagerPage.getInToNavigationFrame());
			serverManagerPage.getUsersAndServicesLink().click();
			driver.switchTo().defaultContent();
			driver.switchTo().frame(serverManagerPage.getMainFrame());
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='basicSearchTerm']")));
			serverManagerPage.getUserSearchTextBox().sendKeys(testData[0]);
			serverManagerPage.getSearchButton().click();
			Thread.sleep(1000);
			List<WebElement> phNums = driver.findElements(By.xpath("(//a[contains(text(),'"+testData[0]+"')]//parent::span//parent::td//following-sibling::td)[2]/span"));
			
			Assert.assertEquals(phNums.get(0).getText().trim(), extData[1]);
			Assert.assertEquals(phNums.get(1).getText().trim(), testData[5]);
			Assert.assertEquals(phNums.get(2).getText().trim(), testData[6]);
			Assert.assertEquals(phNums.get(3).getText().trim(), testData[7]);
			Assert.assertEquals(phNums.get(4).getText().trim(), testData[8]);
			
			driver.findElement(By.xpath("//a[contains(text(),'"+testData[0]+"')]")).click();
			//wait.until(ExpectedConditions.elementToBeClickable(By.name("firstNameDisplay")));
			Thread.sleep(1000);
			//System.out.println("First name is :"+serverManagerPage.getFirstNameTextBox().getText().trim());
			//Assert.assertEquals(serverManagerPage.getFirstNameTextBox().getAttribute("value").trim(), testData[0]);
			Assert.assertEquals(serverManagerPage.getLastNameTextBox().getAttribute("value").trim(), testData[0]);
			Assert.assertEquals(serverManagerPage.getEmailAddress().getAttribute("value").trim(), testData[2]);
			
			wait.until(ExpectedConditions.elementToBeClickable(By.id("phones_0"))).click();
			Thread.sleep(2000);
//			serverManagerPage.getPhoneTab().click();
//			Thread.sleep(3000);
//			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@value='Home Phone']"
//					+ "//..//..//following-sibling::tr/td[2]/input[@value='"+testData[5]+"']")));
			
			List<WebElement> homePhone1 = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//input[@value='Home Phone']"
					+ "//..//..//following-sibling::tr/td[2]/input[@value='"+testData[5]+"']"));
			List<WebElement> homePhone2 = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//input[@value='Home Phone 2']"
					+ "//..//..//following-sibling::tr/td[2]/input[@value='"+testData[6]+"']"));
			List<WebElement> mobNumber1 = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//input[@value='Mobile Number']"
					+ "//..//..//following-sibling::tr/td[2]/input[@value='"+testData[7]+"']"));
			List<WebElement> mobNumber2 = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//input[@value='Mobile Number 2']"
					+ "//..//..//following-sibling::tr/td[2]/input[@value='"+testData[8]+"']"));
			List<WebElement> extNumber = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("(//input[@value='"+extData[1]+"'])[1]"));
			Assert.assertTrue(homePhone1.size()==1 && homePhone2.size()==1 && mobNumber1.size()==1 && 
					mobNumber2.size()==1 && extNumber.size()==1);
			
//			JavascriptExecutor js = (JavascriptExecutor)driver;
//			js.executeScript("arguments[0].scrollIntoView(true);", serverManagerPage.getSipPassword());
//			Thread.sleep(5000);
//			Assert.assertEquals(serverManagerPage.getSipPassword().getAttribute("value").trim(), extData[5]);
//			Assert.assertEquals(serverManagerPage.getSipConfirmPassword().getAttribute("value").trim(), extData[5]);
			
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//a[text()='MiCollab Client'])[2]"))).click();
//			serverManagerPage.getMicollabClientTab().click();
//			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//select[@name='ucaService.featureProfileID']/option[@selected='selected']")));
			String assignedRole = driver.findElement(By.xpath("//select[@name='ucaService.featureProfileID']/option[@selected='selected']")).getText().trim();
			String dskPhoneExt = driver.findElement(By.xpath("//select[@id='UCADeskphoneSelect']/option[@selected='selected']")).getText().trim();
			String mailBoxNum = driver.findElement(By.xpath("//select[@id='ucaMailboxSelect']/option[@selected='selected']")).getText().trim();
			
			Assert.assertEquals(assignedRole, extData[7]);
			Assert.assertTrue(dskPhoneExt.contains(extData[1]));
			Assert.assertEquals(mailBoxNum, extData[1]);
			
			serverManagerPage.getCancelButton().click();
			List<WebElement> role = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//img[@src='images/g_check_000.gif']"));
			Assert.assertTrue(role.size()==4);
			
			
			driver.switchTo().defaultContent();
			driver.switchTo().frame(serverManagerPage.getHeaderFrame());
			Thread.sleep(2000);
			serverManagerPage.getLogoutLink().click();
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
			try
			{
				new CleanUP(driver).deleteUser(driver, ipData, credentials, testData[0]);
				list.clear();
				list.add(extData[8]);
				list.add(extData[9]);
				list.add(extData[10]);
				new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
			}
			catch(Exception e)
			{
					new Take_Screenshot().get_Screenshot(driver, method.getName());
					throw e;
			}
		}
	}
	
	
	
	@Test
	public void test_CreateUser_premiumRole(Method method) throws Exception
	{
		authCodePage = new PM_Auth_Code_Page(driver);
		micoLogin = new Micollab_Login_Page(driver);
		serverManagerPage = new Micollab_Server_Manager_Page(driver);
		pmLoginPage = new PM_Login_Page(driver);
		pmMainPage = new PM_Main_Page(driver);
		pmUser = new PM_User(driver);
		pmUsers = new PM_Users(driver);
		pmExtension = new Extension(driver);
		
		
		list = new ArrayList<String>();
		String[] credentials = loginData.getData("test_pm_valid_login", 1);
		String[] testData = pmTests.getData(method.getName(), 1);
		String[] extData = pmTests.getData(method.getName(), 3);
		String[] micoCredentials = loginData.getData("test_micollab_login", 1);
		wait = new WebDriverWait(driver, 20);
		try 
		{
			pmTests.checkTestStatus(method.getName());
			pmUser = new PM_User(driver);
			pmMainPage = new PM_Main_Page(driver);
			list.add(extData[0]);
			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
			new ReusableUnits(driver).navigateUserToServiceSummaryPage(driver, method.getName(), ipData, loginData, pmTests);
			
			
			pmUser.getCreateAndAssignExtensionToUser().click();
			new SelectDropDownValue().selectByVisibleText(pmExtension.getExtensionType(), "IP");
			pmExtension.getNextButton().click();
			
			//Provide extension details.
			new SelectDropDownValue().selectByVisibleText(pmExtension.getSelectExtensionsRange(), extData[1]);
			String version = new GetMxoneVersionNumber(driver).getMxoneVersionNumber(driver);
			System.out.println(version);
			int ver = Integer.parseInt(version);
			System.out.println(ver);
			if(ver >= 72000)
			{
				pmExtension.setSingleExtensionValue(extData[1]);
			}
			else
			{
				new SelectDropDownValue().selectByVisibleText(pmExtension.getSingleExtensionDropDown(), extData[1]);
			}
			new SelectDropDownValue().selectByIndex(pmExtension.getMyCSPNameDropDown(),0);
			new SelectDropDownValue().selectByVisibleText(pmExtension.getServerDropDown(), "1");
			pmExtension.getFirstName().clear();
			pmExtension.getLastName().clear();
			pmExtension.setFirstName(extData[2]);
			pmExtension.setLastName(extData[3]);
			new SelectDropDownValue().selectByVisibleText(pmExtension.getPhoneTypeDropDown(), extData[4]);
			
			pmExtension.getAuthCodeButton().click();
			authCodePage.getAuthCodeAddButton().click();
			authCodePage.setAuthCodeTextBox(extData[5]);
			new SelectDropDownValue().selectByValue(authCodePage.gethashTypeDropDown(), extData[6]);
			authCodePage.setCilCode(extData[5]);
			authCodePage.getAuthPageApplyButton().click();
			
			List<WebElement> eleCount = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//td[contains(text(),'CLEARTEXT')]"));
			Assert.assertTrue(eleCount.size() == 1);
			
			authCodePage.getContinueButton().click();
			authCodePage.getContinueButtonToServiceSummaryPage().click();
			
//			authCodePage.getNextButtonToMicollabConfigPage().click();
			pmExtension.getNextButton().click();
			new SelectDropDownValue().selectByVisibleText(authCodePage.getMicollabRoleDropDown(), extData[7]);
			driver.switchTo().alert().accept();
			pmExtension.getApplyButton().click();
			
			WebDriverWait wait = new WebDriverWait(driver, 100);
//			wait.until(ExpectedConditions.textToBe(By.className("responseMessage"), "  Import operation successful for:"));
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(@class,'responseMessage')]")));
			Assert.assertEquals(driver.findElement(By.xpath("//span[contains(@class,'responseMessage')]")).getText().trim(), "Add operation successful for:");
			
			
//			Assert.assertEquals(pmUser.getResponseMessage().getText().trim(), "Add operation successful for:");
			pmUser.getDoneButton().click();
//			pmUser.getDoneButton().click();
			
			pmUser.getUserSearchTextBox().clear();
			pmUser.setUserSearchTextBox(testData[0]);
			pmUser.getOnViewRangeButton().click();
			
			List<WebElement> eles = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("(//td[contains(text(),'"+testData[0]+"')])[1]//following-sibling::"
					+ "td[contains(text(),'"+extData[1]+" / SN')]"
					+ "//following-sibling::td[contains(text(),'Micollab')]"
					+ "//following-sibling::td[contains(text(),'"+extData[7]+"')]"));
			Assert.assertTrue(eles.size() == 1);
			
			pmMainPage.getLogoutLink().click();
			driver.get(ipData.getData(2, 0));
			Thread.sleep(1000);
			micoLogin.micollab_login(micoCredentials[0], micoCredentials[1]);
			driver.switchTo().frame(serverManagerPage.getInToNavigationFrame());
			serverManagerPage.getUsersAndServicesLink().click();
			driver.switchTo().defaultContent();
			driver.switchTo().frame(serverManagerPage.getMainFrame());
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='basicSearchTerm']")));
			serverManagerPage.getUserSearchTextBox().sendKeys(testData[0]);
			serverManagerPage.getSearchButton().click();
			Thread.sleep(1000);
			List<WebElement> phNums = driver.findElements(By.xpath("(//a[contains(text(),'"+testData[0]+"')]//parent::span//parent::td//following-sibling::td)[2]/span"));
			
			Assert.assertEquals(phNums.get(0).getText().trim(), extData[1]);
			Assert.assertEquals(phNums.get(1).getText().trim(), testData[5]);
			Assert.assertEquals(phNums.get(2).getText().trim(), testData[6]);
			Assert.assertEquals(phNums.get(3).getText().trim(), testData[7]);
			Assert.assertEquals(phNums.get(4).getText().trim(), testData[8]);
			
			driver.findElement(By.xpath("//a[contains(text(),'"+testData[0]+"')]")).click();
		//	wait.until(ExpectedConditions.elementToBeClickable(By.name("firstNameDisplay")));
			Thread.sleep(1000);
		//	System.out.println("First name is :"+serverManagerPage.getFirstNameTextBox().getText().trim());
			//Assert.assertEquals(serverManagerPage.getFirstNameTextBox().getAttribute("value").trim(), testData[0]);
			Assert.assertEquals(serverManagerPage.getLastNameTextBox().getAttribute("value").trim(), testData[0]);
			Assert.assertEquals(serverManagerPage.getEmailAddress().getAttribute("value").trim(), testData[2]);
			
			wait.until(ExpectedConditions.elementToBeClickable(By.id("phones_0"))).click();
			Thread.sleep(2000);
//			serverManagerPage.getPhoneTab().click();
//			Thread.sleep(3000);
//			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@value='Home Phone']"
//					+ "//..//..//following-sibling::tr/td[2]/input[@value='"+testData[5]+"']")));
			
			List<WebElement> homePhone1 = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//input[@value='Home Phone']"
					+ "//..//..//following-sibling::tr/td[2]/input[@value='"+testData[5]+"']"));
			List<WebElement> homePhone2 = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//input[@value='Home Phone 2']"
					+ "//..//..//following-sibling::tr/td[2]/input[@value='"+testData[6]+"']"));
			List<WebElement> mobNumber1 = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//input[@value='Mobile Number']"
					+ "//..//..//following-sibling::tr/td[2]/input[@value='"+testData[7]+"']"));
			List<WebElement> mobNumber2 = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//input[@value='Mobile Number 2']"
					+ "//..//..//following-sibling::tr/td[2]/input[@value='"+testData[8]+"']"));
			List<WebElement> extNumber = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("(//input[@value='"+extData[1]+"'])[1]"));
			Assert.assertTrue(homePhone1.size()==1 && homePhone2.size()==1 && mobNumber1.size()==1 && 
					mobNumber2.size()==1 && extNumber.size()==1);
			
//			JavascriptExecutor js = (JavascriptExecutor)driver;
//			js.executeScript("arguments[0].scrollIntoView(true);", serverManagerPage.getSipPassword());
//			Thread.sleep(5000);
//			Assert.assertEquals(serverManagerPage.getSipPassword().getAttribute("value").trim(), extData[5]);
//			Assert.assertEquals(serverManagerPage.getSipConfirmPassword().getAttribute("value").trim(), extData[5]);
			
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//a[text()='MiCollab Client'])[2]"))).click();
//			serverManagerPage.getMicollabClientTab().click();
//			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//select[@name='ucaService.featureProfileID']/option[@selected='selected']")));
			String assignedRole = driver.findElement(By.xpath("//select[@name='ucaService.featureProfileID']/option[@selected='selected']")).getText().trim();
			String dskPhoneExt = driver.findElement(By.xpath("//select[@id='UCADeskphoneSelect']/option[@selected='selected']")).getText().trim();
			String mailBoxNum = driver.findElement(By.xpath("//select[@id='ucaMailboxSelect']/option[@selected='selected']")).getText().trim();
			
			Assert.assertEquals(assignedRole, extData[7]);
			Assert.assertTrue(dskPhoneExt.contains(extData[1]));
			Assert.assertEquals(mailBoxNum, extData[1]);
			
			serverManagerPage.getCancelButton().click();
			List<WebElement> role = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//img[@src='images/g_check_000.gif']"));
			Assert.assertTrue(role.size()==4);
			
			
			driver.switchTo().defaultContent();
			driver.switchTo().frame(serverManagerPage.getHeaderFrame());
			Thread.sleep(2000);
			serverManagerPage.getLogoutLink().click();
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
			try
			{
				new CleanUP(driver).deleteUser(driver, ipData, credentials, testData[0]);
				list.clear();
				list.add(extData[8]);
				list.add(extData[9]);
				list.add(extData[10]);
				new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
			}
			catch(Exception e)
			{
					new Take_Screenshot().get_Screenshot(driver, method.getName());
					throw e;
			}
		}
	}
	
	
	
	@Test
	public void test_CreateUser_premiumRole_with_ExistingExtension(Method method) throws Exception
	{
		authCodePage = new PM_Auth_Code_Page(driver);
		micoLogin = new Micollab_Login_Page(driver);
		serverManagerPage = new Micollab_Server_Manager_Page(driver);
		pmLoginPage = new PM_Login_Page(driver);
		pmMainPage = new PM_Main_Page(driver);
		pmUser = new PM_User(driver);
		pmUsers = new PM_Users(driver);
		pmExtension = new Extension(driver);
		
		
		list = new ArrayList<String>();
		String[] credentials = loginData.getData("test_pm_valid_login", 1);
		String[] testData = pmTests.getData(method.getName(), 1);
		String[] extData = pmTests.getData(method.getName(), 3);
		String[] micoCredentials = loginData.getData("test_micollab_login", 1);
		wait = new WebDriverWait(driver, 20);
		try 
		{
			pmTests.checkTestStatus(method.getName());
			pmUser = new PM_User(driver);
			pmMainPage = new PM_Main_Page(driver);
			list.add(extData[0]);
			list.add(extData[1]);
			list.add(extData[2]);
			list.add(extData[3]);
			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
			new ReusableUnits(driver).navigateUserToServiceSummaryPage(driver, method.getName(), ipData, loginData, pmTests);
			
			
//			pmUser.getCreateAndAssignExtensionToUser().click();
//			new SelectDropDownValue().selectByVisibleText(pmExtension.getExtensionType(), "IP");
//			pmExtension.getNextButton().click();
			
			//Provide existing extension details.
			pmUser.setExistingExtensionField(extData[4]);
//			pmUser.getNextButton().click();
			
//			authCodePage.getNextButtonToMicollabConfigPage().click();
			pmExtension.getNextButton().click();
			new SelectDropDownValue().selectByVisibleText(authCodePage.getMicollabRoleDropDown(), extData[5]);
			driver.switchTo().alert().accept();
			pmExtension.getApplyButton().click();
			
			WebDriverWait wait = new WebDriverWait(driver, 100);
//			wait.until(ExpectedConditions.textToBe(By.className("responseMessage"), "  Import operation successful for:"));
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(@class,'responseMessage')]")));
			Assert.assertEquals(driver.findElement(By.xpath("//span[contains(@class,'responseMessage')]")).getText().trim(), "Add operation successful for:");
			
			
//			Assert.assertEquals(pmUser.getResponseMessage().getText().trim(), "Add operation successful for:");
			pmUser.getDoneButton().click();
//			pmUser.getDoneButton().click();
			
			pmUser.getUserSearchTextBox().clear();
			pmUser.setUserSearchTextBox(testData[0]);
			pmUser.getOnViewRangeButton().click();
			
			List<WebElement> eles = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("(//td[contains(text(),'"+testData[0]+"')])[1]//following-sibling::"
					+ "td[contains(text(),'"+extData[4]+" / SN')]"
					+ "//following-sibling::td[contains(text(),'Micollab')]"
					+ "//following-sibling::td[contains(text(),'"+extData[5]+"')]"));
			Assert.assertTrue(eles.size() == 1);
			
			pmMainPage.getLogoutLink().click();
			driver.get(ipData.getData(2, 0));
			Thread.sleep(1000);
			micoLogin.micollab_login(micoCredentials[0], micoCredentials[1]);
			driver.switchTo().frame(serverManagerPage.getInToNavigationFrame());
			serverManagerPage.getUsersAndServicesLink().click();
			driver.switchTo().defaultContent();
			driver.switchTo().frame(serverManagerPage.getMainFrame());
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='basicSearchTerm']")));
			serverManagerPage.getUserSearchTextBox().sendKeys(testData[0]);
			serverManagerPage.getSearchButton().click();
			Thread.sleep(1000);
			List<WebElement> phNums = driver.findElements(By.xpath("(//a[contains(text(),'"+testData[0]+"')]//parent::span//parent::td//following-sibling::td)[2]/span"));
			
			Assert.assertEquals(phNums.get(0).getText().trim(), extData[4]);
			Assert.assertEquals(phNums.get(1).getText().trim(), testData[5]);
			Assert.assertEquals(phNums.get(2).getText().trim(), testData[6]);
			Assert.assertEquals(phNums.get(3).getText().trim(), testData[7]);
			Assert.assertEquals(phNums.get(4).getText().trim(), testData[8]);
			
			driver.findElement(By.xpath("//a[contains(text(),'"+testData[0]+"')]")).click();
			//wait.until(ExpectedConditions.elementToBeClickable(By.name("firstNameDisplay")));
			Thread.sleep(1000);
			//System.out.println("First name is :"+serverManagerPage.getFirstNameTextBox().getText().trim());
			//Assert.assertEquals(serverManagerPage.getFirstNameTextBox().getAttribute("value").trim(), testData[0]);
			Assert.assertEquals(serverManagerPage.getLastNameTextBox().getAttribute("value").trim(), testData[0]);
			Assert.assertEquals(serverManagerPage.getEmailAddress().getAttribute("value").trim(), testData[2]);
			
			wait.until(ExpectedConditions.elementToBeClickable(By.id("phones_0"))).click();
			Thread.sleep(2000);
//			serverManagerPage.getPhoneTab().click();
//			Thread.sleep(3000);
//			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@value='Home Phone']"
//					+ "//..//..//following-sibling::tr/td[2]/input[@value='"+testData[5]+"']")));
			
			List<WebElement> homePhone1 = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//input[@value='Home Phone']"
					+ "//..//..//following-sibling::tr/td[2]/input[@value='"+testData[5]+"']"));
			List<WebElement> homePhone2 = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//input[@value='Home Phone 2']"
					+ "//..//..//following-sibling::tr/td[2]/input[@value='"+testData[6]+"']"));
			List<WebElement> mobNumber1 = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//input[@value='Mobile Number']"
					+ "//..//..//following-sibling::tr/td[2]/input[@value='"+testData[7]+"']"));
			List<WebElement> mobNumber2 = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//input[@value='Mobile Number 2']"
					+ "//..//..//following-sibling::tr/td[2]/input[@value='"+testData[8]+"']"));
			List<WebElement> extNumber = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("(//input[@value='"+extData[4]+"'])[1]"));
			Assert.assertTrue(homePhone1.size()==1 && homePhone2.size()==1 && mobNumber1.size()==1 && 
					mobNumber2.size()==1 && extNumber.size()==1);
			
//			JavascriptExecutor js = (JavascriptExecutor)driver;
//			js.executeScript("arguments[0].scrollIntoView(true);", serverManagerPage.getSipPassword());
//			Thread.sleep(5000);
//			Assert.assertEquals(serverManagerPage.getSipPassword().getAttribute("value").trim(), extData[5]);
//			Assert.assertEquals(serverManagerPage.getSipConfirmPassword().getAttribute("value").trim(), extData[5]);
			
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//a[text()='MiCollab Client'])[2]"))).click();
//			serverManagerPage.getMicollabClientTab().click();
//			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//select[@name='ucaService.featureProfileID']/option[@selected='selected']")));
			String assignedRole = driver.findElement(By.xpath("//select[@name='ucaService.featureProfileID']/option[@selected='selected']")).getText().trim();
			String dskPhoneExt = driver.findElement(By.xpath("//select[@id='UCADeskphoneSelect']/option[@selected='selected']")).getText().trim();
			String mailBoxNum = driver.findElement(By.xpath("//select[@id='ucaMailboxSelect']/option[@selected='selected']")).getText().trim();
			
			Assert.assertEquals(assignedRole, extData[5]);
			Assert.assertTrue(dskPhoneExt.contains(extData[4]));
			Assert.assertEquals(mailBoxNum, extData[4]);
			
			serverManagerPage.getCancelButton().click();
			List<WebElement> role = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//img[@src='images/g_check_000.gif']"));
			Assert.assertTrue(role.size()==4);
			
			
			driver.switchTo().defaultContent();
			driver.switchTo().frame(serverManagerPage.getHeaderFrame());
			Thread.sleep(2000);
			serverManagerPage.getLogoutLink().click();
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
			try
			{
				new CleanUP(driver).deleteUser(driver, ipData, credentials, testData[0]);
				list.clear();
				list.add(extData[6]);
				list.add(extData[7]);
				list.add(extData[8]);
				new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
			}
			catch(Exception e)
			{
					new Take_Screenshot().get_Screenshot(driver, method.getName());
					throw e;
			}
		}
	}
	
	
	@Test
	public void test_CreateUser_standardRole_with_ExistingExtension(Method method) throws Exception
	{
		authCodePage = new PM_Auth_Code_Page(driver);
		micoLogin = new Micollab_Login_Page(driver);
		serverManagerPage = new Micollab_Server_Manager_Page(driver);
		pmLoginPage = new PM_Login_Page(driver);
		pmMainPage = new PM_Main_Page(driver);
		pmUser = new PM_User(driver);
		pmUsers = new PM_Users(driver);
		pmExtension = new Extension(driver);
		
		
		list = new ArrayList<String>();
		String[] credentials = loginData.getData("test_pm_valid_login", 1);
		String[] testData = pmTests.getData(method.getName(), 1);
		String[] extData = pmTests.getData(method.getName(), 3);
		String[] micoCredentials = loginData.getData("test_micollab_login", 1);
		wait = new WebDriverWait(driver, 20);
		try 
		{
			pmTests.checkTestStatus(method.getName());
			pmUser = new PM_User(driver);
			pmMainPage = new PM_Main_Page(driver);
			list.add(extData[0]);
			list.add(extData[1]);
			list.add(extData[2]);
			list.add(extData[3]);
			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
			new ReusableUnits(driver).navigateUserToServiceSummaryPage(driver, method.getName(), ipData, loginData, pmTests);
			
			
//			pmUser.getCreateAndAssignExtensionToUser().click();
//			new SelectDropDownValue().selectByVisibleText(pmExtension.getExtensionType(), "IP");
//			pmExtension.getNextButton().click();
			
			//Provide existing extension details.
			pmUser.setExistingExtensionField(extData[4]);
//			pmUser.getNextButton().click();
			
//			authCodePage.getNextButtonToMicollabConfigPage().click();
			pmExtension.getNextButton().click();
			new SelectDropDownValue().selectByVisibleText(authCodePage.getMicollabRoleDropDown(), extData[5]);
			driver.switchTo().alert().accept();
			pmExtension.getApplyButton().click();
			
			WebDriverWait wait = new WebDriverWait(driver, 100);
//			wait.until(ExpectedConditions.textToBe(By.className("responseMessage"), "  Import operation successful for:"));
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(@class,'responseMessage')]")));
			Assert.assertEquals(driver.findElement(By.xpath("//span[contains(@class,'responseMessage')]")).getText().trim(), "Add operation successful for:");
			
			
//			Assert.assertEquals(pmUser.getResponseMessage().getText().trim(), "Add operation successful for:");
			pmUser.getDoneButton().click();
//			pmUser.getDoneButton().click();
			
			pmUser.getUserSearchTextBox().clear();
			pmUser.setUserSearchTextBox(testData[0]);
			pmUser.getOnViewRangeButton().click();
			
			List<WebElement> eles = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("(//td[contains(text(),'"+testData[0]+"')])[1]//following-sibling::"
					+ "td[contains(text(),'"+extData[4]+" / SN')]"
					+ "//following-sibling::td[contains(text(),'Micollab')]"
					+ "//following-sibling::td[contains(text(),'"+extData[5]+"')]"));
			Assert.assertTrue(eles.size() == 1);
			
			pmMainPage.getLogoutLink().click();
			driver.get(ipData.getData(2, 0));
			Thread.sleep(1000);
			micoLogin.micollab_login(micoCredentials[0], micoCredentials[1]);
			driver.switchTo().frame(serverManagerPage.getInToNavigationFrame());
			serverManagerPage.getUsersAndServicesLink().click();
			driver.switchTo().defaultContent();
			driver.switchTo().frame(serverManagerPage.getMainFrame());
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='basicSearchTerm']")));
			serverManagerPage.getUserSearchTextBox().sendKeys(testData[0]);
			serverManagerPage.getSearchButton().click();
			Thread.sleep(1000);
			List<WebElement> phNums = driver.findElements(By.xpath("(//a[contains(text(),'"+testData[0]+"')]//parent::span//parent::td//following-sibling::td)[2]/span"));
			
			Assert.assertEquals(phNums.get(0).getText().trim(), extData[4]);
			Assert.assertEquals(phNums.get(1).getText().trim(), testData[5]);
			Assert.assertEquals(phNums.get(2).getText().trim(), testData[6]);
			Assert.assertEquals(phNums.get(3).getText().trim(), testData[7]);
			Assert.assertEquals(phNums.get(4).getText().trim(), testData[8]);
			
			driver.findElement(By.xpath("//a[contains(text(),'"+testData[0]+"')]")).click();
			//wait.until(ExpectedConditions.elementToBeClickable(By.name("firstNameDisplay")));
			Thread.sleep(1000);
		//	System.out.println("First name is :"+serverManagerPage.getFirstNameTextBox().getText().trim());
			//Assert.assertEquals(serverManagerPage.getFirstNameTextBox().getAttribute("value").trim(), testData[0]);
			Assert.assertEquals(serverManagerPage.getLastNameTextBox().getAttribute("value").trim(), testData[0]);
			Assert.assertEquals(serverManagerPage.getEmailAddress().getAttribute("value").trim(), testData[2]);
			
			wait.until(ExpectedConditions.elementToBeClickable(By.id("phones_0"))).click();
			Thread.sleep(2000);
//			serverManagerPage.getPhoneTab().click();
//			Thread.sleep(3000);
//			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@value='Home Phone']"
//					+ "//..//..//following-sibling::tr/td[2]/input[@value='"+testData[5]+"']")));
			
			List<WebElement> homePhone1 = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//input[@value='Home Phone']"
					+ "//..//..//following-sibling::tr/td[2]/input[@value='"+testData[5]+"']"));
			List<WebElement> homePhone2 = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//input[@value='Home Phone 2']"
					+ "//..//..//following-sibling::tr/td[2]/input[@value='"+testData[6]+"']"));
			List<WebElement> mobNumber1 = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//input[@value='Mobile Number']"
					+ "//..//..//following-sibling::tr/td[2]/input[@value='"+testData[7]+"']"));
			List<WebElement> mobNumber2 = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//input[@value='Mobile Number 2']"
					+ "//..//..//following-sibling::tr/td[2]/input[@value='"+testData[8]+"']"));
			List<WebElement> extNumber = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("(//input[@value='"+extData[4]+"'])[1]"));
			Assert.assertTrue(homePhone1.size()==1 && homePhone2.size()==1 && mobNumber1.size()==1 && 
					mobNumber2.size()==1 && extNumber.size()==1);
			
//			JavascriptExecutor js = (JavascriptExecutor)driver;
//			js.executeScript("arguments[0].scrollIntoView(true);", serverManagerPage.getSipPassword());
//			Thread.sleep(5000);
//			Assert.assertEquals(serverManagerPage.getSipPassword().getAttribute("value").trim(), extData[5]);
//			Assert.assertEquals(serverManagerPage.getSipConfirmPassword().getAttribute("value").trim(), extData[5]);
			
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//a[text()='MiCollab Client'])[2]"))).click();
//			serverManagerPage.getMicollabClientTab().click();
//			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//select[@name='ucaService.featureProfileID']/option[@selected='selected']")));
			String assignedRole = driver.findElement(By.xpath("//select[@name='ucaService.featureProfileID']/option[@selected='selected']")).getText().trim();
			String dskPhoneExt = driver.findElement(By.xpath("//select[@id='UCADeskphoneSelect']/option[@selected='selected']")).getText().trim();
			String mailBoxNum = driver.findElement(By.xpath("//select[@id='ucaMailboxSelect']/option[@selected='selected']")).getText().trim();
			
			Assert.assertEquals(assignedRole, extData[5]);
			Assert.assertTrue(dskPhoneExt.contains(extData[4]));
			Assert.assertEquals(mailBoxNum, extData[4]);
			
			serverManagerPage.getCancelButton().click();
			List<WebElement> role = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//img[@src='images/g_check_000.gif']"));
			Assert.assertTrue(role.size()==4);
			
			
			driver.switchTo().defaultContent();
			driver.switchTo().frame(serverManagerPage.getHeaderFrame());
			Thread.sleep(2000);
			serverManagerPage.getLogoutLink().click();
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
			try
			{
				new CleanUP(driver).deleteUser(driver, ipData, credentials, testData[0]);
				list.clear();
				list.add(extData[6]);
				list.add(extData[7]);
				list.add(extData[8]);
				new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
			}
			catch(Exception e)
			{
					new Take_Screenshot().get_Screenshot(driver, method.getName());
					throw e;
			}
		}
	}
	
	
	@Test
	public void test_CreateUser_entryRole_with_ExistingExtension(Method method) throws Exception
	{
		authCodePage = new PM_Auth_Code_Page(driver);
		micoLogin = new Micollab_Login_Page(driver);
		serverManagerPage = new Micollab_Server_Manager_Page(driver);
		pmLoginPage = new PM_Login_Page(driver);
		pmMainPage = new PM_Main_Page(driver);
		pmUser = new PM_User(driver);
		pmUsers = new PM_Users(driver);
		pmExtension = new Extension(driver);
		
		
		list = new ArrayList<String>();
		String[] credentials = loginData.getData("test_pm_valid_login", 1);
		String[] testData = pmTests.getData(method.getName(), 1);
		String[] extData = pmTests.getData(method.getName(), 3);
		String[] micoCredentials = loginData.getData("test_micollab_login", 1);
		wait = new WebDriverWait(driver, 20);
		try 
		{
			pmTests.checkTestStatus(method.getName());
			pmUser = new PM_User(driver);
			pmMainPage = new PM_Main_Page(driver);
			list.add(extData[0]);
			list.add(extData[1]);
			list.add(extData[2]);
			list.add(extData[3]);
			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
			new ReusableUnits(driver).navigateUserToServiceSummaryPage(driver, method.getName(), ipData, loginData, pmTests);
			
			
//			pmUser.getCreateAndAssignExtensionToUser().click();
//			new SelectDropDownValue().selectByVisibleText(pmExtension.getExtensionType(), "IP");
//			pmExtension.getNextButton().click();
			
			//Provide existing extension details.
			pmUser.setExistingExtensionField(extData[4]);
//			pmUser.getNextButton().click();
			
//			authCodePage.getNextButtonToMicollabConfigPage().click();
			pmExtension.getNextButton().click();
			new SelectDropDownValue().selectByVisibleText(authCodePage.getMicollabRoleDropDown(), extData[5]);
			driver.switchTo().alert().accept();
			pmExtension.getApplyButton().click();
			
			WebDriverWait wait = new WebDriverWait(driver, 100);
//			wait.until(ExpectedConditions.textToBe(By.className("responseMessage"), "  Import operation successful for:"));
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(@class,'responseMessage')]")));
			Assert.assertEquals(driver.findElement(By.xpath("//span[contains(@class,'responseMessage')]")).getText().trim(), "Add operation successful for:");
			
			
//			Assert.assertEquals(pmUser.getResponseMessage().getText().trim(), "Add operation successful for:");
			pmUser.getDoneButton().click();
//			pmUser.getDoneButton().click();
			
			pmUser.getUserSearchTextBox().clear();
			pmUser.setUserSearchTextBox(testData[0]);
			pmUser.getOnViewRangeButton().click();
			
			List<WebElement> eles = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("(//td[contains(text(),'"+testData[0]+"')])[1]//following-sibling::"
					+ "td[contains(text(),'"+extData[4]+" / SN')]"
					+ "//following-sibling::td[contains(text(),'Micollab')]"
					+ "//following-sibling::td[contains(text(),'"+extData[5]+"')]"));
			Assert.assertTrue(eles.size() == 1);
			
			pmMainPage.getLogoutLink().click();
			driver.get(ipData.getData(2, 0));
			Thread.sleep(1000);
			micoLogin.micollab_login(micoCredentials[0], micoCredentials[1]);
			driver.switchTo().frame(serverManagerPage.getInToNavigationFrame());
			serverManagerPage.getUsersAndServicesLink().click();
			driver.switchTo().defaultContent();
			driver.switchTo().frame(serverManagerPage.getMainFrame());
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='basicSearchTerm']")));
			serverManagerPage.getUserSearchTextBox().sendKeys(testData[0]);
			serverManagerPage.getSearchButton().click();
			Thread.sleep(1000);
			List<WebElement> phNums = driver.findElements(By.xpath("(//a[contains(text(),'"+testData[0]+"')]//parent::span//parent::td//following-sibling::td)[2]/span"));
			
			Assert.assertEquals(phNums.get(0).getText().trim(), extData[4]);
			Assert.assertEquals(phNums.get(1).getText().trim(), testData[5]);
			Assert.assertEquals(phNums.get(2).getText().trim(), testData[6]);
			Assert.assertEquals(phNums.get(3).getText().trim(), testData[7]);
			Assert.assertEquals(phNums.get(4).getText().trim(), testData[8]);
			
			driver.findElement(By.xpath("//a[contains(text(),'"+testData[0]+"')]")).click();
			//wait.until(ExpectedConditions.elementToBeClickable(By.name("firstNameDisplay")));
		Thread.sleep(1000);
//			System.out.println("First name is :"+serverManagerPage.getFirstNameTextBox().getText().trim());
	//		Assert.assertEquals(serverManagerPage.getFirstNameTextBox().getAttribute("value").trim(), testData[0]);
			Assert.assertEquals(serverManagerPage.getLastNameTextBox().getAttribute("value").trim(), testData[0]);
			Assert.assertEquals(serverManagerPage.getEmailAddress().getAttribute("value").trim(), testData[2]);
			
			wait.until(ExpectedConditions.elementToBeClickable(By.id("phones_0"))).click();
			Thread.sleep(2000);
//			serverManagerPage.getPhoneTab().click();
//			Thread.sleep(3000);
//			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@value='Home Phone']"
//					+ "//..//..//following-sibling::tr/td[2]/input[@value='"+testData[5]+"']")));
			
			List<WebElement> homePhone1 = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//input[@value='Home Phone']"
					+ "//..//..//following-sibling::tr/td[2]/input[@value='"+testData[5]+"']"));
			List<WebElement> homePhone2 = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//input[@value='Home Phone 2']"
					+ "//..//..//following-sibling::tr/td[2]/input[@value='"+testData[6]+"']"));
			List<WebElement> mobNumber1 = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//input[@value='Mobile Number']"
					+ "//..//..//following-sibling::tr/td[2]/input[@value='"+testData[7]+"']"));
			List<WebElement> mobNumber2 = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//input[@value='Mobile Number 2']"
					+ "//..//..//following-sibling::tr/td[2]/input[@value='"+testData[8]+"']"));
			List<WebElement> extNumber = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("(//input[@value='"+extData[4]+"'])[1]"));
			Assert.assertTrue(homePhone1.size()==1 && homePhone2.size()==1 && mobNumber1.size()==1 && 
					mobNumber2.size()==1 && extNumber.size()==1);
			
//			JavascriptExecutor js = (JavascriptExecutor)driver;
//			js.executeScript("arguments[0].scrollIntoView(true);", serverManagerPage.getSipPassword());
//			Thread.sleep(5000);
//			Assert.assertEquals(serverManagerPage.getSipPassword().getAttribute("value").trim(), extData[5]);
//			Assert.assertEquals(serverManagerPage.getSipConfirmPassword().getAttribute("value").trim(), extData[5]);
			
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//a[text()='MiCollab Client'])[2]"))).click();
//			serverManagerPage.getMicollabClientTab().click();
//			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//select[@name='ucaService.featureProfileID']/option[@selected='selected']")));
			String assignedRole = driver.findElement(By.xpath("//select[@name='ucaService.featureProfileID']/option[@selected='selected']")).getText().trim();
			String dskPhoneExt = driver.findElement(By.xpath("//select[@id='UCADeskphoneSelect']/option[@selected='selected']")).getText().trim();
			String mailBoxNum = driver.findElement(By.xpath("//select[@id='ucaMailboxSelect']/option[@selected='selected']")).getText().trim();
			
			Assert.assertEquals(assignedRole, extData[5]);
			Assert.assertTrue(dskPhoneExt.contains(extData[4]));
			Assert.assertEquals(mailBoxNum, extData[4]);
			
			serverManagerPage.getCancelButton().click();
			List<WebElement> role = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//img[@src='images/g_check_000.gif']"));
			Assert.assertTrue(role.size()==2);
			
			
			driver.switchTo().defaultContent();
			driver.switchTo().frame(serverManagerPage.getHeaderFrame());
			Thread.sleep(2000);
			serverManagerPage.getLogoutLink().click();
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
			try
			{
				new CleanUP(driver).deleteUser(driver, ipData, credentials, testData[0]);
				list.clear();
				list.add(extData[6]);
				list.add(extData[7]);
				list.add(extData[8]);
				new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
			}
			catch(Exception e)
			{
					new Take_Screenshot().get_Screenshot(driver, method.getName());
					throw e;
			}
		}
	}
	
	@Test
	public void test_CreateUser_basicRole_with_ExistingExtension(Method method) throws Exception
	{
		authCodePage = new PM_Auth_Code_Page(driver);
		micoLogin = new Micollab_Login_Page(driver);
		serverManagerPage = new Micollab_Server_Manager_Page(driver);
		pmLoginPage = new PM_Login_Page(driver);
		pmMainPage = new PM_Main_Page(driver);
		pmUser = new PM_User(driver);
		pmUsers = new PM_Users(driver);
		pmExtension = new Extension(driver);
		
		
		list = new ArrayList<String>();
		String[] credentials = loginData.getData("test_pm_valid_login", 1);
		String[] testData = pmTests.getData(method.getName(), 1);
		String[] extData = pmTests.getData(method.getName(), 3);
		String[] micoCredentials = loginData.getData("test_micollab_login", 1);
		wait = new WebDriverWait(driver, 20);
		try 
		{
			pmTests.checkTestStatus(method.getName());
			pmUser = new PM_User(driver);
			pmMainPage = new PM_Main_Page(driver);
			list.add(extData[0]);
			list.add(extData[1]);
			list.add(extData[2]);
			list.add(extData[3]);
			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
			new ReusableUnits(driver).navigateUserToServiceSummaryPage(driver, method.getName(), ipData, loginData, pmTests);
			
			
//			pmUser.getCreateAndAssignExtensionToUser().click();
//			new SelectDropDownValue().selectByVisibleText(pmExtension.getExtensionType(), "IP");
//			pmExtension.getNextButton().click();
			
			//Provide existing extension details.
			pmUser.setExistingExtensionField(extData[4]);
//			pmUser.getNextButton().click();
			
//			authCodePage.getNextButtonToMicollabConfigPage().click();
			pmExtension.getNextButton().click();
			new SelectDropDownValue().selectByVisibleText(authCodePage.getMicollabRoleDropDown(), extData[5]);
			driver.switchTo().alert().accept();
			pmExtension.getApplyButton().click();
			
			WebDriverWait wait = new WebDriverWait(driver, 100);
//			wait.until(ExpectedConditions.textToBe(By.className("responseMessage"), "  Import operation successful for:"));
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(@class,'responseMessage')]")));
			Assert.assertEquals(driver.findElement(By.xpath("//span[contains(@class,'responseMessage')]")).getText().trim(), "Add operation successful for:");
			
			
//			Assert.assertEquals(pmUser.getResponseMessage().getText().trim(), "Add operation successful for:");
			pmUser.getDoneButton().click();
//			pmUser.getDoneButton().click();
			
			pmUser.getUserSearchTextBox().clear();
			pmUser.setUserSearchTextBox(testData[0]);
			pmUser.getOnViewRangeButton().click();
			
			List<WebElement> eles = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("(//td[contains(text(),'"+testData[0]+"')])[1]//following-sibling::"
					+ "td[contains(text(),'"+extData[4]+" / SN')]"
					+ "//following-sibling::td[contains(text(),'Micollab')]"
					+ "//following-sibling::td[contains(text(),'"+extData[5]+"')]"));
			Assert.assertTrue(eles.size() == 1);
			
			pmMainPage.getLogoutLink().click();
			driver.get(ipData.getData(2, 0));
			Thread.sleep(1000);
			micoLogin.micollab_login(micoCredentials[0], micoCredentials[1]);
			driver.switchTo().frame(serverManagerPage.getInToNavigationFrame());
			serverManagerPage.getUsersAndServicesLink().click();
			driver.switchTo().defaultContent();
			driver.switchTo().frame(serverManagerPage.getMainFrame());
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='basicSearchTerm']")));
			serverManagerPage.getUserSearchTextBox().sendKeys(testData[0]);
			serverManagerPage.getSearchButton().click();
			Thread.sleep(1000);
			List<WebElement> phNums = driver.findElements(By.xpath("(//a[contains(text(),'"+testData[0]+"')]//parent::span//parent::td//following-sibling::td)[2]/span"));
			
			Assert.assertEquals(phNums.get(0).getText().trim(), extData[4]);
			Assert.assertEquals(phNums.get(1).getText().trim(), testData[5]);
			Assert.assertEquals(phNums.get(2).getText().trim(), testData[6]);
			Assert.assertEquals(phNums.get(3).getText().trim(), testData[7]);
			Assert.assertEquals(phNums.get(4).getText().trim(), testData[8]);
			
			driver.findElement(By.xpath("//a[contains(text(),'"+testData[0]+"')]")).click();
			//wait.until(ExpectedConditions.elementToBeClickable(By.name("firstNameDisplay")));
			Thread.sleep(1000);
//			System.out.println("First name is :"+serverManagerPage.getFirstNameTextBox().getText().trim());
	//		Assert.assertEquals(serverManagerPage.getFirstNameTextBox().getAttribute("value").trim(), testData[0]);
			Assert.assertEquals(serverManagerPage.getLastNameTextBox().getAttribute("value").trim(), testData[0]);
			Assert.assertEquals(serverManagerPage.getEmailAddress().getAttribute("value").trim(), testData[2]);
			
			wait.until(ExpectedConditions.elementToBeClickable(By.id("phones_0"))).click();
			Thread.sleep(2000);
//			serverManagerPage.getPhoneTab().click();
//			Thread.sleep(3000);
//			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@value='Home Phone']"
//					+ "//..//..//following-sibling::tr/td[2]/input[@value='"+testData[5]+"']")));
			
			List<WebElement> homePhone1 = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//input[@value='Home Phone']"
					+ "//..//..//following-sibling::tr/td[2]/input[@value='"+testData[5]+"']"));
			List<WebElement> homePhone2 = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//input[@value='Home Phone 2']"
					+ "//..//..//following-sibling::tr/td[2]/input[@value='"+testData[6]+"']"));
			List<WebElement> mobNumber1 = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//input[@value='Mobile Number']"
					+ "//..//..//following-sibling::tr/td[2]/input[@value='"+testData[7]+"']"));
			List<WebElement> mobNumber2 = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//input[@value='Mobile Number 2']"
					+ "//..//..//following-sibling::tr/td[2]/input[@value='"+testData[8]+"']"));
			List<WebElement> extNumber = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("(//input[@value='"+extData[4]+"'])[1]"));
			Assert.assertTrue(homePhone1.size()==1 && homePhone2.size()==1 && mobNumber1.size()==1 && 
					mobNumber2.size()==1 && extNumber.size()==1);
			
//			JavascriptExecutor js = (JavascriptExecutor)driver;
//			js.executeScript("arguments[0].scrollIntoView(true);", serverManagerPage.getSipPassword());
//			Thread.sleep(5000);
//			Assert.assertEquals(serverManagerPage.getSipPassword().getAttribute("value").trim(), extData[5]);
//			Assert.assertEquals(serverManagerPage.getSipConfirmPassword().getAttribute("value").trim(), extData[5]);
			
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//a[text()='MiCollab Client'])[2]"))).click();
//			serverManagerPage.getMicollabClientTab().click();
//			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//select[@name='ucaService.featureProfileID']/option[@selected='selected']")));
			String assignedRole = driver.findElement(By.xpath("//select[@name='ucaService.featureProfileID']/option[@selected='selected']")).getText().trim();
//			String dskPhoneExt = driver.findElement(By.xpath("//select[@id='UCADeskphoneSelect']/option[@selected='selected']")).getText().trim();
//			String mailBoxNum = driver.findElement(By.xpath("//select[@id='ucaMailboxSelect']/option[@selected='selected']")).getText().trim();
			
			Assert.assertEquals(assignedRole, "Default Feature Profile");
//			Assert.assertTrue(dskPhoneExt.contains(extData[4]));
//			Assert.assertEquals(mailBoxNum, extData[4]);
			
			serverManagerPage.getCancelButton().click();
			List<WebElement> role = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//img[@src='images/g_check_000.gif']"));
			Assert.assertTrue(role.size()==1);
			
			
			driver.switchTo().defaultContent();
			driver.switchTo().frame(serverManagerPage.getHeaderFrame());
			Thread.sleep(2000);
			serverManagerPage.getLogoutLink().click();
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
			try
			{
				new CleanUP(driver).deleteUser(driver, ipData, credentials, testData[0]);
				list.clear();
				list.add(extData[6]);
				list.add(extData[7]);
				list.add(extData[8]);
				new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
			}
			catch(Exception e)
			{
					new Take_Screenshot().get_Screenshot(driver, method.getName());
					throw e;
			}
		}
	}
	
	@Test
	public void test_CreateUser_basicRole_with_Template(Method method) throws Exception
	{
		authCodePage = new PM_Auth_Code_Page(driver);
		micoLogin = new Micollab_Login_Page(driver);
		serverManagerPage = new Micollab_Server_Manager_Page(driver);
		pmLoginPage = new PM_Login_Page(driver);
		pmMainPage = new PM_Main_Page(driver);
		pmUser = new PM_User(driver);
		pmUsers = new PM_Users(driver);
		pmExtension = new Extension(driver);
		
		
		list = new ArrayList<String>();
		String[] credentials = loginData.getData("test_pm_valid_login", 1);
		String[] testData = pmTests.getData(method.getName(), 1);
		String[] extData = pmTests.getData(method.getName(), 3);
		String[] tempData = pmTests.getData(method.getName(), 4);
		String[] micoCredentials = loginData.getData("test_micollab_login", 1);
		wait = new WebDriverWait(driver, 20);
		try 
		{
			pmTests.checkTestStatus(method.getName());
			pmUser = new PM_User(driver);
			pmMainPage = new PM_Main_Page(driver);
			list.add(extData[0]);
			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
			new ReusableUnits(driver).createIPTemplate(driver, method.getName(), ipData, loginData, tempData[0]);
			new ReusableUnits(driver).navigateUserToServiceSummaryPage(driver, method.getName(), ipData, loginData, pmTests);
			
			new SelectDropDownValue().selectByValue(pmExtension.getExtensionHomePageTemplateDropDown(),
					"CUST."+tempData[0]+"_SN_IPExtension");
			
			pmUser.getCreateAndAssignExtensionToUser().click();
//			new SelectDropDownValue().selectByVisibleText(pmExtension.getExtensionType(), "IP");
//			pmExtension.getNextButton().click();
			
			//Provide extension details.
			new SelectDropDownValue().selectByVisibleText(pmExtension.getSelectExtensionsRange(), extData[1]);
			String version = new GetMxoneVersionNumber(driver).getMxoneVersionNumber(driver);
			System.out.println(version);
			int ver = Integer.parseInt(version);
			System.out.println(ver);
			if(ver >= 72000)
			{
				pmExtension.setSingleExtensionValue(extData[1]);
			}
			else
			{
				new SelectDropDownValue().selectByVisibleText(pmExtension.getSingleExtensionDropDown(), extData[1]);
			}
			new SelectDropDownValue().selectByIndex(pmExtension.getMyCSPNameDropDown(),0);
			new SelectDropDownValue().selectByVisibleText(pmExtension.getServerDropDown(), "1");
			pmExtension.getFirstName().clear();
			pmExtension.getLastName().clear();
			pmExtension.setFirstName(extData[2]);
			pmExtension.setLastName(extData[3]);
			new SelectDropDownValue().selectByVisibleText(pmExtension.getPhoneTypeDropDown(), extData[4]);
			
			pmExtension.getAuthCodeButton().click();
			authCodePage.getAuthCodeAddButton().click();
			authCodePage.setAuthCodeTextBox(extData[5]);
			new SelectDropDownValue().selectByValue(authCodePage.gethashTypeDropDown(), extData[6]);
			authCodePage.setCilCode(extData[5]);
			authCodePage.getAuthPageApplyButton().click();
			
			List<WebElement> eleCount = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//td[contains(text(),'CLEARTEXT')]"));
			Assert.assertTrue(eleCount.size() == 1);
			
			authCodePage.getContinueButton().click();
			authCodePage.getContinueButtonToServiceSummaryPage().click();
			
//			authCodePage.getNextButtonToMicollabConfigPage().click();
			pmExtension.getNextButton().click();
			new SelectDropDownValue().selectByVisibleText(authCodePage.getMicollabRoleDropDown(), extData[7]);
			driver.switchTo().alert().accept();
			pmExtension.getApplyButton().click();
			
			WebDriverWait wait = new WebDriverWait(driver, 100);
//			wait.until(ExpectedConditions.textToBe(By.className("responseMessage"), "  Import operation successful for:"));
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(@class,'responseMessage')]")));
			Assert.assertEquals(driver.findElement(By.xpath("//span[contains(@class,'responseMessage')]")).getText().trim(), "Add operation successful for:");
			
			
//			Assert.assertEquals(pmUser.getResponseMessage().getText().trim(), "Add operation successful for:");
			pmUser.getDoneButton().click();
//			pmUser.getDoneButton().click();
			
			pmUser.getUserSearchTextBox().clear();
			pmUser.setUserSearchTextBox(testData[0]);
			pmUser.getOnViewRangeButton().click();
			
			List<WebElement> eles = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("(//td[contains(text(),'"+testData[0]+"')])[1]//following-sibling::"
					+ "td[contains(text(),'"+extData[1]+" / SN')]"
					+ "//following-sibling::td[contains(text(),'Micollab')]"
					+ "//following-sibling::td[contains(text(),'"+extData[7]+"')]"));
			Assert.assertTrue(eles.size() == 1);
			
			pmMainPage.getLogoutLink().click();
			driver.get(ipData.getData(2, 0));
			micoLogin.micollab_login(micoCredentials[0], micoCredentials[1]);
			driver.switchTo().frame(serverManagerPage.getInToNavigationFrame());
			serverManagerPage.getUsersAndServicesLink().click();
			driver.switchTo().defaultContent();
			driver.switchTo().frame(serverManagerPage.getMainFrame());
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='basicSearchTerm']")));
			serverManagerPage.getUserSearchTextBox().sendKeys(testData[0]);
			serverManagerPage.getSearchButton().click();
			Thread.sleep(1000);
			List<WebElement> phNums = driver.findElements(By.xpath("(//a[contains(text(),'"+testData[0]+"')]//parent::span//parent::td//following-sibling::td)[2]/span"));
			
			Assert.assertEquals(phNums.get(0).getText().trim(), extData[1]);
			Assert.assertEquals(phNums.get(1).getText().trim(), testData[5]);
			Assert.assertEquals(phNums.get(2).getText().trim(), testData[6]);
			Assert.assertEquals(phNums.get(3).getText().trim(), testData[7]);
			Assert.assertEquals(phNums.get(4).getText().trim(), testData[8]);
			
			driver.findElement(By.xpath("//a[contains(text(),'"+testData[0]+"')]")).click();
			Thread.sleep(1000);
			//System.out.println("First name is :"+serverManagerPage.getFirstNameTextBox().getText().trim());
			//Assert.assertEquals(serverManagerPage.getFirstNameTextBox().getAttribute("value").trim(), testData[0]);
			Assert.assertEquals(serverManagerPage.getLastNameTextBox().getAttribute("value").trim(), testData[0]);
			Assert.assertEquals(serverManagerPage.getEmailAddress().getAttribute("value").trim(), testData[2]);
			serverManagerPage.getPhoneTab().click();
			
			List<WebElement> homePhone1 = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//input[@value='Home Phone']"
					+ "//..//..//following-sibling::tr/td[2]/input[@value='"+testData[5]+"']"));
			List<WebElement> homePhone2 = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//input[@value='Home Phone 2']"
					+ "//..//..//following-sibling::tr/td[2]/input[@value='"+testData[6]+"']"));
			List<WebElement> mobNumber1 = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//input[@value='Mobile Number']"
					+ "//..//..//following-sibling::tr/td[2]/input[@value='"+testData[7]+"']"));
			List<WebElement> mobNumber2 = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//input[@value='Mobile Number 2']"
					+ "//..//..//following-sibling::tr/td[2]/input[@value='"+testData[8]+"']"));
			List<WebElement> extNumber = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//input[@value='"+extData[1]+"']"));
			Assert.assertTrue(homePhone1.size()==1 && homePhone2.size()==1 && mobNumber1.size()==1 && 
					mobNumber2.size()==1 && extNumber.size()==1);
//			Thread.sleep(1000);
//			JavascriptExecutor js = (JavascriptExecutor)driver;
//			js.executeScript("arguments[0].scrollIntoView(true);", serverManagerPage.getSipPassword());
//			Thread.sleep(5000);
//			Assert.assertEquals(serverManagerPage.getSipPassword().getAttribute("value").trim(), extData[5]);
//			Assert.assertEquals(serverManagerPage.getSipConfirmPassword().getAttribute("value").trim(), extData[5]);
			
//			serverManagerPage.getMicollabClientTab().click();
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//a[text()='MiCollab Client'])[2]"))).click();
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//select[@name='ucaService.featureProfileID']/option[@selected='selected']")));
			String assignedRole = driver.findElement(By.xpath("//select[@name='ucaService.featureProfileID']/option[@selected='selected']")).getText().trim();
//			String dskPhoneExt = driver.findElement(By.xpath("//select[@id='UCADeskphoneSelect']/option[@selected='selected']")).getText().trim();
//			String mailBoxNum = driver.findElement(By.xpath("//select[@id='ucaMailboxSelect']/option[@selected='selected']")).getText().trim();
			
			Assert.assertEquals(assignedRole, "Default Feature Profile");
//			Assert.assertTrue(dskPhoneExt.contains(extData[1]));
//			Assert.assertEquals(mailBoxNum, extData[1]);
			
			
			serverManagerPage.getCancelButton().click();
			List<WebElement> role = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//img[@src='images/g_check_000.gif']"));
			Assert.assertTrue(role.size()==1);
			
			driver.switchTo().defaultContent();
			driver.switchTo().frame(serverManagerPage.getHeaderFrame());
			Thread.sleep(2000);
			serverManagerPage.getLogoutLink().click();
			
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
			try
			{
				new CleanUP(driver).deleteUser(driver, ipData, credentials, testData[0]);
				new CleanUP(driver).deleteTemplate(driver, method.getName(), loginData, tempData[0], ipData);
				list.clear();
				list.add(extData[8]);
				list.add(extData[9]);
				list.add(extData[10]);
				new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
			}
			catch(Exception e)
			{
					new Take_Screenshot().get_Screenshot(driver, method.getName());
					throw e;
			}
		}
	}
	
	@Test
	public void test_CreateUser_entryRole_with_Template(Method method) throws Exception
	{
		authCodePage = new PM_Auth_Code_Page(driver);
		micoLogin = new Micollab_Login_Page(driver);
		serverManagerPage = new Micollab_Server_Manager_Page(driver);
		pmLoginPage = new PM_Login_Page(driver);
		pmMainPage = new PM_Main_Page(driver);
		pmUser = new PM_User(driver);
		pmUsers = new PM_Users(driver);
		pmExtension = new Extension(driver);
		
		
		list = new ArrayList<String>();
		String[] credentials = loginData.getData("test_pm_valid_login", 1);
		String[] testData = pmTests.getData(method.getName(), 1);
		String[] extData = pmTests.getData(method.getName(), 3);
		String[] tempData = pmTests.getData(method.getName(), 4);
		String[] micoCredentials = loginData.getData("test_micollab_login", 1);
		wait = new WebDriverWait(driver, 20);
		try 
		{
			pmTests.checkTestStatus(method.getName());
			pmUser = new PM_User(driver);
			pmMainPage = new PM_Main_Page(driver);
			list.add(extData[0]);
			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
			new ReusableUnits(driver).createIPTemplate(driver, method.getName(), ipData, loginData, tempData[0]);
			new ReusableUnits(driver).navigateUserToServiceSummaryPage(driver, method.getName(), ipData, loginData, pmTests);
			
			new SelectDropDownValue().selectByValue(pmExtension.getExtensionHomePageTemplateDropDown(),
					"CUST."+tempData[0]+"_SN_IPExtension");
			
			pmUser.getCreateAndAssignExtensionToUser().click();
//			new SelectDropDownValue().selectByVisibleText(pmExtension.getExtensionType(), "IP");
//			pmExtension.getNextButton().click();
			
			//Provide extension details.
			new SelectDropDownValue().selectByVisibleText(pmExtension.getSelectExtensionsRange(), extData[1]);
			String version = new GetMxoneVersionNumber(driver).getMxoneVersionNumber(driver);
			System.out.println(version);
			int ver = Integer.parseInt(version);
			System.out.println(ver);
			if(ver >= 72000)
			{
				pmExtension.setSingleExtensionValue(extData[1]);
			}
			else
			{
				new SelectDropDownValue().selectByVisibleText(pmExtension.getSingleExtensionDropDown(), extData[1]);
			}
			new SelectDropDownValue().selectByIndex(pmExtension.getMyCSPNameDropDown(),0);
			new SelectDropDownValue().selectByVisibleText(pmExtension.getServerDropDown(), "1");
			pmExtension.getFirstName().clear();
			pmExtension.getLastName().clear();
			pmExtension.setFirstName(extData[2]);
			pmExtension.setLastName(extData[3]);
			new SelectDropDownValue().selectByVisibleText(pmExtension.getPhoneTypeDropDown(), extData[4]);
			
			pmExtension.getAuthCodeButton().click();
			authCodePage.getAuthCodeAddButton().click();
			authCodePage.setAuthCodeTextBox(extData[5]);
			new SelectDropDownValue().selectByValue(authCodePage.gethashTypeDropDown(), extData[6]);
			authCodePage.setCilCode(extData[5]);
			authCodePage.getAuthPageApplyButton().click();
			
			List<WebElement> eleCount = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//td[contains(text(),'CLEARTEXT')]"));
			Assert.assertTrue(eleCount.size() == 1);
			
			authCodePage.getContinueButton().click();
			authCodePage.getContinueButtonToServiceSummaryPage().click();
			
//			authCodePage.getNextButtonToMicollabConfigPage().click();
			pmExtension.getNextButton().click();
			new SelectDropDownValue().selectByVisibleText(authCodePage.getMicollabRoleDropDown(), extData[7]);
			driver.switchTo().alert().accept();
			pmExtension.getApplyButton().click();
			
			WebDriverWait wait = new WebDriverWait(driver, 100);
//			wait.until(ExpectedConditions.textToBe(By.className("responseMessage"), "  Import operation successful for:"));
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(@class,'responseMessage')]")));
			Assert.assertEquals(driver.findElement(By.xpath("//span[contains(@class,'responseMessage')]")).getText().trim(), "Add operation successful for:");
			
			
//			Assert.assertEquals(pmUser.getResponseMessage().getText().trim(), "Add operation successful for:");
			pmUser.getDoneButton().click();
//			pmUser.getDoneButton().click();
			
			pmUser.getUserSearchTextBox().clear();
			pmUser.setUserSearchTextBox(testData[0]);
			pmUser.getOnViewRangeButton().click();
			
			List<WebElement> eles = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("(//td[contains(text(),'"+testData[0]+"')])[1]//following-sibling::"
					+ "td[contains(text(),'"+extData[1]+" / SN')]"
					+ "//following-sibling::td[contains(text(),'Micollab')]"
					+ "//following-sibling::td[contains(text(),'"+extData[7]+"')]"));
			Assert.assertTrue(eles.size() == 1);
			
			pmMainPage.getLogoutLink().click();
			driver.get(ipData.getData(2, 0));
			Thread.sleep(1000);
			micoLogin.micollab_login(micoCredentials[0], micoCredentials[1]);
			driver.switchTo().frame(serverManagerPage.getInToNavigationFrame());
			serverManagerPage.getUsersAndServicesLink().click();
			driver.switchTo().defaultContent();
			driver.switchTo().frame(serverManagerPage.getMainFrame());
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='basicSearchTerm']")));
			serverManagerPage.getUserSearchTextBox().sendKeys(testData[0]);
			serverManagerPage.getSearchButton().click();
			Thread.sleep(1000);
			List<WebElement> phNums = driver.findElements(By.xpath("(//a[contains(text(),'"+testData[0]+"')]//parent::span//parent::td//following-sibling::td)[2]/span"));
			
			Assert.assertEquals(phNums.get(0).getText().trim(), extData[1]);
			Assert.assertEquals(phNums.get(1).getText().trim(), testData[5]);
			Assert.assertEquals(phNums.get(2).getText().trim(), testData[6]);
			Assert.assertEquals(phNums.get(3).getText().trim(), testData[7]);
			Assert.assertEquals(phNums.get(4).getText().trim(), testData[8]);
			
			driver.findElement(By.xpath("//a[contains(text(),'"+testData[0]+"')]")).click();
			Thread.sleep(1000);
			//System.out.println("First name is :"+serverManagerPage.getFirstNameTextBox().getText().trim());
			//Assert.assertEquals(serverManagerPage.getFirstNameTextBox().getAttribute("value").trim(), testData[0]);
			Assert.assertEquals(serverManagerPage.getLastNameTextBox().getAttribute("value").trim(), testData[0]);
			Assert.assertEquals(serverManagerPage.getEmailAddress().getAttribute("value").trim(), testData[2]);
			serverManagerPage.getPhoneTab().click();
			
			List<WebElement> homePhone1 = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//input[@value='Home Phone']"
					+ "//..//..//following-sibling::tr/td[2]/input[@value='"+testData[5]+"']"));
			List<WebElement> homePhone2 = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//input[@value='Home Phone 2']"
					+ "//..//..//following-sibling::tr/td[2]/input[@value='"+testData[6]+"']"));
			List<WebElement> mobNumber1 = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//input[@value='Mobile Number']"
					+ "//..//..//following-sibling::tr/td[2]/input[@value='"+testData[7]+"']"));
			List<WebElement> mobNumber2 = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//input[@value='Mobile Number 2']"
					+ "//..//..//following-sibling::tr/td[2]/input[@value='"+testData[8]+"']"));
			List<WebElement> extNumber = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("(//input[@value='"+extData[1]+"'])[1]"));
			Assert.assertTrue(homePhone1.size()==1 && homePhone2.size()==1 && mobNumber1.size()==1 && 
					mobNumber2.size()==1 && extNumber.size()==1);
			
//			JavascriptExecutor js = (JavascriptExecutor)driver;
//			js.executeScript("arguments[0].scrollIntoView(true);", serverManagerPage.getSipPassword());
//			Thread.sleep(5000);
//			Assert.assertEquals(serverManagerPage.getSipPassword().getAttribute("value").trim(), extData[5]);
//			Assert.assertEquals(serverManagerPage.getSipConfirmPassword().getAttribute("value").trim(), extData[5]);
			
//			serverManagerPage.getMicollabClientTab().click();
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//a[text()='MiCollab Client'])[2]"))).click();
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//select[@name='ucaService.featureProfileID']/option[@selected='selected']")));
			String assignedRole = driver.findElement(By.xpath("//select[@name='ucaService.featureProfileID']/option[@selected='selected']")).getText().trim();
			String dskPhoneExt = driver.findElement(By.xpath("//select[@id='UCADeskphoneSelect']/option[@selected='selected']")).getText().trim();
			String mailBoxNum = driver.findElement(By.xpath("//select[@id='ucaMailboxSelect']/option[@selected='selected']")).getText().trim();
			
			Assert.assertEquals(assignedRole, extData[7]);
			Assert.assertTrue(dskPhoneExt.contains(extData[1]));
			Assert.assertEquals(mailBoxNum, extData[1]);
			
			serverManagerPage.getCancelButton().click();
			List<WebElement> role = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//img[@src='images/g_check_000.gif']"));
			Assert.assertTrue(role.size()==2);
			
			
			driver.switchTo().defaultContent();
			driver.switchTo().frame(serverManagerPage.getHeaderFrame());
			Thread.sleep(2000);
			serverManagerPage.getLogoutLink().click();
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
			try
			{
				new CleanUP(driver).deleteUser(driver, ipData, credentials, testData[0]);
				new CleanUP(driver).deleteTemplate(driver, method.getName(), loginData, tempData[0], ipData);
				list.clear();
				list.add(extData[8]);
				list.add(extData[9]);
				list.add(extData[10]);
				new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
			}
			catch(Exception e)
			{
					new Take_Screenshot().get_Screenshot(driver, method.getName());
					throw e;
			}
		}
	}
	
	
	
	@Test
	public void test_CreateUser_standardRole_with_Template(Method method) throws Exception
	{
		authCodePage = new PM_Auth_Code_Page(driver);
		micoLogin = new Micollab_Login_Page(driver);
		serverManagerPage = new Micollab_Server_Manager_Page(driver);
		pmLoginPage = new PM_Login_Page(driver);
		pmMainPage = new PM_Main_Page(driver);
		pmUser = new PM_User(driver);
		pmUsers = new PM_Users(driver);
		pmExtension = new Extension(driver);
		
		
		list = new ArrayList<String>();
		String[] credentials = loginData.getData("test_pm_valid_login", 1);
		String[] testData = pmTests.getData(method.getName(), 1);
		String[] extData = pmTests.getData(method.getName(), 3);
		String[] tempData = pmTests.getData(method.getName(), 4);
		String[] micoCredentials = loginData.getData("test_micollab_login", 1);
		wait = new WebDriverWait(driver, 20);
		try 
		{
			pmTests.checkTestStatus(method.getName());
			pmUser = new PM_User(driver);
			pmMainPage = new PM_Main_Page(driver);
			list.add(extData[0]);
			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
			new ReusableUnits(driver).createIPTemplate(driver, method.getName(), ipData, loginData, tempData[0]);
			new ReusableUnits(driver).navigateUserToServiceSummaryPage(driver, method.getName(), ipData, loginData, pmTests);
			
			new SelectDropDownValue().selectByValue(pmExtension.getExtensionHomePageTemplateDropDown(),
					"CUST."+tempData[0]+"_SN_IPExtension");
			
			pmUser.getCreateAndAssignExtensionToUser().click();
//			new SelectDropDownValue().selectByVisibleText(pmExtension.getExtensionType(), "IP");
//			pmExtension.getNextButton().click();
			
			//Provide extension details.
			new SelectDropDownValue().selectByVisibleText(pmExtension.getSelectExtensionsRange(), extData[1]);
			String version = new GetMxoneVersionNumber(driver).getMxoneVersionNumber(driver);
			System.out.println(version);
			int ver = Integer.parseInt(version);
			System.out.println(ver);
			if(ver >= 72000)
			{
				pmExtension.setSingleExtensionValue(extData[1]);
			}
			else
			{
				new SelectDropDownValue().selectByVisibleText(pmExtension.getSingleExtensionDropDown(), extData[1]);
			}
			new SelectDropDownValue().selectByIndex(pmExtension.getMyCSPNameDropDown(),0);
			new SelectDropDownValue().selectByVisibleText(pmExtension.getServerDropDown(), "1");
			pmExtension.getFirstName().clear();
			pmExtension.getLastName().clear();
			pmExtension.setFirstName(extData[2]);
			pmExtension.setLastName(extData[3]);
			new SelectDropDownValue().selectByVisibleText(pmExtension.getPhoneTypeDropDown(), extData[4]);
			
			pmExtension.getAuthCodeButton().click();
			authCodePage.getAuthCodeAddButton().click();
			authCodePage.setAuthCodeTextBox(extData[5]);
			new SelectDropDownValue().selectByValue(authCodePage.gethashTypeDropDown(), extData[6]);
			authCodePage.setCilCode(extData[5]);
			authCodePage.getAuthPageApplyButton().click();
			
			List<WebElement> eleCount = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//td[contains(text(),'CLEARTEXT')]"));
			Assert.assertTrue(eleCount.size() == 1);
			
			authCodePage.getContinueButton().click();
			authCodePage.getContinueButtonToServiceSummaryPage().click();
			
//			authCodePage.getNextButtonToMicollabConfigPage().click();
			pmExtension.getNextButton().click();
			new SelectDropDownValue().selectByVisibleText(authCodePage.getMicollabRoleDropDown(), extData[7]);
			driver.switchTo().alert().accept();
			pmExtension.getApplyButton().click();
			
			WebDriverWait wait = new WebDriverWait(driver, 100);
//			wait.until(ExpectedConditions.textToBe(By.className("responseMessage"), "  Import operation successful for:"));
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(@class,'responseMessage')]")));
			Assert.assertEquals(driver.findElement(By.xpath("//span[contains(@class,'responseMessage')]")).getText().trim(), "Add operation successful for:");
			
			
//			Assert.assertEquals(pmUser.getResponseMessage().getText().trim(), "Add operation successful for:");
			pmUser.getDoneButton().click();
//			pmUser.getDoneButton().click();
			
			pmUser.getUserSearchTextBox().clear();
			pmUser.setUserSearchTextBox(testData[0]);
			pmUser.getOnViewRangeButton().click();
			
			List<WebElement> eles = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("(//td[contains(text(),'"+testData[0]+"')])[1]//following-sibling::"
					+ "td[contains(text(),'"+extData[1]+" / SN')]"
					+ "//following-sibling::td[contains(text(),'Micollab')]"
					+ "//following-sibling::td[contains(text(),'"+extData[7]+"')]"));
			Assert.assertTrue(eles.size() == 1);
			
			pmMainPage.getLogoutLink().click();
			driver.get(ipData.getData(2, 0));
			Thread.sleep(1000);
			micoLogin.micollab_login(micoCredentials[0], micoCredentials[1]);
			driver.switchTo().frame(serverManagerPage.getInToNavigationFrame());
			serverManagerPage.getUsersAndServicesLink().click();
			driver.switchTo().defaultContent();
			driver.switchTo().frame(serverManagerPage.getMainFrame());
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='basicSearchTerm']")));
			serverManagerPage.getUserSearchTextBox().sendKeys(testData[0]);
			serverManagerPage.getSearchButton().click();
			Thread.sleep(1000);
			List<WebElement> phNums = driver.findElements(By.xpath("(//a[contains(text(),'"+testData[0]+"')]//parent::span//parent::td//following-sibling::td)[2]/span"));
			
			Assert.assertEquals(phNums.get(0).getText().trim(), extData[1]);
			Assert.assertEquals(phNums.get(1).getText().trim(), testData[5]);
			Assert.assertEquals(phNums.get(2).getText().trim(), testData[6]);
			Assert.assertEquals(phNums.get(3).getText().trim(), testData[7]);
			Assert.assertEquals(phNums.get(4).getText().trim(), testData[8]);
			
			driver.findElement(By.xpath("//a[contains(text(),'"+testData[0]+"')]")).click();
//			wait.until(ExpectedConditions.elementToBeClickable(By.name("firstNameDisplay")));
			Thread.sleep(1000);
	//		System.out.println("First name is :"+serverManagerPage.getFirstNameTextBox().getText().trim());
		//	Assert.assertEquals(serverManagerPage.getFirstNameTextBox().getAttribute("value").trim(), testData[0]);
			Assert.assertEquals(serverManagerPage.getLastNameTextBox().getAttribute("value").trim(), testData[0]);
			Assert.assertEquals(serverManagerPage.getEmailAddress().getAttribute("value").trim(), testData[2]);
			
			wait.until(ExpectedConditions.elementToBeClickable(By.id("phones_0"))).click();
			Thread.sleep(2000);
//			serverManagerPage.getPhoneTab().click();
//			Thread.sleep(3000);
//			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@value='Home Phone']"
//					+ "//..//..//following-sibling::tr/td[2]/input[@value='"+testData[5]+"']")));
			
			List<WebElement> homePhone1 = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//input[@value='Home Phone']"
					+ "//..//..//following-sibling::tr/td[2]/input[@value='"+testData[5]+"']"));
			List<WebElement> homePhone2 = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//input[@value='Home Phone 2']"
					+ "//..//..//following-sibling::tr/td[2]/input[@value='"+testData[6]+"']"));
			List<WebElement> mobNumber1 = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//input[@value='Mobile Number']"
					+ "//..//..//following-sibling::tr/td[2]/input[@value='"+testData[7]+"']"));
			List<WebElement> mobNumber2 = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//input[@value='Mobile Number 2']"
					+ "//..//..//following-sibling::tr/td[2]/input[@value='"+testData[8]+"']"));
			List<WebElement> extNumber = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("(//input[@value='"+extData[1]+"'])[1]"));
			Assert.assertTrue(homePhone1.size()==1 && homePhone2.size()==1 && mobNumber1.size()==1 && 
					mobNumber2.size()==1 && extNumber.size()==1);
			
//			JavascriptExecutor js = (JavascriptExecutor)driver;
//			js.executeScript("arguments[0].scrollIntoView(true);", serverManagerPage.getSipPassword());
//			Thread.sleep(5000);
//			Assert.assertEquals(serverManagerPage.getSipPassword().getAttribute("value").trim(), extData[5]);
//			Assert.assertEquals(serverManagerPage.getSipConfirmPassword().getAttribute("value").trim(), extData[5]);
			
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//a[text()='MiCollab Client'])[2]"))).click();
//			serverManagerPage.getMicollabClientTab().click();
//			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//select[@name='ucaService.featureProfileID']/option[@selected='selected']")));
			String assignedRole = driver.findElement(By.xpath("//select[@name='ucaService.featureProfileID']/option[@selected='selected']")).getText().trim();
			String dskPhoneExt = driver.findElement(By.xpath("//select[@id='UCADeskphoneSelect']/option[@selected='selected']")).getText().trim();
			String mailBoxNum = driver.findElement(By.xpath("//select[@id='ucaMailboxSelect']/option[@selected='selected']")).getText().trim();
			
			Assert.assertEquals(assignedRole, extData[7]);
			Assert.assertTrue(dskPhoneExt.contains(extData[1]));
			Assert.assertEquals(mailBoxNum, extData[1]);
			
			serverManagerPage.getCancelButton().click();
			List<WebElement> role = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//img[@src='images/g_check_000.gif']"));
			Assert.assertTrue(role.size()==4);
			
			
			driver.switchTo().defaultContent();
			driver.switchTo().frame(serverManagerPage.getHeaderFrame());
			Thread.sleep(2000);
			serverManagerPage.getLogoutLink().click();
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
			try
			{
				new CleanUP(driver).deleteUser(driver, ipData, credentials, testData[0]);
				new CleanUP(driver).deleteTemplate(driver, method.getName(), loginData, tempData[0], ipData);
				list.clear();
				list.add(extData[8]);
				list.add(extData[9]);
				list.add(extData[10]);
				new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
			}
			catch(Exception e)
			{
					new Take_Screenshot().get_Screenshot(driver, method.getName());
					throw e;
			}
		}
	}
	
	
	
	@Test
	public void test_CreateUser_premiumRole_with_Template(Method method) throws Exception
	{
		authCodePage = new PM_Auth_Code_Page(driver);
		micoLogin = new Micollab_Login_Page(driver);
		serverManagerPage = new Micollab_Server_Manager_Page(driver);
		pmLoginPage = new PM_Login_Page(driver);
		pmMainPage = new PM_Main_Page(driver);
		pmUser = new PM_User(driver);
		pmUsers = new PM_Users(driver);
		pmExtension = new Extension(driver);
		
		
		list = new ArrayList<String>();
		String[] credentials = loginData.getData("test_pm_valid_login", 1);
		String[] testData = pmTests.getData(method.getName(), 1);
		String[] extData = pmTests.getData(method.getName(), 3);
		String[] tempData = pmTests.getData(method.getName(), 4);
		String[] micoCredentials = loginData.getData("test_micollab_login", 1);
		wait = new WebDriverWait(driver, 20);
		try 
		{
			pmTests.checkTestStatus(method.getName());
			pmUser = new PM_User(driver);
			pmMainPage = new PM_Main_Page(driver);
			list.add(extData[0]);
			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
			new ReusableUnits(driver).createIPTemplate(driver, method.getName(), ipData, loginData, tempData[0]);
			new ReusableUnits(driver).navigateUserToServiceSummaryPage(driver, method.getName(), ipData, loginData, pmTests);
			
			new SelectDropDownValue().selectByValue(pmExtension.getExtensionHomePageTemplateDropDown(),
					"CUST."+tempData[0]+"_SN_IPExtension");
			
			pmUser.getCreateAndAssignExtensionToUser().click();
//			new SelectDropDownValue().selectByVisibleText(pmExtension.getExtensionType(), "IP");
//			pmExtension.getNextButton().click();
			
			//Provide extension details.
			new SelectDropDownValue().selectByVisibleText(pmExtension.getSelectExtensionsRange(), extData[1]);
			String version = new GetMxoneVersionNumber(driver).getMxoneVersionNumber(driver);
			System.out.println(version);
			int ver = Integer.parseInt(version);
			System.out.println(ver);
			if(ver >= 72000)
			{
				pmExtension.setSingleExtensionValue(extData[1]);
			}
			else
			{
				new SelectDropDownValue().selectByVisibleText(pmExtension.getSingleExtensionDropDown(), extData[1]);
			}
			new SelectDropDownValue().selectByIndex(pmExtension.getMyCSPNameDropDown(),0);
			new SelectDropDownValue().selectByVisibleText(pmExtension.getServerDropDown(), "1");
			pmExtension.getFirstName().clear();
			pmExtension.getLastName().clear();
			pmExtension.setFirstName(extData[2]);
			pmExtension.setLastName(extData[3]);
			new SelectDropDownValue().selectByVisibleText(pmExtension.getPhoneTypeDropDown(), extData[4]);
			
			pmExtension.getAuthCodeButton().click();
			authCodePage.getAuthCodeAddButton().click();
			authCodePage.setAuthCodeTextBox(extData[5]);
			new SelectDropDownValue().selectByValue(authCodePage.gethashTypeDropDown(), extData[6]);
			authCodePage.setCilCode(extData[5]);
			authCodePage.getAuthPageApplyButton().click();
			
			List<WebElement> eleCount = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//td[contains(text(),'CLEARTEXT')]"));
			Assert.assertTrue(eleCount.size() == 1);
			
			authCodePage.getContinueButton().click();
			authCodePage.getContinueButtonToServiceSummaryPage().click();
			
//			authCodePage.getNextButtonToMicollabConfigPage().click();
			pmExtension.getNextButton().click();
			new SelectDropDownValue().selectByVisibleText(authCodePage.getMicollabRoleDropDown(), extData[7]);
			driver.switchTo().alert().accept();
			pmExtension.getApplyButton().click();
			
			WebDriverWait wait = new WebDriverWait(driver, 100);
//			wait.until(ExpectedConditions.textToBe(By.className("responseMessage"), "  Import operation successful for:"));
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(@class,'responseMessage')]")));
			Assert.assertEquals(driver.findElement(By.xpath("//span[contains(@class,'responseMessage')]")).getText().trim(), "Add operation successful for:");
			
			
//			Assert.assertEquals(pmUser.getResponseMessage().getText().trim(), "Add operation successful for:");
			pmUser.getDoneButton().click();
//			pmUser.getDoneButton().click();
			
			pmUser.getUserSearchTextBox().clear();
			pmUser.setUserSearchTextBox(testData[0]);
			pmUser.getOnViewRangeButton().click();
			
			List<WebElement> eles = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("(//td[contains(text(),'"+testData[0]+"')])[1]//following-sibling::"
					+ "td[contains(text(),'"+extData[1]+" / SN')]"
					+ "//following-sibling::td[contains(text(),'Micollab')]"
					+ "//following-sibling::td[contains(text(),'"+extData[7]+"')]"));
			Assert.assertTrue(eles.size() == 1);
			
			pmMainPage.getLogoutLink().click();
			driver.get(ipData.getData(2, 0));
			Thread.sleep(1000);
			micoLogin.micollab_login(micoCredentials[0], micoCredentials[1]);
			driver.switchTo().frame(serverManagerPage.getInToNavigationFrame());
			serverManagerPage.getUsersAndServicesLink().click();
			driver.switchTo().defaultContent();
			driver.switchTo().frame(serverManagerPage.getMainFrame());
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='basicSearchTerm']")));
			serverManagerPage.getUserSearchTextBox().sendKeys(testData[0]);
			serverManagerPage.getSearchButton().click();
			Thread.sleep(1000);
			List<WebElement> phNums = driver.findElements(By.xpath("(//a[contains(text(),'"+testData[0]+"')]//parent::span//parent::td//following-sibling::td)[2]/span"));
			
			Assert.assertEquals(phNums.get(0).getText().trim(), extData[1]);
			Assert.assertEquals(phNums.get(1).getText().trim(), testData[5]);
			Assert.assertEquals(phNums.get(2).getText().trim(), testData[6]);
			Assert.assertEquals(phNums.get(3).getText().trim(), testData[7]);
			Assert.assertEquals(phNums.get(4).getText().trim(), testData[8]);
			
			driver.findElement(By.xpath("//a[contains(text(),'"+testData[0]+"')]")).click();
		//	wait.until(ExpectedConditions.elementToBeClickable(By.name("firstNameDisplay")));
			Thread.sleep(1000);
//			System.out.println("First name is :"+serverManagerPage.getFirstNameTextBox().getText().trim());
	//		Assert.assertEquals(serverManagerPage.getFirstNameTextBox().getAttribute("value").trim(), testData[0]);
			Assert.assertEquals(serverManagerPage.getLastNameTextBox().getAttribute("value").trim(), testData[0]);
			Assert.assertEquals(serverManagerPage.getEmailAddress().getAttribute("value").trim(), testData[2]);
			
			wait.until(ExpectedConditions.elementToBeClickable(By.id("phones_0"))).click();
			Thread.sleep(2000);
//			serverManagerPage.getPhoneTab().click();
//			Thread.sleep(3000);
//			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@value='Home Phone']"
//					+ "//..//..//following-sibling::tr/td[2]/input[@value='"+testData[5]+"']")));
			
			List<WebElement> homePhone1 = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//input[@value='Home Phone']"
					+ "//..//..//following-sibling::tr/td[2]/input[@value='"+testData[5]+"']"));
			List<WebElement> homePhone2 = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//input[@value='Home Phone 2']"
					+ "//..//..//following-sibling::tr/td[2]/input[@value='"+testData[6]+"']"));
			List<WebElement> mobNumber1 = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//input[@value='Mobile Number']"
					+ "//..//..//following-sibling::tr/td[2]/input[@value='"+testData[7]+"']"));
			List<WebElement> mobNumber2 = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//input[@value='Mobile Number 2']"
					+ "//..//..//following-sibling::tr/td[2]/input[@value='"+testData[8]+"']"));
			List<WebElement> extNumber = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("(//input[@value='"+extData[1]+"'])[1]"));
			Assert.assertTrue(homePhone1.size()==1 && homePhone2.size()==1 && mobNumber1.size()==1 && 
					mobNumber2.size()==1 && extNumber.size()==1);
			
//			JavascriptExecutor js = (JavascriptExecutor)driver;
//			js.executeScript("arguments[0].scrollIntoView(true);", serverManagerPage.getSipPassword());
//			Thread.sleep(5000);
//			Assert.assertEquals(serverManagerPage.getSipPassword().getAttribute("value").trim(), extData[5]);
//			Assert.assertEquals(serverManagerPage.getSipConfirmPassword().getAttribute("value").trim(), extData[5]);
			
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//a[text()='MiCollab Client'])[2]"))).click();
//			serverManagerPage.getMicollabClientTab().click();
//			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//select[@name='ucaService.featureProfileID']/option[@selected='selected']")));
			String assignedRole = driver.findElement(By.xpath("//select[@name='ucaService.featureProfileID']/option[@selected='selected']")).getText().trim();
			String dskPhoneExt = driver.findElement(By.xpath("//select[@id='UCADeskphoneSelect']/option[@selected='selected']")).getText().trim();
			String mailBoxNum = driver.findElement(By.xpath("//select[@id='ucaMailboxSelect']/option[@selected='selected']")).getText().trim();
			
			Assert.assertEquals(assignedRole, extData[7]);
			Assert.assertTrue(dskPhoneExt.contains(extData[1]));
			Assert.assertEquals(mailBoxNum, extData[1]);
			
			serverManagerPage.getCancelButton().click();
			List<WebElement> role = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//img[@src='images/g_check_000.gif']"));
			Assert.assertTrue(role.size()==4);
			
			
			driver.switchTo().defaultContent();
			driver.switchTo().frame(serverManagerPage.getHeaderFrame());
			Thread.sleep(2000);
			serverManagerPage.getLogoutLink().click();
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
			try
			{
				new CleanUP(driver).deleteUser(driver, ipData, credentials, testData[0]);
				new CleanUP(driver).deleteTemplate(driver, method.getName(), loginData, tempData[0], ipData);
				list.clear();
				list.add(extData[8]);
				list.add(extData[9]);
				list.add(extData[10]);
				new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
			}
			catch(Exception e)
			{
					new Take_Screenshot().get_Screenshot(driver, method.getName());
					throw e;
			}
		}
	}
	
	
	@Test
	public void test_edit_micollab_Role_from_basic_to_entry(Method method) throws Exception
	{
		authCodePage = new PM_Auth_Code_Page(driver);
		micoLogin = new Micollab_Login_Page(driver);
		serverManagerPage = new Micollab_Server_Manager_Page(driver);
		pmLoginPage = new PM_Login_Page(driver);
		pmMainPage = new PM_Main_Page(driver);
		pmUser = new PM_User(driver);
		pmUsers = new PM_Users(driver);
		pmExtension = new Extension(driver);
		
		
		list = new ArrayList<String>();
		String[] credentials = loginData.getData("test_pm_valid_login", 1);
		String[] testData = pmTests.getData(method.getName(), 1);
		String[] extData = pmTests.getData(method.getName(), 3);
		String[] micoCredentials = loginData.getData("test_micollab_login", 1);
		wait = new WebDriverWait(driver, 20);
		try 
		{
			pmTests.checkTestStatus(method.getName());
			pmUser = new PM_User(driver);
			pmMainPage = new PM_Main_Page(driver);
			list.add(extData[0]);
			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
			new ReusableUnits(driver).navigateUserToServiceSummaryPage(driver, method.getName(), ipData, loginData, pmTests);
			
			
			pmUser.getCreateAndAssignExtensionToUser().click();
			new SelectDropDownValue().selectByVisibleText(pmExtension.getExtensionType(), "IP");
			pmExtension.getNextButton().click();
			
			//Provide extension details.
			new SelectDropDownValue().selectByVisibleText(pmExtension.getSelectExtensionsRange(), extData[1]);
			String version = new GetMxoneVersionNumber(driver).getMxoneVersionNumber(driver);
			System.out.println(version);
			int ver = Integer.parseInt(version);
			System.out.println(ver);
			if(ver >= 72000)
			{
				pmExtension.setSingleExtensionValue(extData[1]);
			}
			else
			{
				new SelectDropDownValue().selectByVisibleText(pmExtension.getSingleExtensionDropDown(), extData[1]);
			}
			new SelectDropDownValue().selectByIndex(pmExtension.getMyCSPNameDropDown(),0);
			new SelectDropDownValue().selectByVisibleText(pmExtension.getServerDropDown(), "1");
			pmExtension.getFirstName().clear();
			pmExtension.getLastName().clear();
			pmExtension.setFirstName(extData[2]);
			pmExtension.setLastName(extData[3]);
			new SelectDropDownValue().selectByVisibleText(pmExtension.getPhoneTypeDropDown(), extData[4]);
			
			pmExtension.getAuthCodeButton().click();
			authCodePage.getAuthCodeAddButton().click();
			authCodePage.setAuthCodeTextBox(extData[5]);
			new SelectDropDownValue().selectByValue(authCodePage.gethashTypeDropDown(), extData[6]);
			authCodePage.setCilCode(extData[5]);
			authCodePage.getAuthPageApplyButton().click();
			
			List<WebElement> eleCount = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//td[contains(text(),'CLEARTEXT')]"));
			Assert.assertTrue(eleCount.size() == 1);
			
			authCodePage.getContinueButton().click();
			authCodePage.getContinueButtonToServiceSummaryPage().click();
			
//			authCodePage.getNextButtonToMicollabConfigPage().click();
			pmExtension.getNextButton().click();
			new SelectDropDownValue().selectByVisibleText(authCodePage.getMicollabRoleDropDown(), extData[7]);
			driver.switchTo().alert().accept();
			pmExtension.getApplyButton().click();
			
			WebDriverWait wait = new WebDriverWait(driver, 100);
//			wait.until(ExpectedConditions.textToBe(By.className("responseMessage"), "  Import operation successful for:"));
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(@class,'responseMessage')]")));
			Assert.assertEquals(driver.findElement(By.xpath("//span[contains(@class,'responseMessage')]")).getText().trim(), "Add operation successful for:");
			
			
//			Assert.assertEquals(pmUser.getResponseMessage().getText().trim(), "Add operation successful for:");
			pmUser.getDoneButton().click();
//			pmUser.getDoneButton().click();
			
			pmUser.getUserSearchTextBox().clear();
			pmUser.setUserSearchTextBox(testData[0]);
			pmUser.getOnViewRangeButton().click();
			
			List<WebElement> eles = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("(//td[contains(text(),'"+testData[0]+"')])[1]//following-sibling::"
					+ "td[contains(text(),'"+extData[1]+" / SN')]"
					+ "//following-sibling::td[contains(text(),'Micollab')]"
					+ "//following-sibling::td[contains(text(),'"+extData[7]+"')]"));
			Assert.assertTrue(eles.size() == 1);
			
			pmMainPage.getLogoutLink().click();
			driver.get(ipData.getData(2, 0));
			micoLogin.micollab_login(micoCredentials[0], micoCredentials[1]);
			driver.switchTo().frame(serverManagerPage.getInToNavigationFrame());
			serverManagerPage.getUsersAndServicesLink().click();
			driver.switchTo().defaultContent();
			driver.switchTo().frame(serverManagerPage.getMainFrame());
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='basicSearchTerm']")));
			serverManagerPage.getUserSearchTextBox().sendKeys(testData[0]);
			serverManagerPage.getSearchButton().click();
			Thread.sleep(1000);
			List<WebElement> phNums = driver.findElements(By.xpath("(//a[contains(text(),'"+testData[0]+"')]//parent::span//parent::td//following-sibling::td)[2]/span"));
			
			Assert.assertEquals(phNums.get(0).getText().trim(), extData[1]);
			Assert.assertEquals(phNums.get(1).getText().trim(), testData[5]);
			Assert.assertEquals(phNums.get(2).getText().trim(), testData[6]);
			Assert.assertEquals(phNums.get(3).getText().trim(), testData[7]);
			Assert.assertEquals(phNums.get(4).getText().trim(), testData[8]);
			
			driver.findElement(By.xpath("//a[contains(text(),'"+testData[0]+"')]")).click();
			Thread.sleep(1000);
			//System.out.println("First name is :"+serverManagerPage.getFirstNameTextBox().getText().trim());
			//Assert.assertEquals(serverManagerPage.getFirstNameTextBox().getAttribute("value").trim(), testData[0]);
			Assert.assertEquals(serverManagerPage.getLastNameTextBox().getAttribute("value").trim(), testData[0]);
			Assert.assertEquals(serverManagerPage.getEmailAddress().getAttribute("value").trim(), testData[2]);
			serverManagerPage.getPhoneTab().click();
			
			List<WebElement> homePhone1 = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//input[@value='Home Phone']"
					+ "//..//..//following-sibling::tr/td[2]/input[@value='"+testData[5]+"']"));
			List<WebElement> homePhone2 = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//input[@value='Home Phone 2']"
					+ "//..//..//following-sibling::tr/td[2]/input[@value='"+testData[6]+"']"));
			List<WebElement> mobNumber1 = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//input[@value='Mobile Number']"
					+ "//..//..//following-sibling::tr/td[2]/input[@value='"+testData[7]+"']"));
			List<WebElement> mobNumber2 = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//input[@value='Mobile Number 2']"
					+ "//..//..//following-sibling::tr/td[2]/input[@value='"+testData[8]+"']"));
			List<WebElement> extNumber = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//input[@value='"+extData[1]+"']"));
			Assert.assertTrue(homePhone1.size()==1 && homePhone2.size()==1 && mobNumber1.size()==1 && 
					mobNumber2.size()==1 && extNumber.size()==1);
//			Thread.sleep(1000);
//			JavascriptExecutor js = (JavascriptExecutor)driver;
//			js.executeScript("arguments[0].scrollIntoView(true);", serverManagerPage.getSipPassword());
//			Thread.sleep(5000);
//			Assert.assertEquals(serverManagerPage.getSipPassword().getAttribute("value").trim(), extData[5]);
//			Assert.assertEquals(serverManagerPage.getSipConfirmPassword().getAttribute("value").trim(), extData[5]);
			
//			serverManagerPage.getMicollabClientTab().click();
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//a[text()='MiCollab Client'])[2]"))).click();
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//select[@name='ucaService.featureProfileID']/option[@selected='selected']")));
			String assignedRole = driver.findElement(By.xpath("//select[@name='ucaService.featureProfileID']/option[@selected='selected']")).getText().trim();
//			String dskPhoneExt = driver.findElement(By.xpath("//select[@id='UCADeskphoneSelect']/option[@selected='selected']")).getText().trim();
//			String mailBoxNum = driver.findElement(By.xpath("//select[@id='ucaMailboxSelect']/option[@selected='selected']")).getText().trim();
			
			Assert.assertEquals(assignedRole, "Default Feature Profile");
//			Assert.assertTrue(dskPhoneExt.contains(extData[1]));
//			Assert.assertEquals(mailBoxNum, extData[1]);
			
			
			serverManagerPage.getCancelButton().click();
			List<WebElement> role = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//img[@src='images/g_check_000.gif']"));
			Assert.assertTrue(role.size()==1);
			
			driver.switchTo().defaultContent();
			driver.switchTo().frame(serverManagerPage.getHeaderFrame());
			Thread.sleep(2000);
			serverManagerPage.getLogoutLink().click();
			
			//////////////////////////////////////change the role now////////////////////////////////////
			
			driver.get(ipData.getData(0, 0));
			pmLoginPage.PM_Login(credentials[0], credentials[1]);
			
			pmMainPage.getUsers().click();
			pmUsers.getUser().click();
			
			pmUser.getUserSearchTextBox().clear();
			pmUser.setUserSearchTextBox(extData[1]);
			pmUser.getOnViewRangeButton().click();
			
			driver.findElement(By.xpath("(//td[contains(text(),'"+testData[0]+"')])[1]"
					+ "//following-sibling::td[contains(text(),'"+extData[1]+" ')]//preceding-sibling::td[25]")).click();
			serverManagerPage.getMicollabConfigTab().click();
			new SelectDropDownValue().selectByVisibleText(authCodePage.getMicollabRoleDropDown(), "UCC (V4.0) Entry");
			driver.switchTo().alert().accept();
			pmExtension.getEditPageApplyButton().click();
			
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(@class,'responseMessage')]")));
			Assert.assertEquals(driver.findElement(By.xpath("//span[contains(@class,'responseMessage')]")).getText().trim(), "Change operation successful for:");
			
			
//			Assert.assertEquals(pmUser.getResponseMessage().getText().trim(), "Add operation successful for:");
			pmUser.getDoneButton().click();
//			pmUser.getDoneButton().click();
			
			pmUser.getUserSearchTextBox().clear();
			pmUser.setUserSearchTextBox(testData[0]);
			pmUser.getOnViewRangeButton().click();
			
			List<WebElement> eles1 = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("(//td[contains(text(),'"+testData[0]+"')])[1]//following-sibling::"
					+ "td[contains(text(),'"+extData[1]+" / SN')]"
					+ "//following-sibling::td[contains(text(),'Micollab')]"
					+ "//following-sibling::td[contains(text(),'UCC (V4.0) Entry')]"));
			Assert.assertTrue(eles.size() == 1);
			pmMainPage.getLogoutLink().click();
			
			///////////////////Again verify in Micollab Now///////////////////////
//			pmMainPage.getLogoutLink().click();
			driver.get(ipData.getData(2, 0));
			micoLogin.micollab_login(micoCredentials[0], micoCredentials[1]);
			driver.switchTo().frame(serverManagerPage.getInToNavigationFrame());
			serverManagerPage.getUsersAndServicesLink().click();
			driver.switchTo().defaultContent();
			driver.switchTo().frame(serverManagerPage.getMainFrame());
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='basicSearchTerm']")));
			serverManagerPage.getUserSearchTextBox().sendKeys(testData[0]);
			serverManagerPage.getSearchButton().click();
			Thread.sleep(1000);
			List<WebElement> phNumsEdit = driver.findElements(By.xpath("(//a[contains(text(),'"+testData[0]+"')]//parent::span//parent::td//following-sibling::td)[2]/span"));
			
			Assert.assertEquals(phNumsEdit.get(0).getText().trim(), extData[1]);
			Assert.assertEquals(phNumsEdit.get(1).getText().trim(), testData[5]);
			Assert.assertEquals(phNumsEdit.get(2).getText().trim(), testData[6]);
			Assert.assertEquals(phNumsEdit.get(3).getText().trim(), testData[7]);
			Assert.assertEquals(phNumsEdit.get(4).getText().trim(), testData[8]);
			
			driver.findElement(By.xpath("//a[contains(text(),'"+testData[0]+"')]")).click();
			Thread.sleep(1000);
			//System.out.println("First name is :"+serverManagerPage.getFirstNameTextBox().getText().trim());
			//Assert.assertEquals(serverManagerPage.getFirstNameTextBox().getAttribute("value").trim(), testData[0]);
			Assert.assertEquals(serverManagerPage.getLastNameTextBox().getAttribute("value").trim(), testData[0]);
			Assert.assertEquals(serverManagerPage.getEmailAddress().getAttribute("value").trim(), testData[2]);
			serverManagerPage.getPhoneTab().click();
			
			List<WebElement> homePhoneInEdit = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//input[@value='Home Phone']"
					+ "//..//..//following-sibling::tr/td[2]/input[@value='"+testData[5]+"']"));
			List<WebElement> homePhone2InEdit = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//input[@value='Home Phone 2']"
					+ "//..//..//following-sibling::tr/td[2]/input[@value='"+testData[6]+"']"));
			List<WebElement> mobNumber1InEdit = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//input[@value='Mobile Number']"
					+ "//..//..//following-sibling::tr/td[2]/input[@value='"+testData[7]+"']"));
			List<WebElement> mobNumber2InEdit = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//input[@value='Mobile Number 2']"
					+ "//..//..//following-sibling::tr/td[2]/input[@value='"+testData[8]+"']"));
			List<WebElement> extNumberInEdit = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("(//input[@value='"+extData[1]+"'])[1]"));
			Assert.assertTrue(homePhoneInEdit.size()==1 && homePhone2InEdit.size()==1 && mobNumber1InEdit.size()==1 && 
					mobNumber2InEdit.size()==1 && extNumberInEdit.size()==1);
//			Thread.sleep(1000);
//			JavascriptExecutor js = (JavascriptExecutor)driver;
//			js.executeScript("arguments[0].scrollIntoView(true);", serverManagerPage.getSipPassword());
//			Thread.sleep(5000);
//			Assert.assertEquals(serverManagerPage.getSipPassword().getAttribute("value").trim(), extData[5]);
//			Assert.assertEquals(serverManagerPage.getSipConfirmPassword().getAttribute("value").trim(), extData[5]);
			
//			serverManagerPage.getMicollabClientTab().click();
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//a[text()='MiCollab Client'])[2]"))).click();
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//select[@name='ucaService.featureProfileID']/option[@selected='selected']")));
			String assignedRoleEdit = driver.findElement(By.xpath("//select[@name='ucaService.featureProfileID']/option[@selected='selected']")).getText().trim();
			String dskPhoneExtEdit = driver.findElement(By.xpath("//select[@id='UCADeskphoneSelect']/option[@selected='selected']")).getText().trim();
			String mailBoxNumEdit = driver.findElement(By.xpath("//select[@id='ucaMailboxSelect']/option[@selected='selected']")).getText().trim();
			
			Assert.assertEquals(assignedRoleEdit, "UCC (V4.0) Entry");
			Assert.assertTrue(dskPhoneExtEdit.contains(extData[1]));
			Assert.assertEquals(mailBoxNumEdit, extData[1]);
			
			
			serverManagerPage.getCancelButton().click();
			List<WebElement> roleEdit = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//img[@src='images/g_check_000.gif']"));
			Assert.assertTrue(roleEdit.size()==2);
			
			driver.switchTo().defaultContent();
			driver.switchTo().frame(serverManagerPage.getHeaderFrame());
			Thread.sleep(2000);
			serverManagerPage.getLogoutLink().click();
			
			
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
			try
			{
				new CleanUP(driver).deleteUser(driver, ipData, credentials, testData[0]);
				list.clear();
				list.add(extData[8]);
				list.add(extData[9]);
				list.add(extData[10]);
				new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
			}
			catch(Exception e)
			{
					new Take_Screenshot().get_Screenshot(driver, method.getName());
					throw e;
			}
		}
	}
	
	
	@Test
	public void test_edit_micollab_Role_from_basic_to_premium(Method method) throws Exception
	{
		authCodePage = new PM_Auth_Code_Page(driver);
		micoLogin = new Micollab_Login_Page(driver);
		serverManagerPage = new Micollab_Server_Manager_Page(driver);
		pmLoginPage = new PM_Login_Page(driver);
		pmMainPage = new PM_Main_Page(driver);
		pmUser = new PM_User(driver);
		pmUsers = new PM_Users(driver);
		pmExtension = new Extension(driver);
		
		
		list = new ArrayList<String>();
		String[] credentials = loginData.getData("test_pm_valid_login", 1);
		String[] testData = pmTests.getData(method.getName(), 1);
		String[] extData = pmTests.getData(method.getName(), 3);
		String[] micoCredentials = loginData.getData("test_micollab_login", 1);
		wait = new WebDriverWait(driver, 20);
		try 
		{
			pmTests.checkTestStatus(method.getName());
			pmUser = new PM_User(driver);
			pmMainPage = new PM_Main_Page(driver);
			list.add(extData[0]);
			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
			new ReusableUnits(driver).navigateUserToServiceSummaryPage(driver, method.getName(), ipData, loginData, pmTests);
			
			
			pmUser.getCreateAndAssignExtensionToUser().click();
			new SelectDropDownValue().selectByVisibleText(pmExtension.getExtensionType(), "IP");
			pmExtension.getNextButton().click();
			
			//Provide extension details.
			new SelectDropDownValue().selectByVisibleText(pmExtension.getSelectExtensionsRange(), extData[1]);
			String version = new GetMxoneVersionNumber(driver).getMxoneVersionNumber(driver);
			System.out.println(version);
			int ver = Integer.parseInt(version);
			System.out.println(ver);
			if(ver >= 72000)
			{
				pmExtension.setSingleExtensionValue(extData[1]);
			}
			else
			{
				new SelectDropDownValue().selectByVisibleText(pmExtension.getSingleExtensionDropDown(), extData[1]);
			}
			new SelectDropDownValue().selectByIndex(pmExtension.getMyCSPNameDropDown(),0);
			new SelectDropDownValue().selectByVisibleText(pmExtension.getServerDropDown(), "1");
			pmExtension.getFirstName().clear();
			pmExtension.getLastName().clear();
			pmExtension.setFirstName(extData[2]);
			pmExtension.setLastName(extData[3]);
			new SelectDropDownValue().selectByVisibleText(pmExtension.getPhoneTypeDropDown(), extData[4]);
			
			pmExtension.getAuthCodeButton().click();
			authCodePage.getAuthCodeAddButton().click();
			authCodePage.setAuthCodeTextBox(extData[5]);
			new SelectDropDownValue().selectByValue(authCodePage.gethashTypeDropDown(), extData[6]);
			authCodePage.setCilCode(extData[5]);
			authCodePage.getAuthPageApplyButton().click();
			
			List<WebElement> eleCount = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//td[contains(text(),'CLEARTEXT')]"));
			Assert.assertTrue(eleCount.size() == 1);
			
			authCodePage.getContinueButton().click();
			authCodePage.getContinueButtonToServiceSummaryPage().click();
			
//			authCodePage.getNextButtonToMicollabConfigPage().click();
			pmExtension.getNextButton().click();
			new SelectDropDownValue().selectByVisibleText(authCodePage.getMicollabRoleDropDown(), extData[7]);
			driver.switchTo().alert().accept();
			pmExtension.getApplyButton().click();
			
			WebDriverWait wait = new WebDriverWait(driver, 100);
//			wait.until(ExpectedConditions.textToBe(By.className("responseMessage"), "  Import operation successful for:"));
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(@class,'responseMessage')]")));
			Assert.assertEquals(driver.findElement(By.xpath("//span[contains(@class,'responseMessage')]")).getText().trim(), "Add operation successful for:");
			
			
//			Assert.assertEquals(pmUser.getResponseMessage().getText().trim(), "Add operation successful for:");
			pmUser.getDoneButton().click();
//			pmUser.getDoneButton().click();
			
			pmUser.getUserSearchTextBox().clear();
			pmUser.setUserSearchTextBox(testData[0]);
			pmUser.getOnViewRangeButton().click();
			
			List<WebElement> eles = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("(//td[contains(text(),'"+testData[0]+"')])[1]//following-sibling::"
					+ "td[contains(text(),'"+extData[1]+" / SN')]"
					+ "//following-sibling::td[contains(text(),'Micollab')]"
					+ "//following-sibling::td[contains(text(),'"+extData[7]+"')]"));
			Assert.assertTrue(eles.size() == 1);
			
			pmMainPage.getLogoutLink().click();
			driver.get(ipData.getData(2, 0));
			micoLogin.micollab_login(micoCredentials[0], micoCredentials[1]);
			driver.switchTo().frame(serverManagerPage.getInToNavigationFrame());
			serverManagerPage.getUsersAndServicesLink().click();
			driver.switchTo().defaultContent();
			driver.switchTo().frame(serverManagerPage.getMainFrame());
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='basicSearchTerm']")));
			serverManagerPage.getUserSearchTextBox().sendKeys(testData[0]);
			serverManagerPage.getSearchButton().click();
			Thread.sleep(1000);
			List<WebElement> phNums = driver.findElements(By.xpath("(//a[contains(text(),'"+testData[0]+"')]//parent::span//parent::td//following-sibling::td)[2]/span"));
			
			Assert.assertEquals(phNums.get(0).getText().trim(), extData[1]);
			Assert.assertEquals(phNums.get(1).getText().trim(), testData[5]);
			Assert.assertEquals(phNums.get(2).getText().trim(), testData[6]);
			Assert.assertEquals(phNums.get(3).getText().trim(), testData[7]);
			Assert.assertEquals(phNums.get(4).getText().trim(), testData[8]);
			
			driver.findElement(By.xpath("//a[contains(text(),'"+testData[0]+"')]")).click();
			Thread.sleep(1000);
			//System.out.println("First name is :"+serverManagerPage.getFirstNameTextBox().getText().trim());
			//Assert.assertEquals(serverManagerPage.getFirstNameTextBox().getAttribute("value").trim(), testData[0]);
			Assert.assertEquals(serverManagerPage.getLastNameTextBox().getAttribute("value").trim(), testData[0]);
			Assert.assertEquals(serverManagerPage.getEmailAddress().getAttribute("value").trim(), testData[2]);
			serverManagerPage.getPhoneTab().click();
			
			List<WebElement> homePhone1 = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//input[@value='Home Phone']"
					+ "//..//..//following-sibling::tr/td[2]/input[@value='"+testData[5]+"']"));
			List<WebElement> homePhone2 = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//input[@value='Home Phone 2']"
					+ "//..//..//following-sibling::tr/td[2]/input[@value='"+testData[6]+"']"));
			List<WebElement> mobNumber1 = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//input[@value='Mobile Number']"
					+ "//..//..//following-sibling::tr/td[2]/input[@value='"+testData[7]+"']"));
			List<WebElement> mobNumber2 = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//input[@value='Mobile Number 2']"
					+ "//..//..//following-sibling::tr/td[2]/input[@value='"+testData[8]+"']"));
			List<WebElement> extNumber = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//input[@value='"+extData[1]+"']"));
			Assert.assertTrue(homePhone1.size()==1 && homePhone2.size()==1 && mobNumber1.size()==1 && 
					mobNumber2.size()==1 && extNumber.size()==1);
//			Thread.sleep(1000);
//			JavascriptExecutor js = (JavascriptExecutor)driver;
//			js.executeScript("arguments[0].scrollIntoView(true);", serverManagerPage.getSipPassword());
//			Thread.sleep(5000);
//			Assert.assertEquals(serverManagerPage.getSipPassword().getAttribute("value").trim(), extData[5]);
//			Assert.assertEquals(serverManagerPage.getSipConfirmPassword().getAttribute("value").trim(), extData[5]);
			
//			serverManagerPage.getMicollabClientTab().click();
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//a[text()='MiCollab Client'])[2]"))).click();
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//select[@name='ucaService.featureProfileID']/option[@selected='selected']")));
			String assignedRole = driver.findElement(By.xpath("//select[@name='ucaService.featureProfileID']/option[@selected='selected']")).getText().trim();
//			String dskPhoneExt = driver.findElement(By.xpath("//select[@id='UCADeskphoneSelect']/option[@selected='selected']")).getText().trim();
//			String mailBoxNum = driver.findElement(By.xpath("//select[@id='ucaMailboxSelect']/option[@selected='selected']")).getText().trim();
			
			Assert.assertEquals(assignedRole, "Default Feature Profile");
//			Assert.assertTrue(dskPhoneExt.contains(extData[1]));
//			Assert.assertEquals(mailBoxNum, extData[1]);
			
			
			serverManagerPage.getCancelButton().click();
			List<WebElement> role = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//img[@src='images/g_check_000.gif']"));
			Assert.assertTrue(role.size()==1);
			
			driver.switchTo().defaultContent();
			driver.switchTo().frame(serverManagerPage.getHeaderFrame());
			Thread.sleep(2000);
			serverManagerPage.getLogoutLink().click();
			
			//////////////////////////////////////change the role now////////////////////////////////////
			
			driver.get(ipData.getData(0, 0));
			pmLoginPage.PM_Login(credentials[0], credentials[1]);
			
			pmMainPage.getUsers().click();
			pmUsers.getUser().click();
			
			pmUser.getUserSearchTextBox().clear();
			pmUser.setUserSearchTextBox(extData[1]);
			pmUser.getOnViewRangeButton().click();
			
			driver.findElement(By.xpath("(//td[contains(text(),'"+testData[0]+"')])[1]"
					+ "//following-sibling::td[contains(text(),'"+extData[1]+" ')]//preceding-sibling::td[25]")).click();
			serverManagerPage.getMicollabConfigTab().click();
			new SelectDropDownValue().selectByVisibleText(authCodePage.getMicollabRoleDropDown(), "UCC (V4.0) Premium");
			driver.switchTo().alert().accept();
			pmExtension.getEditPageApplyButton().click();
			
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(@class,'responseMessage')]")));
			Assert.assertEquals(driver.findElement(By.xpath("//span[contains(@class,'responseMessage')]")).getText().trim(), "Change operation successful for:");
			
			
//			Assert.assertEquals(pmUser.getResponseMessage().getText().trim(), "Add operation successful for:");
			pmUser.getDoneButton().click();
//			pmUser.getDoneButton().click();
			
			pmUser.getUserSearchTextBox().clear();
			pmUser.setUserSearchTextBox(testData[0]);
			pmUser.getOnViewRangeButton().click();
			
			List<WebElement> eles1 = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("(//td[contains(text(),'"+testData[0]+"')])[1]//following-sibling::"
					+ "td[contains(text(),'"+extData[1]+" / SN')]"
					+ "//following-sibling::td[contains(text(),'Micollab')]"
					+ "//following-sibling::td[contains(text(),'UCC (V4.0) Premium')]"));
			Assert.assertTrue(eles.size() == 1);
			pmMainPage.getLogoutLink().click();
			
			///////////////////Again verify in Micollab Now///////////////////////
//			pmMainPage.getLogoutLink().click();
			driver.get(ipData.getData(2, 0));
			micoLogin.micollab_login(micoCredentials[0], micoCredentials[1]);
			driver.switchTo().frame(serverManagerPage.getInToNavigationFrame());
			serverManagerPage.getUsersAndServicesLink().click();
			driver.switchTo().defaultContent();
			driver.switchTo().frame(serverManagerPage.getMainFrame());
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='basicSearchTerm']")));
			serverManagerPage.getUserSearchTextBox().sendKeys(testData[0]);
			serverManagerPage.getSearchButton().click();
			Thread.sleep(1000);
			List<WebElement> phNumsEdit = driver.findElements(By.xpath("(//a[contains(text(),'"+testData[0]+"')]//parent::span//parent::td//following-sibling::td)[2]/span"));
			
			Assert.assertEquals(phNumsEdit.get(0).getText().trim(), extData[1]);
			Assert.assertEquals(phNumsEdit.get(1).getText().trim(), testData[5]);
			Assert.assertEquals(phNumsEdit.get(2).getText().trim(), testData[6]);
			Assert.assertEquals(phNumsEdit.get(3).getText().trim(), testData[7]);
			Assert.assertEquals(phNumsEdit.get(4).getText().trim(), testData[8]);
			
			driver.findElement(By.xpath("//a[contains(text(),'"+testData[0]+"')]")).click();
			Thread.sleep(1000);
			//System.out.println("First name is :"+serverManagerPage.getFirstNameTextBox().getText().trim());
			//Assert.assertEquals(serverManagerPage.getFirstNameTextBox().getAttribute("value").trim(), testData[0]);
			Assert.assertEquals(serverManagerPage.getLastNameTextBox().getAttribute("value").trim(), testData[0]);
			Assert.assertEquals(serverManagerPage.getEmailAddress().getAttribute("value").trim(), testData[2]);
			serverManagerPage.getPhoneTab().click();
			
			List<WebElement> homePhoneInEdit = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//input[@value='Home Phone']"
					+ "//..//..//following-sibling::tr/td[2]/input[@value='"+testData[5]+"']"));
			List<WebElement> homePhone2InEdit = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//input[@value='Home Phone 2']"
					+ "//..//..//following-sibling::tr/td[2]/input[@value='"+testData[6]+"']"));
			List<WebElement> mobNumber1InEdit = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//input[@value='Mobile Number']"
					+ "//..//..//following-sibling::tr/td[2]/input[@value='"+testData[7]+"']"));
			List<WebElement> mobNumber2InEdit = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//input[@value='Mobile Number 2']"
					+ "//..//..//following-sibling::tr/td[2]/input[@value='"+testData[8]+"']"));
			List<WebElement> extNumberInEdit = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("(//input[@value='"+extData[1]+"'])[1]"));
			Assert.assertTrue(homePhoneInEdit.size()==1 && homePhone2InEdit.size()==1 && mobNumber1InEdit.size()==1 && 
					mobNumber2InEdit.size()==1 && extNumberInEdit.size()==1);
//			Thread.sleep(1000);
//			JavascriptExecutor js = (JavascriptExecutor)driver;
//			js.executeScript("arguments[0].scrollIntoView(true);", serverManagerPage.getSipPassword());
//			Thread.sleep(5000);
//			Assert.assertEquals(serverManagerPage.getSipPassword().getAttribute("value").trim(), extData[5]);
//			Assert.assertEquals(serverManagerPage.getSipConfirmPassword().getAttribute("value").trim(), extData[5]);
			
//			serverManagerPage.getMicollabClientTab().click();
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//a[text()='MiCollab Client'])[2]"))).click();
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//select[@name='ucaService.featureProfileID']/option[@selected='selected']")));
			String assignedRoleEdit = driver.findElement(By.xpath("//select[@name='ucaService.featureProfileID']/option[@selected='selected']")).getText().trim();
			String dskPhoneExtEdit = driver.findElement(By.xpath("//select[@id='UCADeskphoneSelect']/option[@selected='selected']")).getText().trim();
			String mailBoxNumEdit = driver.findElement(By.xpath("//select[@id='ucaMailboxSelect']/option[@selected='selected']")).getText().trim();
			
			Assert.assertEquals(assignedRoleEdit, "UCC (V4.0) Premium");
			Assert.assertTrue(dskPhoneExtEdit.contains(extData[1]));
			Assert.assertEquals(mailBoxNumEdit, extData[1]);
			
			
			serverManagerPage.getCancelButton().click();
			List<WebElement> roleEdit = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//img[@src='images/g_check_000.gif']"));
			Assert.assertTrue(roleEdit.size()==4);
			
			driver.switchTo().defaultContent();
			driver.switchTo().frame(serverManagerPage.getHeaderFrame());
			Thread.sleep(2000);
			serverManagerPage.getLogoutLink().click();
			
			
			
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
			try
			{
				new CleanUP(driver).deleteUser(driver, ipData, credentials, testData[0]);
				list.clear();
				list.add(extData[8]);
				list.add(extData[9]);
				list.add(extData[10]);
				new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
			}
			catch(Exception e)
			{
					new Take_Screenshot().get_Screenshot(driver, method.getName());
					throw e;
			}
		}
	}
	
	
	@Test
	public void test_edit_micollab_Role_from_basic_to_standard(Method method) throws Exception
	{
		authCodePage = new PM_Auth_Code_Page(driver);
		micoLogin = new Micollab_Login_Page(driver);
		serverManagerPage = new Micollab_Server_Manager_Page(driver);
		pmLoginPage = new PM_Login_Page(driver);
		pmMainPage = new PM_Main_Page(driver);
		pmUser = new PM_User(driver);
		pmUsers = new PM_Users(driver);
		pmExtension = new Extension(driver);
		
		
		list = new ArrayList<String>();
		String[] credentials = loginData.getData("test_pm_valid_login", 1);
		String[] testData = pmTests.getData(method.getName(), 1);
		String[] extData = pmTests.getData(method.getName(), 3);
		String[] micoCredentials = loginData.getData("test_micollab_login", 1);
		wait = new WebDriverWait(driver, 20);
		try 
		{
			pmTests.checkTestStatus(method.getName());
			pmUser = new PM_User(driver);
			pmMainPage = new PM_Main_Page(driver);
			list.add(extData[0]);
			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
			new ReusableUnits(driver).navigateUserToServiceSummaryPage(driver, method.getName(), ipData, loginData, pmTests);
			
			
			pmUser.getCreateAndAssignExtensionToUser().click();
			new SelectDropDownValue().selectByVisibleText(pmExtension.getExtensionType(), "IP");
			pmExtension.getNextButton().click();
			
			//Provide extension details.
			new SelectDropDownValue().selectByVisibleText(pmExtension.getSelectExtensionsRange(), extData[1]);
			String version = new GetMxoneVersionNumber(driver).getMxoneVersionNumber(driver);
			System.out.println(version);
			int ver = Integer.parseInt(version);
			System.out.println(ver);
			if(ver >= 72000)
			{
				pmExtension.setSingleExtensionValue(extData[1]);
			}
			else
			{
				new SelectDropDownValue().selectByVisibleText(pmExtension.getSingleExtensionDropDown(), extData[1]);
			}
			new SelectDropDownValue().selectByIndex(pmExtension.getMyCSPNameDropDown(),0);
			new SelectDropDownValue().selectByVisibleText(pmExtension.getServerDropDown(), "1");
			pmExtension.getFirstName().clear();
			pmExtension.getLastName().clear();
			pmExtension.setFirstName(extData[2]);
			pmExtension.setLastName(extData[3]);
			new SelectDropDownValue().selectByVisibleText(pmExtension.getPhoneTypeDropDown(), extData[4]);
			
			pmExtension.getAuthCodeButton().click();
			authCodePage.getAuthCodeAddButton().click();
			authCodePage.setAuthCodeTextBox(extData[5]);
			new SelectDropDownValue().selectByValue(authCodePage.gethashTypeDropDown(), extData[6]);
			authCodePage.setCilCode(extData[5]);
			authCodePage.getAuthPageApplyButton().click();
			
			List<WebElement> eleCount = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//td[contains(text(),'CLEARTEXT')]"));
			Assert.assertTrue(eleCount.size() == 1);
			
			authCodePage.getContinueButton().click();
			authCodePage.getContinueButtonToServiceSummaryPage().click();
			
//			authCodePage.getNextButtonToMicollabConfigPage().click();
			pmExtension.getNextButton().click();
			new SelectDropDownValue().selectByVisibleText(authCodePage.getMicollabRoleDropDown(), extData[7]);
			driver.switchTo().alert().accept();
			pmExtension.getApplyButton().click();
			
			WebDriverWait wait = new WebDriverWait(driver, 100);
//			wait.until(ExpectedConditions.textToBe(By.className("responseMessage"), "  Import operation successful for:"));
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(@class,'responseMessage')]")));
			Assert.assertEquals(driver.findElement(By.xpath("//span[contains(@class,'responseMessage')]")).getText().trim(), "Add operation successful for:");
			
			
//			Assert.assertEquals(pmUser.getResponseMessage().getText().trim(), "Add operation successful for:");
			pmUser.getDoneButton().click();
//			pmUser.getDoneButton().click();
			
			pmUser.getUserSearchTextBox().clear();
			pmUser.setUserSearchTextBox(testData[0]);
			pmUser.getOnViewRangeButton().click();
			
			List<WebElement> eles = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("(//td[contains(text(),'"+testData[0]+"')])[1]//following-sibling::"
					+ "td[contains(text(),'"+extData[1]+" / SN')]"
					+ "//following-sibling::td[contains(text(),'Micollab')]"
					+ "//following-sibling::td[contains(text(),'"+extData[7]+"')]"));
			Assert.assertTrue(eles.size() == 1);
			
			pmMainPage.getLogoutLink().click();
			driver.get(ipData.getData(2, 0));
			micoLogin.micollab_login(micoCredentials[0], micoCredentials[1]);
			driver.switchTo().frame(serverManagerPage.getInToNavigationFrame());
			serverManagerPage.getUsersAndServicesLink().click();
			driver.switchTo().defaultContent();
			driver.switchTo().frame(serverManagerPage.getMainFrame());
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='basicSearchTerm']")));
			serverManagerPage.getUserSearchTextBox().sendKeys(testData[0]);
			serverManagerPage.getSearchButton().click();
			Thread.sleep(1000);
			List<WebElement> phNums = driver.findElements(By.xpath("(//a[contains(text(),'"+testData[0]+"')]//parent::span//parent::td//following-sibling::td)[2]/span"));
			
			Assert.assertEquals(phNums.get(0).getText().trim(), extData[1]);
			Assert.assertEquals(phNums.get(1).getText().trim(), testData[5]);
			Assert.assertEquals(phNums.get(2).getText().trim(), testData[6]);
			Assert.assertEquals(phNums.get(3).getText().trim(), testData[7]);
			Assert.assertEquals(phNums.get(4).getText().trim(), testData[8]);
			
			driver.findElement(By.xpath("//a[contains(text(),'"+testData[0]+"')]")).click();
			Thread.sleep(1000);
			//System.out.println("First name is :"+serverManagerPage.getFirstNameTextBox().getText().trim());
			//Assert.assertEquals(serverManagerPage.getFirstNameTextBox().getAttribute("value").trim(), testData[0]);
			Assert.assertEquals(serverManagerPage.getLastNameTextBox().getAttribute("value").trim(), testData[0]);
			Assert.assertEquals(serverManagerPage.getEmailAddress().getAttribute("value").trim(), testData[2]);
			serverManagerPage.getPhoneTab().click();
			
			List<WebElement> homePhone1 = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//input[@value='Home Phone']"
					+ "//..//..//following-sibling::tr/td[2]/input[@value='"+testData[5]+"']"));
			List<WebElement> homePhone2 = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//input[@value='Home Phone 2']"
					+ "//..//..//following-sibling::tr/td[2]/input[@value='"+testData[6]+"']"));
			List<WebElement> mobNumber1 = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//input[@value='Mobile Number']"
					+ "//..//..//following-sibling::tr/td[2]/input[@value='"+testData[7]+"']"));
			List<WebElement> mobNumber2 = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//input[@value='Mobile Number 2']"
					+ "//..//..//following-sibling::tr/td[2]/input[@value='"+testData[8]+"']"));
			List<WebElement> extNumber = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//input[@value='"+extData[1]+"']"));
			Assert.assertTrue(homePhone1.size()==1 && homePhone2.size()==1 && mobNumber1.size()==1 && 
					mobNumber2.size()==1 && extNumber.size()==1);
//			Thread.sleep(1000);
//			JavascriptExecutor js = (JavascriptExecutor)driver;
//			js.executeScript("arguments[0].scrollIntoView(true);", serverManagerPage.getSipPassword());
//			Thread.sleep(5000);
//			Assert.assertEquals(serverManagerPage.getSipPassword().getAttribute("value").trim(), extData[5]);
//			Assert.assertEquals(serverManagerPage.getSipConfirmPassword().getAttribute("value").trim(), extData[5]);
			
//			serverManagerPage.getMicollabClientTab().click();
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//a[text()='MiCollab Client'])[2]"))).click();
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//select[@name='ucaService.featureProfileID']/option[@selected='selected']")));
			String assignedRole = driver.findElement(By.xpath("//select[@name='ucaService.featureProfileID']/option[@selected='selected']")).getText().trim();
//			String dskPhoneExt = driver.findElement(By.xpath("//select[@id='UCADeskphoneSelect']/option[@selected='selected']")).getText().trim();
//			String mailBoxNum = driver.findElement(By.xpath("//select[@id='ucaMailboxSelect']/option[@selected='selected']")).getText().trim();
			
			Assert.assertEquals(assignedRole, "Default Feature Profile");
//			Assert.assertTrue(dskPhoneExt.contains(extData[1]));
//			Assert.assertEquals(mailBoxNum, extData[1]);
			
			
			serverManagerPage.getCancelButton().click();
			List<WebElement> role = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//img[@src='images/g_check_000.gif']"));
			Assert.assertTrue(role.size()==1);
			
			driver.switchTo().defaultContent();
			driver.switchTo().frame(serverManagerPage.getHeaderFrame());
			Thread.sleep(2000);
			serverManagerPage.getLogoutLink().click();
			
			//////////////////////////////////////change the role now////////////////////////////////////
			
			driver.get(ipData.getData(0, 0));
			pmLoginPage.PM_Login(credentials[0], credentials[1]);
			
			pmMainPage.getUsers().click();
			pmUsers.getUser().click();
			
			pmUser.getUserSearchTextBox().clear();
			pmUser.setUserSearchTextBox(extData[1]);
			pmUser.getOnViewRangeButton().click();
			
			driver.findElement(By.xpath("(//td[contains(text(),'"+testData[0]+"')])[1]"
					+ "//following-sibling::td[contains(text(),'"+extData[1]+" ')]//preceding-sibling::td[25]")).click();
			serverManagerPage.getMicollabConfigTab().click();
			new SelectDropDownValue().selectByVisibleText(authCodePage.getMicollabRoleDropDown(), "UCC (V4.0) Standard");
			driver.switchTo().alert().accept();
			pmExtension.getEditPageApplyButton().click();
			
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(@class,'responseMessage')]")));
			Assert.assertEquals(driver.findElement(By.xpath("//span[contains(@class,'responseMessage')]")).getText().trim(), "Change operation successful for:");
			
			
//			Assert.assertEquals(pmUser.getResponseMessage().getText().trim(), "Add operation successful for:");
			pmUser.getDoneButton().click();
//			pmUser.getDoneButton().click();
			
			pmUser.getUserSearchTextBox().clear();
			pmUser.setUserSearchTextBox(testData[0]);
			pmUser.getOnViewRangeButton().click();
			
			List<WebElement> eles1 = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("(//td[contains(text(),'"+testData[0]+"')])[1]//following-sibling::"
					+ "td[contains(text(),'"+extData[1]+" / SN')]"
					+ "//following-sibling::td[contains(text(),'Micollab')]"
					+ "//following-sibling::td[contains(text(),'UCC (V4.0) Standard')]"));
			Assert.assertTrue(eles.size() == 1);
			pmMainPage.getLogoutLink().click();
			
			///////////////////Again verify in Micollab Now///////////////////////
//			pmMainPage.getLogoutLink().click();
			driver.get(ipData.getData(2, 0));
			micoLogin.micollab_login(micoCredentials[0], micoCredentials[1]);
			driver.switchTo().frame(serverManagerPage.getInToNavigationFrame());
			serverManagerPage.getUsersAndServicesLink().click();
			driver.switchTo().defaultContent();
			driver.switchTo().frame(serverManagerPage.getMainFrame());
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='basicSearchTerm']")));
			serverManagerPage.getUserSearchTextBox().sendKeys(testData[0]);
			serverManagerPage.getSearchButton().click();
			Thread.sleep(1000);
			List<WebElement> phNumsEdit = driver.findElements(By.xpath("(//a[contains(text(),'"+testData[0]+"')]//parent::span//parent::td//following-sibling::td)[2]/span"));
			
			Assert.assertEquals(phNumsEdit.get(0).getText().trim(), extData[1]);
			Assert.assertEquals(phNumsEdit.get(1).getText().trim(), testData[5]);
			Assert.assertEquals(phNumsEdit.get(2).getText().trim(), testData[6]);
			Assert.assertEquals(phNumsEdit.get(3).getText().trim(), testData[7]);
			Assert.assertEquals(phNumsEdit.get(4).getText().trim(), testData[8]);
			
			driver.findElement(By.xpath("//a[contains(text(),'"+testData[0]+"')]")).click();
			Thread.sleep(1000);
		//	System.out.println("First name is :"+serverManagerPage.getFirstNameTextBox().getText().trim());
		//	Assert.assertEquals(serverManagerPage.getFirstNameTextBox().getAttribute("value").trim(), testData[0]);
			Assert.assertEquals(serverManagerPage.getLastNameTextBox().getAttribute("value").trim(), testData[0]);
			Assert.assertEquals(serverManagerPage.getEmailAddress().getAttribute("value").trim(), testData[2]);
			serverManagerPage.getPhoneTab().click();
			
			List<WebElement> homePhoneInEdit = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//input[@value='Home Phone']"
					+ "//..//..//following-sibling::tr/td[2]/input[@value='"+testData[5]+"']"));
			List<WebElement> homePhone2InEdit = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//input[@value='Home Phone 2']"
					+ "//..//..//following-sibling::tr/td[2]/input[@value='"+testData[6]+"']"));
			List<WebElement> mobNumber1InEdit = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//input[@value='Mobile Number']"
					+ "//..//..//following-sibling::tr/td[2]/input[@value='"+testData[7]+"']"));
			List<WebElement> mobNumber2InEdit = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//input[@value='Mobile Number 2']"
					+ "//..//..//following-sibling::tr/td[2]/input[@value='"+testData[8]+"']"));
			List<WebElement> extNumberInEdit = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("(//input[@value='"+extData[1]+"'])[1]"));
			Assert.assertTrue(homePhoneInEdit.size()==1 && homePhone2InEdit.size()==1 && mobNumber1InEdit.size()==1 && 
					mobNumber2InEdit.size()==1 && extNumberInEdit.size()==1);
//			Thread.sleep(1000);
//			JavascriptExecutor js = (JavascriptExecutor)driver;
//			js.executeScript("arguments[0].scrollIntoView(true);", serverManagerPage.getSipPassword());
//			Thread.sleep(5000);
//			Assert.assertEquals(serverManagerPage.getSipPassword().getAttribute("value").trim(), extData[5]);
//			Assert.assertEquals(serverManagerPage.getSipConfirmPassword().getAttribute("value").trim(), extData[5]);
			
//			serverManagerPage.getMicollabClientTab().click();
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//a[text()='MiCollab Client'])[2]"))).click();
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//select[@name='ucaService.featureProfileID']/option[@selected='selected']")));
			String assignedRoleEdit = driver.findElement(By.xpath("//select[@name='ucaService.featureProfileID']/option[@selected='selected']")).getText().trim();
			String dskPhoneExtEdit = driver.findElement(By.xpath("//select[@id='UCADeskphoneSelect']/option[@selected='selected']")).getText().trim();
			String mailBoxNumEdit = driver.findElement(By.xpath("//select[@id='ucaMailboxSelect']/option[@selected='selected']")).getText().trim();
			
			Assert.assertEquals(assignedRoleEdit, "UCC (V4.0) Standard");
			Assert.assertTrue(dskPhoneExtEdit.contains(extData[1]));
			Assert.assertEquals(mailBoxNumEdit, extData[1]);
			
			
			serverManagerPage.getCancelButton().click();
			List<WebElement> roleEdit = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//img[@src='images/g_check_000.gif']"));
			Assert.assertTrue(roleEdit.size()==4);
			
			driver.switchTo().defaultContent();
			driver.switchTo().frame(serverManagerPage.getHeaderFrame());
			Thread.sleep(2000);
			serverManagerPage.getLogoutLink().click();
			
			
			
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
			try
			{
				new CleanUP(driver).deleteUser(driver, ipData, credentials, testData[0]);
				list.clear();
				list.add(extData[8]);
				list.add(extData[9]);
				list.add(extData[10]);
				new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
			}
			catch(Exception e)
			{
					new Take_Screenshot().get_Screenshot(driver, method.getName());
					throw e;
			}
		}
	}
	
	
	
	@Test
	public void test_edit_micollab_Role_from_entry_to_basic(Method method) throws Exception
	{
		authCodePage = new PM_Auth_Code_Page(driver);
		micoLogin = new Micollab_Login_Page(driver);
		serverManagerPage = new Micollab_Server_Manager_Page(driver);
		pmLoginPage = new PM_Login_Page(driver);
		pmMainPage = new PM_Main_Page(driver);
		pmUser = new PM_User(driver);
		pmUsers = new PM_Users(driver);
		pmExtension = new Extension(driver);
		
		
		list = new ArrayList<String>();
		String[] credentials = loginData.getData("test_pm_valid_login", 1);
		String[] testData = pmTests.getData(method.getName(), 1);
		String[] extData = pmTests.getData(method.getName(), 3);
		String[] micoCredentials = loginData.getData("test_micollab_login", 1);
		wait = new WebDriverWait(driver, 20);
		try 
		{
			pmTests.checkTestStatus(method.getName());
			pmUser = new PM_User(driver);
			pmMainPage = new PM_Main_Page(driver);
			list.add(extData[0]);
			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
			new ReusableUnits(driver).navigateUserToServiceSummaryPage(driver, method.getName(), ipData, loginData, pmTests);
			
			
			pmUser.getCreateAndAssignExtensionToUser().click();
			new SelectDropDownValue().selectByVisibleText(pmExtension.getExtensionType(), "IP");
			pmExtension.getNextButton().click();
			
			//Provide extension details.
			new SelectDropDownValue().selectByVisibleText(pmExtension.getSelectExtensionsRange(), extData[1]);
			String version = new GetMxoneVersionNumber(driver).getMxoneVersionNumber(driver);
			System.out.println(version);
			int ver = Integer.parseInt(version);
			System.out.println(ver);
			if(ver >= 72000)
			{
				pmExtension.setSingleExtensionValue(extData[1]);
			}
			else
			{
				new SelectDropDownValue().selectByVisibleText(pmExtension.getSingleExtensionDropDown(), extData[1]);
			}
			new SelectDropDownValue().selectByIndex(pmExtension.getMyCSPNameDropDown(),0);
			new SelectDropDownValue().selectByVisibleText(pmExtension.getServerDropDown(), "1");
			pmExtension.getFirstName().clear();
			pmExtension.getLastName().clear();
			pmExtension.setFirstName(extData[2]);
			pmExtension.setLastName(extData[3]);
			new SelectDropDownValue().selectByVisibleText(pmExtension.getPhoneTypeDropDown(), extData[4]);
			
			pmExtension.getAuthCodeButton().click();
			authCodePage.getAuthCodeAddButton().click();
			authCodePage.setAuthCodeTextBox(extData[5]);
			new SelectDropDownValue().selectByValue(authCodePage.gethashTypeDropDown(), extData[6]);
			authCodePage.setCilCode(extData[5]);
			authCodePage.getAuthPageApplyButton().click();
			
			List<WebElement> eleCount = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//td[contains(text(),'CLEARTEXT')]"));
			Assert.assertTrue(eleCount.size() == 1);
			
			authCodePage.getContinueButton().click();
			authCodePage.getContinueButtonToServiceSummaryPage().click();
			
//			authCodePage.getNextButtonToMicollabConfigPage().click();
			pmExtension.getNextButton().click();
			new SelectDropDownValue().selectByVisibleText(authCodePage.getMicollabRoleDropDown(), extData[7]);
			driver.switchTo().alert().accept();
			pmExtension.getApplyButton().click();
			
			WebDriverWait wait = new WebDriverWait(driver, 100);
//			wait.until(ExpectedConditions.textToBe(By.className("responseMessage"), "  Import operation successful for:"));
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(@class,'responseMessage')]")));
			Assert.assertEquals(driver.findElement(By.xpath("//span[contains(@class,'responseMessage')]")).getText().trim(), "Add operation successful for:");
			
			
//			Assert.assertEquals(pmUser.getResponseMessage().getText().trim(), "Add operation successful for:");
			pmUser.getDoneButton().click();
//			pmUser.getDoneButton().click();
			
			pmUser.getUserSearchTextBox().clear();
			pmUser.setUserSearchTextBox(testData[0]);
			pmUser.getOnViewRangeButton().click();
			
			List<WebElement> eles = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("(//td[contains(text(),'"+testData[0]+"')])[1]//following-sibling::"
					+ "td[contains(text(),'"+extData[1]+" / SN')]"
					+ "//following-sibling::td[contains(text(),'Micollab')]"
					+ "//following-sibling::td[contains(text(),'"+extData[7]+"')]"));
			Assert.assertTrue(eles.size() == 1);
			
			pmMainPage.getLogoutLink().click();
			driver.get(ipData.getData(2, 0));
			Thread.sleep(1000);
			micoLogin.micollab_login(micoCredentials[0], micoCredentials[1]);
			driver.switchTo().frame(serverManagerPage.getInToNavigationFrame());
			serverManagerPage.getUsersAndServicesLink().click();
			driver.switchTo().defaultContent();
			driver.switchTo().frame(serverManagerPage.getMainFrame());
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='basicSearchTerm']")));
			serverManagerPage.getUserSearchTextBox().sendKeys(testData[0]);
			serverManagerPage.getSearchButton().click();
			Thread.sleep(1000);
			List<WebElement> phNums = driver.findElements(By.xpath("(//a[contains(text(),'"+testData[0]+"')]//parent::span//parent::td//following-sibling::td)[2]/span"));
			
			Assert.assertEquals(phNums.get(0).getText().trim(), extData[1]);
			Assert.assertEquals(phNums.get(1).getText().trim(), testData[5]);
			Assert.assertEquals(phNums.get(2).getText().trim(), testData[6]);
			Assert.assertEquals(phNums.get(3).getText().trim(), testData[7]);
			Assert.assertEquals(phNums.get(4).getText().trim(), testData[8]);
			
			driver.findElement(By.xpath("//a[contains(text(),'"+testData[0]+"')]")).click();
			Thread.sleep(1000);
			//System.out.println("First name is :"+serverManagerPage.getFirstNameTextBox().getText().trim());
			//Assert.assertEquals(serverManagerPage.getFirstNameTextBox().getAttribute("value").trim(), testData[0]);
			Assert.assertEquals(serverManagerPage.getLastNameTextBox().getAttribute("value").trim(), testData[0]);
			Assert.assertEquals(serverManagerPage.getEmailAddress().getAttribute("value").trim(), testData[2]);
			serverManagerPage.getPhoneTab().click();
			
			List<WebElement> homePhone1 = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//input[@value='Home Phone']"
					+ "//..//..//following-sibling::tr/td[2]/input[@value='"+testData[5]+"']"));
			List<WebElement> homePhone2 = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//input[@value='Home Phone 2']"
					+ "//..//..//following-sibling::tr/td[2]/input[@value='"+testData[6]+"']"));
			List<WebElement> mobNumber1 = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//input[@value='Mobile Number']"
					+ "//..//..//following-sibling::tr/td[2]/input[@value='"+testData[7]+"']"));
			List<WebElement> mobNumber2 = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//input[@value='Mobile Number 2']"
					+ "//..//..//following-sibling::tr/td[2]/input[@value='"+testData[8]+"']"));
			List<WebElement> extNumber = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("(//input[@value='"+extData[1]+"'])[1]"));
			Assert.assertTrue(homePhone1.size()==1 && homePhone2.size()==1 && mobNumber1.size()==1 && 
					mobNumber2.size()==1 && extNumber.size()==1);
			
//			JavascriptExecutor js = (JavascriptExecutor)driver;
//			js.executeScript("arguments[0].scrollIntoView(true);", serverManagerPage.getSipPassword());
//			Thread.sleep(5000);
//			Assert.assertEquals(serverManagerPage.getSipPassword().getAttribute("value").trim(), extData[5]);
//			Assert.assertEquals(serverManagerPage.getSipConfirmPassword().getAttribute("value").trim(), extData[5]);
			
//			serverManagerPage.getMicollabClientTab().click();
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//a[text()='MiCollab Client'])[2]"))).click();
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//select[@name='ucaService.featureProfileID']/option[@selected='selected']")));
			String assignedRole = driver.findElement(By.xpath("//select[@name='ucaService.featureProfileID']/option[@selected='selected']")).getText().trim();
			String dskPhoneExt = driver.findElement(By.xpath("//select[@id='UCADeskphoneSelect']/option[@selected='selected']")).getText().trim();
			String mailBoxNum = driver.findElement(By.xpath("//select[@id='ucaMailboxSelect']/option[@selected='selected']")).getText().trim();
			
			Assert.assertEquals(assignedRole, extData[7]);
			Assert.assertTrue(dskPhoneExt.contains(extData[1]));
			Assert.assertEquals(mailBoxNum, extData[1]);
			
			serverManagerPage.getCancelButton().click();
			List<WebElement> role = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//img[@src='images/g_check_000.gif']"));
			Assert.assertTrue(role.size()==2);
			
			
			driver.switchTo().defaultContent();
			driver.switchTo().frame(serverManagerPage.getHeaderFrame());
			Thread.sleep(2000);
			serverManagerPage.getLogoutLink().click();
			
			//////////////////////////////////////change the role now////////////////////////////////////
			
			driver.get(ipData.getData(0, 0));
			pmLoginPage.PM_Login(credentials[0], credentials[1]);
			
			pmMainPage.getUsers().click();
			pmUsers.getUser().click();
			
			pmUser.getUserSearchTextBox().clear();
			pmUser.setUserSearchTextBox(extData[1]);
			pmUser.getOnViewRangeButton().click();
			
			driver.findElement(By.xpath("(//td[contains(text(),'"+testData[0]+"')])[1]"
					+ "//following-sibling::td[contains(text(),'"+extData[1]+" ')]//preceding-sibling::td[25]")).click();
			serverManagerPage.getMicollabConfigTab().click();
			new SelectDropDownValue().selectByVisibleText(authCodePage.getMicollabRoleDropDown(), "Basic User");
			driver.switchTo().alert().accept();
			pmExtension.getEditPageApplyButton().click();
			
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(@class,'responseMessage')]")));
			Assert.assertEquals(driver.findElement(By.xpath("//span[contains(@class,'responseMessage')]")).getText().trim(), "Change operation successful for:");
			
			
//			Assert.assertEquals(pmUser.getResponseMessage().getText().trim(), "Add operation successful for:");
			pmUser.getDoneButton().click();
//			pmUser.getDoneButton().click();
			
			pmUser.getUserSearchTextBox().clear();
			pmUser.setUserSearchTextBox(testData[0]);
			pmUser.getOnViewRangeButton().click();
			
			List<WebElement> eles1 = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("(//td[contains(text(),'"+testData[0]+"')])[1]//following-sibling::"
					+ "td[contains(text(),'"+extData[1]+" / SN')]"
					+ "//following-sibling::td[contains(text(),'Micollab')]"
					+ "//following-sibling::td[contains(text(),'Basic User')]"));
			Assert.assertTrue(eles.size() == 1);
			pmMainPage.getLogoutLink().click();
			
			///////////////////Again verify in Micollab Now///////////////////////
//			pmMainPage.getLogoutLink().click();
			driver.get(ipData.getData(2, 0));
			micoLogin.micollab_login(micoCredentials[0], micoCredentials[1]);
			driver.switchTo().frame(serverManagerPage.getInToNavigationFrame());
			serverManagerPage.getUsersAndServicesLink().click();
			driver.switchTo().defaultContent();
			driver.switchTo().frame(serverManagerPage.getMainFrame());
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='basicSearchTerm']")));
			serverManagerPage.getUserSearchTextBox().sendKeys(testData[0]);
			serverManagerPage.getSearchButton().click();
			Thread.sleep(1000);
			List<WebElement> phNumsEdit = driver.findElements(By.xpath("(//a[contains(text(),'"+testData[0]+"')]//parent::span//parent::td//following-sibling::td)[2]/span"));
			
			Assert.assertEquals(phNumsEdit.get(0).getText().trim(), extData[1]);
			Assert.assertEquals(phNumsEdit.get(1).getText().trim(), testData[5]);
			Assert.assertEquals(phNumsEdit.get(2).getText().trim(), testData[6]);
			Assert.assertEquals(phNumsEdit.get(3).getText().trim(), testData[7]);
			Assert.assertEquals(phNumsEdit.get(4).getText().trim(), testData[8]);
			
			driver.findElement(By.xpath("//a[contains(text(),'"+testData[0]+"')]")).click();
			Thread.sleep(1000);
			//System.out.println("First name is :"+serverManagerPage.getFirstNameTextBox().getText().trim());
			//Assert.assertEquals(serverManagerPage.getFirstNameTextBox().getAttribute("value").trim(), testData[0]);
			Assert.assertEquals(serverManagerPage.getLastNameTextBox().getAttribute("value").trim(), testData[0]);
			Assert.assertEquals(serverManagerPage.getEmailAddress().getAttribute("value").trim(), testData[2]);
			serverManagerPage.getPhoneTab().click();
			
			List<WebElement> homePhoneInEdit = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//input[@value='Home Phone']"
					+ "//..//..//following-sibling::tr/td[2]/input[@value='"+testData[5]+"']"));
			List<WebElement> homePhone2InEdit = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//input[@value='Home Phone 2']"
					+ "//..//..//following-sibling::tr/td[2]/input[@value='"+testData[6]+"']"));
			List<WebElement> mobNumber1InEdit = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//input[@value='Mobile Number']"
					+ "//..//..//following-sibling::tr/td[2]/input[@value='"+testData[7]+"']"));
			List<WebElement> mobNumber2InEdit = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//input[@value='Mobile Number 2']"
					+ "//..//..//following-sibling::tr/td[2]/input[@value='"+testData[8]+"']"));
			List<WebElement> extNumberInEdit = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//input[@value='"+extData[1]+"']"));
			Assert.assertTrue(homePhoneInEdit.size()==1 && homePhone2InEdit.size()==1 && mobNumber1InEdit.size()==1 && 
					mobNumber2InEdit.size()==1 && extNumberInEdit.size()==1);
//			Thread.sleep(1000);
//			JavascriptExecutor js = (JavascriptExecutor)driver;
//			js.executeScript("arguments[0].scrollIntoView(true);", serverManagerPage.getSipPassword());
//			Thread.sleep(5000);
//			Assert.assertEquals(serverManagerPage.getSipPassword().getAttribute("value").trim(), extData[5]);
//			Assert.assertEquals(serverManagerPage.getSipConfirmPassword().getAttribute("value").trim(), extData[5]);
			
//			serverManagerPage.getMicollabClientTab().click();
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//a[text()='MiCollab Client'])[2]"))).click();
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//select[@name='ucaService.featureProfileID']/option[@selected='selected']")));
			String assignedRoleEdit = driver.findElement(By.xpath("//select[@name='ucaService.featureProfileID']/option[@selected='selected']")).getText().trim();
//			String dskPhoneExtEdit = driver.findElement(By.xpath("//select[@id='UCADeskphoneSelect']/option[@selected='selected']")).getText().trim();
//			String mailBoxNumEdit = driver.findElement(By.xpath("//select[@id='ucaMailboxSelect']/option[@selected='selected']")).getText().trim();
			
			Assert.assertEquals(assignedRoleEdit, "Basic User");
//			Assert.assertTrue(dskPhoneExtEdit.contains(extData[1]));
//			Assert.assertEquals(mailBoxNumEdit, extData[1]);
			
			
			serverManagerPage.getCancelButton().click();
			List<WebElement> roleEdit = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//img[@src='images/g_check_000.gif']"));
			Assert.assertTrue(roleEdit.size()==1);
			
			driver.switchTo().defaultContent();
			driver.switchTo().frame(serverManagerPage.getHeaderFrame());
			Thread.sleep(2000);
			serverManagerPage.getLogoutLink().click();
			
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
			try
			{
				new CleanUP(driver).deleteUser(driver, ipData, credentials, testData[0]);
				list.clear();
				list.add(extData[8]);
				list.add(extData[9]);
				list.add(extData[10]);
				new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
			}
			catch(Exception e)
			{
					new Take_Screenshot().get_Screenshot(driver, method.getName());
					throw e;
			}
		}
	}
	
	
	
	@Test
	public void test_edit_micollab_Role_from_entry_to_premium(Method method) throws Exception
	{
		authCodePage = new PM_Auth_Code_Page(driver);
		micoLogin = new Micollab_Login_Page(driver);
		serverManagerPage = new Micollab_Server_Manager_Page(driver);
		pmLoginPage = new PM_Login_Page(driver);
		pmMainPage = new PM_Main_Page(driver);
		pmUser = new PM_User(driver);
		pmUsers = new PM_Users(driver);
		pmExtension = new Extension(driver);
		
		
		list = new ArrayList<String>();
		String[] credentials = loginData.getData("test_pm_valid_login", 1);
		String[] testData = pmTests.getData(method.getName(), 1);
		String[] extData = pmTests.getData(method.getName(), 3);
		String[] micoCredentials = loginData.getData("test_micollab_login", 1);
		wait = new WebDriverWait(driver, 20);
		try 
		{
			pmTests.checkTestStatus(method.getName());
			pmUser = new PM_User(driver);
			pmMainPage = new PM_Main_Page(driver);
			list.add(extData[0]);
			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
			new ReusableUnits(driver).navigateUserToServiceSummaryPage(driver, method.getName(), ipData, loginData, pmTests);
			
			
			pmUser.getCreateAndAssignExtensionToUser().click();
			new SelectDropDownValue().selectByVisibleText(pmExtension.getExtensionType(), "IP");
			pmExtension.getNextButton().click();
			
			//Provide extension details.
			new SelectDropDownValue().selectByVisibleText(pmExtension.getSelectExtensionsRange(), extData[1]);
			String version = new GetMxoneVersionNumber(driver).getMxoneVersionNumber(driver);
			System.out.println(version);
			int ver = Integer.parseInt(version);
			System.out.println(ver);
			if(ver >= 72000)
			{
				pmExtension.setSingleExtensionValue(extData[1]);
			}
			else
			{
				new SelectDropDownValue().selectByVisibleText(pmExtension.getSingleExtensionDropDown(), extData[1]);
			}
			new SelectDropDownValue().selectByIndex(pmExtension.getMyCSPNameDropDown(),0);
			new SelectDropDownValue().selectByVisibleText(pmExtension.getServerDropDown(), "1");
			pmExtension.getFirstName().clear();
			pmExtension.getLastName().clear();
			pmExtension.setFirstName(extData[2]);
			pmExtension.setLastName(extData[3]);
			new SelectDropDownValue().selectByVisibleText(pmExtension.getPhoneTypeDropDown(), extData[4]);
			
			pmExtension.getAuthCodeButton().click();
			authCodePage.getAuthCodeAddButton().click();
			authCodePage.setAuthCodeTextBox(extData[5]);
			new SelectDropDownValue().selectByValue(authCodePage.gethashTypeDropDown(), extData[6]);
			authCodePage.setCilCode(extData[5]);
			authCodePage.getAuthPageApplyButton().click();
			
			List<WebElement> eleCount = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//td[contains(text(),'CLEARTEXT')]"));
			Assert.assertTrue(eleCount.size() == 1);
			
			authCodePage.getContinueButton().click();
			authCodePage.getContinueButtonToServiceSummaryPage().click();
			
//			authCodePage.getNextButtonToMicollabConfigPage().click();
			pmExtension.getNextButton().click();
			new SelectDropDownValue().selectByVisibleText(authCodePage.getMicollabRoleDropDown(), extData[7]);
			driver.switchTo().alert().accept();
			pmExtension.getApplyButton().click();
			
			WebDriverWait wait = new WebDriverWait(driver, 100);
//			wait.until(ExpectedConditions.textToBe(By.className("responseMessage"), "  Import operation successful for:"));
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(@class,'responseMessage')]")));
			Assert.assertEquals(driver.findElement(By.xpath("//span[contains(@class,'responseMessage')]")).getText().trim(), "Add operation successful for:");
			
			
//			Assert.assertEquals(pmUser.getResponseMessage().getText().trim(), "Add operation successful for:");
			pmUser.getDoneButton().click();
//			pmUser.getDoneButton().click();
			
			pmUser.getUserSearchTextBox().clear();
			pmUser.setUserSearchTextBox(testData[0]);
			pmUser.getOnViewRangeButton().click();
			
			List<WebElement> eles = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("(//td[contains(text(),'"+testData[0]+"')])[1]//following-sibling::"
					+ "td[contains(text(),'"+extData[1]+" / SN')]"
					+ "//following-sibling::td[contains(text(),'Micollab')]"
					+ "//following-sibling::td[contains(text(),'"+extData[7]+"')]"));
			Assert.assertTrue(eles.size() == 1);
			
			pmMainPage.getLogoutLink().click();
			driver.get(ipData.getData(2, 0));
			Thread.sleep(1000);
			micoLogin.micollab_login(micoCredentials[0], micoCredentials[1]);
			driver.switchTo().frame(serverManagerPage.getInToNavigationFrame());
			serverManagerPage.getUsersAndServicesLink().click();
			driver.switchTo().defaultContent();
			driver.switchTo().frame(serverManagerPage.getMainFrame());
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='basicSearchTerm']")));
			serverManagerPage.getUserSearchTextBox().sendKeys(testData[0]);
			serverManagerPage.getSearchButton().click();
			Thread.sleep(1000);
			List<WebElement> phNums = driver.findElements(By.xpath("(//a[contains(text(),'"+testData[0]+"')]//parent::span//parent::td//following-sibling::td)[2]/span"));
			
			Assert.assertEquals(phNums.get(0).getText().trim(), extData[1]);
			Assert.assertEquals(phNums.get(1).getText().trim(), testData[5]);
			Assert.assertEquals(phNums.get(2).getText().trim(), testData[6]);
			Assert.assertEquals(phNums.get(3).getText().trim(), testData[7]);
			Assert.assertEquals(phNums.get(4).getText().trim(), testData[8]);
			
			driver.findElement(By.xpath("//a[contains(text(),'"+testData[0]+"')]")).click();
			Thread.sleep(1000);
			//System.out.println("First name is :"+serverManagerPage.getFirstNameTextBox().getText().trim());
			//Assert.assertEquals(serverManagerPage.getFirstNameTextBox().getAttribute("value").trim(), testData[0]);
			Assert.assertEquals(serverManagerPage.getLastNameTextBox().getAttribute("value").trim(), testData[0]);
			Assert.assertEquals(serverManagerPage.getEmailAddress().getAttribute("value").trim(), testData[2]);
			serverManagerPage.getPhoneTab().click();
			
			List<WebElement> homePhone1 = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//input[@value='Home Phone']"
					+ "//..//..//following-sibling::tr/td[2]/input[@value='"+testData[5]+"']"));
			List<WebElement> homePhone2 = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//input[@value='Home Phone 2']"
					+ "//..//..//following-sibling::tr/td[2]/input[@value='"+testData[6]+"']"));
			List<WebElement> mobNumber1 = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//input[@value='Mobile Number']"
					+ "//..//..//following-sibling::tr/td[2]/input[@value='"+testData[7]+"']"));
			List<WebElement> mobNumber2 = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//input[@value='Mobile Number 2']"
					+ "//..//..//following-sibling::tr/td[2]/input[@value='"+testData[8]+"']"));
			List<WebElement> extNumber = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("(//input[@value='"+extData[1]+"'])[1]"));
			Assert.assertTrue(homePhone1.size()==1 && homePhone2.size()==1 && mobNumber1.size()==1 && 
					mobNumber2.size()==1 && extNumber.size()==1);
			
//			JavascriptExecutor js = (JavascriptExecutor)driver;
//			js.executeScript("arguments[0].scrollIntoView(true);", serverManagerPage.getSipPassword());
//			Thread.sleep(5000);
//			Assert.assertEquals(serverManagerPage.getSipPassword().getAttribute("value").trim(), extData[5]);
//			Assert.assertEquals(serverManagerPage.getSipConfirmPassword().getAttribute("value").trim(), extData[5]);
			
//			serverManagerPage.getMicollabClientTab().click();
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//a[text()='MiCollab Client'])[2]"))).click();
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//select[@name='ucaService.featureProfileID']/option[@selected='selected']")));
			String assignedRole = driver.findElement(By.xpath("//select[@name='ucaService.featureProfileID']/option[@selected='selected']")).getText().trim();
			String dskPhoneExt = driver.findElement(By.xpath("//select[@id='UCADeskphoneSelect']/option[@selected='selected']")).getText().trim();
			String mailBoxNum = driver.findElement(By.xpath("//select[@id='ucaMailboxSelect']/option[@selected='selected']")).getText().trim();
			
			Assert.assertEquals(assignedRole, extData[7]);
			Assert.assertTrue(dskPhoneExt.contains(extData[1]));
			Assert.assertEquals(mailBoxNum, extData[1]);
			
			serverManagerPage.getCancelButton().click();
			List<WebElement> role = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//img[@src='images/g_check_000.gif']"));
			Assert.assertTrue(role.size()==2);
			
			
			driver.switchTo().defaultContent();
			driver.switchTo().frame(serverManagerPage.getHeaderFrame());
			Thread.sleep(2000);
			serverManagerPage.getLogoutLink().click();
			
			//////////////////////////////////////change the role now////////////////////////////////////
			
			driver.get(ipData.getData(0, 0));
			pmLoginPage.PM_Login(credentials[0], credentials[1]);
			
			pmMainPage.getUsers().click();
			pmUsers.getUser().click();
			
			pmUser.getUserSearchTextBox().clear();
			pmUser.setUserSearchTextBox(extData[1]);
			pmUser.getOnViewRangeButton().click();
			
			driver.findElement(By.xpath("(//td[contains(text(),'"+testData[0]+"')])[1]"
					+ "//following-sibling::td[contains(text(),'"+extData[1]+" ')]//preceding-sibling::td[25]")).click();
			serverManagerPage.getMicollabConfigTab().click();
			new SelectDropDownValue().selectByVisibleText(authCodePage.getMicollabRoleDropDown(), "UCC (V4.0) Premium");
			driver.switchTo().alert().accept();
			pmExtension.getEditPageApplyButton().click();
			
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(@class,'responseMessage')]")));
			Assert.assertEquals(driver.findElement(By.xpath("//span[contains(@class,'responseMessage')]")).getText().trim(), "Change operation successful for:");
			
			
//			Assert.assertEquals(pmUser.getResponseMessage().getText().trim(), "Add operation successful for:");
			pmUser.getDoneButton().click();
//			pmUser.getDoneButton().click();
			
			pmUser.getUserSearchTextBox().clear();
			pmUser.setUserSearchTextBox(testData[0]);
			pmUser.getOnViewRangeButton().click();
			
			List<WebElement> eles1 = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("(//td[contains(text(),'"+testData[0]+"')])[1]//following-sibling::"
					+ "td[contains(text(),'"+extData[1]+" / SN')]"
					+ "//following-sibling::td[contains(text(),'Micollab')]"
					+ "//following-sibling::td[contains(text(),'UCC (V4.0) Premium')]"));
			Assert.assertTrue(eles.size() == 1);
			pmMainPage.getLogoutLink().click();
			
			///////////////////Again verify in Micollab Now///////////////////////
//			pmMainPage.getLogoutLink().click();
			driver.get(ipData.getData(2, 0));
			micoLogin.micollab_login(micoCredentials[0], micoCredentials[1]);
			driver.switchTo().frame(serverManagerPage.getInToNavigationFrame());
			serverManagerPage.getUsersAndServicesLink().click();
			driver.switchTo().defaultContent();
			driver.switchTo().frame(serverManagerPage.getMainFrame());
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='basicSearchTerm']")));
			serverManagerPage.getUserSearchTextBox().sendKeys(testData[0]);
			serverManagerPage.getSearchButton().click();
			Thread.sleep(1000);
			List<WebElement> phNumsEdit = driver.findElements(By.xpath("(//a[contains(text(),'"+testData[0]+"')]//parent::span//parent::td//following-sibling::td)[2]/span"));
			
			Assert.assertEquals(phNumsEdit.get(0).getText().trim(), extData[1]);
			Assert.assertEquals(phNumsEdit.get(1).getText().trim(), testData[5]);
			Assert.assertEquals(phNumsEdit.get(2).getText().trim(), testData[6]);
			Assert.assertEquals(phNumsEdit.get(3).getText().trim(), testData[7]);
			Assert.assertEquals(phNumsEdit.get(4).getText().trim(), testData[8]);
			
			driver.findElement(By.xpath("//a[contains(text(),'"+testData[0]+"')]")).click();
			Thread.sleep(1000);
			//System.out.println("First name is :"+serverManagerPage.getFirstNameTextBox().getText().trim());
			//Assert.assertEquals(serverManagerPage.getFirstNameTextBox().getAttribute("value").trim(), testData[0]);
			Assert.assertEquals(serverManagerPage.getLastNameTextBox().getAttribute("value").trim(), testData[0]);
			Assert.assertEquals(serverManagerPage.getEmailAddress().getAttribute("value").trim(), testData[2]);
			serverManagerPage.getPhoneTab().click();
			
			List<WebElement> homePhoneInEdit = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//input[@value='Home Phone']"
					+ "//..//..//following-sibling::tr/td[2]/input[@value='"+testData[5]+"']"));
			List<WebElement> homePhone2InEdit = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//input[@value='Home Phone 2']"
					+ "//..//..//following-sibling::tr/td[2]/input[@value='"+testData[6]+"']"));
			List<WebElement> mobNumber1InEdit = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//input[@value='Mobile Number']"
					+ "//..//..//following-sibling::tr/td[2]/input[@value='"+testData[7]+"']"));
			List<WebElement> mobNumber2InEdit = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//input[@value='Mobile Number 2']"
					+ "//..//..//following-sibling::tr/td[2]/input[@value='"+testData[8]+"']"));
			List<WebElement> extNumberInEdit = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("(//input[@value='"+extData[1]+"'])[1]"));
			Assert.assertTrue(homePhoneInEdit.size()==1 && homePhone2InEdit.size()==1 && mobNumber1InEdit.size()==1 && 
					mobNumber2InEdit.size()==1 && extNumberInEdit.size()==1);
//			Thread.sleep(1000);
//			JavascriptExecutor js = (JavascriptExecutor)driver;
//			js.executeScript("arguments[0].scrollIntoView(true);", serverManagerPage.getSipPassword());
//			Thread.sleep(5000);
//			Assert.assertEquals(serverManagerPage.getSipPassword().getAttribute("value").trim(), extData[5]);
//			Assert.assertEquals(serverManagerPage.getSipConfirmPassword().getAttribute("value").trim(), extData[5]);
			
//			serverManagerPage.getMicollabClientTab().click();
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//a[text()='MiCollab Client'])[2]"))).click();
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//select[@name='ucaService.featureProfileID']/option[@selected='selected']")));
			String assignedRoleEdit = driver.findElement(By.xpath("//select[@name='ucaService.featureProfileID']/option[@selected='selected']")).getText().trim();
			String dskPhoneExtEdit = driver.findElement(By.xpath("//select[@id='UCADeskphoneSelect']/option[@selected='selected']")).getText().trim();
			String mailBoxNumEdit = driver.findElement(By.xpath("//select[@id='ucaMailboxSelect']/option[@selected='selected']")).getText().trim();
			
			Assert.assertEquals(assignedRoleEdit, "UCC (V4.0) Premium");
			Assert.assertTrue(dskPhoneExtEdit.contains(extData[1]));
			Assert.assertEquals(mailBoxNumEdit, extData[1]);
			
			
			serverManagerPage.getCancelButton().click();
			List<WebElement> roleEdit = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//img[@src='images/g_check_000.gif']"));
			Assert.assertTrue(roleEdit.size()==4);
			
			driver.switchTo().defaultContent();
			driver.switchTo().frame(serverManagerPage.getHeaderFrame());
			Thread.sleep(2000);
			serverManagerPage.getLogoutLink().click();
			
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
			try
			{
				new CleanUP(driver).deleteUser(driver, ipData, credentials, testData[0]);
				list.clear();
				list.add(extData[8]);
				list.add(extData[9]);
				list.add(extData[10]);
				new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
			}
			catch(Exception e)
			{
					new Take_Screenshot().get_Screenshot(driver, method.getName());
					throw e;
			}
		}
	}
	
	
	@Test
	public void test_edit_micollab_Role_from_entry_to_standard(Method method) throws Exception
	{
		authCodePage = new PM_Auth_Code_Page(driver);
		micoLogin = new Micollab_Login_Page(driver);
		serverManagerPage = new Micollab_Server_Manager_Page(driver);
		pmLoginPage = new PM_Login_Page(driver);
		pmMainPage = new PM_Main_Page(driver);
		pmUser = new PM_User(driver);
		pmUsers = new PM_Users(driver);
		pmExtension = new Extension(driver);
		
		
		list = new ArrayList<String>();
		String[] credentials = loginData.getData("test_pm_valid_login", 1);
		String[] testData = pmTests.getData(method.getName(), 1);
		String[] extData = pmTests.getData(method.getName(), 3);
		String[] micoCredentials = loginData.getData("test_micollab_login", 1);
		wait = new WebDriverWait(driver, 20);
		try 
		{
			pmTests.checkTestStatus(method.getName());
			pmUser = new PM_User(driver);
			pmMainPage = new PM_Main_Page(driver);
			list.add(extData[0]);
			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
			new ReusableUnits(driver).navigateUserToServiceSummaryPage(driver, method.getName(), ipData, loginData, pmTests);
			
			
			pmUser.getCreateAndAssignExtensionToUser().click();
			new SelectDropDownValue().selectByVisibleText(pmExtension.getExtensionType(), "IP");
			pmExtension.getNextButton().click();
			
			//Provide extension details.
			new SelectDropDownValue().selectByVisibleText(pmExtension.getSelectExtensionsRange(), extData[1]);
			String version = new GetMxoneVersionNumber(driver).getMxoneVersionNumber(driver);
			System.out.println(version);
			int ver = Integer.parseInt(version);
			System.out.println(ver);
			if(ver >= 72000)
			{
				pmExtension.setSingleExtensionValue(extData[1]);
			}
			else
			{
				new SelectDropDownValue().selectByVisibleText(pmExtension.getSingleExtensionDropDown(), extData[1]);
			}
			new SelectDropDownValue().selectByIndex(pmExtension.getMyCSPNameDropDown(),0);
			new SelectDropDownValue().selectByVisibleText(pmExtension.getServerDropDown(), "1");
			pmExtension.getFirstName().clear();
			pmExtension.getLastName().clear();
			pmExtension.setFirstName(extData[2]);
			pmExtension.setLastName(extData[3]);
			new SelectDropDownValue().selectByVisibleText(pmExtension.getPhoneTypeDropDown(), extData[4]);
			
			pmExtension.getAuthCodeButton().click();
			authCodePage.getAuthCodeAddButton().click();
			authCodePage.setAuthCodeTextBox(extData[5]);
			new SelectDropDownValue().selectByValue(authCodePage.gethashTypeDropDown(), extData[6]);
			authCodePage.setCilCode(extData[5]);
			authCodePage.getAuthPageApplyButton().click();
			
			List<WebElement> eleCount = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//td[contains(text(),'CLEARTEXT')]"));
			Assert.assertTrue(eleCount.size() == 1);
			
			authCodePage.getContinueButton().click();
			authCodePage.getContinueButtonToServiceSummaryPage().click();
			
//			authCodePage.getNextButtonToMicollabConfigPage().click();
			pmExtension.getNextButton().click();
			new SelectDropDownValue().selectByVisibleText(authCodePage.getMicollabRoleDropDown(), extData[7]);
			driver.switchTo().alert().accept();
			pmExtension.getApplyButton().click();
			
			WebDriverWait wait = new WebDriverWait(driver, 100);
//			wait.until(ExpectedConditions.textToBe(By.className("responseMessage"), "  Import operation successful for:"));
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(@class,'responseMessage')]")));
			Assert.assertEquals(driver.findElement(By.xpath("//span[contains(@class,'responseMessage')]")).getText().trim(), "Add operation successful for:");
			
			
//			Assert.assertEquals(pmUser.getResponseMessage().getText().trim(), "Add operation successful for:");
			pmUser.getDoneButton().click();
//			pmUser.getDoneButton().click();
			
			pmUser.getUserSearchTextBox().clear();
			pmUser.setUserSearchTextBox(testData[0]);
			pmUser.getOnViewRangeButton().click();
			
			List<WebElement> eles = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("(//td[contains(text(),'"+testData[0]+"')])[1]//following-sibling::"
					+ "td[contains(text(),'"+extData[1]+" / SN')]"
					+ "//following-sibling::td[contains(text(),'Micollab')]"
					+ "//following-sibling::td[contains(text(),'"+extData[7]+"')]"));
			Assert.assertTrue(eles.size() == 1);
			
			pmMainPage.getLogoutLink().click();
			driver.get(ipData.getData(2, 0));
			Thread.sleep(1000);
			micoLogin.micollab_login(micoCredentials[0], micoCredentials[1]);
			driver.switchTo().frame(serverManagerPage.getInToNavigationFrame());
			serverManagerPage.getUsersAndServicesLink().click();
			driver.switchTo().defaultContent();
			driver.switchTo().frame(serverManagerPage.getMainFrame());
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='basicSearchTerm']")));
			serverManagerPage.getUserSearchTextBox().sendKeys(testData[0]);
			serverManagerPage.getSearchButton().click();
			Thread.sleep(1000);
			List<WebElement> phNums = driver.findElements(By.xpath("(//a[contains(text(),'"+testData[0]+"')]//parent::span//parent::td//following-sibling::td)[2]/span"));
			
			Assert.assertEquals(phNums.get(0).getText().trim(), extData[1]);
			Assert.assertEquals(phNums.get(1).getText().trim(), testData[5]);
			Assert.assertEquals(phNums.get(2).getText().trim(), testData[6]);
			Assert.assertEquals(phNums.get(3).getText().trim(), testData[7]);
			Assert.assertEquals(phNums.get(4).getText().trim(), testData[8]);
			
			driver.findElement(By.xpath("//a[contains(text(),'"+testData[0]+"')]")).click();
			Thread.sleep(1000);
			//System.out.println("First name is :"+serverManagerPage.getFirstNameTextBox().getText().trim());
			//Assert.assertEquals(serverManagerPage.getFirstNameTextBox().getAttribute("value").trim(), testData[0]);
			Assert.assertEquals(serverManagerPage.getLastNameTextBox().getAttribute("value").trim(), testData[0]);
			Assert.assertEquals(serverManagerPage.getEmailAddress().getAttribute("value").trim(), testData[2]);
			serverManagerPage.getPhoneTab().click();
			
			List<WebElement> homePhone1 = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//input[@value='Home Phone']"
					+ "//..//..//following-sibling::tr/td[2]/input[@value='"+testData[5]+"']"));
			List<WebElement> homePhone2 = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//input[@value='Home Phone 2']"
					+ "//..//..//following-sibling::tr/td[2]/input[@value='"+testData[6]+"']"));
			List<WebElement> mobNumber1 = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//input[@value='Mobile Number']"
					+ "//..//..//following-sibling::tr/td[2]/input[@value='"+testData[7]+"']"));
			List<WebElement> mobNumber2 = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//input[@value='Mobile Number 2']"
					+ "//..//..//following-sibling::tr/td[2]/input[@value='"+testData[8]+"']"));
			List<WebElement> extNumber = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("(//input[@value='"+extData[1]+"'])[1]"));
			Assert.assertTrue(homePhone1.size()==1 && homePhone2.size()==1 && mobNumber1.size()==1 && 
					mobNumber2.size()==1 && extNumber.size()==1);
			
//			JavascriptExecutor js = (JavascriptExecutor)driver;
//			js.executeScript("arguments[0].scrollIntoView(true);", serverManagerPage.getSipPassword());
//			Thread.sleep(5000);
//			Assert.assertEquals(serverManagerPage.getSipPassword().getAttribute("value").trim(), extData[5]);
//			Assert.assertEquals(serverManagerPage.getSipConfirmPassword().getAttribute("value").trim(), extData[5]);
			
//			serverManagerPage.getMicollabClientTab().click();
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//a[text()='MiCollab Client'])[2]"))).click();
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//select[@name='ucaService.featureProfileID']/option[@selected='selected']")));
			String assignedRole = driver.findElement(By.xpath("//select[@name='ucaService.featureProfileID']/option[@selected='selected']")).getText().trim();
			String dskPhoneExt = driver.findElement(By.xpath("//select[@id='UCADeskphoneSelect']/option[@selected='selected']")).getText().trim();
			String mailBoxNum = driver.findElement(By.xpath("//select[@id='ucaMailboxSelect']/option[@selected='selected']")).getText().trim();
			
			Assert.assertEquals(assignedRole, extData[7]);
			Assert.assertTrue(dskPhoneExt.contains(extData[1]));
			Assert.assertEquals(mailBoxNum, extData[1]);
			
			serverManagerPage.getCancelButton().click();
			List<WebElement> role = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//img[@src='images/g_check_000.gif']"));
			Assert.assertTrue(role.size()==2);
			
			
			driver.switchTo().defaultContent();
			driver.switchTo().frame(serverManagerPage.getHeaderFrame());
			Thread.sleep(2000);
			serverManagerPage.getLogoutLink().click();
			
			//////////////////////////////////////change the role now////////////////////////////////////
			
			driver.get(ipData.getData(0, 0));
			pmLoginPage.PM_Login(credentials[0], credentials[1]);
			
			pmMainPage.getUsers().click();
			pmUsers.getUser().click();
			
			pmUser.getUserSearchTextBox().clear();
			pmUser.setUserSearchTextBox(extData[1]);
			pmUser.getOnViewRangeButton().click();
			
			driver.findElement(By.xpath("(//td[contains(text(),'"+testData[0]+"')])[1]"
					+ "//following-sibling::td[contains(text(),'"+extData[1]+" ')]//preceding-sibling::td[25]")).click();
			serverManagerPage.getMicollabConfigTab().click();
			new SelectDropDownValue().selectByVisibleText(authCodePage.getMicollabRoleDropDown(), "UCC (V4.0) Standard");
			driver.switchTo().alert().accept();
			pmExtension.getEditPageApplyButton().click();
			
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(@class,'responseMessage')]")));
			Assert.assertEquals(driver.findElement(By.xpath("//span[contains(@class,'responseMessage')]")).getText().trim(), "Change operation successful for:");
			
			
//			Assert.assertEquals(pmUser.getResponseMessage().getText().trim(), "Add operation successful for:");
			pmUser.getDoneButton().click();
//			pmUser.getDoneButton().click();
			
			pmUser.getUserSearchTextBox().clear();
			pmUser.setUserSearchTextBox(testData[0]);
			pmUser.getOnViewRangeButton().click();
			
			List<WebElement> eles1 = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("(//td[contains(text(),'"+testData[0]+"')])[1]//following-sibling::"
					+ "td[contains(text(),'"+extData[1]+" / SN')]"
					+ "//following-sibling::td[contains(text(),'Micollab')]"
					+ "//following-sibling::td[contains(text(),'UCC (V4.0) Standard')]"));
			Assert.assertTrue(eles.size() == 1);
			pmMainPage.getLogoutLink().click();
			
			///////////////////Again verify in Micollab Now///////////////////////
//			pmMainPage.getLogoutLink().click();
			driver.get(ipData.getData(2, 0));
			micoLogin.micollab_login(micoCredentials[0], micoCredentials[1]);
			driver.switchTo().frame(serverManagerPage.getInToNavigationFrame());
			serverManagerPage.getUsersAndServicesLink().click();
			driver.switchTo().defaultContent();
			driver.switchTo().frame(serverManagerPage.getMainFrame());
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='basicSearchTerm']")));
			serverManagerPage.getUserSearchTextBox().sendKeys(testData[0]);
			serverManagerPage.getSearchButton().click();
			Thread.sleep(1000);
			List<WebElement> phNumsEdit = driver.findElements(By.xpath("(//a[contains(text(),'"+testData[0]+"')]//parent::span//parent::td//following-sibling::td)[2]/span"));
			
			Assert.assertEquals(phNumsEdit.get(0).getText().trim(), extData[1]);
			Assert.assertEquals(phNumsEdit.get(1).getText().trim(), testData[5]);
			Assert.assertEquals(phNumsEdit.get(2).getText().trim(), testData[6]);
			Assert.assertEquals(phNumsEdit.get(3).getText().trim(), testData[7]);
			Assert.assertEquals(phNumsEdit.get(4).getText().trim(), testData[8]);
			
			driver.findElement(By.xpath("//a[contains(text(),'"+testData[0]+"')]")).click();
			Thread.sleep(1000);
			//System.out.println("First name is :"+serverManagerPage.getFirstNameTextBox().getText().trim());
			//Assert.assertEquals(serverManagerPage.getFirstNameTextBox().getAttribute("value").trim(), testData[0]);
			Assert.assertEquals(serverManagerPage.getLastNameTextBox().getAttribute("value").trim(), testData[0]);
			Assert.assertEquals(serverManagerPage.getEmailAddress().getAttribute("value").trim(), testData[2]);
			serverManagerPage.getPhoneTab().click();
			
			List<WebElement> homePhoneInEdit = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//input[@value='Home Phone']"
					+ "//..//..//following-sibling::tr/td[2]/input[@value='"+testData[5]+"']"));
			List<WebElement> homePhone2InEdit = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//input[@value='Home Phone 2']"
					+ "//..//..//following-sibling::tr/td[2]/input[@value='"+testData[6]+"']"));
			List<WebElement> mobNumber1InEdit = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//input[@value='Mobile Number']"
					+ "//..//..//following-sibling::tr/td[2]/input[@value='"+testData[7]+"']"));
			List<WebElement> mobNumber2InEdit = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//input[@value='Mobile Number 2']"
					+ "//..//..//following-sibling::tr/td[2]/input[@value='"+testData[8]+"']"));
			List<WebElement> extNumberInEdit = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("(//input[@value='"+extData[1]+"'])[1]"));
			Assert.assertTrue(homePhoneInEdit.size()==1 && homePhone2InEdit.size()==1 && mobNumber1InEdit.size()==1 && 
					mobNumber2InEdit.size()==1 && extNumberInEdit.size()==1);
//			Thread.sleep(1000);
//			JavascriptExecutor js = (JavascriptExecutor)driver;
//			js.executeScript("arguments[0].scrollIntoView(true);", serverManagerPage.getSipPassword());
//			Thread.sleep(5000);
//			Assert.assertEquals(serverManagerPage.getSipPassword().getAttribute("value").trim(), extData[5]);
//			Assert.assertEquals(serverManagerPage.getSipConfirmPassword().getAttribute("value").trim(), extData[5]);
			
//			serverManagerPage.getMicollabClientTab().click();
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//a[text()='MiCollab Client'])[2]"))).click();
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//select[@name='ucaService.featureProfileID']/option[@selected='selected']")));
			String assignedRoleEdit = driver.findElement(By.xpath("//select[@name='ucaService.featureProfileID']/option[@selected='selected']")).getText().trim();
			String dskPhoneExtEdit = driver.findElement(By.xpath("//select[@id='UCADeskphoneSelect']/option[@selected='selected']")).getText().trim();
			String mailBoxNumEdit = driver.findElement(By.xpath("//select[@id='ucaMailboxSelect']/option[@selected='selected']")).getText().trim();
			
			Assert.assertEquals(assignedRoleEdit, "UCC (V4.0) Standard");
			Assert.assertTrue(dskPhoneExtEdit.contains(extData[1]));
			Assert.assertEquals(mailBoxNumEdit, extData[1]);
			
			
			serverManagerPage.getCancelButton().click();
			List<WebElement> roleEdit = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//img[@src='images/g_check_000.gif']"));
			Assert.assertTrue(roleEdit.size()==4);
			
			driver.switchTo().defaultContent();
			driver.switchTo().frame(serverManagerPage.getHeaderFrame());
			Thread.sleep(2000);
			serverManagerPage.getLogoutLink().click();
			
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
			try
			{
				new CleanUP(driver).deleteUser(driver, ipData, credentials, testData[0]);
				list.clear();
				list.add(extData[8]);
				list.add(extData[9]);
				list.add(extData[10]);
				new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
			}
			catch(Exception e)
			{
					new Take_Screenshot().get_Screenshot(driver, method.getName());
					throw e;
			}
		}
	}
	
	
	@Test
	public void test_edit_micollab_Role_from_standard_to_basic(Method method) throws Exception
	{
		authCodePage = new PM_Auth_Code_Page(driver);
		micoLogin = new Micollab_Login_Page(driver);
		serverManagerPage = new Micollab_Server_Manager_Page(driver);
		pmLoginPage = new PM_Login_Page(driver);
		pmMainPage = new PM_Main_Page(driver);
		pmUser = new PM_User(driver);
		pmUsers = new PM_Users(driver);
		pmExtension = new Extension(driver);
		
		
		list = new ArrayList<String>();
		String[] credentials = loginData.getData("test_pm_valid_login", 1);
		String[] testData = pmTests.getData(method.getName(), 1);
		String[] extData = pmTests.getData(method.getName(), 3);
		String[] micoCredentials = loginData.getData("test_micollab_login", 1);
		wait = new WebDriverWait(driver, 20);
		try 
		{
			pmTests.checkTestStatus(method.getName());
			pmUser = new PM_User(driver);
			pmMainPage = new PM_Main_Page(driver);
			list.add(extData[0]);
			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
			new ReusableUnits(driver).navigateUserToServiceSummaryPage(driver, method.getName(), ipData, loginData, pmTests);
			
			
			pmUser.getCreateAndAssignExtensionToUser().click();
			new SelectDropDownValue().selectByVisibleText(pmExtension.getExtensionType(), "IP");
			pmExtension.getNextButton().click();
			
			//Provide extension details.
			new SelectDropDownValue().selectByVisibleText(pmExtension.getSelectExtensionsRange(), extData[1]);
			String version = new GetMxoneVersionNumber(driver).getMxoneVersionNumber(driver);
			System.out.println(version);
			int ver = Integer.parseInt(version);
			System.out.println(ver);
			if(ver >= 72000)
			{
				pmExtension.setSingleExtensionValue(extData[1]);
			}
			else
			{
				new SelectDropDownValue().selectByVisibleText(pmExtension.getSingleExtensionDropDown(), extData[1]);
			}
			new SelectDropDownValue().selectByIndex(pmExtension.getMyCSPNameDropDown(),0);
			new SelectDropDownValue().selectByVisibleText(pmExtension.getServerDropDown(), "1");
			pmExtension.getFirstName().clear();
			pmExtension.getLastName().clear();
			pmExtension.setFirstName(extData[2]);
			pmExtension.setLastName(extData[3]);
			new SelectDropDownValue().selectByVisibleText(pmExtension.getPhoneTypeDropDown(), extData[4]);
			
			pmExtension.getAuthCodeButton().click();
			authCodePage.getAuthCodeAddButton().click();
			authCodePage.setAuthCodeTextBox(extData[5]);
			new SelectDropDownValue().selectByValue(authCodePage.gethashTypeDropDown(), extData[6]);
			authCodePage.setCilCode(extData[5]);
			authCodePage.getAuthPageApplyButton().click();
			
			List<WebElement> eleCount = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//td[contains(text(),'CLEARTEXT')]"));
			Assert.assertTrue(eleCount.size() == 1);
			
			authCodePage.getContinueButton().click();
			authCodePage.getContinueButtonToServiceSummaryPage().click();
			
//			authCodePage.getNextButtonToMicollabConfigPage().click();
			pmExtension.getNextButton().click();
			new SelectDropDownValue().selectByVisibleText(authCodePage.getMicollabRoleDropDown(), extData[7]);
			driver.switchTo().alert().accept();
			pmExtension.getApplyButton().click();
			
			WebDriverWait wait = new WebDriverWait(driver, 100);
//			wait.until(ExpectedConditions.textToBe(By.className("responseMessage"), "  Import operation successful for:"));
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(@class,'responseMessage')]")));
			Assert.assertEquals(driver.findElement(By.xpath("//span[contains(@class,'responseMessage')]")).getText().trim(), "Add operation successful for:");
			
			
//			Assert.assertEquals(pmUser.getResponseMessage().getText().trim(), "Add operation successful for:");
			pmUser.getDoneButton().click();
//			pmUser.getDoneButton().click();
			
			pmUser.getUserSearchTextBox().clear();
			pmUser.setUserSearchTextBox(testData[0]);
			pmUser.getOnViewRangeButton().click();
			
			List<WebElement> eles = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("(//td[contains(text(),'"+testData[0]+"')])[1]//following-sibling::"
					+ "td[contains(text(),'"+extData[1]+" / SN')]"
					+ "//following-sibling::td[contains(text(),'Micollab')]"
					+ "//following-sibling::td[contains(text(),'"+extData[7]+"')]"));
			Assert.assertTrue(eles.size() == 1);
			
			pmMainPage.getLogoutLink().click();
			driver.get(ipData.getData(2, 0));
			Thread.sleep(1000);
			micoLogin.micollab_login(micoCredentials[0], micoCredentials[1]);
			driver.switchTo().frame(serverManagerPage.getInToNavigationFrame());
			serverManagerPage.getUsersAndServicesLink().click();
			driver.switchTo().defaultContent();
			driver.switchTo().frame(serverManagerPage.getMainFrame());
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='basicSearchTerm']")));
			serverManagerPage.getUserSearchTextBox().sendKeys(testData[0]);
			serverManagerPage.getSearchButton().click();
			Thread.sleep(1000);
			List<WebElement> phNums = driver.findElements(By.xpath("(//a[contains(text(),'"+testData[0]+"')]//parent::span//parent::td//following-sibling::td)[2]/span"));
			
			Assert.assertEquals(phNums.get(0).getText().trim(), extData[1]);
			Assert.assertEquals(phNums.get(1).getText().trim(), testData[5]);
			Assert.assertEquals(phNums.get(2).getText().trim(), testData[6]);
			Assert.assertEquals(phNums.get(3).getText().trim(), testData[7]);
			Assert.assertEquals(phNums.get(4).getText().trim(), testData[8]);
			
			driver.findElement(By.xpath("//a[contains(text(),'"+testData[0]+"')]")).click();
//			wait.until(ExpectedConditions.elementToBeClickable(By.name("firstNameDisplay")));
			Thread.sleep(1000);
	//		System.out.println("First name is :"+serverManagerPage.getFirstNameTextBox().getText().trim());
		//	Assert.assertEquals(serverManagerPage.getFirstNameTextBox().getAttribute("value").trim(), testData[0]);
			Assert.assertEquals(serverManagerPage.getLastNameTextBox().getAttribute("value").trim(), testData[0]);
			Assert.assertEquals(serverManagerPage.getEmailAddress().getAttribute("value").trim(), testData[2]);
			
			wait.until(ExpectedConditions.elementToBeClickable(By.id("phones_0"))).click();
			Thread.sleep(2000);
//			serverManagerPage.getPhoneTab().click();
//			Thread.sleep(3000);
//			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@value='Home Phone']"
//					+ "//..//..//following-sibling::tr/td[2]/input[@value='"+testData[5]+"']")));
			
			List<WebElement> homePhone1 = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//input[@value='Home Phone']"
					+ "//..//..//following-sibling::tr/td[2]/input[@value='"+testData[5]+"']"));
			List<WebElement> homePhone2 = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//input[@value='Home Phone 2']"
					+ "//..//..//following-sibling::tr/td[2]/input[@value='"+testData[6]+"']"));
			List<WebElement> mobNumber1 = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//input[@value='Mobile Number']"
					+ "//..//..//following-sibling::tr/td[2]/input[@value='"+testData[7]+"']"));
			List<WebElement> mobNumber2 = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//input[@value='Mobile Number 2']"
					+ "//..//..//following-sibling::tr/td[2]/input[@value='"+testData[8]+"']"));
			List<WebElement> extNumber = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("(//input[@value='"+extData[1]+"'])[1]"));
			Assert.assertTrue(homePhone1.size()==1 && homePhone2.size()==1 && mobNumber1.size()==1 && 
					mobNumber2.size()==1 && extNumber.size()==1);
			
//			JavascriptExecutor js = (JavascriptExecutor)driver;
//			js.executeScript("arguments[0].scrollIntoView(true);", serverManagerPage.getSipPassword());
//			Thread.sleep(5000);
//			Assert.assertEquals(serverManagerPage.getSipPassword().getAttribute("value").trim(), extData[5]);
//			Assert.assertEquals(serverManagerPage.getSipConfirmPassword().getAttribute("value").trim(), extData[5]);
			
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//a[text()='MiCollab Client'])[2]"))).click();
//			serverManagerPage.getMicollabClientTab().click();
//			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//select[@name='ucaService.featureProfileID']/option[@selected='selected']")));
			String assignedRole = driver.findElement(By.xpath("//select[@name='ucaService.featureProfileID']/option[@selected='selected']")).getText().trim();
			String dskPhoneExt = driver.findElement(By.xpath("//select[@id='UCADeskphoneSelect']/option[@selected='selected']")).getText().trim();
			String mailBoxNum = driver.findElement(By.xpath("//select[@id='ucaMailboxSelect']/option[@selected='selected']")).getText().trim();
			
			Assert.assertEquals(assignedRole, extData[7]);
			Assert.assertTrue(dskPhoneExt.contains(extData[1]));
			Assert.assertEquals(mailBoxNum, extData[1]);
			
			serverManagerPage.getCancelButton().click();
			List<WebElement> role = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//img[@src='images/g_check_000.gif']"));
			Assert.assertTrue(role.size()==4);
			
			
			driver.switchTo().defaultContent();
			driver.switchTo().frame(serverManagerPage.getHeaderFrame());
			Thread.sleep(2000);
			serverManagerPage.getLogoutLink().click();
			
			//////////////////////////////////////change the role now////////////////////////////////////
			
			driver.get(ipData.getData(0, 0));
			pmLoginPage.PM_Login(credentials[0], credentials[1]);
			
			pmMainPage.getUsers().click();
			pmUsers.getUser().click();
			
			pmUser.getUserSearchTextBox().clear();
			pmUser.setUserSearchTextBox(extData[1]);
			pmUser.getOnViewRangeButton().click();
			
			driver.findElement(By.xpath("(//td[contains(text(),'"+testData[0]+"')])[1]"
					+ "//following-sibling::td[contains(text(),'"+extData[1]+" ')]//preceding-sibling::td[25]")).click();
			serverManagerPage.getMicollabConfigTab().click();
			new SelectDropDownValue().selectByVisibleText(authCodePage.getMicollabRoleDropDown(), "Basic User");
			driver.switchTo().alert().accept();
			pmExtension.getEditPageApplyButton().click();
			
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(@class,'responseMessage')]")));
			Assert.assertEquals(driver.findElement(By.xpath("//span[contains(@class,'responseMessage')]")).getText().trim(), "Change operation successful for:");
			
			
//			Assert.assertEquals(pmUser.getResponseMessage().getText().trim(), "Add operation successful for:");
			pmUser.getDoneButton().click();
//			pmUser.getDoneButton().click();
			
			pmUser.getUserSearchTextBox().clear();
			pmUser.setUserSearchTextBox(testData[0]);
			pmUser.getOnViewRangeButton().click();
			
			List<WebElement> eles1 = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("(//td[contains(text(),'"+testData[0]+"')])[1]//following-sibling::"
					+ "td[contains(text(),'"+extData[1]+" / SN')]"
					+ "//following-sibling::td[contains(text(),'Micollab')]"
					+ "//following-sibling::td[contains(text(),'Basic User')]"));
			Assert.assertTrue(eles.size() == 1);
			pmMainPage.getLogoutLink().click();
			
			///////////////////Again verify in Micollab Now///////////////////////
//			pmMainPage.getLogoutLink().click();
			driver.get(ipData.getData(2, 0));
			micoLogin.micollab_login(micoCredentials[0], micoCredentials[1]);
			driver.switchTo().frame(serverManagerPage.getInToNavigationFrame());
			serverManagerPage.getUsersAndServicesLink().click();
			driver.switchTo().defaultContent();
			driver.switchTo().frame(serverManagerPage.getMainFrame());
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='basicSearchTerm']")));
			serverManagerPage.getUserSearchTextBox().sendKeys(testData[0]);
			serverManagerPage.getSearchButton().click();
			Thread.sleep(1000);
			List<WebElement> phNumsEdit = driver.findElements(By.xpath("(//a[contains(text(),'"+testData[0]+"')]//parent::span//parent::td//following-sibling::td)[2]/span"));
			
			Assert.assertEquals(phNumsEdit.get(0).getText().trim(), extData[1]);
			Assert.assertEquals(phNumsEdit.get(1).getText().trim(), testData[5]);
			Assert.assertEquals(phNumsEdit.get(2).getText().trim(), testData[6]);
			Assert.assertEquals(phNumsEdit.get(3).getText().trim(), testData[7]);
			Assert.assertEquals(phNumsEdit.get(4).getText().trim(), testData[8]);
			
			driver.findElement(By.xpath("//a[contains(text(),'"+testData[0]+"')]")).click();
			Thread.sleep(1000);
			//System.out.println("First name is :"+serverManagerPage.getFirstNameTextBox().getText().trim());
			//Assert.assertEquals(serverManagerPage.getFirstNameTextBox().getAttribute("value").trim(), testData[0]);
			Assert.assertEquals(serverManagerPage.getLastNameTextBox().getAttribute("value").trim(), testData[0]);
			Assert.assertEquals(serverManagerPage.getEmailAddress().getAttribute("value").trim(), testData[2]);
			serverManagerPage.getPhoneTab().click();
			
			List<WebElement> homePhoneInEdit = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//input[@value='Home Phone']"
					+ "//..//..//following-sibling::tr/td[2]/input[@value='"+testData[5]+"']"));
			List<WebElement> homePhone2InEdit = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//input[@value='Home Phone 2']"
					+ "//..//..//following-sibling::tr/td[2]/input[@value='"+testData[6]+"']"));
			List<WebElement> mobNumber1InEdit = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//input[@value='Mobile Number']"
					+ "//..//..//following-sibling::tr/td[2]/input[@value='"+testData[7]+"']"));
			List<WebElement> mobNumber2InEdit = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//input[@value='Mobile Number 2']"
					+ "//..//..//following-sibling::tr/td[2]/input[@value='"+testData[8]+"']"));
			List<WebElement> extNumberInEdit = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//input[@value='"+extData[1]+"']"));
			Assert.assertTrue(homePhoneInEdit.size()==1 && homePhone2InEdit.size()==1 && mobNumber1InEdit.size()==1 && 
					mobNumber2InEdit.size()==1 && extNumberInEdit.size()==1);
//			Thread.sleep(1000);
//			JavascriptExecutor js = (JavascriptExecutor)driver;
//			js.executeScript("arguments[0].scrollIntoView(true);", serverManagerPage.getSipPassword());
//			Thread.sleep(5000);
//			Assert.assertEquals(serverManagerPage.getSipPassword().getAttribute("value").trim(), extData[5]);
//			Assert.assertEquals(serverManagerPage.getSipConfirmPassword().getAttribute("value").trim(), extData[5]);
			
//			serverManagerPage.getMicollabClientTab().click();
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//a[text()='MiCollab Client'])[2]"))).click();
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//select[@name='ucaService.featureProfileID']/option[@selected='selected']")));
			String assignedRoleEdit = driver.findElement(By.xpath("//select[@name='ucaService.featureProfileID']/option[@selected='selected']")).getText().trim();
			String dskPhoneExtEdit = driver.findElement(By.xpath("//select[@id='UCADeskphoneSelect']/option[@selected='selected']")).getText().trim();
			String mailBoxNumEdit = driver.findElement(By.xpath("//select[@id='ucaMailboxSelect']/option[@selected='selected']")).getText().trim();
			
			Assert.assertEquals(assignedRoleEdit, "Basic User");
			Assert.assertTrue(dskPhoneExtEdit.contains(extData[1]));
			Assert.assertEquals(mailBoxNumEdit, extData[1]);
			
			
			serverManagerPage.getCancelButton().click();
			List<WebElement> roleEdit = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//img[@src='images/g_check_000.gif']"));
			Assert.assertTrue(roleEdit.size()==1);
			
			driver.switchTo().defaultContent();
			driver.switchTo().frame(serverManagerPage.getHeaderFrame());
			Thread.sleep(2000);
			serverManagerPage.getLogoutLink().click();
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
			try
			{
				new CleanUP(driver).deleteUser(driver, ipData, credentials, testData[0]);
				list.clear();
				list.add(extData[8]);
				list.add(extData[9]);
				list.add(extData[10]);
				new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
			}
			catch(Exception e)
			{
					new Take_Screenshot().get_Screenshot(driver, method.getName());
					throw e;
			}
		}
	}
	
	
	
	@Test
	public void test_edit_micollab_Role_from_standard_to_entry(Method method) throws Exception
	{
		authCodePage = new PM_Auth_Code_Page(driver);
		micoLogin = new Micollab_Login_Page(driver);
		serverManagerPage = new Micollab_Server_Manager_Page(driver);
		pmLoginPage = new PM_Login_Page(driver);
		pmMainPage = new PM_Main_Page(driver);
		pmUser = new PM_User(driver);
		pmUsers = new PM_Users(driver);
		pmExtension = new Extension(driver);
		
		
		list = new ArrayList<String>();
		String[] credentials = loginData.getData("test_pm_valid_login", 1);
		String[] testData = pmTests.getData(method.getName(), 1);
		String[] extData = pmTests.getData(method.getName(), 3);
		String[] micoCredentials = loginData.getData("test_micollab_login", 1);
		wait = new WebDriverWait(driver, 20);
		try 
		{
			pmTests.checkTestStatus(method.getName());
			pmUser = new PM_User(driver);
			pmMainPage = new PM_Main_Page(driver);
			list.add(extData[0]);
			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
			new ReusableUnits(driver).navigateUserToServiceSummaryPage(driver, method.getName(), ipData, loginData, pmTests);
			
			
			pmUser.getCreateAndAssignExtensionToUser().click();
			new SelectDropDownValue().selectByVisibleText(pmExtension.getExtensionType(), "IP");
			pmExtension.getNextButton().click();
			
			//Provide extension details.
			new SelectDropDownValue().selectByVisibleText(pmExtension.getSelectExtensionsRange(), extData[1]);
			String version = new GetMxoneVersionNumber(driver).getMxoneVersionNumber(driver);
			System.out.println(version);
			int ver = Integer.parseInt(version);
			System.out.println(ver);
			if(ver >= 72000)
			{
				pmExtension.setSingleExtensionValue(extData[1]);
			}
			else
			{
				new SelectDropDownValue().selectByVisibleText(pmExtension.getSingleExtensionDropDown(), extData[1]);
			}
			new SelectDropDownValue().selectByIndex(pmExtension.getMyCSPNameDropDown(),0);
			new SelectDropDownValue().selectByVisibleText(pmExtension.getServerDropDown(), "1");
			pmExtension.getFirstName().clear();
			pmExtension.getLastName().clear();
			pmExtension.setFirstName(extData[2]);
			pmExtension.setLastName(extData[3]);
			new SelectDropDownValue().selectByVisibleText(pmExtension.getPhoneTypeDropDown(), extData[4]);
			
			pmExtension.getAuthCodeButton().click();
			authCodePage.getAuthCodeAddButton().click();
			authCodePage.setAuthCodeTextBox(extData[5]);
			new SelectDropDownValue().selectByValue(authCodePage.gethashTypeDropDown(), extData[6]);
			authCodePage.setCilCode(extData[5]);
			authCodePage.getAuthPageApplyButton().click();
			
			List<WebElement> eleCount = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//td[contains(text(),'CLEARTEXT')]"));
			Assert.assertTrue(eleCount.size() == 1);
			
			authCodePage.getContinueButton().click();
			authCodePage.getContinueButtonToServiceSummaryPage().click();
			
//			authCodePage.getNextButtonToMicollabConfigPage().click();
			pmExtension.getNextButton().click();
			new SelectDropDownValue().selectByVisibleText(authCodePage.getMicollabRoleDropDown(), extData[7]);
			driver.switchTo().alert().accept();
			pmExtension.getApplyButton().click();
			
			WebDriverWait wait = new WebDriverWait(driver, 100);
//			wait.until(ExpectedConditions.textToBe(By.className("responseMessage"), "  Import operation successful for:"));
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(@class,'responseMessage')]")));
			Assert.assertEquals(driver.findElement(By.xpath("//span[contains(@class,'responseMessage')]")).getText().trim(), "Add operation successful for:");
			
			
//			Assert.assertEquals(pmUser.getResponseMessage().getText().trim(), "Add operation successful for:");
			pmUser.getDoneButton().click();
//			pmUser.getDoneButton().click();
			
			pmUser.getUserSearchTextBox().clear();
			pmUser.setUserSearchTextBox(testData[0]);
			pmUser.getOnViewRangeButton().click();
			
			List<WebElement> eles = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("(//td[contains(text(),'"+testData[0]+"')])[1]//following-sibling::"
					+ "td[contains(text(),'"+extData[1]+" / SN')]"
					+ "//following-sibling::td[contains(text(),'Micollab')]"
					+ "//following-sibling::td[contains(text(),'"+extData[7]+"')]"));
			Assert.assertTrue(eles.size() == 1);
			
			pmMainPage.getLogoutLink().click();
			driver.get(ipData.getData(2, 0));
			Thread.sleep(1000);
			micoLogin.micollab_login(micoCredentials[0], micoCredentials[1]);
			driver.switchTo().frame(serverManagerPage.getInToNavigationFrame());
			serverManagerPage.getUsersAndServicesLink().click();
			driver.switchTo().defaultContent();
			driver.switchTo().frame(serverManagerPage.getMainFrame());
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='basicSearchTerm']")));
			serverManagerPage.getUserSearchTextBox().sendKeys(testData[0]);
			serverManagerPage.getSearchButton().click();
			Thread.sleep(1000);
			List<WebElement> phNums = driver.findElements(By.xpath("(//a[contains(text(),'"+testData[0]+"')]//parent::span//parent::td//following-sibling::td)[2]/span"));
			
			Assert.assertEquals(phNums.get(0).getText().trim(), extData[1]);
			Assert.assertEquals(phNums.get(1).getText().trim(), testData[5]);
			Assert.assertEquals(phNums.get(2).getText().trim(), testData[6]);
			Assert.assertEquals(phNums.get(3).getText().trim(), testData[7]);
			Assert.assertEquals(phNums.get(4).getText().trim(), testData[8]);
			
			driver.findElement(By.xpath("//a[contains(text(),'"+testData[0]+"')]")).click();
		//	wait.until(ExpectedConditions.elementToBeClickable(By.name("firstNameDisplay")));
		Thread.sleep(1000);
//			System.out.println("First name is :"+serverManagerPage.getFirstNameTextBox().getText().trim());
	//		Assert.assertEquals(serverManagerPage.getFirstNameTextBox().getAttribute("value").trim(), testData[0]);
			Assert.assertEquals(serverManagerPage.getLastNameTextBox().getAttribute("value").trim(), testData[0]);
			Assert.assertEquals(serverManagerPage.getEmailAddress().getAttribute("value").trim(), testData[2]);
			
			wait.until(ExpectedConditions.elementToBeClickable(By.id("phones_0"))).click();
			Thread.sleep(2000);
//			serverManagerPage.getPhoneTab().click();
//			Thread.sleep(3000);
//			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@value='Home Phone']"
//					+ "//..//..//following-sibling::tr/td[2]/input[@value='"+testData[5]+"']")));
			
			List<WebElement> homePhone1 = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//input[@value='Home Phone']"
					+ "//..//..//following-sibling::tr/td[2]/input[@value='"+testData[5]+"']"));
			List<WebElement> homePhone2 = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//input[@value='Home Phone 2']"
					+ "//..//..//following-sibling::tr/td[2]/input[@value='"+testData[6]+"']"));
			List<WebElement> mobNumber1 = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//input[@value='Mobile Number']"
					+ "//..//..//following-sibling::tr/td[2]/input[@value='"+testData[7]+"']"));
			List<WebElement> mobNumber2 = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//input[@value='Mobile Number 2']"
					+ "//..//..//following-sibling::tr/td[2]/input[@value='"+testData[8]+"']"));
			List<WebElement> extNumber = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("(//input[@value='"+extData[1]+"'])[1]"));
			Assert.assertTrue(homePhone1.size()==1 && homePhone2.size()==1 && mobNumber1.size()==1 && 
					mobNumber2.size()==1 && extNumber.size()==1);
			
//			JavascriptExecutor js = (JavascriptExecutor)driver;
//			js.executeScript("arguments[0].scrollIntoView(true);", serverManagerPage.getSipPassword());
//			Thread.sleep(5000);
//			Assert.assertEquals(serverManagerPage.getSipPassword().getAttribute("value").trim(), extData[5]);
//			Assert.assertEquals(serverManagerPage.getSipConfirmPassword().getAttribute("value").trim(), extData[5]);
			
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//a[text()='MiCollab Client'])[2]"))).click();
//			serverManagerPage.getMicollabClientTab().click();
//			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//select[@name='ucaService.featureProfileID']/option[@selected='selected']")));
			String assignedRole = driver.findElement(By.xpath("//select[@name='ucaService.featureProfileID']/option[@selected='selected']")).getText().trim();
			String dskPhoneExt = driver.findElement(By.xpath("//select[@id='UCADeskphoneSelect']/option[@selected='selected']")).getText().trim();
			String mailBoxNum = driver.findElement(By.xpath("//select[@id='ucaMailboxSelect']/option[@selected='selected']")).getText().trim();
			
			Assert.assertEquals(assignedRole, extData[7]);
			Assert.assertTrue(dskPhoneExt.contains(extData[1]));
			Assert.assertEquals(mailBoxNum, extData[1]);
			
			serverManagerPage.getCancelButton().click();
			List<WebElement> role = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//img[@src='images/g_check_000.gif']"));
			Assert.assertTrue(role.size()==4);
			
			
			driver.switchTo().defaultContent();
			driver.switchTo().frame(serverManagerPage.getHeaderFrame());
			Thread.sleep(2000);
			serverManagerPage.getLogoutLink().click();
			
			//////////////////////////////////////change the role now////////////////////////////////////
			
			driver.get(ipData.getData(0, 0));
			pmLoginPage.PM_Login(credentials[0], credentials[1]);
			
			pmMainPage.getUsers().click();
			pmUsers.getUser().click();
			
			pmUser.getUserSearchTextBox().clear();
			pmUser.setUserSearchTextBox(extData[1]);
			pmUser.getOnViewRangeButton().click();
			
			driver.findElement(By.xpath("(//td[contains(text(),'"+testData[0]+"')])[1]"
					+ "//following-sibling::td[contains(text(),'"+extData[1]+" ')]//preceding-sibling::td[25]")).click();
			serverManagerPage.getMicollabConfigTab().click();
			new SelectDropDownValue().selectByVisibleText(authCodePage.getMicollabRoleDropDown(), "UCC (V4.0) Entry");
			driver.switchTo().alert().accept();
			pmExtension.getEditPageApplyButton().click();
			
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(@class,'responseMessage')]")));
			Assert.assertEquals(driver.findElement(By.xpath("//span[contains(@class,'responseMessage')]")).getText().trim(), "Change operation successful for:");
			
			
//			Assert.assertEquals(pmUser.getResponseMessage().getText().trim(), "Add operation successful for:");
			pmUser.getDoneButton().click();
//			pmUser.getDoneButton().click();
			
			pmUser.getUserSearchTextBox().clear();
			pmUser.setUserSearchTextBox(testData[0]);
			pmUser.getOnViewRangeButton().click();
			
			List<WebElement> eles1 = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("(//td[contains(text(),'"+testData[0]+"')])[1]//following-sibling::"
					+ "td[contains(text(),'"+extData[1]+" / SN')]"
					+ "//following-sibling::td[contains(text(),'Micollab')]"
					+ "//following-sibling::td[contains(text(),'UCC (V4.0) Entry')]"));
			Assert.assertTrue(eles.size() == 1);
			pmMainPage.getLogoutLink().click();
			
			///////////////////Again verify in Micollab Now///////////////////////
//			pmMainPage.getLogoutLink().click();
			driver.get(ipData.getData(2, 0));
			micoLogin.micollab_login(micoCredentials[0], micoCredentials[1]);
			driver.switchTo().frame(serverManagerPage.getInToNavigationFrame());
			serverManagerPage.getUsersAndServicesLink().click();
			driver.switchTo().defaultContent();
			driver.switchTo().frame(serverManagerPage.getMainFrame());
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='basicSearchTerm']")));
			serverManagerPage.getUserSearchTextBox().sendKeys(testData[0]);
			serverManagerPage.getSearchButton().click();
			Thread.sleep(1000);
			List<WebElement> phNumsEdit = driver.findElements(By.xpath("(//a[contains(text(),'"+testData[0]+"')]//parent::span//parent::td//following-sibling::td)[2]/span"));
			
			Assert.assertEquals(phNumsEdit.get(0).getText().trim(), extData[1]);
			Assert.assertEquals(phNumsEdit.get(1).getText().trim(), testData[5]);
			Assert.assertEquals(phNumsEdit.get(2).getText().trim(), testData[6]);
			Assert.assertEquals(phNumsEdit.get(3).getText().trim(), testData[7]);
			Assert.assertEquals(phNumsEdit.get(4).getText().trim(), testData[8]);
			
			driver.findElement(By.xpath("//a[contains(text(),'"+testData[0]+"')]")).click();
			Thread.sleep(1000);
			//System.out.println("First name is :"+serverManagerPage.getFirstNameTextBox().getText().trim());
			//Assert.assertEquals(serverManagerPage.getFirstNameTextBox().getAttribute("value").trim(), testData[0]);
			Assert.assertEquals(serverManagerPage.getLastNameTextBox().getAttribute("value").trim(), testData[0]);
			Assert.assertEquals(serverManagerPage.getEmailAddress().getAttribute("value").trim(), testData[2]);
			serverManagerPage.getPhoneTab().click();
			
			List<WebElement> homePhoneInEdit = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//input[@value='Home Phone']"
					+ "//..//..//following-sibling::tr/td[2]/input[@value='"+testData[5]+"']"));
			List<WebElement> homePhone2InEdit = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//input[@value='Home Phone 2']"
					+ "//..//..//following-sibling::tr/td[2]/input[@value='"+testData[6]+"']"));
			List<WebElement> mobNumber1InEdit = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//input[@value='Mobile Number']"
					+ "//..//..//following-sibling::tr/td[2]/input[@value='"+testData[7]+"']"));
			List<WebElement> mobNumber2InEdit = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//input[@value='Mobile Number 2']"
					+ "//..//..//following-sibling::tr/td[2]/input[@value='"+testData[8]+"']"));
			List<WebElement> extNumberInEdit = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("(//input[@value='"+extData[1]+"'])[1]"));
			Assert.assertTrue(homePhoneInEdit.size()==1 && homePhone2InEdit.size()==1 && mobNumber1InEdit.size()==1 && 
					mobNumber2InEdit.size()==1 && extNumberInEdit.size()==1);
//			Thread.sleep(1000);
//			JavascriptExecutor js = (JavascriptExecutor)driver;
//			js.executeScript("arguments[0].scrollIntoView(true);", serverManagerPage.getSipPassword());
//			Thread.sleep(5000);
//			Assert.assertEquals(serverManagerPage.getSipPassword().getAttribute("value").trim(), extData[5]);
//			Assert.assertEquals(serverManagerPage.getSipConfirmPassword().getAttribute("value").trim(), extData[5]);
			
//			serverManagerPage.getMicollabClientTab().click();
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//a[text()='MiCollab Client'])[2]"))).click();
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//select[@name='ucaService.featureProfileID']/option[@selected='selected']")));
			String assignedRoleEdit = driver.findElement(By.xpath("//select[@name='ucaService.featureProfileID']/option[@selected='selected']")).getText().trim();
			String dskPhoneExtEdit = driver.findElement(By.xpath("//select[@id='UCADeskphoneSelect']/option[@selected='selected']")).getText().trim();
			String mailBoxNumEdit = driver.findElement(By.xpath("//select[@id='ucaMailboxSelect']/option[@selected='selected']")).getText().trim();
			
			Assert.assertEquals(assignedRoleEdit, "UCC (V4.0) Entry");
			Assert.assertTrue(dskPhoneExtEdit.contains(extData[1]));
			Assert.assertEquals(mailBoxNumEdit, extData[1]);
			
			
			serverManagerPage.getCancelButton().click();
			List<WebElement> roleEdit = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//img[@src='images/g_check_000.gif']"));
			Assert.assertTrue(roleEdit.size()==2);
			
			driver.switchTo().defaultContent();
			driver.switchTo().frame(serverManagerPage.getHeaderFrame());
			Thread.sleep(2000);
			serverManagerPage.getLogoutLink().click();
			
			
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
			try
			{
				new CleanUP(driver).deleteUser(driver, ipData, credentials, testData[0]);
				list.clear();
				list.add(extData[8]);
				list.add(extData[9]);
				list.add(extData[10]);
				new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
			}
			catch(Exception e)
			{
					new Take_Screenshot().get_Screenshot(driver, method.getName());
					throw e;
			}
		}
	}
	
	
	@Test
	public void test_edit_micollab_Role_from_standard_to_premium(Method method) throws Exception
	{
		authCodePage = new PM_Auth_Code_Page(driver);
		micoLogin = new Micollab_Login_Page(driver);
		serverManagerPage = new Micollab_Server_Manager_Page(driver);
		pmLoginPage = new PM_Login_Page(driver);
		pmMainPage = new PM_Main_Page(driver);
		pmUser = new PM_User(driver);
		pmUsers = new PM_Users(driver);
		pmExtension = new Extension(driver);
		
		
		list = new ArrayList<String>();
		String[] credentials = loginData.getData("test_pm_valid_login", 1);
		String[] testData = pmTests.getData(method.getName(), 1);
		String[] extData = pmTests.getData(method.getName(), 3);
		String[] micoCredentials = loginData.getData("test_micollab_login", 1);
		wait = new WebDriverWait(driver, 20);
		try 
		{
			pmTests.checkTestStatus(method.getName());
			pmUser = new PM_User(driver);
			pmMainPage = new PM_Main_Page(driver);
			list.add(extData[0]);
			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
			new ReusableUnits(driver).navigateUserToServiceSummaryPage(driver, method.getName(), ipData, loginData, pmTests);
			
			
			pmUser.getCreateAndAssignExtensionToUser().click();
			new SelectDropDownValue().selectByVisibleText(pmExtension.getExtensionType(), "IP");
			pmExtension.getNextButton().click();
			
			//Provide extension details.
			new SelectDropDownValue().selectByVisibleText(pmExtension.getSelectExtensionsRange(), extData[1]);
			String version = new GetMxoneVersionNumber(driver).getMxoneVersionNumber(driver);
			System.out.println(version);
			int ver = Integer.parseInt(version);
			System.out.println(ver);
			if(ver >= 72000)
			{
				pmExtension.setSingleExtensionValue(extData[1]);
			}
			else
			{
				new SelectDropDownValue().selectByVisibleText(pmExtension.getSingleExtensionDropDown(), extData[1]);
			}
			new SelectDropDownValue().selectByIndex(pmExtension.getMyCSPNameDropDown(),0);
			new SelectDropDownValue().selectByVisibleText(pmExtension.getServerDropDown(), "1");
			pmExtension.getFirstName().clear();
			pmExtension.getLastName().clear();
			pmExtension.setFirstName(extData[2]);
			pmExtension.setLastName(extData[3]);
			new SelectDropDownValue().selectByVisibleText(pmExtension.getPhoneTypeDropDown(), extData[4]);
			
			pmExtension.getAuthCodeButton().click();
			authCodePage.getAuthCodeAddButton().click();
			authCodePage.setAuthCodeTextBox(extData[5]);
			new SelectDropDownValue().selectByValue(authCodePage.gethashTypeDropDown(), extData[6]);
			authCodePage.setCilCode(extData[5]);
			authCodePage.getAuthPageApplyButton().click();
			
			List<WebElement> eleCount = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//td[contains(text(),'CLEARTEXT')]"));
			Assert.assertTrue(eleCount.size() == 1);
			
			authCodePage.getContinueButton().click();
			authCodePage.getContinueButtonToServiceSummaryPage().click();
			
//			authCodePage.getNextButtonToMicollabConfigPage().click();
			pmExtension.getNextButton().click();
			new SelectDropDownValue().selectByVisibleText(authCodePage.getMicollabRoleDropDown(), extData[7]);
			driver.switchTo().alert().accept();
			pmExtension.getApplyButton().click();
			
			WebDriverWait wait = new WebDriverWait(driver, 100);
//			wait.until(ExpectedConditions.textToBe(By.className("responseMessage"), "  Import operation successful for:"));
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(@class,'responseMessage')]")));
			Assert.assertEquals(driver.findElement(By.xpath("//span[contains(@class,'responseMessage')]")).getText().trim(), "Add operation successful for:");
			
			
//			Assert.assertEquals(pmUser.getResponseMessage().getText().trim(), "Add operation successful for:");
			pmUser.getDoneButton().click();
//			pmUser.getDoneButton().click();
			
			pmUser.getUserSearchTextBox().clear();
			pmUser.setUserSearchTextBox(testData[0]);
			pmUser.getOnViewRangeButton().click();
			
			List<WebElement> eles = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("(//td[contains(text(),'"+testData[0]+"')])[1]//following-sibling::"
					+ "td[contains(text(),'"+extData[1]+" / SN')]"
					+ "//following-sibling::td[contains(text(),'Micollab')]"
					+ "//following-sibling::td[contains(text(),'"+extData[7]+"')]"));
			Assert.assertTrue(eles.size() == 1);
			
			pmMainPage.getLogoutLink().click();
			driver.get(ipData.getData(2, 0));
			Thread.sleep(1000);
			micoLogin.micollab_login(micoCredentials[0], micoCredentials[1]);
			driver.switchTo().frame(serverManagerPage.getInToNavigationFrame());
			serverManagerPage.getUsersAndServicesLink().click();
			driver.switchTo().defaultContent();
			driver.switchTo().frame(serverManagerPage.getMainFrame());
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='basicSearchTerm']")));
			serverManagerPage.getUserSearchTextBox().sendKeys(testData[0]);
			serverManagerPage.getSearchButton().click();
			Thread.sleep(1000);
			List<WebElement> phNums = driver.findElements(By.xpath("(//a[contains(text(),'"+testData[0]+"')]//parent::span//parent::td//following-sibling::td)[2]/span"));
			
			Assert.assertEquals(phNums.get(0).getText().trim(), extData[1]);
			Assert.assertEquals(phNums.get(1).getText().trim(), testData[5]);
			Assert.assertEquals(phNums.get(2).getText().trim(), testData[6]);
			Assert.assertEquals(phNums.get(3).getText().trim(), testData[7]);
			Assert.assertEquals(phNums.get(4).getText().trim(), testData[8]);
			
			driver.findElement(By.xpath("//a[contains(text(),'"+testData[0]+"')]")).click();
		//	wait.until(ExpectedConditions.elementToBeClickable(By.name("firstNameDisplay")));
			Thread.sleep(1000);
//			System.out.println("First name is :"+serverManagerPage.getFirstNameTextBox().getText().trim());
	//		Assert.assertEquals(serverManagerPage.getFirstNameTextBox().getAttribute("value").trim(), testData[0]);
			Assert.assertEquals(serverManagerPage.getLastNameTextBox().getAttribute("value").trim(), testData[0]);
			Assert.assertEquals(serverManagerPage.getEmailAddress().getAttribute("value").trim(), testData[2]);
			
			wait.until(ExpectedConditions.elementToBeClickable(By.id("phones_0"))).click();
			Thread.sleep(2000);
//			serverManagerPage.getPhoneTab().click();
//			Thread.sleep(3000);
//			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@value='Home Phone']"
//					+ "//..//..//following-sibling::tr/td[2]/input[@value='"+testData[5]+"']")));
			
			List<WebElement> homePhone1 = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//input[@value='Home Phone']"
					+ "//..//..//following-sibling::tr/td[2]/input[@value='"+testData[5]+"']"));
			List<WebElement> homePhone2 = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//input[@value='Home Phone 2']"
					+ "//..//..//following-sibling::tr/td[2]/input[@value='"+testData[6]+"']"));
			List<WebElement> mobNumber1 = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//input[@value='Mobile Number']"
					+ "//..//..//following-sibling::tr/td[2]/input[@value='"+testData[7]+"']"));
			List<WebElement> mobNumber2 = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//input[@value='Mobile Number 2']"
					+ "//..//..//following-sibling::tr/td[2]/input[@value='"+testData[8]+"']"));
			List<WebElement> extNumber = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("(//input[@value='"+extData[1]+"'])[1]"));
			Assert.assertTrue(homePhone1.size()==1 && homePhone2.size()==1 && mobNumber1.size()==1 && 
					mobNumber2.size()==1 && extNumber.size()==1);
			
//			JavascriptExecutor js = (JavascriptExecutor)driver;
//			js.executeScript("arguments[0].scrollIntoView(true);", serverManagerPage.getSipPassword());
//			Thread.sleep(5000);
//			Assert.assertEquals(serverManagerPage.getSipPassword().getAttribute("value").trim(), extData[5]);
//			Assert.assertEquals(serverManagerPage.getSipConfirmPassword().getAttribute("value").trim(), extData[5]);
			
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//a[text()='MiCollab Client'])[2]"))).click();
//			serverManagerPage.getMicollabClientTab().click();
//			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//select[@name='ucaService.featureProfileID']/option[@selected='selected']")));
			String assignedRole = driver.findElement(By.xpath("//select[@name='ucaService.featureProfileID']/option[@selected='selected']")).getText().trim();
			String dskPhoneExt = driver.findElement(By.xpath("//select[@id='UCADeskphoneSelect']/option[@selected='selected']")).getText().trim();
			String mailBoxNum = driver.findElement(By.xpath("//select[@id='ucaMailboxSelect']/option[@selected='selected']")).getText().trim();
			
			Assert.assertEquals(assignedRole, extData[7]);
			Assert.assertTrue(dskPhoneExt.contains(extData[1]));
			Assert.assertEquals(mailBoxNum, extData[1]);
			
			serverManagerPage.getCancelButton().click();
			List<WebElement> role = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//img[@src='images/g_check_000.gif']"));
			Assert.assertTrue(role.size()==4);
			
			
			driver.switchTo().defaultContent();
			driver.switchTo().frame(serverManagerPage.getHeaderFrame());
			Thread.sleep(2000);
			serverManagerPage.getLogoutLink().click();
			
			//////////////////////////////////////change the role now////////////////////////////////////
			
			driver.get(ipData.getData(0, 0));
			pmLoginPage.PM_Login(credentials[0], credentials[1]);
			
			pmMainPage.getUsers().click();
			pmUsers.getUser().click();
			
			pmUser.getUserSearchTextBox().clear();
			pmUser.setUserSearchTextBox(extData[1]);
			pmUser.getOnViewRangeButton().click();
			
			driver.findElement(By.xpath("(//td[contains(text(),'"+testData[0]+"')])[1]"
					+ "//following-sibling::td[contains(text(),'"+extData[1]+" ')]//preceding-sibling::td[25]")).click();
			serverManagerPage.getMicollabConfigTab().click();
			new SelectDropDownValue().selectByVisibleText(authCodePage.getMicollabRoleDropDown(), "UCC (V4.0) Premium");
			driver.switchTo().alert().accept();
			pmExtension.getEditPageApplyButton().click();
			
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(@class,'responseMessage')]")));
			Assert.assertEquals(driver.findElement(By.xpath("//span[contains(@class,'responseMessage')]")).getText().trim(), "Change operation successful for:");
			
			
//			Assert.assertEquals(pmUser.getResponseMessage().getText().trim(), "Add operation successful for:");
			pmUser.getDoneButton().click();
//			pmUser.getDoneButton().click();
			
			pmUser.getUserSearchTextBox().clear();
			pmUser.setUserSearchTextBox(testData[0]);
			pmUser.getOnViewRangeButton().click();
			
			List<WebElement> eles1 = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("(//td[contains(text(),'"+testData[0]+"')])[1]//following-sibling::"
					+ "td[contains(text(),'"+extData[1]+" / SN')]"
					+ "//following-sibling::td[contains(text(),'Micollab')]"
					+ "//following-sibling::td[contains(text(),'UCC (V4.0) Premium')]"));
			Assert.assertTrue(eles.size() == 1);
			pmMainPage.getLogoutLink().click();
			
			///////////////////Again verify in Micollab Now///////////////////////
//			pmMainPage.getLogoutLink().click();
			driver.get(ipData.getData(2, 0));
			micoLogin.micollab_login(micoCredentials[0], micoCredentials[1]);
			driver.switchTo().frame(serverManagerPage.getInToNavigationFrame());
			serverManagerPage.getUsersAndServicesLink().click();
			driver.switchTo().defaultContent();
			driver.switchTo().frame(serverManagerPage.getMainFrame());
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='basicSearchTerm']")));
			serverManagerPage.getUserSearchTextBox().sendKeys(testData[0]);
			serverManagerPage.getSearchButton().click();
			Thread.sleep(1000);
			List<WebElement> phNumsEdit = driver.findElements(By.xpath("(//a[contains(text(),'"+testData[0]+"')]//parent::span//parent::td//following-sibling::td)[2]/span"));
			
			Assert.assertEquals(phNumsEdit.get(0).getText().trim(), extData[1]);
			Assert.assertEquals(phNumsEdit.get(1).getText().trim(), testData[5]);
			Assert.assertEquals(phNumsEdit.get(2).getText().trim(), testData[6]);
			Assert.assertEquals(phNumsEdit.get(3).getText().trim(), testData[7]);
			Assert.assertEquals(phNumsEdit.get(4).getText().trim(), testData[8]);
			
			driver.findElement(By.xpath("//a[contains(text(),'"+testData[0]+"')]")).click();
			Thread.sleep(1000);
			//System.out.println("First name is :"+serverManagerPage.getFirstNameTextBox().getText().trim());
			//Assert.assertEquals(serverManagerPage.getFirstNameTextBox().getAttribute("value").trim(), testData[0]);
			Assert.assertEquals(serverManagerPage.getLastNameTextBox().getAttribute("value").trim(), testData[0]);
			Assert.assertEquals(serverManagerPage.getEmailAddress().getAttribute("value").trim(), testData[2]);
			serverManagerPage.getPhoneTab().click();
			
			List<WebElement> homePhoneInEdit = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//input[@value='Home Phone']"
					+ "//..//..//following-sibling::tr/td[2]/input[@value='"+testData[5]+"']"));
			List<WebElement> homePhone2InEdit = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//input[@value='Home Phone 2']"
					+ "//..//..//following-sibling::tr/td[2]/input[@value='"+testData[6]+"']"));
			List<WebElement> mobNumber1InEdit = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//input[@value='Mobile Number']"
					+ "//..//..//following-sibling::tr/td[2]/input[@value='"+testData[7]+"']"));
			List<WebElement> mobNumber2InEdit = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//input[@value='Mobile Number 2']"
					+ "//..//..//following-sibling::tr/td[2]/input[@value='"+testData[8]+"']"));
			List<WebElement> extNumberInEdit = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("(//input[@value='"+extData[1]+"'])[1]"));
			Assert.assertTrue(homePhoneInEdit.size()==1 && homePhone2InEdit.size()==1 && mobNumber1InEdit.size()==1 && 
					mobNumber2InEdit.size()==1 && extNumberInEdit.size()==1);
//			Thread.sleep(1000);
//			JavascriptExecutor js = (JavascriptExecutor)driver;
//			js.executeScript("arguments[0].scrollIntoView(true);", serverManagerPage.getSipPassword());
//			Thread.sleep(5000);
//			Assert.assertEquals(serverManagerPage.getSipPassword().getAttribute("value").trim(), extData[5]);
//			Assert.assertEquals(serverManagerPage.getSipConfirmPassword().getAttribute("value").trim(), extData[5]);
			
//			serverManagerPage.getMicollabClientTab().click();
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//a[text()='MiCollab Client'])[2]"))).click();
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//select[@name='ucaService.featureProfileID']/option[@selected='selected']")));
			String assignedRoleEdit = driver.findElement(By.xpath("//select[@name='ucaService.featureProfileID']/option[@selected='selected']")).getText().trim();
			String dskPhoneExtEdit = driver.findElement(By.xpath("//select[@id='UCADeskphoneSelect']/option[@selected='selected']")).getText().trim();
			String mailBoxNumEdit = driver.findElement(By.xpath("//select[@id='ucaMailboxSelect']/option[@selected='selected']")).getText().trim();
			
			Assert.assertEquals(assignedRoleEdit, "UCC (V4.0) Premium");
			Assert.assertTrue(dskPhoneExtEdit.contains(extData[1]));
			Assert.assertEquals(mailBoxNumEdit, extData[1]);
			
			
			serverManagerPage.getCancelButton().click();
			List<WebElement> roleEdit = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//img[@src='images/g_check_000.gif']"));
			Assert.assertTrue(roleEdit.size()==4);
			
			driver.switchTo().defaultContent();
			driver.switchTo().frame(serverManagerPage.getHeaderFrame());
			Thread.sleep(2000);
			serverManagerPage.getLogoutLink().click();
			
			
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
			try
			{
				new CleanUP(driver).deleteUser(driver, ipData, credentials, testData[0]);
				list.clear();
				list.add(extData[8]);
				list.add(extData[9]);
				list.add(extData[10]);
				new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
			}
			catch(Exception e)
			{
					new Take_Screenshot().get_Screenshot(driver, method.getName());
					throw e;
			}
		}
	}
	

	
	
	@Test
	public void test_edit_micollab_Role_from_premium_to_basic(Method method) throws Exception
	{
		authCodePage = new PM_Auth_Code_Page(driver);
		micoLogin = new Micollab_Login_Page(driver);
		serverManagerPage = new Micollab_Server_Manager_Page(driver);
		pmLoginPage = new PM_Login_Page(driver);
		pmMainPage = new PM_Main_Page(driver);
		pmUser = new PM_User(driver);
		pmUsers = new PM_Users(driver);
		pmExtension = new Extension(driver);
		
		
		list = new ArrayList<String>();
		String[] credentials = loginData.getData("test_pm_valid_login", 1);
		String[] testData = pmTests.getData(method.getName(), 1);
		String[] extData = pmTests.getData(method.getName(), 3);
		String[] micoCredentials = loginData.getData("test_micollab_login", 1);
		wait = new WebDriverWait(driver, 20);
		try 
		{
			pmTests.checkTestStatus(method.getName());
			pmUser = new PM_User(driver);
			pmMainPage = new PM_Main_Page(driver);
			list.add(extData[0]);
			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
			new ReusableUnits(driver).navigateUserToServiceSummaryPage(driver, method.getName(), ipData, loginData, pmTests);
			
			
			pmUser.getCreateAndAssignExtensionToUser().click();
			new SelectDropDownValue().selectByVisibleText(pmExtension.getExtensionType(), "IP");
			pmExtension.getNextButton().click();
			
			//Provide extension details.
			new SelectDropDownValue().selectByVisibleText(pmExtension.getSelectExtensionsRange(), extData[1]);
			String version = new GetMxoneVersionNumber(driver).getMxoneVersionNumber(driver);
			System.out.println(version);
			int ver = Integer.parseInt(version);
			System.out.println(ver);
			if(ver >= 72000)
			{
				pmExtension.setSingleExtensionValue(extData[1]);
			}
			else
			{
				new SelectDropDownValue().selectByVisibleText(pmExtension.getSingleExtensionDropDown(), extData[1]);
			}
			new SelectDropDownValue().selectByIndex(pmExtension.getMyCSPNameDropDown(),0);
			new SelectDropDownValue().selectByVisibleText(pmExtension.getServerDropDown(), "1");
			pmExtension.getFirstName().clear();
			pmExtension.getLastName().clear();
			pmExtension.setFirstName(extData[2]);
			pmExtension.setLastName(extData[3]);
			new SelectDropDownValue().selectByVisibleText(pmExtension.getPhoneTypeDropDown(), extData[4]);
			
			pmExtension.getAuthCodeButton().click();
			authCodePage.getAuthCodeAddButton().click();
			authCodePage.setAuthCodeTextBox(extData[5]);
			new SelectDropDownValue().selectByValue(authCodePage.gethashTypeDropDown(), extData[6]);
			authCodePage.setCilCode(extData[5]);
			authCodePage.getAuthPageApplyButton().click();
			
			List<WebElement> eleCount = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//td[contains(text(),'CLEARTEXT')]"));
			Assert.assertTrue(eleCount.size() == 1);
			
			authCodePage.getContinueButton().click();
			authCodePage.getContinueButtonToServiceSummaryPage().click();
			
//			authCodePage.getNextButtonToMicollabConfigPage().click();
			pmExtension.getNextButton().click();
			new SelectDropDownValue().selectByVisibleText(authCodePage.getMicollabRoleDropDown(), extData[7]);
			driver.switchTo().alert().accept();
			pmExtension.getApplyButton().click();
			
			WebDriverWait wait = new WebDriverWait(driver, 100);
//			wait.until(ExpectedConditions.textToBe(By.className("responseMessage"), "  Import operation successful for:"));
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(@class,'responseMessage')]")));
			Assert.assertEquals(driver.findElement(By.xpath("//span[contains(@class,'responseMessage')]")).getText().trim(), "Add operation successful for:");
			
			
//			Assert.assertEquals(pmUser.getResponseMessage().getText().trim(), "Add operation successful for:");
			pmUser.getDoneButton().click();
//			pmUser.getDoneButton().click();
			
			pmUser.getUserSearchTextBox().clear();
			pmUser.setUserSearchTextBox(testData[0]);
			pmUser.getOnViewRangeButton().click();
			
			List<WebElement> eles = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("(//td[contains(text(),'"+testData[0]+"')])[1]//following-sibling::"
					+ "td[contains(text(),'"+extData[1]+" / SN')]"
					+ "//following-sibling::td[contains(text(),'Micollab')]"
					+ "//following-sibling::td[contains(text(),'"+extData[7]+"')]"));
			Assert.assertTrue(eles.size() == 1);
			
			pmMainPage.getLogoutLink().click();
			driver.get(ipData.getData(2, 0));
			Thread.sleep(1000);
			micoLogin.micollab_login(micoCredentials[0], micoCredentials[1]);
			driver.switchTo().frame(serverManagerPage.getInToNavigationFrame());
			serverManagerPage.getUsersAndServicesLink().click();
			driver.switchTo().defaultContent();
			driver.switchTo().frame(serverManagerPage.getMainFrame());
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='basicSearchTerm']")));
			serverManagerPage.getUserSearchTextBox().sendKeys(testData[0]);
			serverManagerPage.getSearchButton().click();
			Thread.sleep(1000);
			List<WebElement> phNums = driver.findElements(By.xpath("(//a[contains(text(),'"+testData[0]+"')]//parent::span//parent::td//following-sibling::td)[2]/span"));
			
			Assert.assertEquals(phNums.get(0).getText().trim(), extData[1]);
			Assert.assertEquals(phNums.get(1).getText().trim(), testData[5]);
			Assert.assertEquals(phNums.get(2).getText().trim(), testData[6]);
			Assert.assertEquals(phNums.get(3).getText().trim(), testData[7]);
			Assert.assertEquals(phNums.get(4).getText().trim(), testData[8]);
			
			driver.findElement(By.xpath("//a[contains(text(),'"+testData[0]+"')]")).click();
			//wait.until(ExpectedConditions.elementToBeClickable(By.name("firstNameDisplay")));
			Thread.sleep(1000);
//			System.out.println("First name is :"+serverManagerPage.getFirstNameTextBox().getText().trim());
	//		Assert.assertEquals(serverManagerPage.getFirstNameTextBox().getAttribute("value").trim(), testData[0]);
			Assert.assertEquals(serverManagerPage.getLastNameTextBox().getAttribute("value").trim(), testData[0]);
			Assert.assertEquals(serverManagerPage.getEmailAddress().getAttribute("value").trim(), testData[2]);
			
			wait.until(ExpectedConditions.elementToBeClickable(By.id("phones_0"))).click();
			Thread.sleep(2000);
//			serverManagerPage.getPhoneTab().click();
//			Thread.sleep(3000);
//			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@value='Home Phone']"
//					+ "//..//..//following-sibling::tr/td[2]/input[@value='"+testData[5]+"']")));
			
			List<WebElement> homePhone1 = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//input[@value='Home Phone']"
					+ "//..//..//following-sibling::tr/td[2]/input[@value='"+testData[5]+"']"));
			List<WebElement> homePhone2 = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//input[@value='Home Phone 2']"
					+ "//..//..//following-sibling::tr/td[2]/input[@value='"+testData[6]+"']"));
			List<WebElement> mobNumber1 = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//input[@value='Mobile Number']"
					+ "//..//..//following-sibling::tr/td[2]/input[@value='"+testData[7]+"']"));
			List<WebElement> mobNumber2 = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//input[@value='Mobile Number 2']"
					+ "//..//..//following-sibling::tr/td[2]/input[@value='"+testData[8]+"']"));
			List<WebElement> extNumber = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("(//input[@value='"+extData[1]+"'])[1]"));
			Assert.assertTrue(homePhone1.size()==1 && homePhone2.size()==1 && mobNumber1.size()==1 && 
					mobNumber2.size()==1 && extNumber.size()==1);
			
//			JavascriptExecutor js = (JavascriptExecutor)driver;
//			js.executeScript("arguments[0].scrollIntoView(true);", serverManagerPage.getSipPassword());
//			Thread.sleep(5000);
//			Assert.assertEquals(serverManagerPage.getSipPassword().getAttribute("value").trim(), extData[5]);
//			Assert.assertEquals(serverManagerPage.getSipConfirmPassword().getAttribute("value").trim(), extData[5]);
			
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//a[text()='MiCollab Client'])[2]"))).click();
//			serverManagerPage.getMicollabClientTab().click();
//			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//select[@name='ucaService.featureProfileID']/option[@selected='selected']")));
			String assignedRole = driver.findElement(By.xpath("//select[@name='ucaService.featureProfileID']/option[@selected='selected']")).getText().trim();
			String dskPhoneExt = driver.findElement(By.xpath("//select[@id='UCADeskphoneSelect']/option[@selected='selected']")).getText().trim();
			String mailBoxNum = driver.findElement(By.xpath("//select[@id='ucaMailboxSelect']/option[@selected='selected']")).getText().trim();
			
			Assert.assertEquals(assignedRole, extData[7]);
			Assert.assertTrue(dskPhoneExt.contains(extData[1]));
			Assert.assertEquals(mailBoxNum, extData[1]);
			
			serverManagerPage.getCancelButton().click();
			List<WebElement> role = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//img[@src='images/g_check_000.gif']"));
			Assert.assertTrue(role.size()==4);
			
			
			driver.switchTo().defaultContent();
			driver.switchTo().frame(serverManagerPage.getHeaderFrame());
			Thread.sleep(2000);
			serverManagerPage.getLogoutLink().click();
			
			//////////////////////////////////////change the role now////////////////////////////////////
			
			driver.get(ipData.getData(0, 0));
			pmLoginPage.PM_Login(credentials[0], credentials[1]);
			
			pmMainPage.getUsers().click();
			pmUsers.getUser().click();
			
			pmUser.getUserSearchTextBox().clear();
			pmUser.setUserSearchTextBox(extData[1]);
			pmUser.getOnViewRangeButton().click();
			
			driver.findElement(By.xpath("(//td[contains(text(),'"+testData[0]+"')])[1]"
					+ "//following-sibling::td[contains(text(),'"+extData[1]+" ')]//preceding-sibling::td[25]")).click();
			serverManagerPage.getMicollabConfigTab().click();
			new SelectDropDownValue().selectByVisibleText(authCodePage.getMicollabRoleDropDown(), "Basic User");
			driver.switchTo().alert().accept();
			pmExtension.getEditPageApplyButton().click();
			
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(@class,'responseMessage')]")));
			Assert.assertEquals(driver.findElement(By.xpath("//span[contains(@class,'responseMessage')]")).getText().trim(), "Change operation successful for:");
			
			
//			Assert.assertEquals(pmUser.getResponseMessage().getText().trim(), "Add operation successful for:");
			pmUser.getDoneButton().click();
//			pmUser.getDoneButton().click();
			
			pmUser.getUserSearchTextBox().clear();
			pmUser.setUserSearchTextBox(testData[0]);
			pmUser.getOnViewRangeButton().click();
			
			List<WebElement> eles1 = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("(//td[contains(text(),'"+testData[0]+"')])[1]//following-sibling::"
					+ "td[contains(text(),'"+extData[1]+" / SN')]"
					+ "//following-sibling::td[contains(text(),'Micollab')]"
					+ "//following-sibling::td[contains(text(),'Basic User')]"));
			Assert.assertTrue(eles.size() == 1);
			pmMainPage.getLogoutLink().click();
			
			///////////////////Again verify in Micollab Now///////////////////////
//			pmMainPage.getLogoutLink().click();
			driver.get(ipData.getData(2, 0));
			micoLogin.micollab_login(micoCredentials[0], micoCredentials[1]);
			driver.switchTo().frame(serverManagerPage.getInToNavigationFrame());
			serverManagerPage.getUsersAndServicesLink().click();
			driver.switchTo().defaultContent();
			driver.switchTo().frame(serverManagerPage.getMainFrame());
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='basicSearchTerm']")));
			serverManagerPage.getUserSearchTextBox().sendKeys(testData[0]);
			serverManagerPage.getSearchButton().click();
			Thread.sleep(1000);
			List<WebElement> phNumsEdit = driver.findElements(By.xpath("(//a[contains(text(),'"+testData[0]+"')]//parent::span//parent::td//following-sibling::td)[2]/span"));
			
			Assert.assertEquals(phNumsEdit.get(0).getText().trim(), extData[1]);
			Assert.assertEquals(phNumsEdit.get(1).getText().trim(), testData[5]);
			Assert.assertEquals(phNumsEdit.get(2).getText().trim(), testData[6]);
			Assert.assertEquals(phNumsEdit.get(3).getText().trim(), testData[7]);
			Assert.assertEquals(phNumsEdit.get(4).getText().trim(), testData[8]);
			
			driver.findElement(By.xpath("//a[contains(text(),'"+testData[0]+"')]")).click();
			Thread.sleep(1000);
			//System.out.println("First name is :"+serverManagerPage.getFirstNameTextBox().getText().trim());
			//Assert.assertEquals(serverManagerPage.getFirstNameTextBox().getAttribute("value").trim(), testData[0]);
			Assert.assertEquals(serverManagerPage.getLastNameTextBox().getAttribute("value").trim(), testData[0]);
			Assert.assertEquals(serverManagerPage.getEmailAddress().getAttribute("value").trim(), testData[2]);
			serverManagerPage.getPhoneTab().click();
			
			List<WebElement> homePhoneInEdit = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//input[@value='Home Phone']"
					+ "//..//..//following-sibling::tr/td[2]/input[@value='"+testData[5]+"']"));
			List<WebElement> homePhone2InEdit = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//input[@value='Home Phone 2']"
					+ "//..//..//following-sibling::tr/td[2]/input[@value='"+testData[6]+"']"));
			List<WebElement> mobNumber1InEdit = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//input[@value='Mobile Number']"
					+ "//..//..//following-sibling::tr/td[2]/input[@value='"+testData[7]+"']"));
			List<WebElement> mobNumber2InEdit = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//input[@value='Mobile Number 2']"
					+ "//..//..//following-sibling::tr/td[2]/input[@value='"+testData[8]+"']"));
			List<WebElement> extNumberInEdit = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//input[@value='"+extData[1]+"']"));
			Assert.assertTrue(homePhoneInEdit.size()==1 && homePhone2InEdit.size()==1 && mobNumber1InEdit.size()==1 && 
					mobNumber2InEdit.size()==1 && extNumberInEdit.size()==1);
//			Thread.sleep(1000);
//			JavascriptExecutor js = (JavascriptExecutor)driver;
//			js.executeScript("arguments[0].scrollIntoView(true);", serverManagerPage.getSipPassword());
//			Thread.sleep(5000);
//			Assert.assertEquals(serverManagerPage.getSipPassword().getAttribute("value").trim(), extData[5]);
//			Assert.assertEquals(serverManagerPage.getSipConfirmPassword().getAttribute("value").trim(), extData[5]);
			
//			serverManagerPage.getMicollabClientTab().click();
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//a[text()='MiCollab Client'])[2]"))).click();
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//select[@name='ucaService.featureProfileID']/option[@selected='selected']")));
			String assignedRoleEdit = driver.findElement(By.xpath("//select[@name='ucaService.featureProfileID']/option[@selected='selected']")).getText().trim();
			String dskPhoneExtEdit = driver.findElement(By.xpath("//select[@id='UCADeskphoneSelect']/option[@selected='selected']")).getText().trim();
			String mailBoxNumEdit = driver.findElement(By.xpath("//select[@id='ucaMailboxSelect']/option[@selected='selected']")).getText().trim();
			
			Assert.assertEquals(assignedRoleEdit, "Basic User");
			Assert.assertTrue(dskPhoneExtEdit.contains(extData[1]));
			Assert.assertEquals(mailBoxNumEdit, extData[1]);
			
			
			serverManagerPage.getCancelButton().click();
			List<WebElement> roleEdit = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//img[@src='images/g_check_000.gif']"));
			Assert.assertTrue(roleEdit.size()==1);
			
			driver.switchTo().defaultContent();
			driver.switchTo().frame(serverManagerPage.getHeaderFrame());
			Thread.sleep(2000);
			serverManagerPage.getLogoutLink().click();
			
			
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
			try
			{
				new CleanUP(driver).deleteUser(driver, ipData, credentials, testData[0]);
				list.clear();
				list.add(extData[8]);
				list.add(extData[9]);
				list.add(extData[10]);
				new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
			}
			catch(Exception e)
			{
					new Take_Screenshot().get_Screenshot(driver, method.getName());
					throw e;
			}
		}
	}
	
	
	@Test
	public void test_edit_micollab_Role_from_premium_to_entry(Method method) throws Exception
	{
		authCodePage = new PM_Auth_Code_Page(driver);
		micoLogin = new Micollab_Login_Page(driver);
		serverManagerPage = new Micollab_Server_Manager_Page(driver);
		pmLoginPage = new PM_Login_Page(driver);
		pmMainPage = new PM_Main_Page(driver);
		pmUser = new PM_User(driver);
		pmUsers = new PM_Users(driver);
		pmExtension = new Extension(driver);
		
		
		list = new ArrayList<String>();
		String[] credentials = loginData.getData("test_pm_valid_login", 1);
		String[] testData = pmTests.getData(method.getName(), 1);
		String[] extData = pmTests.getData(method.getName(), 3);
		String[] micoCredentials = loginData.getData("test_micollab_login", 1);
		wait = new WebDriverWait(driver, 20);
		try 
		{
			pmTests.checkTestStatus(method.getName());
			pmUser = new PM_User(driver);
			pmMainPage = new PM_Main_Page(driver);
			list.add(extData[0]);
			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
			new ReusableUnits(driver).navigateUserToServiceSummaryPage(driver, method.getName(), ipData, loginData, pmTests);
			
			
			pmUser.getCreateAndAssignExtensionToUser().click();
			new SelectDropDownValue().selectByVisibleText(pmExtension.getExtensionType(), "IP");
			pmExtension.getNextButton().click();
			
			//Provide extension details.
			new SelectDropDownValue().selectByVisibleText(pmExtension.getSelectExtensionsRange(), extData[1]);
			String version = new GetMxoneVersionNumber(driver).getMxoneVersionNumber(driver);
			System.out.println(version);
			int ver = Integer.parseInt(version);
			System.out.println(ver);
			if(ver >= 72000)
			{
				pmExtension.setSingleExtensionValue(extData[1]);
			}
			else
			{
				new SelectDropDownValue().selectByVisibleText(pmExtension.getSingleExtensionDropDown(), extData[1]);
			}
			new SelectDropDownValue().selectByIndex(pmExtension.getMyCSPNameDropDown(),0);
			new SelectDropDownValue().selectByVisibleText(pmExtension.getServerDropDown(), "1");
			pmExtension.getFirstName().clear();
			pmExtension.getLastName().clear();
			pmExtension.setFirstName(extData[2]);
			pmExtension.setLastName(extData[3]);
			new SelectDropDownValue().selectByVisibleText(pmExtension.getPhoneTypeDropDown(), extData[4]);
			
			pmExtension.getAuthCodeButton().click();
			authCodePage.getAuthCodeAddButton().click();
			authCodePage.setAuthCodeTextBox(extData[5]);
			new SelectDropDownValue().selectByValue(authCodePage.gethashTypeDropDown(), extData[6]);
			authCodePage.setCilCode(extData[5]);
			authCodePage.getAuthPageApplyButton().click();
			
			List<WebElement> eleCount = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//td[contains(text(),'CLEARTEXT')]"));
			Assert.assertTrue(eleCount.size() == 1);
			
			authCodePage.getContinueButton().click();
			authCodePage.getContinueButtonToServiceSummaryPage().click();
			
//			authCodePage.getNextButtonToMicollabConfigPage().click();
			pmExtension.getNextButton().click();
			new SelectDropDownValue().selectByVisibleText(authCodePage.getMicollabRoleDropDown(), extData[7]);
			driver.switchTo().alert().accept();
			pmExtension.getApplyButton().click();
			
			WebDriverWait wait = new WebDriverWait(driver, 100);
//			wait.until(ExpectedConditions.textToBe(By.className("responseMessage"), "  Import operation successful for:"));
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(@class,'responseMessage')]")));
			Assert.assertEquals(driver.findElement(By.xpath("//span[contains(@class,'responseMessage')]")).getText().trim(), "Add operation successful for:");
			
			
//			Assert.assertEquals(pmUser.getResponseMessage().getText().trim(), "Add operation successful for:");
			pmUser.getDoneButton().click();
//			pmUser.getDoneButton().click();
			
			pmUser.getUserSearchTextBox().clear();
			pmUser.setUserSearchTextBox(testData[0]);
			pmUser.getOnViewRangeButton().click();
			
			List<WebElement> eles = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("(//td[contains(text(),'"+testData[0]+"')])[1]//following-sibling::"
					+ "td[contains(text(),'"+extData[1]+" / SN')]"
					+ "//following-sibling::td[contains(text(),'Micollab')]"
					+ "//following-sibling::td[contains(text(),'"+extData[7]+"')]"));
			Assert.assertTrue(eles.size() == 1);
			
			pmMainPage.getLogoutLink().click();
			driver.get(ipData.getData(2, 0));
			Thread.sleep(1000);
			micoLogin.micollab_login(micoCredentials[0], micoCredentials[1]);
			driver.switchTo().frame(serverManagerPage.getInToNavigationFrame());
			serverManagerPage.getUsersAndServicesLink().click();
			driver.switchTo().defaultContent();
			driver.switchTo().frame(serverManagerPage.getMainFrame());
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='basicSearchTerm']")));
			serverManagerPage.getUserSearchTextBox().sendKeys(testData[0]);
			serverManagerPage.getSearchButton().click();
			Thread.sleep(1000);
			List<WebElement> phNums = driver.findElements(By.xpath("(//a[contains(text(),'"+testData[0]+"')]//parent::span//parent::td//following-sibling::td)[2]/span"));
			
			Assert.assertEquals(phNums.get(0).getText().trim(), extData[1]);
			Assert.assertEquals(phNums.get(1).getText().trim(), testData[5]);
			Assert.assertEquals(phNums.get(2).getText().trim(), testData[6]);
			Assert.assertEquals(phNums.get(3).getText().trim(), testData[7]);
			Assert.assertEquals(phNums.get(4).getText().trim(), testData[8]);
			
			driver.findElement(By.xpath("//a[contains(text(),'"+testData[0]+"')]")).click();
			//wait.until(ExpectedConditions.elementToBeClickable(By.name("firstNameDisplay")));
			Thread.sleep(1000);
//			System.out.println("First name is :"+serverManagerPage.getFirstNameTextBox().getText().trim());
	//		Assert.assertEquals(serverManagerPage.getFirstNameTextBox().getAttribute("value").trim(), testData[0]);
			Assert.assertEquals(serverManagerPage.getLastNameTextBox().getAttribute("value").trim(), testData[0]);
			Assert.assertEquals(serverManagerPage.getEmailAddress().getAttribute("value").trim(), testData[2]);
			
			wait.until(ExpectedConditions.elementToBeClickable(By.id("phones_0"))).click();
			Thread.sleep(2000);
//			serverManagerPage.getPhoneTab().click();
//			Thread.sleep(3000);
//			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@value='Home Phone']"
//					+ "//..//..//following-sibling::tr/td[2]/input[@value='"+testData[5]+"']")));
			
			List<WebElement> homePhone1 = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//input[@value='Home Phone']"
					+ "//..//..//following-sibling::tr/td[2]/input[@value='"+testData[5]+"']"));
			List<WebElement> homePhone2 = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//input[@value='Home Phone 2']"
					+ "//..//..//following-sibling::tr/td[2]/input[@value='"+testData[6]+"']"));
			List<WebElement> mobNumber1 = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//input[@value='Mobile Number']"
					+ "//..//..//following-sibling::tr/td[2]/input[@value='"+testData[7]+"']"));
			List<WebElement> mobNumber2 = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//input[@value='Mobile Number 2']"
					+ "//..//..//following-sibling::tr/td[2]/input[@value='"+testData[8]+"']"));
			List<WebElement> extNumber = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("(//input[@value='"+extData[1]+"'])[1]"));
			Assert.assertTrue(homePhone1.size()==1 && homePhone2.size()==1 && mobNumber1.size()==1 && 
					mobNumber2.size()==1 && extNumber.size()==1);
			
//			JavascriptExecutor js = (JavascriptExecutor)driver;
//			js.executeScript("arguments[0].scrollIntoView(true);", serverManagerPage.getSipPassword());
//			Thread.sleep(5000);
//			Assert.assertEquals(serverManagerPage.getSipPassword().getAttribute("value").trim(), extData[5]);
//			Assert.assertEquals(serverManagerPage.getSipConfirmPassword().getAttribute("value").trim(), extData[5]);
			
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//a[text()='MiCollab Client'])[2]"))).click();
//			serverManagerPage.getMicollabClientTab().click();
//			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//select[@name='ucaService.featureProfileID']/option[@selected='selected']")));
			String assignedRole = driver.findElement(By.xpath("//select[@name='ucaService.featureProfileID']/option[@selected='selected']")).getText().trim();
			String dskPhoneExt = driver.findElement(By.xpath("//select[@id='UCADeskphoneSelect']/option[@selected='selected']")).getText().trim();
			String mailBoxNum = driver.findElement(By.xpath("//select[@id='ucaMailboxSelect']/option[@selected='selected']")).getText().trim();
			
			Assert.assertEquals(assignedRole, extData[7]);
			Assert.assertTrue(dskPhoneExt.contains(extData[1]));
			Assert.assertEquals(mailBoxNum, extData[1]);
			
			serverManagerPage.getCancelButton().click();
			List<WebElement> role = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//img[@src='images/g_check_000.gif']"));
			Assert.assertTrue(role.size()==4);
			
			
			driver.switchTo().defaultContent();
			driver.switchTo().frame(serverManagerPage.getHeaderFrame());
			Thread.sleep(2000);
			serverManagerPage.getLogoutLink().click();
			
			//////////////////////////////////////change the role now////////////////////////////////////
			
			driver.get(ipData.getData(0, 0));
			pmLoginPage.PM_Login(credentials[0], credentials[1]);
			
			pmMainPage.getUsers().click();
			pmUsers.getUser().click();
			
			pmUser.getUserSearchTextBox().clear();
			pmUser.setUserSearchTextBox(extData[1]);
			pmUser.getOnViewRangeButton().click();
			
			driver.findElement(By.xpath("(//td[contains(text(),'"+testData[0]+"')])[1]"
					+ "//following-sibling::td[contains(text(),'"+extData[1]+" ')]//preceding-sibling::td[25]")).click();
			serverManagerPage.getMicollabConfigTab().click();
			new SelectDropDownValue().selectByVisibleText(authCodePage.getMicollabRoleDropDown(), "UCC (V4.0) Entry");
			driver.switchTo().alert().accept();
			pmExtension.getEditPageApplyButton().click();
			
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(@class,'responseMessage')]")));
			Assert.assertEquals(driver.findElement(By.xpath("//span[contains(@class,'responseMessage')]")).getText().trim(), "Change operation successful for:");
			
			
//			Assert.assertEquals(pmUser.getResponseMessage().getText().trim(), "Add operation successful for:");
			pmUser.getDoneButton().click();
//			pmUser.getDoneButton().click();
			
			pmUser.getUserSearchTextBox().clear();
			pmUser.setUserSearchTextBox(testData[0]);
			pmUser.getOnViewRangeButton().click();
			
			List<WebElement> eles1 = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("(//td[contains(text(),'"+testData[0]+"')])[1]//following-sibling::"
					+ "td[contains(text(),'"+extData[1]+" / SN')]"
					+ "//following-sibling::td[contains(text(),'Micollab')]"
					+ "//following-sibling::td[contains(text(),'UCC (V4.0) Entry')]"));
			Assert.assertTrue(eles.size() == 1);
			pmMainPage.getLogoutLink().click();
			
			///////////////////Again verify in Micollab Now///////////////////////
//			pmMainPage.getLogoutLink().click();
			driver.get(ipData.getData(2, 0));
			micoLogin.micollab_login(micoCredentials[0], micoCredentials[1]);
			driver.switchTo().frame(serverManagerPage.getInToNavigationFrame());
			serverManagerPage.getUsersAndServicesLink().click();
			driver.switchTo().defaultContent();
			driver.switchTo().frame(serverManagerPage.getMainFrame());
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='basicSearchTerm']")));
			serverManagerPage.getUserSearchTextBox().sendKeys(testData[0]);
			serverManagerPage.getSearchButton().click();
			Thread.sleep(1000);
			List<WebElement> phNumsEdit = driver.findElements(By.xpath("(//a[contains(text(),'"+testData[0]+"')]//parent::span//parent::td//following-sibling::td)[2]/span"));
			
			Assert.assertEquals(phNumsEdit.get(0).getText().trim(), extData[1]);
			Assert.assertEquals(phNumsEdit.get(1).getText().trim(), testData[5]);
			Assert.assertEquals(phNumsEdit.get(2).getText().trim(), testData[6]);
			Assert.assertEquals(phNumsEdit.get(3).getText().trim(), testData[7]);
			Assert.assertEquals(phNumsEdit.get(4).getText().trim(), testData[8]);
			
			driver.findElement(By.xpath("//a[contains(text(),'"+testData[0]+"')]")).click();
			Thread.sleep(1000);
			//System.out.println("First name is :"+serverManagerPage.getFirstNameTextBox().getText().trim());
			//Assert.assertEquals(serverManagerPage.getFirstNameTextBox().getAttribute("value").trim(), testData[0]);
			Assert.assertEquals(serverManagerPage.getLastNameTextBox().getAttribute("value").trim(), testData[0]);
			Assert.assertEquals(serverManagerPage.getEmailAddress().getAttribute("value").trim(), testData[2]);
			serverManagerPage.getPhoneTab().click();
			
			List<WebElement> homePhoneInEdit = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//input[@value='Home Phone']"
					+ "//..//..//following-sibling::tr/td[2]/input[@value='"+testData[5]+"']"));
			List<WebElement> homePhone2InEdit = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//input[@value='Home Phone 2']"
					+ "//..//..//following-sibling::tr/td[2]/input[@value='"+testData[6]+"']"));
			List<WebElement> mobNumber1InEdit = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//input[@value='Mobile Number']"
					+ "//..//..//following-sibling::tr/td[2]/input[@value='"+testData[7]+"']"));
			List<WebElement> mobNumber2InEdit = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//input[@value='Mobile Number 2']"
					+ "//..//..//following-sibling::tr/td[2]/input[@value='"+testData[8]+"']"));
			List<WebElement> extNumberInEdit = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("(//input[@value='"+extData[1]+"'])[1]"));
			Assert.assertTrue(homePhoneInEdit.size()==1 && homePhone2InEdit.size()==1 && mobNumber1InEdit.size()==1 && 
					mobNumber2InEdit.size()==1 && extNumberInEdit.size()==1);
//			Thread.sleep(1000);
//			JavascriptExecutor js = (JavascriptExecutor)driver;
//			js.executeScript("arguments[0].scrollIntoView(true);", serverManagerPage.getSipPassword());
//			Thread.sleep(5000);
//			Assert.assertEquals(serverManagerPage.getSipPassword().getAttribute("value").trim(), extData[5]);
//			Assert.assertEquals(serverManagerPage.getSipConfirmPassword().getAttribute("value").trim(), extData[5]);
			
//			serverManagerPage.getMicollabClientTab().click();
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//a[text()='MiCollab Client'])[2]"))).click();
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//select[@name='ucaService.featureProfileID']/option[@selected='selected']")));
			String assignedRoleEdit = driver.findElement(By.xpath("//select[@name='ucaService.featureProfileID']/option[@selected='selected']")).getText().trim();
			String dskPhoneExtEdit = driver.findElement(By.xpath("//select[@id='UCADeskphoneSelect']/option[@selected='selected']")).getText().trim();
			String mailBoxNumEdit = driver.findElement(By.xpath("//select[@id='ucaMailboxSelect']/option[@selected='selected']")).getText().trim();
			
			Assert.assertEquals(assignedRoleEdit, "UCC (V4.0) Entry");
			Assert.assertTrue(dskPhoneExtEdit.contains(extData[1]));
			Assert.assertEquals(mailBoxNumEdit, extData[1]);
			
			
			serverManagerPage.getCancelButton().click();
			List<WebElement> roleEdit = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//img[@src='images/g_check_000.gif']"));
			Assert.assertTrue(roleEdit.size()==2);
			
			driver.switchTo().defaultContent();
			driver.switchTo().frame(serverManagerPage.getHeaderFrame());
			Thread.sleep(2000);
			serverManagerPage.getLogoutLink().click();
			
			
			
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
			try
			{
				new CleanUP(driver).deleteUser(driver, ipData, credentials, testData[0]);
				list.clear();
				list.add(extData[8]);
				list.add(extData[9]);
				list.add(extData[10]);
				new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
			}
			catch(Exception e)
			{
					new Take_Screenshot().get_Screenshot(driver, method.getName());
					throw e;
			}
		}
	}
	
	
	
	@Test
	public void test_edit_micollab_Role_from_premium_to_standard(Method method) throws Exception
	{
		log.debug("Beginning of the test case.");
		authCodePage = new PM_Auth_Code_Page(driver);
		micoLogin = new Micollab_Login_Page(driver);
		serverManagerPage = new Micollab_Server_Manager_Page(driver);
		pmLoginPage = new PM_Login_Page(driver);
		pmMainPage = new PM_Main_Page(driver);
		pmUser = new PM_User(driver);
		pmUsers = new PM_Users(driver);
		pmExtension = new Extension(driver);
		
		
		list = new ArrayList<String>();
		String[] credentials = loginData.getData("test_pm_valid_login", 1);
		String[] testData = pmTests.getData(method.getName(), 1);
		String[] extData = pmTests.getData(method.getName(), 3);
		String[] micoCredentials = loginData.getData("test_micollab_login", 1);
		wait = new WebDriverWait(driver, 20);
		try 
		{
			pmTests.checkTestStatus(method.getName());
			pmUser = new PM_User(driver);
			pmMainPage = new PM_Main_Page(driver);
			list.add(extData[0]);
			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
			new ReusableUnits(driver).navigateUserToServiceSummaryPage(driver, method.getName(), ipData, loginData, pmTests);
			
			
			pmUser.getCreateAndAssignExtensionToUser().click();
			new SelectDropDownValue().selectByVisibleText(pmExtension.getExtensionType(), "IP");
			pmExtension.getNextButton().click();
			
			//Provide extension details.
			new SelectDropDownValue().selectByVisibleText(pmExtension.getSelectExtensionsRange(), extData[1]);
			String version = new GetMxoneVersionNumber(driver).getMxoneVersionNumber(driver);
			System.out.println(version);
			int ver = Integer.parseInt(version);
			System.out.println(ver);
			if(ver >= 72000)
			{
				pmExtension.setSingleExtensionValue(extData[1]);
			}
			else
			{
				new SelectDropDownValue().selectByVisibleText(pmExtension.getSingleExtensionDropDown(), extData[1]);
			}
			new SelectDropDownValue().selectByIndex(pmExtension.getMyCSPNameDropDown(),0);
			new SelectDropDownValue().selectByVisibleText(pmExtension.getServerDropDown(), "1");
			pmExtension.getFirstName().clear();
			pmExtension.getLastName().clear();
			pmExtension.setFirstName(extData[2]);
			pmExtension.setLastName(extData[3]);
			new SelectDropDownValue().selectByVisibleText(pmExtension.getPhoneTypeDropDown(), extData[4]);
			
			pmExtension.getAuthCodeButton().click();
			authCodePage.getAuthCodeAddButton().click();
			authCodePage.setAuthCodeTextBox(extData[5]);
			new SelectDropDownValue().selectByValue(authCodePage.gethashTypeDropDown(), extData[6]);
			authCodePage.setCilCode(extData[5]);
			authCodePage.getAuthPageApplyButton().click();
			
			List<WebElement> eleCount = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//td[contains(text(),'CLEARTEXT')]"));
			Assert.assertTrue(eleCount.size() == 1);
			
			authCodePage.getContinueButton().click();
			authCodePage.getContinueButtonToServiceSummaryPage().click();
			
//			authCodePage.getNextButtonToMicollabConfigPage().click();
			pmExtension.getNextButton().click();
			new SelectDropDownValue().selectByVisibleText(authCodePage.getMicollabRoleDropDown(), extData[7]);
			driver.switchTo().alert().accept();
			pmExtension.getApplyButton().click();
			
			WebDriverWait wait = new WebDriverWait(driver, 100);
//			wait.until(ExpectedConditions.textToBe(By.className("responseMessage"), "  Import operation successful for:"));
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(@class,'responseMessage')]")));
			Assert.assertEquals(driver.findElement(By.xpath("//span[contains(@class,'responseMessage')]")).getText().trim(), "Add operation successful for:");
			log.debug("Creation of Micollab User successful");
			
//			Assert.assertEquals(pmUser.getResponseMessage().getText().trim(), "Add operation successful for:");
			pmUser.getDoneButton().click();
//			pmUser.getDoneButton().click();
			
			pmUser.getUserSearchTextBox().clear();
			pmUser.setUserSearchTextBox(testData[0]);
			pmUser.getOnViewRangeButton().click();
			
			List<WebElement> eles = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("(//td[contains(text(),'"+testData[0]+"')])[1]//following-sibling::"
					+ "td[contains(text(),'"+extData[1]+" / SN')]"
					+ "//following-sibling::td[contains(text(),'Micollab')]"
					+ "//following-sibling::td[contains(text(),'"+extData[7]+"')]"));
			Assert.assertTrue(eles.size() == 1);
			
			pmMainPage.getLogoutLink().click();
			driver.get(ipData.getData(2, 0));
			Thread.sleep(1000);
			micoLogin.micollab_login(micoCredentials[0], micoCredentials[1]);
			
			log.debug("Login to Micollab successful");
			
			driver.switchTo().frame(serverManagerPage.getInToNavigationFrame());
			serverManagerPage.getUsersAndServicesLink().click();
			driver.switchTo().defaultContent();
			driver.switchTo().frame(serverManagerPage.getMainFrame());
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='basicSearchTerm']")));
			serverManagerPage.getUserSearchTextBox().sendKeys(testData[0]);
			serverManagerPage.getSearchButton().click();
			Thread.sleep(1000);
			List<WebElement> phNums = driver.findElements(By.xpath("(//a[contains(text(),'"+testData[0]+"')]//parent::span//parent::td//following-sibling::td)[2]/span"));
			
			Assert.assertEquals(phNums.get(0).getText().trim(), extData[1]);
			Assert.assertEquals(phNums.get(1).getText().trim(), testData[5]);
			Assert.assertEquals(phNums.get(2).getText().trim(), testData[6]);
			Assert.assertEquals(phNums.get(3).getText().trim(), testData[7]);
			Assert.assertEquals(phNums.get(4).getText().trim(), testData[8]);
			
			driver.findElement(By.xpath("//a[contains(text(),'"+testData[0]+"')]")).click();
		//	wait.until(ExpectedConditions.elementToBeClickable(By.name("firstNameDisplay")));
			Thread.sleep(1000);
//			System.out.println("First name is :"+serverManagerPage.getFirstNameTextBox().getText().trim());
	//		Assert.assertEquals(serverManagerPage.getFirstNameTextBox().getAttribute("value").trim(), testData[0]);
			Assert.assertEquals(serverManagerPage.getLastNameTextBox().getAttribute("value").trim(), testData[0]);
			Assert.assertEquals(serverManagerPage.getEmailAddress().getAttribute("value").trim(), testData[2]);
			
			wait.until(ExpectedConditions.elementToBeClickable(By.id("phones_0"))).click();
			Thread.sleep(2000);
//			serverManagerPage.getPhoneTab().click();
//			Thread.sleep(3000);
//			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@value='Home Phone']"
//					+ "//..//..//following-sibling::tr/td[2]/input[@value='"+testData[5]+"']")));
			
			List<WebElement> homePhone1 = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//input[@value='Home Phone']"
					+ "//..//..//following-sibling::tr/td[2]/input[@value='"+testData[5]+"']"));
			List<WebElement> homePhone2 = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//input[@value='Home Phone 2']"
					+ "//..//..//following-sibling::tr/td[2]/input[@value='"+testData[6]+"']"));
			List<WebElement> mobNumber1 = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//input[@value='Mobile Number']"
					+ "//..//..//following-sibling::tr/td[2]/input[@value='"+testData[7]+"']"));
			List<WebElement> mobNumber2 = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//input[@value='Mobile Number 2']"
					+ "//..//..//following-sibling::tr/td[2]/input[@value='"+testData[8]+"']"));
			List<WebElement> extNumber = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("(//input[@value='"+extData[1]+"'])[1]"));
			Assert.assertTrue(homePhone1.size()==1 && homePhone2.size()==1 && mobNumber1.size()==1 && 
					mobNumber2.size()==1 && extNumber.size()==1);
			
//			JavascriptExecutor js = (JavascriptExecutor)driver;
//			js.executeScript("arguments[0].scrollIntoView(true);", serverManagerPage.getSipPassword());
//			Thread.sleep(5000);
//			Assert.assertEquals(serverManagerPage.getSipPassword().getAttribute("value").trim(), extData[5]);
//			Assert.assertEquals(serverManagerPage.getSipConfirmPassword().getAttribute("value").trim(), extData[5]);
			
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//a[text()='MiCollab Client'])[2]"))).click();
//			serverManagerPage.getMicollabClientTab().click();
//			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//select[@name='ucaService.featureProfileID']/option[@selected='selected']")));
			String assignedRole = driver.findElement(By.xpath("//select[@name='ucaService.featureProfileID']/option[@selected='selected']")).getText().trim();
			String dskPhoneExt = driver.findElement(By.xpath("//select[@id='UCADeskphoneSelect']/option[@selected='selected']")).getText().trim();
			String mailBoxNum = driver.findElement(By.xpath("//select[@id='ucaMailboxSelect']/option[@selected='selected']")).getText().trim();
			
			Assert.assertEquals(assignedRole, extData[7]);
			Assert.assertTrue(dskPhoneExt.contains(extData[1]));
			Assert.assertEquals(mailBoxNum, extData[1]);
			
			serverManagerPage.getCancelButton().click();
			List<WebElement> role = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//img[@src='images/g_check_000.gif']"));
			Assert.assertTrue(role.size()==4);
			
			log.debug("Data properly synced to Micollab");
			
			driver.switchTo().defaultContent();
			driver.switchTo().frame(serverManagerPage.getHeaderFrame());
			Thread.sleep(2000);
			serverManagerPage.getLogoutLink().click();
			

			//////////////////////////////////////change the role now////////////////////////////////////
			
			driver.get(ipData.getData(0, 0));
			pmLoginPage.PM_Login(credentials[0], credentials[1]);
			
			pmMainPage.getUsers().click();
			pmUsers.getUser().click();
			
			pmUser.getUserSearchTextBox().clear();
			pmUser.setUserSearchTextBox(extData[1]);
			pmUser.getOnViewRangeButton().click();
			
			driver.findElement(By.xpath("(//td[contains(text(),'"+testData[0]+"')])[1]"
					+ "//following-sibling::td[contains(text(),'"+extData[1]+" ')]//preceding-sibling::td[25]")).click();
			serverManagerPage.getMicollabConfigTab().click();
			new SelectDropDownValue().selectByVisibleText(authCodePage.getMicollabRoleDropDown(), "UCC (V4.0) Standard");
			driver.switchTo().alert().accept();
			pmExtension.getEditPageApplyButton().click();
			
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(@class,'responseMessage')]")));
			Assert.assertEquals(driver.findElement(By.xpath("//span[contains(@class,'responseMessage')]")).getText().trim(), "Change operation successful for:");
			
			
//			Assert.assertEquals(pmUser.getResponseMessage().getText().trim(), "Add operation successful for:");
			pmUser.getDoneButton().click();
//			pmUser.getDoneButton().click();
			
			pmUser.getUserSearchTextBox().clear();
			pmUser.setUserSearchTextBox(testData[0]);
			pmUser.getOnViewRangeButton().click();
			
			List<WebElement> eles1 = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("(//td[contains(text(),'"+testData[0]+"')])[1]//following-sibling::"
					+ "td[contains(text(),'"+extData[1]+" / SN')]"
					+ "//following-sibling::td[contains(text(),'Micollab')]"
					+ "//following-sibling::td[contains(text(),'UCC (V4.0) Standard')]"));
			Assert.assertTrue(eles.size() == 1);
			
			log.debug("Successfully changed the role to Standard");
			
			pmMainPage.getLogoutLink().click();
			
			///////////////////Again verify in Micollab Now///////////////////////
//			pmMainPage.getLogoutLink().click();
			driver.get(ipData.getData(2, 0));
			micoLogin.micollab_login(micoCredentials[0], micoCredentials[1]);
			driver.switchTo().frame(serverManagerPage.getInToNavigationFrame());
			serverManagerPage.getUsersAndServicesLink().click();
			driver.switchTo().defaultContent();
			driver.switchTo().frame(serverManagerPage.getMainFrame());
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='basicSearchTerm']")));
			serverManagerPage.getUserSearchTextBox().sendKeys(testData[0]);
			serverManagerPage.getSearchButton().click();
			Thread.sleep(1000);
			List<WebElement> phNumsEdit = driver.findElements(By.xpath("(//a[contains(text(),'"+testData[0]+"')]//parent::span//parent::td//following-sibling::td)[2]/span"));
			
			Assert.assertEquals(phNumsEdit.get(0).getText().trim(), extData[1]);
			Assert.assertEquals(phNumsEdit.get(1).getText().trim(), testData[5]);
			Assert.assertEquals(phNumsEdit.get(2).getText().trim(), testData[6]);
			Assert.assertEquals(phNumsEdit.get(3).getText().trim(), testData[7]);
			Assert.assertEquals(phNumsEdit.get(4).getText().trim(), testData[8]);
			
			driver.findElement(By.xpath("//a[contains(text(),'"+testData[0]+"')]")).click();
			Thread.sleep(1000);
			//System.out.println("First name is :"+serverManagerPage.getFirstNameTextBox().getText().trim());
			//Assert.assertEquals(serverManagerPage.getFirstNameTextBox().getAttribute("value").trim(), testData[0]);
			Assert.assertEquals(serverManagerPage.getLastNameTextBox().getAttribute("value").trim(), testData[0]);
			Assert.assertEquals(serverManagerPage.getEmailAddress().getAttribute("value").trim(), testData[2]);
			serverManagerPage.getPhoneTab().click();
			
			List<WebElement> homePhoneInEdit = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//input[@value='Home Phone']"
					+ "//..//..//following-sibling::tr/td[2]/input[@value='"+testData[5]+"']"));
			List<WebElement> homePhone2InEdit = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//input[@value='Home Phone 2']"
					+ "//..//..//following-sibling::tr/td[2]/input[@value='"+testData[6]+"']"));
			List<WebElement> mobNumber1InEdit = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//input[@value='Mobile Number']"
					+ "//..//..//following-sibling::tr/td[2]/input[@value='"+testData[7]+"']"));
			List<WebElement> mobNumber2InEdit = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//input[@value='Mobile Number 2']"
					+ "//..//..//following-sibling::tr/td[2]/input[@value='"+testData[8]+"']"));
			List<WebElement> extNumberInEdit = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("(//input[@value='"+extData[1]+"'])[1]"));
			Assert.assertTrue(homePhoneInEdit.size()==1 && homePhone2InEdit.size()==1 && mobNumber1InEdit.size()==1 && 
					mobNumber2InEdit.size()==1 && extNumberInEdit.size()==1);
//			Thread.sleep(1000);
//			JavascriptExecutor js = (JavascriptExecutor)driver;
//			js.executeScript("arguments[0].scrollIntoView(true);", serverManagerPage.getSipPassword());
//			Thread.sleep(5000);
//			Assert.assertEquals(serverManagerPage.getSipPassword().getAttribute("value").trim(), extData[5]);
//			Assert.assertEquals(serverManagerPage.getSipConfirmPassword().getAttribute("value").trim(), extData[5]);
			
//			serverManagerPage.getMicollabClientTab().click();
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//a[text()='MiCollab Client'])[2]"))).click();
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//select[@name='ucaService.featureProfileID']/option[@selected='selected']")));
			String assignedRoleEdit = driver.findElement(By.xpath("//select[@name='ucaService.featureProfileID']/option[@selected='selected']")).getText().trim();
			String dskPhoneExtEdit = driver.findElement(By.xpath("//select[@id='UCADeskphoneSelect']/option[@selected='selected']")).getText().trim();
			String mailBoxNumEdit = driver.findElement(By.xpath("//select[@id='ucaMailboxSelect']/option[@selected='selected']")).getText().trim();
			
			Assert.assertEquals(assignedRoleEdit, "UCC (V4.0) Standard...");
			Assert.assertTrue(dskPhoneExtEdit.contains(extData[1]));
			Assert.assertEquals(mailBoxNumEdit, extData[1]);
			
			
			serverManagerPage.getCancelButton().click();
			List<WebElement> roleEdit = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//img[@src='images/g_check_000.gif']"));
			Assert.assertTrue(roleEdit.size()==4);
			
			log.debug("Changed role is properly synced to Micollab");
			
			driver.switchTo().defaultContent();
			driver.switchTo().frame(serverManagerPage.getHeaderFrame());
			Thread.sleep(2000);
			serverManagerPage.getLogoutLink().click();
			
			
		}
		catch(Error e)
		{
				new Take_Screenshot().get_Screenshot(driver, method.getName());
				log.fatal(e.toString());
				throw e;
		}
		catch(Exception e)
		{
				new Take_Screenshot().get_Screenshot(driver, method.getName());
				log.error(e.toString());
				throw e;
		}
		finally
		{
			try
			{
				new CleanUP(driver).deleteUser(driver, ipData, credentials, testData[0]);
				list.clear();
				list.add(extData[8]);
				list.add(extData[9]);
				list.add(extData[10]);
				new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
			}
			catch(Exception e)
			{
					new Take_Screenshot().get_Screenshot(driver, method.getName());
					log.fatal(e.toString());
					throw e;
			}
		}
	}
	
}
