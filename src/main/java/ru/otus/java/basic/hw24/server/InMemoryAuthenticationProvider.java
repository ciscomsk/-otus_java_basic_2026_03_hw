package ru.otus.java.basic.hw24.server;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class InMemoryAuthenticationProvider implements AuthenticationProvider {
    private Server server;
    private List<User> users;

    public InMemoryAuthenticationProvider(Server server) {
        this.server = server;
        this.users = new CopyOnWriteArrayList<>();
        init();
    }

    @Override
    public void init() {
        users.add(new User("qwe", "qwe", "qwe", Role.ADMIN));
        users.add(new User("asd", "asd", "asd", Role.USER));
        users.add(new User("zxc", "zxc", "zxc", Role.USER));
        System.out.println("Сервис аутентификации запущен в режиме InMemory");
    }

    @Override
    public boolean register(String login, String password, String username, ClientHandler clientHandler) {
        if (login.length() < 3 || password.length() < 3 || username.length() < 3) {
            clientHandler.sendMessage("логин/пароль/имя пользователя должны быть не менее 3х символов");
            return false;
        }

        if (isLoginAlreadyExists(login)) {
            clientHandler.sendMessage("Указанный логин уже занят");
            return false;
        }

        if (isUsernameAlreadyExists(username)) {
            clientHandler.sendMessage("Указанное имя пользователя уже занято");
            return false;
        }

        // регистрируем только с правами USER
        Role user = Role.USER;
        users.add(new User(login, password, username, user));
        clientHandler.setUsername(username);
        clientHandler.setRole(user);
        server.subscribe(clientHandler);
        clientHandler.sendMessage("/regok " + username);

        return true;
    }

    @Override
    public boolean authenticate(String login, String password, ClientHandler clientHandler) {
        String authUsername = getUsernameByCredentials(login, password);
        if (authUsername == null) {
            clientHandler.sendMessage("Неверный логин/пароль");
            return false;
        }

        if (server.isUsernameBusy(authUsername)) {
            clientHandler.sendMessage("Данная учетная запись уже используется");
            return false;
        }

        Role role = getRoleByCredentials(login, password);
        if (role == null) {
            clientHandler.sendMessage("Права не определены");
            return false;
        }

        clientHandler.setUsername(authUsername);
        clientHandler.setRole(role);
        server.subscribe(clientHandler);
        clientHandler.sendMessage("/authok " + authUsername);

        return true;
    }

    private String getUsernameByCredentials(String login, String password) {
        for (User u : users) {
            if (u.login.equals(login) && u.password.equals(password)) {
                return u.username;
            }
        }

        return null;
    }

    private boolean isLoginAlreadyExists(String login) {
        for (User u : users) {
            if (u.login.equals(login)) {
                return true;
            }
        }

        return false;
    }

    private boolean isUsernameAlreadyExists(String username) {
        for (User u : users) {
            if (u.username.equals(username)) {
                return true;
            }
        }

        return false;
    }

    private Role getRoleByCredentials(String login, String password) {
        for (User u : users) {
            if (u.login.equals(login) && u.password.equals(password)) {
                return u.role;
            }
        }

        return null;
    }

    private class User {
        private String login;
        private String password;
        private String username;
        private Role role;

        public User(String login, String password, String username, Role role) {
            this.login = login;
            this.password = password;
            this.username = username;
            this.role = role;
        }
    }
}
