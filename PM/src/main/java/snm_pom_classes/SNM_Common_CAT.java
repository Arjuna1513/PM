package snm_pom_classes;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SNM_Common_CAT 
{
	public SNM_Common_CAT(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="changeThis0_img")
	private WebElement cat0;
	public WebElement getCat0()
	{
		return cat0;
	}
	
	@FindBy(id="changeThis1_img")
	private WebElement cat1;
	public WebElement getCat1()
	{
		return cat1;
	}
	
	@FindBy(id="changeThis2_img")
	private WebElement cat2;
	public WebElement getCat2()
	{
		return cat2;
	}
	
	@FindBy(id="changeThis3_img")
	private WebElement cat3;
	public WebElement getCat3()
	{
		return cat3;
	}
	
	@FindBy(id="changeThis4_img")
	private WebElement cat4;
	public WebElement getCat4()
	{
		return cat4;
	}
	
	@FindBy(id="changeThis5_img")
	private WebElement cat5;
	public WebElement getCat5()
	{
		return cat5;
	}
	
	@FindBy(linkText="Traffic Category")
	private WebElement trafficCategoryLink;
	public WebElement getTrafficCategoryLink()
	{
		return trafficCategoryLink;
	}
	
	@FindBy(linkText="Service Category")
	private WebElement ServiceCategory;
	public WebElement getServiceCategory()
	{
		return ServiceCategory;
	}
	
	@FindBy(linkText="Call Diversion Category")
	private WebElement CallDiversionCategory;
	public WebElement getCallDiversionCategory()
	{
		return CallDiversionCategory;
	}
	
	@FindBy(linkText="Routing Category")
	private WebElement RoutingCategory;
	public WebElement getRoutingCategory()
	{
		return RoutingCategory;
	}
	
	@FindBy(linkText="Type of Common Category Code")
	private WebElement TypeOfCommonCategoryCode;
	public WebElement getTypeOfCommonCategoryCode()
	{
		return TypeOfCommonCategoryCode;
	}
	
	@FindBy(id="exccp.CAT.CATName")
	private WebElement catName;
	public WebElement getCatName()
	{
		return catName;
	}
	public void setCatName(String name)
	{
		catName.sendKeys(name);
	}
	
	@FindBy(id="exccp.INTr.INTrReq_CB")
	private WebElement AllowIntrusionInitiationAndBypassOfDiversion;
	public WebElement getAllowIntrusionInitiationAndBypassOfDiversion()
	{
		return AllowIntrusionInitiationAndBypassOfDiversion;
	}
	
	@FindBy(id="exccp.INTr.INTrRec_CB")
	private WebElement AllowReceptionOfIntrusionAndCallWaitingTone;
	public WebElement getAllowReceptionOfIntrusionAndCallWaitingTone()
	{
		return AllowReceptionOfIntrusionAndCallWaitingTone;
	}
	
	@FindBy(id="exccp.CAB")
	private WebElement AutomaticCallBBackCharacteristics;
	public WebElement getAutomaticCallBBackCharacteristics()
	{
		return AutomaticCallBBackCharacteristics;
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
	private WebElement OfferedTimer;
	public WebElement getOfferedTimer()
	{
		return OfferedTimer;
	}
	public void setOfferedTimer(String time)
	{
		OfferedTimer.sendKeys(time);
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
	private WebElement AllowIndividualDoNotDisturb;
	public WebElement getAllowIndividualDoNotDisturb()
	{
		return AllowIndividualDoNotDisturb;
	}
	
	@FindBy(id="exccp.ECF_CB")
	private WebElement AllowExternalFollowMe;
	public WebElement getAllowExternalFollowMe()
	{
		return AllowExternalFollowMe;
	}
	
	@FindBy(id="exccp.divEx.divIC_CB")
	private WebElement AllowDirectDiversionToACommonDiverteePosition;
	public WebElement getAllowDirectDiversionToACommonDiverteePosition()
	{
		return AllowDirectDiversionToACommonDiverteePosition;
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
	
	@FindBy(id="exccp.intO")
	private WebElement OriginIsAnInternalExtension;
	public WebElement getOriginIsAnInternalExtension()
	{
		return OriginIsAnInternalExtension;
	}
	
	@FindBy(id="exccp.pubO")
	private WebElement OriginIsAPublicExternalLine;
	public WebElement getOriginIsAPublicExternalLine()
	{
		return OriginIsAPublicExternalLine;
	}
	
	@FindBy(id="exccp.priO")
	private WebElement OriginIsAPrivateExternalLine;
	public WebElement getOriginIsAPrivateExternalLine()
	{
		return OriginIsAPrivateExternalLine;
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
	
	@FindBy(name="onCancelButton")
	private WebElement CancelButton;
	public WebElement getCancelButton()
	{
		return CancelButton;
	}
	
	@FindBy(id="viewThis0_img")
	private WebElement Cat0View;
	public WebElement getCat0View()
	{
		return Cat0View;
	}
	
	@FindBy(id="viewThis1_img")
	private WebElement Cat1View;
	public WebElement getCat1View()
	{
		return Cat1View;
	}
	
	@FindBy(id="viewThis2_img")
	private WebElement Cat2View;
	public WebElement getCat2View()
	{
		return Cat2View;
	}
	
	@FindBy(id="viewThis3_img")
	private WebElement Cat3View;
	public WebElement getCat3View()
	{
		return Cat3View;
	}
	
	@FindBy(id="viewThis4_img")
	private WebElement Cat4View;
	public WebElement getCat4View()
	{
		return Cat4View;
	}
	
	@FindBy(id="viewThis5_img")
	private WebElement Cat5View;
	public WebElement getCat5View()
	{
		return Cat5View;
	}
	
}
