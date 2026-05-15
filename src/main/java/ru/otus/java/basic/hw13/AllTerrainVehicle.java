package ru.otus.java.basic.hw13;

import java.util.Set;

public class AllTerrainVehicle extends FuelTransport {
    private static final Set<TerritoryTypes> availableTerritories = Set.of(TerritoryTypes.PLAIN, TerritoryTypes.DENSE_FOREST, TerritoryTypes.SWAMP);

    public AllTerrainVehicle(int fuelAmount, int fuelConsumption) {
        super(fuelAmount, fuelConsumption);
    }

    @Override
    public String toString() {
        return "AllTerrainVehicle{" +
                "fuelAmount=" + fuelAmount +
                ", fuelConsumption=" + fuelConsumption +
                '}';
    }

    @Override
    boolean checkTerrainAvailability(TerritoryTypes territory) {
        boolean isAvailable = availableTerritories.contains(territory);
        if (!isAvailable) {
            System.out.println("Вездеход может ехать по: " + availableTerritories);
        }

        return availableTerritories.contains(territory);
    }
}
