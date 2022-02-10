package PageObject.saucedemo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class BasePageDemo {

    public WebDriver driver;
    private By shoppingCartLink = By.className("shopping_cart_link");

    public BasePageDemo(WebDriver driver) {
        this.driver = driver;
    }

    public void goToShoppingCartLink() {
        driver.findElement(shoppingCartLink).click();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/cart.html");
    }

}
