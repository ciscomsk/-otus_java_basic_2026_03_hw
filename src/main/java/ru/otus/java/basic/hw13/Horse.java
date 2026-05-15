package ru.otus.java.basic.hw13;

import java.util.Set;

public class Horse implements Movable {
    private static final Set<TerritoryTypes> availableTerritories = Set.of(TerritoryTypes.PLAIN, TerritoryTypes.DENSE_FOREST);

    private int endurance;
    private int enduranceConsumption;

    public Horse(int endurance, int enduranceConsumption) {
        this.endurance = endurance;
        this.enduranceConsumption = enduranceConsumption;
    }

    @Override
    public boolean move(int distance, TerritoryTypes territory) {
        if (!availableTerritories.contains(territory)) {
            System.out.println("Лошадь может передвигаться по: " + availableTerritories);
            return false;
        }

        if (endurance == 0) {
            System.out.println("Лошадь устала");
            return false;
        }

        int enduranceRequired = Math.min(distance * enduranceConsumption, endurance);
        endurance -= enduranceRequired;
        float passedDistance = (float) enduranceRequired / enduranceConsumption;

        System.out.println("Вы проехали на лошади " + passedDistance + "km из " + distance + "km");

        return endurance != 0;
    }
}
