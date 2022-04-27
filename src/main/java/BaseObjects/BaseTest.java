package BaseObjects;

import Driver.DriverManager;
import Driver.DriverManagerFactory;
import TestNGUtils.InvokedMethodsListener;
import TestNGUtils.Listener;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;

import java.lang.reflect.InvocationTargetException;

import static com.codeborne.selenide.Browsers.CHROME;

@Listeners({Listener.class, InvokedMethodsListener.class})
public abstract class BaseTest {
    protected WebDriver driver;
    protected ITestContext context;
    protected DriverManager driverManager;

    @BeforeTest
    public void precondition(ITestContext context) {
        this.context = context;
        this.driverManager= DriverManagerFactory.getManager(DriverManagerType.valueOf(context.getSuite().getParameter("config") == null ? String.valueOf(DriverManagerType.CHROME) : context.getSuite().getParameter("config")));
        this.driver = DriverManager.getDriver();
    }

    protected <T> T get(Class<T> page) {
        T instance = null;
        try {
            instance = page.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchElementException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return instance;
    }

    @AfterTest
    public void postcondition() {
        DriverManager.closeDriver();
    }

}

