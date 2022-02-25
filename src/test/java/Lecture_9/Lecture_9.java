package Lecture_9;

import BaseObjects.BaseTest;
import PageObject.herocuapp.*;
import io.qameta.allure.*;
import jdk.jfr.Description;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static PageObject.herocuapp.ConditionsEnum.CONTAINS;
import static PageObject.herocuapp.HomePageLinksEnum.*;

public class Lecture_9 extends BaseTest {

    String url;

    @BeforeClass
    public void getUrl() {
        url = context.getSuite().getParameter("url");
    }

    @BeforeMethod
    public void preconditions() {
        get(HomePage.class)
                .open(url)
                .verifyTitleText()
                .verifySubTitleText();
    }

    @Test(description = "A/B Testing page")
    @Description("Verify A/B Testing page")
    @Step("Searching for '{keyword}' in Google")
    @Link("https://instagram.com/")
    @Issue("ISS-1")
    @TmsLink("ISS-1")
    @Attachment(value = "screenshot", type = "image/png")
    public void abTesting_Test() {
        get(HomePage.class).clickLink(AB_TESTING);

        get(AbTestPage.class)
                .checkTitleText(CONTAINS, "A/B Test")
                .checkText(CONTAINS, "Also known");
    }

    @Test
    public void checkboxes_Test() {
        get(HomePage.class).clickLink(CHECKBOXES);

        get(CheckboxesPage.class)
                .verifyCheckboxStatus(1, false)
                .verifyCheckboxStatus(2, true);

        get(CheckboxesPage.class)
                .clickCheckbox(1)
                .clickCheckbox(2);

        get(CheckboxesPage.class)
                .verifyCheckboxStatus(1, true)
                .verifyCheckboxStatus(2, false);

    }

}
