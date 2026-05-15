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
        if (state == AnimalState.TIRED) { // if (tiredness)
            info();
            return -1;
        }

        int estimated_consumption = distance * enduranceConsumption;
        int real_consumption = Math.min(estimated_consumption, endurance);
        endurance -= real_consumption;
        if (endurance == 0) {
            state = AnimalState.TIRED; // tiredness = true
        }
        float passed_distance = (float) real_consumption / enduranceConsumption;
        float time_spent = passed_distance / moveSpeed;

        System.out.println(name + " " + moveType + " " + passed_distance + "m из " + distance + "m за " + time_spent + "c");
        info();
        if (state == AnimalState.TIRED) { //  if (tiredness)
            return -1;
        }

        return (int) time_spent;
    }

    public int run(int distance) {
        return move(distance, "run", runSpeed, RUN_ENDURANCE_CONSUMPTION);
    }

    public void info() {
        System.out.println("name: " + name + ", endurance: " + endurance + ", state: " + state);
    }
}
