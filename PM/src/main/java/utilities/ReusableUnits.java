package utilities;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import base.ConfigClass;
import pm_pom_classes.PM_Login_Page;
import pm_pom_classes.PM_Main_Page;
import pm_pom_classes.PM_User;
import pm_pom_classes.PM_Users;

public class ReusableUnits
{
	public PM_User pmUser;
	public PM_Users pmUsers;
	public PM_Login_Page pmLoginPge;
	public PM_Main_Page pmMainPge;
	
	public ReusableUnits(WebDriver driver)
	{
		pmUser = new PM_User(driver);
		pmUsers = new PM_Users(driver);
		pmLoginPge = new PM_Login_Page(driver);
		pmMainPge = new PM_Main_Page(driver);
	}
	
	public void createUser(WebDriver driver, String methodName, ExcelReadAndWrite ipData,
			ExcelReadAndWrite loginData,ExcelReadAndWrite pmTests) throws InterruptedException
	{
		driver.get(ipData.getData(0, 0));
		String[] credentials = loginData.getData("test_pm_valid_login", 1);
		String[] testData = pmTests.getData(methodName, 1);
		pmLoginPge.PM_Login(credentials[0], credentials[1]);
		pmMainPge.getUsers().click();
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
		pmUser.setUserSearchTextBox(testData[0]);
		pmUser.getOnViewRangeButton().click();
		List<WebElement> eles = driver.findElements(By.xpath("(//td[contains(text(),'"+testData[0]+"')])[1]"));
		Assert.assertTrue(eles.size() > 0);
	}
}
