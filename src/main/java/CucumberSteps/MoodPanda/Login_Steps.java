package CucumberSteps.MoodPanda;

import BaseObjects.SelenideBaseTest;
import PageObject.moodpanda.HomePage;
import PageObject.moodpanda.LoginPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Login_Steps extends SelenideBaseTest {

    @Given("i load page")
    public void openPage() {
        get(HomePage.class);
    }

    @When("i click get started")
    public void getStarted(){
        get(HomePage.class).clickGetStarted();
    }
    @When("i enter user {string}")
    public void enterUsername(String email){
        get(LoginPage.class).enterUsername(email);
    }
    @When("i enter password {string}")
    public void enterPassword(String pswrd){
        get(LoginPage.class).enterPassword(pswrd);
    }
    @When("click on login button")
    public void clickLoginButton(){
        get(LoginPage.class).clickLogin();
    }

    @Then("check login exception")
    public void checkLoginException(){
        get(LoginPage.class).checkEmailException();
    }

    @Then("i close")
    public void close(){
        super.close();
    }

}
