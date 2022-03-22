package PageObject.herocuapp;

import PageObject.BasePage;
import lombok.extern.log4j.Log4j;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

@Log4j
public class DragAndDropPage extends BasePage {

    private By title = By.tagName("h3");
    private By firstSquare = By.id("column-a");
    private By secondSquare = By.id("column-b");

    public DragAndDropPage verifyTitle() {
        String textOfTitle = getText(title);
        log.debug("Actual text of title: " + textOfTitle);
        Assert.assertEquals(textOfTitle, "Drag and Drop");
        return this;
    }

    public DragAndDropPage dragAndDropAction() throws InterruptedException {
        log.debug("Drag and drop action performed");
        Actions builder = new Actions(driver);
        builder.dragAndDropBy(getWebElement(firstSquare), 0, 500)
                .release(getWebElement(secondSquare)).perform();
        return this;
    }

    public DragAndDropPage checkA_BLocation() {
        log.debug("Check A is located at the left");
        Assert.assertEquals(getText(firstSquare), "A");
        Assert.assertEquals(getText(secondSquare), "B");
        return this;
    }

    public DragAndDropPage checkB_ALocation() {
        log.debug("Check A is located at the right");
        Assert.assertEquals(getText(firstSquare), "B");
        Assert.assertEquals(getText(secondSquare), "A");
        return this;
    }

}
