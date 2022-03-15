package PageObject.herocuapp;

import PageObject.BasePage;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;

public class JavaScriptAlertsPage extends BasePage {

    private By buttonJSAlert = By.cssSelector("[onclick*='jsAlert']");
    private By buttonJSConfirm = By.cssSelector("[onclick*='jsConfirm']");
    private By buttonJSPrompt = By.cssSelector("[onclick='jsPrompt()']");
    private By title = By.tagName("h3");
    private By result = By.xpath("//p[@id='result']");

    public JavaScriptAlertsPage verifyTitle() {
        String textOfTitle = ((JavascriptExecutor)driver).executeScript("return arguments[0].innerHTML;",driver.findElement(title)).toString();
        Assert.assertEquals(textOfTitle, "JavaScript Alerts");
        return this;
    }

    public enum Button {ALERT, CONFIRM, PROMPT}

    public JavaScriptAlertsPage clickOnButton(Button button) {
        By jsButton = null;
        switch (button) {
            case ALERT:
                jsButton = buttonJSAlert;
                break;
            case CONFIRM:
                jsButton = buttonJSConfirm;
                break;
            case PROMPT:
                jsButton = buttonJSPrompt;
                break;
        }
        clickButton(jsButton);
        return this;
    }

    public JavaScriptAlertsPage checkAlertsText(String expectedText) {
        Alert alert = driver.switchTo().alert();
        Assert.assertEquals(alert.getText(), expectedText);
        return this;
    }

    public JavaScriptAlertsPage enterText(String text) {
        log.debug("Entered text into alert's text field: " + text);
        Alert alert = driver.switchTo().alert();
        alert.sendKeys(text);
        return this;
    }

    public JavaScriptAlertsPage checkEnteredText(String text){
        log.debug("Shown text under the alerts' buttons is: " + getText(result));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(result));
        Assert.assertEquals(getText(result), "You entered: " + text);
        return this;
    }

    public JavaScriptAlertsPage closeAlert() {
        log.debug("Alert accepted");
        Alert alert = driver.switchTo().alert();
        alert.accept();
        return this;
    }

}
