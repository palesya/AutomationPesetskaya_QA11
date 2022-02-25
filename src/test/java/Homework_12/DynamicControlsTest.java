package Homework_12;

import BaseObjects.BaseTest;
import PageObject.herocuapp.DynamicControlsPage;
import org.testng.annotations.Test;

public class DynamicControlsTest extends BaseTest {

    @Test(description = "Check dynamic checkbox", priority = 2)
    public void dynamicCheckboxes_Test() {
        get(DynamicControlsPage.class)
                .checkDynamicCheckbox();
    }

    @Test(description = "Check dynamic input", priority = 1)
    public void dynamicInput_Test() {
        get(DynamicControlsPage.class)
                .checkDynamicInput();
    }
}
