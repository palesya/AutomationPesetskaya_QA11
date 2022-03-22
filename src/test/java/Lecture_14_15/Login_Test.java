package Lecture_14_15;

import BaseObjects.BaseTest;
import Entity.User;
import PageFactory.LoginPage;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Login_Test extends BaseTest {

    @BeforeMethod
    public void precondition() {
        get(LoginPage.class)
                .open();
    }

    @Test(priority = 1, enabled = false)
    public void loginPage_Test() {
        get(LoginPage.class)
                .enterUserName("bla")
                .enterPassword("bla")
                .clickLoginButton();
        Assert.assertEquals(get(LoginPage.class).getExceptionText(), "Epic sadface: Username and password do not match any user in this service");
    }

    @Test(priority = 2, dataProvider = "user")
    public void loginPageEntity_Test(User user) {
        get(LoginPage.class)
                .login(user);
        Assert.assertEquals(get(LoginPage.class).getExceptionText(), "Epic sadface: Username and password do not match any user in this service");
    }


    @DataProvider(name = "user")
    public Object[][] data() {
        return new Object[][]{
                {new User("bla", "bla")},
                {new User.UserBuilder().withUserName("bla").withPassword("bla").build()}
        };
    }
}
