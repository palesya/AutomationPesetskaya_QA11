package Homework_9;

import BaseObjects.BaseTest;
import Entity.CheckoutData;
import Entity.User;
import PageObject.saucedemo.*;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static PageObject.saucedemo.MainPage.TypeOfUser.*;

public class Homework_9 extends BaseTest {

    @BeforeTest
    public void preconditions() {
        get(MainPage.class).open();
    }

    @Test (priority = 1)
    public void logInAsStandardUser() {
        get(MainPage.class)
                .logInWithCreatedUser(STANDARD);
        get(ProductsPage.class)
                .isOpened();
    }

    @Test (priority = 2)
    public void addToCart() {
        get(ProductsPage.class)
                .addToCartProductWithPriceFromRange(5,10);
        get(MainPage.class)
                .goToShoppingCartLink();
    }

    @Test (priority = 3)
    public void checkout() {
        get(ShoppingCartPage.class)
                .goToCheckoutPage();
        get(CheckoutPage.class)
                .fillUserDataForCheckout(new CheckoutData("Alesya","Pesetskaya", "1111"));
        get(CheckoutOverviewPage.class)
                .finishCheckout();
    }

    @Test (priority = 4)
    public void removeFromCart() {
        get(MainPage.class)
                .open();
        logInAsStandardUser();
        addToCart();
        get(ShoppingCartPage.class)
                .removeFromCart();
    }


    @Test (priority = 5)
    public void logInAsLockedOutUser() {
        get(MainPage.class)
                .open()
                .logInWithCreatedUser(LOCKED_OUT);
        get(MainPage.class).compareErrorTextWithExpected("errorForLockedOutUser");
    }

    @Test (priority = 6)
    public void logInAsProblemUser() {
        get(MainPage.class)
                .open()
                .logInWithCreatedUser(PROBLEM);
        get(ProductsPage.class)
                .isOpened();
    }

    @Test (priority = 7)
    public void logInAsPerformanceUser() {
        get(MainPage.class)
                .open()
                .logInWithCreatedUser(PERFORMANCE_GLITCH);
        get(ProductsPage.class)
                .isOpened();
    }

    @Test (priority = 8)
    public void logInAsRandomUser() {
        get(MainPage.class)
                .open()
                .logInWithRandomUser(new User("Alesya","1234"));
        get(MainPage.class).compareErrorTextWithExpected("errorForNotRegisteredUser");
    }

    @Test (priority = 9)
    public void logInWithEmptyPassword() {
        get(MainPage.class)
                .open()
                .logInWithRandomUser(new User("Alesya",""));
        get(MainPage.class).compareErrorTextWithExpected("errorForEmptyPassword");
    }

    @Test (priority = 10)
    public void logInWithEmptyUsername() {
        get(MainPage.class)
                .open()
                .logInWithRandomUser(new User("","1234"));
        get(MainPage.class).compareErrorTextWithExpected("errorForEmptyUsername");
    }

}
