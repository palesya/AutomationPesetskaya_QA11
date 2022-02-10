package PageObject.saucedemo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage extends BasePageDemo {

    private By userName = By.id("user-name");
    private By password = By.id("password");
    private By loginButton = By.xpath("//*[@type='submit']");
    private By usernames = By.id("login_credentials");
    private By passwords = By.className("login_password");

    public MainPage(WebDriver driver) {
        super(driver);
    }

    public void logInWithCreatedUser(int userId) {
        String userNames = driver.findElement(usernames).getText();
        String[] userNameArray = userNames.split("\\r?\\n");
        String allPasswords = driver.findElement(passwords).getText();
        String[] pasword = allPasswords.split("\\r?\\n");
        driver.findElement(userName).sendKeys(userNameArray[userId]);
        driver.findElement(password).sendKeys(pasword[1]);
        driver.findElement(loginButton).click();
    }

    public void logInWithRandomUser(String name, String pswrd) {
        driver.findElement(userName).sendKeys(name);
        driver.findElement(password).sendKeys(pswrd);
        driver.findElement(loginButton).click();
    }

    public MainPage openEmptyMainPage() {
        driver.get("https://www.saucedemo.com/");
        driver.findElement(userName).clear();
        driver.findElement(password).clear();
        return this;
    }

    public String getErrorText() {
        return driver.findElement(By.xpath("//h3[@data-test='error']")).getText();
    }

}
