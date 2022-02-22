package test;

import BaseObjects.BaseTest;
import PageObject.googleCom.GoogleComPage;
import PageObject.herocuapp.AbTestPage;
import PageObject.saucedemo.MainPage;
import PageObject.saucedemo.ProductsPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class GoogleCom extends BaseTest {


    @BeforeTest
    public void preconditions() {
        driver.get("https://www.google.com/");
    }

    @Test(priority = 2)
    public void search() {
        get(GoogleComPage.class)
                .searchData("Alesya");
    }

    @Test(priority = 3)
    public void signIn() {
        get(GoogleComPage.class)
                .goToSignInPage();
    }
}
