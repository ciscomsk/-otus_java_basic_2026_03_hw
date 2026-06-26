package ru.otus.java.basic.hw23.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Server {
    private int port;
    private Map<String, ClientHandler> clients;

    public Server(int port) {
        this.port = port;
        clients = new ConcurrentHashMap<>();
    }

    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server started on port: " + port);

            while (true) {
                Socket socket = serverSocket.accept();
                subscribe(new ClientHandler(socket, this));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void subscribe(ClientHandler client) {
        clients.put(client.getUsername(), client);
    }

    public void unsubscribe(ClientHandler client) {
        broadcastMessage(String.format("Client %s exit", client.getUsername()));
        clients.remove(client.getUsername());
    }

    public void broadcastMessage(String message) {
        clients.values().forEach(c -> c.sendMessage(message));
    }

    public void sendPrivateMessage(ClientHandler sender, String username, String message) {
        ClientHandler receiver = clients.get(username);
        if (receiver == null) {
            sender.sendMessage("User with name " + username + " not found");
            return;
        }

        receiver.sendMessage(username + " [private]: " + message);
        sender.sendMessage("[private " + username + "]: " + message);
    }
}
