package Homework_18;

import BaseObjects.SelenideBaseTest;
import PageObject.moodpanda.HomePage;
import PageObject.moodpanda.SignUpPage;
import Properties.PropertyReader;
import org.testng.Assert;
import org.testng.annotations.Test;


import static com.codeborne.selenide.WebDriverRunner.url;

public class MoodPandaTest extends SelenideBaseTest {

    @Test(description = "Check if Homepage is opened", priority = 1)
    public void checkHomepageIsAvailable() {
        get(HomePage.class);
        Assert.assertTrue(url().matches(getProperty("url")));
    }

    @Test(description = "Check Get started redirection", priority = 2, dependsOnMethods = "checkHomepageIsAvailable")
    public void checkGetStartedRedirection() {
        get(HomePage.class)
                .clickGetStarted();
        System.out.println(url());
        Assert.assertTrue(url().matches(getProperty("url") + "login"));
    }

    @Test(description = "Check Sign up redirection from HomePage", priority = 3, dependsOnMethods = "checkHomepageIsAvailable")
    public void checkSignUpRedirection() {
        get(HomePage.class)
                .clickSignUp();
        System.out.println(url());
        Assert.assertTrue(url().matches(getProperty("url") + "signup"));
    }

    @Test(description = "Check informing text on SignUp page", priority = 4, dependsOnMethods = "checkSignUpRedirection")
    public void checkInfoTextOnSignUpPage() {
        get(SignUpPage.class)
                .checkInformingText(getProperty("informingText"));
    }

    @Test(description = "Check Sign up with valid data (without confirming email)", priority = 5, dependsOnMethods = "checkSignUpRedirection")
    public void checkSignUpWithValidData() {
        get(SignUpPage.class)
                .enterFirstName(getProperty("firstName"))
                .enterLastName(getProperty("lastName"))
                .enterEmail(getProperty("email"))
                .enterPassword(getProperty("password"))
                .checkIAmOver16()
                .clickSignUpButton()
                .checkLabelQuestion(getProperty("labelQuestion"))
                .clickSignUpButton();
    }

    @Test(description = "Check Sign up with valid data and unchecked 'Im over 16' checkbox", priority = 6, dependsOnMethods = "checkSignUpRedirection")
    public void checkSignUpWithUncheckedCheckbox() {
        get(SignUpPage.class)
                .enterFirstName("firstName")
                .enterLastName("lastName")
                .enterEmail("test@gmail.com")
                .enterPassword("11111111")
                .uncheckIAmOver16()
                .clickSignUpButton()
                .checkLabelQuestion(getProperty("labelQuestion"))
                .clickSignUpButton()
                .checkAlertIfUncheckedCheckbox(getProperty("alertLessThen16"));
    }
}
