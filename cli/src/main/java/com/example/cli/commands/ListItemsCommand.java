package com.example.cli.commands;

import picocli.CommandLine.Command;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.Callable;

@Command(name = "list", description = "List all items in the database.")
public class ListItemsCommand implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/items"))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("Items: " + response.body());
        return 0;
    }
}