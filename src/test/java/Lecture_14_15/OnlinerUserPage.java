package Lecture_14_15;

import PageObject.BasePage;
import org.openqa.selenium.By;

public class OnlinerUserPage extends BasePage {
    private By email = By.xpath("//input[@type='email']");
    private By password = By.xpath("//*[@type='password']");
    private By repeatPassword = By.xpath("//*[@autocomplete='repeatPassword']//input");


    public OnlinerUserPage auth(String email, String password) {
        enter(this.email, email);
        enter(this.password, password);
        return this;
    }

    public OnlinerUserPage open() {
        super.open();
        return this;
    }


    public OnlinerUserPage auth(OnlinerUser user) {
        enter(this.email, user.email);
        enter(this.password, user.password);
        return this;
    }

    public OnlinerUserPage register(String email, String password, String repeatPassword) {
        enter(this.email, email);
        enter(this.password, password);
        enter(this.repeatPassword, repeatPassword);
        return this;
    }

    public OnlinerUserPage register(OnlinerUser user) {
        enter(this.email, user.email);
        enter(this.password, user.password);
        enter(this.repeatPassword, user.repeatPassword);
        return this;
    }
}
