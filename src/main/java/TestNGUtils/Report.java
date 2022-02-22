package TestNGUtils;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

public class Report implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        Reporter.log("I'm started with "+result.getName(),true);
    }

    @Override
    public void onFinish(ITestContext context) {
        Reporter.log("I'm finished with "+context.getName(),true);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        Reporter.log("I'm failed on "+result.getName(),true);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        Reporter.log("I'm succeed on "+result.getName(),true);
    }
}
