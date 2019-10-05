package utilities;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
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
	
	
	public void generate_BulkFile_For_Existing_Extension(String ext, String delimiter, int extCount, String usrName)
	{
        BufferedReader br = null;
        BufferedWriter br1 = null;
         
        try
        {
            br1 = new BufferedWriter(new FileWriter("./BulkFile.csv"));
            
            int x = 1;
            String str;
            String gmail;
            String equipmentPosition;
            String lcl_ext;
            String tempName = "IPTemp_SN_IPExtension";
            String AnalogTempName = "Digital_SN6_DigitalExtension";
            for(int i=1; i<=extCount; i++)
            {
            	if(i<10)
            	{
            		
            		lcl_ext = ext.substring(0, 19);
	            	str = usrName;
	            	str = str+x;
	            	lcl_ext=lcl_ext+x;
	            	gmail = "import@gmail.com";
	            	equipmentPosition = "1A-2-20-0";
	            	equipmentPosition = equipmentPosition+x;
	            	
	            	if(delimiter.equals(";"))
	            	{
	            		br1.write(str+";"+str+";"+str+";"+str+";SN:"+lcl_ext+";Mitel;SampleRecord;"+str+";Mitel1@123456;"+str+x+"@gmail.com");
	            	}
	            	else if(delimiter.equals(" "))
	            	{
	            		br1.write(str+" "+str+" "+str+" "+str+" SN:"+lcl_ext+" Mitel SampleRecord "+str+" Mitel1@123456 "+str+x+"@gmail.com");
	            	}
	            	else if(delimiter.equals(","))
	            	{
	            		br1.write(str+","+str+","+str+","+str+",SN:"+lcl_ext+",Mitel,SampleRecord,"+str+",Mitel1@123456,"+str+x+"@gmail.com");
	            	}
	            	
	            	x++;
	            	br1.newLine();
            	}
            	else if(i >= 10 && i < 100)
            	{
            		lcl_ext = ext.substring(0, 18);
            		str = usrName;
	            	str = str+x;
	            	lcl_ext=lcl_ext+x;
	            	gmail = "import@gmail.com";
	            	equipmentPosition = "1A-2-20-0";
	            	equipmentPosition = equipmentPosition+x;
	            	
	            	if(delimiter.equals(";"))
	            	{
	            		br1.write(str+";"+str+";"+str+";"+str+";SN:"+lcl_ext+";Mitel;SampleRecord;"+str+";Mitel1@123456;"+str+x+"@gmail.com");
	            	}
	            	else if(delimiter.equals(" "))
	            	{
	            		br1.write(str+" "+str+" "+str+" "+str+" SN:"+lcl_ext+" Mitel SampleRecord "+str+" Mitel1@123456 "+str+x+"@gmail.com");
	            	}
	            	else if(delimiter.equals(","))
	            	{
	            		br1.write(str+","+str+","+str+","+str+",SN:"+lcl_ext+",Mitel,SampleRecord,"+str+",Mitel1@123456,"+str+x+"@gmail.com");
	            	}
	            	
	            	x++;
	            	br1.newLine();
            	}
            	else if(i >= 100 && i < 1000)
            	{
            		lcl_ext = ext.substring(0, 17);
            		str = usrName;
	            	str = str+x;
	            	lcl_ext=lcl_ext+x;
	            	gmail = "import@gmail.com";
	            	equipmentPosition = "1A-2-20-0";
	            	equipmentPosition = equipmentPosition+x;
	            	
	            	if(delimiter.equals(";"))
	            	{
	            		br1.write(str+";"+str+";"+str+";"+str+";SN:"+lcl_ext+";Mitel;SampleRecord;"+str+";Mitel1@123456;"+str+x+"@gmail.com");
	            	}
	            	else if(delimiter.equals(" "))
	            	{
	            		br1.write(str+" "+str+" "+str+" "+str+" SN:"+lcl_ext+" Mitel SampleRecord "+str+" Mitel1@123456 "+str+x+"@gmail.com");
	            	}
	            	else if(delimiter.equals(","))
	            	{
	            		br1.write(str+","+str+","+str+","+str+",SN:"+lcl_ext+",Mitel,SampleRecord,"+str+",Mitel1@123456,"+str+x+"@gmail.com");
	            	}
	            	
	            	x++;
	            	br1.newLine();
            	}
            	else if(i == 1000)
            	{
            		lcl_ext = ext.substring(0, 16);
            		str = usrName;
	            	str = str+x;
	            	lcl_ext=lcl_ext+x;
	            	gmail = "import@gmail.com";
	            	equipmentPosition = "1A-2-20-0";
	            	equipmentPosition = equipmentPosition+x;
	            	
	            	if(delimiter.equals(";"))
	            	{
	            		br1.write(str+";"+str+";"+str+";"+str+";SN:"+lcl_ext+";Mitel;SampleRecord;"+str+";Mitel1@123456;"+str+x+"@gmail.com");
	            	}
	            	else if(delimiter.equals(" "))
	            	{
	            		br1.write(str+" "+str+" "+str+" "+str+" SN:"+lcl_ext+" Mitel SampleRecord "+str+" Mitel1@123456 "+str+x+"@gmail.com");
	            	}
	            	else if(delimiter.equals(","))
	            	{
	            		br1.write(str+","+str+","+str+","+str+",SN:"+lcl_ext+",Mitel,SampleRecord,"+str+",Mitel1@123456,"+str+x+"@gmail.com");
	            	}
	            	
	            	x++;
	            	br1.newLine();
            	}
            	
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally
        {
            try {
                br1.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
	}
	    
	
	public void generate_BulkFile_For_NEW_Extension(String ext, String delimiter, int extCount, String usrName)
	{
        BufferedReader br = null;
        BufferedWriter br1 = null;
         
        try
        {
            br1 = new BufferedWriter(new FileWriter("./BulkFile.csv"));
            
            int x = 1;
            String str;
            String gmail;
            String equipmentPosition;
            String lcl_ext;
            String tempName = "IPTemp_SN_IPExtension";
            String AnalogTempName = "Digital_SN6_DigitalExtension";
            for(int i=1; i<=extCount; i++)
            {
            	if(i<10)
            	{
            		
            		lcl_ext = ext.substring(0, 19);
            		str = usrName;
	            	str = str+x;
	            	lcl_ext=lcl_ext+x;
	            	gmail = "import@gmail.com";
	            	equipmentPosition = "1A-2-20-0";
	            	equipmentPosition = equipmentPosition+x;
	            	
	            	if(delimiter.equals(";"))
	            	{
	            		br1.write(str+";"+str+";"+str+";"+str+";"+tempName+":"+lcl_ext+";Mitel;Kanata;"+str+";Mitel1@123456;"+str+x+"@gmail.com");
	            	}
	            	else if(delimiter.equals(" "))
	            	{
	            		br1.write(str+" "+str+" "+str+" "+str+" "+tempName+":"+lcl_ext+" Mitel SampleRecord "+str+" Mitel1@123456 "+str+x+"@gmail.com");
	            	}
	            	else if(delimiter.equals(","))
	            	{
	            		br1.write(str+","+str+","+str+","+str+","+tempName+":"+lcl_ext+",Mitel,SampleRecord,"+str+",Mitel1@123456,"+str+x+"@gmail.com");
	            	}
	            	
	            	x++;
	            	br1.newLine();
            	}
            	else if(i >= 10 && i < 100)
            	{
            		lcl_ext = ext.substring(0, 18);
            		str = usrName;
	            	str = str+x;
	            	lcl_ext=lcl_ext+x;
	            	gmail = "import@gmail.com";
	            	equipmentPosition = "1A-2-20-0";
	            	equipmentPosition = equipmentPosition+x;
	            	
	            	if(delimiter.equals(";"))
	            	{
	            		br1.write(str+";"+str+";"+str+";"+str+";"+tempName+":"+lcl_ext+";Mitel;Kanata;"+str+";Mitel1@123456;"+str+x+"@gmail.com");
	            	}
	            	else if(delimiter.equals(" "))
	            	{
	            		br1.write(str+" "+str+" "+str+" "+str+" "+tempName+":"+lcl_ext+" Mitel SampleRecord "+str+" Mitel1@123456 "+str+x+"@gmail.com");
	            	}
	            	else if(delimiter.equals(","))
	            	{
	            		br1.write(str+","+str+","+str+","+str+","+tempName+":"+lcl_ext+",Mitel,SampleRecord,"+str+",Mitel1@123456,"+str+x+"@gmail.com");
	            	}
	            	
	            	x++;
	            	br1.newLine();
            	}
            	else if(i >= 100 && i < 1000)
            	{
            		lcl_ext = ext.substring(0, 17);
            		str = usrName;
	            	str = str+x;
	            	lcl_ext=lcl_ext+x;
	            	gmail = "import@gmail.com";
	            	equipmentPosition = "1A-2-20-0";
	            	equipmentPosition = equipmentPosition+x;
	            	
	            	if(delimiter.equals(";"))
	            	{
	            		br1.write(str+";"+str+";"+str+";"+str+";"+tempName+":"+lcl_ext+";Mitel;Kanata;"+str+";Mitel1@123456;"+str+x+"@gmail.com");
	            	}
	            	else if(delimiter.equals(" "))
	            	{
	            		br1.write(str+" "+str+" "+str+" "+str+" "+tempName+":"+lcl_ext+" Mitel SampleRecord "+str+" Mitel1@123456 "+str+x+"@gmail.com");
	            	}
	            	else if(delimiter.equals(","))
	            	{
	            		br1.write(str+","+str+","+str+","+str+","+tempName+":"+lcl_ext+",Mitel,SampleRecord,"+str+",Mitel1@123456,"+str+x+"@gmail.com");
	            	}
	            	
	            	x++;
	            	br1.newLine();
            	}
            	else if(i == 1000)
            	{
            		lcl_ext = ext.substring(0, 16);
            		str = usrName;
	            	str = str+x;
	            	lcl_ext=lcl_ext+x;
	            	gmail = "import@gmail.com";
	            	equipmentPosition = "1A-2-20-0";
	            	equipmentPosition = equipmentPosition+x;
	            	
	            	if(delimiter.equals(";"))
	            	{
	            		br1.write(str+";"+str+";"+str+";"+str+";"+tempName+":"+lcl_ext+";Mitel;Kanata;"+str+";Mitel1@123456;"+str+x+"@gmail.com");
	            	}
	            	else if(delimiter.equals(" "))
	            	{
	            		br1.write(str+" "+str+" "+str+" "+str+" "+tempName+":"+lcl_ext+" Mitel SampleRecord "+str+" Mitel1@123456 "+str+x+"@gmail.com");
	            	}
	            	else if(delimiter.equals(","))
	            	{
	            		br1.write(str+","+str+","+str+","+str+","+tempName+":"+lcl_ext+",Mitel,SampleRecord,"+str+",Mitel1@123456,"+str+x+"@gmail.com");
	            	}
	            	
	            	x++;
	            	br1.newLine();
            	}
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally
        {
            try {
                br1.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
	}
	
}
