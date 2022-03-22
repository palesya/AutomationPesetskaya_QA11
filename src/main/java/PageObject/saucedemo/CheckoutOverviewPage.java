package PageObject.saucedemo;

import PageObject.BasePage;
import org.openqa.selenium.By;
import org.testng.Assert;

public class CheckoutOverviewPage extends BasePage {
    private By quantity = By.className("cart_quantity");
    private By finishButton = By.id("finish");

    public void finishCheckout() {
        Assert.assertTrue(isElementDisplayed(quantity));
        clickButton(finishButton);
        Assert.assertEquals(getText(By.className("complete-header")),"THANK YOU FOR YOUR ORDER");
    }
}
