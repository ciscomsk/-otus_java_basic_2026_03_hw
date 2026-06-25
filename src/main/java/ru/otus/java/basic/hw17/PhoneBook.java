package ru.otus.java.basic.hw17;

import java.util.*;

public class PhoneBook {
    private Map<String, Set<String>> records;

    public PhoneBook() {
        records = new HashMap<>();
    }

    public void add(String name, String phone) {
//        if (!records.containsKey(name)) {
//            records.put(name, new HashSet<>());
//        }
//        records.get(name).add(phone);
        // =
        records.computeIfAbsent(name, _ -> new HashSet<>()).add(phone);
    }

    @Override
    public String toString() {
        return "PhoneBook{" +
                "records=" + records +
                '}';
    }

    public Set<String> find(String name) {
        return records.get(name);
    }

    public boolean containsPhoneNumber(String phone) {
//        return records.values().contains(phone);
        // =
//        return records.containsValue(phone);

//        Collection<Set<String>> values = records.values();
//        for (Set<String> phones : values) {
//            if (phones.contains(phone)) {
//                return true;
//            }
//        }
//        return false;
        // =
        return records.values().stream().anyMatch(p -> p.contains(phone));
    }
}
