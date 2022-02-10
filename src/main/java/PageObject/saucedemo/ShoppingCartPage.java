package PageObject.saucedemo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class ShoppingCartPage extends BasePageDemo {

    private By removeButton = By.id("remove-sauce-labs-bike-light");
    private By checkoutButton = By.id("checkout");
    private By continueShoppingButton = By.id("continue-shopping");

    public ShoppingCartPage(WebDriver driver) {
        super(driver);
    }

    public void removeFromCart() {
        checkIfProductAdded();
        int numberOfRemoveButtons = driver.findElements(removeButton).size();
        driver.findElement(removeButton).click();
        int numberOfRemoveButtonsAfterRemoving = driver.findElements(removeButton).size();
        Assert.assertEquals(numberOfRemoveButtonsAfterRemoving,numberOfRemoveButtons-1);
    }

    public void goToCheckoutPage() {
        checkIfProductAdded();
        driver.findElement(checkoutButton).click();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/checkout-step-one.html");
    }

    public void checkIfProductAdded() {
        Boolean productAdded = driver.findElement(removeButton).isDisplayed();
        if (!productAdded){
            System.out.println("No product is added to the shopping cart");
        }
    }
}
