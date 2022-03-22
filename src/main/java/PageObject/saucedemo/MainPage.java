package PageObject.saucedemo;

import Entity.User;
import PageObject.BasePage;
import org.openqa.selenium.By;
import org.testng.Assert;

public class MainPage extends BasePage {

    private By userName = By.id("user-name");
    private By password = By.id("password");
    private By loginButton = By.xpath("//*[@type='submit']");
    private By passwords = By.className("login_password");
    private By shoppingCartLink = By.className("shopping_cart_link");

    public enum TypeOfUser {
        STANDARD("standard_user"),
        LOCKED_OUT("locked_out_user"),
        PROBLEM("problem_user"),
        PERFORMANCE_GLITCH("performance_glitch_user");

        String login;

        TypeOfUser(String login) {
            this.login = login;
        }

        public String getLogin() {
            return login;
        }
    }

    public MainPage open() {
        super.open();
        return this;
    }

    public void logInWithCreatedUser(TypeOfUser typeOfUser) {
        String allPasswords = getText(passwords);
        String[] passwordString = allPasswords.split("\\r?\\n");
        enter(userName, true, typeOfUser.getLogin());
        enter(password, true, passwordString[1]);
        clickButton(loginButton);
    }

    public void logInWithRandomUser(User user) {
        enter(userName, user.getUserName());
        enter(password, user.getPassword());
        clickButton(loginButton);
    }

    public String getErrorText() {
        return driver.findElement(By.xpath("//h3[@data-test='error']")).getText();
    }

    public void compareErrorTextWithExpected(String error){
        Assert.assertEquals(getErrorText(),properties.getProperty(error));
    }


    public void goToShoppingCartLink() {
        clickButton(shoppingCartLink);
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/cart.html");
    }

}
