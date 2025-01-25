package com.example.cli.commands;

import picocli.CommandLine.Option;
import picocli.CommandLine.Command;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.Callable;

@Command(name = "create", description = "Create a new item.")
public class CreateItemCommand implements Callable<Integer> {
    @Option(names = {"-n", "--name"}, description = "Name of the item", required = true)
    private String name;

    @Option(names = {"-d", "--description"}, description = "Description of the item")
    private String description;

    @Override
    public Integer call() throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        String jsonBody = String.format("{\"name\": \"%s\", \"description\": \"%s\"}", name, description);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/items"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("Created item: " + response.body());
        return 0;
    }
}