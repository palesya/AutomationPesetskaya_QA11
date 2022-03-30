package PageObject.moodpanda;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import java.util.Objects;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

public class SignUpPage {

    SelenideElement firstName = $("[placeholder^='Your first']");
    SelenideElement lastName = $("[placeholder^='e']");
    SelenideElement password = $("[type='password']");
    SelenideElement email = $("[placeholder^='Your email']");
    SelenideElement checkbox = $("[type='checkbox']");
    SelenideElement signUp = $("[class$='right']");
    SelenideElement informingText=$(By.xpath("//div/small"));
    SelenideElement labelQuestion = $("[class='label']");
    SelenideElement alertLessThen16 = $("[class$='small']");

    public SignUpPage checkInformingText(String infoText) {
        informingText.shouldBe(text(infoText));
        return this;
    }

    public SignUpPage enterFirstName(String fName) {
        firstName.should(visible);
        firstName.clear();
        firstName.sendKeys(fName);
        return this;
    }

    public SignUpPage enterLastName(String lName) {
        lastName.clear();
        lastName.sendKeys(lName);
        return this;
    }

    public SignUpPage enterEmail(String mail) {
        email.clear();
        email.sendKeys(mail);
        return this;
    }

    public SignUpPage enterPassword(String pswrd) {
        password.clear();
        password.sendKeys(pswrd);
        return this;
    }

    public SignUpPage checkIAmOver16() {
        if(Objects.equals(checkbox.getAttribute("checked"), "false")) {
            checkbox.click();
        }
        return this;
    }

    public SignUpPage uncheckIAmOver16() {
        if(Objects.equals(checkbox.getAttribute("checked"), "true")) {
            checkbox.click();
        }
        return this;
    }

    public SignUpPage clickSignUpButton() {
        signUp.click();
        return this;
    }

   public SignUpPage checkLabelQuestion(String question) {
        labelQuestion.shouldBe(Condition.text(question));
        return this;
    }

    public SignUpPage checkAlertIfUncheckedCheckbox(String alert) {
        alertLessThen16.shouldBe(Condition.text(alert));
        return this;
    }
}
