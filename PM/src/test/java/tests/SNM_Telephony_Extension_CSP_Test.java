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
import utilities.SelectDropDownValue;
import utilities.Take_Screenshot;

public class SNM_Telephony_Extension_CSP_Test extends ConfigClass
{
	public SNM_Login_Page snmLogin;
	public SNM_Main_Page snmMainPage;
	Logger log = LoggerClass.getLogger("SNM_Telephony_Extension_CSP_Test");
	public SNM_TelephonyPage snmTelephony;
	WebDriverWait wait = null;
	public SNM_TelephonyExtensionsPage telExt;
	public SNM_Telephony_Extensions_CSP_Page snmCSP;
	
	@Test
	public void test_create_CSP(Method method) throws Exception
	{
		snmLogin = new SNM_Login_Page(driver);
		snmMainPage = new SNM_Main_Page(driver);
		snmTelephony = new SNM_TelephonyPage(driver);
		wait = new WebDriverWait(driver, 10);
		telExt = new SNM_TelephonyExtensionsPage(driver);
		snmCSP = new SNM_Telephony_Extensions_CSP_Page(driver);
		
		String[] snmCredentials = loginData.getData("test_snm_valid_login", 1);;
		String[] testData = snmTests.getData(method.getName(), 1);
		
		try
		{
			snmTests.checkTestStatus(method.getName());
			LoggerClass.markTestBeginning(method.getName(), "SNM_Telephony_Extension_CSP_Test");
			driver.get(ipData.getData(1, 0));
			snmLogin.snm_login(snmCredentials[0], snmCredentials[1]);
			
			log.info("After successful login");
			
			snmMainPage.getTelephony().click();
			snmTelephony.getExtensions().click();
			telExt.getCSP().click();
			snmCSP.getAddButton().click();
			snmCSP.getCSPName().clear();
			snmCSP.setCSPName(testData[0]);
			new SelectDropDownValue().selectByValue(snmCSP.getCSPNumberDropDown(), "1");
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
			driver.findElement(By.xpath("//td[contains(text(),'1')]"
					+ "//following-sibling::td[contains(text(),'"+testData[0]+"')]"
					+ "//preceding-sibling::td[22]")).click();
			
			List<WebElement> list1 = new ExplicitWait().numberOfElementsPresent(driver, 3, 
			By.xpath("//td[contains(text(),'CSP Number')]//following-sibling::td[contains(text(),'1')]"));
			
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
			new CleanUP(driver).deleteCSP(driver, ipData, loginData,testData[0], log);
		}
	}
}
