package Lecture_12;

import BaseObjects.BaseTest;
import PageObject.herocuapp.Checkboxes;
import PageObject.herocuapp.HomePage;
import org.testng.annotations.Test;
import static PageObject.herocuapp.HomePageLinksEnum.CHECKBOXES;

public class CheckboxTest extends BaseTest {

    @Test
    public void checkboxes_Test() {
        get(Checkboxes.class)
                .verifyCheckboxStatus(1, false)
                .verifyCheckboxStatus(2, true);

        get(Checkboxes.class)
                .clickCheckbox(1)
                .clickCheckbox(2);

        get(Checkboxes.class)
                .verifyCheckboxStatus(1, true)
                .verifyCheckboxStatus(2, false);

    }
}
