package Homework_10;

import BaseObjects.BaseTest;
import PageObject.saucedemo.MainPage;
import PageObject.saucedemo.ProductsPage;
import PageObject.saucedemo.ShoppingCartPage;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.annotations.*;

public class Homework_10 extends BaseTest {

    @BeforeTest
    public void preconditions() {
        driver.get(context.getSuite().getParameter("url"));
        driver.manage().window().maximize();
    }

    @Test(priority = 1,timeOut = 1000,groups = "login", description = "Log in page")
    @Step("Log in as standard User")
    @Attachment(value = "screenshot", type = "png")
    public void logInAsStandardUser() {
        get(MainPage.class)
                .logInWithCreatedUser(1);
        get(ProductsPage.class)
                .isOpened();
    }

    @Test(priority = 6,groups = "login", description = "Log in page")
    @Step("Log in with empty Username field")
    @Attachment(value = "screenshot", type = "image/png")
    public void logInWithEmptyUsername() {
        get(MainPage.class)
                .openEmptyMainPage()
                .logInWithRandomUser("", "1234");
        Assert.assertEquals(get(MainPage.class).getErrorText(), "Epic sadface: Username is required");
    }

    @Test(priority = 7,groups = "login", description = "Log in page")
    @Step("Log in with empty Password field")
    @Attachment(value = "screenshot", type = "image/png")
    public void logInWithEmptyPassword() {
        get(MainPage.class)
                .openEmptyMainPage()
                .logInWithRandomUser("Alesya", "");
        Assert.assertEquals(get(MainPage.class).getErrorText(), "Epic sadface: Password is required");
    }

    @Test(priority = 8,groups = "login", description = "Log in page")
    @Step("Log in with not registered User")
    @Attachment(value = "screenshot", type = "image/png")
    public void logInAsNotRegisteredUser() {
        get(MainPage.class)
                .openEmptyMainPage()
                .logInWithRandomUser("Alesya", "1234");
        Assert.assertEquals(get(MainPage.class).getErrorText(), "Epic sadface: Username and password do not match any user in this service");
    }

    @Test(priority = 4, dependsOnMethods = "logInAsStandardUser", invocationCount = 3, invocationTimeOut = 2000, description = "Add to cart")
    @Step("Add product to cart 3 times using InvocationCount")
    @Attachment(value = "screenshot", type = "image/png")
    public void addToCartThroughInvocationCount() {
        get(ProductsPage.class)
                .addToCartProductWithPriceFromRange(5, 50);
    }

    @Test(priority =5, dependsOnMethods = {"logInAsStandardUser", "addToCartThroughInvocationCount"}, invocationCount = 3, invocationTimeOut = 2000, description = "Remove from cart")
    @Step("Remove product from cart 3 times using InvocationCount")
    @Attachment(value = "screenshot", type = "image/png")
    public void removeFromCartThroughInvocationCount() {
        get(ShoppingCartPage.class)
                .removeFromCart();
    }

    @Test(priority = 2, dependsOnMethods = "logInAsStandardUser", dataProvider = "Min/Max prices", description = "Add to cart")
    @Step("Add product to cart 3 times using DataProvider using InvocationCount")
    @Attachment(value = "screenshot", type = "image/png")
    public void addToCartThroughDataProvider(int minPrice, int maxPrice) {
        get(ProductsPage.class)
                .addToCartProductWithPriceFromRange(minPrice, maxPrice);
    }

    @Test(priority = 3, dependsOnMethods = {"logInAsStandardUser", "addToCartThroughDataProvider"}, dataProvider = "Min/Max prices", description = "Remove from cart")
    @Step("Remove product from cart 3 times using DataProvider")
    @Attachment(value = "screenshot", type = "image/png")
    public void removeFromCartThroughDataProvider(int minPrice, int maxPrice) {
        get(ShoppingCartPage.class)
                .removeFromCartWithExactPrice(minPrice, maxPrice);
    }

    @Test(enabled = false)
    public void testIsDisabled() {
        System.out.println("Nothing");
    }

    @DataProvider(name = "Min/Max prices")
    public Object[][] inputData() {
        return new Object[][]{
                {0, 10},
                {10, 30},
                {30, 100}
        };
    }

}
