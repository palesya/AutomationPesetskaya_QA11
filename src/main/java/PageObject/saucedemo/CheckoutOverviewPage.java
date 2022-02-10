package PageObject.saucedemo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class CheckoutOverviewPage extends BasePageDemo {
    private By quantity = By.className("cart_quantity");
    private By finishButton = By.id("finish");

    public CheckoutOverviewPage(WebDriver driver) {
        super(driver);
    }

    public void finishCheckout() {
        Assert.assertTrue(driver.findElement(quantity).isDisplayed());
        driver.findElement(finishButton).click();
        Assert.assertEquals(driver.findElement(By.className("complete-header")).getText(),"THANK YOU FOR YOUR ORDER");
    }
}
