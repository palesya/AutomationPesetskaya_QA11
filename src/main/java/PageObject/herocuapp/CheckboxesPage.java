package PageObject.herocuapp;

import PageObject.BasePage;
import org.openqa.selenium.By;
import org.testng.Assert;

public class CheckboxesPage extends BasePage {

    private By checkboxes(Integer index) {
        return By.xpath("(//*[@id='checkboxes']//input)[" + index + "]");
    }

    public CheckboxesPage clickCheckbox(Integer index) {
        click(checkboxes(index));
        return this;
    }

    public CheckboxesPage verifyCheckboxStatus(Integer index, Boolean status) {
        if (status) {
            verifyCheckboxChecked(index);
        } else {
            verifyCheckboxUnchecked(index);
        }
        return this;
    }

    private CheckboxesPage verifyCheckboxChecked(Integer index) {
        Assert.assertEquals(getAttribute(checkboxes(index), "checked"), "true");
        return this;
    }

    private CheckboxesPage verifyCheckboxUnchecked(Integer index) {
        Assert.assertNotEquals(getAttribute(checkboxes(index), "checked"), "true");
        return this;
    }
}
