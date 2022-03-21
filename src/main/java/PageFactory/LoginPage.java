package PageFactory;

import Entity.User;
import PageObject.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage {

    @FindBy(how = How.ID, using = "user-name")
    WebElement userName;

    @FindBy(id = "password")
    WebElement password;

    @FindBy(how = How.XPATH, using = "//input[@id='login-button']")
    WebElement loginButton;

    @FindBy(css = "[data-test='error']")
    WebElement errorMessage;

    public LoginPage() {
        PageFactory.initElements(super.driver, this);
    }

    public LoginPage open() {
        super.open();
        return this;
    }

    public LoginPage enterUserName(String userName) {
        log.debug("Enter username: " + userName);
        this.userName.sendKeys(userName);
        return this;
    }

    public LoginPage enterPassword(String password) {
        log.debug("Enter password: " + password);
        this.password.sendKeys(password);
        return this;
    }

    public LoginPage clickLoginButton() {
        log.debug("Click button: " + loginButton.getAttribute("value"));
        clickButton(loginButton);
        return this;
    }

    public LoginPage login(String userName, String password) {
        enterUserName(userName);
        enterPassword(password);
        clickLoginButton();
        return this;
    }

    public LoginPage login(User user) {
        enterUserName(user.getUserName());
        enterPassword(user.getPassword());
        clickLoginButton();
        return this;
    }

    public String getExceptionText() {
        log.debug("Error message: " + errorMessage.getText());
        return errorMessage.getText();
    }

    public boolean isErrorShown() {
       return isElementExists(errorMessage);
    }
}
