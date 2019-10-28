package snm_pom_classes;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SNM_Telephony_AccountCode_Page 
{
	public SNM_Telephony_AccountCode_Page(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(name="onRemoveSelected")
	private WebElement deleteButton;
	public WebElement getDeleteButton()
	{
		return deleteButton;
	}
	
	@FindBy(name="onAddButton")
	private WebElement addButton;
	public WebElement getAddButton()
	{
		return addButton;
	}
	
	@FindBy(name="range")
	private WebElement range;
	public WebElement getRange()
	{
		return range;
	}
	public void setRange(String value)
	{
		range.sendKeys(value);
	}
	
	@FindBy(name="onViewRangeButton")
	private WebElement viewButton;
	public WebElement getViewButton()
	{
		return viewButton;
	}
	

	@FindBy(name="Apply")
	private WebElement applyButton;
	public WebElement getApplyButton()
	{
		return applyButton;
	}
	
	@FindBy(name="Apply")
	private WebElement customerApplyButton;
	public WebElement getCustomerApplyButton()
	{
		return customerApplyButton;
	}
	
	@FindBy(name="onCancelButton")
	private WebElement cancelButton;
	public WebElement getCancelButton()
	{
		return cancelButton;
	}
	
	@FindBy(name="onCancelButton")
	private WebElement customerCancelButton;
	public WebElement getCustomerCancelButton()
	{
		return customerCancelButton;
	}
	
	@FindBy(name="okbutton")
	private WebElement doneButton;
	public WebElement getDoneButton()
	{
		return doneButton;
	}
	
	@FindBy(id="myACO")
	private WebElement accCode;
	public WebElement getAccountCode()
	{
		return accCode;
	}
	public void setAccountCodeField(String value)
	{
		accCode.sendKeys(value);
	}
	
	@FindBy(id="myACOFirst")
	private WebElement accCodeFirst;
	public WebElement getAccountCodeFirst()
	{
		return accCodeFirst;
	}
	public void setAccountCodeFirstField(String value)
	{
		accCodeFirst.sendKeys(value);
	}
	
	@FindBy(id="myACOLast")
	private WebElement accCodeLast;
	public WebElement getAccountCodeLast()
	{
		return accCodeLast;
	}
	public void setAccountCodeLastField(String value)
	{
		accCodeLast.sendKeys(value);
	}
	
	@FindBy(id="multiStepButton_custgroup_add")
	private WebElement addCustomerButton;
	public WebElement getAddCustomerButton()
	{
		return addCustomerButton;
	}
	
	@FindBy(id="myAccountCode_VO.AOCOP[0].CUST.CUSTName")
	private WebElement customerDropDown;
	public WebElement getCustomerDropDown()
	{
		return customerDropDown;
	}
	@FindBy(xpath="//span[contains(@class,'responseMessage')]")
	private WebElement responseMsg;
	public WebElement getResponseMsg()
	{
		return responseMsg;
	}
	
	@FindBy(id="custGroup_vo.CUST.CUSTName")
	private WebElement customerName;
	public WebElement getCustomerName()
	{
		return customerName;
	}
	public void setCustomerName(String name)
	{
		customerName.sendKeys(name);
	}
	
	@FindBy(id="custGroup_vo.CUST.CUSTNumber")
	private WebElement customerNumber;
	public WebElement getCustomerNumber()
	{
		return customerNumber;
	}
	public void setCustomerNumber(String name)
	{
		customerNumber.sendKeys(name);
	}
}
