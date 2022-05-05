package TestNGUtils;

import Driver.DriverManager;
import Driver.DriverManagerFactory;
import Properties.PropertyReader;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;

import static Properties.PropertyReader.getProperties;

public class Listener implements ITestListener {
    private static Properties properties;
    private static ITestContext context;
    private WebDriver driver;
    private DriverManager driverManager;

    @Override
    public void onStart(ITestContext context) {
        this.context = context;
        System.setProperty("logger_time",getSimpleDate());
        new PropertyReader(context.getSuite().getParameter("config") == null ? System.getProperty("config") : context.getSuite().getParameter("config"));
        this.properties = PropertyReader.getProperties();
        this.driverManager= DriverManagerFactory.getManager(DriverManagerType.valueOf(getProperties().getProperty("browser")));
        this.driver = DriverManager.getDriver();
    }

    private String getSimpleDate() {
        return new SimpleDateFormat("yyyyMMdd-HHmmss").format(Calendar.getInstance().getTime());
    }

    @Override
    public void onFinish(ITestContext context) {
        DriverManager.closeDriver();
    }

}
