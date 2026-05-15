package ru.otus.java.basic.hw11;

public class Horse extends Animal implements Swimmable {
    private static final int SWIM_ENDURANCE_CONSUMPTION = 4;

    private int swim_speed;

    public Horse(String name, int endurance, int runSpeed, int swim_speed) {
        super(name, endurance, runSpeed);
        this.swim_speed = swim_speed;
    }

    @Override
    public int swim(int distance) {
        return move(distance, "swim", swim_speed, SWIM_ENDURANCE_CONSUMPTION);
    }
}
