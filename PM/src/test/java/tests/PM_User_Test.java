package tests;

import static org.testng.Assert.assertEquals;

import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.ConfigClass;
import pm_pom_classes.PM_Login_Page;
import pm_pom_classes.PM_Main_Page;
import pm_pom_classes.PM_User;
import pm_pom_classes.PM_Users;
import utilities.CleanUP;
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
			pmMainPage = new PM_Main_Page(driver);
			pmLoginPage = new PM_Login_Page(driver);
			pmUser = new PM_User(driver);
			pmUsers = new PM_Users(driver);
			driver.get(ipData.getData(0, 0));
			String[] data = loginData.getData("test_pm_valid_login", 1);
			String[] testData = pmTests.getData(method.getName(), 1);
			pmLoginPage.PM_Login(data[0], data[1]);
			pmMainPage.getUsers().click();
			pmUsers.getUser().click();
			pmUser.getAddButton().click();
			pmUser.setFirstNamefield(testData[0]);
			pmUser.setLastNamefield(testData[0]);
			pmUser.setUserIDField(testData[0]);
			Thread.sleep(1000);
			pmUser.setUserPasswordField(testData[1]);
			pmUser.setUserConfirmPasswordField(testData[1]);
			pmUser.setEmailIDField(testData[2]);
			pmUser.setAlternateFirstName(testData[3]);
			pmUser.setAltLastName(testData[4]);
			pmUser.setBusinessField(testData[5]);
			pmUser.setBusiness2(testData[6]);
			pmUser.setMobilePhone(testData[7]);
			pmUser.setMobilePhone2(testData[8]);
			new SelectDropDownValue().selectByIndex(pmUser.getSelectDepartmentDropdown(), 0);
			pmUser.getListFilterAddButton_mySelectedDepts().click();
	//		Thread.sleep(5000);
			pmUser.getApplyButton().click();
	//		Thread.sleep(5000);
			Assert.assertEquals(pmUser.getResponseMessage().getText().trim(), "Add operation successful for:");
			pmUser.getDoneButton().click();
			pmUser.getLogoutLink().click();
		}
		finally
		{
			String[] data = loginData.getData("test_pm_valid_login", 1);
			String[] testData = pmTests.getData(method.getName(), 1);
			new CleanUP().deleteUser(driver, ipData, data, testData);
		}
		
	}
	
}
