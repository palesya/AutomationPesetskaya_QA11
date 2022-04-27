package Lecture_24N;

public class Student {

    public void read(IReader iReader){
        System.out.println("Я читаю ");
        System.out.println(iReader.getContent());
    }
}
