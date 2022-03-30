package Lecture_18;

import BaseObjects.SelenideBaseTest;
import PageObject.moodpanda.HomePage;
import PageObject.moodpanda.LoginPage;
import org.testng.annotations.Test;

public class MoodPandaTest extends SelenideBaseTest {

    @Test
    public void homePage_Test() {
        get(HomePage.class)
                .clickGetStarted();
        get(LoginPage.class)
                .enterUsername("User")
                .enterPassword("password")
                .clickLogin()
                .checkEmailException();
    }
}
