package ru.otus.java.basic.hw27;

public class Fruit {
    private int weight;

    public Fruit(int weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "{" +
                "weight=" + weight +
                '}';
    }

    public int getWeight() {
        return weight;
    }
}
