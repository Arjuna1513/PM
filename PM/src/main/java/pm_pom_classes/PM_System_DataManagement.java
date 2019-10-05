package pm_pom_classes;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PM_System_DataManagement 
{
	public PM_System_DataManagement(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	//Import Tab
	@FindBy(xpath="//a[contains(text(),'Import')]")
	private WebElement importTabButton;
	
	public WebElement getImportTabButton()
	{
		return importTabButton;
	}
	
	@FindBy(name="onImportButton")
	private WebElement onImportButton;
	
	public WebElement getOnImportButton()
	{
		return onImportButton;
	}
	
	@FindBy(xpath="//a[contains(text(),'Manage Templates')]")
	private WebElement manageTemplatesLink;
	
	public WebElement getManageTemplates()
	{
		return manageTemplatesLink;
	}
	
	@FindBy(id="onNextButtonTopID")
	private WebElement nextButton;
	
	public WebElement getNextButton()
	{
		return nextButton;
	}
	
	@FindBy(id="importData1")
	private WebElement userRadioButton;
	
	public WebElement getUserRadioButton()
	{
		return userRadioButton;
	}
	
	@FindBy(name="importDeptDataFile")
	private WebElement deptBrowseButton;
	
	public WebElement getDeptBrowseButton()
	{
		return deptBrowseButton;
	}
	
	
	@FindBy(name="importUserDataFile")
	private WebElement userBrowseButton;
	
	public WebElement getuserBrowseButton()
	{
		return userBrowseButton;
	}
	
	@FindBy(id="myImport_VO.importData[0].CSVInfo.delimiter")
	private WebElement delimiterDropDown;
	
	public WebElement getDelimiterDropDown()
	{
		return delimiterDropDown;
	}
	
	@FindBy(id="onApplyButtonTopID")
	private WebElement applyButton;
	
	public WebElement getApplyButton()
	{
		return applyButton;
	}
	
	@FindBy(name="onApplyButton")
	private WebElement applyButtonBottom;
	
	public WebElement getApplyButtonBottom()
	{
		return applyButtonBottom;
	}
	
	@FindBy(className="responseMessage")
	private WebElement responseMsg;
	
	public WebElement getResponseMsg()
	{
		return responseMsg;
	}
	
	@FindBy(className="okButton")
	private WebElement doneButton;
	
	public WebElement getDoneButton()
	{
		return doneButton;
	}
	
	
	//BackUpRestore
	@FindBy(name="onBackupButton")
	private WebElement backUpButton;
	
	public WebElement getBackUpButton()
	{
		return backUpButton;
	}
	
	@FindBy(id="Subsystem_Comparision")
	private WebElement compareSubSystemTab;
	
	public WebElement getCompareSubSystemTab()
	{
		return compareSubSystemTab;
	}
	
	@FindBy(id="Backup_Restore")
	private WebElement backUpRestoreTab;
	
	public WebElement getBackUpRestoreTab()
	{
		return backUpRestoreTab;
	}
	
	@FindBy(id="Export")
	private WebElement exportTab;
	
	public WebElement getExportTab()
	{
		return exportTab;
	}
	
	@FindBy(id="Scheduling")
	private WebElement scheduleTab;
	
	public WebElement getScheduleTab()
	{
		return scheduleTab;
	}
	
	@FindBy(id="Active_Directory")
	private WebElement activeDirTab;
	
	public WebElement getActiveDirTab()
	{
		return activeDirTab;
	}
	
	@FindBy(id="Config_Mirror_Scheduling")
	private WebElement configMirrorTab;
	
	public WebElement getConfigMirrorTab()
	{
		return configMirrorTab;
	}
}
