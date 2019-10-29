package snm_pom_classes;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SNM_Telephony_Extensions_CSP_Page 
{
	public SNM_Telephony_Extensions_CSP_Page(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(name="onAddButton")
	private WebElement addButton;
	public WebElement getAddButton()
	{
		return addButton;
	}
	
	@FindBy(name="onNextButton")
	private WebElement nextButton;
	public WebElement getNextButton()
	{
		return nextButton;
	}
	
	@FindBy(name="onChangeSelected")
	private WebElement changeButton;
	public WebElement getChangeButton()
	{
		return changeButton;
	}
	
	@FindBy(name="onRemoveSelected")
	private WebElement removeButton;
	public WebElement getRemoveButton()
	{
		return removeButton;
	}
	
	@FindBy(name="templateName")
	private WebElement cspTemplateNameDropDown;
	public WebElement getCSPTemplateNameDropDown()
	{
		return cspTemplateNameDropDown;
	}
	
	@FindBy(xpath="//a[contains(text(),'Manage Templates')]")
	private WebElement CSPManageTemplatesLink;
	public WebElement getManageTemplatesLink()
	{
		return CSPManageTemplatesLink;
	}
	
	@FindBy(xpath="(//input[@name='multiStepBackButton'])[2]")
	private WebElement manageTemplatesContinueButton;
	public WebElement getManageTemplatesContinueButton()
	{
		return manageTemplatesContinueButton;
	}
	
	@FindBy(name="onNewTemplateButton")
	private WebElement TemplateAddButton;
	public WebElement getTemplateAddButton()
	{
		return TemplateAddButton;
	}
	
	@FindBy(name="onUploadTemplateButton")
	private WebElement uploadTemplateButton;
	public WebElement getUploadTemplateButton()
	{
		return uploadTemplateButton;
	}
	
	@FindBy(name="newTemplateName")
	private WebElement uploadCSPTemplateName;
	public WebElement getUploadCSPTemplateName()
	{
		return uploadCSPTemplateName;
	}
	
	@FindBy(id="myCommonServiceProfiles_VO.GESPP[0].CSP.CSPName")
	private WebElement CSPNameForAddingCSPUsingCSPAsTemplate;
	public WebElement getCSPNameForAddingCSPUsingCSPAsTemplate()
	{
		return CSPNameForAddingCSPUsingCSPAsTemplate;
	}
	
	@FindBy(name="templateFile")
	private WebElement browseButton;
	public WebElement getBrowseButton()
	{
		return browseButton;
	}
	
	@FindBy(name="onApplyButton")
	private WebElement uploadCSPApplyButton;
	public WebElement getUploadCSPApplyButton()
	{
		return uploadCSPApplyButton;
	}
	
	@FindBy(name="onApplyButton")
	private WebElement CSPApplyButton;
	public WebElement getCSPApplyButton()
	{
		return CSPApplyButton;
	}
	
	@FindBy(className="responseMessage")
	private WebElement uploadTemplateResponseMessage;
	public WebElement getUploadTemplateResponseMessage()
	{
		return uploadTemplateResponseMessage;
	}
	
	@FindBy(name="okbutton")
	private WebElement doneButton;
	public WebElement getDoneButton()
	{
		return doneButton;
	}
	
	@FindBy(id="myCommonServiceProfiles_VO.GESPP[0].CSP.CUST.CUSTName")
	private WebElement CSPCustomerDropDown;
	public WebElement getCSPCustomerDropDown()
	{
		return CSPCustomerDropDown;
	}
	
	@FindBy(id="myCommonServiceProfiles_VO.GESPP[0].CSP.CSPName")
	private WebElement CSPName;
	public WebElement getCSPName()
	{
		return CSPName;
	}
	public void setCSPName(String name)
	{
		CSPName.sendKeys(name);
	}
	
	@FindBy(id="myCommonServiceProfiles_VO.GESPP[0].CSP.CSPNumber")
	private WebElement CSPNumberDropDown;
	public WebElement getCSPNumberDropDown()
	{
		return CSPNumberDropDown;
	}
	
	@FindBy(id="myCommonServiceProfiles_VO.GESPP[0].pres")
	private WebElement numberPresentationRestriction;
	public WebElement getNumberPresentationRestrictionDropDown()
	{
		return numberPresentationRestriction;
	}
	
	@FindBy(id="myCommonServiceProfiles_VO.GESPP[0].PSTN_CB")
	private WebElement requestANumberFromPSTN;
	public WebElement getRequestANumberFromPSTN()
	{
		return requestANumberFromPSTN;
	}
	
	@FindBy(id="myCommonServiceProfiles_VO.GESPP[0].presPerCall_CB")
	private WebElement numPresentationRestrictionPermittedPerCall;
	public WebElement getNumPresentationRestrictionPermittedPerCall()
	{
		return numPresentationRestrictionPermittedPerCall;
	}
	
	@FindBy(id="myCommonServiceProfiles_VO.GESPP[0].neverFromPSTN_CB")
	private WebElement neverDisplayNumberFropmPSTN;
	public WebElement getNeverDisplayNumberFromPSTN()
	{
		return neverDisplayNumberFropmPSTN;
	}
	
	@FindBy(id="myCommonServiceProfiles_VO.GESPP[0].EMESw_CB")
	private WebElement blockEmergencySwitchingCharacteristics;
	public WebElement getBlockEmergencySwitchingCharacteristics()
	{
		return blockEmergencySwitchingCharacteristics;
	}
	
	@FindBy(id="myCommonServiceProfiles_VO.GESPP[0].DIDC_CB")
	private WebElement blockDirectIndiallingCharacteristics;
	public WebElement getBlockDirectIndiallingCharacteristics()
	{
		return blockDirectIndiallingCharacteristics;
	}
	
	@FindBy(id="myCommonServiceProfiles_VO.GESPP[0].ABBTC")
	private WebElement getCommonAbbNumTrafficClass;
	public WebElement getGetCommonAbbNumTrafficClassDropDown()
	{
		return getCommonAbbNumTrafficClass;
	}
	
	@FindBy(id="myCommonServiceProfiles_VO.GESPP[0].CAB")
	private WebElement automaticCallBackCharacteristics;
	public WebElement getAutomaticCallBackCharacteristicsDropDown()
	{
		return automaticCallBackCharacteristics;
	}
	
	@FindBy(id="myCommonServiceProfiles_VO.GESPP[0].callWaiting.CAWReq_CB")
	private WebElement allowCallwaitingToneInitiation;
	public WebElement getAllowCallwaitingToneInitiation()
	{
		return allowCallwaitingToneInitiation;
	}
	
	@FindBy(id="myCommonServiceProfiles_VO.GESPP[0].callWaiting.CAWProB")
	private WebElement callwaitingToneReceptionBParty;
	public WebElement getCallwaitingToneReceptionBPartyDropDown()
	{
		return callwaitingToneReceptionBParty;
	}
	
	@FindBy(id="myCommonServiceProfiles_VO.GESPP[0].callWaiting.CAWProC_CB")
	private WebElement callwaitingToneReceptionCParty;
	public WebElement getCallwaitingToneReceptionCParty()
	{
		return callwaitingToneReceptionCParty;
	}
	
	@FindBy(id="myCommonServiceProfiles_VO.GESPP[0].ICL")
	private WebElement intrusionCapabilityLevel;
	public WebElement getIntrusionCapabilityLevelDropDown()
	{
		return intrusionCapabilityLevel;
	}
	
	@FindBy(id="myCommonServiceProfiles_VO.GESPP[0].IPL")
	private WebElement intrusionProtectionLevelDropDown;
	public WebElement getIntrusionProtectionLevelDropDown()
	{
		return intrusionProtectionLevelDropDown;
	}
	
	@FindBy(id="myCommonServiceProfiles_VO.GESPP[0].MCT_CB")
	private WebElement allowMaliciousCallTracing;
	public WebElement getAllowMaliciousCallTracing()
	{
		return allowMaliciousCallTracing;
	}
	
	@FindBy(id="myCommonServiceProfiles_VO.GESPP[0].MMW_CB")
	private WebElement manualMsgWaiting;
	public WebElement getManualMsgWaiting()
	{
		return manualMsgWaiting;
	}
	
	@FindBy(id="myCommonServiceProfiles_VO.GESPP[0].callMet0")
	private WebElement callMeteringCategoryPerRoute;
	public WebElement getCallMeteringCategoryPerRoute()
	{
		return callMeteringCategoryPerRoute;
	}
	
	@FindBy(id="myCommonServiceProfiles_VO.GESPP[0].callMet1")
	private WebElement callMeteringCategoryPerExtension;
	public WebElement getCallMeteringCategoryPerExtension()
	{
		return callMeteringCategoryPerExtension;
	}
	
	@FindBy(id="myCommonServiceProfiles_VO.GESPP[0].reqMFC_CB")
	private WebElement allowAReqNumberFromMFC;
	public WebElement getAllowAReqNumberFromMFC()
	{
		return allowAReqNumberFromMFC;
	}
	
	@FindBy(id="myCommonServiceProfiles_VO.GESPP[0].subCharge_CB")
	private WebElement allowASubscriberCharged;
	public WebElement getAllowASubscriberCharged()
	{
		return allowASubscriberCharged;
	}
	
	@FindBy(id="myCommonServiceProfiles_VO.GESPP[0].DND.DNDInd_CB")
	private WebElement allowIndividualDND;
	public WebElement getAllowIndividualDND()
	{
		return allowIndividualDND;
	}
	
	@FindBy(id="myCommonServiceProfiles_VO.GESPP[0].hospitality")
	private WebElement hospitalityClassOfExtensionDropDown;
	public WebElement getHospitalityClassOfExtensionDropDown()
	{
		return hospitalityClassOfExtensionDropDown;
	}
	
	@FindBy(id="myCommonServiceProfiles_VO.GESPP[0].collectCall_CB")
	private WebElement allowIncomingCollectCalls;
	public WebElement getAllowIncomingCollectCalls()
	{
		return allowIncomingCollectCalls;
	}
	
	@FindBy(id="myCommonServiceProfiles_VO.GESPP[0].uncForceGateway_CB")
	private WebElement allowForceGatewayCalls;
	public WebElement getAllowForceGatewayCalls()
	{
		return allowForceGatewayCalls;
	}
	
	@FindBy(id="myCommonServiceProfiles_VO.GESPP[0].SMS_CB")
	private WebElement serviceLicenseShortMessageService;
	public WebElement getServiceLicenseShortMessageService()
	{
		return serviceLicenseShortMessageService;
	}
	
	@FindBy(id="myCommonServiceProfiles_VO.GESPP[0].freeSeating_CB")
	private WebElement serviceLicenseFreeSeating;
	public WebElement getServiceLicenseFreeSeating()
	{
		return serviceLicenseFreeSeating;
	}
	
	
	@FindBy(id="myCommonServiceProfiles_VO.GESPP[0].callOfferedState_CB")
	private WebElement allowExternalControlledCallDistribution;
	public WebElement getAllowExternalControlledCallDistribution()
	{
		return allowExternalControlledCallDistribution;
	}
	
	@FindBy(id="myCommonServiceProfiles_VO.GESPP[0].callOfferedTimer")
	private WebElement offeredTimer;
	public WebElement getOfferedTimer()
	{
		return offeredTimer;
	}
	public void setOfferedTimer(String value)
	{
		offeredTimer.sendKeys(value);
	}
	
	
	@FindBy(id="myCommonServiceProfiles_VO.GESPP[0].commonAuthCodeState_CB")
	private WebElement enableCommonAuthCode;
	public WebElement getEnableCommonAuthCode()
	{
		return enableCommonAuthCode;
	}
	
	
	@FindBy(id="myCommonServiceProfiles_VO.GESPP[0].MTSBusyOption_CB")
	private WebElement allowFreeOnBusy;
	public WebElement getAllowFreeOnBusy()
	{
		return allowFreeOnBusy;
	}
	
	
	@FindBy(id="myCommonServiceProfiles_VO.GESPP[0].silentIntrusion_CB")
	private WebElement extendedServicesInIntrusionState;
	public WebElement getExtendedServicesInIntrusionState()
	{
		return extendedServicesInIntrusionState;
	}
	
	@FindBy(id="myCommonServiceProfiles_VO.GESPP[0].callListDeactivation_CB")
	private WebElement CallListDeactivationForbidden;
	public WebElement getCallListDeactivationForbidden()
	{
		return CallListDeactivationForbidden;
	}
	
	@FindBy(id="myCommonServiceProfiles_VO.GESPP[0].GDND_CB")
	private WebElement allowActivationDeactivationOfDND;
	public WebElement getAllowActivationDeactivationOfDND()
	{
		return allowActivationDeactivationOfDND;
	}
	
	@FindBy(id="myCommonServiceProfiles_VO.GESPP[0].automaticAnswer_CB")
	private WebElement automaticAnswer;
	public WebElement getAutomaticAnswer()
	{
		return automaticAnswer;
	}
	
	
	@FindBy(id="myCommonServiceProfiles_VO.GESPP[0].TRF.TRFPub_CB")
	private WebElement reqTransferPermissionOfPublicTrunk;
	public WebElement getReqTransferPermissionOfPublicTrunk()
	{
		return reqTransferPermissionOfPublicTrunk;
	}
	
	@FindBy(id="myCommonServiceProfiles_VO.GESPP[0].TRF.TRFRec_CB")
	private WebElement transferReception;
	public WebElement getTransferReception()
	{
		return transferReception;
	}
	
	@FindBy(id="myCommonServiceProfiles_VO.GESPP[0].TRF.TRFInt_CB")
	private WebElement permittedToTransFerCallsToIntrudedParty;
	public WebElement getPermittedToTransFerCallsToIntrudedParty()
	{
		return permittedToTransFerCallsToIntrudedParty;
	}
	
	@FindBy(id="myCommonServiceProfiles_VO.GESPP[0].forcedDisconnect")
	private WebElement forcedDisconnectTimer;
	public WebElement getForcedDisconnectTimer()
	{
		return forcedDisconnectTimer;
	}
	public void setForcedDisconnectTimer(String value)
	{
		forcedDisconnectTimer.sendKeys(value);
	}
	
	@FindBy(id="myCommonServiceProfiles_VO.GESPP[0].answerHandling_CB")
	private WebElement answerHandledByExternalApplication;
	public WebElement getAnswerHandledByExternalApplication()
	{
		return answerHandledByExternalApplication;
	}
	
	@FindBy(id="myCommonServiceProfiles_VO.GESPP[0].CNNLog_CB")
	private WebElement enableCentralisedNameAndNumberLog;
	public WebElement getEnableCentralisedNameAndNumberLog()
	{
		return enableCentralisedNameAndNumberLog;
	}
	
	
	@FindBy(id="myCommonServiceProfiles_VO.GESPP[0].logOffRestriction")
	private WebElement logOffRestrict;
	public WebElement getLogOffRestrict()
	{
		return logOffRestrict;
	}
	
	@FindBy(className="responseMessage")
	private WebElement responseMsg;
	public WebElement getResponseMessage()
	{
		return responseMsg;
	}
	
	@FindBy(id="myCommonServiceProfiles_VO.GESPP[0].ECF_CB")
	private WebElement allowExternalFollowMe;
	public WebElement getAllowExternalFollowMe()
	{
		return allowExternalFollowMe;
	}
	
	@FindBy(id="myCommonServiceProfiles_VO.GESPP[0].DIV.divFM_CB")
	private WebElement allowFollowMe;
	public WebElement getAllowFollowMe()
	{
		return allowFollowMe;
	}
	
	
	@FindBy(id="myCommonServiceProfiles_VO.GESPP[0].DIV.divBP_CB")
	private WebElement allowDivBypass;
	public WebElement getAllowDivBypass()
	{
		return allowDivBypass;
	}
	
	@FindBy(id="myCommonServiceProfiles_VO.GESPP[0].intO")
	private WebElement originIsInternalExtension;
	public WebElement getOriginIsInternalExtensionDropDown()
	{
		return originIsInternalExtension;
	}
	
	
	@FindBy(id="myCommonServiceProfiles_VO.GESPP[0].pubO")
	private WebElement originIsPubliExternalLine;
	public WebElement getOriginIsPubliExternalLine()
	{
		return originIsPubliExternalLine;
	}
	
	@FindBy(id="myCommonServiceProfiles_VO.GESPP[0].priO")
	private WebElement originIsPrivateExternalLine;
	public WebElement getOriginIsPrivateExternalLine()
	{
		return originIsPrivateExternalLine;
	}
	
	
	@FindBy(id="myCommonServiceProfiles_VO.GESPP[0].FMAutoBypass_CB")
	private WebElement allowAutoBypassOfFollowMeToSMS;
	public WebElement getAllowAutoBypassOfFollowMeToSMS()
	{
		return allowAutoBypassOfFollowMeToSMS;
	}
	
	
	@FindBy(id="myCommonServiceProfiles_VO.GESPP[0].EFMAutoBypass_CB")
	private WebElement allowAutoBypassOfExternalFollowMeToSMS;
	public WebElement getAllowAutoBypassOfExternalFollowMeToSMS()
	{
		return allowAutoBypassOfExternalFollowMeToSMS;
	}
	
	
	@FindBy(id="myCommonServiceProfiles_VO.GESPP[0].DIV.divIC")
	private WebElement allowDirectDiversionToDropDown;
	public WebElement getAllowDirectDiversionToDropDown()
	{
		return allowDirectDiversionToDropDown;
	}
	
	@FindBy(id="myCommonServiceProfiles_VO.GESPP[0].DIV.divB_CB")
	private WebElement allowDirectDiversionOnBusy;
	public WebElement getAllowDirectDiversionOnBusy()
	{
		return allowDirectDiversionOnBusy;
	}
	
	@FindBy(id="myCommonServiceProfiles_VO.GESPP[0].DIV.divNoA_CB")
	private WebElement allowDirectDiversionOnNoAnswer;
	public WebElement getAllowDirectDiversionOnNoAnswer()
	{
		return allowDirectDiversionOnNoAnswer;
	}
	
	@FindBy(id="myCommonServiceProfiles_VO.GESPP[0].DIV.multiDirDiv_CB")
	private WebElement allowMultiDirectoryDiversion;
	public WebElement getAllowMultiDirectoryDiversion()
	{
		return allowMultiDirectoryDiversion;
	}
	
	@FindBy(id="myCommonServiceProfiles_VO.GESPP[0].RPDivEx.RPFM_CB")
	private WebElement allowRemoteProgrammingOnFollowMe;
	public WebElement getAllowRemoteProgrammingOnFollowMe()
	{
		return allowRemoteProgrammingOnFollowMe;
	}
	
	@FindBy(id="myCommonServiceProfiles_VO.GESPP[0].RPDivEx.RPECF_CB")
	private WebElement allowRemoteProgrammingOnECF;
	public WebElement getAllowRemoteProgrammingOnECF()
	{
		return allowRemoteProgrammingOnECF;
	}
	
	@FindBy(id="myCommonServiceProfiles_VO.GESPP[0].RPDivEx.RPDivNoA_CB")
	private WebElement allowRemoteProgrammingOnNoReply;
	public WebElement getAllowRemoteProgrammingOnNoReply()
	{
		return allowRemoteProgrammingOnNoReply;
	}
	
	@FindBy(id="myCommonServiceProfiles_VO.GESPP[0].RPDivEx.RPDivB_CB")
	private WebElement allowRemoteProgrammingOnBusy;
	public WebElement getAllowRemoteProgrammingOnBusy()
	{
		return allowRemoteProgrammingOnBusy;
	}
	
	@FindBy(id="myCommonServiceProfiles_VO.GESPP[0].RPDivEx.RPDivIC_CB")
	private WebElement allowRemoteProgrammingOnDirectDiversion;
	public WebElement getAllowRemoteProgrammingOnDirectDiversion()
	{
		return allowRemoteProgrammingOnDirectDiversion;
	}
	
	@FindBy(id="myCommonServiceProfiles_VO.GESPP[0].divOnOrigin_CB")
	private WebElement allowPrivateNetworkPartiesToChooseDiversion;
	public WebElement getAllowPrivateNetworkPartiesToChooseDiversion()
	{
		return allowPrivateNetworkPartiesToChooseDiversion;
	}
	
	@FindBy(xpath="//a[contains(text(),'Number Presentation Category')]")
	private WebElement numberPresentationCategory;
	public WebElement getNumberPresentationCategory()
	{
		return numberPresentationCategory;
	}
	
	@FindBy(xpath="//a[contains(text(),'Traffic Category')]")
	private WebElement trafficCategory;
	public WebElement getTrafficCategory()
	{
		return trafficCategory;
	}
	
	@FindBy(xpath="//a[contains(text(),'Service Category')]")
	private WebElement serviceCategory;
	public WebElement getServiceCategory()
	{
		return serviceCategory;
	}
	
	@FindBy(xpath="//a[contains(text(),'Template Name')]")
	private WebElement templateName;
	public WebElement getTemplateNameLink()
	{
		return templateName;
	}
	
	@FindBy(xpath="//a[contains(text(),'Call Diversion Category')]")
	private WebElement callDiversionCategory;
	public WebElement getCallDiversionCategory()
	{
		return callDiversionCategory;
	}
	
	@FindBy(xpath="//a[contains(text(),'Routing Category')]")
	private WebElement routingCategory;
	public WebElement getRoutingCategory()
	{
		return routingCategory;
	}
	
	@FindBy(name="Apply")
	private WebElement editCSPApplyButton;
	public WebElement getEditCSPApplyButton()
	{
		return editCSPApplyButton;
	}
}
