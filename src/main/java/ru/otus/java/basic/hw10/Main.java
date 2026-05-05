package ru.otus.java.basic.hw10;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        User[] users = new User[10];

        Random rnd = new Random();

        IntStream
                .range(0, 10)
                .forEach(i -> users[i] = new User("user" + i, "user" + i, "user" + i, rnd.nextInt(1980, 2008), "user" + i + "@gmail.com"));

//        for (int i = 0; i < users.length; i++) {
//            if ((LocalDate.now().getYear() - users[i].getBirthYear()) > 40) {
//                users[i].showInfo();
//            }
//        }
        // =
//        for (User u : users) {
//            if ((LocalDate.now().getYear() - u.getBirthYear()) > 40) {
//                u.showInfo();
//            }
//        }
        // =
        Arrays.stream(users)
                .filter(u -> LocalDate.now().getYear() - u.getBirthYear() > 40)
                .forEach(User::showInfo);

        System.out.println();

        Box box = new Box(10, 10, 10, "White");
        box.showInfo();
        System.out.println();

        String drill = "дрель";
        String hammer = "молоток";

        box.drop();
        box.put(drill);
        box.setState(BoxState.OPEN);
        box.drop();
        box.put(drill);
        box.put(hammer);
        box.drop();
        box.setState(BoxState.CLOSED);
    }
}
