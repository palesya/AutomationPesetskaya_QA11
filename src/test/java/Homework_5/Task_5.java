package Homework_5;

import Lecture_5.FormData;
import org.openqa.selenium.By;
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
    public void calcLaminate(FormData formData, List<String> expectedData) {
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
        roomwidth.click();
        roomwidth.sendKeys("\b\b\b\b");
        roomwidth.sendKeys(formData.rWidth);
        roomheight.click();
        roomheight.sendKeys("\b\b\b\b");
        roomheight.sendKeys(formData.rHeight);
        lamwidth.click();
        lamwidth.sendKeys("\b\b\b\b");
        lamwidth.sendKeys(formData.lWidth);
        lamheight.click();
        lamheight.sendKeys("\b\b\b\b");
        lamheight.sendKeys(formData.lHeight);
        inpack.click();
        inpack.sendKeys("\b\b\b\b");
        inpack.sendKeys(formData.pack);
        price.click();
        price.sendKeys("\b\b\b\b");
        price.sendKeys(formData.pr);
        selectDirection.selectByValue(formData.direction);
        bias.click();
        bias.sendKeys("\b\b\b\b");
        bias.sendKeys(formData.bi);
        walldist.click();
        walldist.sendKeys("\b\b\b\b");
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
        Assert.assertEquals(actualData, expectedData);

    }

    @DataProvider(name = "data")
    private Object[][] getData() {
        FormData data1 = new FormData("6", "4", "1200", "192", "12", "500", "tow", "300", "10");
        FormData data2 = new FormData("3", "8", "1300", "208", "18", "800", "toh", "200", "12");
        FormData data3 = new FormData("4", "3", "1500", "300", "20", "1000", "toh", "250", "15");
        return new Object[][]{
                {data1, new ArrayList<String>() {{
                    add("Площадь укладки: 23.80 м2.");
                    add("Кол-во панелей: 106 шт.");
                    add("Кол-во упаковок: 9 шт.");
                    add("Стоимость: 12441 руб.");
                    add("Остатки: 2 шт.");
                    add("Отрезки: 8 шт.");
                }}},
                {data2, new ArrayList<String>() {{
                    add("Площадь укладки: 23.74 м2.");
                    add("Кол-во панелей: 93 шт.");
                    add("Кол-во упаковок: 6 шт.");
                    add("Стоимость: 23362 руб.");
                    add("Остатки: 15 шт.");
                    add("Отрезки: 3 шт.");
                }}},
                {data3, new ArrayList<String>() {{
                    add("Площадь укладки: 11.79 м2.");
                    add("Кол-во панелей: 30 шт.");
                    add("Кол-во упаковок: 2 шт.");
                    add("Стоимость: 18000 руб.");
                    add("Остатки: 10 шт.");
                    add("Отрезки: 7 шт.");
                }}},
        };
    }

    @AfterTest
    public void close() {
        driver.close();
        driver.quit();
    }
}
