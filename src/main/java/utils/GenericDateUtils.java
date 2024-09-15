package utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.annotations.Test;

public class GenericDateUtils
{
	
	@Test
	public static String getDate()
	{
	Date date=new Date();
	System.out.println(date);
	//SimpleDateFormat customDate=new SimpleDateFormat("dd_MM_yyyy_HH_mm_ss");
	SimpleDateFormat formatDate=new SimpleDateFormat("dd_MM_yyyy_HH_mm_ss");
	 String newDate=formatDate.format(date);
	return newDate;
	}
}
