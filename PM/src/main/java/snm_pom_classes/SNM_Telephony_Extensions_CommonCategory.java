package snm_pom_classes;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SNM_Telephony_Extensions_CommonCategory 
{
	public SNM_Telephony_Extensions_CommonCategory(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="changeThis0_img")
	private WebElement commonCat0;
	public WebElement getCommonCat0()
	{
		return commonCat0;
	}
	
	@FindBy(id="changeThis1_img")
	private WebElement commonCat1;
	public WebElement getCommonCat1()
	{
		return commonCat1;
	}
	
	@FindBy(id="changeThis2_img")
	private WebElement commonCat2;
	public WebElement getCommonCat2()
	{
		return commonCat2;
	}
	
	@FindBy(id="changeThis3_img")
	private WebElement commonCat3;
	public WebElement getCommonCat3()
	{
		return commonCat3;
	}
	
	@FindBy(id="changeThis4_img")
	private WebElement commonCat4;
	public WebElement getCommonCat4()
	{
		return commonCat4;
	}
	
	@FindBy(id="exccp.CAT.CATName")
	private WebElement commonCatName;
	public WebElement getCommonCatName()
	{
		return commonCatName;
	}
	public void setCommonCatName(String name)
	{
		commonCatName.sendKeys(name);
	}
	
	@FindBy(id="exccp.ABBTC")
	private WebElement abbNumTrafficClass;
	public WebElement getAbbNumTrafficClass()
	{
		return abbNumTrafficClass;
	}
	
	@FindBy(id="exccp.TCDN.TCDNName")
	private WebElement tcdCatNight;
	public WebElement getTCDCatNight()
	{
		return tcdCatNight;
	}
	
	@FindBy(id="exccp.TCDD.TCDDName")
	private WebElement tcdCatDay;
	public WebElement getTCDCatDay()
	{
		return tcdCatDay;
	}
	
	@FindBy(id="exccp.trafCc.trafCcNumber")
	private WebElement trafficConnectionClass;
	public WebElement getTrafficConnectionClass()
	{
		return trafficConnectionClass;
	}
	
	@FindBy(linkText="Service Category")
	private WebElement serviceCategoryLink;
	public WebElement getServiceCategoryLink()
	{
		return serviceCategoryLink;
	}
	
	@FindBy(linkText="Call Diversion Category")
	private WebElement callDivCat;
	public WebElement getCallDivCategory()
	{
		return callDivCat;
	}
	
	
	@FindBy(linkText="Routing Category")
	private WebElement routingCat;
	public WebElement getRoutingCat()
	{
		return routingCat;
	}
	
	@FindBy(linkText="Type of Common Category Code")
	private WebElement typeOfCommonCatCode;
	public WebElement getTypeOfCommonCatCode()
	{
		return typeOfCommonCatCode;
	}
	
	@FindBy(id="exccp.INTr.INTrReq_CB")
	private WebElement  AllowIntrusionInitiationAndBypassOfDiversion;
	public WebElement getAllowIntrusionInitiationAndBypassOfDiversion()
	{
		return AllowIntrusionInitiationAndBypassOfDiversion;
	}
	
	
	@FindBy(id="exccp.INTr.INTrRec_CB")
	private WebElement  AllowReceptionOfIntrusionAndCallWaitingTone;
	public WebElement getAllowReceptionOfIntrusionAndCallWaitingTone()
	{
		return AllowReceptionOfIntrusionAndCallWaitingTone;
	}
	
	@FindBy(id="exccp.INTr.INTrP")
	private WebElement IntrusionPriority;
	public WebElement getIntrusionPriority()
	{
		return IntrusionPriority;
	}
	
	@FindBy(id="exccp.CAB")
	private WebElement AutomaticCallBackDeopDown;
	public WebElement getAutomaticCallBackDeopDown()
	{
		return AutomaticCallBackDeopDown;
	}
	
	
	@FindBy(id="exccp.CAW.CAWReq_CB")
	private WebElement AllowCallWaitingToneInitiation;
	public WebElement getAllowCallWaitingToneInitiation()
	{
		return AllowCallWaitingToneInitiation;
	}
	
	@FindBy(id="exccp.CAW.CAWRec")
	private WebElement CallWaitingToneReception;
	public WebElement getCallWaitingToneReception()
	{
		return CallWaitingToneReception;
	}
	
	@FindBy(id="exccp.callOfferedState_CB")
	private WebElement AllowExternalControlledCallDistribution;
	public WebElement getAllowExternalControlledCallDistribution()
	{
		return AllowExternalControlledCallDistribution;
	}
	
	@FindBy(id="exccp.callOfferedTimer")
	private WebElement offeredTimer;
	public WebElement getOfferedTimer()
	{
		return offeredTimer;
	}
	public void setOfferedTimer(String value)
	{
		offeredTimer.sendKeys(value);
	}
	
	
	@FindBy(id="exccp.silentIntrusion_CB")
	private WebElement extendedServicesInIntrusionState;
	public WebElement getExtendedServicesInIntrusionState()
	{
		return extendedServicesInIntrusionState;
	}
	
	
	@FindBy(id="exccp.ACD_CB")
	private WebElement AutomaticCallDistribution;
	public WebElement getAutomaticCallDistribution()
	{
		return AutomaticCallDistribution;
	}
	
	
	@FindBy(id="exccp.answerHandling_CB")
	private WebElement AnswerHandledByExternalApplication;
	public WebElement getAnswerHandledByExternalApplication()
	{
		return AnswerHandledByExternalApplication;
	}
	
	@FindBy(id="exccp.DND.DNDInd_CB")
	private WebElement AllowIndividualDND;
	public WebElement getAllowIndividualDND()
	{
		return AllowIndividualDND;
	}
	
	@FindBy(id="exccp.ECF_CB")
	private WebElement AllowExternalFollowMe;
	public WebElement getAllowExternalFollowMe()
	{
		return AllowExternalFollowMe;
	}
	
	@FindBy(id="exccp.divEx.divIC_CB")
	private WebElement AllowDirectDivToCommonDiverteePosition;
	public WebElement getAllowDirectDivToCommonDiverteePosition()
	{
		return AllowDirectDivToCommonDiverteePosition;
	}
	
	@FindBy(id="exccp.divEx.divB_CB")
	private WebElement AllowDiversionOnBusy;
	public WebElement getAllowDiversionOnBusy()
	{
		return AllowDiversionOnBusy;
	}
	
	@FindBy(id="exccp.divEx.divNoA_CB")
	private WebElement AllowDiversionOnNoAnswer;
	public WebElement getAllowDiversionOnNoAnswer()
	{
		return AllowDiversionOnNoAnswer;
	}
	
	
	@FindBy(id="exccp.divEx.divFM_CB")
	private WebElement AllowFollowMe;
	public WebElement getAllowFollowMe()
	{
		return AllowFollowMe;
	}
	
	
	@FindBy(id="exccp.RPDivEx.RPFM_CB")
	private WebElement AllowRemoteProgrammingOnFollowMe;
	public WebElement getAllowRemoteProgrammingOnFollowMe()
	{
		return AllowRemoteProgrammingOnFollowMe;
	}
	
	@FindBy(id="exccp.RPDivEx.RPECF_CB")
	private WebElement AllowRemoteProgrammingOnECF;
	public WebElement getAllowRemoteProgrammingOnECF()
	{
		return AllowRemoteProgrammingOnECF;
	}
	
	@FindBy(id="exccp.RPDivEx.RPDivNoA_CB")
	private WebElement AllowRemoteProgrammingOnNoReply;
	public WebElement getAllowRemoteProgrammingOnNoReply()
	{
		return AllowRemoteProgrammingOnNoReply;
	}
	
	@FindBy(id="exccp.RPDivEx.RPDivB_CB")
	private WebElement AllowRemoteProgrammingOnBusy;
	public WebElement getAllowRemoteProgrammingOnBusy()
	{
		return AllowRemoteProgrammingOnBusy;
	}
	
	
	@FindBy(id="exccp.RPDivEx.RPDivIC_CB")
	private WebElement AllowRemoteProgrammingOnDirectDiversion;
	public WebElement getAllowRemoteProgrammingOnDirectDiversion()
	{
		return AllowRemoteProgrammingOnDirectDiversion;
	}
	
	@FindBy(name="Apply")
	private WebElement ApplyButton;
	public WebElement getApplyButton()
	{
		return ApplyButton;
	}
	
	@FindBy(className="responseMessage")
	private WebElement responseMsg;
	public WebElement getResponseMessage()
	{
		return responseMsg;
	}
	
	@FindBy(name="okbutton")
	private WebElement doneButton;
	public WebElement getDoneButton()
	{
		return doneButton;
	}
	
	@FindBy(id="viewThis0_img")
	private WebElement viewThis0;
	public WebElement getViewThis0()
	{
		return viewThis0;
	}
	
	@FindBy(id="viewThis1_img")
	private WebElement viewThis1;
	public WebElement getViewThis1()
	{
		return viewThis1;
	}
	
	@FindBy(id="viewThis2_img")
	private WebElement viewThis2;
	public WebElement getViewThis2()
	{
		return viewThis2;
	}
	
	@FindBy(id="viewThis3_img")
	private WebElement viewThis3;
	public WebElement getViewThis3()
	{
		return viewThis3;
	}
	
	@FindBy(id="viewThis4_img")
	private WebElement viewThis4;
	public WebElement getViewThis4()
	{
		return viewThis4;
	}
}
