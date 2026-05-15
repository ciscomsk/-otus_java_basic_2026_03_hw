package ru.otus.java.basic.hw13;

public class Human implements Movable {
    private String name;
    private Movable currentTransport;
    private int endurance;

    public Human(String name, int endurance) {
        this.name = name;
        this.endurance = endurance;
        this.currentTransport = null;
    }

    public int getEndurance() {
        return endurance;
    }
    @Override
    public String toString() {
        return "Human{" +
                "name='" + name + '\'' +
//                ", currentTransport=" + currentTransport + // java.lang.StackOverflowError - из-за циклической ссылки в Bicycle - owner
                ", endurance=" + endurance +
                '}';
    }

    @Override
    public boolean move(int distance, TerritoryTypes territory) {
        return (currentTransport != null) ? currentTransport.move(distance, territory) : walk(distance);
    }

    public void decreaseEndurance(int amount) {
        endurance -= amount;
    }

    public void getOnTransport(Movable transport) {
        if (currentTransport != null) {
            System.out.println("Вы уже используете " + currentTransport);
            return;
        }

        // как вариант - передавать owner - boolean move(int distance, TerritoryTypes territory, Human human)
        if (transport instanceof Bicycle bc) {
            bc.setOwner(this);
        }

        System.out.println("Вы начали использовать " + transport);
        currentTransport = transport;
    }

    public void getOutOfTransport() {
        if (currentTransport == null) {
            System.out.println("У вас нет транспорта");
            return;
        }

        if (currentTransport instanceof Bicycle bc) {
            bc.setOwner(null);
        }

        System.out.println("Вы перестали использовать " + currentTransport);
        currentTransport = null;
    }

    private boolean walk(int distance) {
        System.out.println("Вы прошли " + distance + "km");
        return true;
    }
}
