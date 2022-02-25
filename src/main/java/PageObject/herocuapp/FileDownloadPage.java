package PageObject.herocuapp;

import PageObject.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.io.File;
import java.util.List;

public class FileDownloadPage extends BasePage{

    private By imgLink = By.xpath("//div[@class='example']/a");

    public FileDownloadPage checkDownload(int numberOfImage) {
    List<WebElement> list = driver.findElements(imgLink);
        list.get(numberOfImage-1).click();
        try {
            Thread.sleep( 10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        File folder = new File(System.getProperty("user.dir"));
        File[] listOfFiles = folder.listFiles();
        File file = null;
        boolean found = false;
        for (File listOfFile : listOfFiles) {
            if (listOfFile.isFile()) {
                String fileName = listOfFile.getName();
                if (fileName.matches(driver.findElement(imgLink).getText())) {
                    found = true;
                    file = new File(fileName);
                    System.out.println(fileName+" was downloaded");
                }
            }
        }
        Assert.assertTrue(found);
        file.deleteOnExit();
        return this;
    }
}
