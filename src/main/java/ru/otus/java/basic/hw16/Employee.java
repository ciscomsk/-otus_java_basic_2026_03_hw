package ru.otus.java.basic.hw16;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Employee {
    private String name;
    private int age;

    public Employee(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public static List<String> getNames(List<Employee> employees) {
//        List<String> names = new ArrayList<>(employees.size());
//        for (Employee emp : employees) {
//            names.add(emp.getName());
//        }
//        return names;
        // =
        return employees.stream().map(Employee::getName).toList();
    }

    public static List<Employee> filterByAge(List<Employee> employees, int minAge) {
//        List<Employee> filteredList = new ArrayList<>();
//        for (Employee emp : employees) {
//            if (emp.getAge() >= minAge) {
//                filteredList.add(emp);
//            }
//        }
//        return  filteredList;
        // =
        return employees.stream().filter(e -> e.getAge() >= minAge).toList();
    }

    public static boolean checkAvgAge(List<Employee> employees, int avgAge) {
//        int ageSum = 0;
//        for (Employee emp : employees) {
//            ageSum += emp.getAge();
//        }

        int cnt = employees.size();
        int ageSum = employees.stream().mapToInt(Employee::getAge).sum(); // внутри класса можно e -> e.age
        int listAvg = ageSum / cnt;

        return listAvg > avgAge;
    }

    public static Employee getYoungest(List<Employee> employees) {
        // список может быть пуст
//        Employee youngest = employees.get(0); // = getFirst
//
//        for (int i = 1; i < employees.size(); i++) { // 1 - оптимизация
//            if (employees.get(i).age < youngest.age) {
//                youngest = employees.get(i);
//            }
//        }
//
//        for (Employee emp : employees) {
//            if (emp.age < youngest.age) {
//                youngest = emp;
//            }
//        }
//        return youngest;

        // oeElse(default value) | orElseThrow
        return employees.stream().min(Comparator.comparingInt(Employee::getAge)).orElse(null);
    }
}
