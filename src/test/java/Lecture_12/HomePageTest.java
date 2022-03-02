package Lecture_12;

import BaseObjects.BaseTest;
import PageObject.herocuapp.HomePage;
import PageObject.herocuapp.HomePageLinksEnum;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

public class HomePageTest extends BaseTest {

    @Parameters({"linkName"})
    @BeforeTest
    public void HomePage_Test(String linkName) {
        get(HomePage.class)
                .open(context.getSuite().getParameter("url"))
                .verifyTitleText()
                .verifySubTitleText();
        get(HomePage.class).clickLink(HomePageLinksEnum.valueOf(linkName));
    }
}
