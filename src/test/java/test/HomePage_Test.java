package test;

import BaseObjects.BaseTest;
import PageObject.herocuapp.HomePage;
import PageObject.herocuapp.HomePageLinksEnum;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class HomePage_Test extends BaseTest {

    @Parameters({"linkName"})
    @Test
    public void homePage_Test(String linkName) {
        get(HomePage.class).open();
        get(HomePage.class).verifyTitleText()
                .verifySubTitleText();
        get(HomePage.class).scrollDown();
        get(HomePage.class).clickLink(HomePageLinksEnum.valueOf(linkName));
    }
}
