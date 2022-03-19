package Lecture_14;

import BaseObjects.BaseTest;
import org.testng.annotations.Test;

public class Lecture_14 extends BaseTest {

    @Test
    public void test(){
        User user1 = new User.Builder().withLastName("test").withFirstName("test").build();
        System.out.println(user1.toString());
        User user2 = new User.Builder().withLastName("test").withFirstName("test").withJob("AQA").withPhone(123).build();
        System.out.println(user2.toString());
        get(OnlinerUserPage.class).open();
        get(OnlinerUserPage.class).register("test", "test", "test");
        get(OnlinerUserPage.class).auth(new OnlinerUser.Builder().withPassword("test").withEmail("test").withRepeatPassword("test").build());
    }
}
