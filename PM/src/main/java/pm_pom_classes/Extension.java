package pm_pom_classes;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Extension 
{
	@FindBy(name="onAddButton")
	private WebElement addButton;

	public WebElement getAddButton() 
	{
		return addButton;
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

	public WebElement getSubmitButton() 
	{
		return submitButton;
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
	private WebElement singleExtensionDropDown;

	public WebElement getSingleExtensionDropDown() 
	{
		return singleExtensionDropDown;
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
	
}
