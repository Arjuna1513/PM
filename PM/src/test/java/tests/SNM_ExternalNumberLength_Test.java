package tests;

import java.io.IOException;
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
import utilities.CleanUP;
import utilities.ExcelReadAndWrite;
import utilities.ExplicitWait;
import utilities.LoggerClass;
import utilities.NumberSeries_Utils;
import utilities.ReusableUnits;
import utilities.Take_Screenshot;

public class SNM_ExternalNumberLength_Test extends ConfigClass
{
	public SNM_Login_Page snmLogin;
	public SNM_Main_Page snmMainPage;
	public SNM_Number_Analysis_Page numAnalysis;
	public SNM_NumberPlan numPlan;
	public SNM_NumberSeries numSeries;
	Logger log = LoggerClass.getLogger("SNM_ExternalNumberLength_Test");
	WebDriverWait wait = null;
	public SNM_External_Length_Page extNumLength;
	
	@Test
	public void test_create_external_number_length(Method method, ITestContext context) throws Exception
	{
		snmLogin = new SNM_Login_Page(driver);
		snmMainPage = new SNM_Main_Page(driver);
		numAnalysis = new SNM_Number_Analysis_Page(driver);
		numPlan = new SNM_NumberPlan(driver);
		numSeries = new SNM_NumberSeries(driver);
		wait = new WebDriverWait(driver, 30);
		extNumLength = new SNM_External_Length_Page(driver);
		
		String[] snmCredentials = null;
		String[] testData = snmTests.getData(method.getName(), 1);
		
		try
		{
			snmTests.checkTestStatus(method.getName());
			LoggerClass.markTestBeginning(method.getName(), "SNM_ExternalNumberLength_Test");
			new ReusableUnits(driver).create_externalNumberLength(driver, method.getName(), ipData, snmTests, loginData, log);
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
			new CleanUP(driver).delete_externalNumberLength(driver, ipData, loginData,testData[0], log);
		}
	}
	
	
	@Test
	public void test_edit_extNumLength(Method method) throws Exception
	{
		List<WebElement> list = new ArrayList<WebElement>();
		snmLogin = new SNM_Login_Page(driver);
		snmMainPage = new SNM_Main_Page(driver);
		numAnalysis = new SNM_Number_Analysis_Page(driver);
		numPlan = new SNM_NumberPlan(driver);
		numSeries = new SNM_NumberSeries(driver);
		wait = new WebDriverWait(driver, 30);
		extNumLength = new SNM_External_Length_Page(driver);
		
		String[] testData = snmTests.getData(method.getName(), 1);
		
		try
		{
			snmTests.checkTestStatus(method.getName());
			
			LoggerClass.markTestBeginning(method.getName(), "SNM_ExternalNumberLength_Test");
			new ReusableUnits(driver).create_externalNumberLength(driver, method.getName(), ipData, snmTests, loginData, log);
			
			log.info("Now edit external number length");
			
			driver.findElement(By.xpath("//td[contains(text(),'"+testData[0]+"')]//preceding-sibling::td[19]")).click();
			extNumLength.getExternalNumberLengthField().clear();
			extNumLength.setExternalNumberLength(testData[3]);
			extNumLength.getApplyButton().click();
			wait.until(ExpectedConditions.textToBePresentInElement(extNumLength.getResponseMsg(), 
					"Change operation successful for:"));
			extNumLength.getDoneButton().click();
			
			log.info("edit done successfully");
			list.clear();
			list = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath
					("//td[contains(text(),'"+testData[3]+"')]"
							+ "//following-sibling::td[contains(text(),'"+testData[1]+"')]"
							+ "//following-sibling::td[contains(text(),'"+testData[2]+"')]"));
			Assert.assertTrue(list.size() == 1);
			
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
			new CleanUP(driver).delete_externalNumberLength(driver, ipData, loginData,testData[3], log);
		}
	}
	
	@Test
	public void test_verify_popup_when_num_greaterThanLen10(Method method) throws Exception
	{
		List<WebElement> list = new ArrayList<WebElement>();
		snmLogin = new SNM_Login_Page(driver);
		snmMainPage = new SNM_Main_Page(driver);
		numAnalysis = new SNM_Number_Analysis_Page(driver);
		numPlan = new SNM_NumberPlan(driver);
		numSeries = new SNM_NumberSeries(driver);
		wait = new WebDriverWait(driver, 30);
		extNumLength = new SNM_External_Length_Page(driver);
		
		String[] testData = snmTests.getData(method.getName(), 1);
		String[] credentials = loginData.getData("test_snm_valid_login", 1);
		
		try
		{
			snmTests.checkTestStatus(method.getName());
			LoggerClass.markTestBeginning(method.getName(), "SNM_ExternalNumberLength_Test");
			
			driver.get(ipData.getData(1, 0));
			snmLogin.snm_login(credentials[0], credentials[1]);
			
			log.info("After successful login");
			
			snmMainPage.getNumber_Analysis().click();
			numAnalysis.getNumber_Plan_Link().click();
			numPlan.getExternalNumberLength().click();
			extNumLength.getAddButton().click();
			extNumLength.setExternalNumberLength(testData[0]);
			extNumLength.setMinimumLength(testData[1]);
			extNumLength.getApplyButton().click();
			Assert.assertEquals(driver.switchTo().alert().getText().trim(), "External Number is not valid.");
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
	}
	
	
	@Test
	public void test_verify_popup_when_minLenNum_greaterThanMaxLenNum(Method method) throws Exception
	{
		List<WebElement> list = new ArrayList<WebElement>();
		snmLogin = new SNM_Login_Page(driver);
		snmMainPage = new SNM_Main_Page(driver);
		numAnalysis = new SNM_Number_Analysis_Page(driver);
		numPlan = new SNM_NumberPlan(driver);
		numSeries = new SNM_NumberSeries(driver);
		wait = new WebDriverWait(driver, 30);
		extNumLength = new SNM_External_Length_Page(driver);
		
		String[] testData = snmTests.getData(method.getName(), 1);
		String[] credentials = loginData.getData("test_snm_valid_login", 1);
		
		try
		{
			snmTests.checkTestStatus(method.getName());
			LoggerClass.markTestBeginning(method.getName(), "SNM_ExternalNumberLength_Test");
			
			driver.get(ipData.getData(1, 0));
			snmLogin.snm_login(credentials[0], credentials[1]);
			
			log.info("After successful login");
			
			snmMainPage.getNumber_Analysis().click();
			numAnalysis.getNumber_Plan_Link().click();
			numPlan.getExternalNumberLength().click();
			extNumLength.getAddButton().click();
			extNumLength.setExternalNumberLength(testData[0]);
			extNumLength.setMinimumLength(testData[1]);
			extNumLength.setMaximumLengthField(testData[2]);
			extNumLength.getApplyButton().click();
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//td[@class='errorresponseMessage']")));
			String errorMsg = driver.findElement(By.xpath("//td[@class='errorresponseMessage']")).getText().trim();
			log.info(errorMsg);
			Assert.assertEquals(errorMsg,"External Number: "+testData[0]+"     Reason: Not allowed     Field: Minimum Length         "
							+ "Message: Minimum length can not be greater then maximum length");
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
	}
	
	
	
	@Test
	public void test_create_extNumLength_without_minNumber(Method method) throws Exception
	{
		List<WebElement> list = new ArrayList<WebElement>();
		snmLogin = new SNM_Login_Page(driver);
		snmMainPage = new SNM_Main_Page(driver);
		numAnalysis = new SNM_Number_Analysis_Page(driver);
		numPlan = new SNM_NumberPlan(driver);
		numSeries = new SNM_NumberSeries(driver);
		wait = new WebDriverWait(driver, 30);
		extNumLength = new SNM_External_Length_Page(driver);
		
		String[] testData = snmTests.getData(method.getName(), 1);
		String[] credentials = loginData.getData("test_snm_valid_login", 1);
		
		try
		{
			snmTests.checkTestStatus(method.getName());
			LoggerClass.markTestBeginning(method.getName(), "SNM_ExternalNumberLength_Test");
			
			driver.get(ipData.getData(1, 0));
			snmLogin.snm_login(credentials[0], credentials[1]);
			
			log.info("After successful login");
			
			snmMainPage.getNumber_Analysis().click();
			numAnalysis.getNumber_Plan_Link().click();
			numPlan.getExternalNumberLength().click();
			extNumLength.getAddButton().click();
			extNumLength.setExternalNumberLength(testData[0]);
			extNumLength.getApplyButton().click();
			Thread.sleep(1000);
			Assert.assertEquals("Minimum Length is required.",driver.switchTo().alert().getText().trim());
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
			new CleanUP(driver).delete_externalNumberLength(driver, ipData, loginData,testData[0], log);
		}
	}
	
	
	@Test
	public void test_edit_extNumLength_provide_minLength_greater_thanMaxLength(Method method) throws Exception
	{
		List<WebElement> list = new ArrayList<WebElement>();
		snmLogin = new SNM_Login_Page(driver);
		snmMainPage = new SNM_Main_Page(driver);
		numAnalysis = new SNM_Number_Analysis_Page(driver);
		numPlan = new SNM_NumberPlan(driver);
		numSeries = new SNM_NumberSeries(driver);
		wait = new WebDriverWait(driver, 30);
		extNumLength = new SNM_External_Length_Page(driver);
		
		String[] testData = snmTests.getData(method.getName(), 1);
		
		try
		{
			snmTests.checkTestStatus(method.getName());
			
			LoggerClass.markTestBeginning(method.getName(), "SNM_ExternalNumberLength_Test");
			new ReusableUnits(driver).create_externalNumberLength(driver, method.getName(), ipData, snmTests, loginData, log);
			
			log.info("Now edit external number length");
			
			driver.findElement(By.xpath("//td[contains(text(),'"+testData[0]+"')]//preceding-sibling::td[19]")).click();
			extNumLength.getMinimumLengthField().clear();
			extNumLength.setMinimumLength(testData[3]);
			extNumLength.getApplyButton().click();
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//td[@class='errorresponseMessage']")));
			String errorMsg = driver.findElement(By.xpath("//td[@class='errorresponseMessage']")).getText().trim();
			log.info(errorMsg);
			Assert.assertEquals(errorMsg,"External Number: "+testData[0]+"     Reason: Not allowed     Field: Minimum Length         "
							+ "Message: Minimum length can not be greater then maximum length");
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
			new CleanUP(driver).delete_externalNumberLength(driver, ipData, loginData,testData[0], log);
		}
	}
}
