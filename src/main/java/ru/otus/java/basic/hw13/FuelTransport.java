package ru.otus.java.basic.hw13;

public abstract class FuelTransport implements Movable {
    int fuelAmount;
    int fuelConsumption;

    public FuelTransport(int fuelAmount, int fuelConsumption) {
        this.fuelAmount = fuelAmount;
        this.fuelConsumption = fuelConsumption;
    }

    @Override
    public boolean move(int distance, TerritoryTypes territory) {
        if (!checkTerrainAvailability(territory)) {
//            System.out.println(this.getClass().getSimpleName() + " может ехать по: " + availableTerritories);
            return false;
        }

        if (fuelAmount == 0) {
            System.out.println("Нет топлива");
            return false;
        }

        int fuelRequired = Math.min(distance * fuelConsumption, fuelAmount);
        fuelAmount -= fuelRequired;
        float passedDistance = (float) fuelRequired / fuelConsumption;

        System.out.println("Вы проехали на транспорте " + passedDistance + "km из " + distance + "km");

        return fuelAmount != 0;
    }


    abstract boolean checkTerrainAvailability(TerritoryTypes territory);
}
