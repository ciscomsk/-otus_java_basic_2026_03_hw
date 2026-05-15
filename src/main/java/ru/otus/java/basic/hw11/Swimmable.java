package ru.otus.java.basic.hw11;

// можно сделать абстрактный SwimAnimal extends Animal c полем swimSpeed и методом swim и наследовать Dog/Horse от него
public interface Swimmable {
    int swim(int distance);
}
