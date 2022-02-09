package Homework_8;

import BaseObjects.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class Homework_8 extends BaseTest {

    @Test(priority = 1)
    public void checkFirstValueInList() {
        driver.get("D:/AutomationPesetskaya_QA11/src/test/java/Homework_8/task_8.html");
        //Создать коллекцию из значений в первой колонке
        List<WebElement> brandsWebEl = driver.findElements(By.cssSelector("td:first-child"));
        List<String> brandsStringList = new ArrayList<>() {{
            brandsWebEl.forEach((element) -> add(element.getText()));
        }};
        Assert.assertEquals(brandsStringList.get(0), "Renault");
    }

    @Test(priority = 2)
    public void checkElementsOnPage() {
        //Ввести значение в поле ввода
        driver.findElement(By.id("brand")).sendKeys("Renault");
        driver.findElement(By.id("model")).sendKeys("Koleos");
        //Кликнуть на чекбокс
        driver.findElement(By.cssSelector("#yes")).click();
        //Кликнуть на кнопку
        driver.findElement(By.xpath("//div/button")).click();
        //Выбрать элемент из списка
        WebElement carDropdown = driver.findElement(By.id("cars"));
        Select selectCar = new Select(carDropdown);
        selectCar.selectByValue("renaultKaptur");
        driver.findElement(By.xpath("//form/button")).click();
        //Проверить что картинка существует
        Assert.assertTrue(driver.findElement(By.cssSelector("[alt^='R']")).isEnabled());
        //Проверить текст тега р
        Assert.assertEquals(driver.findElement(By.xpath("//p[2]")).getText(),"- Ferdinand Porsche");
        //Кликнуть на ссылку и проверить что новая страница была открыта
        driver.findElement(By.tagName("a")).click();
        Assert.assertEquals(driver.getCurrentUrl(),"https://www.renault.by/cars/koleos/overview.html");
    }
}

