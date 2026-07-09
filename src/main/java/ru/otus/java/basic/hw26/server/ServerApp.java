package ru.otus.java.basic.hw26.server;

import java.sql.SQLException;

public class ServerApp {
    public static void main(String[] args) throws SQLException {
        int port = 8189;
        new Server(port).start();
    }
}
