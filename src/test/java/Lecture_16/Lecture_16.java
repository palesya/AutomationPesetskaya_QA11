package Lecture_16;

import Entity.Car;
import Entity.CarNew;
import Entity.Student;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Lecture_16 {

    @Test(priority = 1,dataProvider = "student")
    public void student1_Test(Student student) {
        System.out.println(student.getGroup());
        System.out.println(student);
    }

    @Test(priority = 2)
    public void car1_Test() {
        Car car = new Car.AutoIndustry().withColor("black").withBrand("Volvo").withBody("hatchback").withV(2.0).create();
        System.out.println(car);
    }

    @Test(priority = 3)
    public void car2_Test() {
        CarNew car = new CarNew.AutoIndustry().withColor("black").withBrand("Volvo").withBody("hatchback").withV(2.0).build();
        System.out.println(car);
    }

    @DataProvider(name = "student")
    private Object[][] getStudent() {
        return new Object[][]{
                {new Student("Alex", "Ivanov", 23, "Building", "112327")},
                {new Student() {
                    {
                        setFirstName("Alesya");
                        setAge(32);
                    }
                }}
        };
    }
}
