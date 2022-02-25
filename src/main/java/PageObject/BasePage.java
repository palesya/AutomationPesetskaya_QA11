package PageObject;

import BaseObjects.DriverCreation;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.testng.Reporter.*;
import static BaseObjects.DriverCreation.getDriver;

public abstract class BasePage {

    public WebDriver driver;
    protected WebDriverWait wait;
    protected Actions actions;

    protected BasePage(){
        this.driver = getDriver();
        this.wait=new WebDriverWait(this.driver, Duration.ofSeconds(5));
        this.actions = new Actions(this.driver);
    }

    //try/catch лучше здесь использовать, чтобы не такскать мо методам, которые его используют
    protected void pause(long seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    protected BasePage open(String url) {
        log("Open page " + url);
        driver.get(url);
        return this;
    }

    protected BasePage enter(By element, CharSequence... data) {
        driver.findElement(element).clear();
        driver.findElement(element).sendKeys(data);
        return this;
    }

    protected BasePage click(By element) {
        log("Click on " + element);
        driver.findElement(element).click();
        return this;
    }

    protected BasePage wait(By element) {
        log("Wait for " + element);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(element)));
        return this;
    }



    protected Integer findElementsCount(By element) {
        return driver.findElements(element).size();
    }

    protected String getText(By element) {
        return driver.findElement(element).getText();
    }

    /**
     * @param element       - web element
     * @param attributeName - attriute name
     * @return - string of attribute name
     */
    protected String getAttribute(By element, String attributeName) {
        return driver.findElement(element).getAttribute(attributeName);
    }

}
