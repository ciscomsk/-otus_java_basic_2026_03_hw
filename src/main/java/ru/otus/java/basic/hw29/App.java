package ru.otus.java.basic.hw29;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class App {
    public static final Scanner sc = new Scanner(System.in);
    public static final int NEW_LINE_CODE = 10;

    public static void main(String[] args) {
        listTextFiles();
        String filename = getUserInput("Введите имя файла: ");
        String searchPattern = getUserInput("Введите искомую последовательность символов: ");
//        String filename = "file_3.txt";
//        String searchPattern = "zxC";
        int[] searchCodes = searchPattern.chars().toArray();
        int cnt = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            int c;
            int searchIdx = 0;
            while ((c = reader.read()) != -1) {
                if (c == NEW_LINE_CODE) {
                    continue;
                }

                if (c  == searchCodes[searchIdx]) {
                    searchIdx++;
                    if (searchIdx == searchCodes.length) {
                        searchIdx = 0;
                        cnt++;
                    }
                    continue;
                }

                searchIdx = 0;
            }

            System.out.println(cnt);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void listTextFiles() {
        try (Stream<Path> files = Files.list(Path.of("."))) {
            List<Path> txtFiles = files
                    .filter(p -> Files.isRegularFile(p) && p.toString().endsWith(".txt"))
                    .map(Path::getFileName)
                    .toList();

            System.out.println("Список текстовых файлов в текущей директории: ");
            IntStream.range(0, txtFiles.size())
                    .mapToObj(i -> (i + 1) + ": " + txtFiles.get(i))
                    .forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getUserInput(String message) {
        System.out.println(message);
        return sc.nextLine();
    }
}
