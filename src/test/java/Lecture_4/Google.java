package Lecture_4;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Google {

    private WebDriver driver;

    @BeforeTest
    public void setUp() {
        driver = new ChromeDriver();
        driver.get("https://www.google.by/");
        driver.manage().window().setSize(new Dimension(1536, 824));
    }


    @Test(priority = 1)
    public void checkHelloWorld() {
        driver.get("https://www.google.by/");
        driver.findElement(By.name("q")).clear();
        driver.findElement(By.name("q")).sendKeys("Привет, мир");
        driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
        String foundText = driver.findElement(By.xpath("//h3")).getText();
        Assert.assertTrue(foundText.toLowerCase().contains("Привет, мир".toLowerCase()));
        String enteredText = driver.findElement(By.xpath("//div/div[2]/input")).getAttribute("value");
        Assert.assertEquals(enteredText, "Привет, мир");
    }

    @Test(priority = 2)
    public void checkNothingFound() {
        driver.get("https://www.google.by/");
        driver.findElement(By.name("q")).clear();
        driver.findElement(By.name("q")).sendKeys("*//*");
        driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
        String enteredText = driver.findElement(By.xpath("//p[1]")).getText();
        List<String> resultOptions = new ArrayList<>();
        resultOptions.add("По запросу *//* ничего не найдено.");
        resultOptions.add("Па запыце *//* нічога не знойдзена");
        resultOptions.add("Your search - *//* - did not match any documents.");
        Assert.assertTrue(resultOptions.contains(enteredText));
    }

    @Test(priority = 3)
    public void checkImagesFound() {
        driver.findElement(By.name("q")).clear();
        driver.findElement(By.name("q")).sendKeys("котик");
        driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
        driver.findElement(By.xpath("//div[4]/div/div/div/div/div/div[2]/a")).click();
        driver.findElement(By.tagName("img")).isDisplayed();
    }

    @Test (priority = 4)
    public void findPizzaOnMap() {
        driver.get("https://www.google.by/");
        String buttonName=driver.findElement(By.xpath("//div[3]/center/input")).getAttribute("value");
        if(Objects.equals(buttonName, "Пошук Google")){
            driver.findElement(By.xpath("//div[4]/div/div/a")).click();
        }
        driver.findElement(By.name("q")).sendKeys("пицца");
        driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
        driver.findElement(By.xpath("//div[4]/div/div/div/div/div/div[3]/a")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//div[@id='pane']/div/div/div/div/div[2]/div/div[3]/div/a")).isDisplayed());
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}
