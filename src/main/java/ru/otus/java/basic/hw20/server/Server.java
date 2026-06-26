package ru.otus.java.basic.hw20.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        int port = 8189;
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server started on port: " + port);
            while (true) {
                try (
                        Socket client = serverSocket.accept();
                        BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                        PrintWriter out = new PrintWriter(client.getOutputStream(), true);
                ) {
                    System.out.println("Client connected");
                    while (true) {
                        String validOps = "Valid operations: [+, -, *, /]";
                        out.println(validOps);

                        String expression = in.readLine();
                        if (expression.equals("exit")) {
                            System.out.println("Client disconnected");
                            break;
                        }

                        // может быть ошибка типа 2* 100 - сервер завалится
                        String[] tokens = expression.split("\\s+");
                        int arg1 = Integer.parseInt(tokens[0]);
                        int arg2 = Integer.parseInt(tokens[2]);
                        String op = tokens[1];

                        String result = switch (op) {
                            case "+" -> String.valueOf(arg1 + arg2);
                            case "-" -> String.valueOf(arg1 - arg2);
                            case "*" -> String.valueOf(arg1 * arg2);
                            case "/" -> (arg2 == 0) ? "You can't divide by 0" : String.valueOf(arg1 / arg2);
                            default -> "Invalid operation";
                        };
                        out.println(result);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
