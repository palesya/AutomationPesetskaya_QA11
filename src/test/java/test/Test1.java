package test;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Test1 {

    @AfterMethod
    public void after1(){
        System.out.println("v");
    }

    @Test
    public void tesr1(){
        System.out.println("x");
    }

    @Test
    public void tesr2(){
        System.out.println("c");
    }

    @BeforeMethod
    public void before1(){
        System.out.println("z");
    }


}
