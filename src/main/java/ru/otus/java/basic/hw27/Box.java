package ru.otus.java.basic.hw27;

import java.util.ArrayList;
import java.util.List;

public class Box<T extends Fruit> {
    private List<T> fruits;

    public Box() {
        fruits = new ArrayList<>();
    }

    public void add(T fruit) {
        fruits.add(fruit);
    }

    public int getWeight() {
        return fruits.stream().mapToInt(Fruit::getWeight).sum();
    }

    public boolean compare(Box<? extends T> other) {
        return this.getWeight() == other.getWeight();
    }

    public void transfer(Box<T> target) {
        target.fruits.addAll(this.fruits);
        this.fruits.clear();
    }

    @Override
    public String toString() {
        return "Box{" +
                "fruits=" + fruits +
                '}';
    }
}
