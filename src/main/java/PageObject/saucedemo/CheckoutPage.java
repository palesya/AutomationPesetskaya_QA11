package PageObject.saucedemo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class CheckoutPage extends BasePageDemo{

    private By firstName = By.id("first-name");
    private By lastName = By.id("last-name");
    private By zipPostalCode = By.id("postal-code");
    private By continueButton = By.id("continue");

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public void fillUserDataForCheckout(String fName, String lName, String zipPostCode) {
        driver.findElement(firstName).sendKeys(fName);
        driver.findElement(lastName).sendKeys(lName);
        driver.findElement(zipPostalCode).sendKeys(zipPostCode);
        driver.findElement(continueButton).click();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/checkout-step-two.html");
    }

}
