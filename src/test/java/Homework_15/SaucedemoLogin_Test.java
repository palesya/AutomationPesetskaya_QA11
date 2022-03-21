package Homework_15;

import BaseObjects.BaseTest;
import Entity.User;
import PageFactory.LoginPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class SaucedemoLogin_Test extends BaseTest {

    @BeforeMethod
    public void precondition() {
        get(LoginPage.class)
                .open();
    }

    @Test(priority = 1, dataProvider = "user")
    public void loginWithCreatedUser_Test(User user) {
        get(LoginPage.class)
                .login(user);
        Assert.assertFalse(get(LoginPage.class).isErrorShown());
    }

    @DataProvider(name = "user")
    public Object[][] data() {
        return new Object[][]{
                {new User.userBuilder().withUsername("standard_user").withPassword("secret_sauce").build()}
        };
    }

}
