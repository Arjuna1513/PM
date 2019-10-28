package tests;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import base.ConfigClass;
import snm_pom_classes.SNM_External_Length_Page;
import snm_pom_classes.SNM_Login_Page;
import snm_pom_classes.SNM_Main_Page;
import snm_pom_classes.SNM_NumberPlan;
import snm_pom_classes.SNM_NumberSeries;
import snm_pom_classes.SNM_Number_Analysis_Page;
import snm_pom_classes.SNM_TelephonyExtensionsPage;
import snm_pom_classes.SNM_TelephonyPage;
import snm_pom_classes.SNM_Telephony_AccountCode_Page;
import utilities.CleanUP;
import utilities.ExplicitWait;
import utilities.LoggerClass;
import utilities.ReusableUnits;
import utilities.SelectDropDownValue;
import utilities.Take_Screenshot;

public class SNM_Telephony_AccountCode_Test extends ConfigClass 
{
	public SNM_Login_Page snmLogin;
	public SNM_Main_Page snmMainPage;
	Logger log = LoggerClass.getLogger("SNM_Telephony_AccountCode_Test");
	public SNM_TelephonyPage snmTelephony;
	WebDriverWait wait = null;
	public SNM_Telephony_AccountCode_Page accCode;
	public SNM_TelephonyExtensionsPage telExt;
	
/*	@Test
	public void test_create_account_code(Method method) throws Exception
	{
		List<WebElement> list = new ArrayList<WebElement>();
		snmLogin = new SNM_Login_Page(driver);
		snmMainPage = new SNM_Main_Page(driver);
		snmTelephony = new SNM_TelephonyPage(driver);
		wait = new WebDriverWait(driver, 10);
		accCode = new SNM_Telephony_AccountCode_Page(driver);
		telExt = new SNM_TelephonyExtensionsPage(driver);
		
		String[] snmCredentials = loginData.getData("test_snm_valid_login", 1);;
		String[] testData = snmTests.getData(method.getName(), 1);
		
		try
		{
			snmTests.checkTestStatus(method.getName());
			LoggerClass.markTestBeginning(method.getName(), "SNM_ExternalNumberLength_Test");
			driver.get(ipData.getData(1, 0));
			snmLogin.snm_login(snmCredentials[0], snmCredentials[1]);
			
			log.info("After successful login");
			
			snmMainPage.getTelephony().click();
			snmTelephony.getExtensions().click();
			telExt.getAccountCode().click();
			accCode.getAddButton().click();
			accCode.setAccountCodeField(testData[0]);
//			accCode.setAccountCodeFirstField(testData[1]);
//			accCode.setAccountCodeLastField(testData[2]);
			accCode.getApplyButton().click();
			wait.until(ExpectedConditions.textToBePresentInElement(accCode.getResponseMsg(),
					"Add operation successful for:"));
			log.info("After successful accCode creation");
			accCode.getDoneButton().click();
			accCode.getRange().clear();
			accCode.setRange(testData[0]);
			accCode.getViewButton().click();
			list.clear();
			list = new ExplicitWait().numberOfElementsPresent(driver, 3, 
					By.xpath("//td[contains(text(),'"+testData[0]+"')]"));
			Assert.assertTrue(list.size()==1);
		}
		catch(Error e)
		{
			new Take_Screenshot().get_Screenshot(driver, method.getName());
			log.fatal(e.toString());
			throw e;
		}
		catch(Exception e)
		{
			new Take_Screenshot().get_Screenshot(driver, method.getName());
			log.info(e.toString());
			throw e;
		}
		finally
		{
			new CleanUP(driver).snmTelephonyAccountCode(driver, ipData, loginData,testData[0], log);
		}
	}
*/	
	
	@Test
	public void test_delete_accountCode_using_delete_button(Method method) throws Exception
	{
		List<WebElement> list = new ArrayList<WebElement>();
		snmLogin = new SNM_Login_Page(driver);
		snmMainPage = new SNM_Main_Page(driver);
		snmTelephony = new SNM_TelephonyPage(driver);
		wait = new WebDriverWait(driver, 10);
		accCode = new SNM_Telephony_AccountCode_Page(driver);
		telExt = new SNM_TelephonyExtensionsPage(driver);
		
		String[] snmCredentials = loginData.getData("test_snm_valid_login", 1);;
		String[] testData = snmTests.getData(method.getName(), 1);
		
		try
		{
			snmTests.checkTestStatus(method.getName());
			LoggerClass.markTestBeginning(method.getName(), "SNM_ExternalNumberLength_Test");
			driver.get(ipData.getData(1, 0));
			snmLogin.snm_login(snmCredentials[0], snmCredentials[1]);
			
			log.info("After successful login");
			
			snmMainPage.getTelephony().click();
			snmTelephony.getExtensions().click();
			telExt.getAccountCode().click();
			accCode.getAddButton().click();
			accCode.setAccountCodeField(testData[0]);
//			accCode.setAccountCodeFirstField(testData[1]);
//			accCode.setAccountCodeLastField(testData[2]);
			accCode.getApplyButton().click();
			wait.until(ExpectedConditions.textToBePresentInElement(accCode.getResponseMsg(),
					"Add operation successful for:"));
			log.info("After successful accCode creation");
			accCode.getDoneButton().click();
			accCode.getRange().clear();
			accCode.setRange(testData[0]);
			accCode.getViewButton().click();
			list.clear();
			list = new ExplicitWait().numberOfElementsPresent(driver, 3, 
					By.xpath("//td[contains(text(),'"+testData[0]+"')]"));
			Assert.assertTrue(list.size()==1);
			
			driver.findElement(By.xpath("//td[contains(text(),'"+testData[0]+"')]//preceding-sibling::td[25]")).click();
			accCode.getDeleteButton().click();
			driver.switchTo().alert().accept();
			wait.until(ExpectedConditions.textToBePresentInElement(accCode.getResponseMsg(), 
					"Remove operation successful for:"));
		}
		catch(Error e)
		{
			new Take_Screenshot().get_Screenshot(driver, method.getName());
			log.fatal(e.toString());
			throw e;
		}
		catch(Exception e)
		{
			new Take_Screenshot().get_Screenshot(driver, method.getName());
			log.info(e.toString());
			throw e;
		}
		finally
		{
			new CleanUP(driver).snmTelephonyAccountCode(driver, ipData, loginData,testData[0], log);
		}
	}
	
/*	
	@Test
	public void test_create_account_code_with_firstANDLastCodes(Method method) throws Exception
	{
		List<WebElement> list = new ArrayList<WebElement>();
		snmLogin = new SNM_Login_Page(driver);
		snmMainPage = new SNM_Main_Page(driver);
		snmTelephony = new SNM_TelephonyPage(driver);
		wait = new WebDriverWait(driver, 10);
		accCode = new SNM_Telephony_AccountCode_Page(driver);
		telExt = new SNM_TelephonyExtensionsPage(driver);
		
		String[] snmCredentials = loginData.getData("test_snm_valid_login", 1);;
		String[] testData = snmTests.getData(method.getName(), 1);
		
		try
		{
			snmTests.checkTestStatus(method.getName());
			LoggerClass.markTestBeginning(method.getName(), "SNM_ExternalNumberLength_Test");
			driver.get(ipData.getData(1, 0));
			snmLogin.snm_login(snmCredentials[0], snmCredentials[1]);
			
			log.info("After successful login");
			
			snmMainPage.getTelephony().click();
			snmTelephony.getExtensions().click();
			telExt.getAccountCode().click();
			accCode.getAddButton().click();
//			accCode.setAccountCodeField(testData[0]);
			accCode.setAccountCodeFirstField(testData[0]);
			accCode.setAccountCodeLastField(testData[1]);
			accCode.getApplyButton().click();
			wait.until(ExpectedConditions.textToBePresentInElement(accCode.getResponseMsg(),
					"Add operation successful for:"));
			log.info("After successful accCode creation");
			accCode.getDoneButton().click();
			accCode.getRange().clear();
			accCode.setRange(testData[2]);
			accCode.getViewButton().click();
			list.clear();
			list = new ExplicitWait().numberOfElementsPresent(driver, 3, 
					By.xpath("//td[contains(text(),'"+testData[2]+"')]"));
			Assert.assertTrue(list.size()==1);
		}
		catch(Error e)
		{
			new Take_Screenshot().get_Screenshot(driver, method.getName());
			log.fatal(e.toString());
			throw e;
		}
		catch(Exception e)
		{
			new Take_Screenshot().get_Screenshot(driver, method.getName());
			log.info(e.toString());
			throw e;
		}
		finally
		{
			new CleanUP(driver).snmTelephonyAccountCode(driver, ipData, loginData,testData[2], log);
		}
	}
	
	
	@Test
	public void test_create_accountCode_moreThan_15_Digits(Method method) throws Exception
	{
		List<WebElement> list = new ArrayList<WebElement>();
		snmLogin = new SNM_Login_Page(driver);
		snmMainPage = new SNM_Main_Page(driver);
		snmTelephony = new SNM_TelephonyPage(driver);
		wait = new WebDriverWait(driver, 10);
		accCode = new SNM_Telephony_AccountCode_Page(driver);
		telExt = new SNM_TelephonyExtensionsPage(driver);
		
		String[] snmCredentials = loginData.getData("test_snm_valid_login", 1);;
		String[] testData = snmTests.getData(method.getName(), 1);
		
		try
		{
			snmTests.checkTestStatus(method.getName());
			LoggerClass.markTestBeginning(method.getName(), "SNM_ExternalNumberLength_Test");
			driver.get(ipData.getData(1, 0));
			snmLogin.snm_login(snmCredentials[0], snmCredentials[1]);
			
			log.info("After successful login");
			
			snmMainPage.getTelephony().click();
			snmTelephony.getExtensions().click();
			telExt.getAccountCode().click();
			accCode.getAddButton().click();
			accCode.setAccountCodeField(testData[0]);
//			accCode.setAccountCodeFirstField(testData[0]);
//			accCode.setAccountCodeLastField(testData[1]);
			accCode.getApplyButton().click();
			Assert.assertEquals(driver.switchTo().alert().getText().trim(),
					"Account Code(s) is invalid.");
			driver.switchTo().alert().accept();
			accCode.getCancelButton().click();
		}
		catch(Error e)
		{
			new Take_Screenshot().get_Screenshot(driver, method.getName());
			log.fatal(e.toString());
			throw e;
		}
		catch(Exception e)
		{
			new Take_Screenshot().get_Screenshot(driver, method.getName());
			log.info(e.toString());
			throw e;
		}
		finally
		{
//			new CleanUP(driver).snmTelephonyAccountCode(driver, ipData, loginData,testData[2], log);
		}
	}
	
	
	@Test
	public void test_create_accountCodeFirst_moreThan_15_Digits(Method method) throws Exception
	{
		List<WebElement> list = new ArrayList<WebElement>();
		snmLogin = new SNM_Login_Page(driver);
		snmMainPage = new SNM_Main_Page(driver);
		snmTelephony = new SNM_TelephonyPage(driver);
		wait = new WebDriverWait(driver, 10);
		accCode = new SNM_Telephony_AccountCode_Page(driver);
		telExt = new SNM_TelephonyExtensionsPage(driver);
		
		String[] snmCredentials = loginData.getData("test_snm_valid_login", 1);;
		String[] testData = snmTests.getData(method.getName(), 1);
		
		try
		{
			snmTests.checkTestStatus(method.getName());
			LoggerClass.markTestBeginning(method.getName(), "SNM_ExternalNumberLength_Test");
			driver.get(ipData.getData(1, 0));
			snmLogin.snm_login(snmCredentials[0], snmCredentials[1]);
			
			log.info("After successful login");
			
			snmMainPage.getTelephony().click();
			snmTelephony.getExtensions().click();
			telExt.getAccountCode().click();
			accCode.getAddButton().click();
//			accCode.setAccountCodeField(testData[0]);
			accCode.setAccountCodeFirstField(testData[0]);
			accCode.setAccountCodeLastField(testData[1]);
			accCode.getApplyButton().click();
			Thread.sleep(1000);
			Assert.assertEquals(driver.switchTo().alert().getText().trim(),
					"Account Code First is invalid.");
			driver.switchTo().alert().accept();
			Thread.sleep(1000);
			accCode.getCancelButton().click();
		}
		catch(Error e)
		{
			new Take_Screenshot().get_Screenshot(driver, method.getName());
			log.fatal(e.toString());
			throw e;
		}
		catch(Exception e)
		{
			new Take_Screenshot().get_Screenshot(driver, method.getName());
			log.info(e.toString());
			throw e;
		}
		finally
		{
//			new CleanUP(driver).snmTelephonyAccountCode(driver, ipData, loginData,testData[2], log);
		}
	}
	
	@Test
	public void test_create_accountCodeLast_moreThan_15_Digits(Method method) throws Exception
	{
		List<WebElement> list = new ArrayList<WebElement>();
		snmLogin = new SNM_Login_Page(driver);
		snmMainPage = new SNM_Main_Page(driver);
		snmTelephony = new SNM_TelephonyPage(driver);
		wait = new WebDriverWait(driver, 10);
		accCode = new SNM_Telephony_AccountCode_Page(driver);
		telExt = new SNM_TelephonyExtensionsPage(driver);
		
		String[] snmCredentials = loginData.getData("test_snm_valid_login", 1);;
		String[] testData = snmTests.getData(method.getName(), 1);
		
		try
		{
			snmTests.checkTestStatus(method.getName());
			LoggerClass.markTestBeginning(method.getName(), "SNM_ExternalNumberLength_Test");
			driver.get(ipData.getData(1, 0));
			snmLogin.snm_login(snmCredentials[0], snmCredentials[1]);
			
			log.info("After successful login");
			
			snmMainPage.getTelephony().click();
			snmTelephony.getExtensions().click();
			telExt.getAccountCode().click();
			accCode.getAddButton().click();
//			accCode.setAccountCodeField(testData[0]);
			accCode.setAccountCodeFirstField(testData[0]);
			accCode.setAccountCodeLastField(testData[1]);
			accCode.getApplyButton().click();
			Thread.sleep(1000);
			Assert.assertEquals(driver.switchTo().alert().getText().trim(),
					"Account Code Last is invalid.");
			driver.switchTo().alert().accept();
			Thread.sleep(1000);
			accCode.getCancelButton().click();
		}
		catch(Error e)
		{
			new Take_Screenshot().get_Screenshot(driver, method.getName());
			log.fatal(e.toString());
			throw e;
		}
		catch(Exception e)
		{
			new Take_Screenshot().get_Screenshot(driver, method.getName());
			log.info(e.toString());
			throw e;
		}
		finally
		{
//			new CleanUP(driver).snmTelephonyAccountCode(driver, ipData, loginData,testData[2], log);
		}
	}
	
	
	@Test
	public void test_verify_errMsg_when_accCode_First_last_are_provided(Method method) throws Exception
	{
		List<WebElement> list = new ArrayList<WebElement>();
		snmLogin = new SNM_Login_Page(driver);
		snmMainPage = new SNM_Main_Page(driver);
		snmTelephony = new SNM_TelephonyPage(driver);
		wait = new WebDriverWait(driver, 10);
		accCode = new SNM_Telephony_AccountCode_Page(driver);
		telExt = new SNM_TelephonyExtensionsPage(driver);
		
		String[] snmCredentials = loginData.getData("test_snm_valid_login", 1);;
		String[] testData = snmTests.getData(method.getName(), 1);
		
		try
		{
			snmTests.checkTestStatus(method.getName());
			LoggerClass.markTestBeginning(method.getName(), "SNM_ExternalNumberLength_Test");
			driver.get(ipData.getData(1, 0));
			snmLogin.snm_login(snmCredentials[0], snmCredentials[1]);
			
			log.info("After successful login");
			
			snmMainPage.getTelephony().click();
			snmTelephony.getExtensions().click();
			telExt.getAccountCode().click();
			accCode.getAddButton().click();
			accCode.setAccountCodeField(testData[0]);
			accCode.setAccountCodeFirstField(testData[1]);
			accCode.setAccountCodeLastField(testData[2]);
			accCode.getApplyButton().click();
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//td[@class='errorresponseMessage']")));
			String errorMsg = driver.findElement(By.xpath("//td[@class='errorresponseMessage']")).getText().trim();
			log.info(errorMsg);
			Assert.assertEquals(errorMsg,"Account Code: "+testData[0]+"     "
					+ "Reason: Combination not Allowed             "
					+ "Message: Account Code and Account Code First or Last should not be provided together");
			log.info("Proper error message is displayed.");
		}
		catch(Error e)
		{
			new Take_Screenshot().get_Screenshot(driver, method.getName());
			log.fatal(e.toString());
			throw e;
		}
		catch(Exception e)
		{
			new Take_Screenshot().get_Screenshot(driver, method.getName());
			log.info(e.toString());
			throw e;
		}
		finally
		{
//			new CleanUP(driver).snmTelephonyAccountCode(driver, ipData, loginData,testData[2], log);
		}
	}
	
	
	@Test
	public void test_verify_errMsg_when_only_accCode_First_is_provided(Method method) throws Exception
	{
		List<WebElement> list = new ArrayList<WebElement>();
		snmLogin = new SNM_Login_Page(driver);
		snmMainPage = new SNM_Main_Page(driver);
		snmTelephony = new SNM_TelephonyPage(driver);
		wait = new WebDriverWait(driver, 10);
		accCode = new SNM_Telephony_AccountCode_Page(driver);
		telExt = new SNM_TelephonyExtensionsPage(driver);
		
		String[] snmCredentials = loginData.getData("test_snm_valid_login", 1);;
		String[] testData = snmTests.getData(method.getName(), 1);
		
		try
		{
			snmTests.checkTestStatus(method.getName());
			LoggerClass.markTestBeginning(method.getName(), "SNM_ExternalNumberLength_Test");
			driver.get(ipData.getData(1, 0));
			snmLogin.snm_login(snmCredentials[0], snmCredentials[1]);
			
			log.info("After successful login");
			
			snmMainPage.getTelephony().click();
			snmTelephony.getExtensions().click();
			telExt.getAccountCode().click();
			accCode.getAddButton().click();
//			accCode.setAccountCodeField(testData[0]);
			accCode.setAccountCodeFirstField(testData[0]);
//			accCode.setAccountCodeLastField(testData[2]);
			accCode.getApplyButton().click();
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//td[@class='errorresponseMessage']")));
			String errorMsg = driver.findElement(By.xpath("//td[@class='errorresponseMessage']")).getText().trim();
			log.info(errorMsg);
			Assert.assertEquals(errorMsg,"Account Code First: "+testData[0]+"     "
					+ "Reason: Account Code Last is Required");
			log.info("Proper error message is displayed.");
		}
		catch(Error e)
		{
			new Take_Screenshot().get_Screenshot(driver, method.getName());
			log.fatal(e.toString());
			throw e;
		}
		catch(Exception e)
		{
			new Take_Screenshot().get_Screenshot(driver, method.getName());
			log.info(e.toString());
			throw e;
		}
		finally
		{
//			new CleanUP(driver).snmTelephonyAccountCode(driver, ipData, loginData,testData[2], log);
		}
	}
	
	@Test
	public void test_verify_errMsg_when_only_accCode_Last_is_provided(Method method) throws Exception
	{
		List<WebElement> list = new ArrayList<WebElement>();
		snmLogin = new SNM_Login_Page(driver);
		snmMainPage = new SNM_Main_Page(driver);
		snmTelephony = new SNM_TelephonyPage(driver);
		wait = new WebDriverWait(driver, 10);
		accCode = new SNM_Telephony_AccountCode_Page(driver);
		telExt = new SNM_TelephonyExtensionsPage(driver);
		
		String[] snmCredentials = loginData.getData("test_snm_valid_login", 1);;
		String[] testData = snmTests.getData(method.getName(), 1);
		
		try
		{
			snmTests.checkTestStatus(method.getName());
			LoggerClass.markTestBeginning(method.getName(), "SNM_ExternalNumberLength_Test");
			driver.get(ipData.getData(1, 0));
			snmLogin.snm_login(snmCredentials[0], snmCredentials[1]);
			
			log.info("After successful login");
			
			snmMainPage.getTelephony().click();
			snmTelephony.getExtensions().click();
			telExt.getAccountCode().click();
			accCode.getAddButton().click();
//			accCode.setAccountCodeField(testData[0]);
//			accCode.setAccountCodeFirstField(testData[0]);
			accCode.setAccountCodeLastField(testData[0]);
			accCode.getApplyButton().click();
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//td[@class='errorresponseMessage']")));
			String errorMsg = driver.findElement(By.xpath("//td[@class='errorresponseMessage']")).getText().trim();
			log.info(errorMsg);
			Assert.assertEquals(errorMsg,"Account Code Last:     Reason: Account Code First is Required");
			log.info("Proper error message is displayed.");
		}
		catch(Error e)
		{
			new Take_Screenshot().get_Screenshot(driver, method.getName());
			log.fatal(e.toString());
			throw e;
		}
		catch(Exception e)
		{
			new Take_Screenshot().get_Screenshot(driver, method.getName());
			log.info(e.toString());
			throw e;
		}
		finally
		{
//			new CleanUP(driver).snmTelephonyAccountCode(driver, ipData, loginData,testData[2], log);
		}
	}
	
	
	@Test
	public void test_create_account_code_withCustomer(Method method) throws Exception
	{
		List<WebElement> list = new ArrayList<WebElement>();
		snmLogin = new SNM_Login_Page(driver);
		snmMainPage = new SNM_Main_Page(driver);
		snmTelephony = new SNM_TelephonyPage(driver);
		wait = new WebDriverWait(driver, 10);
		accCode = new SNM_Telephony_AccountCode_Page(driver);
		telExt = new SNM_TelephonyExtensionsPage(driver);
		
		String[] snmCredentials = loginData.getData("test_snm_valid_login", 1);;
		String[] testData = snmTests.getData(method.getName(), 1);
		
		try
		{
			snmTests.checkTestStatus(method.getName());
			LoggerClass.markTestBeginning(method.getName(), "SNM_ExternalNumberLength_Test");
			driver.get(ipData.getData(1, 0));
			snmLogin.snm_login(snmCredentials[0], snmCredentials[1]);
			
			log.info("After successful login");
			
			snmMainPage.getTelephony().click();
			snmTelephony.getExtensions().click();
			telExt.getAccountCode().click();
			accCode.getAddButton().click();
//			accCode.setAccountCodeField(testData[0]);
//			accCode.setAccountCodeFirstField(testData[1]);
//			accCode.setAccountCodeLastField(testData[2]);
			accCode.getAddCustomerButton().click();
			accCode.setCustomerName(testData[1]);
			accCode.setCustomerNumber(testData[2]);
			accCode.getCustomerApplyButton().click();
			accCode.getDoneButton().click();
			log.info("Successfully added customer");
			accCode.setAccountCodeField(testData[0]);
			new SelectDropDownValue().selectByValue(accCode.getCustomerDropDown(), testData[1]);
			
			accCode.getApplyButton().click();
			wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("//span[contains(@class,'responseMessage')]"),
					"Add operation successful for:"));
			log.info("After successful accCode creation");
			accCode.getDoneButton().click();
			accCode.getRange().clear();
			accCode.setRange(testData[0]);
			accCode.getViewButton().click();
			list.clear();
			list = new ExplicitWait().numberOfElementsPresent(driver, 3, 
					By.xpath("//td[contains(text(),'"+testData[0]+"')]"));
			Assert.assertTrue(list.size()==1);
		}
		catch(Error e)
		{
			new Take_Screenshot().get_Screenshot(driver, method.getName());
			log.fatal(e.toString());
			throw e;
		}
		catch(Exception e)
		{
			new Take_Screenshot().get_Screenshot(driver, method.getName());
			log.info(e.toString());
			throw e;
		}
		finally
		{
			new CleanUP(driver).snmTelephonyAccountCode(driver, ipData, loginData,testData[0], log);
			new CleanUP(driver).deleteCustomer(driver, ipData, loginData,testData[1], log);
		}
	}
	
	
	@Test
	public void test_create_account_code_with_firstANDLastCodes_withCustomer(Method method) throws Exception
	{
		List<WebElement> list = new ArrayList<WebElement>();
		snmLogin = new SNM_Login_Page(driver);
		snmMainPage = new SNM_Main_Page(driver);
		snmTelephony = new SNM_TelephonyPage(driver);
		wait = new WebDriverWait(driver, 10);
		accCode = new SNM_Telephony_AccountCode_Page(driver);
		telExt = new SNM_TelephonyExtensionsPage(driver);
		
		String[] snmCredentials = loginData.getData("test_snm_valid_login", 1);;
		String[] testData = snmTests.getData(method.getName(), 1);
		
		try
		{
			snmTests.checkTestStatus(method.getName());
			LoggerClass.markTestBeginning(method.getName(), "SNM_ExternalNumberLength_Test");
			driver.get(ipData.getData(1, 0));
			snmLogin.snm_login(snmCredentials[0], snmCredentials[1]);
			
			log.info("After successful login");
			
			snmMainPage.getTelephony().click();
			snmTelephony.getExtensions().click();
			telExt.getAccountCode().click();
			accCode.getAddButton().click();
//			accCode.setAccountCodeField(testData[0]);
//			accCode.setAccountCodeFirstField(testData[0]);
//			accCode.setAccountCodeLastField(testData[1]);
			
			accCode.getAddCustomerButton().click();
			accCode.setCustomerName(testData[3]);
			accCode.setCustomerNumber(testData[4]);
			accCode.getCustomerApplyButton().click();
			accCode.getDoneButton().click();
			log.info("Successfully added customer");
			accCode.setAccountCodeFirstField(testData[0]);
			accCode.setAccountCodeLastField(testData[1]);
			new SelectDropDownValue().selectByValue(accCode.getCustomerDropDown(), testData[3]);
			
			accCode.getApplyButton().click();
			wait.until(ExpectedConditions.textToBePresentInElement(accCode.getResponseMsg(),
					"Add operation successful for:"));
			log.info("After successful accCode creation");
			accCode.getDoneButton().click();
			accCode.getRange().clear();
			accCode.setRange(testData[2]);
			accCode.getViewButton().click();
			list.clear();
			list = new ExplicitWait().numberOfElementsPresent(driver, 3, 
					By.xpath("//td[contains(text(),'"+testData[2]+"')]"));
			Assert.assertTrue(list.size()==1);
		}
		catch(Error e)
		{
			new Take_Screenshot().get_Screenshot(driver, method.getName());
			log.fatal(e.toString());
			throw e;
		}
		catch(Exception e)
		{
			new Take_Screenshot().get_Screenshot(driver, method.getName());
			log.info(e.toString());
			throw e;
		}
		finally
		{
			new CleanUP(driver).snmTelephonyAccountCode(driver, ipData, loginData,testData[2], log);
			new CleanUP(driver).deleteCustomer(driver, ipData, loginData,testData[3], log);
		}
	}
	
	
	@Test
	public void test_create_accountCode_moreThan_15_Digits_withCustomer(Method method) throws Exception
	{
		List<WebElement> list = new ArrayList<WebElement>();
		snmLogin = new SNM_Login_Page(driver);
		snmMainPage = new SNM_Main_Page(driver);
		snmTelephony = new SNM_TelephonyPage(driver);
		wait = new WebDriverWait(driver, 10);
		accCode = new SNM_Telephony_AccountCode_Page(driver);
		telExt = new SNM_TelephonyExtensionsPage(driver);
		
		String[] snmCredentials = loginData.getData("test_snm_valid_login", 1);;
		String[] testData = snmTests.getData(method.getName(), 1);
		
		try
		{
			snmTests.checkTestStatus(method.getName());
			LoggerClass.markTestBeginning(method.getName(), "SNM_ExternalNumberLength_Test");
			driver.get(ipData.getData(1, 0));
			snmLogin.snm_login(snmCredentials[0], snmCredentials[1]);
			
			log.info("After successful login");
			
			snmMainPage.getTelephony().click();
			snmTelephony.getExtensions().click();
			telExt.getAccountCode().click();
			accCode.getAddButton().click();
//			accCode.setAccountCodeField(testData[0]);
//			accCode.setAccountCodeFirstField(testData[0]);
//			accCode.setAccountCodeLastField(testData[1]);
			accCode.getAddCustomerButton().click();
			accCode.setCustomerName(testData[1]);
			accCode.setCustomerNumber(testData[2]);
			accCode.getCustomerApplyButton().click();
			accCode.getDoneButton().click();
			log.info("Successfully added customer");
			accCode.setAccountCodeField(testData[0]);
			new SelectDropDownValue().selectByValue(accCode.getCustomerDropDown(), testData[1]);
			
			accCode.getApplyButton().click();
			Assert.assertEquals(driver.switchTo().alert().getText().trim(),
					"Account Code(s) is invalid.");
			driver.switchTo().alert().accept();
			accCode.getCancelButton().click();
		}
		catch(Error e)
		{
			new Take_Screenshot().get_Screenshot(driver, method.getName());
			log.fatal(e.toString());
			throw e;
		}
		catch(Exception e)
		{
			new Take_Screenshot().get_Screenshot(driver, method.getName());
			log.info(e.toString());
			throw e;
		}
		finally
		{
//			new CleanUP(driver).snmTelephonyAccountCode(driver, ipData, loginData,testData[2], log);
			new CleanUP(driver).deleteCustomer(driver, ipData, loginData,testData[1], log);
		}
	}
	
	
	@Test
	public void test_create_accountCodeFirst_moreThan_15_Digits_withCustomer(Method method) throws Exception
	{
		List<WebElement> list = new ArrayList<WebElement>();
		snmLogin = new SNM_Login_Page(driver);
		snmMainPage = new SNM_Main_Page(driver);
		snmTelephony = new SNM_TelephonyPage(driver);
		wait = new WebDriverWait(driver, 10);
		accCode = new SNM_Telephony_AccountCode_Page(driver);
		telExt = new SNM_TelephonyExtensionsPage(driver);
		
		String[] snmCredentials = loginData.getData("test_snm_valid_login", 1);;
		String[] testData = snmTests.getData(method.getName(), 1);
		
		try
		{
			snmTests.checkTestStatus(method.getName());
			LoggerClass.markTestBeginning(method.getName(), "SNM_ExternalNumberLength_Test");
			driver.get(ipData.getData(1, 0));
			snmLogin.snm_login(snmCredentials[0], snmCredentials[1]);
			
			log.info("After successful login");
			
			snmMainPage.getTelephony().click();
			snmTelephony.getExtensions().click();
			telExt.getAccountCode().click();
			accCode.getAddButton().click();
			
			accCode.getAddCustomerButton().click();
			accCode.setCustomerName(testData[2]);
			accCode.setCustomerNumber(testData[3]);
			accCode.getCustomerApplyButton().click();
			accCode.getDoneButton().click();
			log.info("Successfully added customer");
			accCode.setAccountCodeFirstField(testData[0]);
			accCode.setAccountCodeLastField(testData[1]);
			new SelectDropDownValue().selectByValue(accCode.getCustomerDropDown(), testData[2]);
			
			accCode.getApplyButton().click();
			Assert.assertEquals(driver.switchTo().alert().getText().trim(),
					"Account Code First is invalid.");
			driver.switchTo().alert().accept();
			accCode.getCancelButton().click();
			
		}
		catch(Error e)
		{
			new Take_Screenshot().get_Screenshot(driver, method.getName());
			log.fatal(e.toString());
			throw e;
		}
		catch(Exception e)
		{
			new Take_Screenshot().get_Screenshot(driver, method.getName());
			log.info(e.toString());
			throw e;
		}
		finally
		{
//			new CleanUP(driver).snmTelephonyAccountCode(driver, ipData, loginData,testData[2], log);
			new CleanUP(driver).deleteCustomer(driver, ipData, loginData,testData[2], log);
		}
	}
	
	
	@Test
	public void test_create_accountCodeLast_moreThan_15_Digits_withCustomer(Method method) throws Exception
	{
		List<WebElement> list = new ArrayList<WebElement>();
		snmLogin = new SNM_Login_Page(driver);
		snmMainPage = new SNM_Main_Page(driver);
		snmTelephony = new SNM_TelephonyPage(driver);
		wait = new WebDriverWait(driver, 10);
		accCode = new SNM_Telephony_AccountCode_Page(driver);
		telExt = new SNM_TelephonyExtensionsPage(driver);
		
		String[] snmCredentials = loginData.getData("test_snm_valid_login", 1);;
		String[] testData = snmTests.getData(method.getName(), 1);
		
		try
		{
			snmTests.checkTestStatus(method.getName());
			LoggerClass.markTestBeginning(method.getName(), "SNM_ExternalNumberLength_Test");
			driver.get(ipData.getData(1, 0));
			snmLogin.snm_login(snmCredentials[0], snmCredentials[1]);
			
			log.info("After successful login");
			
			snmMainPage.getTelephony().click();
			snmTelephony.getExtensions().click();
			telExt.getAccountCode().click();
			accCode.getAddButton().click();
			
			accCode.getAddCustomerButton().click();
			accCode.setCustomerName(testData[2]);
			accCode.setCustomerNumber(testData[3]);
			accCode.getCustomerApplyButton().click();
			accCode.getDoneButton().click();
			log.info("Successfully added customer");
			accCode.setAccountCodeFirstField(testData[0]);
			accCode.setAccountCodeLastField(testData[1]);
			new SelectDropDownValue().selectByValue(accCode.getCustomerDropDown(), testData[2]);
			
			accCode.getApplyButton().click();
			Assert.assertEquals(driver.switchTo().alert().getText().trim(),
					"Account Code Last is invalid.");
			driver.switchTo().alert().accept();
			accCode.getCancelButton().click();
			
		}
		catch(Error e)
		{
			new Take_Screenshot().get_Screenshot(driver, method.getName());
			log.fatal(e.toString());
			throw e;
		}
		catch(Exception e)
		{
			new Take_Screenshot().get_Screenshot(driver, method.getName());
			log.info(e.toString());
			throw e;
		}
		finally
		{
//			new CleanUP(driver).snmTelephonyAccountCode(driver, ipData, loginData,testData[2], log);
			new CleanUP(driver).deleteCustomer(driver, ipData, loginData,testData[2], log);
		}
	}
	
	
	
	@Test
	public void test_verify_errMsg_when_accCode_First_last_are_provided_withCustomer(Method method) throws Exception
	{
		List<WebElement> list = new ArrayList<WebElement>();
		snmLogin = new SNM_Login_Page(driver);
		snmMainPage = new SNM_Main_Page(driver);
		snmTelephony = new SNM_TelephonyPage(driver);
		wait = new WebDriverWait(driver, 10);
		accCode = new SNM_Telephony_AccountCode_Page(driver);
		telExt = new SNM_TelephonyExtensionsPage(driver);
		
		String[] snmCredentials = loginData.getData("test_snm_valid_login", 1);;
		String[] testData = snmTests.getData(method.getName(), 1);
		
		try
		{
			snmTests.checkTestStatus(method.getName());
			LoggerClass.markTestBeginning(method.getName(), "SNM_ExternalNumberLength_Test");
			driver.get(ipData.getData(1, 0));
			snmLogin.snm_login(snmCredentials[0], snmCredentials[1]);
			
			log.info("After successful login");
			
			snmMainPage.getTelephony().click();
			snmTelephony.getExtensions().click();
			telExt.getAccountCode().click();
			accCode.getAddButton().click();
			
			accCode.getAddCustomerButton().click();
			accCode.setCustomerName(testData[3]);
			accCode.setCustomerNumber(testData[4]);
			accCode.getCustomerApplyButton().click();
			accCode.getDoneButton().click();
			log.info("Successfully added customer");
			accCode.setAccountCodeField(testData[0]);
			accCode.setAccountCodeFirstField(testData[1]);
			accCode.setAccountCodeLastField(testData[2]);
			new SelectDropDownValue().selectByValue(accCode.getCustomerDropDown(), testData[3]);
			
			accCode.getApplyButton().click();
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//td[@class='errorresponseMessage']")));
			String errorMsg = driver.findElement(By.xpath("//td[@class='errorresponseMessage']")).getText().trim();
			log.info(errorMsg);
			Assert.assertEquals(errorMsg,"Account Code: "+testData[0]+"     "
					+ "Reason: Combination not Allowed             "
					+ "Message: Account Code and Account Code First or Last should not be provided together");
			log.info("Proper error message is displayed.");
			
		}
		catch(Error e)
		{
			new Take_Screenshot().get_Screenshot(driver, method.getName());
			log.fatal(e.toString());
			throw e;
		}
		catch(Exception e)
		{
			new Take_Screenshot().get_Screenshot(driver, method.getName());
			log.info(e.toString());
			throw e;
		}
		finally
		{
//			new CleanUP(driver).snmTelephonyAccountCode(driver, ipData, loginData,testData[2], log);
			new CleanUP(driver).deleteCustomer(driver, ipData, loginData,testData[3], log);
		}
	}
	
	
	@Test
	public void test_verify_errMsg_when_only_accCode_First_is_provided_withCustomer(Method method) throws Exception
	{
		List<WebElement> list = new ArrayList<WebElement>();
		snmLogin = new SNM_Login_Page(driver);
		snmMainPage = new SNM_Main_Page(driver);
		snmTelephony = new SNM_TelephonyPage(driver);
		wait = new WebDriverWait(driver, 10);
		accCode = new SNM_Telephony_AccountCode_Page(driver);
		telExt = new SNM_TelephonyExtensionsPage(driver);
		
		String[] snmCredentials = loginData.getData("test_snm_valid_login", 1);;
		String[] testData = snmTests.getData(method.getName(), 1);
		
		try
		{
			snmTests.checkTestStatus(method.getName());
			LoggerClass.markTestBeginning(method.getName(), "SNM_ExternalNumberLength_Test");
			driver.get(ipData.getData(1, 0));
			snmLogin.snm_login(snmCredentials[0], snmCredentials[1]);
			
			log.info("After successful login");
			
			snmMainPage.getTelephony().click();
			snmTelephony.getExtensions().click();
			telExt.getAccountCode().click();
			accCode.getAddButton().click();
			
			
			accCode.getAddCustomerButton().click();
			accCode.setCustomerName(testData[1]);
			accCode.setCustomerNumber(testData[2]);
			accCode.getCustomerApplyButton().click();
			accCode.getDoneButton().click();
			log.info("Successfully added customer");
//			accCode.setAccountCodeField(testData[0]);
			accCode.setAccountCodeFirstField(testData[0]);
//			accCode.setAccountCodeLastField(testData[2]);
			new SelectDropDownValue().selectByValue(accCode.getCustomerDropDown(), testData[1]);
			
			accCode.getApplyButton().click();
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//td[@class='errorresponseMessage']")));
			String errorMsg = driver.findElement(By.xpath("//td[@class='errorresponseMessage']")).getText().trim();
			log.info(errorMsg);
			Assert.assertEquals(errorMsg,"Account Code First: "+testData[0]+"     "
					+ "Reason: Account Code Last is Required");
			log.info("Proper error message is displayed.");
		}
		catch(Error e)
		{
			new Take_Screenshot().get_Screenshot(driver, method.getName());
			log.fatal(e.toString());
			throw e;
		}
		catch(Exception e)
		{
			new Take_Screenshot().get_Screenshot(driver, method.getName());
			log.info(e.toString());
			throw e;
		}
		finally
		{
//			new CleanUP(driver).snmTelephonyAccountCode(driver, ipData, loginData,testData[2], log);
			new CleanUP(driver).deleteCustomer(driver, ipData, loginData,testData[1], log);
		}
	}
	
	
	@Test
	public void test_verify_errMsg_when_only_accCode_Last_is_provided_withCustomer(Method method) throws Exception
	{
		List<WebElement> list = new ArrayList<WebElement>();
		snmLogin = new SNM_Login_Page(driver);
		snmMainPage = new SNM_Main_Page(driver);
		snmTelephony = new SNM_TelephonyPage(driver);
		wait = new WebDriverWait(driver, 10);
		accCode = new SNM_Telephony_AccountCode_Page(driver);
		telExt = new SNM_TelephonyExtensionsPage(driver);
		
		String[] snmCredentials = loginData.getData("test_snm_valid_login", 1);;
		String[] testData = snmTests.getData(method.getName(), 1);
		
		try
		{
			snmTests.checkTestStatus(method.getName());
			LoggerClass.markTestBeginning(method.getName(), "SNM_ExternalNumberLength_Test");
			driver.get(ipData.getData(1, 0));
			snmLogin.snm_login(snmCredentials[0], snmCredentials[1]);
			
			log.info("After successful login");
			
			snmMainPage.getTelephony().click();
			snmTelephony.getExtensions().click();
			telExt.getAccountCode().click();
			accCode.getAddButton().click();
			
			
			accCode.getAddCustomerButton().click();
			accCode.setCustomerName(testData[1]);
			accCode.setCustomerNumber(testData[2]);
			accCode.getCustomerApplyButton().click();
			accCode.getDoneButton().click();
			log.info("Successfully added customer");
//			accCode.setAccountCodeField(testData[0]);
//			accCode.setAccountCodeFirstField(testData[0]);
			accCode.setAccountCodeLastField(testData[0]);
			new SelectDropDownValue().selectByValue(accCode.getCustomerDropDown(), testData[1]);
			
			accCode.getApplyButton().click();
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//td[@class='errorresponseMessage']")));
			String errorMsg = driver.findElement(By.xpath("//td[@class='errorresponseMessage']")).getText().trim();
			log.info(errorMsg);
			Assert.assertEquals(errorMsg,"Account Code Last:     Reason: Account Code First is Required");
			log.info("Proper error message is displayed.");
		}
		catch(Error e)
		{
			new Take_Screenshot().get_Screenshot(driver, method.getName());
			log.fatal(e.toString());
			throw e;
		}
		catch(Exception e)
		{
			new Take_Screenshot().get_Screenshot(driver, method.getName());
			log.info(e.toString());
			throw e;
		}
		finally
		{
//			new CleanUP(driver).snmTelephonyAccountCode(driver, ipData, loginData,testData[2], log);
			new CleanUP(driver).deleteCustomer(driver, ipData, loginData,testData[1], log);
		}
	}
*/	
}
