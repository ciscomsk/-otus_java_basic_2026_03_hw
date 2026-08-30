package ru.otus.java.basic.hw24.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Server {
    private int port;
    private List<ClientHandler> clients;
    private AuthenticationProvider authenticationProvider;

    public Server(int port) {
        this.port = port;
        this.clients = new CopyOnWriteArrayList<>();
        this.authenticationProvider = new InMemoryAuthenticationProvider(this);
    }

    public AuthenticationProvider getAuthenticationProvider() {
        return authenticationProvider;
    }

    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server started on port: " + port);


            while (true) {
                Socket socket = serverSocket.accept();
                new ClientHandler(socket, this);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void subscribe(ClientHandler client) {
        clients.add(client);
    }

    public void unsubscribe(ClientHandler client, boolean forced) {
        String msg = (forced) ? "Пользователь %s был отключен" : "Пользователь %s вышел";
        broadcastMessage("admin", String.format(msg, client.getUsername()));
        clients.remove(client);
    }

    public void kick(ClientHandler sender, String username) {
        ClientHandler client = getHandlerByUsername(username);
        if (client == null) {
            sender.sendMessage("Пользователь: " + username + " - не найден");
            return;
        }

        client.disconnect(true);
    }

    public void broadcastMessage(String sender, String message) {
        String msg = String.format("%s%s:%s %s", ConsoleColors.CYAN_BOLD, sender, ConsoleColors.RESET, message);

        for (ClientHandler c : clients) {
            c.sendMessage(msg);
        }
    }

    public boolean isUsernameBusy(String username) {
        for (ClientHandler c : clients) {
            if (c.getUsername().equals(username)) {
                return true;
            }
        }

        return false;
    }

    private ClientHandler getHandlerByUsername(String username) {
        for (ClientHandler c : clients) {
            if (c.getUsername().equals(username)) {
                return c;
            }
        }

        return null;
    }
}
