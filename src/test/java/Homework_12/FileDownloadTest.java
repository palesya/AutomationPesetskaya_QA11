package Homework_12;

import BaseObjects.BaseTest;
import PageObject.herocuapp.FileDownloadPage;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class FileDownloadTest extends BaseTest{

    @Parameters({"linkName"})
    @Test(description = "Check file's downloading")
    public void fileDownload_Test() {
        get(FileDownloadPage.class)
        .checkDownload(1);
    }
}
