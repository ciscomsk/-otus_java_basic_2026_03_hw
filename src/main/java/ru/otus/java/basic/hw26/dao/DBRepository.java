package ru.otus.java.basic.hw26.dao;

public interface DBRepository {
    void init();

    void addUser(String login, String password, String username);

    String getUsernameByCredentials(String login, String password);
}
