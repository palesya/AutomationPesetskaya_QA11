package Lecture_18;

import org.testng.annotations.Test;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Configuration.browserSize;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.*;

public class Lecture_18 {
    @Test
    public void moodPandaHomePage_test() {
        browserSize="1000x1000";
        open("https://moodpanda.com/");
        $("[class^='button is-white']").click();
        $("[type='text']").sendKeys("user");
        $("[type='password']").sendKeys("password");
        System.out.println($("[class^='button is-v']").find("span").getText());
        $("[class^='button is-v']").should(exist);
        $("[class^='button is-v']").click();
        $(byXpath("//*[contains(@class,'danger is-small')]")).shouldHave(text("Invalid email address"));
        System.out.println($$("[class='button is-small is-fullwidth is-rounded is-light']").texts());
        $$("[class='button is-small is-fullwidth is-rounded is-light']").should(size(3));
    }
}
