package snm_pom_classes;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SystemHardwareMediaGateway 
{
	public SystemHardwareMediaGateway(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(name="onAddButton")
	private WebElement addButton;
	
	public WebElement getAddButton()
	{
		return addButton;
	}
	
	@FindBy(name="range")
	private WebElement range;
	
	public WebElement getViewMediaGatewayInputRange()
	{
		return range;
	}
	
	public void setViewMediaGatewayInputRange(String rangeValue)
	{
		range.sendKeys(rangeValue);
	}
	
	@FindBy(name="onViewRangeButton")
	private WebElement mediaGatewayViewButton;
	
	public WebElement getMediaGatewayViewButton()
	{
		return mediaGatewayViewButton;
	}
	
	@FindBy(id="selectedMGWType1")
	private WebElement selectMediaServer;
	
	public WebElement getSelectMediaServer()
	{
		return selectMediaServer;
	}
	
	@FindBy(id="selectedMGWType0")
	private WebElement selectMediaGateway;
	
	public WebElement getSelectMediaGateway()
	{
		return selectMediaGateway;
	}
	
	@FindBy(id="onNextButtonTopID")
	private WebElement nextButton;
	
	public WebElement getNextButton()
	{
		return nextButton;
	}
	
	@FindBy(id="addMediaGateway_VOs.mediaGateway_VO[0].mediaGatewayControlData.controlLAN0")
	private WebElement controlData;
	
	public WebElement getControlData()
	{
		return controlData;
	}
	
	public void setControlData(String controlDataValue)
	{
		controlData.sendKeys(controlDataValue);
	}
	
	@FindBy(id="addMediaGateway_VOs.mediaGateway_VO[0].mediaGatewayControlData.defaultGatewayLAN0")
	private WebElement defaultGatewayValue;
	
	public WebElement getDefaultGatewayBalue()
	{
		return defaultGatewayValue;
	}
	
	public void setDefaultGatewayValue(String defaultGatewayValue)
	{
		this.defaultGatewayValue.sendKeys(defaultGatewayValue);
	}
	
	@FindBy(id="addMediaGateway_VOs.mediaGateway_VO[0].mediaGatewayControlData.symbolicName")
	private WebElement symbolicName;
	
	public WebElement getSymbolicName()
	{
		return symbolicName;
	}
	
	public void setSymbolicName(String value)
	{
		symbolicName.sendKeys(value);
	}
	
	@FindBy(id="addMediaGateway_VOs.mediaGateway_VO[0].mediaGatewayMediaData[0].mediaLAN0")
	private WebElement mediaData;
	
	public WebElement getMediaData()
	{
		return mediaData;
	}
	
	public void setMediaData(String value)
	{
		mediaData.sendKeys(value);
	}
	
	@FindBy(id="onApplyButtonTopID")
	private WebElement applyButton;
	
	public WebElement getApplyButton()
	{
		return applyButton;
	}
	
	@FindBy(id="addMediaGateway_VOs.mediaGateway_VO[1].mediaGatewayIndividual")
	private WebElement MGW_Dropdown;
	
	public WebElement getMGW_Dropdown()
	{
		return MGW_Dropdown;
	}
	
	@FindBy(className="responseMessage")
	private WebElement reponseMessage;
	
	public WebElement getResponseMessage()
	{
		return reponseMessage;
	}
	
	@FindBy(xpath="//img[@src='/wbm/styles/Aastra/icons/Green_Dot.png']")
	private WebElement status;
	
	public WebElement getStatus()
	{
		return status;
	}
	
	@FindBy(className="okButton")
	private WebElement doneButton;
	
	public WebElement getDoneButton()
	{
		return doneButton;
	}
	
/*	@FindBy(id="onApplyButtonTopID")
	private WebElement applyButton;
	
	public WebElement getApplyButton()
	{
		return applyButton;
	}*/
}
