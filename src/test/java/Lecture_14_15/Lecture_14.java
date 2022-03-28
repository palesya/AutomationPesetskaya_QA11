package Lecture_14_15;

import BaseObjects.BaseTest;
import org.testng.annotations.Test;

public class Lecture_14 extends BaseTest {

    @Test
    public void BuilderExamlpe_test(){
        User_Builder user1 = new User_Builder.Builder().withLastName("test").withFirstName("test").build();
        System.out.println(user1.toString());
        User_Builder user2 = new User_Builder.Builder().withLastName("test").withFirstName("test").withJob("AQA").withPhone(123).build();
        System.out.println(user2.toString());
        get(OnlinerUserPage.class).open();
        get(OnlinerUserPage.class).register("test", "test", "test");
        get(OnlinerUserPage.class).auth(new OnlinerUser.Builder().withPassword("test").withEmail("test").withRepeatPassword("test").build());
    }
}
