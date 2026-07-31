package ru.otus.java.basic.hw33;

import com.google.gson.Gson;
import ru.otus.java.basic.hw33.app.ItemsService;
import ru.otus.java.basic.hw33.errorshandling.BusinessLogicException;
import ru.otus.java.basic.hw33.errorshandling.ErrorDto;
import ru.otus.java.basic.hw33.processors.*;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class Dispatcher {
    private RequestProcessor defaultNotFoundRequestProcessor;
    private RequestProcessor defaultStaticResourceRequestProcessor;
    private Map<String, RequestProcessor> processors;

    public Dispatcher() {
        ItemsService itemsService = new ItemsService();
        this.processors = new HashMap<>();
        this.processors.put("GET /add", new AddRequestProcessor());
        this.processors.put("GET /hello", new HelloRequestProcessor());
        this.processors.put("GET /items", new GetItemsProcessor(itemsService));
        this.processors.put("POST /items", new CreateItemProcessor(itemsService));
        this.processors.put("DELETE /items", new DeleteItemProcessor(itemsService));
        this.defaultNotFoundRequestProcessor = new DefaultNotFoundRequestProcessor();
        this.defaultStaticResourceRequestProcessor = new DefaultStaticResourceProcessor();
    }

    public void execute(HttpRequest request, OutputStream output) throws IOException {
        if (Files.exists(Path.of("static", request.getUri().substring(1)))) {
            defaultStaticResourceRequestProcessor.execute(request, output);
            return;
        }

        if (!processors.containsKey(request.getRoutingKey())) {
            defaultNotFoundRequestProcessor.execute(request, output);
            return;
        }

        try {
            processors.get(request.getRoutingKey()).execute(request, output);
        } catch (BusinessLogicException e) {
            Gson gson = new Gson();
            ErrorDto errorDto = new ErrorDto(e.getCode(), e.getDescription());

            String response =
                    """
                            HTTP/1.1 400 Bad Request\r
                            Content-Type: application/json\r
                            \r
                            %s
                            """.formatted(gson.toJson(errorDto));

            output.write(response.getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            Gson gson = new Gson();
            ErrorDto errorDto = new ErrorDto("UNEXPECTED_CRITICAL_ERROR", "Произошла непредвиденная ошибка при обработке запроса");

            String response =
                    """
                            HTTP/1.1 500 Internal Server Error\r
                            Content-Type: application/json\r
                            \r
                            %s
                            """.formatted(gson.toJson(errorDto));

            output.write(response.getBytes(StandardCharsets.UTF_8));
        }

    }
}
