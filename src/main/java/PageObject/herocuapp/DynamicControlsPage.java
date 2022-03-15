package PageObject.herocuapp;

import PageObject.BasePage;
import org.openqa.selenium.By;
import org.testng.Assert;

public class DynamicControlsPage extends BasePage {
    private By checkbox = By.id("checkbox");
    private By removeButton = By.xpath("//button[text()='Remove']");
    private By enableButton = By.xpath("//button[text()='Enable']");
    private By inputField = By.xpath("//*[@type='text']");
    private By itsGoneMessage = By.xpath("//*[@id='checkbox-example']/p[@id='message']");
    private By itsEnabledMessage = By.xpath("//*[@id='input-example']/p[@id='message']");

    public DynamicControlsPage checkDynamicCheckbox() {
        Assert.assertTrue(driver.findElement(checkbox).isDisplayed());
        clickButton(removeButton);
        wait(itsGoneMessage);
        Assert.assertEquals(driver.findElement(itsGoneMessage).getText(),"It's gone!");
        int numberOfCheckboxes = driver.findElements(checkbox).size();
        Assert.assertEquals(numberOfCheckboxes,0);
        return this;
    }

    public DynamicControlsPage checkDynamicInput() {
        Assert.assertFalse(driver.findElement(inputField).isEnabled());
        clickButton(enableButton);
        wait(itsEnabledMessage);
        Assert.assertEquals(driver.findElement(itsEnabledMessage).getText(),"It's enabled!");
        Assert.assertTrue(driver.findElement(inputField).isEnabled());
        return this;
    }


}
