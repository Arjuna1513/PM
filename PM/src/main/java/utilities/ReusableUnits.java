package utilities;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import base.ConfigClass;
import pm_pom_classes.Extension;
import pm_pom_classes.PM_Function_Keys;
import pm_pom_classes.PM_Login_Page;
import pm_pom_classes.PM_Main_Page;
import pm_pom_classes.PM_Services;
import pm_pom_classes.PM_User;
import pm_pom_classes.PM_Users;
import snm_pom_classes.SNM_External_Length_Page;
import snm_pom_classes.SNM_Login_Page;
import snm_pom_classes.SNM_Main_Page;
import snm_pom_classes.SNM_NumberPlan;
import snm_pom_classes.SNM_NumberSeries;
import snm_pom_classes.SNM_Number_Analysis_Page;
import snm_pom_classes.SNM_TelephonyExtensionsPage;
import snm_pom_classes.SNM_TelephonyPage;
import snm_pom_classes.SNM_Telephony_Extensions_CSP_Page;
import snm_pom_classes.SNM_Telephony_Groups_Customer_Page;
import snm_pom_classes.SNM_Telephony_Groups_Page;

public class ReusableUnits
{
	public PM_User pmUser;
	public PM_Users pmUsers;
	public PM_Login_Page pmLoginPge;
	public PM_Main_Page pmMainPge;
	public PM_Services pmServices;
	public Extension pmExtension;
	WebDriverWait wait;
	PM_Function_Keys funcKeys;
	public SNM_Login_Page snmLogin;
	public SNM_Main_Page snmMainPage;
	public SNM_Number_Analysis_Page numAnalysis;
	public SNM_NumberPlan numPlan;
	public SNM_NumberSeries numSeries;
	public SNM_External_Length_Page extNumLength;
	public SNM_Telephony_Extensions_CSP_Page snmCSP;
	public SNM_TelephonyPage snmTelephony;
	public SNM_TelephonyExtensionsPage telExt;
	public SNM_Telephony_Groups_Page snmTelGrp;
	public SNM_Telephony_Groups_Customer_Page snmTelGrpCust;
	public ReusableUnits(WebDriver driver)
	{
		pmUser = new PM_User(driver);
		pmUsers = new PM_Users(driver);
		pmLoginPge = new PM_Login_Page(driver);
		pmMainPge = new PM_Main_Page(driver);
		pmServices = new PM_Services(driver);
		pmExtension = new Extension(driver);
		funcKeys = new PM_Function_Keys(driver);
		snmLogin = new SNM_Login_Page(driver);
		snmMainPage = new SNM_Main_Page(driver);
		numAnalysis = new SNM_Number_Analysis_Page(driver);
		numPlan = new SNM_NumberPlan(driver);
		numSeries = new SNM_NumberSeries(driver);
		extNumLength = new SNM_External_Length_Page(driver);
		snmCSP = new SNM_Telephony_Extensions_CSP_Page(driver);
		snmTelephony = new SNM_TelephonyPage(driver);
		telExt = new SNM_TelephonyExtensionsPage(driver);
		snmTelGrp = new SNM_Telephony_Groups_Page(driver);
		snmTelGrpCust = new SNM_Telephony_Groups_Customer_Page(driver);
	}
	
	public void navigateUserToServiceSummaryPage(WebDriver driver, String methodName, ExcelReadAndWrite ipData,
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
		pmUser.getNextButton().click();
	}
	
	public void createUser(WebDriver driver, String methodName, ExcelReadAndWrite ipData,
			ExcelReadAndWrite loginData,ExcelReadAndWrite pmTests) throws InterruptedException
	{
		driver.get(ipData.getData(0, 0));
		String[] credentials = loginData.getData("test_pm_valid_login", 1);
		for(String s : credentials)
		{
			System.out.println(s);
		}
		String[] testData = pmTests.getData(methodName, 1);
		System.out.println(testData);
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
//		Thread.sleep(5000);
		pmUser.setBusiness2(testData[6]);
//		Thread.sleep(5000);
		pmUser.setMobilePhone(testData[7]);
//		Thread.sleep(5000);
		pmUser.setMobilePhone2(testData[8]);
//		Thread.sleep(5000);
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
	
	public void createUserWithExtension(WebDriver driver, String methodName, ExcelReadAndWrite ipData,
			ExcelReadAndWrite loginData,ExcelReadAndWrite pmTests) throws InterruptedException
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
			pmExtension.setSingleExtensionValue(extData[2]);
		}
		else
		{
			new SelectDropDownValue().selectByVisibleText(pmExtension.getSingleExtensionDropDown(), extData[2]);
		}
		new SelectDropDownValue().selectByIndex(pmExtension.getMyCSPNameDropDown(),0);
		new SelectDropDownValue().selectByVisibleText(pmExtension.getServerDropDown(), extData[3]);
		pmExtension.setFirstName(extData[4]);
		pmExtension.setLastName(extData[5]);
		new SelectDropDownValue().selectByVisibleText(pmExtension.getPhoneTypeDropDown(), extData[6]);
		pmExtension.getApplyButton().click();
//		/*Assert.assertEquals(pmExtension.getResponseMessage(), "Add operation successful for:");
//		pmExtension.getDoneButton().click();*/
		
		pmUser.getApplyButton().click();
		Assert.assertEquals(pmUser.getResponseMessage().getText().trim(), "Add operation successful for:");
		pmUser.getDoneButton().click();
		
//		/*new SelectDropDownValue().selectByVisibleText(pmExtension.getExtensionTypeDropDownHomePage(), "IP");
//		pmExtension.setEnterExtensionNumberTextBox(extData[2]);
//		pmExtension.getViewRangeButton().click();*/
		
		pmUser.setUserSearchTextBox(testData[0]);
		pmUser.getOnViewRangeButton().click();
		
		List<WebElement> eles = driver.findElements(By.xpath("(//td[contains(text(),'"+testData[0]+"')])[1]//following-sibling::td[contains(text(),'"+extData[2]+"')]"));
		Assert.assertTrue(eles.size()==1);
	}
	
	
	public void createUserWith_Virtual_Extension(WebDriver driver, String methodName, ExcelReadAndWrite ipData,
			ExcelReadAndWrite loginData,ExcelReadAndWrite pmTests) throws InterruptedException
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
		new SelectDropDownValue().selectByVisibleText(pmExtension.getExtensionType(), "Virtual");
		pmExtension.getNextButton().click();
		
		//Provide extension details.
		new SelectDropDownValue().selectByVisibleText(pmExtension.getSelectExtensionsRange(), extData[1]);
		String version = new GetMxoneVersionNumber(driver).getMxoneVersionNumber(driver);
		System.out.println(version);
		int ver = Integer.parseInt(version);
		System.out.println(ver);
		if(ver >= 720000)
		{
			pmExtension.setVirtualExtensionTextBox(extData[1]);
		}
		else
		{
			new SelectDropDownValue().selectByVisibleText(pmExtension.getVirtualExtensionTextBox(), extData[1]);
		}
		new SelectDropDownValue().selectByVisibleText(pmExtension.getVirtualExtensionTypeDropDown(), "Virtual");
		new SelectDropDownValue().selectByIndex(pmExtension.getMyCSPNameDropDown(), 0);
		new SelectDropDownValue().selectByVisibleText(pmExtension.getVirtualExtServerDropDown(), extData[2]);
		pmExtension.setVirtualExtFirstName(extData[3]);
		pmExtension.setVirtualExtLastName(extData[4]);
		pmExtension.getApplyButton().click();
		
		pmUser.getApplyButton().click();
		Assert.assertEquals(pmUser.getResponseMessage().getText().trim(), "Add operation successful for:");
		pmUser.getDoneButton().click();
		
		pmUser.setUserSearchTextBox(testData[0]);
		pmUser.getOnViewRangeButton().click();
		
		List<WebElement> eles = driver.findElements(By.xpath("(//td[contains(text(),'"+testData[0]+"')])[1]//following-sibling::td[contains(text(),'"+extData[1]+"')]"));
		Assert.assertTrue(eles.size()==1);
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
	
	
	public void create_multi_TerminalExtension(WebDriver driver, String methodName, ExcelReadAndWrite ipData,
			ExcelReadAndWrite loginData,ExcelReadAndWrite pmTests, int index, String type) throws InterruptedException
	{
		wait = new WebDriverWait(driver, 10);
		driver.get(ipData.getData(0, 0));
		String[] credentials = loginData.getData("test_pm_valid_login", 1);
		String[] testData = pmTests.getData(methodName, 1);
		pmLoginPge.PM_Login(credentials[0], credentials[1]);
		pmMainPge.getServices().click();
		pmServices.getExtension().click();
		pmExtension.getAddButton().click();
		new SelectDropDownValue().selectByVisibleText(pmExtension.getExtensionType(), "Multi-Terminal");
		pmExtension.getNextButton().click();
		new SelectDropDownValue().selectByVisibleText(pmExtension.getSelectExtensionsRange(), testData[1]);
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
		new SelectDropDownValue().selectByIndex(pmExtension.getMyCSPNameDropDown(), index);
		new SelectDropDownValue().selectByVisibleText(pmExtension.getMultiTerminalServerDropDown(), testData[2]);
		
		if(type.equalsIgnoreCase("IP"))
		{
			pmExtension.getAddIPButtonForMultiTerminal().click();
			pmExtension.getApplyButton().click();
		}
		else if(type.equalsIgnoreCase("mobile"))
		{
			pmExtension.getAddMobileExtButtonForMultiTerminal().click();
			pmExtension.setMobileExtRemoteNumber(testData[5]);
			pmExtension.setMobileExtReceivedANumber(testData[5]);
			pmExtension.getApplyButton().click();
		}
		else if(type.equalsIgnoreCase("dect"))
		{
			
		}
		else if(type.equalsIgnoreCase("sipauto"))
		{
			pmExtension.getAddSipAutoRegTerminalButtonForMultiTerminal().click();
			pmExtension.setSipAutoPortNumber(testData[5]);
			pmExtension.setSipAutoPortIdentity(testData[6]);
			pmExtension.setSIPAutoURI(testData[7]);
			pmExtension.getApplyButton().click();
		}
		else if(type.equalsIgnoreCase("sipremote"))
		{
			
		}
		
		pmExtension.setMultiTerminalFirstName(testData[3]);
		pmExtension.setMultiTerminalLastName(testData[4]);
		pmExtension.getApplyButton().click();
		Assert.assertEquals(pmExtension.getResponseMessage(), "Add operation successful for:");
		pmExtension.getDoneButton().click();
		
		if(type.equalsIgnoreCase("none"))
		{
			new SelectDropDownValue().selectByVisibleText(pmExtension.getExtensionTypeDropDownHomePage(), "Virtual");
		}
		else
		{
			new SelectDropDownValue().selectByVisibleText(pmExtension.getExtensionTypeDropDownHomePage(), "Multi-Terminal");
		}
		pmExtension.setEnterExtensionNumberTextBox(testData[1]);
		pmExtension.getViewRangeButton().click();
		List<WebElement> eles = driver.findElements(By.xpath("//td[contains(text(),'"+testData[2]+"')]"));
		Assert.assertTrue(eles.size()==1);
	}
	
	
	public void create_virtual_extension(WebDriver driver, String methodName, ExcelReadAndWrite ipData,
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
		new SelectDropDownValue().selectByVisibleText(pmExtension.getExtensionType(), "Virtual");
		pmExtension.getNextButton().click();
		new SelectDropDownValue().selectByVisibleText(pmExtension.getSelectExtensionsRange(), testData[1]);
		String version = new GetMxoneVersionNumber(driver).getMxoneVersionNumber(driver);
		System.out.println(version);
		int ver = Integer.parseInt(version);
		System.out.println(ver);
		if(ver >= 720000)
		{
			pmExtension.setVirtualExtensionTextBox(testData[1]);
		}
		else
		{
			new SelectDropDownValue().selectByVisibleText(pmExtension.getVirtualExtensionTextBox(), testData[1]);
		}
		new SelectDropDownValue().selectByVisibleText(pmExtension.getVirtualExtensionTypeDropDown(), "Virtual");
		new SelectDropDownValue().selectByIndex(pmExtension.getMyCSPNameDropDown(), index);
		new SelectDropDownValue().selectByVisibleText(pmExtension.getVirtualExtServerDropDown(), testData[2]);
		pmExtension.setVirtualExtFirstName(testData[3]);
		pmExtension.setVirtualExtLastName(testData[4]);
		pmExtension.getApplyButton().click();
		Assert.assertEquals(pmExtension.getResponseMessage(), "Add operation successful for:");
		pmExtension.getDoneButton().click();
		new SelectDropDownValue().selectByVisibleText(pmExtension.getExtensionTypeDropDownHomePage(), "Virtual");
		pmExtension.setEnterExtensionNumberTextBox(testData[1]);
		pmExtension.getViewRangeButton().click();
		List<WebElement> eles = driver.findElements(By.xpath("//td[contains(text(),'"+testData[1]+"')]"));
		Assert.assertTrue(eles.size()==1);
	}
	
	public void createExt_With_FunctionKey(WebDriver driver, String methodName, ExcelReadAndWrite ipData,
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
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("vacDir")));
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
		new SelectDropDownValue().selectByVisibleText(pmExtension.getServerDropDown(), testData[4]);
		pmExtension.setFirstName(testData[5]);
		pmExtension.setLastName(testData[6]);
		new SelectDropDownValue().selectByVisibleText(pmExtension.getPhoneTypeDropDown(), testData[7]);
		pmExtension.getFunctionKeysButton().click();
		funcKeys.getFuncKey2().click();
		if(testData[8].equals("DMN"))
		{
			new SelectDropDownValue().selectByValue(funcKeys.getFunctionType(), testData[8]);
			funcKeys.setKeyLabel("DMN-"+testData[3]);
			funcKeys.setDmnDir(testData[3]);
		}
		else if(testData[8].equals("EDN"))
		{
			new SelectDropDownValue().selectByValue(funcKeys.getFunctionType(), testData[8]);
			funcKeys.setKeyLabel("EDN-"+testData[3]);
			funcKeys.setEdnDir(testData[3]);
		}
		else if(testData[8].equals("GMA"))
		{
			new SelectDropDownValue().selectByValue(funcKeys.getFunctionType(), testData[8]);
			funcKeys.setKeyLabel("GMA-"+testData[3]);
			funcKeys.setGMAGroupNumber(testData[3]);
		}
		else if(testData[8].equals("MNS"))
		{
			new SelectDropDownValue().selectByValue(funcKeys.getFunctionType(), testData[8]);
			funcKeys.setKeyLabel("MNS-"+testData[3]);
			funcKeys.setMNSDir(testData[3]);
		}
		else if(testData[8].equals("TNS"))
		{
			new SelectDropDownValue().selectByValue(funcKeys.getFunctionType(), testData[8]);
			funcKeys.setKeyLabel("TNS-"+testData[3]);
			funcKeys.setTnsDigit(testData[3]);
		}
		else if(testData[8].equals("MCT"))
		{
			new SelectDropDownValue().selectByValue(funcKeys.getFunctionType(), testData[8]);
			funcKeys.setKeyLabel("MCT");
		}
		else if(testData[8].equals("MOI"))
		{
			new SelectDropDownValue().selectByValue(funcKeys.getFunctionType(), testData[8]);
			funcKeys.setKeyLabel("MOI");
		}
		else if(testData[8].equals("PGM"))
		{
			new SelectDropDownValue().selectByValue(funcKeys.getFunctionType(), testData[8]);
			funcKeys.setKeyLabel("PGM");
		}
		else if(testData[8].equals("REC"))
		{
			new SelectDropDownValue().selectByValue(funcKeys.getFunctionType(), testData[8]);
			funcKeys.setKeyLabel("REC");
			funcKeys.setRecordURI(testData[3]);
		}
		funcKeys.getFuncKeyOKButton().click();
		funcKeys.getApplyButton().click();
		pmExtension.getApplyButton().click();
		Assert.assertEquals(pmExtension.getResponseMessage(), "Add operation successful for:");
		pmExtension.getDoneButton().click();
		new SelectDropDownValue().selectByVisibleText(pmExtension.getExtensionTypeDropDownHomePage(), "IP");
		pmExtension.setEnterExtensionNumberTextBox(testData[2]);
		pmExtension.getViewRangeButton().click();
		List<WebElement> eles = driver.findElements(By.xpath("//td[contains(text(),'"+testData[2]+"')]"));
		Assert.assertTrue(eles.size()==1);
		
	}
	
	
	public void createExt_Line1_SCA_SCABR(WebDriver driver, String methodName, ExcelReadAndWrite ipData,
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
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("vacDir")));
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
		new SelectDropDownValue().selectByVisibleText(pmExtension.getServerDropDown(), testData[4]);
		pmExtension.setFirstName(testData[5]);
		pmExtension.setLastName(testData[6]);
		new SelectDropDownValue().selectByVisibleText(pmExtension.getPhoneTypeDropDown(), testData[7]);
		pmExtension.getFunctionKeysButton().click();
		funcKeys.getFuncKeyLine1().click();
//		Thread.sleep(10000);
		if(testData[8].equals("SCA"))
		{
			new SelectDropDownValue().selectByValue(funcKeys.getFunctionType(), testData[8]);
			funcKeys.setKeyLabel("SCA");
			funcKeys.setSCADir(testData[2]);
			Thread.sleep(3000);
		}
		else if(testData[8].equals("SCABR"))
		{
			new SelectDropDownValue().selectByValue(funcKeys.getFunctionType(), testData[8]);
			funcKeys.setKeyLabel("SCABR");
			funcKeys.setSCADir(testData[2]);
		}
		funcKeys.getFuncKeyOKButton().click();
		funcKeys.getApplyButton().click();
		pmExtension.getApplyButton().click();
		Assert.assertEquals(pmExtension.getResponseMessage(), "Add operation successful for:");
		pmExtension.getDoneButton().click();
		new SelectDropDownValue().selectByVisibleText(pmExtension.getExtensionTypeDropDownHomePage(), "IP");
		pmExtension.setEnterExtensionNumberTextBox(testData[2]);
		pmExtension.getViewRangeButton().click();
		List<WebElement> eles = driver.findElements(By.xpath("//td[contains(text(),'"+testData[2]+"')]"));
		Assert.assertTrue(eles.size()==1);
	}
	
	
	public void createExt_SCA_SCABRFunctionKey(WebDriver driver, String methodName, ExcelReadAndWrite ipData,
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
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("vacDir")));
		new SelectDropDownValue().selectByVisibleText(pmExtension.getSelectExtensionsRange(), testData[3]);
		String version = new GetMxoneVersionNumber(driver).getMxoneVersionNumber(driver);
		System.out.println(version);
		int ver = Integer.parseInt(version);
		System.out.println(ver);
		if(ver >= 720000)
		{
			pmExtension.setSingleExtensionValue(testData[3]);
		}
		else
		{
			new SelectDropDownValue().selectByVisibleText(pmExtension.getSingleExtensionDropDown(), testData[3]);
		}
		new SelectDropDownValue().selectByIndex(pmExtension.getMyCSPNameDropDown(), index);
		new SelectDropDownValue().selectByVisibleText(pmExtension.getServerDropDown(), testData[4]);
		pmExtension.setFirstName(testData[5]);
		pmExtension.setLastName(testData[6]);
		new SelectDropDownValue().selectByVisibleText(pmExtension.getPhoneTypeDropDown(), testData[7]);
		pmExtension.getFunctionKeysButton().click();
		funcKeys.getFuncKey2().click();
		if(testData[8].equals("SCA"))
		{
			new SelectDropDownValue().selectByValue(funcKeys.getFunctionType(), testData[8]);
			funcKeys.setKeyLabel("SCA-"+testData[2]);
			funcKeys.setSCADir(testData[2]);
		}
		else if(testData[8].equals("SCABR"))
		{
			new SelectDropDownValue().selectByValue(funcKeys.getFunctionType(), testData[8]);
			funcKeys.setKeyLabel("SCABR-"+testData[2]);
			funcKeys.setSCADir(testData[2]);
		}
		funcKeys.getFuncKeyOKButton().click();
		funcKeys.getApplyButton().click();
		pmExtension.getApplyButton().click();
		Assert.assertEquals(pmExtension.getResponseMessage(), "Add operation successful for:");
		pmExtension.getDoneButton().click();
		new SelectDropDownValue().selectByVisibleText(pmExtension.getExtensionTypeDropDownHomePage(), "IP");
		pmExtension.setEnterExtensionNumberTextBox(testData[3]);
		pmExtension.getViewRangeButton().click();
		List<WebElement> eles = driver.findElements(By.xpath("//td[contains(text(),'"+testData[3]+"')]"));
		Assert.assertTrue(eles.size()==1);
	}
	
	public void clear_function_key(WebDriver driver, String methodName, ExcelReadAndWrite ipData, ExcelReadAndWrite loginData,
			ExcelReadAndWrite pmTests, String fKey) throws InterruptedException
	{
		String[] credentials = loginData.getData("test_pm_valid_login", 1);
		String[] testData = pmTests.getData(methodName, 1);
		driver.get(ipData.getData(0, 0));
		pmLoginPge.PM_Login(credentials[0], credentials[1]);
		pmMainPge.getServices().click();
		pmServices.getExtension().click();
		new SelectDropDownValue().selectByVisibleText(pmExtension.getExtensionTypeDropDownHomePage(), "IP");
		pmExtension.setEnterExtensionNumberTextBox(testData[4]);
		pmExtension.getViewRangeButton().click();
		driver.findElement(By.xpath("//td[contains(text(),'"+testData[4]+"')]//preceding-sibling::td[20]")).click();
		List<WebElement> eles = null;
		if(fKey.equals("TNS"))
		{
			eles  = driver.findElements(By.xpath("//td[contains(text(),'TNS-"+testData[5]+" TNS "+testData[5]+"')]"));
		}
		else if(fKey.equals("MNS"))
		{
			eles  = driver.findElements(By.xpath("//td[contains(text(),'MNS-"+testData[5]+" MNS "+testData[5]+"')]"));
		}
		else if(fKey.equals("DMN"))
		{
			eles  = driver.findElements(By.xpath("//td[contains(text(),'DMN-"+testData[5]+" DMN"+"')]"));
		}
		else if(fKey.equals("MCT"))
		{
			eles  = driver.findElements(By.xpath("//td[contains(text(),'MCT MCT')]"));
		}
		else if(fKey.equals("EDN"))
		{
			eles  = driver.findElements(By.xpath("//td[contains(text(),'EDN-"+testData[5]+" EDN "+testData[5]+"')]"));
		}
		else if(fKey.equals("GMA"))
		{
			eles  = driver.findElements(By.xpath("//td[contains(text(),'GMA-"+testData[5]+" GMA "+testData[5]+"')]"));
		}
		else if(fKey.equals("MOI"))
		{
			eles  = driver.findElements(By.xpath("//td[contains(text(),'MOI MOI')]"));
		}
		else if(fKey.equals("PGM"))
		{
			eles  = driver.findElements(By.xpath("//td[contains(text(),'PGM PGM')]"));
		}
		else if(fKey.equals("REC"))
		{
			eles  = driver.findElements(By.xpath("//td[contains(text(),'REC REC http://149.13.0.80:80//nrj.ogg')]"));
		}
		Assert.assertTrue(eles.size() == 1);
		pmExtension.getDoneButton().click();
		driver.findElement(By.xpath("//td[contains(text(),'"+testData[4]+"')]//preceding-sibling::td[19]")).click();
		new SelectDropDownValue().selectByValue(pmExtension.getPhoneTypeDropDown(), "Mitel6869i");
		pmExtension.getFunctionKeysButton().click();
		funcKeys.getFuncKey2().click();
		new SelectDropDownValue().selectByValue(funcKeys.getFunctionType(),"REMOVE");
		funcKeys.getFuncKeyOKButton().click();
		funcKeys.getApplyButton().click();
		pmExtension.getEditPageApplyButton().click();
		Assert.assertEquals(pmExtension.getResponseMessage(), "Change operation successful for:");
		pmExtension.getDoneButton().click();
		new SelectDropDownValue().selectByVisibleText(pmExtension.getExtensionTypeDropDownHomePage(), "IP");
		pmExtension.getEnterExtensionNumberTextBox().clear();
		pmExtension.setEnterExtensionNumberTextBox(testData[4]);
		pmExtension.getViewRangeButton().click();
		driver.findElement(By.xpath("//td[contains(text(),'"+testData[4]+"')]//preceding-sibling::td[20]")).click();
		List<WebElement> eles1 = null;
		if(fKey.equals("TNS"))
		{
			eles1  = driver.findElements(By.xpath("//td[contains(text(),'TNS')]"));
		}
		else if(fKey.equals("MNS"))
		{
			eles1  = driver.findElements(By.xpath("//td[contains(text(),'MNS')]"));
		}
		else if(fKey.equals("DMN"))
		{
			eles1  = driver.findElements(By.xpath("//td[contains(text(),'DMN')]"));
		}
		else if(fKey.equals("MCT"))
		{
			eles1  = driver.findElements(By.xpath("//td[contains(text(),'MCT')]"));
		}
		else if(fKey.equals("EDN"))
		{
			eles1  = driver.findElements(By.xpath("//td[contains(text(),'EDN-"+testData[5]+" EDN "+testData[5]+"')]"));
		}
		else if(fKey.equals("GMA"))
		{
			eles1  = driver.findElements(By.xpath("//td[contains(text(),'GMA')]"));
		}
		else if(fKey.equals("MOI"))
		{
			eles1  = driver.findElements(By.xpath("//td[contains(text(),'MOI')]"));
		}
		else if(fKey.equals("PGM"))
		{
			eles1  = driver.findElements(By.xpath("//td[contains(text(),'PGM')]"));
		}
		else if(fKey.equals("REC"))
		{
			eles1  = driver.findElements(By.xpath("//td[contains(text(),'REC')]"));
		}
		Assert.assertTrue(eles1.size() == 0);
		pmMainPge.getLogoutLink().click();
	}
	
	
	public void clear_function_key_SCA_SCABR(WebDriver driver, String methodName, ExcelReadAndWrite ipData, ExcelReadAndWrite loginData,
			ExcelReadAndWrite pmTests, String fKey) throws InterruptedException
	{
		String[] credentials = loginData.getData("test_pm_valid_login", 1);
		String[] testData = pmTests.getData(methodName, 1);
		driver.get(ipData.getData(0, 0));
		pmLoginPge.PM_Login(credentials[0], credentials[1]);
		pmMainPge.getServices().click();
		pmServices.getExtension().click();
		new SelectDropDownValue().selectByVisibleText(pmExtension.getExtensionTypeDropDownHomePage(), "IP");
		pmExtension.setEnterExtensionNumberTextBox(testData[5]);
		pmExtension.getViewRangeButton().click();
		driver.findElement(By.xpath("//td[contains(text(),'"+testData[5]+"')]//preceding-sibling::td[20]")).click();
		List<WebElement> eles = null;
		if(fKey.equals("SCA"))
		{
			eles  = driver.findElements(By.xpath("//td[contains(text(),'SCA SCA "+testData[5]+"')]"));
		}
		else if(fKey.equals("SCABR"))
		{
			eles  = driver.findElements(By.xpath("//td[contains(text(),'SCABR SCABR "+testData[5]+"')]"));
		}
		Assert.assertTrue(eles.size() == 1);
		pmExtension.getDoneButton().click();
		
		pmExtension.getEnterExtensionNumberTextBox().clear();
		pmExtension.setEnterExtensionNumberTextBox(testData[6]);
		pmExtension.getViewRangeButton().click();
		driver.findElement(By.xpath("//td[contains(text(),'"+testData[6]+"')]//preceding-sibling::td[20]")).click();
		List<WebElement> eles1 = null;
		if(fKey.equals("SCA"))
		{
			eles1  = driver.findElements(By.xpath("//td[contains(text(),'SCA SCA "+testData[5]+"')]"));
		}
		else if(fKey.equals("SCABR"))
		{
			eles1  = driver.findElements(By.xpath("//td[contains(text(),'SCABR SCABR "+testData[5]+"')]"));
		}
		Assert.assertTrue(eles1.size() == 1);
		pmExtension.getDoneButton().click();
		
		driver.findElement(By.xpath("//td[contains(text(),'"+testData[6]+"')]//preceding-sibling::td[19]")).click();
		new SelectDropDownValue().selectByValue(pmExtension.getPhoneTypeDropDown(), "Mitel6869i");
		pmExtension.getFunctionKeysButton().click();
		funcKeys.getFuncKey2().click();
		new SelectDropDownValue().selectByValue(funcKeys.getFunctionType(),"REMOVE");
		funcKeys.getFuncKeyOKButton().click();
		funcKeys.getApplyButton().click();
		pmExtension.getEditPageApplyButton().click();
		Assert.assertEquals(pmExtension.getResponseMessage(), "Change operation successful for:");
		pmExtension.getDoneButton().click();
		new SelectDropDownValue().selectByVisibleText(pmExtension.getExtensionTypeDropDownHomePage(), "IP");
		pmExtension.getEnterExtensionNumberTextBox().clear();
		pmExtension.setEnterExtensionNumberTextBox(testData[6]);
		pmExtension.getViewRangeButton().click();
		driver.findElement(By.xpath("//td[contains(text(),'"+testData[6]+"')]//preceding-sibling::td[20]")).click();
		List<WebElement> eles2 = null;
		if(fKey.equals("SCA"))
		{
			eles2  = driver.findElements(By.xpath("//td[contains(text(),'SCA SCA "+testData[5]+"')]"));
		}
		else if(fKey.equals("SCABR"))
		{
			eles2  = driver.findElements(By.xpath("//td[contains(text(),'SCABR SCABR "+testData[5]+"')]"));
		}
		Assert.assertTrue(eles2.size() == 0);
		pmExtension.getDoneButton().click();
		new SelectDropDownValue().selectByVisibleText(pmExtension.getExtensionTypeDropDownHomePage(), "IP");
		pmExtension.getEnterExtensionNumberTextBox().clear();
		pmExtension.setEnterExtensionNumberTextBox(testData[5]);
		pmExtension.getViewRangeButton().click();
		driver.findElement(By.xpath("//td[contains(text(),'"+testData[5]+"')]//preceding-sibling::td[19]")).click();
		new SelectDropDownValue().selectByValue(pmExtension.getPhoneTypeDropDown(), "Mitel6869i");
		pmExtension.getFunctionKeysButton().click();
		funcKeys.getFuncKeyLine1().click();
		new SelectDropDownValue().selectByValue(funcKeys.getFunctionType(),"REMOVE");
		funcKeys.getFuncKeyOKButton().click();
		funcKeys.getApplyButton().click();
		pmExtension.getEditPageApplyButton().click();
		Assert.assertEquals(pmExtension.getResponseMessage(), "Change operation successful for:");
		pmExtension.getDoneButton().click();
		driver.findElement(By.xpath("//td[contains(text(),'"+testData[5]+"')]//preceding-sibling::td[20]")).click();
		List<WebElement> eles3 = null;
		if(fKey.equals("SCA"))
		{
			eles3  = driver.findElements(By.xpath("//td[contains(text(),'SCA SCA "+testData[5]+"')]"));
		}
		else if(fKey.equals("SCABR"))
		{
			eles3  = driver.findElements(By.xpath("//td[contains(text(),'SCABR SCABR "+testData[5]+"')]"));
		}
		Assert.assertTrue(eles3.size() == 0);
		pmExtension.getDoneButton().click();
		pmMainPge.getLogoutLink().click();
	}
	
	
	public void createIPTemplate(WebDriver driver, String methodName, ExcelReadAndWrite ipData, ExcelReadAndWrite loginData, String templateName) throws InterruptedException
	{
		String[] credentials = loginData.getData("test_pm_valid_login", 1);
//		String[] testData = pmTests.getData(methodName, 3);
		driver.get(ipData.getData(0, 0));
		pmLoginPge.PM_Login(credentials[0], credentials[1]);
		pmMainPge.getServices().click();
		pmServices.getExtension().click();
		pmExtension.getManageTemplates().click();
		pmExtension.getAddNewTemplateButton().click();
		new SelectDropDownValue().selectByVisibleText(pmExtension.getExtensionType(), "IP");
		pmExtension.getNextButton().click();
		new SelectDropDownValue().selectByIndex(pmExtension.getServerDropDown(), 0);
		new SelectDropDownValue().selectByIndex(pmExtension.getMyCSPNameDropDown(), 0);
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
	
	
	public void create_virtual_template(WebDriver driver, String methodName, ExcelReadAndWrite ipData, ExcelReadAndWrite loginData, String templateName) throws InterruptedException
	{
		String[] credentials = loginData.getData("test_pm_valid_login", 1);
		driver.get(ipData.getData(0, 0));
		pmLoginPge.PM_Login(credentials[0], credentials[1]);
		pmMainPge.getServices().click();
		pmServices.getExtension().click();
		pmExtension.getManageTemplates().click();
		pmExtension.getAddNewTemplateButton().click();
		new SelectDropDownValue().selectByVisibleText(pmExtension.getExtensionType(), "Virtual");
		pmExtension.getNextButton().click();
		new SelectDropDownValue().selectByVisibleText(pmExtension.getVirtualExtensionTypeDropDown(), "Virtual");
		new SelectDropDownValue().selectByIndex(pmExtension.getMyCSPNameDropDown(), 0);
		new SelectDropDownValue().selectByVisibleText(pmExtension.getVirtualExtServerDropDown(), "1");
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


	//Reusable methods of SIPDECT Extensions
	public void create_SIPDECT_Extension(WebDriver driver, String methodName, ExcelReadAndWrite ipData,
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
		new SelectDropDownValue().selectByVisibleText(pmExtension.getExtensionType(), "SIP DECT");
		pmExtension.getNextButton().click();
		new SelectDropDownValue().selectByVisibleText(pmExtension.getSelectExtensionsRange(), testData[1]);
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
		new SelectDropDownValue().selectByIndex(pmExtension.getMyCSPNameDropDown(), index);
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
		pmExtension.getApplyButton().click();
		
		Assert.assertEquals(pmExtension.getResponseMessage(), "Add operation successful for:");
		pmExtension.getDoneButton().click();
		
		new SelectDropDownValue().selectByVisibleText(pmExtension.getExtensionTypeDropDownHomePage(), "Multi-Terminal");
		pmExtension.setEnterExtensionNumberTextBox(testData[1]);
		pmExtension.getViewRangeButton().click();
		
		List<WebElement> eles = driver.findElements(By.xpath("//td[contains(text(),'"+testData[1]+"')]"));
		Assert.assertTrue(eles.size()==1);
	}


	public void navigate_to_sipdect_settings_page(WebDriver driver, String methodName, ExcelReadAndWrite ipData,
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
		new SelectDropDownValue().selectByVisibleText(pmExtension.getExtensionType(), "SIP DECT");
		pmExtension.getNextButton().click();
		
		
//		/*pmExtension.getApplyButton().click();
//		
//		Assert.assertEquals(pmExtension.getResponseMessage(), "Add operation successful for:");
//		pmExtension.getDoneButton().click();
//		
//		new SelectDropDownValue().selectByVisibleText(pmExtension.getExtensionTypeDropDownHomePage(), "Multi-Terminal");
//		pmExtension.setEnterExtensionNumberTextBox(testData[1]);
//		pmExtension.getViewRangeButton().click();
//		
//		List<WebElement> eles = driver.findElements(By.xpath("//td[contains(text(),'"+testData[2]+"')]"));
//		Assert.assertTrue(eles.size()==1);*/
	}

	
	public void createUserWith_SIPDECT_Extension(WebDriver driver, String methodName, ExcelReadAndWrite ipData,
			ExcelReadAndWrite loginData,ExcelReadAndWrite pmTests) throws InterruptedException
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
	}
	
	public void navigateToExtensionEditPage(WebDriver driver, String methodName, ExcelReadAndWrite ipData,
			ExcelReadAndWrite loginData,ExcelReadAndWrite pmTests, String extType) throws InterruptedException
	{
		wait = new WebDriverWait(driver, 10);
		driver.get(ipData.getData(0, 0));
		String[] credentials = loginData.getData("test_pm_valid_login", 1);
		String[] testData = pmTests.getData(methodName, 1);
		pmLoginPge.PM_Login(credentials[0], credentials[1]);
		pmMainPge.getServices().click();
		pmServices.getExtension().click();
		new SelectDropDownValue().selectByVisibleText(pmExtension.getExtensionTypeDropDownHomePage(), extType);
		pmExtension.setEnterExtensionNumberTextBox(testData[1]);
		pmExtension.getViewRangeButton().click();
		driver.findElement(By.xpath("//td[contains(text(),'"+testData[1]+"')]//preceding-sibling::td[19]")).click();
	}
	
	
	//Helper method for Personal Number List:
	public void create_personal_number_for_given_extension(WebDriver driver, String methodName, ExcelReadAndWrite ipData,
			ExcelReadAndWrite loginData,ExcelReadAndWrite pmTests, String extType) throws InterruptedException
	{
		String[] credentials = loginData.getData("test_pm_valid_login", 1);
		String[] testData = pmTests.getData(methodName, 1);
		
		driver.get(ipData.getData(0, 0));
		pmLoginPge.PM_Login(credentials[0], credentials[1]);
		pmMainPge.getServices().click();
		pmServices.getExtension().click();
		pmExtension.getAddButton().click();
		
		if(extType.equals("IP"))
		{
			Thread.sleep(1000);
			new SelectDropDownValue().selectByVisibleText(pmExtension.getExtensionType(), "IP");
			pmExtension.getNextButton().click();
			
			new SelectDropDownValue().selectByVisibleText(pmExtension.getSelectExtensionsRange(), testData[1]);
			String version = new GetMxoneVersionNumber(driver).getMxoneVersionNumber(driver);
			System.out.println(version);
			int ver = Integer.parseInt(version);
			System.out.println(ver);
			if(ver >= 720000)
			{
				pmExtension.setSingleExtensionValue(testData[1]);
			}
			else
			{
				new SelectDropDownValue().selectByVisibleText(pmExtension.getSingleExtensionDropDown(), testData[1]);
			}
			new SelectDropDownValue().selectByIndex(pmExtension.getMyCSPNameDropDown(), 0);
			new SelectDropDownValue().selectByIndex(pmExtension.getServerDropDown(), 1);
		}
		else if(extType.equals("analog"))
		{
			Thread.sleep(1000);
			new SelectDropDownValue().selectByVisibleText(pmExtension.getExtensionType(), "Analog");
			pmExtension.getNextButton().click();
			
			new SelectDropDownValue().selectByVisibleText(pmExtension.getSelectExtensionsRange(), testData[1]);
			String version = new GetMxoneVersionNumber(driver).getMxoneVersionNumber(driver);
			System.out.println(version);
			int ver = Integer.parseInt(version);
			System.out.println(ver);
			if(ver >= 720000)
			{
				pmExtension.setEnterAnalogDirectoryNumber(testData[1]);
			}
			else
			{
				new SelectDropDownValue().selectByVisibleText(pmExtension.getAnalogDirctoryDropDown(), testData[1]);
			}
			new SelectDropDownValue().selectByIndex(pmExtension.getCommonCategoryDropDown(), 0);
			pmExtension.setEquipmentPosition(testData[2]);
		}
		else if(extType.equals("digital"))
		{
			Thread.sleep(1000);
			new SelectDropDownValue().selectByVisibleText(pmExtension.getExtensionType(), "Digital");
			pmExtension.getNextButton().click();
			new SelectDropDownValue().selectByVisibleText(pmExtension.getSelectExtensionsRange(), testData[1]);
			String version = new GetMxoneVersionNumber(driver).getMxoneVersionNumber(driver);
			System.out.println(version);
			int ver = Integer.parseInt(version);
			System.out.println(ver);
			if(ver >= 720000)
			{
				pmExtension.setDigitalExtensionNumber(testData[1]);
			}
			else
			{
				new SelectDropDownValue().selectByVisibleText(pmExtension.getEnterDigitalExtensionNumber(), testData[1]);
			}
			new SelectDropDownValue().selectByIndex(pmExtension.getDigitalPhoneTypeDropDown(), 1);
			new SelectDropDownValue().selectByIndex(pmExtension.getDigitalCommonCategoryDropDown(), 0);
			pmExtension.setEquipmentPosition(testData[2]);
		}
		else if(extType.equals("virtual"))
		{
			Thread.sleep(1000);
			new SelectDropDownValue().selectByVisibleText(pmExtension.getExtensionType(), "Virtual");
			pmExtension.getNextButton().click();
			new SelectDropDownValue().selectByVisibleText(pmExtension.getSelectExtensionsRange(), testData[1]);
			String version = new GetMxoneVersionNumber(driver).getMxoneVersionNumber(driver);
			System.out.println(version);
			int ver = Integer.parseInt(version);
			System.out.println(ver);
			if(ver >= 720000)
			{
				pmExtension.setVirtualExtensionTextBox(testData[1]);
			}
			else
			{
				new SelectDropDownValue().selectByVisibleText(pmExtension.getVirtualExtensionTextBox(), testData[1]);
			}
			new SelectDropDownValue().selectByVisibleText(pmExtension.getVirtualExtensionTypeDropDown(), "Virtual");
			new SelectDropDownValue().selectByIndex(pmExtension.getMyCSPNameDropDown(), 0);
			new SelectDropDownValue().selectByIndex(pmExtension.getVirtualExtServerDropDown(), 1);
		}
		else if(extType.equals("sip-dect"))
		{
			Thread.sleep(1000);
			new SelectDropDownValue().selectByVisibleText(pmExtension.getExtensionType(), "SIP DECT");
			pmExtension.getNextButton().click();
			new SelectDropDownValue().selectByVisibleText(pmExtension.getSelectExtensionsRange(), testData[1]);
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
			new SelectDropDownValue().selectByIndex(pmExtension.getMultiTerminalServerDropDown(), 1);
			
			pmExtension.getAddSipDectTerminalButton().click();
			pmExtension.setSIPDectName(testData[2]);
			pmExtension.setSIPDectDescription1("SIP DECT");
			pmExtension.setSIPDectDescription2("SIP DECT");
			pmExtension.setSIPDectAuthKey(testData[3]);
			pmExtension.setSIPDectIPEINumber(testData[4]);
			pmExtension.getApplyButton().click();
		}
		
		pmExtension.getPENListButton().click();
		
		new Call_list_utilities(driver).create_extension_with_personalNumber(driver, methodName,
				loginData, pmTests, ipData);
		pmExtension.getMultiStepBackButton().click();
		
		pmExtension.getApplyButton().click();
		Assert.assertEquals(pmExtension.getResponseMessage(), "Add operation successful for:");
		pmExtension.getDoneButton().click();
		
		if(extType.equals("IP"))
		{
			new SelectDropDownValue().selectByVisibleText(pmExtension.getExtensionTypeDropDownHomePage(), "IP");
		}
		else if(extType.equals("analog"))
		{
			new SelectDropDownValue().selectByVisibleText(pmExtension.getExtensionTypeDropDownHomePage(), "Analog");
		}
		else if(extType.equals("digital"))
		{
			new SelectDropDownValue().selectByVisibleText(pmExtension.getExtensionTypeDropDownHomePage(), "Digital");
		}
		else if(extType.equals("virtual"))
		{
			new SelectDropDownValue().selectByVisibleText(pmExtension.getExtensionTypeDropDownHomePage(), "Virtual");
		}
		else if(extType.equals("sip-dect"))
		{
			new SelectDropDownValue().selectByVisibleText(pmExtension.getExtensionTypeDropDownHomePage(), "Multi-Terminal");
		}
		
		pmExtension.setEnterExtensionNumberTextBox(testData[1]);
		pmExtension.getViewRangeButton().click();
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("blockUI")));
		driver.findElement(By.xpath("//td[contains(text(),'"+testData[1]+"')]//preceding-sibling::td[20]")).click();
	
		new Call_list_utilities(driver).verifyPN_In_Ext_View_Page(driver, "1");
		pmExtension.getDoneButton().click();
		
	}
	
	public void create_externalNumberLength(WebDriver driver, String methodName,
			ExcelReadAndWrite ipData, ExcelReadAndWrite snmData,
			ExcelReadAndWrite loginData, Logger log)
	{
		List<WebElement> list = null;
		wait = new WebDriverWait(driver, 15);
		String[] snmCredentials = null;
		String[] testData = null;
		
			snmCredentials = loginData.getData("test_snm_valid_login", 1);
			testData = snmData.getData(methodName, 1);
			driver.get(ipData.getData(1, 0));
			snmLogin.snm_login(snmCredentials[0], snmCredentials[1]);
			
			log.info("After successful login");
			
			snmMainPage.getNumber_Analysis().click();
			numAnalysis.getNumber_Plan_Link().click();
			numPlan.getExternalNumberLength().click();
			extNumLength.getAddButton().click();
			extNumLength.setExternalNumberLength(testData[0]);
			extNumLength.setMinimumLength(testData[1]);
			extNumLength.setMaximumLengthField(testData[2]);
			extNumLength.getApplyButton().click();
			wait.until(ExpectedConditions.textToBePresentInElement(extNumLength.getResponseMsg(), 
					"Add operation successful for:"));
			log.debug("Add external number length is successful");
			extNumLength.getDoneButton().click();
			list = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath
					("//td[contains(text(),'"+testData[0]+"')]"
							+ "//following-sibling::td[contains(text(),'"+testData[1]+"')]"
							+ "//following-sibling::td[contains(text(),'"+testData[2]+"')]"));
			Assert.assertTrue(list.size() == 1);
	}
	
	public void createWorkingCSP(WebDriver driver, String methodName,
			ExcelReadAndWrite ipData, ExcelReadAndWrite snmData,
			ExcelReadAndWrite loginData, Logger log)
	{
		wait = new WebDriverWait(driver, 15);
		String[] snmCredentials = null;
		String[] testData = null;
		
			snmCredentials = loginData.getData("test_snm_valid_login", 1);
			testData = snmData.getData(methodName, 1);
			driver.get(ipData.getData(1, 0));
			snmLogin.snm_login(snmCredentials[0], snmCredentials[1]);
			
			snmMainPage.getTelephony().click();
			snmTelephony.getExtensions().click();
			telExt.getCSP().click();
			snmCSP.getAddButton().click();
			snmCSP.getCSPName().clear();
			snmCSP.setCSPName(testData[0]);
			new SelectDropDownValue().selectByValue(snmCSP.getCSPNumberDropDown(), "2");
			snmCSP.getNextButton().click();
			snmCSP.getRequestANumberFromPSTN().click();
			snmCSP.getNextButton().click();
			snmCSP.getNextButton().click();
			snmCSP.getAllowCallwaitingToneInitiation().click();
			new SelectDropDownValue().selectByValue(snmCSP.getCallwaitingToneReceptionBPartyDropDown(), "3");
			snmCSP.getCallwaitingToneReceptionCParty().click();
			snmCSP.getAllowIndividualDND().click();
			snmCSP.getAllowActivationDeactivationOfDND().click();
			snmCSP.getNextButton().click();
			snmCSP.getAllowFollowMe().click();
			new SelectDropDownValue().selectByValue(snmCSP.getAllowDirectDiversionToDropDown(), "1");
			snmCSP.getAllowDirectDiversionOnBusy().click();
			snmCSP.getAllowDirectDiversionOnNoAnswer().click();
			snmCSP.getAllowMultiDirectoryDiversion().click();
			snmCSP.getAllowRemoteProgrammingOnFollowMe().click();
			snmCSP.getAllowRemoteProgrammingOnNoReply().click();
			snmCSP.getAllowRemoteProgrammingOnECF().click();
			snmCSP.getAllowRemoteProgrammingOnBusy().click();
			snmCSP.getAllowRemoteProgrammingOnDirectDiversion().click();
			snmCSP.getAllowPrivateNetworkPartiesToChooseDiversion().click();
			snmCSP.getNextButton().click();
			snmCSP.getCSPApplyButton().click();
			wait.until(ExpectedConditions.textToBePresentInElement(snmCSP.getResponseMessage(), 
					"Add operation successful for:"));
			snmCSP.getDoneButton().click();
			driver.findElement(By.xpath("//td[contains(text(),'2')]"
					+ "//following-sibling::td[contains(text(),'"+testData[0]+"')]"
					+ "//preceding-sibling::td[22]")).click();
			
			List<WebElement> list1 = new ExplicitWait().numberOfElementsPresent(driver, 3, 
			By.xpath("//td[contains(text(),'CSP Number')]//following-sibling::td[contains(text(),'2')]"));
			
			List<WebElement> list2 = new ExplicitWait().numberOfElementsPresent(driver, 3, 
			By.xpath("//td[contains(text(),'CSP Name')]//following-sibling::td[contains(text(),'"+testData[0]+"')]"));
			
			List<WebElement> list3 = new ExplicitWait().numberOfElementsPresent(driver, 3, 
			By.xpath("//td[contains(text(),'Request A-number from the PSTN')]"
							+ "//following-sibling::td[contains(text(),'Not restricted for extension')]"));
			
			List<WebElement> list4 = new ExplicitWait().numberOfElementsPresent(driver, 3, 
			By.xpath("//td[contains(text(),'Allow Call Waiting Tone Initiation')]"
					+ "//following-sibling::td[contains(text(),'Yes')]"));
			
			List<WebElement> list5 = new ExplicitWait().numberOfElementsPresent(driver, 3, 
				By.xpath("//td[contains(text(),'Call Waiting Tone Reception(B-party)')]"
							+ "//following-sibling::td[contains(text(),'Active "
							+ "(calls from extension, attendant, external line)')]"));
			
			List<WebElement> list6 = new ExplicitWait().numberOfElementsPresent(driver, 3, 
					By.xpath("//td[contains(text(),'Call Waiting Tone Reception(C-party)')]"
							+ "//following-sibling::td[contains(text(),'Yes')]"));
			
			List<WebElement> list7 = new ExplicitWait().numberOfElementsPresent(driver, 3, 
					By.xpath("//td[contains(text(),'Allow Individual Do Not Disturb')]"
							+ "//following-sibling::td[contains(text(),'Yes')]"));
			
			List<WebElement> list8 = new ExplicitWait().numberOfElementsPresent(driver, 3, 
					By.xpath("//td[contains(text(),'Enable Common Authorization Code')]"
							+ "//following-sibling::td[contains(text(),'Enabled')]"));
			
			List<WebElement> list9 = new ExplicitWait().numberOfElementsPresent(driver, 3, 
					By.xpath("//td[contains(text(),'Allow Activation/Deactivation of Group Do Not Disturb')]"
							+ "//following-sibling::td[contains(text(),'Permitted')]"));
			
			List<WebElement> list10 = new ExplicitWait().numberOfElementsPresent(driver, 3, 
					By.xpath("//td[contains(text(),'Allow External Follow Me')]"
							+ "//following-sibling::td[contains(text(),'Yes')]"));
			
			List<WebElement> list11 = new ExplicitWait().numberOfElementsPresent(driver, 3, 
					By.xpath("//td[contains(text(),'Allow Follow Me')]"
							+ "//following-sibling::td[contains(text(),'Yes')]"));
			
			List<WebElement> list12 = new ExplicitWait().numberOfElementsPresent(driver, 3, 
					By.xpath("//td[contains(text(),'Allow Direct Diversion to')]"
							+ "//following-sibling::td[contains(text(),'An individual or "
							+ "common divertee position')]"));
			
			List<WebElement> list13 = new ExplicitWait().numberOfElementsPresent(driver, 3, 
					By.xpath("//td[contains(text(),'Allow Diversion on Busy')]"
							+ "//following-sibling::td[contains(text(),'Yes')]"));
			
			List<WebElement> list14 = new ExplicitWait().numberOfElementsPresent(driver, 3, 
					By.xpath("//td[contains(text(),'Allow Diversion on No Answer')]"
							+ "//following-sibling::td[contains(text(),'Yes')]"));
			
			List<WebElement> list15 = new ExplicitWait().numberOfElementsPresent(driver, 3, 
					By.xpath("//td[contains(text(),'Allow Multi Directory Diversion')]"
							+ "//following-sibling::td[contains(text(),'Yes')]"));
			
			List<WebElement> list16 = new ExplicitWait().numberOfElementsPresent(driver, 3, 
					By.xpath("//td[contains(text(),'Allow Remote Programming on Follow Me')]"
							+ "//following-sibling::td[contains(text(),'Yes')]"));
			
			List<WebElement> list17 = new ExplicitWait().numberOfElementsPresent(driver, 3, 
					By.xpath("//td[contains(text(),'Allow Remote Programming on ECF')]"
							+ "//following-sibling::td[contains(text(),'Yes')]"));
			
			List<WebElement> list18 = new ExplicitWait().numberOfElementsPresent(driver, 3, 
					By.xpath("//td[contains(text(),'Allow Remote Programming on No Reply')]"
							+ "//following-sibling::td[contains(text(),'Yes')]"));
			
			List<WebElement> list19 = new ExplicitWait().numberOfElementsPresent(driver, 3, 
					By.xpath("//td[contains(text(),'Allow Remote Programming on Busy')]"
							+ "//following-sibling::td[contains(text(),'Yes')]"));
			
			List<WebElement> list20 = new ExplicitWait().numberOfElementsPresent(driver, 3, 
					By.xpath("//td[contains(text(),'Allow Remote Programming on Direct Diversion')]"
							+ "//following-sibling::td[contains(text(),'Yes')]"));
			
			List<WebElement> list21 = new ExplicitWait().numberOfElementsPresent(driver, 3, 
					By.xpath("//td[contains(text(),'Allow Private Network Parties to choose Diversion "
							+ "on origin.')]//following-sibling::td[contains(text(),'Yes')]"));
			
			Assert.assertTrue(list1.size()==1 && list2.size()==1 && list3.size()==1 && list4.size()==1 && list5.size()==1
					&&	list6.size()==1 && list7.size()==1 && list8.size()==1 && list9.size()==1 && list10.size()==1
					&& list11.size()==1 && list12.size()==1 && list13.size()==1 && list14.size()==1 && list15.size()==1 && list16.size()==1  &&
					list17.size()==1  && list18.size()==1 && list19.size()==1 && list20.size()==1 && list21.size()==1);
			snmCSP.getDoneButton().click();
//			snmMainPage.getLogoutButton().click();
	}
	
	
	public void createDefaultCSP(WebDriver driver, String methodName,
			ExcelReadAndWrite ipData, ExcelReadAndWrite snmData,
			ExcelReadAndWrite loginData, Logger log)
	{
		wait = new WebDriverWait(driver, 15);
		String[] snmCredentials = null;
		String[] testData = null;
		
			snmCredentials = loginData.getData("test_snm_valid_login", 1);
			testData = snmData.getData(methodName, 1);
			driver.get(ipData.getData(1, 0));
			snmLogin.snm_login(snmCredentials[0], snmCredentials[1]);
			
			snmMainPage.getTelephony().click();
			snmTelephony.getExtensions().click();
			telExt.getCSP().click();
			snmCSP.getAddButton().click();
			snmCSP.getCSPName().clear();
			snmCSP.setCSPName(testData[0]);
			new SelectDropDownValue().selectByValue(snmCSP.getCSPNumberDropDown(), "2");
			snmCSP.getNextButton().click();
//			snmCSP.getRequestANumberFromPSTN().click();
			snmCSP.getNextButton().click();
			snmCSP.getNextButton().click();
			snmCSP.getNextButton().click();
			snmCSP.getNextButton().click();
			snmCSP.getCSPApplyButton().click();
			wait.until(ExpectedConditions.textToBePresentInElement(snmCSP.getResponseMessage(), 
					"Add operation successful for:"));
			snmCSP.getDoneButton().click();
			driver.findElement(By.xpath("//td[contains(text(),'2')]"
					+ "//following-sibling::td[contains(text(),'"+testData[0]+"')]"
					+ "//preceding-sibling::td[22]")).click();
			
			List<WebElement> list1 = new ExplicitWait().numberOfElementsPresent(driver, 3, 
			By.xpath("//td[contains(text(),'CSP Number')]//following-sibling::td[contains(text(),'2')]"));
			
			List<WebElement> list2 = new ExplicitWait().numberOfElementsPresent(driver, 3, 
			By.xpath("//td[contains(text(),'CSP Name')]//following-sibling::td[contains(text(),'"+testData[0]+"')]"));
			
			Assert.assertTrue(list1.size()==1 && list2.size()==1);
			snmCSP.getDoneButton().click();
	}
	
	public void editAnyGivenCSP(WebDriver driver, String methodName,
			ExcelReadAndWrite ipData, ExcelReadAndWrite snmData,
			ExcelReadAndWrite loginData, Logger log, String cspChanges) throws InterruptedException
	{
		wait = new WebDriverWait(driver, 5);
//		String[] snmCredentials = loginData.getData("test_snm_valid_login", 1);
		String[] testData = snmData.getData(methodName, 1);
		driver.findElement(By.xpath("//td[contains(text(),'"+testData[0]+"')]//preceding-sibling::td[21]")).click();
		if(cspChanges.equalsIgnoreCase("RequestANumberForPSTN"))
		{
			snmCSP.getNumberPresentationCategory().click();
			snmCSP.getRequestANumberFromPSTN().click();
			snmCSP.getEditCSPApplyButton().click();
			wait.until(ExpectedConditions.textToBePresentInElement(snmCSP.getResponseMessage(), 
					"Change operation successful for:"));
			log.info("After configuring Request A number from PSTN");
			snmCSP.getDoneButton().click();
			driver.findElement(By.xpath("//td[contains(text(),'2')]"
					+ "//following-sibling::td[contains(text(),'"+testData[0]+"')]"
					+ "//preceding-sibling::td[22]")).click();
			
			List<WebElement> list1 = new ExplicitWait().numberOfElementsPresent(driver, 3, 
					By.xpath("//td[contains(text(),'CSP Number')]//following-sibling::td[contains(text(),'2')]"));
					
					List<WebElement> list2 = new ExplicitWait().numberOfElementsPresent(driver, 3, 
					By.xpath("//td[contains(text(),'CSP Name')]//following-sibling::td[contains(text(),'"+testData[0]+"')]"));
					
					List<WebElement> list3 = new ExplicitWait().numberOfElementsPresent(driver, 3, 
					By.xpath("//td[contains(text(),'Request A-number from the PSTN')]"
									+ "//following-sibling::td[contains(text(),'Not restricted for extension')]"));
					log.info("Request A Number from PSTN is configured successfully.");
					Assert.assertTrue(list1.size()==1 && list2.size()==1 && list3.size()==1);
					snmCSP.getDoneButton().click();
		}
		else if(cspChanges.equalsIgnoreCase("AllowCallWaitingToneInitiation"))
		{
			snmCSP.getServiceCategory().click();
			snmCSP.getAllowCallwaitingToneInitiation().click();
			snmCSP.getEditCSPApplyButton().click();
			wait.until(ExpectedConditions.textToBePresentInElement(snmCSP.getResponseMessage(), 
					"Change operation successful for:"));
			log.info("After configuring Allow Call Waiting Tone Initiation");
			snmCSP.getDoneButton().click();
			driver.findElement(By.xpath("//td[contains(text(),'2')]"
					+ "//following-sibling::td[contains(text(),'"+testData[0]+"')]"
					+ "//preceding-sibling::td[22]")).click();
			
			List<WebElement> list1 = new ExplicitWait().numberOfElementsPresent(driver, 3, 
					By.xpath("//td[contains(text(),'CSP Number')]//following-sibling::td[contains(text(),'2')]"));
					
					List<WebElement> list2 = new ExplicitWait().numberOfElementsPresent(driver, 3, 
					By.xpath("//td[contains(text(),'CSP Name')]//following-sibling::td[contains(text(),'"+testData[0]+"')]"));
					
					List<WebElement> list3 = new ExplicitWait().numberOfElementsPresent(driver, 3, 
							By.xpath("//td[contains(text(),'Allow Call Waiting Tone Initiation')]"
									+ "//following-sibling::td[contains(text(),'Yes')]"));
					Assert.assertTrue(list1.size()==1 && list2.size()==1 && list3.size()==1);
					snmCSP.getDoneButton().click();
		}
		else if(cspChanges.equalsIgnoreCase("CallWaitingToneReceptionBParty"))
		{
			snmCSP.getServiceCategory().click();
			new SelectDropDownValue().selectByValue(snmCSP.getCallwaitingToneReceptionBPartyDropDown(), "3");
			snmCSP.getEditCSPApplyButton().click();
			wait.until(ExpectedConditions.textToBePresentInElement(snmCSP.getResponseMessage(), 
					"Change operation successful for:"));
			log.info("After configuring Call Waiting Tone Reception(B-party)");
			snmCSP.getDoneButton().click();
			driver.findElement(By.xpath("//td[contains(text(),'2')]"
					+ "//following-sibling::td[contains(text(),'"+testData[0]+"')]"
					+ "//preceding-sibling::td[22]")).click();
			
			List<WebElement> list1 = new ExplicitWait().numberOfElementsPresent(driver, 3, 
					By.xpath("//td[contains(text(),'CSP Number')]//following-sibling::td[contains(text(),'2')]"));
					
					List<WebElement> list2 = new ExplicitWait().numberOfElementsPresent(driver, 3, 
					By.xpath("//td[contains(text(),'CSP Name')]//following-sibling::td[contains(text(),'"+testData[0]+"')]"));
					
					List<WebElement> list3 = new ExplicitWait().numberOfElementsPresent(driver, 3, 
							By.xpath("//td[contains(text(),'Call Waiting Tone Reception(B-party)')]"
										+ "//following-sibling::td[contains(text(),'Active "
										+ "(calls from extension, attendant, external line)')]"));
					Assert.assertTrue(list1.size()==1 && list2.size()==1 && list3.size()==1);
					snmCSP.getDoneButton().click();
		}
		else if(cspChanges.equalsIgnoreCase("CallWaitingToneReceptionCParty"))
		{
			snmCSP.getServiceCategory().click();
			snmCSP.getCallwaitingToneReceptionCParty().click();
			snmCSP.getEditCSPApplyButton().click();
			wait.until(ExpectedConditions.textToBePresentInElement(snmCSP.getResponseMessage(), 
					"Change operation successful for:"));
			log.info("After configuring Call Waiting Tone Reception(B-party)");
			snmCSP.getDoneButton().click();
			driver.findElement(By.xpath("//td[contains(text(),'2')]"
					+ "//following-sibling::td[contains(text(),'"+testData[0]+"')]"
					+ "//preceding-sibling::td[22]")).click();
			
			List<WebElement> list1 = new ExplicitWait().numberOfElementsPresent(driver, 3, 
					By.xpath("//td[contains(text(),'CSP Number')]//following-sibling::td[contains(text(),'2')]"));
					
					List<WebElement> list2 = new ExplicitWait().numberOfElementsPresent(driver, 3, 
					By.xpath("//td[contains(text(),'CSP Name')]//following-sibling::td[contains(text(),'"+testData[0]+"')]"));
					
					List<WebElement> list3 = new ExplicitWait().numberOfElementsPresent(driver, 3, 
							By.xpath("//td[contains(text(),'Call Waiting Tone Reception(C-party)')]"
									+ "//following-sibling::td[contains(text(),'Yes')]"));
					Assert.assertTrue(list1.size()==1 && list2.size()==1 && list3.size()==1);
					snmCSP.getDoneButton().click();
		}
		else if(cspChanges.equalsIgnoreCase("AllowIndividualDND"))
		{
			snmCSP.getServiceCategory().click();
			snmCSP.getAllowIndividualDND().click();
			snmCSP.getEditCSPApplyButton().click();
			wait.until(ExpectedConditions.textToBePresentInElement(snmCSP.getResponseMessage(), 
					"Change operation successful for:"));
			log.info("After configuring Call Waiting Tone Reception(B-party)");
			snmCSP.getDoneButton().click();
			driver.findElement(By.xpath("//td[contains(text(),'2')]"
					+ "//following-sibling::td[contains(text(),'"+testData[0]+"')]"
					+ "//preceding-sibling::td[22]")).click();
			
			List<WebElement> list1 = new ExplicitWait().numberOfElementsPresent(driver, 3, 
					By.xpath("//td[contains(text(),'CSP Number')]//following-sibling::td[contains(text(),'2')]"));
					
					List<WebElement> list2 = new ExplicitWait().numberOfElementsPresent(driver, 3, 
					By.xpath("//td[contains(text(),'CSP Name')]//following-sibling::td[contains(text(),'"+testData[0]+"')]"));
					
					List<WebElement> list3 = new ExplicitWait().numberOfElementsPresent(driver, 3, 
							By.xpath("//td[contains(text(),'Allow Individual Do Not Disturb')]"
									+ "//following-sibling::td[contains(text(),'Yes')]"));
					Assert.assertTrue(list1.size()==1 && list2.size()==1 && list3.size()==1);
					snmCSP.getDoneButton().click();
		}
		else if(cspChanges.equalsIgnoreCase("ActivationDeactivationGrpDND"))
		{
			snmCSP.getServiceCategory().click();
			snmCSP.getAllowActivationDeactivationOfDND().click();
			snmCSP.getEditCSPApplyButton().click();
			wait.until(ExpectedConditions.textToBePresentInElement(snmCSP.getResponseMessage(), 
					"Change operation successful for:"));
			log.info("After configuring Activation/Deactivation of Group Do Not Disturb");
			snmCSP.getDoneButton().click();
			driver.findElement(By.xpath("//td[contains(text(),'2')]"
					+ "//following-sibling::td[contains(text(),'"+testData[0]+"')]"
					+ "//preceding-sibling::td[22]")).click();
			
			List<WebElement> list1 = new ExplicitWait().numberOfElementsPresent(driver, 3, 
					By.xpath("//td[contains(text(),'CSP Number')]//following-sibling::td[contains(text(),'2')]"));
					
					List<WebElement> list2 = new ExplicitWait().numberOfElementsPresent(driver, 3, 
					By.xpath("//td[contains(text(),'CSP Name')]//following-sibling::td[contains(text(),'"+testData[0]+"')]"));
					
					List<WebElement> list3 = new ExplicitWait().numberOfElementsPresent(driver, 3, 
							By.xpath("//td[contains(text(),'Allow Activation/Deactivation of Group Do Not Disturb')]"
									+ "//following-sibling::td[contains(text(),'Permitted')]"));
					Assert.assertTrue(list1.size()==1 && list2.size()==1 && list3.size()==1);
					snmCSP.getDoneButton().click();
		}
		else if(cspChanges.equalsIgnoreCase("ForceGatewayCalls"))
		{
			snmCSP.getServiceCategory().click();
			snmCSP.getAllowForceGatewayCalls().click();
			snmCSP.getEditCSPApplyButton().click();
			wait.until(ExpectedConditions.textToBePresentInElement(snmCSP.getResponseMessage(), 
					"Change operation successful for:"));
			log.info("After configuring force gateway calls");
			snmCSP.getDoneButton().click();
			driver.findElement(By.xpath("//td[contains(text(),'2')]"
					+ "//following-sibling::td[contains(text(),'"+testData[0]+"')]"
					+ "//preceding-sibling::td[22]")).click();
			
			List<WebElement> list1 = new ExplicitWait().numberOfElementsPresent(driver, 3, 
					By.xpath("//td[contains(text(),'CSP Number')]//following-sibling::td[contains(text(),'2')]"));
					
					List<WebElement> list2 = new ExplicitWait().numberOfElementsPresent(driver, 3, 
					By.xpath("//td[contains(text(),'CSP Name')]//following-sibling::td[contains(text(),'"+testData[0]+"')]"));
					
					List<WebElement> list3 = new ExplicitWait().numberOfElementsPresent(driver, 3, 
							By.xpath("//td[contains(text(),'Force Calls from or to IP Terminal to be Gateway "
									+ "Calls')]//following-sibling::td[contains(text(),'Yes')]"));
					Assert.assertTrue(list1.size()==1 && list2.size()==1 && list3.size()==1);
					snmCSP.getDoneButton().click();
		}
		else if(cspChanges.equalsIgnoreCase("CallDiversionCategory"))
		{
			snmCSP.getCallDiversionCategory().click();
//			snmCSP.getAllowExternalFollowMe().click();
			snmCSP.getAllowFollowMe().click();
			new SelectDropDownValue().selectByValue(snmCSP.getAllowDirectDiversionToDropDown(), "1");
			snmCSP.getAllowDirectDiversionOnBusy().click();
			snmCSP.getAllowDirectDiversionOnNoAnswer().click();
			snmCSP.getAllowMultiDirectoryDiversion().click();
			snmCSP.getAllowRemoteProgrammingOnFollowMe().click();
			snmCSP.getAllowRemoteProgrammingOnNoReply().click();
			snmCSP.getAllowRemoteProgrammingOnECF().click();
			snmCSP.getAllowRemoteProgrammingOnBusy().click();
			snmCSP.getAllowRemoteProgrammingOnDirectDiversion().click();
			snmCSP.getAllowPrivateNetworkPartiesToChooseDiversion().click();
			
			snmCSP.getEditCSPApplyButton().click();
			wait.until(ExpectedConditions.textToBePresentInElement(snmCSP.getResponseMessage(), 
					"Change operation successful for:"));
			log.info("After configuring force gateway calls");
			snmCSP.getDoneButton().click();
			driver.findElement(By.xpath("//td[contains(text(),'2')]"
					+ "//following-sibling::td[contains(text(),'"+testData[0]+"')]"
					+ "//preceding-sibling::td[22]")).click();
			
			List<WebElement> list1 = new ExplicitWait().numberOfElementsPresent(driver, 3, 
					By.xpath("//td[contains(text(),'CSP Number')]//following-sibling::td[contains(text(),'2')]"));
					
					List<WebElement> list2 = new ExplicitWait().numberOfElementsPresent(driver, 3, 
					By.xpath("//td[contains(text(),'CSP Name')]//following-sibling::td[contains(text(),'"+testData[0]+"')]"));
					
					
					List<WebElement> list3 = new ExplicitWait().numberOfElementsPresent(driver, 3, 
							By.xpath("//td[contains(text(),'Allow External Follow Me')]"
									+ "//following-sibling::td[contains(text(),'Yes')]"));
					
					List<WebElement> list4 = new ExplicitWait().numberOfElementsPresent(driver, 3, 
							By.xpath("//td[contains(text(),'Allow Follow Me')]"
									+ "//following-sibling::td[contains(text(),'Yes')]"));
					
					List<WebElement> list5 = new ExplicitWait().numberOfElementsPresent(driver, 3, 
							By.xpath("//td[contains(text(),'Allow Direct Diversion to')]"
									+ "//following-sibling::td[contains(text(),'An individual or "
									+ "common divertee position')]"));
					
					List<WebElement> list6 = new ExplicitWait().numberOfElementsPresent(driver, 3, 
							By.xpath("//td[contains(text(),'Allow Diversion on Busy')]"
									+ "//following-sibling::td[contains(text(),'Yes')]"));
					
					List<WebElement> list7 = new ExplicitWait().numberOfElementsPresent(driver, 3, 
							By.xpath("//td[contains(text(),'Allow Diversion on No Answer')]"
									+ "//following-sibling::td[contains(text(),'Yes')]"));
					
					List<WebElement> list8 = new ExplicitWait().numberOfElementsPresent(driver, 3, 
							By.xpath("//td[contains(text(),'Allow Multi Directory Diversion')]"
									+ "//following-sibling::td[contains(text(),'Yes')]"));
					
					List<WebElement> list9 = new ExplicitWait().numberOfElementsPresent(driver, 3, 
							By.xpath("//td[contains(text(),'Allow Remote Programming on Follow Me')]"
									+ "//following-sibling::td[contains(text(),'Yes')]"));
					
					List<WebElement> list10 = new ExplicitWait().numberOfElementsPresent(driver, 3, 
							By.xpath("//td[contains(text(),'Allow Remote Programming on ECF')]"
									+ "//following-sibling::td[contains(text(),'Yes')]"));
					
					List<WebElement> list11 = new ExplicitWait().numberOfElementsPresent(driver, 3, 
							By.xpath("//td[contains(text(),'Allow Remote Programming on No Reply')]"
									+ "//following-sibling::td[contains(text(),'Yes')]"));
					
					List<WebElement> list12 = new ExplicitWait().numberOfElementsPresent(driver, 3, 
							By.xpath("//td[contains(text(),'Allow Remote Programming on Busy')]"
									+ "//following-sibling::td[contains(text(),'Yes')]"));
					
					List<WebElement> list13 = new ExplicitWait().numberOfElementsPresent(driver, 3, 
							By.xpath("//td[contains(text(),'Allow Remote Programming on Direct Diversion')]"
									+ "//following-sibling::td[contains(text(),'Yes')]"));
					
					List<WebElement> list14 = new ExplicitWait().numberOfElementsPresent(driver, 3, 
							By.xpath("//td[contains(text(),'Allow Private Network Parties to choose Diversion "
									+ "on origin.')]//following-sibling::td[contains(text(),'Yes')]"));
//					Thread.sleep(100000);
					Assert.assertTrue(list1.size()==1 && list2.size()==1 && list3.size()==1 && list4.size()==1 && list5.size()==1
						&&	list6.size()==1 && list7.size()==1 && list8.size()==1 && list9.size()==1 && list10.size()==1
							&& list11.size()==1 && list12.size()==1 && list13.size()==1 && list14.size()==1);
					snmCSP.getDoneButton().click();
		}
	}
	
	public void createCSPTemplate(WebDriver driver, String methodName, 
			ExcelReadAndWrite ipData, ExcelReadAndWrite loginData, 
			String templateName) throws InterruptedException
	{
		WebDriverWait wait = new WebDriverWait(driver, 5);
		String[] credentials = loginData.getData("test_snm_valid_login", 1);
//		String[] testData = snmTests.getData(methodName, 3);
		driver.get(ipData.getData(1, 0));
		snmLogin.snm_login(credentials[0], credentials[1]);
		snmMainPage.getTelephony().click();
		snmTelephony.getExtensions().click();
		telExt.getCSP().click();
		snmCSP.getManageTemplatesLink().click();
		snmCSP.getTemplateAddButton().click();
		snmCSP.getNextButton().click();
		
		snmCSP.getRequestANumberFromPSTN().click();
		snmCSP.getNextButton().click();
		snmCSP.getNextButton().click();
		snmCSP.getAllowCallwaitingToneInitiation().click();
		new SelectDropDownValue().selectByValue(snmCSP.getCallwaitingToneReceptionBPartyDropDown(), "3");
		snmCSP.getCallwaitingToneReceptionCParty().click();
		snmCSP.getAllowIndividualDND().click();
		snmCSP.getAllowActivationDeactivationOfDND().click();
		snmCSP.getNextButton().click();
		snmCSP.getAllowFollowMe().click();
		new SelectDropDownValue().selectByValue(snmCSP.getAllowDirectDiversionToDropDown(), "1");
		snmCSP.getAllowDirectDiversionOnBusy().click();
		snmCSP.getAllowDirectDiversionOnNoAnswer().click();
		snmCSP.getAllowMultiDirectoryDiversion().click();
		snmCSP.getAllowRemoteProgrammingOnFollowMe().click();
		snmCSP.getAllowRemoteProgrammingOnNoReply().click();
		snmCSP.getAllowRemoteProgrammingOnECF().click();
		snmCSP.getAllowRemoteProgrammingOnBusy().click();
		snmCSP.getAllowRemoteProgrammingOnDirectDiversion().click();
		snmCSP.getAllowPrivateNetworkPartiesToChooseDiversion().click();
		snmCSP.getNextButton().click();
		snmCSP.getNextButton().click();
		snmCSP.getUploadCSPTemplateName().sendKeys(templateName);
		snmCSP.getCSPApplyButton().click();
		wait.until(ExpectedConditions.textToBePresentInElement(snmCSP.getResponseMessage(), 
				"New Template operation successful for:"));
		snmCSP.getDoneButton().click();
		List<WebElement> list = new ExplicitWait().numberOfElementsPresent(driver, 3, 
				By.xpath("//td[contains(text(),'"+templateName+"')]"));
		Assert.assertTrue(list.size()==1);
//		snmCSP.getManageTemplatesContinueButton().click();
//		pmMainPge.getLogoutLink().click();
	}
	
	public void createCustomer(WebDriver driver,String methodName, ExcelReadAndWrite ipData, 
			ExcelReadAndWrite snmTests,
			ExcelReadAndWrite loginData,
			Logger log)
	{
		List<WebElement> list = null;
		WebDriverWait wait = new WebDriverWait(driver, 15);
		String[] snmCredentials = null;
		String[] testData = null;
		
			snmCredentials = loginData.getData("test_snm_valid_login", 1);
			testData = snmTests.getData(methodName, 1);
			driver.get(ipData.getData(1, 0));
			snmLogin.snm_login(snmCredentials[0], snmCredentials[1]);
			
			log.info("After successful login");
			
			snmMainPage.getTelephony().click();
			snmTelephony.getGroups().click();
			snmTelGrp.getCustomerLink().click();
			snmTelGrpCust.getCustAddButton().click();
			snmTelGrpCust.setCustomerName(testData[1]);
			snmTelGrpCust.setCustomerNumber(testData[2]);
			snmTelGrpCust.getCustomerApplyButton().click();
			wait.until(ExpectedConditions.textToBePresentInElement(snmCSP.getResponseMessage(), 
					"Add operation successful for:"));
			snmTelGrpCust.getDoneButton().click();
			List<WebElement> elePresent = new ExplicitWait().numberOfElementsPresent(driver, 3, 
					By.xpath("//td[contains(text(),'"+testData[2]+"')]//following-sibling::td[contains(text(),'"+testData[1]+"')]"));
			Assert.assertTrue(elePresent.size()==1);
			snmMainPage.getLogoutButton().click();
	}
	
	
	public void createWorkingCSPWithCustomer(WebDriver driver, String methodName,
			ExcelReadAndWrite ipData, ExcelReadAndWrite snmData,
			ExcelReadAndWrite loginData, Logger log)
	{
		wait = new WebDriverWait(driver, 15);
		String[] snmCredentials = null;
		String[] testData = null;
		
			snmCredentials = loginData.getData("test_snm_valid_login", 1);
			testData = snmData.getData(methodName, 1);
			driver.get(ipData.getData(1, 0));
			snmLogin.snm_login(snmCredentials[0], snmCredentials[1]);
			
			snmMainPage.getTelephony().click();
			snmTelephony.getExtensions().click();
			telExt.getCSP().click();
			snmCSP.getAddButton().click();
			new SelectDropDownValue().selectByValue(snmCSP.getCSPCustomerDropDown(), testData[1]);
			snmCSP.getCSPName().clear();
			snmCSP.setCSPName(testData[0]);
			new SelectDropDownValue().selectByValue(snmCSP.getCSPNumberDropDown(), "0");
			snmCSP.getNextButton().click();
			snmCSP.getRequestANumberFromPSTN().click();
			snmCSP.getNextButton().click();
			snmCSP.getNextButton().click();
			snmCSP.getAllowCallwaitingToneInitiation().click();
			new SelectDropDownValue().selectByValue(snmCSP.getCallwaitingToneReceptionBPartyDropDown(), "3");
			snmCSP.getCallwaitingToneReceptionCParty().click();
			snmCSP.getAllowIndividualDND().click();
			snmCSP.getAllowActivationDeactivationOfDND().click();
			snmCSP.getNextButton().click();
			snmCSP.getAllowFollowMe().click();
			new SelectDropDownValue().selectByValue(snmCSP.getAllowDirectDiversionToDropDown(), "1");
			snmCSP.getAllowDirectDiversionOnBusy().click();
			snmCSP.getAllowDirectDiversionOnNoAnswer().click();
			snmCSP.getAllowMultiDirectoryDiversion().click();
			snmCSP.getAllowRemoteProgrammingOnFollowMe().click();
			snmCSP.getAllowRemoteProgrammingOnNoReply().click();
			snmCSP.getAllowRemoteProgrammingOnECF().click();
			snmCSP.getAllowRemoteProgrammingOnBusy().click();
			snmCSP.getAllowRemoteProgrammingOnDirectDiversion().click();
			snmCSP.getAllowPrivateNetworkPartiesToChooseDiversion().click();
			snmCSP.getNextButton().click();
			snmCSP.getCSPApplyButton().click();
			wait.until(ExpectedConditions.textToBePresentInElement(snmCSP.getResponseMessage(), 
					"Add operation successful for:"));
			snmCSP.getDoneButton().click();
			driver.findElement(By.xpath("//td[contains(text(),'0')]"
					+ "//following-sibling::td[contains(text(),'"+testData[0]+"')]"
					+ "//following-sibling::td[contains(text(),'"+testData[1]+"')]//preceding-sibling::td[24]")).click();
			
			List<WebElement> list1 = new ExplicitWait().numberOfElementsPresent(driver, 3, 
			By.xpath("//td[contains(text(),'CSP Number')]//following-sibling::td[contains(text(),'0')]"));
			
			List<WebElement> list2 = new ExplicitWait().numberOfElementsPresent(driver, 3, 
			By.xpath("//td[contains(text(),'CSP Name')]//following-sibling::td[contains(text(),'"+testData[0]+"')]"));
			
			List<WebElement> list3 = new ExplicitWait().numberOfElementsPresent(driver, 3, 
			By.xpath("//td[contains(text(),'Request A-number from the PSTN')]"
							+ "//following-sibling::td[contains(text(),'Not restricted for extension')]"));
			
			List<WebElement> list4 = new ExplicitWait().numberOfElementsPresent(driver, 3, 
			By.xpath("//td[contains(text(),'Allow Call Waiting Tone Initiation')]"
					+ "//following-sibling::td[contains(text(),'Yes')]"));
			
			List<WebElement> list5 = new ExplicitWait().numberOfElementsPresent(driver, 3, 
				By.xpath("//td[contains(text(),'Call Waiting Tone Reception(B-party)')]"
							+ "//following-sibling::td[contains(text(),'Active "
							+ "(calls from extension, attendant, external line)')]"));
			
			List<WebElement> list6 = new ExplicitWait().numberOfElementsPresent(driver, 3, 
					By.xpath("//td[contains(text(),'Call Waiting Tone Reception(C-party)')]"
							+ "//following-sibling::td[contains(text(),'Yes')]"));
			
			List<WebElement> list7 = new ExplicitWait().numberOfElementsPresent(driver, 3, 
					By.xpath("//td[contains(text(),'Allow Individual Do Not Disturb')]"
							+ "//following-sibling::td[contains(text(),'Yes')]"));
			
			List<WebElement> list8 = new ExplicitWait().numberOfElementsPresent(driver, 3, 
					By.xpath("//td[contains(text(),'Enable Common Authorization Code')]"
							+ "//following-sibling::td[contains(text(),'Enabled')]"));
			
			List<WebElement> list9 = new ExplicitWait().numberOfElementsPresent(driver, 3, 
					By.xpath("//td[contains(text(),'Allow Activation/Deactivation of Group Do Not Disturb')]"
							+ "//following-sibling::td[contains(text(),'Permitted')]"));
			
			List<WebElement> list10 = new ExplicitWait().numberOfElementsPresent(driver, 3, 
					By.xpath("//td[contains(text(),'Allow External Follow Me')]"
							+ "//following-sibling::td[contains(text(),'Yes')]"));
			
			List<WebElement> list11 = new ExplicitWait().numberOfElementsPresent(driver, 3, 
					By.xpath("//td[contains(text(),'Allow Follow Me')]"
							+ "//following-sibling::td[contains(text(),'Yes')]"));
			
			List<WebElement> list12 = new ExplicitWait().numberOfElementsPresent(driver, 3, 
					By.xpath("//td[contains(text(),'Allow Direct Diversion to')]"
							+ "//following-sibling::td[contains(text(),'An individual or "
							+ "common divertee position')]"));
			
			List<WebElement> list13 = new ExplicitWait().numberOfElementsPresent(driver, 3, 
					By.xpath("//td[contains(text(),'Allow Diversion on Busy')]"
							+ "//following-sibling::td[contains(text(),'Yes')]"));
			
			List<WebElement> list14 = new ExplicitWait().numberOfElementsPresent(driver, 3, 
					By.xpath("//td[contains(text(),'Allow Diversion on No Answer')]"
							+ "//following-sibling::td[contains(text(),'Yes')]"));
			
			List<WebElement> list15 = new ExplicitWait().numberOfElementsPresent(driver, 3, 
					By.xpath("//td[contains(text(),'Allow Multi Directory Diversion')]"
							+ "//following-sibling::td[contains(text(),'Yes')]"));
			
			List<WebElement> list16 = new ExplicitWait().numberOfElementsPresent(driver, 3, 
					By.xpath("//td[contains(text(),'Allow Remote Programming on Follow Me')]"
							+ "//following-sibling::td[contains(text(),'Yes')]"));
			
			List<WebElement> list17 = new ExplicitWait().numberOfElementsPresent(driver, 3, 
					By.xpath("//td[contains(text(),'Allow Remote Programming on ECF')]"
							+ "//following-sibling::td[contains(text(),'Yes')]"));
			
			List<WebElement> list18 = new ExplicitWait().numberOfElementsPresent(driver, 3, 
					By.xpath("//td[contains(text(),'Allow Remote Programming on No Reply')]"
							+ "//following-sibling::td[contains(text(),'Yes')]"));
			
			List<WebElement> list19 = new ExplicitWait().numberOfElementsPresent(driver, 3, 
					By.xpath("//td[contains(text(),'Allow Remote Programming on Busy')]"
							+ "//following-sibling::td[contains(text(),'Yes')]"));
			
			List<WebElement> list20 = new ExplicitWait().numberOfElementsPresent(driver, 3, 
					By.xpath("//td[contains(text(),'Allow Remote Programming on Direct Diversion')]"
							+ "//following-sibling::td[contains(text(),'Yes')]"));
			
			List<WebElement> list21 = new ExplicitWait().numberOfElementsPresent(driver, 3, 
					By.xpath("//td[contains(text(),'Allow Private Network Parties to choose Diversion "
							+ "on origin.')]//following-sibling::td[contains(text(),'Yes')]"));
			
			List<WebElement> list22 = new ExplicitWait().numberOfElementsPresent(driver, 3, 
					By.xpath("//td[contains(text(),'Customer')]//following-sibling::td[contains(text(),'"+testData[1]+"')]"));
			
			Assert.assertTrue(list1.size()==1 && list2.size()==1 && list3.size()==1 && list4.size()==1 && list5.size()==1
					&&	list6.size()==1 && list7.size()==1 && list8.size()==1 && list9.size()==1 && list10.size()==1
					&& list11.size()==1 && list12.size()==1 && list13.size()==1 && list14.size()==1 && list15.size()==1 && list16.size()==1  &&
					list17.size()==1  && list18.size()==1 && list19.size()==1 && list20.size()==1 && list21.size()==1 && list22.size()==1);
			snmCSP.getDoneButton().click();
//			snmMainPage.getLogoutButton().click();
	}
}
