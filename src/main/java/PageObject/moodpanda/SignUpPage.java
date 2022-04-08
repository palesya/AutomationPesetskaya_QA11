package PageObject.moodpanda;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import lombok.extern.log4j.Log4j;
import org.openqa.selenium.By;

import java.util.Objects;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.WebDriverRunner.url;

@Log4j
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
        log.debug("Informing text is checked");
        return this;
    }

    public SignUpPage enterFirstName(String fName) {
        firstName.should(visible);
        firstName.clear();
        firstName.sendKeys(fName);
        log.debug("Data for first name was sent: "+fName);
        return this;
    }

    public SignUpPage enterLastName(String lName) {
        lastName.clear();
        lastName.sendKeys(lName);
        log.debug("Data for last name was sent: "+lName);
        return this;
    }

    public SignUpPage enterEmail(String mail) {
        email.clear();
        email.sendKeys(mail);
        log.debug("Data for email was sent: "+mail);
        return this;
    }

    public SignUpPage enterPassword(String pswrd) {
        password.clear();
        password.sendKeys(pswrd);
        log.debug("Data for password was sent: "+pswrd);
        return this;
    }

    public SignUpPage checkIAmOver16() {
        if(Objects.equals(checkbox.getAttribute("checked"), "false")) {
            checkbox.click();
        }
        log.debug("Checkbox ImOver16 is checked");
        return this;
    }

    public SignUpPage uncheckIAmOver16() {
        if(Objects.equals(checkbox.getAttribute("checked"), "true")) {
            checkbox.click();
        }
        log.debug("Checkbox ImOver16 is unchecked");
        return this;
    }

    public SignUpPage clickSignUpButton() {
        signUp.click();
        log.debug("Sign up button was licked");
        return this;
    }

   public SignUpPage checkLabelQuestion(String question) {
        labelQuestion.shouldBe(Condition.text(question));
       log.debug("Label question is checked");
        return this;
    }

    public SignUpPage checkAlertIfUncheckedCheckbox(String alert) {
        alertLessThen16.shouldBe(Condition.text(alert));
        log.debug("Alert is checked");
        return this;
    }
}
