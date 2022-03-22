package PageObject.saucedemo;

import Entity.CheckoutData;
import PageObject.BasePage;
import org.openqa.selenium.By;
import org.testng.Assert;

public class CheckoutPage extends BasePage {

    private By firstName = By.id("first-name");
    private By lastName = By.id("last-name");
    private By zipPostalCode = By.id("postal-code");
    private By continueButton = By.id("continue");

    public void fillUserDataForCheckout(CheckoutData data) {
        enter(firstName,true, data.getFirstName());
        enter(lastName,true,data.getLastName());
        enter(zipPostalCode,true,data.getZipPostalCode());
        clickButton(continueButton);
        compareCurrentUrlWithExpected(properties.getProperty("urlCheckoutStep2"));
    }

}
