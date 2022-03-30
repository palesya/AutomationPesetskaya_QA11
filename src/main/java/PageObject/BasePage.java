package PageObject;

import Properties.PropertyReader;
import lombok.extern.log4j.Log4j;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import static BaseObjects.DriverCreation.getDriver;

@Log4j
public abstract class BasePage {

    public WebDriver driver;
    protected WebDriverWait wait;
    protected Actions actions;
    protected Properties properties;

    protected BasePage() {
        this.driver = getDriver();
        this.wait = new WebDriverWait(this.driver, Duration.ofSeconds(5));
        this.actions = new Actions(this.driver);
        this.properties = PropertyReader.getProperties();
    }

    protected void pause(long seconds) {
        log.debug("Pause for seconds: "+seconds);
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public BasePage open() {
        String url = properties.getProperty("url");
        log.debug("Open page " + url);
        driver.get(url);
        return this;
    }

    public BasePage compareCurrentUrlWithExpected(String expectedUrl) {
        log.debug("Compare current url " + driver.getCurrentUrl());
        Assert.assertEquals(driver.getCurrentUrl(),expectedUrl);
        return this;
    }

    public BasePage enterPropertyValueIntoField(By field, String property) {
        String propertyValue = properties.getProperty(property);
        getWebElement(field).clear();
        getWebElement(field).sendKeys(propertyValue);
        return this;
    }

    public BasePage open(String url) {
        log.debug("Open page " + url);
        driver.get(url);
        return this;
    }

    protected BasePage enter(By element, Boolean autoClean, CharSequence... data) {
        log.debug("Enter " + Arrays.toString(data));
        if (autoClean) {
            enter(element, data);
        } else {
            getWebElement(element).sendKeys(data);
        }
        return this;
    }

    protected BasePage enter(By element, CharSequence... data) {
        log.debug("Enter " + Arrays.toString(data));
        getWebElement(element).sendKeys(data);
        return this;
    }

    private BasePage click(By element) {
        log.debug("Click on " + getWebElement(element).getText());
        getWebElement(element).click();
        return this;
    }

    protected BasePage clickLink(String linkText) {
        log.debug("Click link " + linkText);
        getWebElement(By.linkText(linkText)).click();
        return this;
    }

    protected BasePage zoomIn(Integer value) {
        log.debug("Zoom in");
        WebElement webElement = getWebElement(By.tagName("html"));
        for (int i = 0; i<value; i++)
        {
            webElement.sendKeys(Keys.chord(new CharSequence[]{Keys.COMMAND, Keys.ADD}));
        }
        return this;
    }

    protected BasePage zoomOut(Integer value) {
        log.debug("Zoom out");
        WebElement webElement = getWebElement(By.tagName("html"));
        for (int i = 0; i<value; i++){
            webElement.sendKeys(Keys.chord(new CharSequence[]{Keys.COMMAND, Keys.SUBTRACT}));
        }
        return this;
    }

    protected BasePage alertAccept() {
        log.debug("Accept alert");
       driver.switchTo().alert().accept();
        return this;
    }

    protected BasePage alertDismiss() {
        log.debug("Dismiss alert");
        driver.switchTo().alert().dismiss();
        return this;
    }

    protected BasePage alertSendKeys(String sendText) {
        log.debug("Send keys to alert: "+sendText);
        driver.switchTo().alert().sendKeys(sendText);
        return this;
    }

    protected BasePage screenshot(String path){
        log.debug("Take screenshot to directory: "+path);
        TakesScreenshot takesScreenshot=((TakesScreenshot) driver);
        File file = takesScreenshot.getScreenshotAs(OutputType.FILE);
        try{
            FileUtils.copyFile(file,new File(path));
        } catch(IOException e){
            log.error(e.getMessage());
        }
        return this;
    }

    protected BasePage clickButton(By element) {
        log.debug("Click on button " + element);
        click(element);
        return this;
    }

    protected BasePage clickButton(WebElement element) {
        log.debug("Click on button " + element);
        element.click();
        return this;
    }

    protected BasePage clickButtonAndRepeat(By element) {
        try {
            clickButton(element);
        } catch (
                ElementNotVisibleException e) {
            clickButton(element);
        }
        return this;
    }

    protected BasePage clickTab(By element) {
        log.debug("Click on tab " + element);
        click(element);
        return this;
    }

    protected BasePage submit(By element) {
        log.debug("Submit " + element);
        getWebElement(element).submit();
        return this;
    }

    protected BasePage selectByValue(By element, String value) {
        log.debug("Select by value: " + value);
        Select select = new Select(getWebElement(element));
        select.selectByValue(value);
        return this;
    }

    protected BasePage selectByIndex(By element, Integer index) {
        log.debug("Select by index: " + index);
        Select select = new Select(getWebElement(element));
        select.selectByIndex(index);
        return this;
    }

    protected BasePage wait(By element) {
        log.debug("Wait for " + element);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(getWebElement(element)));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
        return this;
    }

    protected BasePage clear(By field) {
        log.debug("Clear field " + field);
        getWebElement(field).clear();
        return this;
    }

    public BasePage scrollDown() {
        log.debug("Scroll Down");
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0,document.body.scrollHeight)", "");
        return this;
    }

    protected WebElement getWebElement(By element) {
        log.debug("Get web element" + element);
        return driver.findElement(element);
    }

    protected List<WebElement> getWebElements(By element) {
        log.debug("Get web elements" + element);
        return driver.findElements(element);
    }

    protected Integer findElementsCount(By element) {
        log.debug("Count elements: "+element);
        return driver.findElements(element).size();
    }

    protected String getText(By element) {
        log.debug("Get text of element: "+element);
        return getWebElement(element).getText();
    }

   public Boolean isElementExists(By element) {
       log.debug("Is element exist: "+element);
        List<WebElement> elementList = driver.findElements(element);
        return elementList.size()>0;
    }

    public Boolean isElementExists(WebElement element) {
        log.debug("Is element exist: "+element);
        try {
            element.isDisplayed();
            return true;
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    public Boolean isElementDisplayed(By element) {
        log.debug("Is element displayed: "+element);
        return getWebElement(element).isDisplayed();
    }

    public Boolean isElementEnabled(By element) {
        log.debug("Is element enabled: "+element);
        return getWebElement(element).isEnabled();
    }

    protected BasePage verify(ExpectedCondition<BasePage> condition){
        wait.ignoring(ElementNotVisibleException.class).until(condition);
        return this;
    }

    protected void waitForElementToAppear(By element) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
    }

    protected void waitForElementToDisappear(By element) {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(element));
    }

    /**
     * @param element       - web element
     * @param attributeName - attribute name
     * @return - string of attribute name
     */
    protected String getAttribute(By element, String attributeName) {
        return getWebElement(element).getAttribute(attributeName);
    }

}
