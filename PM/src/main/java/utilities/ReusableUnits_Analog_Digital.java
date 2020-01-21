package utilities;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import pm_pom_classes.Extension;
import pm_pom_classes.PM_Function_Keys;
import pm_pom_classes.PM_Login_Page;
import pm_pom_classes.PM_Main_Page;
import pm_pom_classes.PM_Services;
import pm_pom_classes.PM_User;
import pm_pom_classes.PM_Users;

public class ReusableUnits_Analog_Digital 
{
	public PM_User pmUser;
	public PM_Users pmUsers;
	public PM_Login_Page pmLoginPge;
	public PM_Main_Page pmMainPge;
	public PM_Services pmServices;
	public Extension pmExtension;
	WebDriverWait wait;
	PM_Function_Keys funcKeys;
	
	public ReusableUnits_Analog_Digital(WebDriver driver)
	{
		pmUser = new PM_User(driver);
		pmUsers = new PM_Users(driver);
		pmLoginPge = new PM_Login_Page(driver);
		pmMainPge = new PM_Main_Page(driver);
		pmServices = new PM_Services(driver);
		pmExtension = new Extension(driver);
		funcKeys = new PM_Function_Keys(driver);
	}
	
	public void createAnalogTemplate(WebDriver driver,ExcelReadAndWrite ipData, ExcelReadAndWrite loginData, String templateName) throws InterruptedException
	{
		String[] credentials = loginData.getData("test_pm_valid_login", 1);
//		String[] testData = pmTests.getData(methodName, 3);
		driver.get(ipData.getData(0, 0));
		pmLoginPge.PM_Login(credentials[0], credentials[1]);
		pmMainPge.getServices().click();
		pmServices.getExtension().click();
		pmExtension.getManageTemplates().click();
		pmExtension.getAddNewTemplateButton().click();
		new SelectDropDownValue().selectByVisibleText(pmExtension.getExtensionType(), "Analog");
		pmExtension.getNextButton().click();
		new SelectDropDownValue().selectByIndex(pmExtension.getCommonCategoryDropDown(), 0);
		pmExtension.getNextButton().click();
		pmExtension.setTempplateName(templateName);
		pmExtension.getApplyButton().click();
		Assert.assertEquals(pmExtension.getResponseMessage(), "New Template operation successful for:");
		pmExtension.getDoneButton().click();
		List<WebElement> list = driver.findElements(By.xpath("//td[contains(text(),'"+templateName+"')]"));
		Assert.assertTrue(list.size()==1);
//		pmExtension.getApplyButton().click();
		pmExtension.getTemplateSubmitButton().click();
		pmMainPge.getLogoutLink().click();
	}
	
	public void createDigitalTemplate(WebDriver driver,ExcelReadAndWrite ipData, ExcelReadAndWrite loginData, String templateName) throws InterruptedException
	{
		String[] credentials = loginData.getData("test_pm_valid_login", 1);
//		String[] testData = pmTests.getData(methodName, 3);
		driver.get(ipData.getData(0, 0));
		pmLoginPge.PM_Login(credentials[0], credentials[1]);
		pmMainPge.getServices().click();
		pmServices.getExtension().click();
		pmExtension.getManageTemplates().click();
		pmExtension.getAddNewTemplateButton().click();
		new SelectDropDownValue().selectByVisibleText(pmExtension.getExtensionType(), "Digital");
		pmExtension.getNextButton().click();
		new SelectDropDownValue().selectByIndex(pmExtension.getDigitalCommonCategoryDropDown(), 0);
		pmExtension.getNextButton().click();
		pmExtension.setTempplateName(templateName);
		pmExtension.getApplyButton().click();
		Assert.assertEquals(pmExtension.getResponseMessage(), "New Template operation successful for:");
		pmExtension.getDoneButton().click();
		List<WebElement> list = driver.findElements(By.xpath("//td[contains(text(),'"+templateName+"')]"));
		Assert.assertTrue(list.size()==1);
//		pmExtension.getApplyButton().click();
		pmExtension.getTemplateSubmitButton().click();
		pmMainPge.getLogoutLink().click();
	}
	
	public void createDigitalExtension(WebDriver driver, String methodName, ExcelReadAndWrite ipData, ExcelReadAndWrite pmTests,
			ExcelReadAndWrite loginData, int index) throws InterruptedException
	{
		wait = new WebDriverWait(driver, 10);
		driver.get(ipData.getData(0, 0));
		String[] credentials = loginData.getData("test_pm_valid_login", 1);
		String[] testData = pmTests.getData(methodName, 1);
		pmLoginPge.PM_Login(credentials[0], credentials[1]);
		pmMainPge.getServices().click();
		pmServices.getExtension().click();
		pmExtension.getAddButton().click();
		new SelectDropDownValue().selectByVisibleText(pmExtension.getExtensionType(), "Digital");
		pmExtension.getNextButton().click();
		new SelectDropDownValue().selectByVisibleText(pmExtension.getSelectExtensionsRange(), testData[1]);
		String version = new GetMxoneVersionNumber(driver).getMxoneVersionNumber(driver);
		System.out.println(version);
		int ver = Integer.parseInt(version);
		System.out.println(ver);
		if(ver >= 72000)
		{
			pmExtension.setDigitalExtensionNumber(testData[1]);
		}
		else
		{
			new SelectDropDownValue().selectByVisibleText(pmExtension.getEnterDigitalExtensionNumber(), testData[1]);
		}
		new SelectDropDownValue().selectByVisibleText(pmExtension.getDigitalPhoneTypeDropDown(), testData[2]);
		new SelectDropDownValue().selectByIndex(pmExtension.getDigitalCommonCategoryDropDown(), index);
		pmExtension.setEquipmentPosition(testData[3]);
//		new SelectDropDownValue().selectByVisibleText(pmExtension.getServerDropDown(), testData[3]);
		pmExtension.setDigitalFirstname(testData[4]);
		pmExtension.setDigitalLastname(testData[5]);
//		new SelectDropDownValue().selectByVisibleText(pmExtension.getPhoneTypeDropDown(), testData[6]);
		pmExtension.getApplyButton().click();
		Assert.assertEquals(pmExtension.getResponseMessage(), "Add operation successful for:");
		pmExtension.getDoneButton().click();
		new SelectDropDownValue().selectByVisibleText(pmExtension.getExtensionTypeDropDownHomePage(), "Digital");
		pmExtension.setEnterExtensionNumberTextBox(testData[1]);
		pmExtension.getViewRangeButton().click();
		List<WebElement> eles = driver.findElements(By.xpath("//td[contains(text(),'"+testData[1]+"')]//following-sibling::td[contains(text(),'Digital')]"));
		Assert.assertTrue(eles.size()==1);
	}
	
	
	public void createAnalogExtension(WebDriver driver, String methodName, ExcelReadAndWrite ipData, ExcelReadAndWrite pmTests,
			ExcelReadAndWrite loginData, int index) throws InterruptedException
	{
		wait = new WebDriverWait(driver, 10);
		driver.get(ipData.getData(0, 0));
		String[] credentials = loginData.getData("test_pm_valid_login", 1);
		String[] testData = pmTests.getData(methodName, 1);
		pmLoginPge.PM_Login(credentials[0], credentials[1]);
		pmMainPge.getServices().click();
		pmServices.getExtension().click();
		pmExtension.getAddButton().click();
		new SelectDropDownValue().selectByVisibleText(pmExtension.getExtensionType(), "Analog");
		pmExtension.getNextButton().click();
		new SelectDropDownValue().selectByVisibleText(pmExtension.getSelectExtensionsRange(), testData[1]);
		String version = new GetMxoneVersionNumber(driver).getMxoneVersionNumber(driver);
		System.out.println(version);
		int ver = Integer.parseInt(version);
		System.out.println(ver);
		if(ver >= 72000)
		{
			pmExtension.setEnterAnalogDirectoryNumber(testData[1]);
		}
		else
		{
			new SelectDropDownValue().selectByVisibleText(pmExtension.getAnalogDirctoryDropDown(), testData[1]);
		}
		new SelectDropDownValue().selectByIndex(pmExtension.getCommonCategoryDropDown(), index);
		pmExtension.setEquipmentPosition(testData[2]);
//		new SelectDropDownValue().selectByVisibleText(pmExtension.getServerDropDown(), testData[3]);
		pmExtension.setAnalogFirstName(testData[3]);
		pmExtension.setAnalogLastname(testData[4]);
//		new SelectDropDownValue().selectByVisibleText(pmExtension.getPhoneTypeDropDown(), testData[6]);
		pmExtension.getApplyButton().click();
		Assert.assertEquals(pmExtension.getResponseMessage(), "Add operation successful for:");
		pmExtension.getDoneButton().click();
		new SelectDropDownValue().selectByVisibleText(pmExtension.getExtensionTypeDropDownHomePage(), "Analog");
		pmExtension.setEnterExtensionNumberTextBox(testData[1]);
		pmExtension.getViewRangeButton().click();
		List<WebElement> eles = driver.findElements(By.xpath("//td[contains(text(),'"+testData[1]+"')]//following-sibling::td[contains(text(),'Analog')]"));
		Assert.assertTrue(eles.size()==1);
	}
	
	
	public void createUser_With_Digital_Extension(WebDriver driver, String methodName, ExcelReadAndWrite ipData,
			ExcelReadAndWrite loginData,ExcelReadAndWrite pmTests, int index) throws InterruptedException
	{
		ArrayList<String> list = new ArrayList<String>();
		String[] extData = pmTests.getData(methodName, 3);
		list.add(extData[0]);
		new ExecuteCommands(driver).executeCmds(methodName, ipData, loginData, list);
		
		String[] credentials = loginData.getData("test_pm_valid_login", 1);
		String[] testData = pmTests.getData(methodName, 1);
		
		driver.get(ipData.getData(0, 0));
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
		pmUser.getNextButton().click();
		pmUser.getCreateAndAssignExtensionToUser().click();
		new SelectDropDownValue().selectByVisibleText(pmExtension.getExtensionType(), "Digital");
		pmExtension.getNextButton().click();
		
		//Provide extension details.
		new SelectDropDownValue().selectByVisibleText(pmExtension.getSelectExtensionsRange(), extData[1]);
		String version = new GetMxoneVersionNumber(driver).getMxoneVersionNumber(driver);
		System.out.println(version);
		int ver = Integer.parseInt(version);
		System.out.println(ver);
		if(ver >= 72000)
		{
			pmExtension.setDigitalExtensionNumber(extData[1]);
		}
		else
		{
			new SelectDropDownValue().selectByVisibleText(pmExtension.getEnterDigitalExtensionNumber(), extData[1]);
		}
		new SelectDropDownValue().selectByVisibleText(pmExtension.getDigitalPhoneTypeDropDown(), extData[2]);
		new SelectDropDownValue().selectByIndex(pmExtension.getDigitalCommonCategoryDropDown(), index);
		pmExtension.setEquipmentPosition(extData[3]);
//		new SelectDropDownValue().selectByVisibleText(pmExtension.getServerDropDown(), testData[3]);
		/*pmExtension.setDigitalFirstname(testData[4]);
		pmExtension.setDigitalLastname(testData[5]);*/
//		new SelectDropDownValue().selectByVisibleText(pmExtension.getPhoneTypeDropDown(), testData[6]);
		pmExtension.getApplyButton().click();
		pmExtension.getApplyButton().click();
		Assert.assertEquals(pmExtension.getResponseMessage(), "Add operation successful for:");
		pmExtension.getDoneButton().click();
		pmUser.setUserSearchTextBox(testData[0]);
		pmUser.getOnViewRangeButton().click();
		
		List<WebElement> eles = driver.findElements(By.xpath("(//td[contains(text(),'"+testData[0]+"')])[1]//following-sibling::td[contains(text(),'"+extData[1]+"')]"));
		Assert.assertTrue(eles.size()==1);
	}
	
	
	public void createUser_With_Analog_Extension(WebDriver driver, String methodName, ExcelReadAndWrite ipData,
			ExcelReadAndWrite loginData,ExcelReadAndWrite pmTests, int index) throws InterruptedException
	{
		ArrayList<String> list = new ArrayList<String>();
		String[] extData = pmTests.getData(methodName, 3);
		list.add(extData[0]);
		new ExecuteCommands(driver).executeCmds(methodName, ipData, loginData, list);
		
		String[] credentials = loginData.getData("test_pm_valid_login", 1);
		String[] testData = pmTests.getData(methodName, 1);
		
		driver.get(ipData.getData(0, 0));
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
		pmUser.getNextButton().click();
		pmUser.getCreateAndAssignExtensionToUser().click();
		new SelectDropDownValue().selectByVisibleText(pmExtension.getExtensionType(), "Analog");
		pmExtension.getNextButton().click();
		
		//Provide extension details.
		new SelectDropDownValue().selectByVisibleText(pmExtension.getSelectExtensionsRange(), extData[1]);
		String version = new GetMxoneVersionNumber(driver).getMxoneVersionNumber(driver);
		System.out.println(version);
		int ver = Integer.parseInt(version);
		System.out.println(ver);
		if(ver >= 72000)
		{
			pmExtension.setEnterAnalogDirectoryNumber(extData[1]);
		}
		else
		{
			new SelectDropDownValue().selectByVisibleText(pmExtension.getAnalogDirctoryDropDown(), extData[1]);
		}
		new SelectDropDownValue().selectByIndex(pmExtension.getCommonCategoryDropDown(), index);
		pmExtension.setEquipmentPosition(extData[2]);
//		new SelectDropDownValue().selectByVisibleText(pmExtension.getServerDropDown(), testData[3]);
	/*	pmExtension.setAnalogFirstName(testData[3]);
		pmExtension.setAnalogLastname(testData[4]);*/
//		new SelectDropDownValue().selectByVisibleText(pmExtension.getPhoneTypeDropDown(), testData[6]);
		pmExtension.getApplyButton().click();
		pmExtension.getApplyButton().click();
		Assert.assertEquals(pmExtension.getResponseMessage(), "Add operation successful for:");
		pmExtension.getDoneButton().click();
		pmUser.setUserSearchTextBox(testData[0]);
		pmUser.getOnViewRangeButton().click();
		
		List<WebElement> eles = driver.findElements(By.xpath("(//td[contains(text(),'"+testData[0]+"')])[1]//following-sibling::td[contains(text(),'"+extData[1]+"')]"));
		Assert.assertTrue(eles.size()==1);
	}
}
