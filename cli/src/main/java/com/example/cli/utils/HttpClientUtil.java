package com.example.cli.utils;

// Java 11+ HTTP client imports
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

// For handling IO operations and exceptions
import java.io.IOException;

public class HttpClientUtil {
    private static final HttpClient CLIENT = HttpClient.newHttpClient();

    public static HttpResponse<String> sendRequest(HttpRequest request) throws IOException, InterruptedException {
        return CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
    }
}