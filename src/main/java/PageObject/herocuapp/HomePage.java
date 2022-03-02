package PageObject.herocuapp;

import PageObject.BasePage;
import org.openqa.selenium.By;
import org.testng.Assert;

public class HomePage extends BasePage {

    private By title = By.tagName("h1");
    private By subTitle = By.tagName("h2");

    private By getLink(String text) {
        return By.partialLinkText(text);
    }

    public HomePage open(String url) {
        super.open(url);
        return this;
    }

    public HomePage verifyTitleText() {
        Assert.assertEquals(getText(title), "Welcome to the-internet");
        return this;
    }

    public HomePage verifySubTitleText() {
        Assert.assertEquals(getText(subTitle), "Available Examples");
        return this;
    }

    public HomePage clickLink(HomePageLinksEnum link) {
        actions.click(driver.findElement(getLink(link.getLink()))).perform();
        //click(getLink(link.getLink()));
        return this;
    }

}
