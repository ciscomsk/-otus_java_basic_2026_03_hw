package ru.otus.java.basic.hw32;

import java.util.HashMap;
import java.util.Map;

public class HttpRequest {
    private String rawRequest;
    private String uri;
    private String httpMethod;
    private Map<String, String> params;

    public HttpRequest(String rawRequest) {
        this.rawRequest = rawRequest;
        this.params = new HashMap<>();
        parse();
    }

    public String getUri() {
        return uri;
    }

    public String getParam(String key) {
        return params.get(key);
    }

    public void info(boolean showRawRequest) {
        if (showRawRequest) {
            System.out.println(rawRequest);
        }

        System.out.println("HTTP METHOD: " + httpMethod);
        System.out.println("URI: " + uri);
        System.out.println("PARAMS: " + params);
    }

    private void parse() {
        int start = rawRequest.indexOf(" ");
        int end = rawRequest.indexOf(" ", start + 1);
        httpMethod = rawRequest.substring(0, start);
        uri = rawRequest.substring(start + 1, end);

        if (uri.contains("?")) {
            String[] tokens = uri.split("[?]");
            uri = tokens[0];

            String[] keysValues = tokens[1].split("&");
            for (String p : keysValues) {
                String[] keyValue = p.split("=", 2);
                params.put(keyValue[0], keyValue[1]);
            }
        }
    }
}
