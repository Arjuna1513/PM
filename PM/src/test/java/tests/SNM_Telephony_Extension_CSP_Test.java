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
import org.testng.annotations.Test;

import base.ConfigClass;
import snm_pom_classes.SNM_Login_Page;
import snm_pom_classes.SNM_Main_Page;
import snm_pom_classes.SNM_TelephonyExtensionsPage;
import snm_pom_classes.SNM_TelephonyPage;
import snm_pom_classes.SNM_Telephony_AccountCode_Page;
import snm_pom_classes.SNM_Telephony_Extensions_CSP_Page;
import utilities.CleanUP;
import utilities.ExplicitWait;
import utilities.LoggerClass;
import utilities.ReusableUnits;
import utilities.SelectDropDownValue;
import utilities.Take_Screenshot;
import utilities.UploadFile;

public class SNM_Telephony_Extension_CSP_Test extends ConfigClass
{
	public SNM_Login_Page snmLogin;
	public SNM_Main_Page snmMainPage;
	Logger log = LoggerClass.getLogger("SNM_Telephony_Extension_CSP_Test");
	public SNM_TelephonyPage snmTelephony;
	WebDriverWait wait = null;
	public SNM_TelephonyExtensionsPage telExt;
	public SNM_Telephony_Extensions_CSP_Page snmCSP;
	
/*	@Test
	public void test_create_CSP(Method method) throws Exception
	{
		String[] snmCredentials = loginData.getData("test_snm_valid_login", 1);;
		String[] testData = snmTests.getData(method.getName(), 1);
		
		try
		{
			snmTests.checkTestStatus(method.getName());
			LoggerClass.markTestBeginning(method.getName(), "SNM_Telephony_Extension_CSP_Test");
			new ReusableUnits(driver).createWorkingCSP(driver, method.getName(), 
					ipData, snmTests, loginData, log);
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
			new CleanUP(driver).deleteCSP(driver, ipData, loginData,testData[0], log);
		}
	}
	
	
	@Test
	public void test_create_default_CSP(Method method) throws Exception
	{
		String[] snmCredentials = loginData.getData("test_snm_valid_login", 1);;
		String[] testData = snmTests.getData(method.getName(), 1);
		
		try
		{
			snmTests.checkTestStatus(method.getName());
			LoggerClass.markTestBeginning(method.getName(), "test_create_default_CSP");
			new ReusableUnits(driver).createDefaultCSP(driver, method.getName(), 
					ipData, snmTests, loginData, log);
//			driver.findElement(By.xpath("//td[contains(text(),'CSP 1')]//preceding-sibling::td[21]")).click();
			
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
			new CleanUP(driver).deleteCSP(driver, ipData, loginData,testData[0], log);
		}
	}
	
	@Test
	public void test_edit_CSP_to_configure_Request_A_number_from_PSTN(Method method) throws Exception
	{
		snmCSP = new SNM_Telephony_Extensions_CSP_Page(driver);
		wait = new WebDriverWait(driver, 5);
		String[] snmCredentials = loginData.getData("test_snm_valid_login", 1);;
		String[] testData = snmTests.getData(method.getName(), 1);
		
		try
		{
			snmTests.checkTestStatus(method.getName());
			LoggerClass.markTestBeginning(method.getName(), "test_create_default_CSP");
			new ReusableUnits(driver).createDefaultCSP(driver, method.getName(), 
					ipData, snmTests, loginData, log);
			
			log.info("After creating default CSP");
			new ReusableUnits(driver).editAnyGivenCSP(driver, method.getName(), ipData, 
					snmTests, loginData, log, "RequestANumberForPSTN");
			
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
			new CleanUP(driver).deleteCSP(driver, ipData, loginData,testData[0], log);
		}
	}
	
	@Test
	public void test_edit_CSP_to_configure_Allow_call_waiting_tone_initiation(Method method) throws Exception
	{
		snmCSP = new SNM_Telephony_Extensions_CSP_Page(driver);
		wait = new WebDriverWait(driver, 5);
		String[] snmCredentials = loginData.getData("test_snm_valid_login", 1);;
		String[] testData = snmTests.getData(method.getName(), 1);
		
		try
		{
			snmTests.checkTestStatus(method.getName());
			LoggerClass.markTestBeginning(method.getName(), "test_create_default_CSP");
			new ReusableUnits(driver).createDefaultCSP(driver, method.getName(), 
					ipData, snmTests, loginData, log);
			
			log.info("After creating default CSP");
			new ReusableUnits(driver).editAnyGivenCSP(driver, method.getName(), ipData, 
					snmTests, loginData, log, "AllowCallWaitingToneInitiation");
			
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
			new CleanUP(driver).deleteCSP(driver, ipData, loginData,testData[0], log);
		}
	}
	
	@Test
	public void test_edit_call_waiting_tone_reception_b_party(Method method) throws Exception
	{
		snmCSP = new SNM_Telephony_Extensions_CSP_Page(driver);
		wait = new WebDriverWait(driver, 5);
		String[] snmCredentials = loginData.getData("test_snm_valid_login", 1);;
		String[] testData = snmTests.getData(method.getName(), 1);
		
		try
		{
			snmTests.checkTestStatus(method.getName());
			LoggerClass.markTestBeginning(method.getName(), "test_create_default_CSP");
			new ReusableUnits(driver).createDefaultCSP(driver, method.getName(), 
					ipData, snmTests, loginData, log);
			
			log.info("After creating default CSP");
			new ReusableUnits(driver).editAnyGivenCSP(driver, method.getName(), ipData, 
					snmTests, loginData, log, "CallWaitingToneReceptionBParty");
			
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
			new CleanUP(driver).deleteCSP(driver, ipData, loginData,testData[0], log);
		}
	}
	
	@Test
	public void test_edit_call_waiting_tone_reception_c_party(Method method) throws Exception
	{
		snmCSP = new SNM_Telephony_Extensions_CSP_Page(driver);
		wait = new WebDriverWait(driver, 5);
		String[] snmCredentials = loginData.getData("test_snm_valid_login", 1);;
		String[] testData = snmTests.getData(method.getName(), 1);
		
		try
		{
			snmTests.checkTestStatus(method.getName());
			LoggerClass.markTestBeginning(method.getName(), "test_create_default_CSP");
			new ReusableUnits(driver).createDefaultCSP(driver, method.getName(), 
					ipData, snmTests, loginData, log);
			
			log.info("After creating default CSP");
			new ReusableUnits(driver).editAnyGivenCSP(driver, method.getName(), ipData, 
					snmTests, loginData, log, "CallWaitingToneReceptionCParty");
			
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
			new CleanUP(driver).deleteCSP(driver, ipData, loginData,testData[0], log);
		}
	}
	
	@Test
	public void test_edit_allow_individual_DND(Method method) throws Exception
	{
		snmCSP = new SNM_Telephony_Extensions_CSP_Page(driver);
		wait = new WebDriverWait(driver, 5);
		String[] snmCredentials = loginData.getData("test_snm_valid_login", 1);;
		String[] testData = snmTests.getData(method.getName(), 1);
		
		try
		{
			snmTests.checkTestStatus(method.getName());
			LoggerClass.markTestBeginning(method.getName(), "test_create_default_CSP");
			new ReusableUnits(driver).createDefaultCSP(driver, method.getName(), 
					ipData, snmTests, loginData, log);
			
			log.info("After creating default CSP");
			new ReusableUnits(driver).editAnyGivenCSP(driver, method.getName(), ipData, 
					snmTests, loginData, log, "AllowIndividualDND");
			
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
			new CleanUP(driver).deleteCSP(driver, ipData, loginData,testData[0], log);
		}
	}
	
	@Test
	public void test_edit_allow_activation_deactivation_grpDND(Method method) throws Exception
	{
		snmCSP = new SNM_Telephony_Extensions_CSP_Page(driver);
		wait = new WebDriverWait(driver, 5);
		String[] snmCredentials = loginData.getData("test_snm_valid_login", 1);;
		String[] testData = snmTests.getData(method.getName(), 1);
		
		try
		{
			snmTests.checkTestStatus(method.getName());
			LoggerClass.markTestBeginning(method.getName(), "test_create_default_CSP");
			new ReusableUnits(driver).createDefaultCSP(driver, method.getName(), 
					ipData, snmTests, loginData, log);
			
			log.info("After creating default CSP");
			new ReusableUnits(driver).editAnyGivenCSP(driver, method.getName(), ipData, 
					snmTests, loginData, log, "ActivationDeactivationGrpDND");
			
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
			new CleanUP(driver).deleteCSP(driver, ipData, loginData,testData[0], log);
		}
	}
	
	@Test
	public void test_edit_forceGatwayCalls(Method method) throws Exception
	{
		snmCSP = new SNM_Telephony_Extensions_CSP_Page(driver);
		wait = new WebDriverWait(driver, 5);
		String[] snmCredentials = loginData.getData("test_snm_valid_login", 1);;
		String[] testData = snmTests.getData(method.getName(), 1);
		
		try
		{
			snmTests.checkTestStatus(method.getName());
			LoggerClass.markTestBeginning(method.getName(), "test_create_default_CSP");
			new ReusableUnits(driver).createDefaultCSP(driver, method.getName(), 
					ipData, snmTests, loginData, log);
			
			log.info("After creating default CSP");
			new ReusableUnits(driver).editAnyGivenCSP(driver, method.getName(), ipData, 
					snmTests, loginData, log, "ForceGatewayCalls");
			
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
			new CleanUP(driver).deleteCSP(driver, ipData, loginData,testData[0], log);
		}
	}
	
	@Test
	public void test_edit_call_diversion_category_settings(Method method) throws Exception
	{
		snmCSP = new SNM_Telephony_Extensions_CSP_Page(driver);
		wait = new WebDriverWait(driver, 5);
		String[] snmCredentials = loginData.getData("test_snm_valid_login", 1);;
		String[] testData = snmTests.getData(method.getName(), 1);
		
		try
		{
			snmTests.checkTestStatus(method.getName());
			LoggerClass.markTestBeginning(method.getName(), "test_create_default_CSP");
			new ReusableUnits(driver).createDefaultCSP(driver, method.getName(), 
					ipData, snmTests, loginData, log);
			
			log.info("After creating default CSP");
			new ReusableUnits(driver).editAnyGivenCSP(driver, method.getName(), ipData, 
					snmTests, loginData, log, "CallDiversionCategory");
			
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
			new CleanUP(driver).deleteCSP(driver, ipData, loginData,testData[0], log);
		}
	}
	
	@Test
	public void test_remove_CSP_using_removeButton(Method method) throws Exception
	{
		snmCSP = new SNM_Telephony_Extensions_CSP_Page(driver);
		wait = new WebDriverWait(driver, 5);
		String[] snmCredentials = loginData.getData("test_snm_valid_login", 1);;
		String[] testData = snmTests.getData(method.getName(), 1);
		
		try
		{
			snmTests.checkTestStatus(method.getName());
			LoggerClass.markTestBeginning(method.getName(), "test_create_default_CSP");
			new ReusableUnits(driver).createWorkingCSP(driver, method.getName(), ipData, snmTests, 
					loginData, log);
			
			log.info("After creating default CSP");
			driver.findElement(By.xpath("//td[contains(text(),'"+testData[0]+"')]//preceding-sibling::td[27]")).click();
			snmCSP.getRemoveButton().click();
			driver.switchTo().alert().accept();
			wait.until(ExpectedConditions.textToBePresentInElement(snmCSP.getResponseMessage(), 
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
			new CleanUP(driver).deleteCSP(driver, ipData, loginData,testData[0], log);
		}
	}
	
	@Test
	public void test_add_CSP_using_existing_CSP_As_Template(Method method) throws Exception
	{
		snmCSP = new SNM_Telephony_Extensions_CSP_Page(driver);
		wait = new WebDriverWait(driver, 5);
		String[] snmCredentials = loginData.getData("test_snm_valid_login", 1);;
		String[] testData = snmTests.getData(method.getName(), 1);
		
		try
		{
			snmTests.checkTestStatus(method.getName());
			LoggerClass.markTestBeginning(method.getName(), "test_create_default_CSP");
			new ReusableUnits(driver).createWorkingCSP(driver, method.getName(), ipData, snmTests, 
					loginData, log);
			
			log.info("After creating default CSP");
			driver.findElement(By.xpath("//td[contains(text(),'"+testData[0]+"')]//preceding-sibling::td[12]")).click();
			snmCSP.getCSPNameForAddingCSPUsingCSPAsTemplate().sendKeys(testData[1]);
			new SelectDropDownValue().selectByValue(snmCSP.getCSPNumberDropDown(), "3");
			
			snmCSP.getNextButton().click();
			snmCSP.getNextButton().click();
			snmCSP.getNextButton().click();
			snmCSP.getNextButton().click();
			snmCSP.getNextButton().click();
			snmCSP.getCSPApplyButton().click();
			
			wait.until(ExpectedConditions.textToBePresentInElement(snmCSP.getResponseMessage(), 
					"Add operation successful for:"));
			snmCSP.getDoneButton().click();
			
			List<WebElement> list = new ExplicitWait().numberOfElementsPresent(driver,3, 
					By.xpath("//td[contains(text(),'"+testData[1]+"')]"));
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
			new CleanUP(driver).deleteCSP(driver, ipData, loginData,testData[0], log);
			new CleanUP(driver).deleteCSP(driver, ipData, loginData,testData[1], log);
		}
	}
	
	@Test
	public void test_create_template_using_existing_CSP(Method method) throws Exception
	{
		snmCSP = new SNM_Telephony_Extensions_CSP_Page(driver);
		wait = new WebDriverWait(driver, 5);
		String[] snmCredentials = loginData.getData("test_snm_valid_login", 1);;
		String[] testData = snmTests.getData(method.getName(), 1);
		
		try
		{
			snmTests.checkTestStatus(method.getName());
			LoggerClass.markTestBeginning(method.getName(), "test_create_default_CSP");
			new ReusableUnits(driver).createWorkingCSP(driver, method.getName(), ipData, snmTests, 
					loginData, log);
			
			log.info("After creating default CSP");
			driver.findElement(By.xpath("//td[contains(text(),'"+testData[0]+"')]//preceding-sibling::td[8]")).click();
			snmCSP.getUploadCSPTemplateName().sendKeys(testData[1]);
//			new SelectDropDownValue().selectByValue(snmCSP.getCSPNumberDropDown(), "3");
			
//			snmCSP.getNextButton().click();
//			snmCSP.getNextButton().click();
//			snmCSP.getNextButton().click();
//			snmCSP.getNextButton().click();
//			snmCSP.getNextButton().click();
			snmCSP.getUploadCSPApplyButton().click();
			
			wait.until(ExpectedConditions.textToBePresentInElement(snmCSP.getResponseMessage(), 
					"Create template from operation successful for:"));
//			snmCSP.getDoneButton().click();
			snmCSP.getManageTemplatesLink().click();
			List<WebElement> list = new ExplicitWait().numberOfElementsPresent(driver, 3, 
					By.xpath("//td[contains(text(),'"+testData[1]+"')]"));
			Assert.assertTrue(list.size()==1);
			snmCSP.getManageTemplatesContinueButton().click();
			
//			List<WebElement> list = new ExplicitWait().numberOfElementsPresent(driver,3, 
//					By.xpath("//td[contains(text(),'"+testData[1]+"')]"));
//			Assert.assertTrue(list.size() == 1);
			
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
			new CleanUP(driver).deleteCSP(driver, ipData, loginData,testData[0], log);
			new CleanUP(driver).deleteCSPTemplate(driver, method.getName(), loginData, testData[1], ipData);
//			new CleanUP(driver).deleteCSP(driver, ipData, loginData,testData[1], log);
		}
	}*/
	
	/*@Test
	public void test_create_CSP_Template(Method method) throws Exception
	{
		snmCSP = new SNM_Telephony_Extensions_CSP_Page(driver);
		wait = new WebDriverWait(driver, 5);
		String[] snmCredentials = loginData.getData("test_snm_valid_login", 1);;
		String[] testData = snmTests.getData(method.getName(), 1);
		
		try
		{
			snmTests.checkTestStatus(method.getName());
			LoggerClass.markTestBeginning(method.getName(), "test_create_default_CSP");
			new ReusableUnits(driver).createCSPTemplate(driver, method.getName(), ipData, 
					loginData, testData[0]);
//			snmMainPage.getLogoutButton().click();
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
			new CleanUP(driver).deleteCSPTemplate(driver, method.getName(), loginData, testData[0], ipData);
		}
	}*/
	
	/*@Test
	public void test_upload_CSP_Template(Method method) throws Exception
	{
		snmCSP = new SNM_Telephony_Extensions_CSP_Page(driver);
		snmMainPage = new SNM_Main_Page(driver);
		snmTelephony = new SNM_TelephonyPage(driver);
		telExt = new SNM_TelephonyExtensionsPage(driver);
		snmLogin = new SNM_Login_Page(driver);
//		WebDriverWait wait = new WebDriverWait(driver, 5);
		
		wait = new WebDriverWait(driver, 5);
		String[] snmCredentials = loginData.getData("test_snm_valid_login", 1);;
		String[] testData = snmTests.getData(method.getName(), 1);
		
		try
		{
			snmTests.checkTestStatus(method.getName());
			LoggerClass.markTestBeginning(method.getName(), "test_create_default_CSP");
			driver.get(ipData.getData(1, 0));
			snmLogin.snm_login(snmCredentials[0], snmCredentials[1]);
			snmMainPage.getTelephony().click();
			snmTelephony.getExtensions().click();
			telExt.getCSP().click();
			snmCSP.getManageTemplatesLink().click();
			
			snmCSP.getUploadTemplateButton().click();
			String path = System.getProperty("user.dir");
			log.info("Path of xml file is "+path);
			snmCSP.getBrowseButton().sendKeys(path+"/CSP0Template.xml");
			snmCSP.getUploadCSPApplyButton().click();
			wait.until(ExpectedConditions.textToBePresentInElement(snmCSP.getResponseMessage(), 
					"Upload Template operation successful for:"));
			snmCSP.getDoneButton().click();
			List<WebElement> tempPresent = new ExplicitWait().numberOfElementsPresent(driver, 3, 
					By.xpath("//td[contains(text(),'"+testData[0]+"')]"));
			Assert.assertTrue(tempPresent.size()==1);
			Thread.sleep(5000);
			snmMainPage.getLogoutButton().click();
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
			new CleanUP(driver).deleteCSPTemplate(driver, method.getName(), loginData, testData[0], ipData);
		}
	}*/
	
	/*@Test
	public void test_edit_CSP_Template(Method method) throws Exception
	{
		snmCSP = new SNM_Telephony_Extensions_CSP_Page(driver);
		wait = new WebDriverWait(driver, 5);
		String[] snmCredentials = loginData.getData("test_snm_valid_login", 1);;
		String[] testData = snmTests.getData(method.getName(), 1);
		
		try
		{
			snmTests.checkTestStatus(method.getName());
			LoggerClass.markTestBeginning(method.getName(), "test_create_default_CSP");
			new ReusableUnits(driver).createCSPTemplate(driver, method.getName(), ipData, 
					loginData, testData[0]);
			driver.findElement(By.xpath("//td[contains(text(),'"+testData[0]+"')]"
					+ "//preceding-sibling::td[19]")).click();
			snmCSP.getServiceCategory().click();
			snmCSP.getAllowForceGatewayCalls().click();
			
			snmCSP.getTemplateNameLink().click();
			snmCSP.getUploadCSPTemplateName().clear();
			snmCSP.getUploadCSPTemplateName().sendKeys(testData[1]);
			snmCSP.getEditCSPApplyButton().click();
			
			wait.until(ExpectedConditions.textToBePresentInElement(snmCSP.getResponseMessage(), 
					"Change Template operation successful for:"));
			snmCSP.getDoneButton().click();
			driver.findElement(By.xpath("//td[contains(text(),'"+testData[0]+"')]"
					+ "//preceding-sibling::td[20]")).click();
			
			List<WebElement> listElement = new ExplicitWait().numberOfElementsPresent(driver, 3, 
					By.xpath("//td[contains(text(),'Force Calls from or to IP Terminal to be Gateway "
							+ "Calls')]//following-sibling::td[contains(text(),'Yes')]"));
			
			List<WebElement> listElement1 = new ExplicitWait().numberOfElementsPresent(driver, 3, 
					By.xpath("//td[contains(text(),'Template Name')]"
							+ "//following-sibling::td[contains(text(),'"+testData[1]+"')]"));
			Assert.assertTrue(listElement.size()==1);
			snmCSP.getDoneButton().click();
//			snmMainPage.getLogoutButton().click();
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
			new CleanUP(driver).deleteCSPTemplate(driver, method.getName(), loginData, testData[1], ipData);
		}
	}	*/
	
	
	/*@Test
	public void test_create_CSP_using_Template(Method method) throws Exception
	{
		snmCSP = new SNM_Telephony_Extensions_CSP_Page(driver);
		wait = new WebDriverWait(driver, 5);
		String[] snmCredentials = loginData.getData("test_snm_valid_login", 1);;
		String[] testData = snmTests.getData(method.getName(), 1);
		
		try
		{
			snmTests.checkTestStatus(method.getName());
			LoggerClass.markTestBeginning(method.getName(), "test_create_default_CSP");
			new ReusableUnits(driver).createCSPTemplate(driver, method.getName(), ipData, 
					loginData, testData[0]);
			snmCSP.getManageTemplatesContinueButton().click();
			new SelectDropDownValue().selectByValue(snmCSP.getCSPTemplateNameDropDown(), "CUST."+testData[0]);
			snmCSP.getAddButton().click();
			snmCSP.setCSPName(testData[1]);
			new SelectDropDownValue().selectByValue(snmCSP.getCSPNumberDropDown(), "2");
			snmCSP.getNextButton().click();
			snmCSP.getNextButton().click();
			snmCSP.getNextButton().click();
			snmCSP.getNextButton().click();
			snmCSP.getNextButton().click();
			snmCSP.getCSPApplyButton().click();
//			snmMainPage.getLogoutButton().click();
			wait.until(ExpectedConditions.textToBePresentInElement(snmCSP.getResponseMessage(), 
					"Add operation successful for:"));
			snmCSP.getDoneButton().click();
			
			List<WebElement> listElement = new ExplicitWait().numberOfElementsPresent(driver, 3,
					By.xpath("//td[contains(text(),'2')]//following-sibling::td[contains(text(),'"+testData[1]+"')]"));
			Assert.assertTrue(listElement.size()==1);
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
			new CleanUP(driver).deleteCSPTemplate(driver, method.getName(), loginData, testData[0], ipData);
			new CleanUP(driver).deleteCSP(driver, ipData, loginData, testData[1], log);
		}
	}*/
	
	/*@Test
	public void test_create_CSP_with_customer(Method method) throws Exception
	{
		String[] snmCredentials = loginData.getData("test_snm_valid_login", 1);;
		String[] testData = snmTests.getData(method.getName(), 1);
		
		try
		{
			snmTests.checkTestStatus(method.getName());
			LoggerClass.markTestBeginning(method.getName(), "SNM_Telephony_Extension_CSP_Test");
			new ReusableUnits(driver).createCustomer(driver, method.getName(), ipData, snmTests, 
					loginData, log);
			new ReusableUnits(driver).createWorkingCSPWithCustomer(driver, method.getName(), 
					ipData, snmTests, loginData, log);
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
			new CleanUP(driver).deleteCSPWithCustomer(driver, ipData, loginData, testData[0], testData[1], log);
			new CleanUP(driver).deleteCustomer(driver, ipData, loginData, testData[1], log);
		}
	}*/
	
	@Test
	public void test_edit_CSP_name(Method method) throws Exception
	{
		WebDriverWait wait = new WebDriverWait(driver, 5);
		snmCSP = new SNM_Telephony_Extensions_CSP_Page(driver);
		String[] snmCredentials = loginData.getData("test_snm_valid_login", 1);;
		String[] testData = snmTests.getData(method.getName(), 1);
		
		try
		{
			snmTests.checkTestStatus(method.getName());
			LoggerClass.markTestBeginning(method.getName(), "SNM_Telephony_Extension_CSP_Test");
			new ReusableUnits(driver).createWorkingCSP(driver, method.getName(), 
					ipData, snmTests, loginData, log);
			driver.findElement(By.xpath("//td[contains(text(),'2')]"
					+ "//following-sibling::td[contains(text(),'"+testData[0]+"')]//preceding-sibling::td[21]")).click();
			snmCSP.getCSPName().clear();
			snmCSP.setCSPName(testData[1]);
			snmCSP.getEditCSPApplyButton().click();
			wait.until(ExpectedConditions.textToBePresentInElement(snmCSP.getResponseMessage(), 
					"Change operation successful for:"));
			snmCSP.getDoneButton().click();
			List<WebElement> element = new ExplicitWait().numberOfElementsPresent(driver, 3, By.xpath("//td[contains(text(),'2')]"
					+ "//following-sibling::td[contains(text(),'"+testData[1]+"')]"));
			Assert.assertTrue(element.size()==1);
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
			new CleanUP(driver).deleteCSP(driver, ipData, loginData,testData[1], log);
		}
	}
}
	
