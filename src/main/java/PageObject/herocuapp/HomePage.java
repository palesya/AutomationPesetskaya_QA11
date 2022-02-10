package PageObject.herocuapp;

import PageObject.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class HomePage extends BasePage {

    private By title = By.tagName("h1");
    private By subTitle = By.tagName("h2");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    private By getLink(String text) {
        return By.partialLinkText(text);
    }

    public HomePage verifyTitleText() {
        Assert.assertEquals(driver.findElement(title).getText(), "Welcome to the-internet");
        return this;
    }

    public HomePage verifySubTitleText() {
        Assert.assertEquals(driver.findElement(subTitle).getText(), "Available Examples");
        return this;
    }

    public void clickLink(String text) {
        driver.findElement(getLink(text)).click();
    }

}
