package snm_pom_classes;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SNM_ServicesPage 
{
	@FindBy(linkText="Connections")
	private WebElement connections;
	
	@FindBy(linkText="Messages")
	private WebElement messages;
	
	@FindBy(linkText="Voice Announcements")
	private WebElement voiceAnnouncements;
	
	@FindBy(linkText="Media")
	private WebElement media;
	
	@FindBy(linkText="Branch Office")
	private WebElement branchOffice;
	
	@FindBy(linkText="Routing Server")
	private WebElement routingServer;
	
	@FindBy(linkText="CSTA Server")
	private WebElement CSTAServer;
	
	@FindBy(linkText="Incoming Call Handling ")
	private WebElement incomingCallHandling ;
	
	@FindBy(linkText="Enterprise Gateway")
	private WebElement enterpriseGateway;
	
	public SNM_ServicesPage(WebDriver driver) 
	{
		PageFactory.initElements(driver, this);
	}
	
}
