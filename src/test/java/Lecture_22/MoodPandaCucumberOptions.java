package Lecture_22;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        features={"src/test/resources/feature/MoodPanda_Test.feature"},
        glue={"CucumberSteps/MoodPanda"},
plugin={"pretty","html:target/cucumber.html","json:target/cucumber.json"})
public class MoodPandaCucumberOptions extends AbstractTestNGCucumberTests {
    @Override
    @DataProvider
    public Object[][] scenarios(){
        return super.scenarios();
    }
}
