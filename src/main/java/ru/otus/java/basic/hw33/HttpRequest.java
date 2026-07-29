package ru.otus.java.basic.hw33;

import java.util.HashMap;
import java.util.Map;

public class HttpRequest {
    private String rawRequest;
    private HttpMethod httpMethod;
    private String uri;
    private String body;
    private Map<String, String> params;

    public HttpRequest(String rawRequest) {
        this.rawRequest = rawRequest;
        this.params = new HashMap<>();
        parse();
    }

    public String getUri() {
        return uri;
    }

    public String getBody() {
        return body;
    }

    public String getRoutingKey() {
        return httpMethod + " " + uri;
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

    public boolean containsParam(String key) {
        return params.containsKey(key);
    }

    private void parse() {
        int start = rawRequest.indexOf(" ");
        int end = rawRequest.indexOf(" ", start + 1);
        httpMethod = HttpMethod.valueOf(rawRequest.substring(0, start));
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

        if (httpMethod == HttpMethod.POST) {
            body = rawRequest.substring(rawRequest.indexOf("\r\n\r\n") + 4);
        }
    }
}
