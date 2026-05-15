package ru.otus.java.basic.hw11;

public class Dog extends Animal implements Swimmable {
    private static final int SWIM_ENDURANCE_CONSUMPTION = 2;

    private int swim_speed;

    public Dog(String name, int endurance, int runSpeed, int swim_speed) {
        super(name, endurance, runSpeed);
        this.swim_speed = swim_speed;
    }

    @Override
    public int swim(int distance) {
        return move(distance, "swim", swim_speed, SWIM_ENDURANCE_CONSUMPTION);
    }
}
