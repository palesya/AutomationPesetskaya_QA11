package test;

import BaseObjects.BaseTest;
import PageObject.herocuapp.DragAndDropPage;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class DragAndDrop_Test extends BaseTest {

    @Parameters({"linkName"})
    @Test
    public void dragAndDropPage_Test() throws InterruptedException {
        get(DragAndDropPage.class)
                .verifyTitle()
                .checkA_BLocation()
                .dragAndDropAction()
                .checkB_ALocation();
    }
}
