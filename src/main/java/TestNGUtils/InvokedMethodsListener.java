package TestNGUtils;

import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestContext;
import org.testng.ITestResult;

import static BaseObjects.DriverCreation.getDriver;

//позволяет выполнить что-то до и после методов -
public class InvokedMethodsListener implements IInvokedMethodListener {

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult, ITestContext context) {
        if(!testResult.isSuccess()&&getDriver()!=null){
            byte[] file = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES);
            saveScreenshot(file);}
    }

    @Attachment(value="screenshots", type="image/png")
    private byte[] saveScreenshot(byte[] bytes){
        return bytes;
    }
}
