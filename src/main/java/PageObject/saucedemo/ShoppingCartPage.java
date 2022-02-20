package PageObject.saucedemo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;
import java.util.Objects;

import static org.openqa.selenium.support.locators.RelativeLocator.with;

public class ShoppingCartPage extends BasePageDemo {

    private By removeButton = By.xpath("//button[text()='Remove']");
    private By checkoutButton = By.id("checkout");
    private By productName = By.className("inventory_item_name");
    private By continueShoppingButton = By.id("continue-shopping");
    private By price = By.cssSelector("div.inventory_item_price");

    public ShoppingCartPage(WebDriver driver) {
        super(driver);
    }

    public void removeFromCart() {
        checkIfProductAdded();
        int numberOfRemoveButtons = driver.findElements(removeButton).size();
        String nameOfAddedProduct = driver.findElement(with(productName).above(removeButton)).getText();
        driver.findElement(removeButton).click();
        System.out.println("Product " + nameOfAddedProduct + " is removed");
        int numberOfRemoveButtonsAfterRemoving = driver.findElements(removeButton).size();
        Assert.assertEquals(numberOfRemoveButtonsAfterRemoving, numberOfRemoveButtons - 1);
    }

    public void removeFromCartWithExactPrice(int minPrice, int maxPrice) {
        checkIfProductAdded();
        int numberOfRemoveButtons = driver.findElements(removeButton).size();
        List<WebElement> prices = driver.findElements(price);
        WebElement priceWebElement;
        double selectedPrice;
        int counterOfSuitableProducts=0;
        for (WebElement price : prices) {
            selectedPrice = Double.parseDouble(price.getText().substring(1));
            if (selectedPrice <= maxPrice & selectedPrice >= minPrice) {
                priceWebElement = price;
                driver.findElement(with(removeButton).toRightOf(priceWebElement)).click();
                String nameOfAddedProduct = driver.findElement(with(productName).above(priceWebElement)).getText();
                System.out.println("Product " + nameOfAddedProduct + " with price "+price.getText()+" was removed");
                counterOfSuitableProducts++;
                break;
            }
        }
        if(counterOfSuitableProducts==0) {
            System.out.println("No product with price from the range " + minPrice + "-" + maxPrice + "$ is found");
        }
        int numberOfRemoveButtonsAfterRemoving = driver.findElements(removeButton).size();
        Assert.assertEquals(numberOfRemoveButtonsAfterRemoving, numberOfRemoveButtons - 1);
    }

    public void goToCheckoutPage() {
        checkIfProductAdded();
        driver.findElement(checkoutButton).click();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/checkout-step-one.html");
    }

    public void checkIfProductAdded() {
        int productAdded = driver.findElements(removeButton).size();
        if (productAdded == 0) {
            System.out.println("No product is added to the shopping cart");
        }
    }
}
