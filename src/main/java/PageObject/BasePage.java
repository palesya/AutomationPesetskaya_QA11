package PageObject;

import Configuration.PropertyReader;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

import static BaseObjects.DriverCreation.getDriver;

public abstract class BasePage {

    public WebDriver driver;
    protected WebDriverWait wait;
    protected Actions actions;
    protected Properties properties;
    protected Logger log= Logger.getLogger(BasePage.class);

    protected BasePage() {
        this.driver = getDriver();
        this.wait = new WebDriverWait(this.driver, Duration.ofSeconds(5));
        this.actions = new Actions(this.driver);
        this.properties = PropertyReader.getProperties();
    }

    protected void pause(long seconds) {
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

    public BasePage open(String url) {
        log.debug("Open page " + url);
        driver.get(url);
        return this;
    }

    protected BasePage enter(By element, CharSequence... data) {
        log.debug("Enter " + Arrays.toString(data));
        driver.findElement(element).clear();
        driver.findElement(element).sendKeys(data);
        return this;
    }

    protected BasePage click(By element) {
        log.debug("Click on " + driver.findElement(element).getText());
        driver.findElement(element).click();
        return this;
    }

    protected BasePage clickLink(String linkText) {
        log.debug("Click link " + linkText);
        driver.findElement(By.linkText(linkText)).click();
        return this;
    }

    protected BasePage wait(By element) {
        log.debug("Wait for " + element);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(element)));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
        return this;
    }

    public BasePage scrollDown() {
        log.debug("Scroll Down");
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0,document.body.scrollHeight)", "");
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
