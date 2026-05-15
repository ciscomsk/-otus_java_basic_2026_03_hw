package ru.otus.java.basic.hw12;

import java.util.Arrays;

public class App {
    public static void main(String[] args) {
        Plate plate = new Plate(30);

        Cat[] cats = {
                new Cat("cat1", 10),
                new Cat("cat2", 12),
                new Cat("cat3", 14)
        };

//        for (int i = 0; i < cats.length; i++) {
//            cats[i].eat(plate);
//        }
        // =
//        for (Cat cat : cats) {
//            cat.eat(plate);
//        }
        // +
//        for (int i = 0; i < cats.length; i++) {
//            cats[i].info();
//        }
        // =
//        for (Cat cat : cats) {
//            cat.info();
//        }
        // =
        Arrays.stream(cats).forEach(c -> {
            c.eat(plate);
            c.info();
            System.out.println(plate.getCurrentAmount());
        });
        System.out.println();

        plate.addFood(10);
        System.out.println(plate.getCurrentAmount());
        plate.addFood(50);
        System.out.println(plate.getCurrentAmount());
    }
}
