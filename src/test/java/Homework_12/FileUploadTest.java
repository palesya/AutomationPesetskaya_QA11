package Homework_12;

import BaseObjects.BaseTest;
import PageObject.herocuapp.FileUploadPage;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class FileUploadTest extends BaseTest {

    @Parameters({"filepath"})
    @Test(description = "Check file's uploading")
    public void fileUpload_Test(String filepath) {
        get(FileUploadPage.class)
                .checkUpload(filepath);
    }
}
