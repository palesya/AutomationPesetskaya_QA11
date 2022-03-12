package Lecture_13;

import BaseObjects.BaseTest;
import PageObject.herocuapp.JavaScriptAlertsPage;
import org.testng.annotations.Test;

import static PageObject.herocuapp.JavaScriptAlertsPage.Button.*;

public class JavaScriptAlerts_Test extends BaseTest {

    @Test
    public void alert_Test() {
        get(JavaScriptAlertsPage.class)
                .verifyTitle()
                .clickOnButton(ALERT)
                .checkAlertsText("I am a JS Alert")
                .closeAlert();
    }

}
