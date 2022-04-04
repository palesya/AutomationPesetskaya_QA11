package PageObject.moodpanda;

import com.codeborne.selenide.SelenideElement;
import lombok.extern.log4j.Log4j;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.WebDriverRunner.url;
@Log4j
public class LoginPage {

    SelenideElement password = $("[type='password']");
    SelenideElement userName = $("[type='text']");
    SelenideElement login = $("[class^='button is-v']");
    SelenideElement emailException = $(byXpath("//*[contains(@class,'danger is-small')]"));

    public LoginPage clickLogin() {
        login.click();
        log.debug("Login was clicked. Current url is: "+url());
        return this;
    }

    public LoginPage enterUsername(String name) {
        userName.sendKeys(name);
        log.debug("Data for username was sent: "+name);
        return this;
    }

    public LoginPage enterPassword(String pswrd) {
        password.sendKeys(pswrd);
        log.debug("Data for password was sent: "+pswrd);
        return this;
    }

    public LoginPage checkEmailException() {
        emailException.shouldHave(text("Invalid email address"));
        log.debug("Email exception was checked");
        return this;
    }

}
