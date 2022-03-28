package PageObject.herocuapp;

import PageObject.BasePage;
import lombok.extern.log4j.Log4j;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;

@Log4j
public class FramesPage extends BasePage {

    private By title = By.tagName("h3");
    private By frame = By.id("mce_0_ifr");
    private By textArea = By.id("tinymce");

    public FramesPage verifyTitle(String titleText) {
        String textInTextArea = ((JavascriptExecutor)driver).executeScript("return arguments[0].innerHTML;",driver.findElement(title)).toString();
        Assert.assertEquals(textInTextArea, titleText);
        return this;
    }

    public FramesPage clickOnFrameLink(String text) {
        clickLink(text);
        return this;
    }

    public FramesPage switchToFrame() {
        driver.switchTo().frame(driver.findElement(frame));
        return this;
    }

    public FramesPage enterTextArea(String enterText) {
        enter(textArea,true,enterText);
        return this;
    }

    public FramesPage unSwitchFrame() {
        driver.switchTo().defaultContent();
        return this;
    }

    public FramesPage checkTextInTextArea(String expectedText) {
        String textInTextArea = ((JavascriptExecutor)driver).executeScript("return arguments[0].innerHTML;",driver.findElement(By.tagName("p"))).toString();
        log.debug("Existing text in text area is: " + textInTextArea);
        Assert.assertEquals(textInTextArea, expectedText);
        return this;
    }

}
