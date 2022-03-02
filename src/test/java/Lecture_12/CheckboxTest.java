package Lecture_12;

import BaseObjects.BaseTest;
import PageObject.herocuapp.CheckboxesPage;
import org.testng.annotations.Test;

public class CheckboxTest extends BaseTest {

    @Test
    public void checkboxes_Test() {
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
