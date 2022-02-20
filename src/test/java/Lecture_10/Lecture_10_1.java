package Lecture_10;

import org.testng.annotations.*;

public class Lecture_10_1 {

    @BeforeSuite(groups = "Smoke")
    public void beforeSuite() {
        System.out.println("Hello,I'm before suite");
    }

    @BeforeClass(groups = {"Smoke"})
    public void beforeClass() {
        System.out.println("Hello,I'm before class");
    }

    @BeforeTest(groups = {"Smoke"})
    public void beforeTest() {
        System.out.println("Hello,I'm before test");
    }

    @BeforeGroups(groups = {"Smoke"})
    public void beforeGroups() {
        System.out.println("Hello,I'm before groups");
    }

    @BeforeMethod(groups = {"Smoke"})
    public void beforeMethod() {
        System.out.println("Hello,I'm before method");
    }

    @Test(groups = {"Smoke"})
    public void test1() {
        System.out.println("Hello,I'm test 1");
    }

    @Test(groups = {"Regression"})
    public void test2() {
        System.out.println("Hello,I'm test 2");
    }

    @AfterSuite(groups = "Smoke")
    public void afterSuite() {
        System.out.println("Hello,I'm after suite");
    }

    @AfterClass(groups = {"Smoke"})
    public void afterClass() {
        System.out.println("Hello,I'm after class");
    }

    @AfterTest(groups = {"Smoke"})
    public void afterTest() {
        System.out.println("Hello,I'm after test");
    }

    @AfterGroups(groups = {"Smoke"})
    public void afterGroups() {
        System.out.println("Hello,I'm after groups");
    }

    @AfterMethod(groups = {"Smoke"})
    public void afterMethod() {
        System.out.println("Hello,I'm after method");
    }
}
