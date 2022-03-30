package PageObject.moodpanda;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class HomePage {
    SelenideElement getStarted = $("[class^='button is-white']");
    SelenideElement signUp = $(byXpath("//*[@id='navMenu']//*[@href='/signup']"));

    public HomePage clickGetStarted() {
        getStarted.click();
        $(getStarted).should(disappear);
        return this;
    }

    public HomePage clickSignUp() {
        signUp.click();
        return this;
    }
}
