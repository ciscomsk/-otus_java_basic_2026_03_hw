package ru.otus.java.basic.hw12;

public class Plate {
    private int maxAmount;
    private int currentAmount;

    public Plate(int maxAmount) {
        this.maxAmount = maxAmount;
        this.currentAmount = maxAmount;
    }

    public int getCurrentAmount() {
        return currentAmount;
    }

    public void addFood(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("amount < 0"); // v1
//            return; // v2 + логирование
        }

        currentAmount = Math.min(amount + currentAmount, maxAmount);
    }

    public boolean decreaseFood(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("amount < 0"); // v1
        }

        if (amount > currentAmount) { // v2 - if (amount > currentCapacity || amount < 0) + логирование
            return false;
        }

        currentAmount -= amount;
        return true;
    }
}
