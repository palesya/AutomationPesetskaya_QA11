package PageObject.herocuapp;

import PageObject.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import static PageObject.herocuapp.ConditionsEnum.CONTAINS;
import static PageObject.herocuapp.ConditionsEnum.EQUALS;

public class AbTestPage extends BasePage {

    private By title = By.tagName("h3");
    private By content = By.tagName("p");

    public AbTestPage checkTitleText(ConditionsEnum conditions, String expectedText) {
        switch (conditions) {
            case EQUALS:
                Assert.assertEquals(getText(title), expectedText);
                break;
            case CONTAINS:
                Assert.assertTrue(getText(title).contains(expectedText));
                break;
        }
        return this;
    }

    public AbTestPage checkText(ConditionsEnum conditions, String content) {
        if (conditions == EQUALS) {
            wait.until(ExpectedConditions.textToBe(this.content,content));
        } else if (conditions == CONTAINS) {
            Assert.assertTrue(getText(this.content).contains(content));
        }
        return this;
    }

}
