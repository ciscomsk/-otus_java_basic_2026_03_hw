package ru.otus.java.basic.hw26.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientHandler {
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    private Server server;
    private String username;
    private boolean authenticated;

    public ClientHandler(Socket socket, Server server) throws IOException {
        this.socket = socket;
        this.in = new DataInputStream(socket.getInputStream());
        this.out = new DataOutputStream(socket.getOutputStream());
        this.server = server;

        new Thread(() -> {
            try {
                System.out.println("Client connected " + socket.getPort());

                while (true) {
                    sendMessage("Для начала работы необходимо пройти аутентификацию:\n/auth login password\n" +
                            "или пройти регистрацию:\n/reg login password username");

                    String message = in.readUTF();
                    if (message.startsWith("/")) {
                        if (message.equals("/exit")) {
                            sendMessage("/exitok");
                            break;
                        }

                        if (message.startsWith("/auth ")) {
                            String[] tokens = message.split(" ");
                            if (tokens.length != 3) {
                                sendMessage("Неверный формат команды /auth");
                                continue;
                            }

                            if (server.getAuthenticationProvider().authenticate(tokens[1], tokens[2], this)) {
                                authenticated = true;
                                break;
                            }
                        }

                        if (message.startsWith("/reg")) {
                            String[] tokens = message.split(" ");
                            if (tokens.length != 4) {
                                sendMessage("Неверный формат команды /reg");
                                continue;
                            }

                            if (server.getAuthenticationProvider().register(tokens[1], tokens[2], tokens[3], this)) {
                                authenticated = true;
                                break;
                            }
                        }
                    }
                }

                while (authenticated) {
                    String message = in.readUTF();

                    if (message.startsWith("/")) {
                        if (message.equals("/exit")) {
                            sendMessage("/exitok");
                            break;
                        }
                    } else {
                        server.broadcastMessage(username, message);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                disconnect();
            }
        }).start();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void sendMessage(String message) {
        try {
            out.writeUTF(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void disconnect() {
        server.unsubscribe(this);

        try {
            if (in != null) {
                in.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            if (out != null) {
                out.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            if (socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
