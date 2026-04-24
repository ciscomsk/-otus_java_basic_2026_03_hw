package ru.otus.java.basic.hw7;

import java.util.Arrays;

public class App {
    public static void main(String[] args) {
        int[][] arr = {{1, 2, 3}, {0, -5, 5}};
//        System.out.println(sumOfPositiveElements(arr));

//        printSquare(4);

        int[][] arr2 = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
//        nullifyDiagonal(arr2, MatrixDiagonals.MAIN);
//        nullifyDiagonal(arr2, MatrixDiagonals.SECONDARY);
//        nullifyDiagonal(arr2, MatrixDiagonals.BOTH);
//        print2DArray(arr2);

        int[][] arr3 = {{1, 2, 3}};
//        System.out.println(secondElementSum(arr3, MatrixElements.ROW));
//        System.out.println(secondElementSum(arr2, MatrixElements.ROW));
//        System.out.println(secondElementSum(arr3, MatrixElements.COLUMN));
//        System.out.println(secondElementSum(arr2, MatrixElements.COLUMN));

        System.out.println(findMax(arr));
    }

    public static int sumOfPositiveElements(int[][] array) {
//        int sum = 0;
////        for (int i = 0; i < array.length; i++) {
////            for (int j = 0; j < array[i].length; j++) {
////                if (array[i][j] > 0) {
////                    sum += array[i][j];
////                }
////            }
////        }
//        // =
//        for (int[] arr : array) {
//            for (int i : arr) {
//                if (i > 0) {
//                    sum += i;
//                }
//            }
//        }

        int sum = Arrays.stream(array).flatMapToInt(Arrays::stream).filter(el -> el > 0).sum();
        return sum;
    }

    public static void printSquare(int size) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print('*');
            }
            System.out.println();
        }
    }

    public static void nullifyDiagonal(int[][] array, MatrixDiagonals diagonal) {
//        for (int i = 0; i < array.length; i++) {
//            for (int j = 0; j < array[i].length; j++) {
//                if (i == j) {
//                    array[i][j] = 0;
//                }
//            }
//        }
        // =
        if (diagonal == MatrixDiagonals.MAIN) {
            for (int i = 0; i < array.length; i++) {
                array[i][i] = 0;
            }
        }

        if (diagonal == MatrixDiagonals.SECONDARY) {
            for (int i = 0; i < array.length; i++) {
                array[i][array.length - 1 - i] = 0;
            }
        }

        if (diagonal == MatrixDiagonals.BOTH) {
            for (int i = 0; i < array.length; i++) {
                array[i][i] = 0;
                array[i][array.length - 1 - i] = 0;
            }
        }
    }

    public static int findMax(int[][] array) {
        int max = array[0][0]; // v2 - Integer.MIN_VALUE

//        for (int i = 0; i < array.length; i++) {
//            for (int j = 0; j < array[i].length; j++) {
//                if (array[i][j] > max) {
//                    max = array[i][j];
//                }
//            }
//        }
        // =
        for (int[] arr : array) {
            for (int i : arr) {
                if (i > max) {
                    max = i;
                }
            }
        }

        return max;
    }

    public static int secondElementSum(int[][] array, MatrixElements element) {
        int sum = 0;
        if (element == MatrixElements.ROW) {
//            if (array.length < 2) {
//                return -1;
//            }
//            for (int i = 0; i < array[1].length; i++) {
//                sum += array[1][i];
//            }
            // =
            sum = (array.length > 1) ? Arrays.stream(array[1]).sum() : -1;
        }

        if (element == MatrixElements.COLUMN) {
            if (array[0].length < 2) {
                return -1;
            }
//            for (int[] ints : array) {
//                sum += ints[1];
//            }
            // =
            for (int[] arr : array) {
                sum += arr[1];
            }
        }

        return sum;
    }

    public static void print2DArray(int[][] array) {
//        for (int i = 0; i < array.length; i++) {
//            for (int j = 0; j < array[i].length; j++) {
//                System.out.print(array[i][j] + " ");
//            }
//            System.out.println();
//        }
        // =
        for (int[] arr : array) {
            for (int i : arr) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }
}
