package Homework_13;

import BaseObjects.BaseTest;
import PageObject.herocuapp.JavaScriptAlertsPage;
import org.testng.annotations.Test;

import static PageObject.herocuapp.JavaScriptAlertsPage.Button.PROMPT;

public class JavaScriptAlerts_Test extends BaseTest {

    @Test
    public void jsPrompt_Test() {
        get(JavaScriptAlertsPage.class)
                .verifyTitle()
                .clickOnButton(PROMPT)
                .checkAlertsText("I am a JS prompt")
                .enterText("Hello")
                .closeAlert()
                .checkEnteredText("Hello");
    }

}
