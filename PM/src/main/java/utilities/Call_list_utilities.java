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
import pm_pom_classes.PM_Login_Page;
import pm_pom_classes.PM_Main_Page;
import pm_pom_classes.PM_Services;
import pm_pom_classes.PM_User;
import pm_pom_classes.PM_Users;

public class Call_list_utilities 
{
	public ArrayList<String> list;
	public PM_Login_Page loginPage;
	public PM_User pmUser;
	public PM_Users pmUsers;
	public Extension pmExtension;
	public PM_Main_Page pmMainPge;
	public PM_Services pmServices;
	WebDriverWait wait = null;
	
	public Call_list_utilities(WebDriver driver)
	{
		wait = new WebDriverWait(driver, 10);
		pmExtension = new Extension(driver);
		loginPage = new PM_Login_Page(driver);
		pmMainPge = new PM_Main_Page(driver);
		pmServices = new PM_Services(driver);
		pmUser = new PM_User(driver);
		pmUsers = new PM_Users(driver);
		list = new ArrayList<String>();
	}
	
	
	public void create_extension_with_personalNumber(WebDriver driver, String methodName, ExcelReadAndWrite loginData,
			ExcelReadAndWrite pmTests, ExcelReadAndWrite ipData) throws InterruptedException
	{
		String [] testData = pmTests.getData(methodName, 1);
		String [] extData = pmTests.getData(methodName, 3);
		////////////////////////////////////////////////////////////////////////////////////////
		String id = null; 
		for(int i=0; i<10; i++)
		{
			id = "changeThis";
			driver.findElement(By.id(id+i+"_img")).click();
			driver.findElement(By.name("onAdvancedButton")).click();
			int y = 3;
			
			for(int j=0; j<10; j++)
			{
				int x = 0;
				String seq = "myPersonalNumber_VO.PELPP.LISTs.LIST[0].CHOs.CHO["+j+"].ANSPOS";
				String stime = "myPersonalNumber_VO.PELPP.LISTs.LIST[0].CHOs.CHO["+j+"].STIME";
				driver.findElement(By.id(seq)).sendKeys(extData[y]);
				driver.findElement(By.id(stime)).clear();
				driver.findElement(By.id(stime)).sendKeys("7");
				y++;
			}
			driver.findElement(By.xpath("(//input[@name='Apply'])[1]")).click();
		}
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("blockUI")));
//		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("blockUI")));
		////////////////////////////////////////////////////////////////////////////////////////
	}
	
	public void verifyPN_In_Ext_View_Page(WebDriver driver, String activeListNumber)
	{
		List<WebElement> v1 = driver.findElements(By.xpath("//td[contains(text(),'Profile1')]"));
		List<WebElement> v2 = driver.findElements(By.xpath("//td[contains(text(),'Profile2')]"));
		List<WebElement> v3 = driver.findElements(By.xpath("//td[contains(text(),'Profile3')]"));
		List<WebElement> v4 = driver.findElements(By.xpath("//td[contains(text(),'Profile4')]"));
		List<WebElement> v5 = driver.findElements(By.xpath("//td[contains(text(),'Profile5')]"));
		List<WebElement> v6 = driver.findElements(By.xpath("//td[contains(text(),'Profile6')]"));
		List<WebElement> v7 = driver.findElements(By.xpath("//td[contains(text(),'Profile7')]"));
		List<WebElement> v8 = driver.findElements(By.xpath("//td[contains(text(),'Profile8')]"));
		List<WebElement> v9 = driver.findElements(By.xpath("//td[contains(text(),'Profile9')]"));
		List<WebElement> v10 = driver.findElements(By.xpath("//td[contains(text(),'Profile10')]"));
		
		Assert.assertTrue(v1.size()==2 && v2.size()==1 && v3.size()==1 && v4.size()==1 && v5.size()==1 && v6.size()==1
				& v7.size()==1 && v8.size()==1 && v9.size()==1 && v10.size()==1);
		
		List<WebElement> active = driver.findElements(By.xpath("(//td[starts-with(.,'Status')])["+activeListNumber+"]//following-sibling::td[starts-with(text(),'Active')]"));
		Assert.assertTrue(active.size()==1);

	}
	
	
	//Helper method for Personal Number List:
		public void create_personal_number_for_given_extension(WebDriver driver, String methodName, ExcelReadAndWrite ipData,
				ExcelReadAndWrite loginData,ExcelReadAndWrite pmTests, String extType) throws InterruptedException
		{
			String[] credentials = loginData.getData("test_pm_valid_login", 1);
			String[] testData = pmTests.getData(methodName, 1);
			
			driver.get(ipData.getData(0, 0));
			loginPage.PM_Login(credentials[0], credentials[1]);
			pmMainPge.getServices().click();
			pmServices.getExtension().click();
			pmExtension.getAddButton().click();
			
			if(extType.equals("IP"))
			{
				new SelectDropDownValue().selectByVisibleText(pmExtension.getExtensionType(), "IP");
				pmExtension.getNextButton().click();
				
				new SelectDropDownValue().selectByVisibleText(pmExtension.getSelectExtensionsRange(), testData[1]);
				String version = new GetMxoneVersionNumber(driver).getMxoneVersionNumber(driver);
				System.out.println(version);
				int ver = Integer.parseInt(version);
				System.out.println(ver);
				if(ver >= 72000)
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
				new SelectDropDownValue().selectByIndex(pmExtension.getCommonCategoryDropDown(), 0);
				pmExtension.setEquipmentPosition(testData[2]);
			}
			else if(extType.equals("digital"))
			{
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
				new SelectDropDownValue().selectByIndex(pmExtension.getDigitalPhoneTypeDropDown(), 1);
				new SelectDropDownValue().selectByIndex(pmExtension.getDigitalCommonCategoryDropDown(), 0);
				pmExtension.setEquipmentPosition(testData[2]);
			}
			else if(extType.equals("virtual"))
			{
				new SelectDropDownValue().selectByVisibleText(pmExtension.getExtensionType(), "Virtual");
				pmExtension.getNextButton().click();
				new SelectDropDownValue().selectByVisibleText(pmExtension.getSelectExtensionsRange(), testData[1]);
				String version = new GetMxoneVersionNumber(driver).getMxoneVersionNumber(driver);
				System.out.println(version);
				int ver = Integer.parseInt(version);
				System.out.println(ver);
				if(ver >= 72000)
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
				new SelectDropDownValue().selectByVisibleText(pmExtension.getExtensionType(), "SIP DECT");
				pmExtension.getNextButton().click();
				new SelectDropDownValue().selectByVisibleText(pmExtension.getSelectExtensionsRange(), testData[1]);
				String version = new GetMxoneVersionNumber(driver).getMxoneVersionNumber(driver);
				System.out.println(version);
				int ver = Integer.parseInt(version);
				System.out.println(ver);
				if(ver >= 72000)
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
}
