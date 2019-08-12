package pm_pom_classes;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PM_Login_Page 
{

	@FindBy(name="userId")
	private WebElement userLoginTextBox;

	public void setUserLoginTextBox(String userID) 
	{
		userLoginTextBox.sendKeys(userID);
	}
	
	
	@FindBy(name="password")
	private WebElement userPasswordTextBox;
	
	public WebElement getUserPasswordTextBox() {
		return userPasswordTextBox;
	}

	public void setUserPasswordTextBox(String userPassword) 
	{
		userPasswordTextBox.sendKeys(userPassword);
	}
	
	
	@FindBy(name="login")
	private WebElement userLoginButton;
	
	
	
	public WebElement getUserLoginButton() {
		return userLoginButton;
	}
	
	@FindBy(xpath="//span[text()='The user or password is incorrect. Please try again.']")
	private WebElement loginErrorMessage;

	public WebElement getLoginErrorMessage() {
		return loginErrorMessage;
	}

	public PM_Login_Page(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	
}
