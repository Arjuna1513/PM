package tests;

import java.lang.reflect.Method;
import java.util.ArrayList;

import org.testng.annotations.Test;

import base.ConfigClass;
import pm_pom_classes.PM_Login_Page;
import pm_pom_classes.PM_User;
import utilities.ExecuteCommands;
import utilities.ReusableUnits;

public class PM_Extension_Test extends ConfigClass
{
	public ArrayList<String> list;
	public PM_Login_Page loginPage;
	public PM_User pmUser;
	
	@Test
	public void test_create_IP_extension(Method method) throws InterruptedException
	{
		pmTests.checkTestStatus(method.getName());
		pmUser = new PM_User(driver);
		String[] testData = pmTests.getData(method.getName(), 1);
		list = new ArrayList<String>();
		list.add(testData[0]);
		new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
		new ReusableUnits(driver).createExtension(driver, method.getName(), ipData, loginData, pmTests);
		pmUser.getLogoutLink().click();
	}
}
