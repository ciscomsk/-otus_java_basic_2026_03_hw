package ru.otus.java.basic.hw31;

import java.util.Arrays;
import java.util.stream.IntStream;

public class App {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(filterArray(new int[]{1, 2, 1, 2, 2})));
//        System.out.println(Arrays.toString(filterArray(new int[]{2, 2, 2, 2})));

        System.out.println(checkArray(new int[]{1, 2}));
        System.out.println(checkArray(new int[]{1, 1}));
        System.out.println(checkArray(new int[]{1, 3}));
        System.out.println(checkArray(new int[]{1, 2, 2, 1}));
    }

    public static int[] filterArray(int[] origin) {
//        int lastIdx = -1;
//        for (int i = origin.length - 1; i >= 0; i--) {
//            if (origin[i] == 1) {
//                lastIdx = i;
//                break;
//            }
//        }

        int lastIdx = IntStream.range(0, origin.length)
                .filter(i -> origin[i] == 1)
                .reduce((first, second) -> second)
                .orElse(-1);

        if (lastIdx == -1) {
            throw new RuntimeException("Array doesn't contain 1");
        }

        int[] filtered = new int[origin.length - (lastIdx + 1)];
        System.arraycopy(origin, lastIdx + 1, filtered, 0, filtered.length);

        return filtered;
    }

    public static boolean checkArray(int[] arr) {
        int nOne = 0;
        int nTwo = 0;
        for (int el : arr) {
            if (el != 1 && el != 2) {
                return false;
            }

            if (el == 1) {
                nOne++;
            }

            if (el == 2) {
                nTwo++;
            }
        }

        return nOne > 0 && nTwo > 0;
    }
}
