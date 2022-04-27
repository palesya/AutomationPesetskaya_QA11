package PageObject.RabotaBy.HeaderPage;

import PageObject.BasePage;
import org.openqa.selenium.By;
import org.testng.Assert;

public class HeaderElement extends BasePage {

    private By totalHeader = By.xpath("//*[@class='supernova-overlay__navi']");

    public HeaderElement checkHeaderElement(){
        Assert.assertTrue(isElementExists(totalHeader));
        return this;
    }
}
