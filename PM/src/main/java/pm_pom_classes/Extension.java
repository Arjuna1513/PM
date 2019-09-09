package pm_pom_classes;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Extension 
{
	
	public Extension(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(name="onAddButton")
	private WebElement addButton;

	public WebElement getAddButton() 
	{
		return addButton;
	}
	
	@FindBy(name="onRemoveSelected")
	private WebElement removeButton;
	
	public WebElement getRemoveButton()
	{
		return removeButton;
	}
	
	@FindBy(linkText="About")
	private WebElement aboutlink;
	
	public WebElement getAboutLink()
	{
		return aboutlink;
	}
	
	@FindBy(name="okbutton")
	private WebElement doneButton;
	
	public WebElement getDoneButton()
	{
		return doneButton;
	}
	
	@FindBy(className="responseMessage")
	private WebElement responseMessage;
	
	public String getResponseMessage()
	{
		return responseMessage.getText().trim();
	}
	
	@FindBy(name="onNextButton")
	private WebElement nextButton;
	
	public WebElement getNextButton()
	{
		return nextButton;
	}

	@FindBy(name="onApplyButton")
	private WebElement applyButton;
	
	public WebElement getApplyButton()
	{
		return applyButton;
	}
	
	@FindBy(id="onApplyButtonTopID")
	private WebElement applyButtonAfterAssigningDigitalExtension;
	
	public WebElement getApplyButtonAfterAssigningDigitalExtension()
	{
		return applyButtonAfterAssigningDigitalExtension;
	}
	
	//MiVoice MX-ONE: drop-down
	@FindBy(name="range")
	private WebElement miVoiceMXONEDropDown;

	public WebElement getMiVoiceMXONEDropDown() 
	{
		return miVoiceMXONEDropDown;
	}

	//Extension type drop-down
	@FindBy(name="rangeFields[0]")
	private WebElement extensionTypeDropDownHomePage;

	public WebElement getExtensionTypeDropDownHomePage() 
	{
		return extensionTypeDropDownHomePage;
	}
	
	//enter extension number text box
	@FindBy(name="rangeFields[1]")
	private WebElement enterExtensionNumberTextBox;

	public void setEnterExtensionNumberTextBox(String extensionNumber) 
	{
		enterExtensionNumberTextBox.sendKeys(extensionNumber);
	}

	public WebElement getEnterExtensionNumberTextBox() 
	{
		return enterExtensionNumberTextBox;
	}
	
	//enter equipment position
	@FindBy(name="rangeFields[2]")
	private WebElement enterEquipmentPosition;

	public void setEnterEquipmentPosition(String equipmentPosition) 
	{
		enterEquipmentPosition.sendKeys(equipmentPosition);
	}

	public WebElement getEnterEquipmentPosition() 
	{
		return enterEquipmentPosition;
	}
	
	//view button
	@FindBy(name="onViewRangeButton")
	private WebElement viewButton;

	public WebElement getViewRangeButton() 
	{
		return viewButton;
	}
	
	//view limit drop_down
	@FindBy(name="viewLimit")
	private WebElement viewLimit;

	public WebElement getViewLimit() 
	{
		return viewLimit;
	}
	
	//templatedrop link
	@FindBy(linkText="Manage Templates")
	private WebElement manageTemplatesLink;

	public WebElement getManageTemplatesLink() 
	{
		return manageTemplatesLink;
	}

	
	//add new template button
	@FindBy(name="onNewTemplateButton")
	private WebElement addNewTemplateButton;

	public WebElement getAddNewTemplateButton() 
	{
		return addNewTemplateButton;
	}
	
	//onUploadTemplateButton link
	@FindBy(name="onUploadTemplateButton")
	private WebElement uploadTemplateButton;

	public WebElement getUploadTemplateButton() 
	{
		return uploadTemplateButton;
	}
	
	//submit button
	@FindBy(name="multiStepBackButton")
	private WebElement submitButton;

	public WebElement getTemplateSubmitButton() 
	{
		return submitButton;
	}
	
	@FindBy(id="newTemplateName")
	private WebElement templateName;
	
	public WebElement getTemplateName()
	{
		return templateName;
	}
	
	@FindBy(id="addItem_extension")
	private WebElement createAndAssignExtensionToUser;

	public WebElement getCreateAndAssignExtensionToUser() 
	{
		return createAndAssignExtensionToUser;
	}
	
	@FindBy(name="templateName")
	private WebElement extensionHomePageTemplateDropDown;
	
	public WebElement getExtensionHomePageTemplateDropDown()
	{
		return extensionHomePageTemplateDropDown;
	}
	
	public void setTempplateName(String name)
	{
		templateName.sendKeys(name);
	}
	//mivoice mxone drop-down 
	@FindBy(name="nodeName")
	private WebElement mivoiceMxoneDropDown;

	public WebElement getMivoiceMxoneDropDown() 
	{
		return mivoiceMxoneDropDown;
	}
	
	//extensionType drop down
	@FindBy(id="extensionType")
	private WebElement extensionType;

	public WebElement getExtensionType() 
	{
		return extensionType;
	}
	
	//multiple extensions check box
	@FindBy(id="multipleExtensions_CB")
	private WebElement multipleExtensionsCheckBox;

	public WebElement getMultipleExtensionsCheckBox() 
	{
		return multipleExtensionsCheckBox;
	}
	
	//multiple extensions range dropdown
	@FindBy(id="multipleExtensionsRange")
	private WebElement multipleExtensionsRangeDropDown;

	public WebElement getMultipleExtensionsRangeDropDown() 
	{
		return multipleExtensionsRangeDropDown;
	}
	
	//extensions drop down
	@FindBy(id="vacDir")
	private WebElement selectExtensionsRange;

	public WebElement getSelectExtensionsRange() 
	{
		return selectExtensionsRange;
	}
	
	//extensions drop down
	@FindBy(id="myIPExtension_VO.DIR")
	private WebElement singleExtensionValue;

	public WebElement getSingleExtensionValue() 
	{
		return singleExtensionValue;
	}
	
	public void setSingleExtensionValue(String value)
	{
		singleExtensionValue.sendKeys(value);
	}
	
	@FindBy(id="myVirtualExtension_VO.DIR")
	private WebElement virtualExtensionTextBox;
	
	public WebElement getVirtualExtensionTextBox()
	{
		return virtualExtensionTextBox;
	}
	
	public void setVirtualExtensionTextBox(String value)
	{
		virtualExtensionTextBox.sendKeys(value);
	}
	
	@FindBy(id="myVirtualExtension_VO.NIINP.name1")
	private WebElement virtualExtFirstName;
	
	public WebElement getVirtualExtFirstName()
	{
		return virtualExtFirstName;
	}
	
	public void setVirtualExtFirstName(String value)
	{
		virtualExtFirstName.sendKeys(value);
	}
	
	
	@FindBy(id="myVirtualExtension_VO.NIINP.name2")
	private WebElement virtualExtLastName;
	
	public WebElement getVirtualExtLastName()
	{
		return virtualExtLastName;
	}
	
	public void setVirtualExtLastName(String value)
	{
		virtualExtLastName.sendKeys(value);
	}
	
	@FindBy(id="myVirtualExtension_VO.GEDIP.SPOPT")
	private WebElement virtualExtHotlineDelayedHotlineDropDown;
	
	public WebElement getVirtualExtHotlineDelayedHotlineDropDown()
	{
		return virtualExtHotlineDelayedHotlineDropDown;
	}
	
	@FindBy(id="myMultiTerminalExtension_VO.DIR")
	private WebElement multiTerminalExtensionTextBox;
	
	public void setMultiTerminalExtensionTextBox(String value)
	{
		multiTerminalExtensionTextBox.sendKeys(value);
	}
	
	public WebElement getMultiTerminalExtensionDropDown()
	{
		return multiTerminalExtensionTextBox;
	}
	
	@FindBy(id="myMultiTerminalExtension_VO.NIINP.name1")
	private WebElement multiTerminalFirstName;
	
	public WebElement getMultiTerminalFirstName()
	{
		return multiTerminalFirstName;
	}
	
	public void setMultiTerminalFirstName(String value)
	{
		multiTerminalFirstName.sendKeys(value);
	}
	
	
	@FindBy(id="myMultiTerminalExtension_VO.NIINP.name2")
	private WebElement multiTerminalLastName;
	
	public WebElement getMultiTerminalLastName()
	{
		return multiTerminalLastName;
	}
	
	public void setMultiTerminalLastName(String value)
	{
		multiTerminalLastName.sendKeys(value);
	}
	
	@FindBy(id="myMultiTerminalExtension_VO.GEDIP.LIM.LIMNumber")
	private WebElement multiTerminalServerDropDown;
	
	public WebElement getMultiTerminalServerDropDown()
	{
		return multiTerminalServerDropDown;
	}
	
	@FindBy(id="myVirtualExtension_VO.GEDIP.NDNUM")
	private WebElement virtualExtHotlineDelayedHotlineTextBox;
	public void SetVirtualExtHotlineDelayedHotlineTextBox(String value)
	{
		virtualExtHotlineDelayedHotlineTextBox.sendKeys(value);
	}
	
	@FindBy(id="myIPExtension_VO.DIR")
	private WebElement singleExtensionDropDown;
	
	public WebElement getSingleExtensionDropDown()
	{
		return singleExtensionDropDown;
	}
	
	@FindBy(id="virtualExtensionType")
	private WebElement virtualExtensionTypeDropDown;
	
	public WebElement getVirtualExtensionTypeDropDown()
	{
		return virtualExtensionTypeDropDown;
	}
	
	//extensions range input
	@FindBy(id="extensionRange")
	private WebElement extensionsRangeInput;

	public void setExtensionsRangeInput(String extensionsRange) 
	{
		extensionsRangeInput.sendKeys(extensionsRange);;
	}

	public WebElement getExtensionsRangeInput() 
	{
		return extensionsRangeInput;
	}
	
	//extension description
	@FindBy(name="myIPExtension_VO.extDescription")
	private WebElement extensionDescription;

	public void setExtensionDescription(String description) 
	{
		extensionsRangeInput.sendKeys(description);
	}

	//server drop-down
	@FindBy(id="myIPExtension_VO.GEDIP.LIM.LIMNumber")
	private WebElement serverDropDown;

	public WebElement getServerDropDown() 
	{
		return serverDropDown;
	}
	
	@FindBy(id="myVirtualExtension_VO.GEDIP.LIM.LIMNumber")
	private WebElement virtualExtServerDropDown;

	public WebElement getVirtualExtServerDropDown() 
	{
		return virtualExtServerDropDown;
	}
	
	//customer drop-down
	@FindBy(id="myCustGroupName")
	private WebElement myCustGroupName;

	public WebElement getMyCustGroupName() 
	{
		return myCustGroupName;
	}
	
	//CSP drop-down
	@FindBy(id="myCSPName")
	private WebElement myCSPNameDropDown;

	public WebElement getMyCSPNameDropDown() 
	{
		return myCSPNameDropDown;
	}
	
	//BOSS-SECRETARY drop-down
	@FindBy(id="myIPExtension_VO.GEDIP.BSEC")
	private WebElement bossSecretaryDropDown;

	public WebElement getBossSecretaryDropDown() 
	{
		return bossSecretaryDropDown;
	}
	
	//edit auth code button
	@FindBy(id="editItem_authorizationcode")
	private WebElement authCodeButton;

	public WebElement getAuthCodeButton() 
	{
		return authCodeButton;
	}
	
	//PEN List button
	@FindBy(id="editItem_personalnumber")
	private WebElement PENListButton;

	public WebElement getPENListButton() 
	{
		return PENListButton;
	}
	
	//PhoneType drop-down
	@FindBy(id="myIPExtPhoneType")
	private WebElement phoneTypeDropDown;

	public WebElement getPhoneTypeDropDown() 
	{
		return phoneTypeDropDown;
	}
	
	
	//Function Keys Button
	@FindBy(id="changeItem_ipfunctionkeys")
	private WebElement functionKeysButton;

	public WebElement getFunctionKeysButton() 
	{
		return functionKeysButton;
	}
	
	//No alias Number radio button
	@FindBy(id="aliasNumberType0")
	private WebElement noAliasNumber;

	public WebElement getNoAliasNumber() 
	{
		return noAliasNumber;
	}
	
	//Individual Alias Number
	@FindBy(id="aliasNumberType1")
	private WebElement individualAliasNumber;

	public WebElement getIndividualAliasNumber() 
	{
		return individualAliasNumber;
	}
	
	//Group Alias Number
	@FindBy(id="aliasNumberType2")
	private WebElement groupAliasNumber;

	public WebElement getGroupAliasNumber() 
	{
		return groupAliasNumber;
	}
	
	//Extension group 1
	@FindBy(id="myExtnHuntGroups[0]")
	private WebElement extensionGroup1;

	public void setExtensionGroup1(String huntGrpNumber) 
	{
		extensionGroup1.sendKeys(huntGrpNumber);
	}

	public WebElement getExtensionGroup1() 
	{
		return extensionGroup1;
	}
	
	//Group Alias Number
	@FindBy(id="myExtnHuntGroups[1]")
	private WebElement extensionGroup2;

	public WebElement getExtensionGroup2() 
	{
		return extensionGroup2;
	}
	
	public void setExtensionGroup2(String huntGrpNumber) 
	{
		extensionGroup2.sendKeys(huntGrpNumber);
	}

	//Extension group 2
	@FindBy(id="myExtnHuntGroups[2]")
	private WebElement extensionGroup3;

	public WebElement getExtensionGroup3() 
	{
		return extensionGroup3;
	}
	
	public void setExtensionGroup3(String huntGrpNumber) 
	{
		extensionGroup3.sendKeys(huntGrpNumber);
	}
	
	//Extension group 3
	@FindBy(id="myExtnHuntGroups[3]")
	private WebElement extensionGroup4;

	public WebElement getExtensionGroup4() 
	{
		return extensionGroup4;
	}
	
	public void setExtensionGroup4(String huntGrpNumber) 
	{
		extensionGroup4.sendKeys(huntGrpNumber);
	}

	//group alias radio button 1
	@FindBy(xpath="(//input[@id='groupAliasIndex0'])[1]")
	private WebElement groupAliasRadioButton1;

	public WebElement getGroupAliasRadioButton1() 
	{
		return groupAliasRadioButton1;
	}
	
	//group alias radio button 2
	@FindBy(xpath="(//input[@id='groupAliasIndex0'])[2]")
	private WebElement groupAliasRadioButton2;

	public WebElement getGroupAliasRadioButton2() 
	{
		return groupAliasRadioButton2;
	}
	
	//group alias radio button 3
	@FindBy(xpath="(//input[@id='groupAliasIndex0'])[3]")
	private WebElement groupAliasRadioButton3;

	public WebElement getGroupAliasRadioButton3() 
	{
		return groupAliasRadioButton3;
	}
	
	//group alias radio button 4
	@FindBy(xpath="(//input[@id='groupAliasIndex0'])[4]")
	private WebElement groupAliasRadioButton4;

	public WebElement getGroupAliasRadioButton4() 
	{
		return groupAliasRadioButton4;
	}
	
	//call pick up group
	@FindBy(id="pickupGroupID")
	private WebElement pickUpGroupDropDown;

	public WebElement getPickUpGroupDropDown() 
	{
		return pickUpGroupDropDown;
	}
	
	//Group Do Not Disturn drop down
	@FindBy(id="myGroupBelongings_VO.donotDisturbGroup.GNAME")
	private WebElement groupDNDDropDown;

	public WebElement getGroupDNDDropDown() 
	{
		return groupDNDDropDown;
	}
	
	@FindBy(id="myIPExtension_VO.NIINP.name1")
	private WebElement firstName;
	
	public WebElement getFirstName()
	{
		return firstName;
	}
	
	public void setFirstName(String name)
	{
		firstName.sendKeys(name);
	}
	
	
	@FindBy(id="myIPExtension_VO.NIINP.name2")
	private WebElement lastName;
	
	public WebElement getLastName()
	{
		return lastName;
	}
	
	public void setLastName(String name)
	{
		lastName.sendKeys(name);
	}
	
	@FindBy(name="onAdvancedButton")
	private WebElement advanceButton;
	
	public WebElement getAdvanceButton()
	{
		return advanceButton;
	}
	
	@FindBy(id="myParallellRinging_VO.PLLNP.SDIR[0]")
	private WebElement secDirNum1;
	
	public WebElement getSecondaryDirNum1()
	{
		return secDirNum1;
	}
	
	public void setSecDirNum1(String number)
	{
		secDirNum1.sendKeys(number);
	}
		
	@FindBy(id="myParallellRinging_VO.PLLNP.SDIR[1]")
	private WebElement secDirNum2;
	
	public WebElement getSecondaryDirNum2()
	{
		return secDirNum2;
	}
	
	public void setSecDirNum2(String number)
	{
		secDirNum2.sendKeys(number);
	}
	
	@FindBy(name="Apply")
	private WebElement editPageApplyButton;
	
	public WebElement getEditPageApplyButton()
	{
		return editPageApplyButton;
	}
					
	@FindBy(xpath="//input[@id='myIPExtension_VO.GEDIP.enhancedServices_CB']")
	private WebElement thirdPartySIPClient;
	
	public WebElement getThirdPartySIPClient()
	{
		return thirdPartySIPClient;
	}
	
	@FindBy(id="myCPPGroup_CB")
	private WebElement extensionCallParkPool;
	
	public WebElement getExtensionCallParkPool()
	{
		return extensionCallParkPool;
	}
	
	@FindBy(id="myIPExtension_VO.GEDIP.freeOnSecondLine")
	private WebElement secondLineSetting;
	
	public WebElement getSecondLineSetting()
	{
		return secondLineSetting;
	}
	
	@FindBy(id="myIPExtension_VO.GEDIP.SPOPT")
	private WebElement phoneHotLineType;
	
	public WebElement getPhoneHotLineType()
	{
		return phoneHotLineType;
	}
	
	@FindBy(id="myIPExtension_VO.GEDIP.NDNUM")
	private WebElement hotLineNumber;
	
	public void setHotLineNumber(String value)
	{
		hotLineNumber.sendKeys(value);
	}
	
	@FindBy(xpath="//a[text()='Manage Templates']")
	private WebElement manageTemplates;
	
	public WebElement getManageTemplates()
	{
		return manageTemplates;
	}
	
	@FindBy(name="newTemplateName")
	private WebElement templateApplyButton;
	
	public WebElement getTemplateApplyButton()
	{
		return templateApplyButton;
	}
	
	@FindBy(id="myAnalogueExtension_VO.EXDDP.CAT.CATName")
	private WebElement commonCategoryDropDown;
	
	public WebElement getCommonCategoryDropDown()
	{
		return commonCategoryDropDown;
	}
	
	@FindBy(id="myDigitalExtension_VO.KSDDP.CAT.CATName")
	private WebElement digitalCommonCategoryDropDown;
	
	public WebElement getDigitalCommonCategoryDropDown()
	{
		return digitalCommonCategoryDropDown;
	}
	
	
	@FindBy(id="myAnalogueExtension_VO.DIR")
	private WebElement enterAnalogDirctoryNumber;
	
	public WebElement getEnterAnalogDirectorynumber()
	{
		return enterAnalogDirctoryNumber;
	}
	
	@FindBy(id="myDigitalExtension_VO.DIR")
	private WebElement enterDigitalExtensionNumber;
	
	public WebElement getEnterDigitalExtensionNumber()
	{
		return enterDigitalExtensionNumber;
	}
	
	@FindBy(id="myDigitalExtension_VO.DIR")
	private WebElement setDigitalExtensionNumber;
	
	public void setDigitalExtensionNumber(String value)
	{
		setDigitalExtensionNumber.sendKeys(value);
	}
	
	public void setEnterAnalogDirectoryNumber(String value)
	{
		enterAnalogDirctoryNumber.sendKeys(value);
	}
	
	@FindBy(id="myAnalogueExtension_VO.DIR")
	private WebElement analogDirectoryDropDown;
	
	public WebElement getAnalogDirctoryDropDown()
	{
		return analogDirectoryDropDown;
	}
	
	@FindBy(id="hardwareDesc")
	private WebElement equipmentPosition;
	
	public void setEquipmentPosition(String value)
	{
		equipmentPosition.sendKeys(value);
	}
	
	public WebElement getEquipmentPosition()
	{
		return equipmentPosition;
	}
	
	@FindBy(id="myAnalogueExtension_VO.SPEXP.nonDialledConnExtn")
	private WebElement analogHotLineDelayedHotLineDropDown;
	
	public WebElement getAnalogHotLineDelayedHotLineDropDown()
	{
		return analogHotLineDelayedHotLineDropDown;
	}
	
	@FindBy(id="myAnalogueExtension_VO.SPEXP.nonDialledConnNumber")
	private WebElement analogHotLineDelayedHotLineTextBox;
	
	public void setAnalogHotLineDelayedHotLineTextBox(String value)
	{
		analogHotLineDelayedHotLineTextBox.sendKeys(value);
	}
	
	@FindBy(id="myAnalogueExtension_VO.NIINP.name1")
	private WebElement analogFirstname;
	
	public void setAnalogFirstName(String value)
	{
		analogFirstname.sendKeys(value);
	}
	
	public WebElement getAnalogFirstname()
	{
		return analogFirstname;
	}
	
	@FindBy(id="myAnalogueExtension_VO.NIINP.name2")
	private WebElement analogLastname;
	
	public void setAnalogLastname(String value)
	{
		analogLastname.sendKeys(value);
	}
	
	public WebElement getAnalogLastname()
	{
		return analogLastname;
	}
	
	@FindBy(id="myDigitalExtension_VO.KSCAP.ITYPE")
	private WebElement digitalPhoneTypeDropDown;
	
	public WebElement getDigitalPhoneTypeDropDown()
	{
		return digitalPhoneTypeDropDown;
	}
	
	
	@FindBy(id="myDigitalExtension_VO.NIINP.name1")
	private WebElement digitalFirstname;
	
	public void setDigitalFirstname(String value)
	{
		digitalFirstname.sendKeys(value);
	}
	
	public WebElement getDigitalFirstname()
	{
		return digitalFirstname;
	}
	
	@FindBy(id="myDigitalExtension_VO.NIINP.name2")
	private WebElement digitalLastname;
	
	public void setDigitalLastname(String value)
	{
		digitalLastname.sendKeys(value);
	}
	
	public WebElement getDigitalLastname()
	{
		return digitalLastname;
	}
	
	@FindBy(id="myDigitalExtension_VO.KSCAP.agentPos_CB")
	private WebElement digitalAgentPosition;
	
	public WebElement getDigitalAgentPosition()
	{
		return digitalAgentPosition;
	}
	
	@FindBy(id="myDigitalExtension_VO.SPEXP.nonDialledConnExtn")
	private WebElement hotLineDelayedHotLineDropDown;
	
	public WebElement getHotLineDelayedHotLineDropDown()
	{
		return hotLineDelayedHotLineDropDown;
	}
	
	@FindBy(id="myDigitalExtension_VO.SPEXP.nonDialledConnNumber")
	private WebElement hotLineNumberTextBox;
	
	public WebElement getHotLineNumberTextBox()
	{
		return hotLineNumberTextBox;
	}
	
	public void setHotLineNumberTextBox(String value)
	{
		 hotLineNumberTextBox.sendKeys(value);;
	}
	
	@FindBy(name="onSwapSelectedButton")
	private WebElement swapButton;
	
	public WebElement getSwapButton()
	{
		return swapButton;
	}
	
	@FindBy(id="addItem_ipTerminal")
	public WebElement addIPButtonForMultiTerminal;
	
	public WebElement getAddIPButtonForMultiTerminal()
	{
		return addIPButtonForMultiTerminal;
	}
	
	
	@FindBy(id="addItem_dectTerminal")
	public WebElement addDectButtonForMultiTerminal;
	
	public WebElement getAddDectButtonForMultiTerminal()
	{
		return addDectButtonForMultiTerminal;
	}
	
	
	@FindBy(id="addItem_mobileOverISDNTerminal")
	public WebElement addMobileExtButtonForMultiTerminal;
	
	public WebElement getAddMobileExtButtonForMultiTerminal()
	{
		return addMobileExtButtonForMultiTerminal;
	}
	
	
	@FindBy(id="addItem_mobileOverSIPTerminal")
	public WebElement addSipRemoteButtonForMultiTerminal;
	
	public WebElement getAddSipRemoteButtonForMultiTerminal()
	{
		return addSipRemoteButtonForMultiTerminal;
	}
	
	
	@FindBy(id="addItem_sipTerminal")
	public WebElement addSipAutoRegTerminalButtonForMultiTerminal;
	
	public WebElement getAddSipAutoRegTerminalButtonForMultiTerminal()
	{
		return addSipAutoRegTerminalButtonForMultiTerminal;
	}
	
	
	@FindBy(id="myMultiTerminalExtension_VO.GEDIP.SPOPT")
	private WebElement hotLineDelayedHotLineForMultiTerminal;
	
	public WebElement getHotLineDelayedHotLineForMultiTerminal()
	{
		return hotLineDelayedHotLineForMultiTerminal;
	}
	
	
	@FindBy(id="portNumber")
	private WebElement sipAutoPortNumber;
	
	public void setSipAutoPortNumber(String value)
	{
		sipAutoPortNumber.sendKeys(value);
	}
	
	@FindBy(id="myIpexp.TERMID")
	private WebElement sipAutoPortIdentity;
	
	public void setSipAutoPortIdentity(String value)
	{
		sipAutoPortIdentity.sendKeys(value);
	}
	
	@FindBy(id="myIpexp.URI")
	private WebElement sipAutoURI;
	
	public void setSIPAutoURI(String value)
	{
		sipAutoURI.sendKeys(value);
	}
	
	@FindBy(id="myMultiTerminalExtension_VO.REEXP.defMobNum")
	private WebElement mobileExtRemoteNumber;
	
	public void setMobileExtRemoteNumber(String value)
	{
		mobileExtRemoteNumber.sendKeys(value);
	}
	
	@FindBy(id="myRemNum")
	private WebElement mobileExtReceivedANumber;
	
	public void setMobileExtReceivedANumber(String value)
	{
		mobileExtReceivedANumber.sendKeys(value);
	}
}
