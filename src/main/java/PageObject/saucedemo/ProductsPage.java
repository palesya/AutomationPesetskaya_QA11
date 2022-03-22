package PageObject.saucedemo;

import PageObject.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;
import java.util.Objects;

import static org.openqa.selenium.support.locators.RelativeLocator.with;

public class ProductsPage extends BasePage {

    private By addOrRemoveButton = By.xpath("//div[@id='inventory_container']//button");
    private By price = By.cssSelector("div.inventory_item_price");
    private By productName = By.className("inventory_item_name");

    public ProductsPage isOpened() {
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html");
        return this;
    }

    public void addToCartProductWithPriceFromRange(int minPrice, int maxPrice) {
        List<WebElement> prices = driver.findElements(price);
        WebElement priceWebElement;
        double selectedPrice;
        String buttonName;
        int counterOfSuitableProducts = 0;
        for (WebElement price : prices) {
            selectedPrice = Double.parseDouble(price.getText().substring(1));
            if (selectedPrice <= maxPrice & selectedPrice >= minPrice) {
                priceWebElement = price;
                buttonName = driver.findElement(with(addOrRemoveButton).toRightOf(priceWebElement)).getText();
                if (Objects.equals(buttonName, "ADD TO CART")) {
                    driver.findElement(with(addOrRemoveButton).toRightOf(priceWebElement)).click();
                    String nameOfAddedProduct = driver.findElement(with(productName).above(priceWebElement)).getText();
                    System.out.println(nameOfAddedProduct + " with price " + selectedPrice + "$ was addet to Cart.");
                    counterOfSuitableProducts++;
                } else {
                    continue;
                }
                break;
            }
        }
        if(counterOfSuitableProducts==0){
            System.out.println("No available for adding product with price from the range " + minPrice + "-" + maxPrice + "$ is found");
        }
    }
}
