package Lecture_5;

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
import java.util.Locale;

public class Lecture_5 {

    WebDriver driver = null;

    @BeforeTest
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().fullscreen();
    }

    @Test
    public void smokeTest1() {
        driver.get("http://13gp.by/informatsiya/meditsinskie-kalkulyatory/994-raschet-indeksa-massy-tela");
        WebElement height = driver.findElement(By.name("ht"));
        WebElement weight = driver.findElement(By.name("mass"));
        WebElement calcButton = driver.findElement(By.cssSelector("[type='button']"));
        WebElement results = driver.findElement(By.name("result"));
        WebElement imt = driver.findElement(By.id("resline"));
        //Actions
        height.sendKeys("183");
        weight.sendKeys("58");
        calcButton.click();
        //Checks
        String actualResults = results.getAttribute("value");
        String actualValueIMT = imt.getText();
        Assert.assertEquals(actualResults, "17.32");
        Assert.assertEquals(actualValueIMT, "Пониженный вес");
    }

    @Test(dataProvider = "data")
    public void smokeTest2(String creatinin, String age, String weight, String height, List<String> expectedData) {
        driver.get("http://13gp.by/informatsiya/meditsinskie-kalkulyatory/995-raschet-skorosti-klubochkovoj-filtratsii-skf");
        //Web elements
        WebElement sex = driver.findElement(By.id("oSex"));
        Select selectSex = new Select(sex);
        WebElement oCr = driver.findElement(By.id("oCr"));
        WebElement oAge = driver.findElement(By.id("oAge"));
        WebElement oWeight = driver.findElement(By.id("oWeight"));
        WebElement oHeight = driver.findElement(By.id("oHeight"));
        WebElement calcBtn = driver.findElement(By.cssSelector("[value='Рассчитать']"));
        List<WebElement> results = driver.findElements(By.xpath("//form//li//div"));
        //Actions
        selectSex.selectByIndex(1);
        oCr.sendKeys(creatinin);
        oAge.sendKeys(age);
        oWeight.sendKeys(weight);
        oHeight.sendKeys(height);
        calcBtn.click();
        //Results
        List<String> actualData = new ArrayList<>() {{
            results.forEach((element) -> add(element.getText()));
        }};

        Assert.assertEquals(actualData, expectedData);
    }


    /**
     * @param data -input String data
     * @return Upper case string
     */
    public String getData(String data) {

        return data.toUpperCase(Locale.ROOT);
    }

    @DataProvider(name = "data")
    private Object[][] getData() {
        return new Object[][]{
                {"80", "38", "55", "163", new ArrayList<String>() {{
                    add("MDRD: 74 (мл/мин/1,73кв.м)");
                    add("ХБП: 2 стадия (при наличии почечного повреждения)");
                    add("Cockroft-Gault: 70 (мл/мин)");
                    add("Поверхность тела:1.58 (кв.м)");
                }}},
                {"90", "50", "65", "192", new ArrayList<String>() {{
                    add("MDRD: 61 (мл/мин/1,73кв.м)");
                    add("ХБП: 2 стадия (при наличии почечного повреждения)");
                    add("Cockroft-Gault: 65 (мл/мин)");
                    add("Поверхность тела:1.92 (кв.м)");
                }}},
        };
    }


    @AfterTest
    public void close() {
        driver.close();
        driver.quit();
    }
}
