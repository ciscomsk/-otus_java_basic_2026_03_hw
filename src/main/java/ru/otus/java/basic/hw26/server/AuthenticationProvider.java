package ru.otus.java.basic.hw26.server;

public interface AuthenticationProvider {
    void init();

    boolean register(String login, String password, String username, ClientHandler clientHandler);

    boolean authenticate(String login, String password, ClientHandler clientHandler);
}
