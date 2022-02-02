package Homework_6;

import BaseObjects.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static org.openqa.selenium.support.locators.RelativeLocator.with;

public class Task_6 extends BaseTest {

    @Test(priority = 1)
    public void logIn() {
        driver.get("https://www.saucedemo.com/");
        String userNames = driver.findElement(By.xpath("//div[contains(@id,'cred')]")).getText();
        String[] userName = userNames.split("\\r?\\n");
        String allPasswords = driver.findElement(By.className("login_password")).getText();
        String[] pasword = allPasswords.split("\\r?\\n");
        //Web elements
        WebElement usernameField = driver.findElement(By.xpath("//input[contains(@class,'input_error') and @type='text']"));
        WebElement passwordField = driver.findElement(with(By.id("password")).below(usernameField));
        WebElement loginButton = driver.findElement(By.cssSelector("[class|='submit']"));
        //Actions
        usernameField.sendKeys(userName[1]);
        passwordField.sendKeys(pasword[1]);
        loginButton.click();
    }

    //выбираем первый товар с ценой меньше 10$ и добавляем его в корзину (чтобы использовать Relative locator toRightOf)
    @Test(priority = 2)
    public void addToCart() {
        WebElement priceWebElement = findElementWithPrice();
        if (priceWebElement != null) {
            driver.findElement(with(By.tagName("button")).toRightOf(priceWebElement)).click();
        } else {
            System.out.println("No price that is less tan 10$ is found");
        }
    }

    @Test(priority = 3)
    public void checkCart() {
        String productPriceAddedToCart = findElementWithPrice().getText();
        String productNameAddedToCart = findElementWithPrice().findElement(By.xpath(".//ancestor::div[contains(@class,'description')]//div[@class='inventory_item_name']")).getText();
        WebElement shoppingCartLink = driver.findElement(By.className("shopping_cart_link"));
        shoppingCartLink.click();
        Assert.assertEquals(driver.findElement(By.className("title")).getText().toLowerCase(), "Your Cart".toLowerCase());
        String productNameShownInCart = driver.findElement(By.className("inventory_item_name")).getText();
        String ProductPriceShownInCart = driver.findElement(By.cssSelector(".inventory_item_price")).getText();
        Assert.assertEquals(ProductPriceShownInCart, productPriceAddedToCart);
        Assert.assertEquals(productNameShownInCart, productNameAddedToCart);
    }

    public WebElement findElementWithPrice() {
        WebElement priceWebElement = null;
        List<WebElement> prices = driver.findElements(By.cssSelector(".inventory_item_price"));
        for (WebElement price : prices) {
            double selectedPrice = Double.parseDouble(price.getText().substring(1));
            if (selectedPrice < 10) {
                priceWebElement = price;
                break;
            }
        }
        return priceWebElement;
    }

}
