package ru.otus.java.basic.hw32;

import java.io.IOException;
import java.net.Socket;

public class RequestHandler {
    private final Socket socket;
    private Dispatcher dispatcher;

    public RequestHandler(Socket socket, Dispatcher dispatcher) {
        this.socket = socket;
        this.dispatcher = dispatcher;
    }

    public void handle() {
        try (socket) {
            System.out.println("Получено входящее подключение");
            byte[] buffer = new byte[8192];
            int n;
            n = socket.getInputStream().read(buffer);

            String rawRequest = new String(buffer, 0, n);
            HttpRequest request = new HttpRequest(rawRequest);
            request.info(true);

            dispatcher.execute(request, socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
