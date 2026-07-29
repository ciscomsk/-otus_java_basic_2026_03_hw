package ru.otus.java.basic.hw33.processors;

import com.google.gson.Gson;
import ru.otus.java.basic.hw33.HttpRequest;
import ru.otus.java.basic.hw33.app.ItemsService;
import ru.otus.java.basic.hw33.app.dto.ItemDto;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class GetItemsProcessor implements RequestProcessor {
    private ItemsService itemsService;

    public GetItemsProcessor(ItemsService itemsService) {
        this.itemsService = itemsService;
    }

    @Override
    public void execute(HttpRequest request, OutputStream output) throws IOException {
        Gson gson = new Gson();
        String result = null;
        if (!request.containsParam("id")) {
            result = gson.toJson(itemsService.getAll());
        } else {
            long id = Long.parseLong(request.getParam("id"));
            ItemDto itemDto = itemsService.getById(id);
            result = gson.toJson(itemDto);
        }

        String response =
                """
                        HTTP/1.1 200 OK \r
                        Content-Type: application/json\r
                        \r
                        %s
                        """.formatted(result);

        output.write(response.getBytes(StandardCharsets.UTF_8));
    }
}
