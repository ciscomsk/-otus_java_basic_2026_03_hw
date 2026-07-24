package ru.otus.java.basic.hw32;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HttpServer {
    private int port;
    private Dispatcher dispatcher;
    private int poolSize;

    public HttpServer(int port, int poolSize) {
        this.port = port;
        this.dispatcher = new Dispatcher();
        this.poolSize = poolSize;
    }

    public void start() {
        ExecutorService pool = Executors.newFixedThreadPool(poolSize);

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Сервер запущен на порту: " + port);
            System.out.println("Ожидаем подключения");

            while (true) {
                Socket socket = serverSocket.accept();
                pool.execute(() -> new RequestHandler(socket, dispatcher));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
