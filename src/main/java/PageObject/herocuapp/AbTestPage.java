package PageObject.herocuapp;

import PageObject.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class AbTestPage extends BasePage {

    private By title = By.tagName("h3");
    private By content = By.tagName("p");


    public AbTestPage(WebDriver driver) {
        super(driver);
    }

    public AbTestPage checkTitleTxt(){
        Assert.assertEquals(driver.findElement(title).getText(),"A/B Test Variation 1");
        return this;
    }

    public AbTestPage checkContainText(String content){
        Assert.assertEquals(driver.findElement(this.content).getText(),"Also");
        return this;
    }
}
