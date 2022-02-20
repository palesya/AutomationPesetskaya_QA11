package Lecture_9;

import BaseObjects.BaseTest;
import PageObject.herocuapp.AbTestPage;
import PageObject.herocuapp.HomePage;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Lecture_9 extends BaseTest {

    @BeforeTest
    public void preconditions() {
        driver.get(context.getSuite().getParameter("url"));
    }

    @Test
    public void test() {
        get(HomePage.class)
                .verifyTitleText()
                .verifySubTitleText()
                .clickLink("A/B Testing");

        get(AbTestPage.class)
                .checkContainText("Also");
    }

}
