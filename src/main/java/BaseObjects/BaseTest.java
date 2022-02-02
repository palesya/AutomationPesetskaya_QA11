package BaseObjects;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import static BaseObjects.DriverCreation.closeDriver;
import static BaseObjects.DriverCreation.getDriver;
import static BaseObjects.DriverCreation.Drivers.*;

public class BaseTest {
    protected WebDriver driver;

    @BeforeTest
    public void precondition() {
        this.driver = getDriver(CHROME);
    }

    @AfterTest
    public void postcondition() {
        closeDriver();
    }

}
