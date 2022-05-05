package Homework_22;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(features = {"src/test/resources/feature/Booking_Test.feature"},
        glue = {"CucumberSteps/Booking"},plugin={"pretty","html:target/cucumber.html","json:target/cucumber.json"})
public class BookingCucumberOptions extends AbstractTestNGCucumberTests {
    @Override
    @DataProvider
    public Object[][] scenarios(){
        return super.scenarios();
    }

}
