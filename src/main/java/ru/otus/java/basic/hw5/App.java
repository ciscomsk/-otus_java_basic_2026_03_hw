package ru.otus.java.basic.hw5;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.IntStream;

public class App {
    public static void main(String[] args) {
//        printNTimes(3, "text");

        int[] arr1 = {1, 2, 3, 4, 5, 6, 7};
//        sumByCondition(arr1);
//        fillArray(arr1, -1);
//        increaseElements(arr1, 10);
//        compareArrayHalves(arr1);

        int[] arr2 = {5, 4, 3, 2, 1};
        int[] arr3 = {1, 2, 3, 4, 5, 6};
//        sumArrays(arr1, arr2, arr3);

        int[] arr4 = {1, 1, 1, 1, 1, 5};
        int[] arr5 = {5, 3, 4, -2};
        int[] arr6 = {7, 2, 2, 2};
        System.out.println(checkEqualityIdxNaive(arr4));
        System.out.println(checkEqualityIdxNaive(arr5));
        System.out.println(checkEqualityIdxNaive(arr6));
        System.out.println(checkEqualityOptimized(arr4));
        System.out.println(checkEqualityOptimized(arr5));
        System.out.println(checkEqualityOptimized(arr6));

        int[] arr7 = {5, 4, 3, 2, 1};
//        System.out.println(checkOrder(arr3, SortOrder.ASC));
//        System.out.println(checkOrder(arr7, SortOrder.DESC));

        int[] arr8 = {5, 4, 3, 2, 1};
        int[] arr9 = {1, 2, 3, 4, 5, 6};
//        revertArray(arr8);
//        revertArray(arr9);
    }

    public static void printNTimes(int num, String str) {
//        for (int i = 0; i < num; i++) {
//            System.out.println(str);
//        }
        // =
        IntStream.rangeClosed(1, num).forEach(_ -> System.out.println(str));
    }

    public static void sumByCondition(int[] array) {
//        int sum = 0;
//        for (int i = 0; i < array.length; i++) {
//            if (array[i] > 5) {
//                sum += array[i];
//            }
//        }
        // =
        int sum = Arrays.stream(array).filter(el -> el > 5).sum();
        System.out.println(sum);
    }

    public static void fillArray(int[] array, int value) {
//        for (int i = 0; i < array.length; i++) {
//            array[i] = value;
//        }
        // =
        Arrays.fill(array, value);
        System.out.println(Arrays.toString(array));
    }

    public static void increaseElements(int[] array, int value) {
//        for (int i = 0; i < array.length; i++) {
//            array[i] += value;
//        }
        // =
        IntStream.range(0, array.length).forEach(idx -> array[idx] += value);
        System.out.println(Arrays.toString(array));
    }

    public static void compareArrayHalves(int[] array) {
        int center = array.length / 2;
        int leftHalf = 0;
        int rightHalf = 0;
        // =
//        int leftHalf = 0, rightHalf = 0;

        // for arrays with an odd number of elements, we assume that the middle element belongs to both halves
        int oddArrModifier = (array.length % 2 == 0) ? 0 : 1;

        for (int i = 0; i < center + oddArrModifier; i++) {
            leftHalf += array[i];
        }
        for (int i = center; i < array.length; i++) {
            rightHalf += array[i];
        }

        if (rightHalf > leftHalf) {
            System.out.println("Правая половина массива: " + rightHalf + " > левой половины массива: " + leftHalf);
        } else {
            System.out.println("Левая половина массива: " + leftHalf + " > правой половины массива: " + rightHalf);
        }
    }

    public static void sumArrays(int[]... arrays) {
//        int maxLength = 0;
//
////        for (int i = 0; i < arrays.length; i++) {
//        // =
//        for (int[] array : arrays) {
//            if (array.length > maxLength) {
//                maxLength = array.length;
//            }
//        }

        int maxLength = Arrays.stream(arrays).map(a -> a.length).max(Comparator.naturalOrder()).orElse(0); // v2 - Integer::compare
        if (maxLength == 0) {
            return;
        }

        int[] resArr = new int[maxLength];

//        for (int i = 0; i < arrays.length; i++) {
        // =
        for (int[] arr : arrays) {
            for (int i = 0; i < arr.length; i++) {
                resArr[i] += arr[i];
            }
        }

        System.out.println(Arrays.toString(resArr));
    }

    public static int checkEqualityIdxNaive(int[] array) {
        int leftSum = 0, rightSum = 0;
        for (int i = 0; i < array.length - 1; i++) {
            leftSum += array[i];
            for (int j = i + 1; j < array.length; j++) {
                rightSum += array[j];
            }

            if (leftSum == rightSum) {
                return i;
            }

            rightSum = 0;
        }

        return -1;
    }

    /*
        Тут можно посчитать сумму всех элементов, проверить что если не делится на 2, то впринципе границы быть не может.
        А если делится, то за одинарный цикл ее можно будет найти, сравнивая удвоенную левую часть с суммой.
        Обходов массива будет меньше.
     */
    public static int checkEqualityOptimized(int[] arr) {
        int leftHalfSum = 0;
        int arrSum = Arrays.stream(arr).sum();

        if (arrSum % 2 != 0) {
            return -1;
        }

        for (int i = 0; i < arr.length; i++) { // ? оптимизация: arr.length - 1
            leftHalfSum += arr[i];
            if (leftHalfSum * 2 == arrSum) {
                return i;
            }
        }

        return -1;
    }

    public static boolean checkOrder(int[] array, SortOrder order) {
        if (order == SortOrder.ASC) {
            for (int i = 0; i < array.length - 1; i++) {
                if (array[i] >= array[i + 1]) {
                    return false;
                }
            }
        }

        if (order == SortOrder.DESC) {
            for (int i = 0; i < array.length - 1; i++) {
                if (array[i + 1] >= array[i]) {
                    return false;
                }
            }
        }

        return true;
    }

    public static void revertArray(int[] array) {
        int tmp, mirrorIdx;
        for (int i = 0; i < array.length / 2; i++) {
            tmp = array[i];
            mirrorIdx = array.length - 1 - i;
            array[i] = array[mirrorIdx];
            array[mirrorIdx] = tmp;
        }
        System.out.println(Arrays.toString(array));
    }
}
