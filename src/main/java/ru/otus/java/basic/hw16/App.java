package ru.otus.java.basic.hw16;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class App {
    public static void main(String[] args) {
        List<Integer> nums = buildRangeList(5, 7);
        System.out.println(nums);
        System.out.println(sumList(nums));
        fillList(0, nums);
        System.out.println(nums);
        System.out.println(incElement(3, nums));
        System.out.println();

        Employee e1 = new Employee("Mike", 40);
        Employee e2 = new Employee("Anna", 25);
        Employee e3 = new Employee("John", 35);
        List<Employee> employees = new ArrayList<>(Arrays.asList(e1, e2, e3));
        System.out.println(Employee.getNames(employees));
        System.out.println(Employee.filterByAge(employees, 30));
        System.out.println(Employee.checkAvgAge(employees, 30));
        System.out.println(Employee.getYoungest(employees));
    }

    public static List<Integer> buildRangeList(int min, int max) {
        List<Integer> list = new ArrayList<>();

//        for (int i = min; i <= max; i++) {
//            list.add(i);
//        }
        // =
        IntStream.rangeClosed(min, max).forEach(list::add);

        return list;
    }

    public static int sumList(List<Integer> list) {
//        int sum = 0;
////        for (int i = 0; i < list.size(); i++) {
////            if (list.get(i) > 5) {
////                sum += list.get(i);
////            }
////        }
//        // =
//        for (Integer el : list) {
//            if (el > 5) {
//                sum += el;
//            }
//        }
//        return sum;

        return list.stream().filter(el -> el > 5).mapToInt(Integer::intValue).sum();
    }

    public static void fillList(int el, List<Integer> list) {
//        for (int i = 0; i < list.size(); i++) {
//            list.set(i, el);
//        }
        // =
        list.replaceAll(_ -> el);
    }

    // v1 - void
    public static void incElementV(int inc, List<Integer> list) {
//        for (int i = 0; i < list.size(); i++) {
//            list.set(i, list.get(i) + inc);
//        }
        // =
        list.replaceAll(el -> el + inc);
    }
    
    // v2 - stream
    public static List<Integer> incElement(int inc, List<Integer> list) {
        return list.stream().map(el -> el + inc).toList();
    }
}
