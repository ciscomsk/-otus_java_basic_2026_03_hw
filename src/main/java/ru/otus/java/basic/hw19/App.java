package ru.otus.java.basic.hw19;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public class App {
    public static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        listTextFiles();
        String file = getFilename();
        try {
            printFileContent(file);
            appendFile(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void listTextFiles() {
        try (Stream<Path> files = Files.list(Path.of("."))) {
            List<Path> textFiles = files
                    .filter(p -> Files.isRegularFile(p) && p.toString().endsWith(".txt"))
                    .map(Path::getFileName)
                    .toList();

            System.out.println(textFiles);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getFilename() {
        System.out.println("Enter the file name: ");
        return sc.nextLine();
    }

    public static void printFileContent(String filename) throws IOException {
        String content = Files.readString(Path.of(filename));
        System.out.println(content);
    }

    public static void appendFile(String filename) throws IOException {
        System.out.println("Enter text to append: ");
        String userText = sc.nextLine();
        Files.writeString(Path.of(filename), userText, StandardOpenOption.APPEND);
    }
}
