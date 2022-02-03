package Homework_7;

import BaseObjects.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Homework_7 extends BaseTest {

    @Test(priority = 1)
    public void checkboxesAreBothSelected() {
        driver.get("http://the-internet.herokuapp.com/");
        driver.findElement(By.xpath("//a[@href='/checkboxes']")).click();
        WebElement checkbox1 = driver.findElement(By.xpath("//input[1]"));
        WebElement checkbox2 = driver.findElement(By.xpath("//input[2]"));
        checkbox1.click();
        Assert.assertTrue(checkbox1.isSelected());
        Assert.assertTrue(checkbox2.isSelected());
    }

    @Test(priority = 2)
    public void checkOption1InDropdown() {
        driver.get("http://the-internet.herokuapp.com/");
        driver.findElement(By.xpath("//a[@href='/dropdown']")).click();
        String values = driver.findElement(By.id("dropdown")).getText();
        String[] allValuesArray = values.split("\\r?\\n");
        Assert.assertEquals(allValuesArray[1].trim(), "Option 1");
    }
}
