package ru.otus.java.basic.hw18;

import java.util.*;

public class PersonDatabaseDummy {
    private static final HashSet<Position> MANAGER_POSITIONS = new HashSet<>(
            Arrays.asList(
                    Position.MANAGER,
                    Position.DIRECTOR,
                    Position.BRANCH_DIRECTOR,
                    Position.SENIOR_MANAGER
            )
    );

    private final List<Person> persons;
    private final Map<Long, Person> personIds;
    private final Set<Person> managers;
    private final Set<Long> employeeIds;

    public PersonDatabaseDummy() {
        persons = new ArrayList<>();
        personIds = new HashMap<>();
        managers = new HashSet<>();
        employeeIds = new HashSet<>();
    }

    public void add(Person person) {
        persons.add(person);
        personIds.put(person.getId(), person);
        if (MANAGER_POSITIONS.contains(person.getPosition())) {
            managers.add(person);
        } else {
            employeeIds.add(person.getId());
        }
    }


    public Person findById(Long id) {
        return personIds.get(id);
    }

    public boolean isManager(Person person) {
        return managers.contains(person);
    }

    public boolean isEmployee(Long id) {
        return employeeIds.contains(id);
    }
}