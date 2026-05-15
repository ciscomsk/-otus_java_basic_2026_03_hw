package ru.otus.java.basic.hw11;

public abstract class Animal {
    private static final int RUN_ENDURANCE_CONSUMPTION = 1;

    String name;
    int endurance;
    int runSpeed;
    //    boolean tiredness;
    AnimalState state;

    public Animal(String name, int endurance, int runSpeed) {
        this.name = name;
        this.endurance = endurance;
        this.runSpeed = runSpeed;
        this.state = AnimalState.ACTIVE;
    }

    // moveType лучше сделать enum
    int move(int distance, String moveType, int moveSpeed, int enduranceConsumption) {
        if (state == AnimalState.TIRED) {
            info();
            return -1;
        }

        int requiredEndurance = Math.min(distance * enduranceConsumption, endurance);
        endurance -= requiredEndurance;
        if (endurance == 0) {
            state = AnimalState.TIRED;
        }
        float passedDistance = (float) requiredEndurance / enduranceConsumption;
        float timeSpent = passedDistance / moveSpeed;

        System.out.println(name + " " + moveType + " " + passedDistance + "m из " + distance + "m за " + timeSpent + "c");
        info();
        if (state == AnimalState.TIRED) {
            return -1;
        }

        return (int) timeSpent;
    }

    public int run(int distance) {
        return move(distance, "run", runSpeed, RUN_ENDURANCE_CONSUMPTION);
    }

    public void info() {
        System.out.println("name: " + name + ", endurance: " + endurance + ", state: " + state);
    }
}
