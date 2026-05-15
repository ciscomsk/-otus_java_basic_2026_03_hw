package ru.otus.java.basic.hw13;

import java.util.Set;

public class Bicycle implements Movable {
    private static final Set<TerritoryTypes> availableTerritories = Set.of(TerritoryTypes.DENSE_FOREST, TerritoryTypes.PLAIN);

    private int enduranceConsumption;
    private Human owner;

    public Bicycle(int enduranceConsumption) {
        this.enduranceConsumption = enduranceConsumption;
        this.owner = null;
    }

    public Human getOwner() {
        return owner;
    }

    public void setOwner(Human owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "Bicycle{" +
                "enduranceConsumption=" + enduranceConsumption +
                ", owner=" + owner +
                '}';
    }

    @Override
    public boolean move(int distance, TerritoryTypes territory) {
        if (!availableTerritories.contains(territory)) {
            System.out.println("Велосипед может ехать по: " + availableTerritories);
            return false;
        }

        int enduranceAmount = owner.getEndurance();
        if (enduranceAmount == 0) {
            System.out.println("Человек устал");
            return false;
        }

        int enduranceRequired = Math.min(distance * enduranceConsumption, enduranceAmount);
        owner.decreaseEndurance(enduranceRequired);
        float passedDistance = (float) enduranceRequired / enduranceConsumption;

        System.out.println("Вы проехали на велосипеде " + passedDistance + "km из " + distance + "km");

        return owner.getEndurance() != 0;
    }
}
