package utilities;

import java.io.File;
import java.io.IOException;
import org.codehaus.plexus.util.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Take_Screenshot 
{
	public void get_Screenshot(WebDriver driver, String fileName) throws IOException
	{
		TakesScreenshot ts = (TakesScreenshot)driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File dest = new File(System.getProperty("user.dir").replace("\\", "\\"+"\\")+"\\ScreenShots\\"+fileName+".png");
		System.out.println(System.getProperty("user.dir"));
		FileUtils.copyFile(src, dest);
	}
}
