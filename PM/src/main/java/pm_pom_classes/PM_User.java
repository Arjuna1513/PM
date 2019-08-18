package pm_pom_classes;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;



public class PM_User
{
	public static String addSuccessfulMsg = "Add operation successful for:";
	public PM_User(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//a/b[text()=' Logout']")
	private WebElement logOutLink;
	
	public WebElement getLogoutLink()
	{
		return logOutLink;
	}
	
	@FindBy(name="onAddButton")
	private WebElement addButton;
	
	@FindBy(name="range")
	private WebElement userSearchTextBox;
	
	@FindBy(name="onViewRangeButton")
	private WebElement onViewRangeButton;
	
	@FindBy(name="viewLimit")
	private WebElement viewExtensionsLimit;
	
	@FindBy(xpath="(//input[@name='onNextButton'])[2]")
	private WebElement nextButton;
	
	@FindBy(xpath="//input[@id='onApplyButtonTopID']")
	private WebElement applyButton;
	
	@FindBy(xpath="((//input[@name='onCancelButton'])[2]")
	private WebElement cancelButton;
	
	@FindBy(xpath="//button[@name='onViewSelectedButton']")
	private WebElement viewButton;
	
	public WebElement getViewButton()
	{
		return viewButton;
	}
	
	public WebElement getUserSearchTextBox() 
	{
		return userSearchTextBox;
	}

	public void setUserSearchTextBox(String text) 
	{
		userSearchTextBox.sendKeys(text);
	}

	public WebElement getAddButton() 
	{
		return addButton;
	}

	public WebElement getOnViewRangeButton() 
	{
		return onViewRangeButton;
	}

	public WebElement getViewExtensionsLimit() 
	{
		return viewExtensionsLimit;
	}

	public WebElement getNextButton() 
	{
		return nextButton;
	}

	public WebElement getApplyButton() 
	{
		return applyButton;
	}

	public WebElement getCancelButton() 
	{
		return cancelButton;
	}

	//checkbox for selecting multiple users
	@FindBy(name="selectItem")
	private WebElement selectCheckBox;
	
	public WebElement getSelectCheckBox() 
	{
		return selectCheckBox;
	}

	//edit user page apply button
	@FindBy(name="Apply")
	private WebElement editUserPageApplyButton;
	
	public WebElement getEditUserPageApplyButton()
	{
		return editUserPageApplyButton;
	}
	
	//results paging dropdown
	@FindBy(name="pageSelect")
	private WebElement resultsPagingDropDown;
	
	public WebElement getResultsPagingDropDown()
	{
		return resultsPagingDropDown;
	}
	
	//click on go after selecting results page from drop down
	@FindBy(name="onDirectJumpPageGo")
	private WebElement userResultsPagingGoButton;
	
	public WebElement getUsersResultsPagingGoButton()
	{
		return userResultsPagingGoButton;
	}
	
	//List of checkbox webElements
	@FindBys( {
		   @FindBy(name="selectItem")
			} )
	private List<WebElement> listOfCheckBoxes;
	
	public List<WebElement> getListOfCheckBoxes()
	{
		return listOfCheckBoxes;
	}
	
	//remove multiple-selected users
	@FindBy(name="onRemoveSelected")
	private WebElement removeSelected;
		
	public WebElement getRemoveSelected() 
	{
		return removeSelected;
	}
	
	//No data Found Message
	@FindBy(xpath="//b[text()=' No data found']")
	private WebElement noDataFoundMessage;
	
	public WebElement getNoDataFoundMessage() 
	{
		return noDataFoundMessage;
	}

	//first Name
	@FindBy(id="myUser_VO.firstName")
	private WebElement firstNamefield;
	
	public void setFirstNamefield(String firstName) 
	{
		firstNamefield.sendKeys(firstName);
	}
	
	public WebElement getFirstName()
	{
		return firstNamefield;
	}


	//last Name
	@FindBy(id="myUser_VO.lastName")
	private WebElement lastNamefield;
	
	
	public void setLastNamefield(String lastName) 
	{
		lastNamefield.sendKeys(lastName);
	}
	
	public WebElement getLastName()
	{
		return lastNamefield;
	}

	//alternate LastName
	@FindBy(id="myAltLastNames")
	private WebElement altLastName;
	
	public void setAltLastName(String lastName) 
	{
		altLastName.sendKeys(lastName);
	}
	
	//user ID
	@FindBy(id="myUser_VO.userId")
	private WebElement userIDField;
	
	public WebElement getUserIDField()
	{
		return userIDField;
	}
		
	public void setUserIDField(String userID) 
	{
		this.userIDField.sendKeys(userID);
	}

	//Done button
	@FindBy(className="okButton")
	private WebElement doneButton;
	
	public WebElement getDoneButton()
	{
		return doneButton;
	}
	//user password
	@FindBy(name="myUser_VO.password")
	private WebElement userPasswordField;
	
		
	public void setUserPasswordField(String password) 
	{
		this.userPasswordField.sendKeys(password);
	}


	//confirm password
	@FindBy(name="confirmPassword")
	private WebElement userConfirmPasswordField;
	
		
	public void setUserConfirmPasswordField(String confirmPassword) 
	{
		this.userConfirmPasswordField.sendKeys(confirmPassword);
	}


	//email id
	@FindBy(id="myUser_VO.email")
	private WebElement emailIDField;
		
	public void setEmailIDField(String emailID) 
	{
		this.emailIDField.sendKeys(emailID);
	}


	//SMS
	@FindBy(id="myUser_VO.SMS")
	private WebElement sms;
	
		
	public void setSms(String sms) 
	{
		this.sms.sendKeys(sms);
	}


	//Alternate First Name
	@FindBy(id="myAltFirstNames")
	private WebElement alternateFirstName;
	
		
	public void setAlternateFirstName(String alternateFN) 
	{
		this.alternateFirstName.sendKeys(alternateFN);
	}


	//keywords
	@FindBy(id="myKeywords")
	private WebElement keywords;
	
	
	public void setKeywords(String keywords) 
	{
		this.keywords.sendKeys(keywords);
	}


	//Business
	@FindBy(id="mySelectedUDFList[0]")
	private WebElement businessField;
	
	
	public void setBusinessField(String business) 
	{
		this.businessField.sendKeys(business);
	}

	//Business2
	@FindBy(id="mySelectedUDFList[1]")
	private WebElement business2;
	

	public void setBusiness2(String business2)
	{
		this.business2.sendKeys(business2);
	}
		
	//Mobile Phones
	@FindBy(id="mySelectedUDFList[2]")
	private WebElement mobilePhone;
	
		
	public void setMobilePhone(String mobilePhone) 
	{
		this.mobilePhone.sendKeys(mobilePhone);
	}


	//Mobile Phone2
	@FindBy(id="mySelectedUDFList[3]")
	private WebElement mobilePhone2;

	public void setMobilePhone2(String mobilePhone2) 
	{
		this.mobilePhone2.sendKeys(mobilePhone2);
	}
		
	//Department drop down
	@FindBy(id="mySelectedDepts")
	private WebElement selectDepartmentDropdown;
	
	
	public WebElement getSelectDepartmentDropdown() 
	{
		return selectDepartmentDropdown;
	}

	//move selected element to right pane
	@FindBy(id="listFilterAddButton_mySelectedDepts")
	private WebElement listFilterAddButton_mySelectedDepts;

	public WebElement getListFilterAddButton_mySelectedDepts() 
	{
		return listFilterAddButton_mySelectedDepts;
	}
	
	//response message
	@FindBy(className="responseMessage")
	private WebElement responseMessage;

	public WebElement getResponseMessage() 
	{
		return responseMessage;
	}
	
	//existing extension field.
	@FindBy(id="myExistingExtensions[0]")
	private WebElement existingExtensionField;

	public WebElement getExistingExtensionField() 
	{
		return existingExtensionField;
	}
	
	public void setExistingExtensionField(String extensionValue)
	{
		existingExtensionField.sendKeys(extensionValue);
	}
	
	//Template drop-down
	@FindBy(id="templateName")
	private WebElement templateNameDropDown;

	public WebElement getTemplateNameDropDown() 
	{
		return templateNameDropDown;
	}	
	
	//new extension for user
	@FindBy(id="addItem_extension")
	private WebElement createAndAssignExtensionToUser;

	public WebElement getCreateAndAssignExtensionToUser() 
	{
		return createAndAssignExtensionToUser;
	}
	
	//user edit symbol
	@FindBy(id="changeThis0_img")
	private WebElement userEditSymbol;
	
	public WebElement getUserEditSymbol()
	{
		return userEditSymbol;
	}
	
	//verifying the content of help window
	@FindBy(xpath="//p[contains(text(),'Users can be added one by one or be imported from files extracted "
			+ "from other user management systems.   When the system is installed a System Setup Admin is "
			+ "added. All users except the System Setup Admin can be  removed.')]")
	private WebElement userTextParagraph1;
	
	public WebElement getUserTextParagraph1()
	{
		return userTextParagraph1;
	}
	
	@FindBy(xpath="//p[contains(text(),'After installation it is recommended to create an extra System Setup "
			+ "Admin to keep as backup if something happens to the original System Setup Admin account, for example "
			+ "if the password is lost, otherwise the whole system must be reinstalled.')]")
	private WebElement userParagraph2;
	
	public WebElement getUserParagraph2()
	{
		return userParagraph2;
	}
	
	@FindBy(xpath="//p[contains(text(),'When a user is added, it is automatically assigned the security profile')]")
	private WebElement userParagraph3;
	
	public WebElement getUserParagraph3()
	{
		return userParagraph3;
	}
	
	@FindBy()
	private WebElement userParagraph4;
	
	public WebElement getUserParagraph4()
	{
		return userParagraph4;
	}
	
	@FindBy(xpath="//strong[contains(text(),'Administrator')]")
	private WebElement userParagraph5;
	
	public WebElement getUserParagraph5()
	{
		return userParagraph5;
	}
	
	@FindBy(xpath="//p[contains(text(),'The logged on administrator can only see the users, "
			+ "administrators and administrator users, that the logged on administrator has added. "
			+ "An administrator at a lower level could not see the administrator on a higher level in the hierarchy')]")
	private WebElement userParagraph6;
	
	public WebElement getUserParagraph6()
	{
		return userParagraph6;
	}
	
	@FindBy(xpath="//p[contains(text(),'Services, for example extensions and mailboxes, can be assigned to users. "
			+ "Services are provided by the configured subsystems. ')]")
	private WebElement userParagraph7;
	
	public WebElement getUserParagraph7()
	{
		return userParagraph7;
	}
	
	@FindBy(linkText="Help")
	private WebElement helpLink;
	
	public WebElement getHelpLink()
	{
		return helpLink;
	}
	
	@FindBy(name="onPrintSelected")
	private WebElement userPrintButton;
	
	public WebElement getUsrPrintButton()
	{
		return userPrintButton;
	}
	/*//subsystem selection drop-down extensionType
	@FindBy(id="nodeName")
	private WebElement subSystemSelectionDropdown;

	public WebElement getSubSystemSelectionDropdown() 
	{
		return subSystemSelectionDropdown;
	}
	
	//extensionType drop-down
	@FindBy(id="extensionType")
	private WebElement extensionTypeDropDown;

	public WebElement getExtensionTypeDropDown() 
	{
		return extensionTypeDropDown;
	}*/
}
