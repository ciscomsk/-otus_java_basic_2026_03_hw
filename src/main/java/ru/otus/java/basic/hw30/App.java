package ru.otus.java.basic.hw30;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class App {
    public static final int PRINT_COUNT = 5;

    private int currentTurn = 0;

    public static void main(String[] args) {
        App app = new App();
        try (ExecutorService pool = Executors.newFixedThreadPool(3)) {
            pool.execute(() -> app.printLetter('A', 0));
            pool.execute(() -> app.printLetter('B', 1));
            pool.execute(() -> app.printLetter('C', 2));
        }
    }

    public /*synchronized*/ void printLetter(char letter, int myTurn) { // v1
        for (int i = 0; i < PRINT_COUNT; i++) {
            synchronized (this) { // v2
                while (currentTurn != myTurn) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
//                        Thread.currentThread().interrupt();
//                        return;
                    }
                }

                System.out.print(letter);
                currentTurn = (currentTurn + 1) % 3;
                notifyAll();
            }
        }
    }
}
