package listeners;

import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import utils.GenericDateUtils;


public class ExtentManager 
{
	static ExtentReports extent;
	public static ExtentReports getInstance() 
	{
		System.out.println("*****Generating report instance********");
		if (extent==null)
		{
			extent= createInstance();
			return extent;
		}
		else 
		{
			return extent;
		}
		
		
	}
	//GenericDateUtils.getDate()
	public static ExtentReports createInstance()
	{
		String filePath=System.getProperty("user.dir")
				+"/Reports/API automation report_"+GenericDateUtils.getDate()+".html";
		
//		String filePath=System.getProperty("user.dir")
//				+"/Reports/API automation report_"+new Date()+".html";
		System.out.println("******Reports can be found at "+filePath+"***********");
				
		ExtentSparkReporter reporter=new ExtentSparkReporter(filePath);
		reporter.config().setTheme(Theme.DARK);
		reporter.config().setReportName(" API testing using RA automation report");
		reporter.config().setDocumentTitle("Automation report");
		
		ExtentReports extent=new ExtentReports();
		extent.attachReporter(reporter);
		return extent;
	}

}
