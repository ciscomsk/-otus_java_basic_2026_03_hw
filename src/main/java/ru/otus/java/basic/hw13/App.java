package ru.otus.java.basic.hw13;

public class App {
    public static void main(String[] args) {
        Human human = new Human("Mike", 20);
        Car car = new Car(120, 10);
        AllTerrainVehicle hover = new AllTerrainVehicle(200, 15);

        System.out.println(human.move(10, TerritoryTypes.DENSE_FOREST));
        human.getOutOfTransport();
        human.getOnTransport(car);
        human.getOnTransport(hover);
        System.out.println(human.move(10, TerritoryTypes.DENSE_FOREST));
        System.out.println(human.move(10, TerritoryTypes.PLAIN));
        System.out.println(human.move(5, TerritoryTypes.PLAIN));
        human.getOutOfTransport();
        human.getOnTransport(hover);
        human.getOutOfTransport();
        System.out.println();

        Bicycle bicycle = new Bicycle(5);
        System.out.println(bicycle.getOwner());
        human.getOnTransport(bicycle);
        System.out.println(human.move(5, TerritoryTypes.SWAMP));
        System.out.println(human.move(2, TerritoryTypes.PLAIN));
        System.out.println(bicycle.getOwner());
        human.getOutOfTransport();
        System.out.println(bicycle.getOwner());
    }
}
