package com.example.cli;

import picocli.CommandLine.Command;
import picocli.CommandLine;

import com.example.cli.commands.CreateItemCommand; // Import subcommand classes
import com.example.cli.commands.ListItemsCommand;
import com.example.cli.commands.GetItemCommand;
import com.example.cli.commands.DeleteItemCommand;

@Command(
    name = "crud-cli",
    mixinStandardHelpOptions = true,
    version = "1.0",
    description = "A simple CLI for interacting with the backend.",
    subcommands = {
        CreateItemCommand.class, // Register your subcommands here
        ListItemsCommand.class,
        GetItemCommand.class,
        DeleteItemCommand.class
    }
)
public class CrudCLI implements Runnable {
    public static void main(String[] args) {
        new CommandLine(new CrudCLI()).execute(args);
    }

    @Override
    public void run() {
        System.out.println("Welcome to CRUD CLI! Use --help for more information.");
    }
}