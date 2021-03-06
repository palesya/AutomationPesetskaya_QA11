package Lecture_6;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.openqa.selenium.support.locators.RelativeLocator.with;

public class Lecture_6 {

    WebDriver driver = null;

    @BeforeTest
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");
    }


    @Test(priority=1)
    public void aboveTest() {
        WebElement password=driver.findElement(By.id("password"));
        driver.findElement(with(By.tagName("input")).above(password)).sendKeys("above");
    }

    @Test(priority=2)
    public void belowTest() {
        WebElement password=driver.findElement(By.id("password"));
        driver.findElement(with(By.tagName("input")).below(password)).sendKeys("above");
    }

    @Test(priority=3)
    public void rightOfTest() {
        WebElement loginCredentials=driver.findElement(By.id("login_credentials"));
        System.out.println(driver.findElement(with(By.tagName("h4")).toRightOf(loginCredentials)).getText());

    }

    @Test(priority=4)
    public void leftOfTest() {
        WebElement loginPassword=driver.findElement(By.className("login_password"));
        System.out.println(driver.findElement(with(By.tagName("h4")).toLeftOf(loginPassword)).getText());
    }

    @Test(priority=5)
    public void nearTest() {
        WebElement password=driver.findElement(By.id("password"));
        System.out.println(driver.findElements(with(By.tagName("input")).near(password,98)).size());
        System.out.println(driver.findElements(with(By.tagName("input")).near(password,60)).size());
        driver.findElement(with(By.tagName("input")).near(password)).sendKeys("above");
    }


    @AfterTest
    public void close() {
        driver.close();
        driver.quit();
    }
}




