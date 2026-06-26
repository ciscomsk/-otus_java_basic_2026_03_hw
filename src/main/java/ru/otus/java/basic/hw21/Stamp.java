package ru.otus.java.basic.hw21;

public class Stamp {
    private static long time;

    public static void stamp() {
        time = System.currentTimeMillis();
    }

    public static void measure() {
        time = System.currentTimeMillis() - time;
        System.out.println("Time: " + time);
    }
}
