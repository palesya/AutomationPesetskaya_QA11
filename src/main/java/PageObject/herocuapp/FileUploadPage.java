package PageObject.herocuapp;

import PageObject.BasePage;
import org.openqa.selenium.By;
import org.testng.Assert;

import java.io.File;

public class FileUploadPage extends BasePage {

    private By chooseFileButton = By.id("file-upload");
    private By uploadButton = By.id("file-submit");
    private By nameOfUploadedFile = By.id("uploaded-files");

    public FileUploadPage checkUpload(String filepath){
        File file = new File(filepath);
        driver.findElement(chooseFileButton).sendKeys(file.getAbsolutePath());
        clickButton(uploadButton);
        Assert.assertEquals(getText(nameOfUploadedFile),file.getName());
        return this;
    }

}
