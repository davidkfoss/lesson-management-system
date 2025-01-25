package com.example.cli.commands;

import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.Callable;

@Command(name = "get", description = "Retrieve an item by ID.")
public class GetItemCommand implements Callable<Integer> {
    @Option(names = {"-i", "--id"}, description = "ID of the item", required = true)
    private Long id;

    @Override
    public Integer call() throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/items/" + id))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("Item: " + response.body());
        return 0;
    }
}