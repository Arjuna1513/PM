package utilities;

import java.util.List;

import org.apache.commons.math3.analysis.function.Exp;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

//import junit.framework.Assert;
import pm_pom_classes.Extension;
import pm_pom_classes.PM_Login_Page;
import pm_pom_classes.PM_Main_Page;
import pm_pom_classes.PM_Services;
import pm_pom_classes.PM_System;
import pm_pom_classes.PM_System_DataManagement;
import pm_pom_classes.PM_User;
import pm_pom_classes.PM_Users;
import snm_pom_classes.SNM_External_Length_Page;
import snm_pom_classes.SNM_Login_Page;
import snm_pom_classes.SNM_Main_Page;
import snm_pom_classes.SNM_NumAna__Num_Plan_SystemNumbers_Page;
import snm_pom_classes.SNM_NumberPlan;
import snm_pom_classes.SNM_NumberSeries;
import snm_pom_classes.SNM_Number_Analysis_Page;
import snm_pom_classes.SNM_TelephonyExtensionsPage;
import snm_pom_classes.SNM_TelephonyPage;
import snm_pom_classes.SNM_Telephony_AccountCode_Page;
import snm_pom_classes.SNM_Telephony_Extensions_CSP_Page;
import snm_pom_classes.SNM_Telephony_Groups_Customer_Page;
import snm_pom_classes.SNM_Telephony_Groups_Page;

public class CleanUP 
{
	public PM_Login_Page pmLoginPage;
	public PM_Main_Page pmMainPage;
	public PM_User pmUser;
	public PM_Users pmUsers;
	public PM_Services pmServices;
	public Extension pmExtension;
	public PM_System pmSystem;
	public PM_System_DataManagement dataMgmt;
	public SNM_Login_Page snmLogin;
	public SNM_Main_Page snmMainPage;
	public SNM_Number_Analysis_Page numAnalysis;
	public SNM_NumberPlan numPlan;
	public SNM_NumberSeries numSeries;
	public SNM_External_Length_Page extNumLength;
	public SNM_Telephony_AccountCode_Page accCode;
	public SNM_TelephonyExtensionsPage telExt;
	public SNM_TelephonyPage snmTelephony;
	public SNM_Telephony_Groups_Page snmTelGrp;
	public SNM_Telephony_Groups_Customer_Page snmTelCustGrps;
	public SNM_Telephony_Extensions_CSP_Page snmCSP;
	public SNM_NumAna__Num_Plan_SystemNumbers_Page sysNumbers;
	
	public CleanUP(WebDriver driver)
	{
		pmLoginPage = new PM_Login_Page(driver);
		pmMainPage = new PM_Main_Page(driver);
		pmUser = new PM_User(driver);
		pmUsers = new PM_Users(driver);
		pmServices = new PM_Services(driver);
		pmExtension = new Extension(driver);
		pmSystem = new PM_System(driver);
		dataMgmt = new PM_System_DataManagement(driver);
		snmLogin = new SNM_Login_Page(driver);
		snmMainPage = new SNM_Main_Page(driver);
		numAnalysis = new SNM_Number_Analysis_Page(driver);
		numPlan = new SNM_NumberPlan(driver);
		numSeries = new SNM_NumberSeries(driver);
		extNumLength = new SNM_External_Length_Page(driver);
		snmTelephony = new SNM_TelephonyPage(driver);
		accCode = new SNM_Telephony_AccountCode_Page(driver);
		telExt = new SNM_TelephonyExtensionsPage(driver);
		snmTelGrp = new SNM_Telephony_Groups_Page(driver);
		snmTelCustGrps = new SNM_Telephony_Groups_Customer_Page(driver);
		snmCSP = new SNM_Telephony_Extensions_CSP_Page(driver);
		sysNumbers = new SNM_NumAna__Num_Plan_SystemNumbers_Page(driver);
	}
	
	public void deleteUser(WebDriver driver, ExcelReadAndWrite ipData, String[] data, String uerName) throws InterruptedException
	{
		pmMainPage = new PM_Main_Page(driver);
		pmLoginPage = new PM_Login_Page(driver);
		pmUser = new PM_User(driver);
		pmUsers = new PM_Users(driver);
		driver.get(ipData.getData(0, 0));
		pmLoginPage.PM_Login(data[0], data[1]);
		pmMainPage.getUsers().click();
		pmUsers.getUser().click();
		pmUser.setUserSearchTextBox(uerName);
		pmUser.getOnViewRangeButton().click();
		List<WebElement> eles = driver.findElements(By.xpath("(//td[contains(text(),'"+uerName+"')]//preceding-sibling::td)[8]"));
		if(eles.size() > 0)
		{
			driver.findElement(By.xpath("(//td[contains(text(),'"+uerName+"')]//preceding-sibling::td)[8]")).click();
			driver.switchTo().alert().accept();
			
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(@class,'responseMessage')]")));
			Assert.assertEquals(driver.findElement(By.xpath("//span[contains(@class,'responseMessage')]")).getText().trim(), "Remove operation successful for:");
		}
		pmUser.getUserSearchTextBox().clear();
		pmUser.setUserSearchTextBox(uerName);
		pmUser.getOnViewRangeButton().click();
		
		List<WebElement> count = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("(//td[contains(text(),'"+uerName+"')])[1]"));
//		List<WebElement> eles1 = driver.findElements(By.xpath("(//td[contains(text(),'"+uerName+"')])[1]"));
		Assert.assertTrue(count.size() == 0);
		pmMainPage.getLogoutLink().click();
	}
	
	
	public void delete_import_summary_record(WebDriver driver, ExcelReadAndWrite ipData, ExcelReadAndWrite loginData) throws InterruptedException
	{
		List<WebElement> list;
		String[] credentials = loginData.getData("test_pm_valid_login", 1);
		driver.get(ipData.getData(0, 0));
		pmLoginPage.PM_Login(credentials[0], credentials[1]);
		pmMainPage.getSystem().click();
		pmSystem.getDataManagement().click();
		dataMgmt.getImportTabButton().click();
//		list = driver.findElements(By.xpath("//img[contains(@id,'removeThis')]"));
		list = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//img[contains(@id,'removeThis')]"));
		if(list.size() > 0)
		{
			for(WebElement element : list)
			{
				element.click();
				driver.switchTo().alert().accept();
//				Assert.assertEquals(dataMgmt.getResponseMsg().getText().trim(), "Remove operation successful for:");
			}
			pmMainPage.getLogoutLink().click();
		}
	
	}
	
	
	public void delete_existing_backUps(WebDriver driver, ExcelReadAndWrite ipData, ExcelReadAndWrite loginData) throws InterruptedException
	{
		List<WebElement> list;
		String[] credentials = loginData.getData("test_pm_valid_login", 1);
		driver.get(ipData.getData(0, 0));
		pmLoginPage.PM_Login(credentials[0], credentials[1]);
		pmMainPage.getSystem().click();
		pmSystem.getDataManagement().click();
		dataMgmt.getBackUpRestoreTab().click();
//		dataMgmt.getBackUpButton().click();
//		list = driver.findElements(By.xpath("//img[contains(@id,'removeThis')]"));
		list = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//img[contains(@id,'removeThis')]"));
		if(list.size() > 0)
		{
			for(WebElement element : list)
			{
				element.click();
				driver.switchTo().alert().accept();
				Assert.assertEquals(dataMgmt.getResponseMsg().getText().trim(), "Remove operation successful for:");
			}
		}
		pmMainPage.getLogoutLink().click();
	}
	
	
	public void deleteUser(WebDriver driver, ExcelReadAndWrite ipData, String[] data, String uerName, int iterations) throws InterruptedException
	{
		pmMainPage = new PM_Main_Page(driver);
		pmLoginPage = new PM_Login_Page(driver);
		pmUser = new PM_User(driver);
		pmUsers = new PM_Users(driver);
		driver.get(ipData.getData(0, 0));
		driver.get(ipData.getData(0, 0));
		pmLoginPage.PM_Login(data[0], data[1]);
		pmMainPage.getUsers().click();
		pmUsers.getUser().click();
		pmUser.setUserSearchTextBox(uerName);
		pmUser.getOnViewRangeButton().click();
		List<WebElement> eles = driver.findElements(By.xpath("//input[@name='selectItem']"));
		if(eles.size() > 0)
		{
			for(int i=1; i<=iterations; i++)
			{
				driver.findElement(By.xpath("(//input[@name='selectItem'])["+i+"]")).click();
			}
			pmUser.getRemoveSelected().click();
			driver.switchTo().alert().accept();
			Assert.assertEquals(pmUser.getResponseMessage().getText().trim(), "Remove operation successful for:");
			pmUser.getLogoutLink().click();
		}
	}
	
	public void delete_Users_With_10Users_for_Each_click(WebDriver driver, ExcelReadAndWrite ipData, String[] data, String uerName, int count) throws InterruptedException
	{
		pmMainPage = new PM_Main_Page(driver);
		pmLoginPage = new PM_Login_Page(driver);
		pmUser = new PM_User(driver);
		pmUsers = new PM_Users(driver);
		driver.get(ipData.getData(0, 0));
		driver.get(ipData.getData(0, 0));
		pmLoginPage.PM_Login(data[0], data[1]);
		pmMainPage.getUsers().click();
		pmUsers.getUser().click();
		new SelectDropDownValue().selectByVisibleText(pmUser.getViewExtensionsLimit(), "10");
		pmUser.setUserSearchTextBox(uerName);
		pmUser.getOnViewRangeButton().click();
		WebDriverWait wait = new WebDriverWait(driver, 100);
		/*List<WebElement> eleList;
		eleList = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//input[@name='selectItem']"));
		for(int i=1; i<=count/10; i++)
		{
			List<WebElement> eles;
			eles = driver.findElements(By.name("selectItem"));
			if(eles.size() > 0)
			{
					driver.findElement(By.name("selectItemAll")).click();
					pmUser.getRemoveSelected().click();
					driver.switchTo().alert().accept();
					Assert.assertEquals(pmUser.getResponseMessage().getText().trim(), "Remove operation successful for:");
				
			}
		}*/
		
		
		while(new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//input[@name='selectItem']")).size()>0)
		{
			List<WebElement> eles;
			eles = driver.findElements(By.name("selectItem"));
			if(eles.size() > 0)
			{
				driver.findElement(By.name("selectItemAll")).click();
				pmUser.getRemoveSelected().click();
				driver.switchTo().alert().accept();
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("blockUI")));
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("blockUI")));
				wait.until(ExpectedConditions.presenceOfElementLocated(By.className("responseMessage")));
//					Thread.sleep(1000);
				
			}
		}
		
		
			pmUser.getOnViewRangeButton().click();
			String message = driver.findElement(By.xpath("//b[text()=' No data found']")).getText().trim();
			Assert.assertEquals(message, "No data found");
//			pmUser.getRemoveSelected().click();
//			driver.switchTo().alert().accept();
//			Assert.assertEquals(pmUser.getResponseMessage().getText().trim(), "Remove operation successful for:");
			pmUser.getLogoutLink().click();
		
	}
	
	
	public void deleteMultipleExtensions(WebDriver driver, ExcelReadAndWrite ipData, String[] data, String extNumber, int iterations) throws InterruptedException
	{
		pmMainPage = new PM_Main_Page(driver);
		pmLoginPage = new PM_Login_Page(driver);
		pmUser = new PM_User(driver);
		pmUsers = new PM_Users(driver);
		driver.get(ipData.getData(0, 0));
		pmLoginPage.PM_Login(data[0], data[1]);
		pmMainPage.getUsers().click();
		pmUsers.getUser().click();
		pmUser.setUserSearchTextBox(extNumber);
		pmUser.getOnViewRangeButton().click();
		List<WebElement> eles = driver.findElements(By.xpath("//input[@name='selectItem']"));
		if(eles.size() > 0)
		{
			for(int i=1; i<=iterations; i++)
			{
				driver.findElement(By.xpath("(//input[@name='selectItem'])["+i+"]")).click();
			}
			pmUser.getRemoveSelected().click();
			driver.switchTo().alert().accept();
			Assert.assertEquals(pmUser.getResponseMessage().getText().trim(), "Remove operation successful for:");
			pmUser.getLogoutLink().click();
		}
	}
	
	public void deleteTemplate(WebDriver driver, String methodName, ExcelReadAndWrite loginData, 
			String templateName, ExcelReadAndWrite ipData) throws InterruptedException
	{
		WebDriverWait wait = new WebDriverWait(driver, 10);
		driver.get(ipData.getData(0, 0));
		String[] credentials = loginData.getData("test_pm_valid_login", 1);
//		String[] testData = pmTests.getData(methodName, 3);
		pmLoginPage.PM_Login(credentials[0], credentials[1]);
		pmMainPage.getServices().click();
		pmServices.getExtension().click();
		pmExtension.getManageTemplates().click();
//		Thread.sleep(1000);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//td[contains(text(),'"+templateName+"')]")));
		driver.findElement(By.xpath("//td[contains(text(),'"+templateName+"')]//preceding-sibling::td[18]")).click();
		driver.switchTo().alert().accept();
		Assert.assertEquals("Remove Template operation successful for:", pmExtension.getResponseMessage());
		pmExtension.getTemplateSubmitButton().click();
		pmMainPage.getLogoutLink().click();
	}
	
	
	public void delete_externalNumberLength(WebDriver driver,ExcelReadAndWrite ipData, ExcelReadAndWrite loginData,
			String extNumValue ,Logger log)
	{
		List<WebElement> list = null;
		WebDriverWait wait = new WebDriverWait(driver, 15);
		String[] snmCredentials = null;
		String[] testData = null;
		
			snmCredentials = loginData.getData("test_snm_valid_login", 1);
//			testData = snmData.getData(methodName, 1);
			driver.get(ipData.getData(1, 0));
			snmLogin.snm_login(snmCredentials[0], snmCredentials[1]);
			
			log.info("After successful login");
			
			snmMainPage.getNumber_Analysis().click();
			numAnalysis.getNumber_Plan_Link().click();
			numPlan.getExternalNumberLength().click();
			
			list = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath
					("//td[contains(text(),'"+extNumValue+"')]"));
			if(list.size() == 1)
			{
				log.debug("Record is present");
				driver.findElement(By.xpath("//td[contains(text(),'"+extNumValue+"')]//preceding-sibling::td[18]")).click();
				wait.until(ExpectedConditions.textToBePresentInElement(extNumLength.getResponseMsg(), 
						"Remove operation successful for:"));
				
				list.clear();
				list = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath
						("//td[contains(text(),'"+extNumValue+"')]"));
				
				Assert.assertTrue(list.size() == 0);
			}
	}
	
	
	public void snmTelephonyAccountCode(WebDriver driver,ExcelReadAndWrite ipData, ExcelReadAndWrite loginData,
			String extNumValue ,Logger log)
	{
		List<WebElement> list = null;
		WebDriverWait wait = new WebDriverWait(driver, 15);
		String[] snmCredentials = null;
		String[] testData = null;
		
			snmCredentials = loginData.getData("test_snm_valid_login", 1);
//			testData = snmData.getData(methodName, 1);
			driver.get(ipData.getData(1, 0));
			snmLogin.snm_login(snmCredentials[0], snmCredentials[1]);
			
			log.info("After successful login");
			
			snmMainPage.getTelephony().click();
			snmTelephony.getExtensions().click();
			telExt.getAccountCode().click();
			
			list = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath
					("//td[contains(text(),'"+extNumValue+"')]"));
			if(list.size() == 1)
			{
				log.debug("Record is present");
				driver.findElement(By.xpath("//td[contains(text(),'"+extNumValue+"')]//preceding-sibling::td[18]")).click();
				wait.until(ExpectedConditions.textToBePresentInElement(extNumLength.getResponseMsg(), 
						"Remove operation successful for:"));
				
				list.clear();
				list = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath
						("//td[contains(text(),'"+extNumValue+"')]"));
				
				Assert.assertTrue(list.size() == 0);
			}
	}
	
	
	public void deleteCustomer(WebDriver driver,ExcelReadAndWrite ipData, ExcelReadAndWrite loginData,
			String extNumValue ,Logger log)
	{
		List<WebElement> list = null;
		WebDriverWait wait = new WebDriverWait(driver, 15);
		String[] snmCredentials = null;
		String[] testData = null;
		
			snmCredentials = loginData.getData("test_snm_valid_login", 1);
//			testData = snmData.getData(methodName, 1);
			driver.get(ipData.getData(1, 0));
			snmLogin.snm_login(snmCredentials[0], snmCredentials[1]);
			
			log.info("After successful login");
			
			snmMainPage.getTelephony().click();
			snmTelephony.getGroups().click();
			snmTelGrp.getCustomerLink().click();
			
			list = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath
					("//td[contains(text(),'"+extNumValue+"')]"));
			if(list.size() == 1)
			{
				log.debug("Record is present");
				driver.findElement(By.xpath("//td[contains(text(),'"+extNumValue+"')]//preceding-sibling::td[20]")).click();
				wait.until(ExpectedConditions.textToBePresentInElement(accCode.getResponseMsg(), 
						"Remove operation successful for:"));
				
				list.clear();
				list = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath
						("//td[contains(text(),'"+extNumValue+"')]"));
				
				Assert.assertTrue(list.size() == 0);
			}
			snmMainPage.getLogoutButton().click();
	}
	
	
	public void deleteCSP(WebDriver driver,ExcelReadAndWrite ipData, ExcelReadAndWrite loginData,
			String extNumValue ,Logger log)
	{
		List<WebElement> list = null;
		WebDriverWait wait = new WebDriverWait(driver, 15);
		String[] snmCredentials = null;
		String[] testData = null;
		
			snmCredentials = loginData.getData("test_snm_valid_login", 1);
//			testData = snmData.getData(methodName, 1);
			driver.get(ipData.getData(1, 0));
			snmLogin.snm_login(snmCredentials[0], snmCredentials[1]);
			
			log.info("After successful login");
			
			snmMainPage.getTelephony().click();
			snmTelephony.getExtensions().click();
			telExt.getCSP().click();
			
			list = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath
					("//td[contains(text(),'"+extNumValue+"')]"));
			if(list.size() == 1)
			{
				log.debug("Record is present");
				driver.findElement(By.xpath("//td[contains(text(),'"+extNumValue+"')]//preceding-sibling::td[20]")).click();
				wait.until(ExpectedConditions.textToBePresentInElement(extNumLength.getResponseMsg(), 
						"Remove operation successful for:"));
				
				list.clear();
				list = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath
						("//td[contains(text(),'"+extNumValue+"')]"));
				
				Assert.assertTrue(list.size() == 0);
			}
	}
	
	
	public void deleteCSPWithCustomer(WebDriver driver,ExcelReadAndWrite ipData, ExcelReadAndWrite loginData,
			String tempName ,String custName,Logger log)
	{
		List<WebElement> list = null;
		WebDriverWait wait = new WebDriverWait(driver, 15);
		String[] snmCredentials = null;
		String[] testData = null;
		
			snmCredentials = loginData.getData("test_snm_valid_login", 1);
//			testData = snmData.getData(methodName, 1);
			driver.get(ipData.getData(1, 0));
			snmLogin.snm_login(snmCredentials[0], snmCredentials[1]);
			
			log.info("After successful login");
			
			snmMainPage.getTelephony().click();
			snmTelephony.getExtensions().click();
			telExt.getCSP().click();
			
			list = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath
					("//td[contains(text(),'0')]//following-sibling::td[contains(text(),'"+tempName+"')]"
							+ "//following-sibling::td[contains(text(),'"+custName+"')]"));
			if(list.size() == 1)
			{
				log.debug("Record is present");
				driver.findElement(By.xpath("//td[contains(text(),'0')]"
						+ "//following-sibling::td[contains(text(),'"+tempName+"')]"
						+ "//following-sibling::td[contains(text(),'"+custName+"')]//preceding-sibling::td[22]")).click();
				wait.until(ExpectedConditions.textToBePresentInElement(extNumLength.getResponseMsg(), 
						"Remove operation successful for:"));
				
				list.clear();
				list = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath
						("//td[contains(text(),'"+custName+"')]"));
				
				Assert.assertTrue(list.size() == 0);
			}
	}
	
	public void deleteCSPTemplate(WebDriver driver, String methodName, ExcelReadAndWrite loginData, 
			String templateName, ExcelReadAndWrite ipData) throws InterruptedException
	{
		WebDriverWait wait = new WebDriverWait(driver, 10);
		driver.get(ipData.getData(1, 0));
		String[] credentials = loginData.getData("test_snm_valid_login", 1);
//		String[] testData = pmTests.getData(methodName, 3);
		snmLogin.snm_login(credentials[0], credentials[1]);
		snmMainPage.getTelephony().click();
		snmTelephony.getExtensions().click();
		telExt.getCSP().click();
		Thread.sleep(2000);
		snmCSP.getManageTemplatesLink().click();
		Thread.sleep(2000);
		List<WebElement> list = new ExplicitWait().numberOfElementsPresent(driver, 3, 
				By.xpath("//td[contains(text(),'"+templateName+"')]"));
		if(list.size()==1)
		{
			driver.findElement(By.xpath("//td[contains(text(),'"+templateName+"')]//preceding-sibling::td[18]")).click();
			driver.switchTo().alert().accept();
			wait.until(ExpectedConditions.textToBePresentInElement(extNumLength.getResponseMsg(), 
					"Remove Template operation successful for:"));
			List<WebElement> list1 = new ExplicitWait().numberOfElementsPresent(driver, 3, 
					By.xpath("//td[contains(text(),'"+templateName+"')]"));
			
			Assert.assertTrue(list1.size()==0);
		}
//		Thread.sleep(5000);
		snmCSP.getManageTemplatesContinueButton().click();
//		Thread.sleep(5000);
		pmMainPage.getLogoutLink().click();
	}
	
	public void removeSystemNumbers(WebDriver driver, String methodName, ExcelReadAndWrite loginData, 
			ExcelReadAndWrite ipData) throws InterruptedException
	{
		WebDriverWait wait = new WebDriverWait(driver, 10);
		driver.get(ipData.getData(1, 0));
		String[] credentials = loginData.getData("test_snm_valid_login", 1);
//		String[] testData = pmTests.getData(methodName, 3);
		snmLogin.snm_login(credentials[0], credentials[1]);
		snmMainPage.getNumber_Analysis().click();
		numAnalysis.getNumber_Plan_Link().click();
		numPlan.getSystemNumbers().click();
		sysNumbers.getInternationalPrefix().clear();
		sysNumbers.getCountryCode().clear();
		sysNumbers.getNationalPrefix().clear();
		sysNumbers.getApplyButton().click();
		wait.until(ExpectedConditions.textToBePresentInElement(sysNumbers.getResponseMessage(), sysNumbers.getMessage()));
		sysNumbers.getDoneButton().click();
		pmMainPage.getLogoutLink().click();
	}
}
