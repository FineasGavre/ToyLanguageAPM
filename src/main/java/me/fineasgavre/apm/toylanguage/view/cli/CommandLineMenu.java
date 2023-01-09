package me.fineasgavre.apm.toylanguage.view.cli;

import me.fineasgavre.apm.toylanguage.view.cli.commands.Command;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CommandLineMenu {
    private final Scanner scanner = new Scanner(System.in);
    private final Map<String, Command> commands;

    public CommandLineMenu() {
        commands = new HashMap<>();
    }

    public void addCommand(Command command) {
        commands.put(command.getKey(), command);
    }

    public void show() {
        System.out.println("ToyLanguage Toolkit\nAvailable options:");

        while (true) {
            printMenu();
            System.out.print("Please select an option: ");

            var key = scanner.nextLine();
            var command = commands.get(key);

            if (command == null) {
                System.out.println("Unknown option selected, please try again!");
                continue;
            }

            command.execute();
        }
    }

    private void printMenu() {
        System.out.println();

        for (var command : commands.values()) {
            System.out.println(command.getKey() + " | " + command.getDescription());
        }
    }
}
