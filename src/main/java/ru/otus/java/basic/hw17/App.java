package ru.otus.java.basic.hw17;

import java.util.Map;

public class App {
    public static void main(String[] args) {
        PhoneBook phones = new PhoneBook();
        phones.add("Mike", "444444");
        phones.add("Mike", "555555");
        phones.add("Iren", "777777");

        System.out.println(phones.find("Mike"));
        System.out.println(phones.find("John"));
        System.out.println(phones.containsPhoneNumber("444444"));
        System.out.println(phones.containsPhoneNumber("888888"));
    }
}
