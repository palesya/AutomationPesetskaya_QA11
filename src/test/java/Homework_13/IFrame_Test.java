package Homework_13;

import BaseObjects.BaseTest;
import PageObject.herocuapp.FramesPage;
import org.testng.annotations.Test;

public class IFrame_Test extends BaseTest {

    @Test
    public void iFrameTest() {
        get(FramesPage.class)
                .clickOnFrameLink("iFrame")
                .switchToFrame()
                .checkTextInTextArea("Your content goes here.")
                .unSwitchFrame();
    }

}
