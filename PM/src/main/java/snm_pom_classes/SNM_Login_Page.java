package snm_pom_classes;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SNM_Login_Page 
{
	@FindBy(name="userId")
	private WebElement snmUserNameField;
	
	@FindBy(name="password")
	private WebElement snmPasswordField;

	@FindBy(name="login")
	private WebElement snmUserLoginBtn;
	
	@FindBy(xpath="//font[contains(text(), 'Version')]")
	private WebElement versionText;
	
	@FindBy(xpath="//font[contains(text(),'Mitel Networks')]")
	private WebElement copyRightText;
	
	@FindBy(xpath="//span[text()='The user or password is incorrect. Please try again.']")
	private WebElement errorMessage;
	
	public WebElement getErrorMessage() 
	{
		return errorMessage;
	}

	public SNM_Login_Page(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	public WebElement getSnmUserNameField() 
	{
		return snmUserNameField;
	}

	public void setSnmUserNameField(String userName) 
	{
		snmUserNameField.sendKeys(userName);
	}
	
	public WebElement getSnmPasswordField()
	{
		return snmPasswordField;
	}
	
	public void setSnmPasswordField(String password)
	{
		snmPasswordField.sendKeys(password);
	}

	public WebElement getSnmUserLoginBtn() 
	{
		return snmUserLoginBtn;
	}

	public WebElement getVersionText() {
		return versionText;
	}

	public WebElement getCopyRightText() {
		return copyRightText;
	}

	public void snm_login(String usrName, String usrPassword)
	{
		setSnmUserNameField(usrName);
		getSnmPasswordField().click();
		setSnmPasswordField(usrPassword);
		getSnmUserLoginBtn().click();
	}
}
