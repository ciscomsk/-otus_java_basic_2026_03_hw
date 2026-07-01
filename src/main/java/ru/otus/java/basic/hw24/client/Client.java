package ru.otus.java.basic.hw24.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    Scanner sc;
    DataInputStream in;
    Socket socket;
    DataOutputStream out;

    public Client(String host, int port) throws IOException {
        this.sc = new Scanner(System.in);
        this.socket = new Socket(host, port);
        this.in = new DataInputStream(socket.getInputStream());
        this.out = new DataOutputStream(socket.getOutputStream());

        new Thread(() -> {
            try {
                while (true) {
                    String message = in.readUTF();

                    if (message.startsWith("/")) {
                        if (message.equals("/exitok")) {
                            break;
                        }

                        if (message.startsWith("/authok")) {
                            String username = message.split(" ")[1];
                            System.out.println("Вы подключились к чату с именем: " + username);
                        }

                        if (message.startsWith("/regok")) {
                            String username = message.split(" ")[1];
                            System.out.println("Вы зарегистрировались и вошли в чат с именем: " + username);
                        }
                    } else {
                        System.out.println(message);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                disconnect();
            }
        }).start();

        try {
            while (true) {
                String message = sc.nextLine();
                out.writeUTF(message);

                if (message.equals("/exit")) {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void disconnect() {
        sc.close();

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
