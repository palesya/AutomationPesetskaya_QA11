package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public abstract class BasePage {

    public WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    private void pause(long timeout){
        try {
            Thread.sleep(timeout*1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

}
