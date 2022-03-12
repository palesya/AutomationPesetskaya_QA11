package Lecture_13;

import BaseObjects.BaseTest;
import PageObject.herocuapp.FramesPage;
import org.testng.annotations.Test;

public class Frames_Test extends BaseTest {

    @Test
    public void iFrameTest() {
        get(FramesPage.class)
                .verifyTitle("Frames")
                .clickOnFrameLink("iFrame")
                .verifyTitle("An iFrame containing the TinyMCE WYSIWYG Editor")
                .switchToFrame()
                .enterTextArea("Hello")
                .unSwitchFrame();
    }

}
