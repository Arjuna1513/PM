package snm_pom_classes;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CSP 
{
	public CSP(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	//CSP Add button
	@FindBy(xpath="(//input[@class='buttons1'])[2]")
	private WebElement cspAddButton;
	public WebElement getCSPAddButton()
	{
		return cspAddButton;
	}
	
	//CSP Name
	@FindBy(id="myCommonServiceProfiles_VO.GESPP[0].CSP.CSPName")
	private WebElement CSPName;
	
	public void setCSPName(String name) 
	{
		CSPName.sendKeys(name);
	}
	public WebElement getCSPName()
	{
		return CSPName;
	}
	
	//CSP Number dropdown
	@FindBy(id="myCommonServiceProfiles_VO.GESPP[0].CSP.CSPNumber")
	private WebElement CSPNumberDropDown;
	public WebElement getCSPNumberDropDown()
	{
		return CSPNumberDropDown;
	}
	
	//Request A Number from PSTN
	@FindBy(id="myCommonServiceProfiles_VO.GESPP[0].PSTN_CB")
	private WebElement requestAnumberFromPSTN;
	public WebElement getRequestAnumberFromPSTN()
	{
		return requestAnumberFromPSTN;
	}
	
	//Number presentation restriction.
	@FindBy(id="myCommonServiceProfiles_VO.GESPP[0].pres")
	private WebElement numberPresentationRestriction;
	public WebElement getNumberPresentationRestriction()
	{
		return numberPresentationRestriction;
	}
	
	//call waiting tone initiation
	@FindBy(id="myCommonServiceProfiles_VO.GESPP[0].callWaiting.CAWReq_CB")
	private WebElement callWaitingToneInitation;
	public WebElement getCallWaitingToneInitation()
	{
		return callWaitingToneInitation;
	}
	
	//Call waiting tone B party
	@FindBy(id="myCommonServiceProfiles_VO.GESPP[0].callWaiting.CAWProB")
	private WebElement callWaitingToneBParty;
	public WebElement getCallWaitingToneBParty()
	{
		return callWaitingToneBParty;
	}
	
	//call waiting tone C party
	@FindBy(id="myCommonServiceProfiles_VO.GESPP[0].callWaiting.CAWProC_CB")
	private WebElement callWaitingToneCParty;
	public WebElement getCallWaitingToneCParty()
	{
		return callWaitingToneCParty;
	}
	
	//Intrusion Protection Level
	@FindBy(id="myCommonServiceProfiles_VO.GESPP[0].IPL")
	private WebElement intrusionProtectionLevel;
	public WebElement getIntrusionProtectionLevel()
	{
		return intrusionProtectionLevel;
	}
	
	//Allow DND
	@FindBy(id="myCommonServiceProfiles_VO.GESPP[0].DND.DNDInd_CB")
	private WebElement DND;
	public WebElement getIndivaidualDoNotDisturb()
	{
		return DND;
	}
	
	//Force calls through gateWay.
	@FindBy(id="myCommonServiceProfiles_VO.GESPP[0].uncForceGateway_CB")
	private WebElement forceCallsThroughGateway;
	public WebElement getForceCallsThroughGateway()
	{
		return forceCallsThroughGateway;
	}
	
	//ActivationDeactivationOfDND
	@FindBy(id="myCommonServiceProfiles_VO.GESPP[0].GDND_CB")
	private WebElement activationDeactivationOfDND;
	public WebElement getActivationDeactivationOfDND()
	{
		return activationDeactivationOfDND;
	}
	
	//Next Button
	@FindBy(id="onNextButtonTopID")
	private WebElement nextButton;
	public WebElement getNextButtonTopID()
	{
		return nextButton;
	}
	
	//External Follow Me
	@FindBy(id="myCommonServiceProfiles_VO.GESPP[0].ECF_CB")
	private WebElement externalFollowMe;
	public WebElement getExternalFollowMe()
	{
		return externalFollowMe;
	}
	
	//Allow Follow Me
	@FindBy(id="myCommonServiceProfiles_VO.GESPP[0].DIV.divFM_CB")
	private WebElement allowFollowMe;
	public WebElement getFollowMe()
	{
		return allowFollowMe;
	}
	
	//Allow Direct Diversion To
	@FindBy(id="myCommonServiceProfiles_VO.GESPP[0].DIV.divIC")
	private WebElement allowDirectDiversionTo;
	public WebElement getDirectDiversionTo()
	{
		return allowDirectDiversionTo;
	}
	
	//Allow Diversion On Busy
	@FindBy(id="myCommonServiceProfiles_VO.GESPP[0].DIV.divB_CB")
	private WebElement allowDiversionOnBusy;
	public WebElement getDiversionOnBusy()
	{
		return allowDiversionOnBusy;
	}
	
	//Allow Diversion On No Answer
	@FindBy(id="myCommonServiceProfiles_VO.GESPP[0].DIV.divNoA_CB")
	private WebElement allowDiversionOnNoAnswer;
	public WebElement getDiversionOnNoAnswer()
	{
		return allowDiversionOnNoAnswer;
	}
	
	//allow remote programming on follow me
	@FindBy(id="myCommonServiceProfiles_VO.GESPP[0].RPDivEx.RPFM_CB")
	private WebElement allowRemoteProgrammingOnFollowMe;
	public WebElement getRemoteProgrammingOnFollowMe()
	{
		return allowRemoteProgrammingOnFollowMe;
	}
	
	//allow remote programming on ECF
	@FindBy(id="myCommonServiceProfiles_VO.GESPP[0].RPDivEx.RPECF_CB")
	private WebElement allowRemoteProgrammingOnECF;
	public WebElement getRemoteProgrammingOnECF()
	{
		return allowRemoteProgrammingOnECF;
	}
	
	//allow remote programming on ECF
	@FindBy(id="myCommonServiceProfiles_VO.GESPP[0].RPDivEx.RPDivNoA_CB")
	private WebElement allowRemoteProgrammingOnNoReply;
	public WebElement getRemoteProgrammingOnNoReply()
	{
		return allowRemoteProgrammingOnNoReply;
	}
	
	//allow remote programming on Busy
	@FindBy(id="myCommonServiceProfiles_VO.GESPP[0].RPDivEx.RPDivB_CB")
	private WebElement allowRemoteProgrammingOnBusy;
	public WebElement getRemoteProgrammingOnBusy()
	{
		return allowRemoteProgrammingOnBusy;
	}
	
	//allow remote programming on Direct Diversion
	@FindBy(id="myCommonServiceProfiles_VO.GESPP[0].RPDivEx.RPDivIC_CB")
	private WebElement allowRemoteProgrammingOnDirectDiversion;
	public WebElement getRemoteProgrammingOnDirectDivesrion()
	{
		return allowRemoteProgrammingOnDirectDiversion;
	}
	
	//Apply button
	@FindBy(id="onApplyButtonTopID")
	private WebElement applyButton;
	public WebElement getApplyButton()
	{
		return applyButton;
	}
	
	//Response message
	@FindBy(className="responseMessage")
	private WebElement reponseMessage;
	
	public WebElement getResponseMessage()
	{
		return reponseMessage;
	}
}
