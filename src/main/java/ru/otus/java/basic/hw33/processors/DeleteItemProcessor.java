package ru.otus.java.basic.hw33.processors;

import ru.otus.java.basic.hw33.HttpRequest;
import ru.otus.java.basic.hw33.app.ItemsService;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class DeleteItemProcessor implements RequestProcessor {
    private ItemsService itemsService;

    public DeleteItemProcessor(ItemsService itemsService) {
        this.itemsService = itemsService;
    }

    @Override
    public void execute(HttpRequest request, OutputStream output) throws IOException {
        itemsService.deleteById(Integer.parseInt(request.getParam("pathVariable")));

        String response =
                """
                        HTTP/1.1 200 OK \r
                        Content-Type: application/json\r
                        \r
                        """;

        output.write(response.getBytes(StandardCharsets.UTF_8));
    }
}
