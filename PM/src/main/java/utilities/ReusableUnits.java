package utilities;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import base.ConfigClass;
import pm_pom_classes.Extension;
import pm_pom_classes.PM_Login_Page;
import pm_pom_classes.PM_Main_Page;
import pm_pom_classes.PM_Services;
import pm_pom_classes.PM_User;
import pm_pom_classes.PM_Users;

public class ReusableUnits
{
	public PM_User pmUser;
	public PM_Users pmUsers;
	public PM_Login_Page pmLoginPge;
	public PM_Main_Page pmMainPge;
	public PM_Services pmServices;
	public Extension pmExtension;
	WebDriverWait wait;
	
	public ReusableUnits(WebDriver driver)
	{
		pmUser = new PM_User(driver);
		pmUsers = new PM_Users(driver);
		pmLoginPge = new PM_Login_Page(driver);
		pmMainPge = new PM_Main_Page(driver);
		pmServices = new PM_Services(driver);
		pmExtension = new Extension(driver);
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
	
	
	
	public void createUser(WebDriver driver, String methodName, ExcelReadAndWrite ipData,
			ExcelReadAndWrite loginData,ExcelReadAndWrite pmTests, int iterations) throws InterruptedException
	{
		driver.get(ipData.getData(0, 0));
		String[] credentials = loginData.getData("test_pm_valid_login", 1);
		String[] testData = pmTests.getData(methodName, 1);
		pmLoginPge.PM_Login(credentials[0], credentials[1]);
		for(int i=0; i<10; i++)
		{
			pmMainPge.getUsers().click();
			pmUsers.getUser().click();
			pmUser.getAddButton().click();
			pmUser.setFirstNamefield(testData[0]+i);
			pmUser.setLastNamefield(testData[0]+i);
			pmUser.setUserIDField(testData[0]+i);
			Thread.sleep(1000);
			pmUser.setUserPasswordField(testData[1]);
			pmUser.setUserConfirmPasswordField(testData[1]);
			pmUser.setEmailIDField(testData[2]);
			pmUser.setAlternateFirstName(testData[3]+i);
			pmUser.setAltLastName(testData[4]+i);
			pmUser.setBusinessField(testData[5]);
			pmUser.setBusiness2(testData[6]);
			pmUser.setMobilePhone(testData[7]);
			pmUser.setMobilePhone2(testData[8]);
			new SelectDropDownValue().selectByIndex(pmUser.getSelectDepartmentDropdown(), 0);
			pmUser.getListFilterAddButton_mySelectedDepts().click();
			pmUser.getApplyButton().click();
			Assert.assertEquals(pmUser.getResponseMessage().getText().trim(), "Add operation successful for:");
			pmUser.getDoneButton().click();
			pmUser.setUserSearchTextBox(testData[0]+i);
			pmUser.getOnViewRangeButton().click();
			List<WebElement> eles = driver.findElements(By.xpath("(//td[contains(text(),'"+testData[0]+i+"')])[1]"));
			Assert.assertTrue(eles.size() > 0);
		}
	}
	
	
	public void createExtension(WebDriver driver, String methodName, ExcelReadAndWrite ipData,
			ExcelReadAndWrite loginData,ExcelReadAndWrite pmTests, int index) throws InterruptedException
	{
		wait = new WebDriverWait(driver, 10);
		driver.get(ipData.getData(0, 0));
		String[] credentials = loginData.getData("test_pm_valid_login", 1);
		String[] testData = pmTests.getData(methodName, 1);
		pmLoginPge.PM_Login(credentials[0], credentials[1]);
		pmMainPge.getServices().click();
		pmServices.getExtension().click();
		pmExtension.getAddButton().click();
		new SelectDropDownValue().selectByVisibleText(pmExtension.getExtensionType(), "IP");
		pmExtension.getNextButton().click();
		new SelectDropDownValue().selectByVisibleText(pmExtension.getSelectExtensionsRange(), testData[1]);
		String version = new GetMxoneVersionNumber(driver).getMxoneVersionNumber(driver);
		System.out.println(version);
		int ver = Integer.parseInt(version);
		System.out.println(ver);
		if(ver >= 720000)
		{
			pmExtension.setSingleExtensionValue(testData[2]);
		}
		else
		{
			new SelectDropDownValue().selectByVisibleText(pmExtension.getSingleExtensionDropDown(), testData[2]);
		}
		new SelectDropDownValue().selectByIndex(pmExtension.getMyCSPNameDropDown(), index);
		new SelectDropDownValue().selectByVisibleText(pmExtension.getServerDropDown(), testData[3]);
		pmExtension.setFirstName(testData[4]);
		pmExtension.setLastName(testData[5]);
		new SelectDropDownValue().selectByVisibleText(pmExtension.getPhoneTypeDropDown(), testData[6]);
		pmExtension.getApplyButton().click();
		Assert.assertEquals(pmExtension.getResponseMessage(), "Add operation successful for:");
		pmExtension.getDoneButton().click();
		new SelectDropDownValue().selectByVisibleText(pmExtension.getExtensionTypeDropDownHomePage(), "IP");
		pmExtension.setEnterExtensionNumberTextBox(testData[2]);
		pmExtension.getViewRangeButton().click();
		List<WebElement> eles = driver.findElements(By.xpath("//td[contains(text(),'"+testData[2]+"')]"));
		Assert.assertTrue(eles.size()==1);
	}
}
