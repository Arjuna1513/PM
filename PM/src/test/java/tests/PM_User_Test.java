package tests;

import static org.testng.Assert.assertEquals;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.ConfigClass;
import pm_pom_classes.Extension;
import pm_pom_classes.PM_Login_Page;
import pm_pom_classes.PM_Main_Page;
import pm_pom_classes.PM_User;
import pm_pom_classes.PM_Users;
import utilities.CleanUP;
import utilities.ExecuteCommands;
import utilities.GetMxoneVersionNumber;
import utilities.ReusableUnits;
import utilities.SelectDropDownValue;
import utilities.Take_Screenshot;

public class PM_User_Test extends ConfigClass
{
	public PM_Login_Page pmLoginPage;
	public PM_Main_Page pmMainPage;
	public PM_User pmUser;
	public PM_Users pmUsers;
	ArrayList<String> list = null;
	WebDriverWait wait = null;
	public Extension pmExtension;
	
	@Test
	public void test_create_user(Method method) throws Exception
	{
		try 
		{
//			Thread.sleep(5000);
			pmTests.checkTestStatus(method.getName());
			new ReusableUnits(driver).createUser(driver, method.getName(), ipData, loginData, pmTests);
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
			String[] credentials = loginData.getData("test_pm_valid_login", 1);
			String[] testData = pmTests.getData(method.getName(), 1);
			new CleanUP(driver).deleteUser(driver, ipData, credentials, testData[0]);
		}
		
	}
/*	
	@Test
	public void test_edit_user(Method method) throws Exception
	{
		try 
		{
			pmTests.checkTestStatus(method.getName());
			pmUser = new PM_User(driver);
			pmUsers = new PM_Users(driver);
			pmLoginPage = new PM_Login_Page(driver);
			pmMainPage = new PM_Main_Page(driver);
			String[] testData = pmTests.getData(method.getName(), 1);
			new ReusableUnits(driver).createUser(driver, method.getName(), ipData, loginData, pmTests);
			driver.findElement(By.xpath("(//td[contains(text(),'"+testData[0]+"')]//preceding-sibling::td)[7]")).click();
			pmUser.getFirstName().clear();
			pmUser.setFirstNamefield(testData[9]);
			pmUser.getLastName().clear();
			pmUser.setLastNamefield(testData[9]);
			pmUser.getUserIDField().clear();
			pmUser.setUserIDField(testData[9]);
			pmUser.getApplyButton().click();
			Assert.assertEquals(pmUser.getResponseMessage().getText().trim(), "Change operation successful for:");
			pmUser.getDoneButton().click();
			pmUser.getUserSearchTextBox().clear();
			pmUser.setUserSearchTextBox(testData[9]);
			pmUser.getOnViewRangeButton().click();
			List<WebElement> eles = driver.findElements(By.xpath("(//td[contains(text(),'"+testData[9]+"')])[1]"));
			Assert.assertTrue(eles.size() > 0);
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
			String[] credentials = loginData.getData("test_pm_valid_login", 1);
			String[] testData = pmTests.getData(method.getName(), 1);
			new CleanUP(driver).deleteUser(driver, ipData, credentials, testData[9]);
		}
	}
	
	@Test
	public void test_delete_user(Method method) throws Exception
	{
		try
		{
			pmTests.checkTestStatus(method.getName());
			pmUser = new PM_User(driver);
			new ReusableUnits(driver).createUser(driver, method.getName(), ipData, loginData, pmTests);
			String[] credentials = loginData.getData("test_pm_valid_login", 1);
			String[] testData = pmTests.getData(method.getName(), 1);
			driver.findElement(By.xpath("(//td[contains(text(),'"+testData[0]+"')]//preceding-sibling::td)[8]")).click();
			driver.switchTo().alert().accept();
			Assert.assertEquals(pmUser.getResponseMessage().getText().trim(), "Remove operation successful for:");
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
	}
	
	@Test
	public void test_userSearchUsingViewSymbol(Method method) throws Exception
	{
		try
		{
			pmTests.checkTestStatus(method.getName());
			new ReusableUnits(driver).createUser(driver, method.getName(), ipData, loginData, pmTests);
			String[] testData = pmTests.getData(method.getName(), 1);
			driver.findElement(By.xpath("(//td[contains(text(),'"+testData[0]+"')])[1]//preceding-sibling::td[20]")).click();
			List<WebElement> eles = driver.findElements(By.xpath("//td[contains(text(),'User Id')]//following-sibling::td[contains(text(),'"+testData[0]+"')]"));
			Assert.assertTrue(eles.size() > 0);
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
			String[] credentials = loginData.getData("test_pm_valid_login", 1);
			String[] testData = pmTests.getData(method.getName(), 1);
			new CleanUP(driver).deleteUser(driver, ipData, credentials, testData[0]);
		}
	}
	
	
	@Test
	public void test_delete_user_byClicking_delete_button(Method method) throws Exception
	{
		try
		{
			pmTests.checkTestStatus(method.getName());
			pmUser = new PM_User(driver);
			new ReusableUnits(driver).createUser(driver, method.getName(), ipData, loginData, pmTests);
			String[] testData = pmTests.getData(method.getName(), 1);
			driver.findElement(By.xpath("(//td[contains(text(),'"+testData[0]+"')])[1]//preceding-sibling::td[25]")).click();
			pmUser.getRemoveSelected().click();
			driver.switchTo().alert().accept();
			Assert.assertEquals(pmUser.getResponseMessage().getText().trim(), "Remove operation successful for:");
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
			String[] credentials = loginData.getData("test_pm_valid_login", 1);
			String[] testData = pmTests.getData(method.getName(), 1);
			new CleanUP(driver).deleteUser(driver, ipData, credentials, testData[0]);
		}
	}
	
	@Test
	public void test_user_print(Method method) throws Exception
	{
		try
		{
			pmTests.checkTestStatus(method.getName());
			pmUser = new PM_User(driver);
			new ReusableUnits(driver).createUser(driver, method.getName(), ipData, loginData, pmTests);
			String[] testData = pmTests.getData(method.getName(), 1);
			driver.findElement(By.xpath("(//td[contains(text(),'"+testData[0]+"')])[1]//preceding-sibling::td[25]")).click();
			String parentWindow = driver.getWindowHandle();
			pmUser.getUsrPrintButton().click();
			Thread.sleep(1000);
			Set<String> windows = driver.getWindowHandles();
			for(String win:windows)
			{
				if(!(win.equals(parentWindow)))
				{
					driver.switchTo().window(win);
					List<WebElement> usrId = driver.findElements(By.xpath("//tr/td[contains(text(),'User Id')]//following-sibling::td[contains(text(),'"+testData[0]+"')]"));
					List<WebElement> fName = driver.findElements(By.xpath("//tr/td[contains(text(),'First Name')]//following-sibling::td[contains(text(),'"+testData[0]+"')]"));
					List<WebElement> lName = driver.findElements(By.xpath("//tr/td[contains(text(),'Last Name')]//following-sibling::td[contains(text(),'"+testData[0]+"')]"));
					Assert.assertTrue(usrId.size() > 0 && fName.size() > 0 && lName.size() > 0);
					driver.close();
				}
			}
			driver.switchTo().window(parentWindow);
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
			String[] credentials = loginData.getData("test_pm_valid_login", 1);
			String[] testData = pmTests.getData(method.getName(), 1);
			new CleanUP(driver).deleteUser(driver, ipData, credentials, testData[0]);
		}
	}
	
	
	@Test
	public void test_viewUser_with_viewButton(Method method) throws Exception
	{
		try
		{
			pmTests.checkTestStatus(method.getName());
			pmUser = new PM_User(driver);
			new ReusableUnits(driver).createUser(driver, method.getName(), ipData, loginData, pmTests);
			String[] testData = pmTests.getData(method.getName(), 1);
			driver.findElement(By.xpath("(//td[contains(text(),'"+testData[0]+"')])[1]//preceding-sibling::td[25]")).click();
			pmUser.getViewButton().click();
			List<WebElement> usrId = driver.findElements(By.xpath("//tr/td[contains(text(),'User Id')]//following-sibling::td[contains(text(),'"+testData[0]+"')]"));
			List<WebElement> fName = driver.findElements(By.xpath("//tr/td[contains(text(),'First Name')]//following-sibling::td[contains(text(),'"+testData[0]+"')]"));
			List<WebElement> lName = driver.findElements(By.xpath("//tr/td[contains(text(),'Last Name')]//following-sibling::td[contains(text(),'"+testData[0]+"')]"));
			Assert.assertTrue(usrId.size() > 0 && fName.size() > 0 && lName.size() > 0);
			pmUser.getDoneButton().click();
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
			String[] credentials = loginData.getData("test_pm_valid_login", 1);
			String[] testData = pmTests.getData(method.getName(), 1);
			new CleanUP(driver).deleteUser(driver, ipData, credentials, testData[0]);
		}
	}
	
	@Test
	public void test_verify_helpText(Method method) throws Exception
	{
		try
		{	pmTests.checkTestStatus(method.getName());
			driver.get(ipData.getData(0, 0));
			pmUser = new PM_User(driver);
			pmUsers = new PM_Users(driver);
			pmLoginPage = new PM_Login_Page(driver);
			pmMainPage = new PM_Main_Page(driver);
			String[] credentials = loginData.getData("test_pm_valid_login", 1);
			pmLoginPage.PM_Login(credentials[0], credentials[1]);
			pmMainPage.getUsers().click();
			pmUsers.getUser().click();
			String parentWindow = driver.getWindowHandle();
			pmUser.getHelpLink().click();
			Thread.sleep(1000);
			Set<String> windows = driver.getWindowHandles();
			for(String win:windows)
			{
				if(!(win.equals(parentWindow)))
				{
					driver.switchTo().window(win);
					Assert.assertEquals(driver.findElement(By.xpath("(//p)[1]")).getText().trim(), 
							"Users can be added one by one or be imported from files extracted from other "
							+ "user management systems. When the system is installed a System Setup Admin "
							+ "is added. All users except the System Setup Admin can be removed.");
					
					Assert.assertEquals(driver.findElement(By.xpath("(//p)[2]")).getText().trim(), 
							"After installation it is recommended to create an extra System Setup Admin to "
							+ "keep as backup if something happens to the original System Setup Admin account,"
							+ " for example if the password is lost, otherwise the whole system must be reinstalled.");
					
					Assert.assertEquals(driver.findElement(By.xpath("(//p)[3]")).getText().trim(), 
							"When a user is added, it is automatically assigned the security profile End User."
							+ " An end user can be promoted to administrator by assigning the user a different "
							+ "security profile and defining access to departments and locations in the Administrator task.");
					
					Assert.assertEquals(driver.findElement(By.xpath("(//p)[4]")).getText().trim(), 
							"The logged on administrator can only see the users, administrators and "
							+ "administrator users, that the logged on administrator has added. An "
							+ "administrator at a lower level could not see the administrator on a higher "
							+ "level in the hierarchy");
					
					Assert.assertEquals(driver.findElement(By.xpath("(//p)[5]")).getText().trim(), 
							"Services, for example extensions and mailboxes, can be assigned to users. "
							+ "Services are provided by the configured subsystems.");
					
					Assert.assertEquals(driver.findElement(By.xpath("(//p)[6]")).getText().trim(), 
							"The privilege Manage user data is required to add, change or remove users. "
							+ "Privileges are set in the Security Profile task.");
					
					Assert.assertEquals(driver.findElement(By.xpath("(//p)[7]")).getText().trim(), 
							"User Defined Fields (UDFs) are defined in the UDF Mapping task by an "
							+ "administrator assigned the privilege Manage UDF data.");
					driver.close();
				}
			}
			driver.switchTo().window(parentWindow);
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
	}
	
	@Test
	public void test_delete_multipleUsers(Method method) throws Exception
	{
		pmUser = new PM_User(driver);
		try 
		{
			pmTests.checkTestStatus(method.getName());
			new ReusableUnits(driver).createUser(driver, method.getName(), ipData, loginData, pmTests, 10);
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
			String[] credentials = loginData.getData("test_pm_valid_login", 1);
			String[] testData = pmTests.getData(method.getName(), 1);
			new CleanUP(driver).deleteUser(driver, ipData, credentials, testData[0], 10);
		}
	}
	
	
	
	@Test
	public void test_search_using_wildCard_star(Method method) throws Exception
	{
			pmUser = new PM_User(driver);
		try 
		{
			pmTests.checkTestStatus(method.getName());
			String[] testData = pmTests.getData(method.getName(), 1);
			new ReusableUnits(driver).createUser(driver, method.getName(), ipData, loginData, pmTests, 10);
			pmUser.getUserSearchTextBox().clear();
			pmUser.setUserSearchTextBox("*");
			pmUser.getOnViewRangeButton().click();
			List<WebElement> eles = driver.findElements(By.xpath("//input[@name='selectItem']"));
			Assert.assertTrue(eles.size()>=10);
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
			String[] credentials = loginData.getData("test_pm_valid_login", 1);
			String[] testData = pmTests.getData(method.getName(), 1);
			new CleanUP(driver).deleteUser(driver, ipData, credentials, testData[0], 10);
		}
	}
	
	
	@Test
	public void test_search_using_wildCard_questionMark(Method method) throws Exception
	{
			pmUser = new PM_User(driver);
		try 
		{
			pmTests.checkTestStatus(method.getName());
			String[] testData = pmTests.getData(method.getName(), 1);
			new ReusableUnits(driver).createUser(driver, method.getName(), ipData, loginData, pmTests);
			pmUser.getUserSearchTextBox().clear();
			pmUser.setUserSearchTextBox(testData[9]+"?");
			pmUser.getOnViewRangeButton().click();
			List<WebElement> eles = driver.findElements(By.xpath("(//td[contains(text(),'"+testData[0]+"')])[1]"));
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
			String[] credentials = loginData.getData("test_pm_valid_login", 1);
			String[] testData = pmTests.getData(method.getName(), 1);
			new CleanUP(driver).deleteUser(driver, ipData, credentials, testData[0]);
		}
	}
	
	@Test
	public void test_createUserWithExtension(Method method) throws Exception
	{
		list = new ArrayList<String>();
		String[] credentials = loginData.getData("test_pm_valid_login", 1);
		String[] testData = pmTests.getData(method.getName(), 1);
		try
		{
			pmTests.checkTestStatus(method.getName());
			new ReusableUnits(driver).createUserWithExtension(driver, method.getName(), ipData, loginData, pmTests);
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
			list.add(extData[7]);
			list.add(extData[8]);
			list.add(extData[9]);
			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
		}
	}
	
	@Test
	public void test_editUserToRemoveExtension(Method method) throws Exception
	{
		list = new ArrayList<String>();
		String[] credentials = loginData.getData("test_pm_valid_login", 1);
		String[] testData = pmTests.getData(method.getName(), 1);
		String[] extData = pmTests.getData(method.getName(), 3);
		try
		{
			pmTests.checkTestStatus(method.getName());
			pmUser = new PM_User(driver);
			pmMainPage = new PM_Main_Page(driver);
			new ReusableUnits(driver).createUserWithExtension(driver, method.getName(), ipData, loginData, pmTests);
			driver.findElement(By.xpath("(//td[contains(text(),'"+testData[0]+"')])[1]"
			+ "//following-sibling::td[contains(text(),'"+extData[2]+" ')]//preceding-sibling::td[25]")).click();
			pmUser.getUserEditServiceSummary().click();
			driver.findElement(By.id("removeThis"+extData[2]+"-IP-SN_img")).click();
			driver.switchTo().alert().accept();
			Thread.sleep(2000);
			pmUser.getApplyButton().click();
			Assert.assertEquals(pmUser.getResponseMessage().getText().trim(), "Change operation successful for:");
			pmUser.getDoneButton().click();
			
			pmUser.getUserSearchTextBox().clear();
			pmUser.setUserSearchTextBox(testData[0]);
			pmUser.getOnViewRangeButton().click();
			
			List<WebElement> eles = driver.findElements(By.xpath("(//td[contains(text(),'"+testData[0]+"')])[1]//following-sibling::td[contains(text(),'"+extData[2]+"')]"));
			Assert.assertTrue(eles.size()==0);
			
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
			list.add(extData[7]);
			list.add(extData[8]);
			list.add(extData[9]);
			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
		}
	}
	
	@Test
	public void test_editUserAssignExistingExtension(Method method) throws Exception
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
			pmMainPage = new PM_Main_Page(driver);
			list.add(extData[0]);
			list.add(extData[1]);
			list.add(extData[2]);
			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
			new ReusableUnits(driver).createUser(driver, method.getName(), ipData, loginData, pmTests);
			driver.findElement(By.xpath("(//td[contains(text(),'"+testData[0]+"')])[1]//preceding-sibling::td[19]")).click();
			pmUser.getUserEditServiceSummary().click();
			pmUser.setExistingExtensionField(extData[3]);
			pmUser.getApplyButton().click();
			Assert.assertEquals(pmUser.getResponseMessage().getText().trim(), "Change operation successful for:");
			pmUser.getDoneButton().click();
			
			pmUser.getUserSearchTextBox().clear();
			pmUser.setUserSearchTextBox(testData[0]);
			pmUser.getOnViewRangeButton().click();
			List<WebElement> eles = driver.findElements(By.xpath("(//td[contains(text(),'"+testData[0]+"')])[1]//following-sibling::td[contains(text(),'"+extData[3]+"')]"));
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
			list.add(extData[4]);
			list.add(extData[5]);
			list.add(extData[6]);
			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
		}
	}
	
	@Test
	public void test_createUserAssignExistingExtension(Method method) throws Exception
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
			pmMainPage = new PM_Main_Page(driver);
			list.add(extData[0]);
			list.add(extData[1]);
			list.add(extData[2]);
			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
			new ReusableUnits(driver).navigateUserToServiceSummaryPage(driver, method.getName(), ipData, loginData, pmTests);
			pmUser.setExistingExtensionField(extData[3]);
			pmUser.getApplyButton().click();
			Assert.assertEquals(pmUser.getResponseMessage().getText().trim(), "Add operation successful for:");
			pmUser.getDoneButton().click();
			
			pmUser.getUserSearchTextBox().clear();
			pmUser.setUserSearchTextBox(testData[0]);
			pmUser.getOnViewRangeButton().click();
			List<WebElement> eles = driver.findElements(By.xpath("(//td[contains(text(),'"+testData[0]+"')])[1]//following-sibling::td[contains(text(),'"+extData[3]+"')]"));
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
			list.add(extData[4]);
			list.add(extData[5]);
			list.add(extData[6]);
			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
		}
	}
	
	@Test
	public void test_createUserWithExtesnsionUsingIPTemplate(Method method) throws Exception
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
			pmMainPage = new PM_Main_Page(driver);
			pmExtension = new Extension(driver);
			pmUsers = new PM_Users(driver);
			list.add(extData[0]);
			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
			new ReusableUnits(driver).createIPTemplate(driver, method.getName(), ipData, loginData, extData[2]);
			new ReusableUnits(driver).navigateUserToServiceSummaryPage(driver, method.getName(), ipData, loginData, pmTests);
			new SelectDropDownValue().selectByValue(pmExtension.getExtensionHomePageTemplateDropDown(),
					"CUST."+extData[2]+"_SN_IPExtension");
			pmUser.getCreateAndAssignExtensionToUser().click();
			new SelectDropDownValue().selectByVisibleText(pmExtension.getSelectExtensionsRange(), extData[1]);
			String version = new GetMxoneVersionNumber(driver).getMxoneVersionNumber(driver);
			System.out.println(version);
			int ver = Integer.parseInt(version);
			System.out.println(ver);
			if(ver >= 720000)
			{
				pmExtension.setSingleExtensionValue(extData[1]);
			}
			else
			{
				new SelectDropDownValue().selectByVisibleText(pmExtension.getSingleExtensionDropDown(), extData[2]);
			}
			new SelectDropDownValue().selectByVisibleText(pmExtension.getPhoneTypeDropDown(), extData[3]);
			pmExtension.getApplyButton().click();
			
			pmUser.getApplyButton().click();
			Assert.assertEquals(pmUser.getResponseMessage().getText().trim(), "Add operation successful for:");
			pmUser.getDoneButton().click();
			pmMainPage.getUsers().click();
			pmUsers.getUser().click();
			pmUser.setUserSearchTextBox(extData[1]);
			pmUser.getOnViewRangeButton().click();
			List<WebElement> eles = driver.findElements(By.xpath("(//td[contains(text(),'"+testData[0]+"')])[1]//following-sibling::td[contains(text(),'"+extData[1]+"')]"));
			Assert.assertTrue(eles.size()==1);
			pmMainPage.getLogoutLink().click();
			
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
			new CleanUP(driver).deleteTemplate(driver, method.getName(), loginData, extData[2], ipData);
			new CleanUP(driver).deleteUser(driver, ipData, credentials, testData[0]);
			list.clear();
			list.add(extData[4]);
			list.add(extData[5]);
			list.add(extData[6]);
			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
		}
	}
	
	
	@Test
	public void test_editUserAssignNewExtension(Method method) throws Exception
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
			pmMainPage = new PM_Main_Page(driver);
			pmExtension = new Extension(driver);
			list.add(extData[0]);
			list.add(extData[1]);
			list.add(extData[2]);
			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
			new ReusableUnits(driver).createUser(driver, method.getName(), ipData, loginData, pmTests);
			driver.findElement(By.xpath("(//td[contains(text(),'"+testData[0]+"')])[1]//preceding-sibling::td[19]")).click();
			pmUser.getUserEditServiceSummary().click();
			pmUser.getCreateAndAssignExtensionToUser().click();
			new SelectDropDownValue().selectByVisibleText(pmExtension.getExtensionType(), "IP");
			pmExtension.getNextButton().click();
			
			//Provide extension details.
			new SelectDropDownValue().selectByVisibleText(pmExtension.getSelectExtensionsRange(), extData[1]);
			String version = new GetMxoneVersionNumber(driver).getMxoneVersionNumber(driver);
			System.out.println(version);
			int ver = Integer.parseInt(version);
			System.out.println(ver);
			if(ver >= 720000)
			{
				pmExtension.setSingleExtensionValue(extData[1]);
			}
			else
			{
				new SelectDropDownValue().selectByVisibleText(pmExtension.getSingleExtensionDropDown(), extData[1]);
			}
			new SelectDropDownValue().selectByIndex(pmExtension.getMyCSPNameDropDown(),0);
			new SelectDropDownValue().selectByIndex(pmExtension.getServerDropDown(), 1);
			pmExtension.setFirstName(extData[4]);
			pmExtension.setLastName(extData[5]);
			new SelectDropDownValue().selectByVisibleText(pmExtension.getPhoneTypeDropDown(), extData[2]);
			pmExtension.getApplyButton().click();
			
			pmUser.getApplyButton().click();
			Assert.assertEquals(pmUser.getResponseMessage().getText().trim(), "Change operation successful for:");
			pmUser.getDoneButton().click();
			
			pmUser.getUserSearchTextBox().clear();
			pmUser.setUserSearchTextBox(testData[0]);
			pmUser.getOnViewRangeButton().click();
			
			List<WebElement> eles = driver.findElements(By.xpath("(//td[contains(text(),'"+testData[0]+"')])[1]//following-sibling::td[contains(text(),'"+extData[1]+"')]"));
			Assert.assertTrue(eles.size()==1);
			pmMainPage.getLogoutLink().click();
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
			list.add(extData[3]);
			list.add(extData[4]);
			list.add(extData[5]);
			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
		}
	}
	*/
}
