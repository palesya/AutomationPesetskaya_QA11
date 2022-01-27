package Homework_5;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class Task_5 {

    WebDriver driver = null;

    @BeforeTest
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().fullscreen();
    }

    @Test(dataProvider = "data")
    public void calcLaminate(FormData formData, List<String> expectedResults) {
        driver.get("https://masterskayapola.ru/kalkulyator/laminata.html");
        //Web elements
        WebElement roomwidth = driver.findElement(By.name("calc_roomwidth"));
        WebElement roomheight = driver.findElement(By.name("calc_roomheight"));
        WebElement lamwidth = driver.findElement(By.name("calc_lamwidth"));
        WebElement lamheight = driver.findElement(By.name("calc_lamheight"));
        WebElement inpack = driver.findElement(By.name("calc_inpack"));
        WebElement price = driver.findElement(By.name("calc_price"));
        WebElement direct = driver.findElement(By.name("calc_direct"));
        Select selectDirection = new Select(direct);
        WebElement bias = driver.findElement(By.name("calc_bias"));
        WebElement walldist = driver.findElement(By.name("calc_walldist"));
        WebElement calcButton = driver.findElement(By.cssSelector("[value='Рассчитать']"));

        List<WebElement> results = driver.findElements(By.xpath("//div[@class='col-xs-12 col-sm-12 whiteback']//div[@class='col-xs-12 col-sm-12']"));

        //Actions
        clearField(roomwidth);
        roomwidth.sendKeys(formData.rWidth);
        clearField(roomheight);
        roomheight.sendKeys(formData.rHeight);
        clearField(lamwidth);
        lamwidth.sendKeys(formData.lWidth);
        clearField(lamheight);
        lamheight.sendKeys(formData.lHeight);
        clearField(inpack);
        inpack.sendKeys(formData.pack);
        clearField(price);
        price.sendKeys(formData.pr);
        selectDirection.selectByValue(formData.direction);
        clearField(bias);
        bias.sendKeys(formData.bi);
        clearField(walldist);
        walldist.sendKeys(formData.wDist);
        calcButton.click();

        //Results
        /*results.forEach(element -> {
            String data = element.getText();
            System.out.println(data);
        });*/

        List<String> actualData = new ArrayList<>() {{
            results.forEach((element) -> add(element.getText()));
        }};
        Assert.assertEquals(actualData, expectedResults);

    }

    @DataProvider(name = "data")
    private Object[][] getData() {
        FormData data1 = new FormData("6", "4", "1200", "192", "12", "500", "tow", "300", "10");
        FormData data2 = new FormData("3", "8", "1300", "208", "18", "800", "toh", "200", "12");
        FormData data3 = new FormData("4", "3", "1500", "300", "20", "1000", "toh", "250", "15");
        CalculatedResults results1 = new CalculatedResults("23.80", "106", "9", "12441", "2", "8");
        CalculatedResults results2 = new CalculatedResults("23.74", "93", "6", "23362", "15", "3");
        CalculatedResults results3 = new CalculatedResults("11.79", "30", "2", "18000", "10", "7");
        return new Object[][]{
                {data1, results1.getResults()},
                {data2, results2.getResults()},
                {data3, results3.getResults()},
        };
    }

    @AfterTest
    public void close() {
        driver.close();
        driver.quit();
    }

    private void clearField(WebElement element){
        element.click();
        do{
            element.sendKeys(Keys.BACK_SPACE);
        }
        while(!element.getAttribute("value").isEmpty());
    }
}
