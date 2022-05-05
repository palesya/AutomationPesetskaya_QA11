package Lecture_24N;

public class Test {
    public static void main(String[] args) {
        reader();
    }

    public static void reader(){
        Student student=new Student();
        student.read(new Book());
        student.read(new News());
    }
}
