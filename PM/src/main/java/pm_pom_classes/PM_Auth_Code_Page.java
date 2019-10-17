package pm_pom_classes;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PM_Auth_Code_Page 
{
	public PM_Auth_Code_Page(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="editItem_authorizationcode")
	private WebElement authCodeButton;
	
	public WebElement getAuthCodeButton()
	{
		return authCodeButton;
	}
	
	@FindBy(name="onAddButton")
	private WebElement authCodeAddButton;
	
	public WebElement getAuthCodeAddButton()
	{
		return authCodeAddButton;
	}
	
	@FindBy(xpath="(//input[@name='multiStepBackButton'])[2]")
	private WebElement continueButton;
	
	public WebElement getContinueButton()
	{
		return continueButton;
	}
	
	@FindBy(name="Apply")
	private WebElement authPageApplyButton;
	
	public WebElement getAuthPageApplyButton()
	{
		return authPageApplyButton;
	}
	
	@FindBy(xpath="(//input[@name='onCancelButton'])[2]")
	private WebElement authCodeCancelButton;
	
	public WebElement getAuthCodeCancelButton()
	{
		return authCodeCancelButton;
	}
	
	@FindBy(name="myAuthorizationCode_VO.AUCOPs.indAUCOP[0].AUTH")
	private WebElement authCodeTextBox;
	
	public WebElement getAuthCodeTextBox()
	{
		return authCodeTextBox;
	}
	
	public void setAuthCodeTextBox(String authCode)
	{
		authCodeTextBox.sendKeys(authCode);
	}
	
	@FindBy(name="myHashType")
	private WebElement hashTypeDropDown;
	
	public WebElement gethashTypeDropDown()
	{
		return hashTypeDropDown;
	}
	
	@FindBy(name="myAuthorizationCode_VO.AUCOPs.indAUCOP[0].CILCode")
	private WebElement cilCode;
	
	public WebElement getCilCode()
	{
		return cilCode;
	}
	
	public void setCilCode(String cilCodeValue)
	{
		cilCode.sendKeys(cilCodeValue);
	}
	
	@FindBy(name="myCustGroupName")
	private WebElement custGroupDropDown;
	
	public WebElement getCustGroupDropDown()
	{
		return custGroupDropDown;
	}
	
	@FindBy(id="onApplyButtonTopID")
	private WebElement continueButtonToServiceSummaryPage;
	
	public WebElement getContinueButtonToServiceSummaryPage()
	{
		return continueButtonToServiceSummaryPage;
	}
	
	@FindBy(id="onApplyButtonTopID")
	private WebElement nextButtonToMicollabConfigPage;
	
	public WebElement getNextButtonToMicollabConfigPage()
	{
		return nextButtonToMicollabConfigPage;
	}
	
	@FindBy(id="micollabRole")
	private WebElement micollabRoleDropDown;
	
	public WebElement getMicollabRoleDropDown()
	{
		return micollabRoleDropDown;
	}
}
