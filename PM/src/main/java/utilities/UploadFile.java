package utilities;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class UploadFile 
{
	//when there is no type="file" attribute in html tag
	public void uploadFileUsingRobot(String path)
	{
		StringSelection select = new StringSelection(path);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(select, null);
		Robot r = null;
		try 
		{
			 r = new Robot();
		} 
		catch (AWTException e) 
		{
			e.printStackTrace();
		}
		r.keyPress(KeyEvent.VK_CONTROL);
		r.keyPress(KeyEvent.VK_V);
		r.keyRelease(KeyEvent.VK_V);
		r.keyRelease(KeyEvent.VK_CONTROL);
		r.keyPress(KeyEvent.VK_ENTER);
		r.keyRelease(KeyEvent.VK_ENTER);				
	}
	
	public void uploadUsingAutoIt(String uploadFilePath, String fileName)
	{
		try 
		{
			Runtime.getRuntime().exec(uploadFilePath+"\\"+fileName);
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
}
