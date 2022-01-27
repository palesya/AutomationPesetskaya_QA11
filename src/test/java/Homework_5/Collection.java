package Homework_5;

import java.util.ArrayList;
import java.util.List;

public class Collection {
    public static void main(String[] args) {
        List<Integer> collection = new ArrayList<>() {{
            add(1);
            add(2);
            add(3);
            add(0);
            add(-1);
            add(10);
            add(11);
            add(-20);
            add(40);
        }};
        System.out.print("Elements greater than 0: ");
        collection.stream().filter((element) -> element > 0)
                .forEach((element) -> System.out.print(element + " "));
        System.out.println("");
        System.out.print("Elements less than 0: ");
        collection.stream().filter((element) -> element < 0)
                .forEach((element) -> System.out.print(element + " "));
        System.out.println("");
        //элементы кратные 5 (не включая ноль)
        System.out.print("Elements multiples of 5: ");
        collection.stream().filter((element) -> ((element % 5) == 0)&&(element!= 0))
                .forEach((element) -> System.out.print(element + " "));
        System.out.println("");
        //элементы кратные 10 (не включая ноль)
        System.out.print("Elements multiples of 10: ");
        collection.stream().filter((element) -> ((element % 10) == 0)&&(element!= 0))
                .forEach((element) -> System.out.print(element + " "));

    }

}
