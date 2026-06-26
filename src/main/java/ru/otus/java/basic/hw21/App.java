package ru.otus.java.basic.hw21;

import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {
        int size = 100_000_000;
        double[] arr = new double[size];

        Stamp.stamp();
        sequentialExecution(arr);
        Stamp.measure();

        Stamp.stamp();
        parallelExecution(arr);
        Stamp.measure();
    }

    public static void sequentialExecution(double[] arr) {
        System.out.println(Thread.currentThread().getName());
        for (int i = 0; i < arr.length; i++) {
            arr[i] = 1.14 * Math.cos(i) * Math.sin(i * 0.2) * Math.cos(i / 1.2);
        }
    }

    public static void parallelExecution(double[] arr) {
        int threadsCnt = 4;
        int partitionSize = arr.length / threadsCnt;
        List<Thread> threads = new ArrayList<>(threadsCnt);

        for (int i = 0; i < threadsCnt; i++) {
            int part = i;
            Thread thread = new Thread(() -> {
                System.out.println(Thread.currentThread().getName());
                for (int j = part * partitionSize; j < (part + 1) * partitionSize; j++) {
                    arr[j] = 1.14 * Math.cos(j) * Math.sin(j * 0.2) * Math.cos(j / 1.2);
                }
            });
            thread.start();
            threads.add(thread);
        }

        // v1
        threads.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        // v2
//        for (Thread t : threads) {
//            try {
//                t.join();;
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
    }
}
