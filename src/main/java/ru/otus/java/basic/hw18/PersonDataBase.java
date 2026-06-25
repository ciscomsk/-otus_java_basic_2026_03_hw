package ru.otus.java.basic.hw18;

import java.util.*;

import static ru.otus.java.basic.hw18.Position.*;

public class PersonDataBase {
    public static final Set<Position> MANAGER_POSITIONS = Set.of(BRANCH_DIRECTOR, DIRECTOR, MANAGER, SENIOR_MANAGER);

    private List<Person> persons;
    private Map<Long, Person> idMap;

    public PersonDataBase() {
        persons = new ArrayList<>();
        idMap = new HashMap<>();
    }

    public Person findById(long id) {
        return idMap.get(id);
    }

    public void add(Person person) {
        persons.add(person);
        idMap.put(person.getId(), person);
    }

    // static ?
    public boolean isManager(Person person) {
        return MANAGER_POSITIONS.contains(person.getPosition());
    }

    public boolean isEmployee(long id) {
        Person person = idMap.get(id); // может быть null
        return !isManager(person);
    }
}
