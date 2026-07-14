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

    /*
        !!!
        <?> - можно передать любой класс
        здесь достаточно <?> тк есть ограничения в классе - <T extends Fruit>, те другой тип просто нельзя создать
        если бы в классе не было этого ограничения - нужно было бы прописать ограничение в методе - <? extends Fruit>
     */
    public boolean compare(Box<?> other) {
        return this.getWeight() == other.getWeight();
    }

    public void transfer(Box<? super T> target) {
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
