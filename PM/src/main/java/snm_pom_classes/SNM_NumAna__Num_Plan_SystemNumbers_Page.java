package snm_pom_classes;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SNM_NumAna__Num_Plan_SystemNumbers_Page 
{
	public SNM_NumAna__Num_Plan_SystemNumbers_Page(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	private String message = "Change operation successful";
	public String getMessage()
	{
		return message;
	}
	
	@FindBy(name="changeButton")
	private WebElement doneButton;
	public WebElement getDoneButton()
	{
		return doneButton;
	}
	
	@FindBy(className="responseMessage")
	private WebElement responseMessage;
	public WebElement getResponseMessage()
	{
		return responseMessage;
	}
	
	@FindBy(id="onApplyButtonTopID")
	private WebElement applyButton;
	public WebElement getApplyButton()
	{
		return applyButton;
	}
	public void setApplyButton(String value)
	{
		applyButton.sendKeys(value);
	}
	
	@FindBy(id="mySystemNumber_VO.internationalPrefix")
	private WebElement internationalPrefix;
	public WebElement getInternationalPrefix()
	{
		return internationalPrefix;
	}
	public void setInternationalPrefix(String value)
	{
		internationalPrefix.sendKeys(value);
	}
	
	@FindBy(id="mySystemNumber_VO.countryCode")
	private WebElement countryCode;
	public WebElement getCountryCode()
	{
		return countryCode;
	}
	public void setCountryCode(String value)
	{
		countryCode.sendKeys(value);
	}
	
	@FindBy(id="mySystemNumber_VO.nationalPrefix")
	private WebElement nationalPrefix;
	public WebElement getNationalPrefix()
	{
		return nationalPrefix;
	}
	public void setNationalPrefix(String value)
	{
		nationalPrefix.sendKeys(value);
	}
	
}
