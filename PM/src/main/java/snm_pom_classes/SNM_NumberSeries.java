package snm_pom_classes;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import utilities.ExplicitWait;


public class SNM_NumberSeries 
{
	WebDriverWait wait = null;
	//Elements of Number Series Home Page
	@FindBy(name="onAddButton")
	private WebElement addButton;
	
	@FindBy(name="range")
	private WebElement numberSeriesDropDown;
	
	@FindBy(name="onViewRangeButton")
	private WebElement numberSeriesViewButton;
	
	@FindBy(id="onNextButtonTopID")
	private WebElement nextButton;
	
	@FindBy(xpath="(//input[@name='onCancelButton'])[1]")
	private WebElement cancelButton;
	
	@FindBy(name="okbutton")
	private WebElement doneButton;
	
	public WebElement getDoneButton()
	{
		return doneButton;
	}
	
	@FindBy(id="removeThis2_img")
	private WebElement deleteNumberSeries;
	
	public WebElement getDeleteNumberSeries() 
	{
		return deleteNumberSeries;
	}

	//Elements of Number Series step 1 page
	@FindBy(id="numType0")
	private WebElement internalNumbers;
	
	@FindBy(id="numType1")
	private WebElement externalNumbers;
	
	@FindBy(xpath="(//input[@name='onBackButton'])[1]")
	private WebElement backButton;
	
	//elements of Number series add step 2 page.	
	@FindBy(id="onApplyButtonTopID")
	private WebElement applyButton;
	
	@FindBy(id="ans_ex_vo.NADAP.start")
	private WebElement directoryNumbersTextBox;
	
	@FindBy(id="ans_oc_vo.NADAP.start")
	private WebElement commonOperatorNumbers;
	
	@FindBy(id="ans_oi_vo.NADAP.start")
	private WebElement individualOperatorNumbers;
	
	@FindBy(id="ans_ac_vo.NADAP.start")
	private WebElement commonAbbreviatedNumbers;
	
	@FindBy(name="onAdvancedButton")
	private WebElement advanceButton;
	
	@FindBy(xpath="//span[contains(@class,'responseMessage')]")
	private WebElement responseMsg;
	
	public WebElement getResponseMsg()
	{
		return responseMsg;
	}

	
	//Elements displayed after clciking on Advanced button
	
	@FindBy(name="ans_oe_vo.NADAP.start")
	private WebElement emergencyNumberToOperator;
	
	@FindBy(name="ans_ai_vo.NADAP.start")
	private WebElement individualAbbreviatedNumbers;
	
	@FindBy(name="ans_rd_vo.NADAP.start")
	private WebElement routeDirectoryNumbers;
	
	@FindBy(name="ans_dn_vo.NADAP.start")
	private WebElement dialedNumberInfoService;
	
	@FindBy(name="ans_pg_vo.NADAP.start")
	private WebElement pagingNumbers;
	
	@FindBy(name="ans_gr_vo.NADAP.start")
	private WebElement gatewayRoutingNumbers;
	
	//WebElements of External Numbers
	@FindBy(name="ans_racopen_vo.NADAP.start")
	private WebElement externalCoordinationDestination;
	
	@FindBy(name="ans_racclose_vo.NADAP.start")
	private WebElement externalDestination;
	
	@FindBy(name="ans_leastcost_vo.NADAP.start")
	private WebElement leastCostRoutingAccessNumbers;
	
	@FindBy(name="ans_commondir_vo.NADAP.start")
	private WebElement commonDirectIndialingOperatorNumbers;
	
	@FindBy(name="ans_ownnode_vo.NADAP.start")
	private WebElement ownNodeNumber;
	
	@FindBy(name="ans_commonpublic_vo.NADAP.start")
	private WebElement commonPublicDirNumbers;
	
	@FindBy(name="ans_r1_vo.NADAP.start")
	private WebElement accessNumbersForMobileExtensionWithoutAuth;
	
	@FindBy(name="ans_r2_vo.NADAP.start")
	private WebElement accessNumbersForMobileExtensionWithAuth;
	
	@FindBy(name="ans_r3_vo.NADAP.start")
	private WebElement accessNumbersForMobileExtensionDialTone;
	
	@FindBy(name="ans_pd_vo.NADAP.start")
	private WebElement publicDestinationLCR;
	
	@FindBy(name="ans_di_vo.NADAP.start")
	private WebElement directInwardServiceAccess;
	
	@FindBy(name="ans_destinationpool_vo.NADAP.start")
	private WebElement fictitiousDestinationNumbers;
	
	

	public WebElement getAddButton() 
	{
		return addButton;
	}

	public WebElement getNumberSeriesDropDown() 
	{
		return numberSeriesDropDown;
	}

	public WebElement getNumberSeriesViewButton() 
	{
		return numberSeriesViewButton;
	}

	public WebElement getNextButton() 
	{
		return nextButton;
	}

	public WebElement getCancelButton() 
	{
		return cancelButton;
	}

	public WebElement getInternalNumbers() 
	{
		return internalNumbers;
	}

	public WebElement getExternalNumbers() 
	{
		return externalNumbers;
	}

	public WebElement getBackButton() {
		return backButton;
	}

	public WebElement getApplyButton() 
	{
		return applyButton;
	}

	public WebElement getDirectoryNumbersTextBox() 
	{
		return directoryNumbersTextBox;
	}

	public void setDirectoryNumbersTextBox(String directoryNumbers) 
	{
		directoryNumbersTextBox.sendKeys(directoryNumbers);
	}

	public WebElement getCommonOperatorNumbers() 
	{
		return commonOperatorNumbers;
	}

	public void setCommonOperatorNumbers(String commonOperatorNumbers) 
	{
		this.commonOperatorNumbers.sendKeys(commonOperatorNumbers);
	}

	public WebElement getIndividualOperatorNumbers() 
	{
		return individualOperatorNumbers;
	}

	public void setIndividualOperatorNumbers(String individualOperatorNumbers) 
	{
		this.individualOperatorNumbers.sendKeys(individualOperatorNumbers);
	}

	public WebElement getCommonAbbreviatedNumbers() 
	{
		return commonAbbreviatedNumbers;
	}

	public void setCommonAbbreviatedNumbers(String commonAbbreviatedNumbers) 
	{
		this.commonAbbreviatedNumbers.sendKeys(commonAbbreviatedNumbers);;
	}

	public WebElement getAdvanceButton() 
	{
		return advanceButton;
	}

	public WebElement getEmergencyNumberToOperator() 
	{
		return emergencyNumberToOperator;
	}

	public void setEmergencyNumberToOperator(String emergencyNumberToOperator) 
	{
		this.emergencyNumberToOperator.sendKeys(emergencyNumberToOperator);;
	}

	public WebElement getIndividualAbbreviatedNumbers() 
	{
		return individualAbbreviatedNumbers;
	}

	public void setIndividualAbbreviatedNumbers(String individualAbbreviatedNumbers) {
		this.individualAbbreviatedNumbers.sendKeys(individualAbbreviatedNumbers);;
	}

	public WebElement getRouteDirectoryNumbers() 
	{
		return routeDirectoryNumbers;
	}

	public void setRouteDirectoryNumbers(String routeDirectoryNumbers) 
	{
		this.routeDirectoryNumbers.sendKeys(routeDirectoryNumbers);
	}

	public WebElement getDialedNumberInfoService() 
	{
		return dialedNumberInfoService;
	}

	public void setDialedNumberInfoService(String dialedNumberInfoService) 
	{
		this.dialedNumberInfoService.sendKeys(dialedNumberInfoService);;
	}

	public WebElement getPagingNumbers() 
	{
		return pagingNumbers;
	}

	public void setPagingNumbers(String pagingNumbers) 
	{
		this.pagingNumbers.sendKeys(pagingNumbers);;
	}

	public WebElement getGatewayRoutingNumbers() 
	{
		return gatewayRoutingNumbers;
	}

	public void setGatewayRoutingNumbers(String gatewayRoutingNumbers) {
		this.gatewayRoutingNumbers.sendKeys(gatewayRoutingNumbers);;
	}

	public WebElement getExternalCoordinationDestination() 
	{
		return externalCoordinationDestination;
	}

	public void setExternalCoordinationDestination(String externalCoordinationDestination) 
	{
		this.externalCoordinationDestination.sendKeys(externalCoordinationDestination);;
	}

	public WebElement getExternalDestination() 
	{
		return externalDestination;
	}

	public void setExternalDestination(String externalDestination) 
	{
		this.externalDestination.sendKeys(externalDestination);;
	}

	public WebElement getLeastCostRoutingAccessNumbers() 
	{
		return leastCostRoutingAccessNumbers;
	}

	public void setLeastCostRoutingAccessNumbers(String leastCostRoutingAccessNumbers) 
	{
		this.leastCostRoutingAccessNumbers.sendKeys(leastCostRoutingAccessNumbers);;
	}

	public WebElement getCommonDirectIndialingOperatorNumbers() 
	{
		return commonDirectIndialingOperatorNumbers;
	}

	public void setCommonDirectIndialingOperatorNumbers(String commonDirectIndialingOperatorNumbers) 
	{
		this.commonDirectIndialingOperatorNumbers.sendKeys(commonDirectIndialingOperatorNumbers);;
	}

	public WebElement getOwnNodeNumber() 
	{
		return ownNodeNumber;
	}

	public void setOwnNodeNumber(String ownNodeNumber) 
	{
		this.ownNodeNumber.sendKeys(ownNodeNumber);;
	}

	public WebElement getCommonPublicDirNumbers() 
	{
		return commonPublicDirNumbers;
	}

	public void setCommonPublicDirNumbers(String commonPublicDirNumbers) 
	{
		this.commonPublicDirNumbers.sendKeys(commonPublicDirNumbers);;
	}

	public WebElement getAccessNumbersForMobileExtensionWithoutAuth() 
	{
		return accessNumbersForMobileExtensionWithoutAuth;
	}

	public void setAccessNumbersForMobileExtensionWithoutAuth(String accessNumbersForMobileExtensionWithoutAuth) 
	{
		this.accessNumbersForMobileExtensionWithoutAuth.sendKeys(accessNumbersForMobileExtensionWithoutAuth);;
	}

	public WebElement getAccessNumbersForMobileExtensionWithAuth() 
	{
		return accessNumbersForMobileExtensionWithAuth;
	}

	public void setAccessNumbersForMobileExtensionWithAuth(String accessNumbersForMobileExtensionWithAuth) 
	{
		this.accessNumbersForMobileExtensionWithAuth.sendKeys(accessNumbersForMobileExtensionWithAuth);;
	}

	public WebElement getAccessNumbersForMobileExtensionDialTone() 
	{
		return accessNumbersForMobileExtensionDialTone;
	}

	public void setAccessNumbersForMobileExtensionDialTone(String accessNumbersForMobileExtensionDialTone) 
	{
		this.accessNumbersForMobileExtensionDialTone.sendKeys(accessNumbersForMobileExtensionDialTone);;
	}

	public WebElement getPublicDestinationLCR() 
	{
		return publicDestinationLCR;
	}

	public void setPublicDestinationLCR(String publicDestinationLCR) 
	{
		this.publicDestinationLCR.sendKeys(publicDestinationLCR);;
	}

	public WebElement getDirectInwardServiceAccess() 
	{
		return directInwardServiceAccess;
	}

	public void setDirectInwardServiceAccess(String directInwardServiceAccess) 
	{
		this.directInwardServiceAccess.sendKeys(directInwardServiceAccess);;
	}

	public WebElement getFictitiousDestinationNumbers() 
	{
		return fictitiousDestinationNumbers;
	}

	public void setFictitiousDestinationNumbers(String fictitiousDestinationNumbers) 
	{
		this.fictitiousDestinationNumbers.sendKeys(fictitiousDestinationNumbers);;
	}
	
	

	public SNM_NumberSeries(WebDriver driver) 
	{
		PageFactory.initElements(driver, this);
	}
		
	//Deleting common operator Numbers
		
	
	@FindBy(xpath="(//td[contains(text(),'Directory numbers')]//preceding-sibling::td/a)[2]")
	private WebElement deleteDirectoryNumbers;

	public WebElement getDeleteDirectoryNumbers() 
	{
		return deleteDirectoryNumbers;
	}			
				
	@FindBy(xpath="(//td[contains(text(),'Common operator numbers')]//preceding-sibling::td/a)[2]")
	private WebElement deleteCommonOperatorNumber;

	public WebElement getDeleteCommonOperatorNumber() 
	{
		return deleteCommonOperatorNumber;
	}
	
	@FindBy(xpath="(//td[contains(text(),'Access numbers for mobile extension (without authorization)')]//preceding-sibling::td/a)[2]")
	private WebElement deleteAccessNumbersMobileExtensionWithoutAuthorization;

	public WebElement getDeleteAccessNumbersMobileExtensionWithoutAuthorization() 
	{
		return deleteAccessNumbersMobileExtensionWithoutAuthorization;
	}
	
	@FindBy(xpath="(//td[contains(text(),'Access numbers for mobile extension (with authorization)')]//preceding-sibling::td/a)[2]")
	private WebElement deleteAccessNumbersMobileExtensionWithAuthorization;

	public WebElement getDeleteAccessNumbersMobileExtensionWithAuthorization() 
	{
		return deleteAccessNumbersMobileExtensionWithAuthorization;
	}
	
	@FindBy(xpath="(//td[contains(text(),'Access numbers for mobile extension (without authorization and DialTone)')]//preceding-sibling::td/a)[2]")
	private WebElement deleteAccessNumbersMobileExtensionWithoutDialtone;

	public WebElement getDeleteAccessNumbersMobileExtensionWithoutDialtone() 
	{
		return deleteAccessNumbersMobileExtensionWithoutDialtone;
	}
	
	@FindBy(xpath="(//td[contains(text(),'Individual abbreviated numbers')]//preceding-sibling::td/a)[2]")
	private WebElement deleteIndividualAbbreviatedNumbers;

	public WebElement getDeleteIndividualAbbreviatedNumbers() 
	{
		return deleteIndividualAbbreviatedNumbers;
	}
	
	@FindBy(xpath="(//td[contains(text(),'Common abbreviated numbers')]//preceding-sibling::td/a)[2]")
	private WebElement deleteCommonAbbreviatedNumbers;

	public WebElement getDeleteCommonAbbreviatedNumbers() 
	{
		return deleteCommonAbbreviatedNumbers;
	}
	
	@FindBy(xpath="(//td[contains(text(),'Common direct in-dialing operator numbers')]//preceding-sibling::td/a)[2]")
	private WebElement deleteCommonDirectInDialingOperatorNumbers;

	public WebElement getDeleteCommonDirectInDialingOperatorNumbers() 
	{
		return deleteCommonDirectInDialingOperatorNumbers;
	}
	
	@FindBy(xpath="(//td[contains(text(),'Individual operator numbers')]//preceding-sibling::td/a)[2]")
	private WebElement deleteIndividualOperatorNumber;

	public WebElement getDeleteIndividualOperatorNumber() 
	{
		return deleteIndividualOperatorNumber;
	}
	
	@FindBy(xpath="(//td[contains(text(),'Emergency numbers to operator')]//preceding-sibling::td/a)[2]")
	private WebElement deleteEmergencyNumbersToOperator;

	public WebElement getDeleteEmergencyNumbersToOperator() 
	{
		return deleteEmergencyNumbersToOperator;
	}
	
	@FindBy(xpath="(//td[contains(text(),'External destination')]//preceding-sibling::td/a)[2]")
	private WebElement deleteExternalDestination;

	public WebElement getDeleteExternalDestination() 
	{
		return deleteExternalDestination;
	}
	
	@FindBy(xpath="(//td[contains(text(),'External coordinated destination')]//preceding-sibling::td/a)[2]")
	private WebElement deleteExternalCoordinatedDestination;

	public WebElement getDeleteExternalCoordinatedDestination() 
	{
		return deleteExternalCoordinatedDestination;
	}
	
	@FindBy(xpath="(//td[contains(text(),'Public destination least cost routing')]//preceding-sibling::td/a)[2]")
	private WebElement deletePublicDestinationLeastCostRouting;

	public WebElement getDeletePublicDestinationLeastCostRouting() 
	{
		return deletePublicDestinationLeastCostRouting;
	}
	
	@FindBy(xpath="(//td[contains(text(),'Route directory numbers')]//preceding-sibling::td/a)[2]")
	private WebElement deleteRouteDirectoryNumbers;

	public WebElement getDeleteRouteDirectoryNumbers() 
	{
		return deleteRouteDirectoryNumbers;
	}
	
	@FindBy(xpath="(//td[contains(text(),'Direct inward service access')]//preceding-sibling::td/a)[2]")
	private WebElement deleteDirectInwardServiceAccess;

	public WebElement getDeleteDirectInwardServiceAccess() 
	{
		return deleteDirectInwardServiceAccess;
	}
	
	@FindBy(xpath="(//td[contains(text(),'Dialed number information service')]//preceding-sibling::td/a)[2]")
	private WebElement deleteDialedNumberInformationService;

	public WebElement getDeleteDialedNumberInformationService() 
	{
		return deleteDialedNumberInformationService;
	}
	
	@FindBy(xpath="(//td[contains(text(),'Least cost routing access numbers')]//preceding-sibling::td/a)[2]")
	private WebElement deleteLeastCostRoutingAccessNumbers;

	public WebElement getDeleteLeastCostRoutingAccessNumbers() 
	{
		return deleteLeastCostRoutingAccessNumbers;
	}
	
	@FindBy(xpath="(//td[contains(text(),'Common public directory numbers')]//preceding-sibling::td/a)[2]")
	private WebElement deleteCommonPublicDirectoryNumbers;

	public WebElement getDeleteCommonPublicDirectoryNumbers() 
	{
		return deleteCommonPublicDirectoryNumbers;
	}
	
	@FindBy(xpath="(//td[contains(text(),'Own node number')]//preceding-sibling::td/a)[2]")
	private WebElement deleteOwnNodeNumber;

	public WebElement getDeleteOwnNodeNumber() 
	{
		return deleteOwnNodeNumber;
	}
	
	@FindBy(xpath="(//td[contains(text(),'Fictitious destination numbers')]//preceding-sibling::td/a)[2]")
	private WebElement deleteFictitiousDestinationNumbers;

	public WebElement getDeleteFictitiousDestinationNumbers() 
	{
		return deleteFictitiousDestinationNumbers;
	}
	
	@FindBy(xpath="(//td[contains(text(),'Paging numbers')]//preceding-sibling::td/a)[2]")
	private WebElement deletePagingNumbers;

	public WebElement getDeletePagingNumbers() 
	{
		return deletePagingNumbers;
	}
	
	@FindBy(xpath="(//td[contains(text(),'Gateway routing numbers')]//preceding-sibling::td/a)[2]")
	private WebElement deleteGatewayRoutingNumbers;

	public WebElement getDeleteGatewayRoutingNumbers() 
	{
		return deleteGatewayRoutingNumbers;
	}
	
	@FindBy(xpath="(//td[contains(text(),'Fictitious destinations(Initiated through Destination task)')]//preceding-sibling::td/a)[2]")
	private WebElement deleteFictitiousDestinationsInitiatedThroughDestinationTask;

	public WebElement getDeleteFictitiousDestinationsInitiatedThroughDestinationTask() 
	{
		return deleteFictitiousDestinationsInitiatedThroughDestinationTask;
	}
	
	//delete number series method
	@FindAll(@FindBy(xpath="(//td[contains(text(),'Directory numbers')]//preceding-sibling::td/a)[2]"))
	private WebElement deleteAllDirectoryNumbers;
	
	/*public void deleteAnyNumberSeries(WebDriver driver, SNM_NumberSeries numberSeries, String numberType) throws InterruptedException
	{
		List<WebElement> list = driver.findElements(By.xpath("//td[contains(text(),'"+numberType+"')]"));
		System.out.println(numberType);
		for(int i=0; i<list.size(); i++)
		{
			System.out.println("In for loop");
			driver.findElement(By.id("removeThis0_img")).click();
			driver.switchTo().alert().accept();
			Assert.assertEquals(numberSeries.getResponseMsg().getText().trim(), "Remove operation successful for:");
			Thread.sleep(2000);
		}
		
	}*/
	
	public void deleteAnyNumberSeries(WebDriver driver, SNM_NumberSeries numSeries, String number, String numberType, Logger log) throws InterruptedException
	{
		
		wait = new WebDriverWait(driver, 20);
		
		driver.findElement(By.xpath("//td[contains(text(),"
				+ "'"+number+"')]"
				+ "//following-sibling::td[contains(text(),'"+numberType+"')]"
						+ "//preceding-sibling::td[20]")).click();
		
		driver.switchTo().alert().accept();
		log.debug("Before deleting Directory numbers");
		wait.until(ExpectedConditions.textToBePresentInElement(numSeries.getResponseMsg(), 
				"Remove operation successful for:"));
		log.debug("After deleting Directory numbers");
		
		List<WebElement> eleList = new ExplicitWait().numberOfElementsPresent
		(driver, 3, By.xpath("//td[contains(text(),'"+number+"')]"
				+ "//following-sibling::td[contains(text(),'"+numberType+"')]"));
		
		Assert.assertTrue(eleList.size()==0);
		
		/*for(int i=0; i<list.size(); i++)
		{
			System.out.println("In for loop");
			driver.findElement(By.id("removeThis0_img")).click();
			driver.switchTo().alert().accept();
			Assert.assertEquals(numberSeries.getResponseMsg().getText().trim(), "Remove operation successful for:");
			Thread.sleep(2000);
		}*/
		
	}
	
	public void editAnyNumberSeries(WebDriver driver, SNM_NumberSeries numberSeries, String editDirNum, WebElement element) throws InterruptedException
	{
			driver.findElement(By.id("changeThis0_img")).click();
			element.clear();
			element.sendKeys(editDirNum);
			Thread.sleep(1000);
			numberSeries.getApplyButton().click();
			Assert.assertEquals(numberSeries.getResponseMsg().getText().trim(), "Change operation successful for:");
	}
	
	
	
}
