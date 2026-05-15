package ru.otus.java.basic.hw12;

public class Cat {
    private String name;
    private int appetite;
    private boolean fullness;

    public Cat(String name, int appetite) {
        this.name = name;
        this.appetite = appetite;
        this.fullness = false;
    }

    public void eat(Plate plate) {
        // v1
        boolean res = plate.decreaseFood(appetite);
        if (!res) {
            System.out.println(name + " в тарелке не хватает еды");
            return;
        }

        fullness = true;
        System.out.println(name + " поел");

        // v2
//        fullness = plate.decreaseFood(appetite);
    }

    public void info() {
        System.out.println("name: " + name + ", fullness: " + fullness);
    }
}
