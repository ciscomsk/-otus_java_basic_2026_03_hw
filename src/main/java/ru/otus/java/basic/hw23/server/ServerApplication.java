package ru.otus.java.basic.hw23.server;

public class ServerApplication {
    public static void main(String[] args) {
        int port = 8189;
        new Server(port).start();
    }
}
