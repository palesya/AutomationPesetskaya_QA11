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
        //css  .class1 .class2
        String userNames = driver.findElement(By.cssSelector(".login_credentials_wrap-inner .login_credentials")).getText();
        String[] userName = userNames.split("\\r?\\n");
        //className
        String allPasswords = driver.findElement(By.className("login_password")).getText();
        String[] pasword = allPasswords.split("\\r?\\n");
        //Web elements
        //поиск элемента с условием AND
        WebElement usernameField = driver.findElement(By.xpath("//input[contains(@class,'input_error') and @type='text']"));
        //id
        WebElement passwordField = driver.findElement(with(By.id("password")).below(usernameField));
        //css  [attribute|=value]
        WebElement loginButton = driver.findElement(By.cssSelector("[class|='submit']"));
        //Actions
        usernameField.sendKeys("Alesya");
        passwordField.sendKeys("123");
        loginButton.click();
        //Поиск по частичному совпадению текста, например By.xpath("//tag[contains(text(),'text')]");
        Assert.assertTrue(driver.findElement(By.xpath("//h3[contains(text(),'sadface')]")).isDisplayed());
        usernameField.clear();
        usernameField.sendKeys(userName[1]);
        passwordField.clear();
        passwordField.sendKeys(pasword[1]);
        loginButton.click();
    }

    @Test(priority = 2)
    public void checkProductPage() {
        //css tagname
        String footer = driver.findElement(By.cssSelector("footer")).getText();
        Assert.assertTrue(footer.contains("2022"));
        //css [attribute~=value]
        Assert.assertTrue(driver.findElement(By.cssSelector("[alt~=Open]")).isDisplayed());
        // css [attribute=value]
        Assert.assertTrue(driver.findElement(By.cssSelector("[class=app_logo]")).isDisplayed());
        //[attribute*=value]
        String[] filterOptions = driver.findElement(By.cssSelector("[class*=sort]")).getText().split("\\r?\\n");
        Assert.assertEquals(filterOptions[0],"Name (A to Z)");
    }

    //выбираем первый товар с ценой меньше 10$ и добавляем его в корзину (чтобы использовать Relative locator toRightOf)
    @Test(priority = 3)
    public void addToCart() {
        WebElement priceWebElement = findElementWithPrice();
        if (priceWebElement != null) {
            //css   .class1.class2 + toRightOf
            driver.findElement(with(By.cssSelector(".btn.btn_primary.btn_small")).toRightOf(priceWebElement)).click();
            //css   [attribute^=value]
            Assert.assertTrue(driver.findElement(By.cssSelector("[name^=remove]")).isDisplayed());

        } else {
            System.out.println("No price that is less tan 10$ is found");
        }
    }

    @Test(priority = 4)
    public void checkCart() {
        String productPriceAddedToCart = findElementWithPrice().getText();
        // ancestor
        String productNameAddedToCart = findElementWithPrice().findElement(By.xpath(".//ancestor::div[contains(@class,'description')]//div[@class='inventory_item_name']")).getText();
        // following
        WebElement shoppingCartLink = driver.findElement(By.xpath("//div[@class='header_label']/following::a"));
        shoppingCartLink.click();
        //Поиск по атрибуту, например By.xpath("//tag[@attribute='value']");
        Assert.assertEquals(driver.findElement(By.xpath("//span[@class='title']")).getText().toLowerCase(), "Your Cart".toLowerCase());
        //css [attribute$=value]
        String productNameShownInCart = driver.findElement(By.cssSelector("[class$=name]")).getText();
        // css  .class
        String productPriceShownInCart = driver.findElement(By.cssSelector(".inventory_item_price")).getText();
        //Поиск по тексту, например By.xpath("//tag[text()='text']");
        Assert.assertTrue(driver.findElement(By.xpath("//div[contains(text(),'1')]")).isDisplayed());
        Assert.assertEquals(productPriceShownInCart, productPriceAddedToCart);
        Assert.assertEquals(productNameShownInCart, productNameAddedToCart);
    }

    public WebElement findElementWithPrice() {
        WebElement priceWebElement = null;
        //css  tagname.class
        List<WebElement> prices = driver.findElements(By.cssSelector("div.inventory_item_price"));
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
