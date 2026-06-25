package ru.otus.java.basic.hw18;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public class App {
    public static void main(String[] args) {
        PersonDataBase personDB = new PersonDataBase();
        Person mike = new Person("Mike", Position.ENGINEER, 1);
        Person john = new Person("John", Position.ENGINEER, 2);
        Person irene = new Person("Irene", Position.DIRECTOR, 3);
        personDB.add(mike);
        personDB.add(john);
        personDB.add(irene);
        System.out.println(personDB.findById(3));
        System.out.println(personDB.isManager(mike));
        System.out.println(personDB.isManager(irene));
        System.out.println(personDB.isEmployee(1));
        System.out.println(personDB.isEmployee(3));

        List<Integer> nums = new ArrayList<>();
        IntStream.rangeClosed(1, 10).forEach(nums::add);
        System.out.println(nums);
        Collections.shuffle(nums);
        System.out.println(nums);
//        exchangeSort(nums);
        bubbleSort(nums);
        System.out.println(nums);
    }

    public static void exchangeSort(List<Integer> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = i + 1; j < list.size(); j++) {
                if (list.get(i) > list.get(j)) {
                    int tmp = list.get(i);
                    list.set(i, list.get(j));
                    list.set(j, tmp);
                }
            }
        }
    }

    public static void bubbleSort(List<Integer> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = 0; j < list.size() - 1 - i; j++) {
                if (list.get(j) > list.get(j + 1)) {
                    int tmp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, tmp);
                }
            }
        }
    }
}
