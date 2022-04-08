package PageObject.moodpanda;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.WebDriverRunner.url;

import lombok.extern.log4j.Log4j;

@Log4j
public class HomePage {
    SelenideElement getStarted = $("[class^='button is-white']");
    SelenideElement signUp = $(byXpath("//*[@id='navMenu']//*[@href='/signup']"));

    public HomePage clickGetStarted() {
        getStarted.click();
        getStarted.should(disappear);
        log.debug("Get Started was clicked. Current url is: "+url());
        return this;
    }

    public HomePage clickSignUp() {
        signUp.click();
        log.debug("Sign up was clicked. Current url is: "+url());
        return this;
    }
}
