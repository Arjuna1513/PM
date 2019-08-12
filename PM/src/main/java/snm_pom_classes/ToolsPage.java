package snm_pom_classes;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ToolsPage 
{
	@FindBy(id="Command_Line")
	private WebElement commandLine;
	
	@FindBy(name="CommandLine")
	private WebElement cmdFrame;
	
	@FindBy(id="Quality_of_Service")
	private WebElement qualityofService;       
	
	@FindBy(id="Tracing")
	private WebElement signalTracing;
	
	@FindBy(name="commandField")
	private WebElement commandField;
	
	@FindBy(name="cmdApply")
	private WebElement cmdApply;
	
	public WebElement getCmdApply() 
	{
		return cmdApply;
	}

	public WebElement getCommandField() 
	{
		return commandField;
	}
	
	public WebElement getCommandLine() 
	{
		return commandLine;
	}
	
	public WebElement getcmdFrame() 
	{
		return cmdFrame;
	}

	public WebElement getQualityofService() 
	{
		return qualityofService;
	}

	public WebElement getSignalTracing() {
		return signalTracing;
	}

	public ToolsPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
}
