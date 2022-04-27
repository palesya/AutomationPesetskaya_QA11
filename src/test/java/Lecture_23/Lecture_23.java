package Lecture_23;

import org.testng.annotations.Test;

public class Lecture_23 {
    @Test
    public void test(){
        String find="найдено 287 тем";
        System.out.println(Integer.parseInt(find.split(" ")[1]));
    }
}
