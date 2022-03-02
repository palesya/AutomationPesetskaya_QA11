package Homework_12;

import BaseObjects.BaseTest;
import PageObject.herocuapp.ContextMenuPage;
import org.testng.annotations.Test;

public class ContextMenuTest extends BaseTest {

    @Test(description="Right click os Spot")
    private void ContextMenu_Test (){
        get(ContextMenuPage.class)
                .rightClickOnSpot();
    }

}
