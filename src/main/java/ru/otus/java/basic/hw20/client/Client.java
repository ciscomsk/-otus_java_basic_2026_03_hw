package ru.otus.java.basic.hw20.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try (
                Socket socket = new Socket("localhost", 8189);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true)
        ) {
            while (true) {
                System.out.println("Received: " + in.readLine());
                System.out.println("Enter expression or exit: ");

                String expression = sc.nextLine();
                out.println(expression);
                if (expression.equals("exit")) {
                    break;
                }

                String result = in.readLine();
                System.out.println(result);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
