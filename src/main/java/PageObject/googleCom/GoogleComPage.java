package PageObject.googleCom;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class GoogleComPage {

    private By inputField = By.xpath("//*[@type='text']");
    private By searchButton = By.xpath("//div[@class='FPdoLc lJ9FBc']//*[@name='btnK']");
    private By signInButton = By.xpath("//a[@class='gb_1 gb_2 gb_6d gb_6c']");


    public WebDriver driver;

    public GoogleComPage(WebDriver driver) {
        this.driver = driver;
    }

    public void searchData(String data) {
        driver.findElement(inputField).clear();
        driver.findElement(inputField).sendKeys(data);
        driver.findElement(searchButton).click();
        Assert.assertTrue(driver.getCurrentUrl().contains("https://www.google.com/search?"));

    }

    public void goToSignInPage() {
        driver.findElement(signInButton).click();
        Assert.assertTrue(driver.getCurrentUrl().contains("accounts.google.com/signin"));
    }

}
