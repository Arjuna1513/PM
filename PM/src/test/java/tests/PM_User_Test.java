package tests;

import static org.testng.Assert.assertEquals;

import java.lang.reflect.Method;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.ConfigClass;
import pm_pom_classes.PM_Login_Page;
import pm_pom_classes.PM_Main_Page;
import pm_pom_classes.PM_User;
import pm_pom_classes.PM_Users;
import utilities.CleanUP;
import utilities.ReusableUnits;
import utilities.SelectDropDownValue;

public class PM_User_Test extends ConfigClass
{
	public PM_Login_Page pmLoginPage;
	public PM_Main_Page pmMainPage;
	public PM_User pmUser;
	public PM_Users pmUsers;
	
	@Test
	public void test_create_user(Method method) throws InterruptedException
	{
		try 
		{
			pmTests.checkTestStatus(method.getName());
			new ReusableUnits(driver).createUser(driver, method.getName(), ipData, loginData, pmTests);
		}
		finally
		{
			String[] credentials = loginData.getData("test_pm_valid_login", 1);
			String[] testData = pmTests.getData(method.getName(), 1);
			new CleanUP().deleteUser(driver, ipData, credentials, testData[0]);
		}
		
	}
	
	@Test
	public void test_edit_user(Method method) throws InterruptedException
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
		finally
		{
			String[] credentials = loginData.getData("test_pm_valid_login", 1);
			String[] testData = pmTests.getData(method.getName(), 1);
			new CleanUP().deleteUser(driver, ipData, credentials, testData[9]);
		}
	}
	
}
