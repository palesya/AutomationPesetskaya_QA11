package Homework_9;

import BaseObjects.BaseTest;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Additional extends BaseTest {

    @Test(priority = 1)
    public void createCollection() {
        driver.get("D:/AutomationPesetskaya_QA11/src/test/java/Homework_8/task_8.html");
        int numberOfColumns = driver.findElements(By.xpath("//th")).size();
        int numberOfElements = driver.findElements(By.xpath("//tr/td")).size();
        List<String> dataFromLine;
        List<List<String>> allDataFromTable = new ArrayList<>();
        for (int i = 0; i < numberOfElements; i += 3) {
            dataFromLine = driver.findElements(By.xpath("//tr/td")).stream().skip(i).limit(numberOfColumns).map(el -> el.getText()).collect(Collectors.toList());
            allDataFromTable.add(dataFromLine);
        }
        System.out.println(allDataFromTable);
    }
}
