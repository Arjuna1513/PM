
package snm_pom_classes;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SNM_Telephony_Groups_Customer_Page 
{
	public SNM_Telephony_Groups_Customer_Page(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(name="onAddButton")
	private WebElement custAddButton;
	public WebElement getCustAddButton()
	{
		return custAddButton;
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
	
	
	@FindBy(name="Apply")
	private WebElement customerApplyButton;
	public WebElement getCustomerApplyButton()
	{
		return customerApplyButton;
	}
	
	@FindBy(name="okbutton")
	private WebElement doneButton;
	public WebElement getDoneButton()
	{
		return doneButton;
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
