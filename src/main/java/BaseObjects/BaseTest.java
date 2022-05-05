package BaseObjects;

import Driver.DriverManager;
import TestNGUtils.InvokedMethodsListener;
import TestNGUtils.Listener;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;

import java.lang.reflect.InvocationTargetException;

@Listeners({Listener.class, InvokedMethodsListener.class})
public abstract class BaseTest {
    protected WebDriver driver;
    protected ITestContext context;
    protected DriverManager driverManager;

    @BeforeTest
    public void precondition(ITestContext context) {
        this.context = context;

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

}

