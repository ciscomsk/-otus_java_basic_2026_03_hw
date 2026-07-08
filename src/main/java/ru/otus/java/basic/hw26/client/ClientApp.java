package ru.otus.java.basic.hw26.client;

import java.io.IOException;

public class ClientApp {
    public static void main(String[] args) {
        String host = "localhost";
        int port = 8189;
        try {
            new Client(host, port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
