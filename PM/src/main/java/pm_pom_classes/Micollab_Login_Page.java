package pm_pom_classes;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Micollab_Login_Page 
{
	public Micollab_Login_Page(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(name="username")
	private WebElement loginField;
	
	public WebElement getLoginField()
	{
		return loginField;
	}
	
	public void setLoginField(String usrName)
	{
		loginField.sendKeys(usrName);
	}
	
	
	@FindBy(name="password")
	private WebElement password;
	
	public WebElement getPassword()
	{
		return password;
	}
	
	public void setPassword(String usrName)
	{
		password.sendKeys(usrName);
	}
	
	@FindBy(name="submit")
	private WebElement submitButton;
	
	public WebElement getSubmitButton()
	{
		return submitButton;
	}
	
}
