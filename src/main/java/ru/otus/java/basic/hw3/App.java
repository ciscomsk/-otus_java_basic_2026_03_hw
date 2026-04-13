package ru.otus.java.basic.hw3;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
//        greetings();
//        checkSign(5, 10, 15);
//        checkSign(5, -10, -15);
//        selectColor();
//        compareNumbers();
//        addOrSubtractAndPrint(5, 10, true);
//        addOrSubtractAndPrint(5, 10, false);
//        int methodNumber = askUser();
//        executeMethod(methodNumber);

        int a = -1_320_973_450;
        int b = -1_710_534_712;
        int c = -1_220_937_526;
        long sum1 = a + b + c;
        long sum2 = (long) a + (long) b + (long) c;
        System.out.println(sum1); // 42521608 - неправильно
        System.out.println(sum2); // -4252445688 - правильно
    }


    public static void greetings() {
        // v1
        List.of("Hello", "World", "from", "Java").forEach(System.out::println);

        // v2
//        System.out.println("Hello\nWorld\nfrom\nJava");
    }


    public static void checkSign(int a, int b, int c) {
        long sum = (long) a + (long) b + (long) c;
        System.out.println(sum);

        String msg = (sum >= 0) ? "Сумма положительная" : "Сумма отрицательная";
        System.out.println(msg);
        // =
//        if (sum >= 0) {
//            System.out.println("Сумма положительная");
//        } else {
//            System.out.println("Сумма отрицательная");
//        }
    }

    public static void selectColor() {
        int data = new Random().nextInt(30);
        System.out.println(data);

        String res;
        if (data > 20) {
            res = "Красный";
        } else if (data > 10) {
            res = "Желтый";
        } else {
            res = "Зеленый";
        }
        System.out.println(res);
        // =
//        if (data <= 10) {
//            System.out.println("Красный");
//        } else if (data <= 20) {
//            System.out.println("Желтый");
//        } else {
//            System.out.println("Зеленый");
//        }
    }

    public static void compareNumbers() {
        Random rnd = new Random();
        int a = rnd.nextInt();
        int b = rnd.nextInt();
        System.out.println(a);
        System.out.println(b);

        String msg = (a >= b) ? "a >= b" : "a < b";
        System.out.println(msg);
        // =
//        if (a >= b) {
//            System.out.println("a >= b");
//        } else {
//            System.out.println("a < b");
//        }
    }

    // возможно переполнение
    public static void addOrSubtractAndPrint(int initValue, int delta, boolean increment) {
        long res = (increment) ? (long) initValue + (long) delta : (long) initValue - (long) delta;
        System.out.println(res);
    }

    public static int askUser() {
        Scanner sc = new Scanner(System.in);
        int num;
        do {
            System.out.println("Введите число от 1 до 5: ");
            num = sc.nextInt();
        } while (num > 5 || num < 1);

        return num;
    }

    public static void executeMethod(int methodNumber) {
        switch (methodNumber) {
            case 1 -> greetings();
            case 2 -> {
                Random rnd = new Random();
                int a = rnd.nextInt();
                int b = rnd.nextInt();
                int c = rnd.nextInt();
                System.out.println(a);
                System.out.println(b);
                System.out.println(c);
                checkSign(a, b, c);
            }
            case 3 -> selectColor();
            case 4 -> compareNumbers();
            case 5 -> {
                Random rnd = new Random();
                int init = rnd.nextInt();
                int delta = rnd.nextInt();
                boolean increment = rnd.nextBoolean();
                System.out.println(init);
                System.out.println(delta);
                System.out.println(increment);
                addOrSubtractAndPrint(init, delta, increment);
            }
            default ->
                    System.out.println("Unknown method number!"); // для примера - в данном условии нельзя оказаться из-за do while
        }
    }
}
