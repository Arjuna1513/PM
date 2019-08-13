package tests;

import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import base.ConfigClass;
import pm_pom_classes.PM_Login_Page;
import pm_pom_classes.PM_Main_Page;
import pm_pom_classes.PM_User;
import pm_pom_classes.PM_Users;

public class PM_User_Tests extends ConfigClass
{
	public PM_Login_Page pmLoginPage;
	public PM_Main_Page pmMainPage;
	public PM_User pmUser;
	public PM_Users pmUsers;
	
	public PM_User_Tests(WebDriver driver)
	{
		pmLoginPage = new PM_Login_Page(driver);
		pmMainPage = new PM_Main_Page(driver);
		pmUser = new PM_User(driver);
		pmUsers = new PM_Users(driver);
	}
	
	@Test
	public void test_create_user(Method method)
	{
		loginData.checkTestStatus(method.getName());
		driver.get(ipData.getData(0, 0));
		String[] data = loginData.getData("test_pm_valid_login", 0, 1);
		pmLoginPage.PM_Login(data[0], data[1]);		
	}
	
}
