package config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.Test;

public class ConfigManager 
{

	public static void updatePropertyFile(String key,String value)
	{
		Properties prop=new Properties();
		
		File srcFile=new File(System.getProperty("user.dir")+"/Config/config.properties");
		FileInputStream fileInputStream;
		try
		{
			fileInputStream = new FileInputStream(srcFile);
			prop.load(fileInputStream);
			prop.setProperty(key, value);
			FileOutputStream fileOutputStream = new FileOutputStream(srcFile);
			prop.store(fileOutputStream,null);
			
		} 
		catch (IOException e)
		{
			System.out.println("Could not load the file"+e.getMessage());
		}
		 
			
		
	}
	
	
	
	

	public static String getProperty(String key)
	{
		String value=null;
		Properties prop=new Properties();
		
		File src=new File(System.getProperty("user.dir")+"/Config/config.properties");
		try {
			FileInputStream fis=new FileInputStream(src);
			prop.load(fis);
			value= prop.getProperty(key);
		} catch (FileNotFoundException e)
		{
			System.out.println("Cold not find file"+e.getMessage());
		} catch (IOException e)
		{

			System.out.println("Cold not load the file");
		}
		return value;
	}
}
