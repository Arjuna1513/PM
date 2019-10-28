package snm_pom_classes;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SNM_External_Length_Page 
{
	public SNM_External_Length_Page(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(name="onAddButton")
	private WebElement addButton;
	public WebElement getAddButton()
	{
		return addButton;
	}
	
	@FindBy(id="exnl_length_vo.NANLP.EXL")
	private WebElement externalNumberLengthField;
	public WebElement getExternalNumberLengthField()
	{
		return externalNumberLengthField;
	}
	
	public void setExternalNumberLength(String value)
	{
		externalNumberLengthField.sendKeys(value);
	}
	
	@FindBy(id="exnl_length_vo.NANLP.MIN")
	private WebElement minimumLengthField;
	public WebElement getMinimumLengthField()
	{
		return minimumLengthField;
	}
	public void setMinimumLength(String value)
	{
		minimumLengthField.sendKeys(value);
	}
	
	@FindBy(id="exnl_length_vo.NANLP.MAX")
	private WebElement maximumLengthField;
	public WebElement getMaximumLengthField()
	{
		return maximumLengthField;
	}
	public void setMaximumLengthField(String value)
	{
		maximumLengthField.sendKeys(value);
	}
	
	@FindBy(name="Apply")
	private WebElement applyButton;
	public WebElement getApplyButton()
	{
		return applyButton;
	}
	
	@FindBy(name="okbutton")
	private WebElement doneButton;
	public WebElement getDoneButton()
	{
		return doneButton;
	}
	
	@FindBy(xpath="//span[contains(@class,'responseMessage')]")
	private WebElement responseMsg;
	
	public WebElement getResponseMsg()
	{
		return responseMsg;
	}
}
