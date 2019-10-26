package base;

public interface AutoConstants 
{
	public static String chromeKey = "webdriver.chrome.driver";
	public static String firefoxKey = "webdriver.gecko.driver";
	public static String chromePath = System.getProperty("user.dir")+"/Driver/chromedriver.exe";
	public static String firefoxPath = System.getProperty("user.dir")+"/Driver/geckodriver.exe";
	public static String excelPath = System.getProperty("user.dir")+"/src/main/java/myTestData/TestData.xlsx";
}
