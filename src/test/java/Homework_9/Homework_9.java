package Homework_9;

import BaseObjects.BaseTest;
import PageObject.saucedemo.*;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Homework_9 extends BaseTest {

    @BeforeTest
    public void preconditions() {
        driver.get(context.getSuite().getParameter("url"));
    }

    @Test (priority = 1)
    public void logInAsStandardUser() {
        get(MainPage.class)
                .logInWithCreatedUser(1);
        get(ProductsPage.class)
                .isOpened();
    }

    @Test (priority = 2)
    public void addToCart() {
        get(ProductsPage.class)
                .addToCartProductWithPriceFromRange(5,10);
        get(BasePageDemo.class)
                .goToShoppingCartLink();
    }

    @Test (priority = 3)
    public void checkout() {
        get(ShoppingCartPage.class)
                .goToCheckoutPage();
        get(CheckoutPage.class)
                .fillUserDataForCheckout("Alesya","Pesetskaya", "1111");
        get(CheckoutOverviewPage.class)
                .finishCheckout();
    }

    @Test (priority = 4)
    public void removeFromCart() {
        get(MainPage.class)
                .openEmptyMainPage();
        logInAsStandardUser();
        addToCart();
        get(ShoppingCartPage.class)
                .removeFromCart();
    }


    @Test (priority = 5)
    public void logInAsLockedOutUser() {
        get(MainPage.class)
                .openEmptyMainPage()
                .logInWithCreatedUser(2);
        Assert.assertEquals(get(MainPage.class).getErrorText(),"Epic sadface: Sorry, this user has been locked out.");
    }

    @Test (priority = 6)
    public void logInAsProblemUser() {
        get(MainPage.class)
                .openEmptyMainPage()
                .logInWithCreatedUser(3);
        get(ProductsPage.class)
                .isOpened();
    }

    @Test (priority = 7)
    public void logInAsPerformanceUser() {
        get(MainPage.class)
                .openEmptyMainPage()
                .logInWithCreatedUser(4);
        get(ProductsPage.class)
                .isOpened();
    }

    @Test (priority = 8)
    public void logInAsRandomUser() {
        get(MainPage.class)
                .openEmptyMainPage()
                .logInWithRandomUser("Alesya","1234");
        Assert.assertEquals(get(MainPage.class).getErrorText(),"Epic sadface: Username and password do not match any user in this service");
    }

    @Test (priority = 9)
    public void logInWithEmptyPassword() {
        get(MainPage.class)
                .openEmptyMainPage()
                .logInWithRandomUser("Alesya","");
        Assert.assertEquals(get(MainPage.class).getErrorText(),"Epic sadface: Password is required");
    }

    @Test (priority = 10)
    public void logInWithEmptyUsername() {
        get(MainPage.class)
                .openEmptyMainPage()
                .logInWithRandomUser("","1234");
        Assert.assertEquals(get(MainPage.class).getErrorText(),"Epic sadface: Username is required");
    }

}
