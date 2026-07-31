package ru.otus.java.basic.hw33;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class HttpRequest {
    private static final Logger logger = LogManager.getLogger(HttpRequest.class);

    private String rawRequest;
    private HttpMethod httpMethod;
    private String uri;
    private Map<String, String> headers;
    private String body;
    private Map<String, String> params;
    private Map<String, String> pathVars;

    public HttpRequest(String rawRequest) {
        this.rawRequest = rawRequest;
        this.params = new HashMap<>();
        this.pathVars = new HashMap<>();
        this.headers = new HashMap<>();
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

    public String getPathVar(String key) {
        return pathVars.get(key);
    }

    public void info() {
        logger.debug(rawRequest);

        logger.info("HTTP METHOD: " + httpMethod);
        logger.info("URI: " + uri);
        logger.info("HEADERS: " + headers);
        logger.info("PARAMS: " + params);
    }

    public boolean containsParam(String key) {
        return params.containsKey(key);
    }

    private void parse() {
        int start = rawRequest.indexOf(" ");
        int end = rawRequest.indexOf(" ", start + 1);
        httpMethod = HttpMethod.valueOf(rawRequest.substring(0, start));
        uri = rawRequest.substring(start + 1, end);


        String rawHeaders = rawRequest.substring(rawRequest.indexOf("\r\n") + 2, rawRequest.indexOf("\r\n\r\n"));
        String[] headersArr = rawHeaders.split("\r\n");
        for (String header : headersArr) {
            String[] headerKV = header.split(": ", 2);
            headers.put(headerKV[0], headerKV[1]);
        }

        if (uri.contains("?")) {
            String[] tokens = uri.split("[?]");
            uri = tokens[0];

            String[] keysValues = tokens[1].split("&");
            for (String p : keysValues) {
                String[] keyValue = p.split("=", 2);
                params.put(keyValue[0], keyValue[1]);
            }
        } else {
            long nSlash = uri.chars().filter(c -> c == '/').count();
            if (nSlash > 1) {
                String maybePathVar = uri.substring(uri.lastIndexOf('/') + 1);
                boolean isNumeric = maybePathVar.chars().allMatch(Character::isDigit);
                if (isNumeric) {
                    uri = uri.substring(0, uri.lastIndexOf('/'));
                    pathVars.put("pathVariable", maybePathVar);
                }
            }
        }

        if (httpMethod == HttpMethod.POST) {
            body = rawRequest.substring(rawRequest.indexOf("\r\n\r\n") + 4);
        }
    }
}
