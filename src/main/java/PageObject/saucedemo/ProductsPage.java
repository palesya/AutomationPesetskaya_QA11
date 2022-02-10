package PageObject.saucedemo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

import static org.openqa.selenium.support.locators.RelativeLocator.with;

public class ProductsPage extends BasePageDemo {

    private By addToCartButton = By.cssSelector(".btn.btn_primary.btn_small");
    private By removeButton = By.cssSelector("[name^=remove]");
    private By price = By.cssSelector("div.inventory_item_price");
    private By filter = By.className("product_sort_container");
    private By productImage = By.className("inventory_item_img");


    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    public void isOpened() {
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html");
    }

    public void addToCartFirstProductWithPriceFromRange(int minPrice, int maxPrice) {
        WebElement priceWebElement = null;
        double selectedPrice = 0;
        List<WebElement> prices = driver.findElements(price);
        for (WebElement price : prices) {
            selectedPrice = Double.parseDouble(price.getText().substring(1));
            if (selectedPrice <= maxPrice & selectedPrice>=minPrice) {
                priceWebElement = price;
                break;
            }
        }
        if (priceWebElement != null) {
            driver.findElement(with(addToCartButton).toRightOf(priceWebElement)).click();
            Assert.assertTrue(driver.findElement(removeButton).isDisplayed());
            System.out.println("Product with price "+ selectedPrice+"$ was addet to Cart.");
        } else {
            System.out.println("No price from the range "+ minPrice+"-"+maxPrice+"$ is found");
        }
    }
}
