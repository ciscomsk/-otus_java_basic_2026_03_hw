package ru.otus.java.basic.hw33.processors;

import ru.otus.java.basic.hw33.HttpRequest;
import ru.otus.java.basic.hw33.errorshandling.BusinessLogicException;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class AddRequestProcessor implements RequestProcessor {
    @Override
    public void execute(HttpRequest request, OutputStream output) throws IOException {
        if (!request.containsParam("a")) {
            throw new BusinessLogicException("INVALID_INPUT_DATA", "отсутствует обязательный параметр 'a'");
        }

        if (!request.containsParam("b")) {
            throw new BusinessLogicException("INVALID_INPUT_DATA", "отсутствует обязательный параметр 'b'");
        }

        int a = Integer.parseInt(request.getParam("a"));
        int b = Integer.parseInt(request.getParam("b"));
        String result = a + " + " + b + " = " + (a + b);

        String response =
                """
                        HTTP/1.1 200 OK \r
                        Content-Type: text/html\r
                        \r
                        <html><body><h1>%s</h1></body></html>
                        """.formatted(result);

        output.write(response.getBytes(StandardCharsets.UTF_8));
    }
}
