package Lecture_12;

import BaseObjects.BaseTest;
import PageObject.herocuapp.AbTestPage;
import org.testng.annotations.Test;

import static PageObject.herocuapp.ConditionsEnum.CONTAINS;

public class ABTestingTest extends BaseTest {


    @Test(description = "A/B Testing page")
    public void abTesting_Test() {
        get(AbTestPage.class)
                .checkTitleText(CONTAINS, "A/B Test")
                .checkText(CONTAINS, "Also known");
    }

}
