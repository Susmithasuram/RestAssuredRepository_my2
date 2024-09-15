package listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class ReportListener implements ITestListener
{
	ExtentReports extent=ExtentManager.getInstance();
	ExtentTest extentTest;
	
	@Override
	public synchronized void onFinish(ITestContext context)
	{
		extent.flush();
		System.out.println("*****Generating report*************");
	}
	
	@Override
	public synchronized void onTestStart(ITestResult result)
	{
		extentTest= extent.createTest(result.getMethod().getMethodName());
	}

	@Override
	public synchronized void onTestSuccess(ITestResult result) 
	{
		extentTest.pass("test Passed");
		System.out.println("**********creating test for report*******");
	}

	@Override
	public synchronized void onTestFailure(ITestResult result) 
	{
		extentTest.fail("test failed"+result.getThrowable());
	}

	@Override
	public synchronized void onTestSkipped(ITestResult result)
	{
		extentTest.skip("test skipped"+result.getThrowable());
	}

}
