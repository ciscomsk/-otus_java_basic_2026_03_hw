package ru.otus.java.basic.hw13;

import java.util.Set;

public class Car extends FuelTransport {
    private static final Set<TerritoryTypes> availableTerritories = Set.of(TerritoryTypes.PLAIN);

    public Car(int fuelAmount, int fuelConsumption) {
        super(fuelAmount, fuelConsumption);
    }

    @Override
    public String toString() {
        return "Car{" +
                "fuelAmount=" + fuelAmount +
                ", fuelConsumption=" + fuelConsumption +
                '}';
    }

    @Override
    boolean checkTerrainAvailability(TerritoryTypes territory) {
        boolean isAvailable = availableTerritories.contains(territory);
        if (!isAvailable) {
            System.out.println("Машина может ехать по: " + availableTerritories);
        }

        return availableTerritories.contains(territory);
    }
}
