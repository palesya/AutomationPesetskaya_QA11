package TestNGUtils;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class Listener implements ITestListener {

    @Override
    public void onStart(ITestContext context) {
        System.out.println("Hello,I'm started!!!");
    }
}
