package ru.otus.java.basic.hw26.server;

import ru.otus.java.basic.hw26.dao.DBRepository;
import ru.otus.java.basic.hw26.dao.DBRepositoryImpl;
import ru.otus.java.basic.hw26.dao.UniqueViolationException;

import java.sql.SQLException;

public class DBAuthenticationProvider implements AuthenticationProvider {
    private Server server;
    private DBRepository dbRepository;

    public DBAuthenticationProvider(Server server) throws SQLException {
        this.server = server;
        this.dbRepository = new DBRepositoryImpl();
        init();
    }

    @Override
    public void init() {
        dbRepository.init();
        System.out.println("Сервис аутентификации запущен в режиме DB");
    }

    @Override
    public boolean register(String login, String password, String username, ClientHandler clientHandler) {
        if (login.length() < 3 || password.length() < 3 || username.length() < 3) {
            clientHandler.sendMessage("логин/пароль/имя пользователя должны быть не менее 3х символов");
            return false;
        }

        try {
            dbRepository.addUser(login, password, username);
        } catch (UniqueViolationException e) {
            String msg = e.getMessage().contains("login") ? "Указанный логин уже занят" : "Указанное имя пользователя уже занято";
            clientHandler.sendMessage(msg);
            return false;
        }


        clientHandler.setUsername(username);
        server.subscribe(clientHandler);
        clientHandler.sendMessage("/regok " + username);

        return true;
    }

    @Override
    public boolean authenticate(String login, String password, ClientHandler clientHandler) {
        String authUsername = dbRepository.getUsernameByCredentials(login, password);
        System.out.println(authUsername);
        if (authUsername == null) {
            clientHandler.sendMessage("Неверный логин/пароль");
            return false;
        }

        if (server.isUsernameBusy(authUsername)) {
            clientHandler.sendMessage("Данная учетная запись уже используется");
            return false;
        }

        clientHandler.setUsername(authUsername);
        server.subscribe(clientHandler);
        clientHandler.sendMessage("/authok " + authUsername);

        return true;
    }
}
