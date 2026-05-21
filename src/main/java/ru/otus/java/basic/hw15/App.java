package ru.otus.java.basic.hw15;

public class App {
    public static void main(String[] args) {
        String[][] arr1 = {{"1", "2", "3", "4"}, {"1", "2", "3", "4"}, {"1", "2", "3", "4"}, {"1", "2", "3", "4"}};
        String[][] arr2 = {{"1", "2", "3", "4"}};
        String[][] arr3 = {{"1", "2", "3", "4"}, {"1", "2", "3", "4"}, {"1", "2", "3", "4"}, {"1", "some_str", "3", "4"}};

        System.out.println(sumArr(arr1));
//        System.out.println(sumArr(arr2));
//        System.out.println(sumArr(arr3));
    }

    public static int sumArr(String[][] arr) {
        validateArrSize(arr);

        int sum = 0;

        // v1
//        int cellX = 0, cellY = 0;
//        try {
//            for (int i = 0; i < arr.length; i++) {
//                cellY = i;
//                for (int j = 0; j < arr[i].length; j++) {
//                    cellX = j;
//                    sum += Integer.parseInt(arr[i][j]);
//                }
//            }
//        } catch (NumberFormatException e) {
//            throw new AppArraySizeException("Ошибка преобразования к числу в ячейке: ["  + cellY + "][" + cellX + "]");
//        }

        // v2
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                try {
                    sum += Integer.parseInt(arr[i][j]);
                } catch (NumberFormatException e) {
                    throw new AppArrayDataException("Ошибка преобразования к числу в ячейке: [" + i + "][" + j + "]");
                }
            }
        }

        return sum;
    }


    public static void validateArrSize(String[][] arr) {
        if (arr.length != 4) {
            throw new AppArraySizeException("Размер массива должен быть: 4 x 4");
        }

        for (String[] a : arr) {
            if (a.length != 4) {
                throw new AppArraySizeException("Размер массива должен быть: 4 x 4");
            }
        }
    }
}
