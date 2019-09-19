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
import pm_pom_classes.PM_Function_Keys;
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
	PM_Function_Keys funcKeys;
	
	public ReusableUnits(WebDriver driver)
	{
		pmUser = new PM_User(driver);
		pmUsers = new PM_Users(driver);
		pmLoginPge = new PM_Login_Page(driver);
		pmMainPge = new PM_Main_Page(driver);
		pmServices = new PM_Services(driver);
		pmExtension = new Extension(driver);
		funcKeys = new PM_Function_Keys(driver);
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
		/*Assert.assertEquals(pmExtension.getResponseMessage(), "Add operation successful for:");
		pmExtension.getDoneButton().click();*/
		
		pmUser.getApplyButton().click();
		Assert.assertEquals(pmUser.getResponseMessage().getText().trim(), "Add operation successful for:");
		pmUser.getDoneButton().click();
		
		/*new SelectDropDownValue().selectByVisibleText(pmExtension.getExtensionTypeDropDownHomePage(), "IP");
		pmExtension.setEnterExtensionNumberTextBox(extData[2]);
		pmExtension.getViewRangeButton().click();*/
		
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
	
	
	public void createIPTemplate(WebDriver driver, String methodName, ExcelReadAndWrite ipData, ExcelReadAndWrite loginData, String templateName)
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
	
	
	public void create_virtual_template(WebDriver driver, String methodName, ExcelReadAndWrite ipData, ExcelReadAndWrite loginData, String templateName)
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
		
		
		/*pmExtension.getApplyButton().click();
		
		Assert.assertEquals(pmExtension.getResponseMessage(), "Add operation successful for:");
		pmExtension.getDoneButton().click();
		
		new SelectDropDownValue().selectByVisibleText(pmExtension.getExtensionTypeDropDownHomePage(), "Multi-Terminal");
		pmExtension.setEnterExtensionNumberTextBox(testData[1]);
		pmExtension.getViewRangeButton().click();
		
		List<WebElement> eles = driver.findElements(By.xpath("//td[contains(text(),'"+testData[2]+"')]"));
		Assert.assertTrue(eles.size()==1);*/
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
}
