package utilities;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import snm_pom_classes.SNM_Login_Page;
import snm_pom_classes.SNM_Main_Page;
import snm_pom_classes.SNM_NumberPlan;
import snm_pom_classes.SNM_NumberSeries;
import snm_pom_classes.SNM_Number_Analysis_Page;

public class NumberSeries_Utils 
{
	public SNM_Login_Page snmLogin;
	public SNM_Main_Page snmMainPage;
	public SNM_Number_Analysis_Page numAnalysis;
	public SNM_NumberPlan numPlan;
	public SNM_NumberSeries numSeries;
	WebDriverWait wait = null;
	
	public void createAnyInternalNumberSeries(WebDriver driver, String methodName,
			ExcelReadAndWrite ipData, ExcelReadAndWrite snmData,
			ExcelReadAndWrite loginData, Logger log, String numSeriesType)
	{
		snmLogin = new SNM_Login_Page(driver);
		snmMainPage = new SNM_Main_Page(driver);
		numAnalysis = new SNM_Number_Analysis_Page(driver);
		numPlan = new SNM_NumberPlan(driver);
		numSeries = new SNM_NumberSeries(driver);
		wait = new WebDriverWait(driver, 30);
		
		String[] snmCredentials = null;
		String[] testData = null;
		
			snmCredentials = loginData.getData("test_snm_valid_login", 1);
			testData = snmData.getData(methodName, 1);
			driver.get(ipData.getData(1, 0));
			snmLogin.snm_login(snmCredentials[0], snmCredentials[1]);
			
			log.info("After successful login");
			
			snmMainPage.getNumber_Analysis().click();
			numAnalysis.getNumber_Plan_Link().click();
			numPlan.getNumberSeries().click();
			numSeries.getAddButton().click();
			
			numSeries.getNextButton().click();
			if(numSeriesType.equalsIgnoreCase("ex"))
			{
				numSeries.setDirectoryNumbersTextBox(testData[0]);
			}
			else if(numSeriesType.equalsIgnoreCase("oc"))
			{
				numSeries.setCommonOperatorNumbers(testData[0]);
			}
			else if(numSeriesType.equalsIgnoreCase("oi"))
			{
				numSeries.setIndividualOperatorNumbers(testData[0]);
			}
			else if(numSeriesType.equalsIgnoreCase("ac"))
			{
				numSeries.setCommonAbbreviatedNumbers(testData[0]);
			}
			else if(numSeriesType.equalsIgnoreCase("oe"))
			{
				numSeries.getAdvanceButton().click();
				numSeries.setEmergencyNumberToOperator(testData[0]);
			}
			else if(numSeriesType.equalsIgnoreCase("ai"))
			{
				numSeries.getAdvanceButton().click();
				numSeries.setIndividualAbbreviatedNumbers(testData[0]);
			}
			else if(numSeriesType.equalsIgnoreCase("rd"))
			{
				numSeries.getAdvanceButton().click();
				numSeries.setRouteDirectoryNumbers(testData[0]);
			}
			else if(numSeriesType.equalsIgnoreCase("dn"))
			{
				numSeries.getAdvanceButton().click();
				numSeries.setDialedNumberInfoService(testData[0]);
			}
			else if(numSeriesType.equalsIgnoreCase("pg"))
			{
				numSeries.getAdvanceButton().click();
				numSeries.setPagingNumbers(testData[0]);
			}
			else if(numSeriesType.equalsIgnoreCase("gr"))
			{
				numSeries.getAdvanceButton().click();
				numSeries.setGatewayRoutingNumbers(testData[0]);
			}
			numSeries.getApplyButton().click();
			wait.until(ExpectedConditions.textToBePresentInElement(numSeries.getResponseMsg(), 
					"Add operation successful for:"));
			log.debug("Add Directory numbers is successful");
			
			numSeries.getDoneButton().click();
			new SelectDropDownValue().selectByValue(numSeries.getNumberSeriesDropDown(), numSeriesType);
			numSeries.getNumberSeriesViewButton().click();
			log.debug("Before Viewing the created Number series");
			
			if(numSeriesType.equalsIgnoreCase("ex"))
			{
				List<WebElement> elePresent = new ExplicitWait().numberOfElementsPresent(driver, 3, 
						By.xpath("//td[contains(text(),'"+testData[2]+"')]"
								+ "//following-sibling::td[contains(text(),'"+testData[1]+"')]"));
				Assert.assertTrue(elePresent.size() == 1);
				log.debug("Element is viewed successfully");
			}
			else if(numSeriesType.equalsIgnoreCase("oc"))
			{
				List<WebElement> elePresent = new ExplicitWait().numberOfElementsPresent(driver, 3, 
						By.xpath("//td[contains(text(),'"+testData[2]+"')]"
								+ "//following-sibling::td[contains(text(),'"+testData[1]+"')]"));
				Assert.assertTrue(elePresent.size() == 1);
				log.debug("Element is viewed successfully");
			}
			else if(numSeriesType.equalsIgnoreCase("oi"))
			{
				List<WebElement> elePresent = new ExplicitWait().numberOfElementsPresent(driver, 3, 
						By.xpath("//td[contains(text(),'"+testData[2]+"')]"
								+ "//following-sibling::td[contains(text(),'"+testData[1]+"')]"));
				Assert.assertTrue(elePresent.size() == 1);
				log.debug("Element is viewed successfully");
			}
			else if(numSeriesType.equalsIgnoreCase("ac"))
			{
				List<WebElement> elePresent = new ExplicitWait().numberOfElementsPresent(driver, 3, 
						By.xpath("//td[contains(text(),'"+testData[2]+"')]"
								+ "//following-sibling::td[contains(text(),'"+testData[1]+"')]"));
				Assert.assertTrue(elePresent.size() == 1);
				log.debug("Element is viewed successfully");
			}
			else if(numSeriesType.equalsIgnoreCase("oe"))
			{
				List<WebElement> elePresent = new ExplicitWait().numberOfElementsPresent(driver, 3, 
						By.xpath("//td[contains(text(),'"+testData[2]+"')]"
								+ "//following-sibling::td[contains(text(),'"+testData[1]+"')]"));
				Assert.assertTrue(elePresent.size() == 1);
				log.debug("Element is viewed successfully");
			}
			else if(numSeriesType.equalsIgnoreCase("ai"))
			{
				List<WebElement> elePresent1 = new ExplicitWait().numberOfElementsPresent(driver, 3, 
						By.xpath("//td[contains(text(),'"+testData[2]+"#"+"')]"
								+ "//following-sibling::td[contains(text(),'"+testData[1]+"')]"));
				List<WebElement> elePresent2 = new ExplicitWait().numberOfElementsPresent(driver, 3, 
						By.xpath("//td[contains(text(),'"+testData[3]+"#"+"')]"
								+ "//following-sibling::td[contains(text(),'"+testData[1]+"')]"));
				
				Assert.assertTrue(elePresent1.size() == 1);
				Assert.assertTrue(elePresent2.size() == 1);
				log.debug("Element is viewed successfully");
			}
			else if(numSeriesType.equalsIgnoreCase("rd"))
			{
				List<WebElement> elePresent = new ExplicitWait().numberOfElementsPresent(driver, 3, 
						By.xpath("//td[contains(text(),'"+testData[2]+"')]"
								+ "//following-sibling::td[contains(text(),'"+testData[1]+"')]"));
				Assert.assertTrue(elePresent.size() == 1);
				log.debug("Element is viewed successfully");
			}
			else if(numSeriesType.equalsIgnoreCase("dn"))
			{
				List<WebElement> elePresent = new ExplicitWait().numberOfElementsPresent(driver, 3, 
						By.xpath("//td[contains(text(),'"+testData[2]+"')]"
								+ "//following-sibling::td[contains(text(),'"+testData[1]+"')]"));
				Assert.assertTrue(elePresent.size() == 1);
				log.debug("Element is viewed successfully");
			}
			else if(numSeriesType.equalsIgnoreCase("pg"))
			{
				List<WebElement> elePresent = new ExplicitWait().numberOfElementsPresent(driver, 3, 
						By.xpath("//td[contains(text(),'"+testData[2]+"')]"
								+ "//following-sibling::td[contains(text(),'"+testData[1]+"')]"));
				Assert.assertTrue(elePresent.size() == 1);
				log.debug("Element is viewed successfully");
			}
			else if(numSeriesType.equalsIgnoreCase("gr"))
			{
				List<WebElement> elePresent = new ExplicitWait().numberOfElementsPresent(driver, 3, 
						By.xpath("//td[contains(text(),'"+testData[2]+"')]"
								+ "//following-sibling::td[contains(text(),'"+testData[1]+"')]"));
				Assert.assertTrue(elePresent.size() == 1);
				log.debug("Element is viewed successfully");
			}
	}
	
	
	
	public void createAnyExternalNumberSeries(WebDriver driver, String methodName,
			ExcelReadAndWrite ipData, ExcelReadAndWrite snmData,
			ExcelReadAndWrite loginData, Logger log, String numSeriesType)
	{
		snmLogin = new SNM_Login_Page(driver);
		snmMainPage = new SNM_Main_Page(driver);
		numAnalysis = new SNM_Number_Analysis_Page(driver);
		numPlan = new SNM_NumberPlan(driver);
		numSeries = new SNM_NumberSeries(driver);
		wait = new WebDriverWait(driver, 30);
		
		String[] snmCredentials = null;
		String[] testData = null;
		
			snmCredentials = loginData.getData("test_snm_valid_login", 1);
			testData = snmData.getData(methodName, 1);
			driver.get(ipData.getData(1, 0));
			snmLogin.snm_login(snmCredentials[0], snmCredentials[1]);
			
			log.info("After successful login");
			
			snmMainPage.getNumber_Analysis().click();
			numAnalysis.getNumber_Plan_Link().click();
			numPlan.getNumberSeries().click();
			numSeries.getAddButton().click();
			
			numSeries.getExternalNumbers().click();
			numSeries.getNextButton().click();
			
			if(numSeriesType.equalsIgnoreCase("ec"))
			{
				numSeries.setExternalCoordinationDestination(testData[0]);
			}
			else if(numSeriesType.equalsIgnoreCase("ed"))
			{
				numSeries.setExternalDestination(testData[0]);
			}
			else if(numSeriesType.equalsIgnoreCase("lc"))
			{
				numSeries.setLeastCostRoutingAccessNumbers(testData[0]);
			}
			else if(numSeriesType.equalsIgnoreCase("od"))
			{
				numSeries.setCommonDirectIndialingOperatorNumbers(testData[0]);
			}
			else if(numSeriesType.equalsIgnoreCase("en"))
			{
				numSeries.setOwnNodeNumber(testData[0]);
			}
			else if(numSeriesType.equalsIgnoreCase("cp"))
			{
				numSeries.setCommonPublicDirNumbers(testData[0]);
			}
			else if(numSeriesType.equalsIgnoreCase("r1"))
			{
				numSeries.setAccessNumbersForMobileExtensionWithoutAuth(testData[0]);
			}
			else if(numSeriesType.equalsIgnoreCase("r2"))
			{
				numSeries.setAccessNumbersForMobileExtensionWithAuth(testData[0]);
			}
			else if(numSeriesType.equalsIgnoreCase("r3"))
			{
				numSeries.setAccessNumbersForMobileExtensionDialTone(testData[0]);
			}
			else if(numSeriesType.equalsIgnoreCase("pd"))
			{
				numSeries.setPublicDestinationLCR(testData[0]);
			}
			else if(numSeriesType.equalsIgnoreCase("di"))
			{
				numSeries.setDirectInwardServiceAccess(testData[0]);
			}
			else if(numSeriesType.equalsIgnoreCase("dp"))
			{
				numSeries.setFictitiousDestinationNumbers(testData[0]);
			}
			numSeries.getApplyButton().click();
			wait.until(ExpectedConditions.textToBePresentInElement(numSeries.getResponseMsg(), 
					"Add operation successful for:"));
			log.debug("Add Directory numbers is successful");
			
			numSeries.getDoneButton().click();
			new SelectDropDownValue().selectByValue(numSeries.getNumberSeriesDropDown(), numSeriesType);
			numSeries.getNumberSeriesViewButton().click();
			log.debug("Before Viewing the created Number series");
			
			if(numSeriesType.equalsIgnoreCase("ec"))
			{
				List<WebElement> elePresent = new ExplicitWait().numberOfElementsPresent(driver, 3, 
						By.xpath("//td[contains(text(),'"+testData[2]+"')]"
								+ "//following-sibling::td[contains(text(),'"+testData[1]+"')]"));
				Assert.assertTrue(elePresent.size() == 1);
				log.debug("Element is viewed successfully");
			}
			else if(numSeriesType.equalsIgnoreCase("ed"))
			{
				List<WebElement> elePresent = new ExplicitWait().numberOfElementsPresent(driver, 3, 
						By.xpath("//td[contains(text(),'"+testData[2]+"')]"
								+ "//following-sibling::td[contains(text(),'"+testData[1]+"')]"));
				Assert.assertTrue(elePresent.size() == 1);
				log.debug("Element is viewed successfully");
			}
			else if(numSeriesType.equalsIgnoreCase("lc"))
			{
				List<WebElement> elePresent = new ExplicitWait().numberOfElementsPresent(driver, 3, 
						By.xpath("//td[contains(text(),'"+testData[2]+"')]"
								+ "//following-sibling::td[contains(text(),'"+testData[1]+"')]"));
				Assert.assertTrue(elePresent.size() == 1);
				log.debug("Element is viewed successfully");
			}
			else if(numSeriesType.equalsIgnoreCase("od"))
			{
				List<WebElement> elePresent = new ExplicitWait().numberOfElementsPresent(driver, 3, 
						By.xpath("//td[contains(text(),'"+testData[2]+"')]"
								+ "//following-sibling::td[contains(text(),'"+testData[1]+"')]"));
				Assert.assertTrue(elePresent.size() == 1);
				log.debug("Element is viewed successfully");
			}
			else if(numSeriesType.equalsIgnoreCase("en"))
			{
				List<WebElement> elePresent = new ExplicitWait().numberOfElementsPresent(driver, 3, 
						By.xpath("//td[contains(text(),'"+testData[2]+"')]"
								+ "//following-sibling::td[contains(text(),'"+testData[1]+"')]"));
				Assert.assertTrue(elePresent.size() == 1);
				log.debug("Element is viewed successfully");
			}
			else if(numSeriesType.equalsIgnoreCase("cp"))
			{
				List<WebElement> elePresent1 = new ExplicitWait().numberOfElementsPresent(driver, 3, 
						By.xpath("//td[contains(text(),'"+testData[2]+"')]"
								+ "//following-sibling::td[contains(text(),'"+testData[1]+"')]"));
				List<WebElement> elePresent2 = new ExplicitWait().numberOfElementsPresent(driver, 3, 
						By.xpath("//td[contains(text(),'"+testData[3]+"')]"
								+ "//following-sibling::td[contains(text(),'"+testData[1]+"')]"));
				
				Assert.assertTrue(elePresent1.size() == 1);
				Assert.assertTrue(elePresent2.size() == 1);
				log.debug("Element is viewed successfully");
			}
			else if(numSeriesType.equalsIgnoreCase("r1"))
			{
				List<WebElement> elePresent = new ExplicitWait().numberOfElementsPresent(driver, 3, 
						By.xpath("//td[contains(text(),'"+testData[2]+"')]"
								+ "//following-sibling::td[contains(text(),'"+testData[1]+"')]"));
				Assert.assertTrue(elePresent.size() == 1);
				log.debug("Element is viewed successfully");
			}
			else if(numSeriesType.equalsIgnoreCase("r2"))
			{
				List<WebElement> elePresent = new ExplicitWait().numberOfElementsPresent(driver, 3, 
						By.xpath("//td[contains(text(),'"+testData[2]+"')]"
								+ "//following-sibling::td[contains(text(),'"+testData[1]+"')]"));
				Assert.assertTrue(elePresent.size() == 1);
				log.debug("Element is viewed successfully");
			}
			else if(numSeriesType.equalsIgnoreCase("r3"))
			{
				List<WebElement> elePresent = new ExplicitWait().numberOfElementsPresent(driver, 3, 
						By.xpath("//td[contains(text(),'"+testData[2]+"')]"
								+ "//following-sibling::td[contains(text(),'"+testData[1]+"')]"));
				Assert.assertTrue(elePresent.size() == 1);
				log.debug("Element is viewed successfully");
			}
			else if(numSeriesType.equalsIgnoreCase("pd"))
			{
				List<WebElement> elePresent = new ExplicitWait().numberOfElementsPresent(driver, 3, 
						By.xpath("//td[contains(text(),'"+testData[2]+"')]"
								+ "//following-sibling::td[contains(text(),'"+testData[1]+"')]"));
				Assert.assertTrue(elePresent.size() == 1);
				log.debug("Element is viewed successfully");
			}
			else if(numSeriesType.equalsIgnoreCase("di"))
			{
				List<WebElement> elePresent = new ExplicitWait().numberOfElementsPresent(driver, 3, 
						By.xpath("//td[contains(text(),'"+testData[2]+"')]"
								+ "//following-sibling::td[contains(text(),'"+testData[1]+"')]"));
				Assert.assertTrue(elePresent.size() == 1);
				log.debug("Element is viewed successfully");
			}
			else if(numSeriesType.equalsIgnoreCase("dp"))
			{
				List<WebElement> elePresent = new ExplicitWait().numberOfElementsPresent(driver, 3, 
						By.xpath("//td[contains(text(),'"+testData[2]+"')]"
								+ "//following-sibling::td[contains(text(),'"+testData[1]+"')]"));
				Assert.assertTrue(elePresent.size() == 1);
				log.debug("Element is viewed successfully");
			}
	}
	
	
	
	
	public void deleteAnyInternalNumberSeries(WebDriver driver, String methodName,
			ExcelReadAndWrite ipData, ExcelReadAndWrite snmData,
			ExcelReadAndWrite loginData, Logger log, String numSeriesType) throws InterruptedException
	{
		wait = new WebDriverWait(driver, 20);
		
		
		snmLogin = new SNM_Login_Page(driver);
		snmMainPage = new SNM_Main_Page(driver);
		numAnalysis = new SNM_Number_Analysis_Page(driver);
		numPlan = new SNM_NumberPlan(driver);
		numSeries = new SNM_NumberSeries(driver);
		wait = new WebDriverWait(driver, 30);
		
		String[] snmCredentials = null;
		String[] testData = null;
		
			snmCredentials = loginData.getData("test_snm_valid_login", 1);
			testData = snmData.getData(methodName, 1);
			driver.get(ipData.getData(1, 0));
			snmLogin.snm_login(snmCredentials[0], snmCredentials[1]);
			
			log.info("After successful login");
			
			snmMainPage.getNumber_Analysis().click();
			numAnalysis.getNumber_Plan_Link().click();
			numPlan.getNumberSeries().click();
			
		new SelectDropDownValue().selectByValue(numSeries.getNumberSeriesDropDown(), numSeriesType);
		numSeries.getNumberSeriesViewButton().click();
		Thread.sleep(2000);
		
		
		if(numSeriesType.equalsIgnoreCase("ex"))
		{
			if(driver.findElement(By.xpath("//td[contains(text(),'"+testData[2]+"')]")) != null)
			{
				driver.findElement(By.xpath("//td[contains(text(),"
						+ "'"+testData[2]+"')]"
						+ "//following-sibling::td[contains(text(),'"+testData[1]+"')]"
								+ "//preceding-sibling::td[20]")).click();
				
				driver.switchTo().alert().accept();
				log.debug("Before deleting Directory numbers");
				wait.until(ExpectedConditions.textToBePresentInElement(numSeries.getResponseMsg(), 
						"Remove operation successful for:"));
				log.debug("After deleting Directory numbers");
				
				List<WebElement> eleList = new ExplicitWait().numberOfElementsPresent
				(driver, 3, By.xpath("//td[contains(text(),'"+testData[2]+"')]"));
				
				Assert.assertTrue(eleList.size()==0);
			}
			
		}
		else if(numSeriesType.equalsIgnoreCase("oc"))
		{
			if(driver.findElement(By.xpath("//td[contains(text(),'"+testData[2]+"')]")) != null)
			{
				driver.findElement(By.xpath("//td[contains(text(),"
						+ "'"+testData[2]+"')]"
						+ "//following-sibling::td[contains(text(),'"+testData[1]+"')]"
								+ "//preceding-sibling::td[20]")).click();
				
				driver.switchTo().alert().accept();
				log.debug("Before deleting Directory numbers");
				wait.until(ExpectedConditions.textToBePresentInElement(numSeries.getResponseMsg(), 
						"Remove operation successful for:"));
				log.debug("After deleting Directory numbers");
				
				List<WebElement> eleList = new ExplicitWait().numberOfElementsPresent
				(driver, 3, By.xpath("//td[contains(text(),'"+testData[2]+"')]"));
				
				Assert.assertTrue(eleList.size()==0);
			}
			
		}
		else if(numSeriesType.equalsIgnoreCase("oi"))
		{
			if(driver.findElement(By.xpath("//td[contains(text(),'"+testData[2]+"')]")) != null)
			{
				driver.findElement(By.xpath("//td[contains(text(),"
						+ "'"+testData[2]+"')]"
						+ "//following-sibling::td[contains(text(),'"+testData[1]+"')]"
								+ "//preceding-sibling::td[20]")).click();
				
				driver.switchTo().alert().accept();
				log.debug("Before deleting Directory numbers");
				wait.until(ExpectedConditions.textToBePresentInElement(numSeries.getResponseMsg(), 
						"Remove operation successful for:"));
				log.debug("After deleting Directory numbers");
				
				List<WebElement> eleList = new ExplicitWait().numberOfElementsPresent
				(driver, 3, By.xpath("//td[contains(text(),'"+testData[2]+"')]"));
				
				Assert.assertTrue(eleList.size()==0);
			}
			
		}
		else if(numSeriesType.equalsIgnoreCase("ac"))
		{
			if(driver.findElement(By.xpath("//td[contains(text(),'"+testData[2]+"')]")) != null)
			{
				driver.findElement(By.xpath("//td[contains(text(),"
						+ "'"+testData[2]+"')]"
						+ "//following-sibling::td[contains(text(),'"+testData[1]+"')]"
								+ "//preceding-sibling::td[20]")).click();
				
				driver.switchTo().alert().accept();
				log.debug("Before deleting Directory numbers");
				wait.until(ExpectedConditions.textToBePresentInElement(numSeries.getResponseMsg(), 
						"Remove operation successful for:"));
				log.debug("After deleting Directory numbers");
				
				List<WebElement> eleList = new ExplicitWait().numberOfElementsPresent
				(driver, 3, By.xpath("//td[contains(text(),'"+testData[2]+"')]"));
				
				Assert.assertTrue(eleList.size()==0);
			}
			
		}
		else if(numSeriesType.equalsIgnoreCase("oe"))
		{
			if(driver.findElement(By.xpath("//td[contains(text(),'"+testData[2]+"')]")) != null)
			{
				driver.findElement(By.xpath("//td[contains(text(),"
						+ "'"+testData[2]+"')]"
						+ "//following-sibling::td[contains(text(),'"+testData[1]+"')]"
								+ "//preceding-sibling::td[20]")).click();
				
				driver.switchTo().alert().accept();
				log.debug("Before deleting Directory numbers");
				wait.until(ExpectedConditions.textToBePresentInElement(numSeries.getResponseMsg(), 
						"Remove operation successful for:"));
				log.debug("After deleting Directory numbers");
				
				List<WebElement> eleList = new ExplicitWait().numberOfElementsPresent
				(driver, 3, By.xpath("//td[contains(text(),'"+testData[2]+"')]"));
				
				Assert.assertTrue(eleList.size()==0);
			}
			
		}
		else if(numSeriesType.equalsIgnoreCase("ai"))
		{
			if(driver.findElement(By.xpath("//td[contains(text(),'"+testData[2]+"#"+"')]")) != null)
			{
				driver.findElement(By.xpath("//td[contains(text(),"
						+ "'"+testData[2]+"#"+"')]"
						+ "//following-sibling::td[contains(text(),'"+testData[1]+"')]"
								+ "//preceding-sibling::td[20]")).click();
				
				driver.switchTo().alert().accept();
				log.debug("Before deleting Directory numbers");
				wait.until(ExpectedConditions.textToBePresentInElement(numSeries.getResponseMsg(), 
						"Remove operation successful for:"));
				log.debug("After deleting Directory numbers");
				
				List<WebElement> eleList = new ExplicitWait().numberOfElementsPresent
				(driver, 3, By.xpath("//td[contains(text(),'"+testData[2]+"#"+"')]"));
				
				Assert.assertTrue(eleList.size()==0);
				
				driver.findElement(By.xpath("//td[contains(text(),"
						+ "'"+testData[3]+"#"+"')]"
						+ "//following-sibling::td[contains(text(),'"+testData[1]+"')]"
								+ "//preceding-sibling::td[20]")).click();
				
				driver.switchTo().alert().accept();
				log.debug("Before deleting Directory numbers");
				wait.until(ExpectedConditions.textToBePresentInElement(numSeries.getResponseMsg(), 
						"Remove operation successful for:"));
				log.debug("After deleting Directory numbers");
				
				List<WebElement> eleList1 = new ExplicitWait().numberOfElementsPresent
				(driver, 3, By.xpath("//td[contains(text(),'"+testData[2]+"#"+"')]"));
				
				Assert.assertTrue(eleList1.size()==0);
			}
			
		}
		else if(numSeriesType.equalsIgnoreCase("rd"))
		{
			if(driver.findElement(By.xpath("//td[contains(text(),'"+testData[2]+"')]")) != null)
			{
				driver.findElement(By.xpath("//td[contains(text(),"
						+ "'"+testData[2]+"')]"
						+ "//following-sibling::td[contains(text(),'"+testData[1]+"')]"
								+ "//preceding-sibling::td[20]")).click();
				
				driver.switchTo().alert().accept();
				log.debug("Before deleting Directory numbers");
				wait.until(ExpectedConditions.textToBePresentInElement(numSeries.getResponseMsg(), 
						"Remove operation successful for:"));
				log.debug("After deleting Directory numbers");
				
				List<WebElement> eleList = new ExplicitWait().numberOfElementsPresent
				(driver, 3, By.xpath("//td[contains(text(),'"+testData[2]+"')]"));
				
				Assert.assertTrue(eleList.size()==0);
			}
			
		}
		else if(numSeriesType.equalsIgnoreCase("dn"))
		{
			if(driver.findElement(By.xpath("//td[contains(text(),'"+testData[2]+"')]")) != null)
			{
				driver.findElement(By.xpath("//td[contains(text(),"
						+ "'"+testData[2]+"')]"
						+ "//following-sibling::td[contains(text(),'"+testData[1]+"')]"
								+ "//preceding-sibling::td[20]")).click();
				
				driver.switchTo().alert().accept();
				log.debug("Before deleting Directory numbers");
				wait.until(ExpectedConditions.textToBePresentInElement(numSeries.getResponseMsg(), 
						"Remove operation successful for:"));
				log.debug("After deleting Directory numbers");
				
				List<WebElement> eleList = new ExplicitWait().numberOfElementsPresent
				(driver, 3, By.xpath("//td[contains(text(),'"+testData[2]+"')]"));
				
				Assert.assertTrue(eleList.size()==0);
			}
			
		}
		else if(numSeriesType.equalsIgnoreCase("pg"))
		{
			if(driver.findElement(By.xpath("//td[contains(text(),'"+testData[2]+"')]")) != null)
			{
				driver.findElement(By.xpath("//td[contains(text(),"
						+ "'"+testData[2]+"')]"
						+ "//following-sibling::td[contains(text(),'"+testData[1]+"')]"
								+ "//preceding-sibling::td[20]")).click();
				
				driver.switchTo().alert().accept();
				log.debug("Before deleting Directory numbers");
				wait.until(ExpectedConditions.textToBePresentInElement(numSeries.getResponseMsg(), 
						"Remove operation successful for:"));
				log.debug("After deleting Directory numbers");
				
				List<WebElement> eleList = new ExplicitWait().numberOfElementsPresent
				(driver, 3, By.xpath("//td[contains(text(),'"+testData[2]+"')]"));
				
				Assert.assertTrue(eleList.size()==0);
			}
		}
		else if(numSeriesType.equalsIgnoreCase("gr"))
		{
			if(driver.findElement(By.xpath("//td[contains(text(),'"+testData[2]+"')]")) != null)
			{
				driver.findElement(By.xpath("//td[contains(text(),"
						+ "'"+testData[2]+"')]"
						+ "//following-sibling::td[contains(text(),'"+testData[1]+"')]"
								+ "//preceding-sibling::td[20]")).click();
				
				driver.switchTo().alert().accept();
				log.debug("Before deleting Directory numbers");
				wait.until(ExpectedConditions.textToBePresentInElement(numSeries.getResponseMsg(), 
						"Remove operation successful for:"));
				log.debug("After deleting Directory numbers");
				
				List<WebElement> eleList = new ExplicitWait().numberOfElementsPresent
				(driver, 3, By.xpath("//td[contains(text(),'"+testData[2]+"')]"));
				
				Assert.assertTrue(eleList.size()==0);
			}
		}
	}
	
	
	
	public void deleteAnyExternalNumberSeries(WebDriver driver, String methodName,
			ExcelReadAndWrite ipData, ExcelReadAndWrite snmData,
			ExcelReadAndWrite loginData, Logger log, String numSeriesType) throws InterruptedException
	{
		wait = new WebDriverWait(driver, 20);
		
		
		snmLogin = new SNM_Login_Page(driver);
		snmMainPage = new SNM_Main_Page(driver);
		numAnalysis = new SNM_Number_Analysis_Page(driver);
		numPlan = new SNM_NumberPlan(driver);
		numSeries = new SNM_NumberSeries(driver);
		wait = new WebDriverWait(driver, 30);
		
		String[] snmCredentials = null;
		String[] testData = null;
		
			snmCredentials = loginData.getData("test_snm_valid_login", 1);
			testData = snmData.getData(methodName, 1);
			driver.get(ipData.getData(1, 0));
			snmLogin.snm_login(snmCredentials[0], snmCredentials[1]);
			
			log.info("After successful login");
			
			snmMainPage.getNumber_Analysis().click();
			numAnalysis.getNumber_Plan_Link().click();
			numPlan.getNumberSeries().click();
			
		new SelectDropDownValue().selectByValue(numSeries.getNumberSeriesDropDown(), numSeriesType);
		numSeries.getNumberSeriesViewButton().click();
		Thread.sleep(2000);
		
		
		if(numSeriesType.equalsIgnoreCase("ec"))
		{
			if(driver.findElement(By.xpath("//td[contains(text(),'"+testData[2]+"')]")) != null)
			{
				driver.findElement(By.xpath("//td[contains(text(),"
						+ "'"+testData[2]+"')]"
						+ "//following-sibling::td[contains(text(),'"+testData[1]+"')]"
								+ "//preceding-sibling::td[20]")).click();
				
				driver.switchTo().alert().accept();
				log.debug("Before deleting Directory numbers");
				wait.until(ExpectedConditions.textToBePresentInElement(numSeries.getResponseMsg(), 
						"Remove operation successful for:"));
				log.debug("After deleting Directory numbers");
				
				List<WebElement> eleList = new ExplicitWait().numberOfElementsPresent
				(driver, 3, By.xpath("//td[contains(text(),'"+testData[2]+"')]"));
				
				Assert.assertTrue(eleList.size()==0);
			}
			
		}
		else if(numSeriesType.equalsIgnoreCase("ed"))
		{
			if(driver.findElement(By.xpath("//td[contains(text(),'"+testData[2]+"')]")) != null)
			{
				driver.findElement(By.xpath("//td[contains(text(),"
						+ "'"+testData[2]+"')]"
						+ "//following-sibling::td[contains(text(),'"+testData[1]+"')]"
								+ "//preceding-sibling::td[20]")).click();
				
				driver.switchTo().alert().accept();
				log.debug("Before deleting Directory numbers");
				wait.until(ExpectedConditions.textToBePresentInElement(numSeries.getResponseMsg(), 
						"Remove operation successful for:"));
				log.debug("After deleting Directory numbers");
				
				List<WebElement> eleList = new ExplicitWait().numberOfElementsPresent
				(driver, 3, By.xpath("//td[contains(text(),'"+testData[2]+"')]"));
				
				Assert.assertTrue(eleList.size()==0);
			}
			
		}
		else if(numSeriesType.equalsIgnoreCase("lc"))
		{
			if(driver.findElement(By.xpath("//td[contains(text(),'"+testData[2]+"')]")) != null)
			{
				driver.findElement(By.xpath("//td[contains(text(),"
						+ "'"+testData[2]+"')]"
						+ "//following-sibling::td[contains(text(),'"+testData[1]+"')]"
								+ "//preceding-sibling::td[20]")).click();
				
				driver.switchTo().alert().accept();
				log.debug("Before deleting Directory numbers");
				wait.until(ExpectedConditions.textToBePresentInElement(numSeries.getResponseMsg(), 
						"Remove operation successful for:"));
				log.debug("After deleting Directory numbers");
				
				List<WebElement> eleList = new ExplicitWait().numberOfElementsPresent
				(driver, 3, By.xpath("//td[contains(text(),'"+testData[2]+"')]"));
				
				Assert.assertTrue(eleList.size()==0);
			}
			
		}
		else if(numSeriesType.equalsIgnoreCase("od"))
		{
			if(driver.findElement(By.xpath("//td[contains(text(),'"+testData[2]+"')]")) != null)
			{
				driver.findElement(By.xpath("//td[contains(text(),"
						+ "'"+testData[2]+"')]"
						+ "//following-sibling::td[contains(text(),'"+testData[1]+"')]"
								+ "//preceding-sibling::td[20]")).click();
				
				driver.switchTo().alert().accept();
				log.debug("Before deleting Directory numbers");
				wait.until(ExpectedConditions.textToBePresentInElement(numSeries.getResponseMsg(), 
						"Remove operation successful for:"));
				log.debug("After deleting Directory numbers");
				
				List<WebElement> eleList = new ExplicitWait().numberOfElementsPresent
				(driver, 3, By.xpath("//td[contains(text(),'"+testData[2]+"')]"));
				
				Assert.assertTrue(eleList.size()==0);
			}
			
		}
		else if(numSeriesType.equalsIgnoreCase("en"))
		{
			if(driver.findElement(By.xpath("//td[contains(text(),'"+testData[2]+"')]")) != null)
			{
				driver.findElement(By.xpath("//td[contains(text(),"
						+ "'"+testData[2]+"')]"
						+ "//following-sibling::td[contains(text(),'"+testData[1]+"')]"
								+ "//preceding-sibling::td[20]")).click();
				
				driver.switchTo().alert().accept();
				log.debug("Before deleting Directory numbers");
				wait.until(ExpectedConditions.textToBePresentInElement(numSeries.getResponseMsg(), 
						"Remove operation successful for:"));
				log.debug("After deleting Directory numbers");
				
				List<WebElement> eleList = new ExplicitWait().numberOfElementsPresent
				(driver, 3, By.xpath("//td[contains(text(),'"+testData[2]+"')]"));
				
				Assert.assertTrue(eleList.size()==0);
			}
			
		}
		else if(numSeriesType.equalsIgnoreCase("cp"))
		{
			if(driver.findElement(By.xpath("//td[contains(text(),'"+testData[2]+"')]")) != null)
			{
				driver.findElement(By.xpath("//td[contains(text(),"
						+ "'"+testData[2]+"')]"
						+ "//following-sibling::td[contains(text(),'"+testData[1]+"')]"
								+ "//preceding-sibling::td[20]")).click();
				
				driver.switchTo().alert().accept();
				log.debug("Before deleting Directory numbers");
				wait.until(ExpectedConditions.textToBePresentInElement(numSeries.getResponseMsg(), 
						"Remove operation successful for:"));
				log.debug("After deleting Directory numbers");
				
				List<WebElement> eleList = new ExplicitWait().numberOfElementsPresent
				(driver, 3, By.xpath("//td[contains(text(),'"+testData[2]+"')]"));
				
				Assert.assertTrue(eleList.size()==0);
				
				driver.findElement(By.xpath("//td[contains(text(),"
						+ "'"+testData[3]+"')]"
						+ "//following-sibling::td[contains(text(),'"+testData[1]+"')]"
								+ "//preceding-sibling::td[20]")).click();
				
				driver.switchTo().alert().accept();
				log.debug("Before deleting Directory numbers");
				wait.until(ExpectedConditions.textToBePresentInElement(numSeries.getResponseMsg(), 
						"Remove operation successful for:"));
				log.debug("After deleting Directory numbers");
				
				List<WebElement> eleList1 = new ExplicitWait().numberOfElementsPresent
				(driver, 3, By.xpath("//td[contains(text(),'"+testData[2]+"')]"));
				
				Assert.assertTrue(eleList1.size()==0);
			}
			
		}
		else if(numSeriesType.equalsIgnoreCase("r1"))
		{
			if(driver.findElement(By.xpath("//td[contains(text(),'"+testData[2]+"')]")) != null)
			{
				driver.findElement(By.xpath("//td[contains(text(),"
						+ "'"+testData[2]+"')]"
						+ "//following-sibling::td[contains(text(),'"+testData[1]+"')]"
								+ "//preceding-sibling::td[20]")).click();
				
				driver.switchTo().alert().accept();
				log.debug("Before deleting Directory numbers");
				wait.until(ExpectedConditions.textToBePresentInElement(numSeries.getResponseMsg(), 
						"Remove operation successful for:"));
				log.debug("After deleting Directory numbers");
				
				List<WebElement> eleList = new ExplicitWait().numberOfElementsPresent
				(driver, 3, By.xpath("//td[contains(text(),'"+testData[2]+"')]"));
				
				Assert.assertTrue(eleList.size()==0);
			}
			
		}
		else if(numSeriesType.equalsIgnoreCase("r2"))
		{
			if(driver.findElement(By.xpath("//td[contains(text(),'"+testData[2]+"')]")) != null)
			{
				driver.findElement(By.xpath("//td[contains(text(),"
						+ "'"+testData[2]+"')]"
						+ "//following-sibling::td[contains(text(),'"+testData[1]+"')]"
								+ "//preceding-sibling::td[20]")).click();
				
				driver.switchTo().alert().accept();
				log.debug("Before deleting Directory numbers");
				wait.until(ExpectedConditions.textToBePresentInElement(numSeries.getResponseMsg(), 
						"Remove operation successful for:"));
				log.debug("After deleting Directory numbers");
				
				List<WebElement> eleList = new ExplicitWait().numberOfElementsPresent
				(driver, 3, By.xpath("//td[contains(text(),'"+testData[2]+"')]"));
				
				Assert.assertTrue(eleList.size()==0);
			}
			
		}
		else if(numSeriesType.equalsIgnoreCase("r3"))
		{
			if(driver.findElement(By.xpath("//td[contains(text(),'"+testData[2]+"')]")) != null)
			{
				driver.findElement(By.xpath("//td[contains(text(),"
						+ "'"+testData[2]+"')]"
						+ "//following-sibling::td[contains(text(),'"+testData[1]+"')]"
								+ "//preceding-sibling::td[20]")).click();
				
				driver.switchTo().alert().accept();
				log.debug("Before deleting Directory numbers");
				wait.until(ExpectedConditions.textToBePresentInElement(numSeries.getResponseMsg(), 
						"Remove operation successful for:"));
				log.debug("After deleting Directory numbers");
				
				List<WebElement> eleList = new ExplicitWait().numberOfElementsPresent
				(driver, 3, By.xpath("//td[contains(text(),'"+testData[2]+"')]"));
				
				Assert.assertTrue(eleList.size()==0);
			}
		}
		else if(numSeriesType.equalsIgnoreCase("pd"))
		{
			if(driver.findElement(By.xpath("//td[contains(text(),'"+testData[2]+"')]")) != null)
			{
				driver.findElement(By.xpath("//td[contains(text(),"
						+ "'"+testData[2]+"')]"
						+ "//following-sibling::td[contains(text(),'"+testData[1]+"')]"
								+ "//preceding-sibling::td[20]")).click();
				
				driver.switchTo().alert().accept();
				log.debug("Before deleting Directory numbers");
				wait.until(ExpectedConditions.textToBePresentInElement(numSeries.getResponseMsg(), 
						"Remove operation successful for:"));
				log.debug("After deleting Directory numbers");
				
				List<WebElement> eleList = new ExplicitWait().numberOfElementsPresent
				(driver, 3, By.xpath("//td[contains(text(),'"+testData[2]+"')]"));
				
				Assert.assertTrue(eleList.size()==0);
			}
		}
		else if(numSeriesType.equalsIgnoreCase("di"))
		{
			if(driver.findElement(By.xpath("//td[contains(text(),'"+testData[2]+"')]")) != null)
			{
				driver.findElement(By.xpath("//td[contains(text(),"
						+ "'"+testData[2]+"')]"
						+ "//following-sibling::td[contains(text(),'"+testData[1]+"')]"
								+ "//preceding-sibling::td[20]")).click();
				
				driver.switchTo().alert().accept();
				log.debug("Before deleting Directory numbers");
				wait.until(ExpectedConditions.textToBePresentInElement(numSeries.getResponseMsg(), 
						"Remove operation successful for:"));
				log.debug("After deleting Directory numbers");
				
				List<WebElement> eleList = new ExplicitWait().numberOfElementsPresent
				(driver, 3, By.xpath("//td[contains(text(),'"+testData[2]+"')]"));
				
				Assert.assertTrue(eleList.size()==0);
			}
		}
		else if(numSeriesType.equalsIgnoreCase("dp"))
		{
			if(driver.findElement(By.xpath("//td[contains(text(),'"+testData[2]+"')]")) != null)
			{
				driver.findElement(By.xpath("//td[contains(text(),"
						+ "'"+testData[2]+"')]"
						+ "//following-sibling::td[contains(text(),'"+testData[1]+"')]"
								+ "//preceding-sibling::td[20]")).click();
				
				driver.switchTo().alert().accept();
				log.debug("Before deleting Directory numbers");
				wait.until(ExpectedConditions.textToBePresentInElement(numSeries.getResponseMsg(), 
						"Remove operation successful for:"));
				log.debug("After deleting Directory numbers");
				
				List<WebElement> eleList = new ExplicitWait().numberOfElementsPresent
				(driver, 3, By.xpath("//td[contains(text(),'"+testData[2]+"')]"));
				
				Assert.assertTrue(eleList.size()==0);
			}
		}
	}
}
