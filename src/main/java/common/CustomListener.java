package common;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.Status;

public class CustomListener implements ITestListener 
{
	
	public void onStart(ITestContext context) {
		System.out.println("*** Test Suite " + context.getName() + " started ***");
	}

	public void onFinish(ITestContext context) {
		System.out.println(("*** Test Suite " + context.getName() + " ending ***"));
		ExtentTestManager.endTest();
		ExtentManager.getInstance().flush();
	}

	public void onTestStart(ITestResult result) {
		System.out.println(("*** Running test Case " + result.getMethod().getMethodName() + "..."));
		ExtentTestManager.startTest(result.getMethod().getMethodName());
	}

	public void onTestSuccess(ITestResult result) {
		printMgsOnConsole(result);
		ExtentTestManager.getTest().log(Status.PASS, "Test passed");
	}

	public void onTestFailure(ITestResult result) {
		System.out.println("*** Test execution " + result.getMethod().getMethodName() + " failed...");

		ExtentTestManager.getTest().log(Status.FAIL, result.getMethod().getMethodName());
	}

	public void onTestSkipped(ITestResult result) {
		printMgsOnConsole(result);
		ExtentTestManager.getTest().log(Status.SKIP, result.getMethod().getMethodName() + result);
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		System.out.println("*** Test failed but within percentage % " + result.getMethod().getMethodName());
	}
	private void printMgsOnConsole(ITestResult result)
	{
		try
		{
			int status = result.getStatus();
			String msg;
			double timeTaken  = (result.getEndMillis() - result.getStartMillis())/1000.00;
			switch(status) 
			{
			case ITestResult.FAILURE:
				msg = String.format("Test Case \"%s\" ==>> FAILED [Time Taken = %s sec]", result.getMethod().getMethodName(), timeTaken);
				System.out.println(msg);
				break;
			case ITestResult.SKIP:
				msg = String.format("Test Case \"%s\" ==>> SKIPPED [Time Taken = %s sec]", result.getMethod().getMethodName(), timeTaken);
				System.out.println(msg);
				break;
			case ITestResult.SUCCESS:
				msg = String.format("Test Case \"%s\" ==>> PASSED [Time Taken = %s sec]", result.getMethod().getMethodName(), timeTaken);
				System.out.println(msg);
				break;
			default:
				if(result.getThrowable() != null)
					result.getThrowable().printStackTrace();

			}
		}
		catch(Exception ex)
		{
		}
	}
}
