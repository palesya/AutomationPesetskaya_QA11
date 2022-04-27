package Driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static Properties.PropertyReader.getProperties;

public class ChromeDriverManager extends DriverManager{
    @Override
    public void createDriver() {
        WebDriver webDriver;
        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions=new ChromeOptions();
        if(getProperties().containsKey("chrome.options")){
            chromeOptions.addArguments(getProperties().getProperty("chromeOptions").split(","));
            webDriver=new ChromeDriver(chromeOptions);
        }else{
            webDriver=new ChromeDriver();
        }
        driver.set(webDriver);
    }
}
