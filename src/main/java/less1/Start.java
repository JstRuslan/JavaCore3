package less1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Start {

    public static void main(String[] args) {
// Task 1
        Double[] myArr = {1.0, 2.0, 3.0, 4.0, 5.0};
        System.out.println(Arrays.toString(myArr));
        Scanner sc;
        int i, j;

        do {
            System.out.printf("Enter first number from 1 to %d: ", myArr.length);
            sc = new Scanner(System.in);
            i = sc.nextInt();
        }
        while ((i <= 0) || (i > myArr.length));

        do {
            System.out.printf("Enter second number from 1 to %d: ", myArr.length);
            sc = new Scanner(System.in);
            j = sc.nextInt();
        }
        while ((j <= 0) || (j > myArr.length));

        ChangeItems.changeItems(myArr, i-1, j-1);

        System.out.println(Arrays.toString(myArr));

//Task 2
        Integer[] intNum = {1, 2, 3, 4, 5};
        String[] strNum = {"A", "B", "C", "D", "E"};

        ArrayList<Integer> iResult = ChangeItems.transformToCollection(intNum);
        System.out.println(iResult);
        ArrayList<String> strResult = ChangeItems.transformToCollection(strNum);
        System.out.println(strResult);


//Task3

        Apple ap1 = new Apple("red");
        Apple ap2 = new Apple("green");
        Orange or = new Orange();

        Box<Fruit> box1 = new Box<>();
        Box<Fruit> box2 = new Box<>();
        Box<Fruit> box3 = new Box<>();
        Box<Fruit> box4 = new Box<>();

        box1.addFruit(ap1, 10);
        box1.addFruit(ap2, 7);
        box2.addFruit(or, 8);
        box3.addFruit(or,7);


        System.out.println(">>>Weight & amount");
        box1.getWeight();
        box1.getAmount();

        //false, т.к. при одинаковом количестве имеют разный вес
        System.out.println(">>>Compare boxes: " +  box1.compareBox(box3));


        System.out.println(">>>ShiftBox");
        box3.shiftBox(box4);

    }
}