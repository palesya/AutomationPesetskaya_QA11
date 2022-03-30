package PageObject.moodpanda;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class LoginPage {

    SelenideElement password = $("[type='password']");
    SelenideElement userName = $("[type='text']");
    SelenideElement login = $("[class^='button is-v']");
    SelenideElement emailException = $(byXpath("//*[contains(@class,'danger is-small')]"));

    public LoginPage clickLogin() {
        login.click();
        return this;
    }

    public LoginPage enterUsername(String name) {
        userName.sendKeys(name);
        return this;
    }

    public LoginPage enterPassword(String pswrd) {
        password.sendKeys(pswrd);
        return this;
    }

    public LoginPage checkEmailException() {
        emailException.shouldHave(text("Invalid email address"));
        return this;
    }

}
