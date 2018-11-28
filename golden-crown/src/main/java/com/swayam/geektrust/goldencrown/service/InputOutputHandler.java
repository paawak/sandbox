package com.swayam.geektrust.goldencrown.service;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.swayam.geektrust.goldencrown.service.command.Command;
import com.swayam.geektrust.goldencrown.service.command.CommandFactory;

public class InputOutputHandler {

    private final CommandFactory commandFactory;

    public InputOutputHandler(CommandFactory commandFactory) {
        this.commandFactory = commandFactory;
    }

    public void invokeAndWait(InputStream inputStream, PrintStream standardOut, PrintStream errorOut) {

        Map<String, Object> context = new HashMap<>();

        standardOut.println("Welcome to the Tame of Thrones! Please key-in your questions.");

        try (Scanner scanner = new Scanner(inputStream);) {
            while (scanner.hasNextLine()) {
                String rawCommand = scanner.nextLine();

                try {

                    Command command = getCommandToExecute(rawCommand);

                    String result = command.execute(Collections.unmodifiableMap(context), rawCommand);

                    if ((result != null) && (result.trim().length() > 0)) {
                        standardOut.println(result);
                    }

                    Optional<Entry<String, Object>> contextEntry = command.newContextEntry();

                    if (contextEntry.isPresent()) {
                        context.put(contextEntry.get().getKey(), contextEntry.get().getValue());
                    }

                } catch (Exception e) {
                    errorOut.println(e.getMessage());
                }

            }
        }
    }

    private Command getCommandToExecute(String rawCommand) {
        List<Command> commandsThatCanExecute =
                commandFactory.getAllAvailableCommands().stream().filter(command -> command.canExecute(rawCommand)).collect(Collectors.toList());

        if (commandsThatCanExecute.isEmpty()) {
            return commandFactory.getUnhandledCommand();
        } else if (commandsThatCanExecute.size() > 1) {
            throw new IllegalArgumentException("Too many commands that can potentially execute: " + commandsThatCanExecute);
        }

        return commandsThatCanExecute.get(0);
    }

}
