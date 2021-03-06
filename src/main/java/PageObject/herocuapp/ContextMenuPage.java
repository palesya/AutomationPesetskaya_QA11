package PageObject.herocuapp;

import PageObject.BasePage;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.testng.Assert;

public class ContextMenuPage extends BasePage {
    private By spot = By.id("hot-spot");

    public ContextMenuPage rightClickOnSpot() {
        actions.contextClick(driver.findElement(spot)).perform();
        Alert alert=driver.switchTo().alert();
        String alertsText = alert.getText();
        Assert.assertEquals(alertsText,"You selected a context menu");
        alert.accept();
        return this;
    }

}
