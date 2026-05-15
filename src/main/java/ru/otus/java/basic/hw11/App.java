package ru.otus.java.basic.hw11;

public class App {
    public static void main(String[] args) {
        Dog dog = new Dog("some_dog", 100, 4, 2);
        int t3 = dog.run(10);
        System.out.println(t3);
        int t4 = dog.swim(20);
        System.out.println(t4);
        int t5 = dog.swim(50);
        System.out.println(t5);
        dog.run(10);
        System.out.println();

        Horse horse = new Horse("some_horse", 100, 10, 3);
        int t1 = horse.run(90);
        System.out.println(t1);
        int t2 = horse.swim(20);
        System.out.println(t2);
        horse.run(10);
    }
}
