package TestNGUtils;

import com.relevantcodes.extentreports.LogStatus;
import org.testng.ITestContext;
import org.testng.ITestListener;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import org.testng.ITestResult;

public class ExtentReport implements ITestListener {

    private static ExtentReports extentReports;
    private static ExtentTest extentTest;

    /**
     * System.getProperty("user.dir") - куда хотим положить отчёт (не абсолютный путь, а генерируемый на каждой машине)
     * "/target/" - в папку target
     * ".html" - тип отчёта
     * context.getName() - имя теста
     */
    @Override
    public void onStart(ITestContext context) {
        extentReports = new ExtentReports(System.getProperty("user.dir") + "/target/" + context.getName() + ".html");
        extentTest = extentReports.startTest(context.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        extentTest.log(LogStatus.FAIL, result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        extentTest.log(LogStatus.PASS, result.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        extentReports.endTest(extentTest);
        extentReports.flush();
    }
}
