package snm_pom_classes;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SNM_SystemHardware 
{
	public SNM_SystemHardware(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="Media_Gateway")
	private WebElement mediaGateway;
	
	public WebElement getMediaGateway()
	{
		return mediaGateway;
	}
	
	@FindBy(id="Media_Gateway_LoadSharing")
	private WebElement mediaGatewayLoadSharing;
	
	public WebElement getMediaGatewayLoadSharing()
	{
		return mediaGatewayLoadSharing;
	}
	
	@FindBy(id="SystemEquipConfig")
	private WebElement equipmentConfig;
	
	public WebElement getEquipmentConfig()
	{
		return equipmentConfig;
	}
	
	@FindBy(id="SystemEquipData")
	private WebElement equipmentData;
	
	public WebElement getEquipmentData()
	{
		return equipmentData;
	}
	
	@FindBy(id="SystemEquipVacanies")
	private WebElement equipmentVacanies;
	
	public WebElement getEquipmentVacanies()
	{
		return equipmentVacanies;
	}
	
	@FindBy(id="HardwareDescription")
	private WebElement hardwareDescription;
	
	public WebElement getHardwareDescription()
	{
		return hardwareDescription;
	}
	
	@FindBy(id="TimeInfo")
	private WebElement timeInfo;
	
	public WebElement getTimeInfo()
	{
		return timeInfo;
	}
	
	@FindBy(id="Blocking")
	private WebElement blocking;
	
	public WebElement getBlocking()
	{
		return blocking;
	}
	
	@FindBy(id="BoardList")
	private WebElement boardList;
	
	public WebElement getBoardList()
	{
		return boardList;
	}
	
	@FindBy(id="TransportMedia")
	private WebElement transportMedia;
	
	public WebElement getTransportMedia()
	{
		return transportMedia;
	}
}
